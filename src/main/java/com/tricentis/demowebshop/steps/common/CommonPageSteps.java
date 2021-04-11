package com.tricentis.demowebshop.steps.common;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;

public class CommonPageSteps {

    @Step("the user opens page: http://demowebshop.tricentis.com/{path}")
    public void openPage(String path) {
        open(path);
    }
}
