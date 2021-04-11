package com.tricentis.demowebshop.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static java.lang.String.format;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

public class HeaderMenu extends UIElement {
    private static final By CONTENT = cssSelector(".header-menu");
    private static final String TAB_BY_NAME = ".//a[contains(text(),'%s')]";

    public HeaderMenu() {
        super(CONTENT);
    }

    @Step("the user in {this} selects [{tabName}] tab")
    public void selectTab(String tabName) {
        innerLink(xpath(format(TAB_BY_NAME, tabName)));
    }

    @Override
    public String toString() {
        return "Header menu";
    }
}
