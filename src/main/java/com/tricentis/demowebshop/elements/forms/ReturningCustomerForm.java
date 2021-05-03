package com.tricentis.demowebshop.elements.forms;

import com.tricentis.demowebshop.elements.UIElement;
import com.tricentis.demowebshop.utils.credentials.CredentialsService;
import com.tricentis.demowebshop.utils.credentials.beans.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static java.lang.String.format;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

public class ReturningCustomerForm extends UIElement {
    private static final By CONTENT = cssSelector(".returning-wrapper");
    private static final String TEXTFIELD_BY_NAME = ".//label[.='%s']/following-sibling::input";
    private final User user;

    public ReturningCustomerForm(String userRole) {
        super(CONTENT);
        this.user = new CredentialsService().getUser(userRole);
    }

    @Step("the user in [{this}] enters [{email}] email")
    public ReturningCustomerForm email() {
        innerTextField(xpath(format(TEXTFIELD_BY_NAME, "Email:"))).enterText(user.getName());
        return this;
    }

    @Step("the user in [{this}] enters [{password}] password")
    public ReturningCustomerForm password() {
        innerTextField(xpath(format(TEXTFIELD_BY_NAME, "Password:"))).enterText(user.getPassword());
        return this;
    }

    @Step("the user in [{this}] clicks 'Log in' button")
    public void login() {
        innerButton(cssSelector(".login-button")).click();
    }

    @Override
    public String toString() {
        return "Returning customer form";
    }
}
