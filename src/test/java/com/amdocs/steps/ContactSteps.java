package com.amdocs.steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class ContactSteps extends CommonPageSteps {
    @Step("the user in {this} checks that general inquiries block is visible")
    public void checkGeneralInquiriesBlockShouldBeVisible() {
        $("#divAbout1Section").shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    @Override
    public String toString() {
        return "Contact page";
    }
}
