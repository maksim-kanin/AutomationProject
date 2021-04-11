package com.tricentis.demowebshop.driver;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;

import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static com.tricentis.demowebshop.config.UITestsConfig.DEV_CONFIG;

public class LocalDriver implements DriverProvider {
    @Override
    public void setupConfiguration() {
        addListener("AllureSelenide", new AllureSelenide()
                .screenshots(false)
                .savePageSource(false));
        Configuration.startMaximized = true;
        Configuration.baseUrl = DEV_CONFIG.getBaseUrl();
    }
}
