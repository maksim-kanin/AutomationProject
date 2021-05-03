package com.tricentis.demowebshop.driver;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static com.tricentis.demowebshop.config.DevConfig.DEV_CONFIG;

public class RemoteDriver implements DriverProvider {
    @Override
    public void setupConfiguration() {
        addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;
        Configuration.browser = DEV_CONFIG.getBrowserName();
        Configuration.baseUrl = DEV_CONFIG.getBaseUrl();
        Configuration.browserVersion = String.valueOf(DEV_CONFIG.getBrowserVersion());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = String.format("https://%s:%s@" + DEV_CONFIG.getSelenoidUrl() + "/wd/hub",
                DEV_CONFIG.getRemoteDriverUser(),
                DEV_CONFIG.getRemoteDriverPassword());
    }
}
