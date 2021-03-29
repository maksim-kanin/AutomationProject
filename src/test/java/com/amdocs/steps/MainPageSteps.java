package com.amdocs.steps;

import com.amdocs.core.elements.Button;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
import static org.openqa.selenium.By.cssSelector;

public class MainPageSteps extends CommonPageSteps {

    private final SelenideElement searchPopup = $("form[ng-submit=\"search(searchTerm)\"] input");

    @Step("the user opens {this}")
    public void openUrl() {
        open("");
        clickAcceptCookieIfNeeded();
        $(".app_sp_header_section #HomePageTopHeaderLeft").shouldBe(Condition.visible);
    }

    @Step("the user in {this} selects tab [{tabName}]")
    public void selectFooterTab(String tabName) {
        String tabSelector = "//*[@class='app_toolbar']//*[contains(@class,'app_toolbar_navitem_link') and .='%s']";
        $x(format(tabSelector, tabName)).click();
    }

    @Step("the user in {this} opens search popup")
    public void openSearchPopup() {
        new Button(cssSelector(".app_tm_search_button")).click();
        searchPopup.shouldBe(Condition.visible);
    }

    @Step("the user in {this} searches text [{text}]")
    public void search(String text) {
        if (!searchPopup.isDisplayed()) {
            openSearchPopup();
        }
        searchPopup.setValue(text).pressEnter();
        $(".cm-general-search__section").shouldBe(Condition.visible, Duration.ofSeconds(30));
    }

    @Step("the user in {this} opens learn more block")
    public void openLearnMore() {
        $("#HomePageTopHeaderLeft .header-content__container .svg-button").click();
    }

    @Override
    public String toString() {
        return "Main page";
    }
}
