package com.amdocs.core.driver;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.amdocs.core.config.UITestsConfig.DEV_CONFIG;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class RemoteDriver implements DriverProvider {
    @Override
    public void init() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;
        Configuration.browser = DEV_CONFIG.getBrowserName();
        Configuration.baseUrl = DEV_CONFIG.getBaseUrl();
        Configuration.browserVersion = String.valueOf(DEV_CONFIG.getBrowserVersion());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = String.format(System.getProperty("web.remote.driver"),
                DEV_CONFIG.getRemoteDriverUser(),
                DEV_CONFIG.getRemoteDriverPassword());
    }
}
