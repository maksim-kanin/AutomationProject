package com.tricentis.demowebshop.elements.forms;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.tricentis.demowebshop.elements.Button;
import com.tricentis.demowebshop.elements.UIElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;
import static org.openqa.selenium.By.*;

public class RegisterForm extends UIElement {
    private static final By CONTENT = cssSelector("form[action='/register']");
    private static final String GENDER_BY_NAME = "#gender-%s";
    private static final String TEXTFIELD_BY_NAME = ".//label[.='%s']/following-sibling::input";

    public RegisterForm() {
        super(CONTENT);
    }

    @Step("the user in [{this}] selects [{type}] type")
    public RegisterForm gender(String type) {
        $(format(GENDER_BY_NAME, type)).click();
        return this;
    }

    @Step("the user in [{this}] enters [{name}] name")
    public RegisterForm firstName(String name) {
        innerTextField(xpath(format(TEXTFIELD_BY_NAME, "First name:"))).enterText(name);
        return this;
    }

    @Step("the user in [{this}] enters [{name}] name")
    public RegisterForm lastName(String name) {
        innerTextField(xpath(format(TEXTFIELD_BY_NAME, "Last name:"))).enterText(name);
        return this;
    }

    @Step("the user in [{this}] enters [{email}] email")
    public RegisterForm email(String email) {
        innerTextField(xpath(format(TEXTFIELD_BY_NAME, "Email:"))).enterText(email);
        return this;
    }

    @Step("the user in [{this}] enters [{password}] password")
    public RegisterForm password(String password) {
        innerTextField(xpath(format(TEXTFIELD_BY_NAME, "Password:"))).enterText(password);
        return this;
    }

    @Step("the user in [{this}] enters [{confirmPassword}] confirmPassword")
    public RegisterForm confirmPassword(String confirmPassword) {
        innerTextField(xpath(format(TEXTFIELD_BY_NAME, "Confirm password:"))).enterText(confirmPassword);
        return this;
    }

    @Step("the user in [{this}] clicks 'Register' button")
    public void register() {
        innerButton(id("register-button")).click();
        SelenideElement continueButton = new Button(cssSelector(".register-continue-button")).getSelenideElement();
        continueButton.shouldBe(Condition.visible, Duration.ofSeconds(30));
    }

    @Override
    public String toString() {
        return "Register form";
    }
}
