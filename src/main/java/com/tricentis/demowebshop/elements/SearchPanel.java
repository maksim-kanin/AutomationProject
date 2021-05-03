package com.tricentis.demowebshop.elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.cssSelector;

public class SearchPanel extends UIElement {
    private static final By SEARCH_BUTTON = cssSelector(".search-box-button");

    public SearchPanel(By selector, Supplier<SelenideElement> context) {
        super(selector, context);
    }

    public SearchPanel(By selector) {
        super(selector);
    }

    @Step("the user types [{text}] in [{this}]")
    public void typeText(String text) {
        this.getSelenideElement().$("input[type='text']").setValue(text);
    }

    @Step("the user clicks 'Search' button in [{this}]")
    public void search() {
        innerButton(SEARCH_BUTTON).click();
    }
}
