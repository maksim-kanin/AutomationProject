package com.amdocs.steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

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
    public void assertCustomerSuccessBannerIsVisible() {
        assertThat($("#divCustomerEvidenceSection .evidence_title").isDisplayed())
                .as("Customer Success banner should be visible, but it wasn't!")
                .isTrue();
    }

    @Override
    public String toString() {
        return "Cloud page";
    }
}
