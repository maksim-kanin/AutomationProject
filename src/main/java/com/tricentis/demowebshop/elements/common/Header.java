package com.tricentis.demowebshop.elements.common;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.tricentis.demowebshop.elements.Link;
import com.tricentis.demowebshop.elements.SearchPanel;
import com.tricentis.demowebshop.elements.UIElement;
import com.tricentis.demowebshop.elements.forms.RegisterForm;
import com.tricentis.demowebshop.elements.forms.ReturningCustomerForm;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

public class Header extends UIElement {
    private static final By CONTENT = cssSelector("div.header");
    private static final String LINK_BY_NAME = ".//a[.//span[contains(text(),'%s')] or contains(text(),'%s')]";
    private static final String LINK_BY_NAME_AND_COUNT = ".//a[./span[.='%s'] and ./span[.='(%s)']]";
    private static final By SEARCH_PANEL = cssSelector(".search-box");
    private final SearchPanel searchPanel;

    public Header() {
        super(CONTENT);
        this.searchPanel = new SearchPanel(SEARCH_PANEL);
    }

    public Link getLink(String linkName) {
        return innerLink(xpath(format(LINK_BY_NAME, linkName, linkName)));
    }

    public Link getLinkWithCount(String linkName, int count) {
        return innerLink(xpath(format(LINK_BY_NAME_AND_COUNT, linkName, count)));
    }

    @Step("the user in [{this}] opens register form")
    public RegisterForm openRegisterForm() {
        SelenideElement form = $("form[action='/register']");
        getLink("Register").click();
        form.shouldBe(Condition.visible, Duration.ofSeconds(30));
        return new RegisterForm();
    }

    @Step("the user in [{this}] opens returning customer form")
    public ReturningCustomerForm openReturningCustomerForm(String userRole) {
        SelenideElement form = $(".returning-wrapper");
        getLink("Log in").click();
        form.shouldBe(Condition.visible, Duration.ofSeconds(30));
        return new ReturningCustomerForm(userRole);
    }

    @Step("the user in [{this}] search [{text}] text")
    public void search(String text) {
        searchPanel.typeText(text);
        searchPanel.search();
    }

    @Step("the user in [{this}] clicks on link with [{name}] name")
    public void clickLink(String name) {
        getLink(name).click();
    }

    @Override
    public String toString() {
        return "Header";
    }
}
