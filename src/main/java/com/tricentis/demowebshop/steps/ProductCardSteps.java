package com.tricentis.demowebshop.steps;

import com.tricentis.demowebshop.elements.cards.ProductCard;
import com.tricentis.demowebshop.steps.common.CommonPageSteps;
import io.qameta.allure.Step;

public class ProductCardSteps {
    @Step("the user adds to compare [{cardName}] card")
    public void addCardToCompare(String cardName) {
        new CommonPageSteps().openPage(cardName);
        new ProductCard().addToCompare();
    }
}
