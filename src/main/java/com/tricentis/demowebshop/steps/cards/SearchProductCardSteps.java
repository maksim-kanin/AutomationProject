package com.tricentis.demowebshop.steps.cards;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.tricentis.demowebshop.elements.cards.SearchProductCard;
import io.qameta.allure.Step;

import java.time.Duration;

public class SearchProductCardSteps {
    @Step("Check that card with [{name}] name is visible")
    public void assertCardIsVisible(String name) {
        SelenideElement card = new SearchProductCard(name).getSelenideElement();
        card.shouldBe(Condition.visible, Duration.ofSeconds(30));
    }
}
