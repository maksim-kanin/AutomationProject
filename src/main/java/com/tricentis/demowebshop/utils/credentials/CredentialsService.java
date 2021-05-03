package com.tricentis.demowebshop.utils.credentials;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.tricentis.demowebshop.utils.credentials.beans.Credentials;
import com.tricentis.demowebshop.utils.credentials.beans.User;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.tricentis.demowebshop.config.DevConfig.DEV_CONFIG;

//todo Fix it for execution in several threads
public class CredentialsService implements CredentialsProvider {
    public static Map<String, String> LOCK_USERS_MAP = new HashMap<>();

    @Override
    public User getUser(String userRole) {
        Credentials[] credentials = yamlToBean(getUsersInputStream("users.yaml"), Credentials[].class);
        User user = Arrays.stream(credentials)
                .filter(c -> c.getRole().equals(userRole))
                .map(Credentials::getUsers)
                .collect(Collectors.toList())
                .get(0)
                .stream()
                .filter(u -> !isUserLocked(u))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Unable to get user"));
        lockUser(user);
        return user;
    }

    //todo Add user lock timestamp
    public void lockUser(User user) {
        if (!LOCK_USERS_MAP.isEmpty()) {
            LOCK_USERS_MAP.put(user.getName(), getSessionId() + DEV_CONFIG.getBrowserName());
        }
    }

    //todo
    public void releaseUser(User user) {

    }

    public boolean isUserLocked(User user) {
        if (!LOCK_USERS_MAP.isEmpty()) {
            String name = user.getName();
            return LOCK_USERS_MAP.get(name).equals(getSessionId() + DEV_CONFIG.getBrowserName());
        }
        return false;
    }

    private <T> T yamlToBean(InputStream pathToYAML, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        T bean;
        try {
            bean = mapper.readValue(pathToYAML, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bean;
    }

    private InputStream getUsersInputStream(String usersYaml) {
        return Objects.requireNonNull(Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(usersYaml));
    }

    private String getSessionId() {
        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
    }
}
