package com.amdocs.core.driver;

import io.qameta.allure.selenide.AllureSelenide;

import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class LocalDriver implements DriverProvider {
    @Override
    public void init() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(false).savePageSource(false));
    }
}
