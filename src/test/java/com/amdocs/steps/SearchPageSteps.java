package com.amdocs.steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$;

public class SearchPageSteps extends CommonPageSteps {
    @Step("the user in {this} checks that search results contains [{text}]")
    public void checkSearchTextPresent(String text) {
        $$(".search-result").find(Condition.text(text));
    }

    @Override
    public String toString() {
        return "Search page";
    }
}
