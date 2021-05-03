package com.tricentis.demowebshop.elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.function.Supplier;

public class Link extends UIElement {
    public Link(By selector, Supplier<SelenideElement> context) {
        super(selector, context);
    }

    public Link(By selector) {
        super(selector);
    }

    @Step("the user clicks [{this}]")
    public void click() {
        this.getSelenideElement().click();
    }
}
