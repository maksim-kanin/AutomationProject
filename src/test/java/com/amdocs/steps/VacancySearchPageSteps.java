package com.amdocs.steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class VacancySearchPageSteps {

    @Step("the user in {this} checks that search results contains [{vacancy}]")
    public void checkVacancyIsPresent(String vacancy) {
        $("h1.keyword-title").shouldBe(Condition.visible, Duration.ofSeconds(30));
        $$(".jobTitle.hidden-phone").find(Condition.text(vacancy));
    }

    @Override
    public String toString() {
        return "Vacancy search page";
    }
}
