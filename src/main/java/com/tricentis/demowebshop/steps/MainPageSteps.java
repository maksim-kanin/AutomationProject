package com.tricentis.demowebshop.steps;

import com.codeborne.selenide.SelenideElement;
import com.tricentis.demowebshop.elements.Button;
import com.tricentis.demowebshop.elements.TextField;
import com.tricentis.demowebshop.elements.cards.FeaturedProductCard;
import com.tricentis.demowebshop.elements.common.HeaderMenu;
import com.tricentis.demowebshop.steps.common.CommonPageSteps;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

public class MainPageSteps {
    private final CommonPageSteps commonPageSteps = new CommonPageSteps();

    public void open() {
        commonPageSteps.openPage("");
    }

    public void selectTab(String tabName) {
        new HeaderMenu().selectTab(tabName);
    }

    @Step("the user adds to cart featured [{productName}] product")
    public void addFeaturedProductToCart(String productName) {
        new FeaturedProductCard(productName).addToCart();
    }

    @Step("the user subscribes on news via email [{email}]")
    public void subscribe(String email) {
        new TextField(cssSelector("input#newsletter-email")).enterText(email);
        Button subscribeButton = new Button(id("newsletter-subscribe-button"));
        subscribeButton.click();
        subscribeButton.getSelenideElement().shouldBe(not(visible));
    }

    @Step("Check that successful subscription label is visible")
    public void assertSubscriptionIsSuccessful() {
        SelenideElement label = $("div#newsletter-result-block");
        assertThat(label.getText().isEmpty())
                .isFalse();
    }
}