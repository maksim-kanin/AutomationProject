package com.tricentis.demowebshop.elements.cards;

import com.tricentis.demowebshop.elements.UIElement;
import org.openqa.selenium.By;

import static java.lang.String.format;

public class SearchProductCard extends UIElement {
    private static final String CARD_BY_NAME = "//*[@class='search-results']//*[@class='item-box' and .//*[contains(text(),'%s')]]";

    public SearchProductCard(String name) {
        super(By.xpath(format(CARD_BY_NAME, name)));
    }
}
