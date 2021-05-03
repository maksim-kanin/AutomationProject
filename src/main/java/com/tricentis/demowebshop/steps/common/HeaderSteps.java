package com.tricentis.demowebshop.steps.common;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.tricentis.demowebshop.elements.common.Header;
import com.tricentis.demowebshop.elements.forms.RegisterForm;
import com.tricentis.demowebshop.elements.forms.ReturningCustomerForm;
import io.qameta.allure.Step;

import java.time.Duration;

public class HeaderSteps {
    private final Header header = new Header();

    public void searchText(String text) {
        header.search(text);
    }

    public void clickLink(String name) {
        header.clickLink(name);
    }

    public RegisterForm openRegisterForm() {
        return header.openRegisterForm();
    }

    public ReturningCustomerForm openReturningCustomerForm(String userRole) {
        return header.openReturningCustomerForm(userRole);
    }

    @Step("Check that link with [{name}] name is visible")
    public void assertLinkIsVisible(String name) {
        SelenideElement link = header.getLink(name).getSelenideElement();
        link.shouldBe(Condition.visible, Duration.ofSeconds(30));
    }

    @Step("Check that link with [{name}] name and [{count}] count is visible")
    public void assertLinkIsVisible(String name, int count) {
        SelenideElement link = header.getLinkWithCount(name, count).getSelenideElement();
        link.shouldBe(Condition.visible, Duration.ofSeconds(30));
    }
}
