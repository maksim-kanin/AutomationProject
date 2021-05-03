package com.tricentis.demowebshop.elements.cards;

import com.tricentis.demowebshop.elements.UIElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static org.openqa.selenium.By.cssSelector;

public class ProductCard extends UIElement {
    private static final By CONTENT = cssSelector(".product-details-page");
    private static final By ADD_TO_COMPARE = cssSelector(".add-to-compare-list-button");

    public ProductCard() {
        super(CONTENT);
    }

    @Step("the user in [{this}] adds product in compare list")
    public void addToCompare() {
        innerButton(ADD_TO_COMPARE).click();
    }

    @Override
    public String toString() {
        return "Product card";
    }
}
