package com.amdocs.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class CareersPageSteps {
    @Step("the user in {this} searches job [{jobName}]")
    public void searchJob(String jobName) {
        SelenideElement searchField = $("#keywordsearch-q");
        searchField.shouldBe(Condition.visible, Duration.ofSeconds(30));
        searchField.setValue(jobName);
        searchField.pressEnter();
    }

    @Override
    public String toString() {
        return "Careers page";
    }
}
