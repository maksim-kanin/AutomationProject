package com.amdocs.core.elements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.function.Supplier;

import static com.codeborne.selenide.Selenide.$;
import static java.util.Objects.requireNonNull;

public abstract class UIElement {
    private final By selector;
    private final SelenideElement element;
    private final Supplier<SelenideElement> context;

    public UIElement(By selector, Supplier<SelenideElement> context) {
        requireNonNull(selector, "Selector must be specified!");
        this.selector = selector;
        this.context = context;
        this.element = context == null ? $(selector) : context.get().$(selector);
    }

    public UIElement(By selector) {
        this(selector, null);
    }

    public SelenideElement getSelenideElement() {
        return element;
    }

    public Button innerButton(By selector) {
        return new Button(selector, this::getSelenideElement);
    }

    @Override
    public String toString() {
        return context == null ?
                "[" + element.toString() + "]" :
                "[Parent: " + context.get().toString() + ", child: " + element.toString() + "]";
    }
}
