package com.tricentis.demowebshop.elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.function.Supplier;

public class TextField extends UIElement {
    public TextField(By selector, Supplier<SelenideElement> context) {
        super(selector, context);
    }

    public TextField(By selector) {
        super(selector);
    }

    @Step("the user enters [{text}] text in [{this}]")
    public void enterText(String text) {
        this.getSelenideElement().setValue(text);
    }
}
