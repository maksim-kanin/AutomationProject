package com.amdocs.steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class CloudPageSteps extends CommonPageSteps {
    private static final String CLOUD_PATH = "amdocsone/services-hybrid-operations/cloud-practice";

    @Step("the user opens {this}")
    public void openUrl() {
        open(CLOUD_PATH);
        clickAcceptCookieIfNeeded();
        $(".app-domains-links-section").shouldBe(Condition.visible);
    }

    @Step("the user in {this} selects tab [{tabName}]")
    public void selectTab(String tabName) {
        String tabSelector = "//div[contains(@class,'link-item-orange') and .='%s']";
        $x(format(tabSelector, tabName)).click();
    }

    @Step("the user in {this} checks that customer success banner is visible")
    public void checkCustomerSuccessBannerIsVisible() {
        $("#divCustomerEvidenceSection .evidence_title").shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    @Override
    public String toString() {
        return "Cloud page";
    }
}
