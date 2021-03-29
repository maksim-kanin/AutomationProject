package com.amdocs.core.elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.function.Supplier;

public class Button extends UIElement {
    public Button(By by, Supplier<SelenideElement> context) {
        super(by, context);
    }

    public Button(By by) {
        super(by);
    }

    @Step("the user clicks {this}")
    public void click() {
        this.getSelenideElement().click();
    }
}
