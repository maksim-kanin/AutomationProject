package com.tricentis.demowebshop.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class CompareProductsSteps {
    @Step("the user remove [{productName}] product from compare list")
    public void removeProduct(String productName) {
        ElementsCollection removeButtons = $$(".remove-button");
        SelenideElement productLabel = $($x(format("//*[@class='product-name']//a[contains(text(),'%s')]", productName)));
        int labelX = productLabel.getLocation().getX();
        int labelWidth = productLabel.getSize().getWidth();
        int labelCenter = labelX + labelWidth / 2;
        removeButtons.stream()
                .filter(button -> {
                    int buttonX = button.getLocation().getX();
                    int buttonWidth = button.getSize().getWidth();
                    int buttonCenter = buttonX + buttonWidth / 2;
                    return Math.abs(labelCenter - buttonCenter) <= 5;
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("There is no 'Remove' button for product: " + productName))
                .click();
    }

    @Step("Check that product with [{productName}] name is invisible")
    public void assertProductIsInvisible(String productName) {
        SelenideElement productLabel = $($x(format("//*[@class='product-name']//a[contains(text(),'%s')]", productName)));
        productLabel.shouldBe(not(Condition.visible), Duration.ofSeconds(30));
    }
}
