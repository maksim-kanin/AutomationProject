package com.tricentis.demowebshop.elements.cards;

import com.codeborne.selenide.Condition;
import com.tricentis.demowebshop.elements.UIElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

public class FeaturedProductCard extends UIElement {
    private static final String CONTENT_BY_NAME = "//*[contains(@class,'product-grid')]//*[@class='product-item' and .//*[contains(text(),'%s')]]";
    private static final By ADD_TO_CART_BUTTON = cssSelector(".product-box-add-to-cart-button");

    public FeaturedProductCard(String name) {
        super(xpath(format(CONTENT_BY_NAME, name)));
    }

    @Step("the user in [{this}] clicks 'Add to cart' button")
    public void addToCart() {
        innerButton(ADD_TO_CART_BUTTON).click();
        $(".bar-notification.success").shouldBe(Condition.visible, Duration.ofSeconds(30));
    }
}
