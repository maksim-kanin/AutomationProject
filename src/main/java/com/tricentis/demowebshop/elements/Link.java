package com.tricentis.demowebshop.elements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.function.Supplier;

public class Link extends UIElement {
    public Link(By selector, Supplier<SelenideElement> context) {
        super(selector, context);
    }

    public Link(By selector) {
        super(selector);
    }
}
