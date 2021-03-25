package com.amdocs.steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class NeoPageSteps extends CommonPageSteps {
    @Step("the user in {this} checks that 'Automate Service & Network Operations with Amdocs NEO' block is visible")
    public void checkNeoBlockVisible() {
        $(".app_sp_section_article_cover+.app_sp_section_articles h1").shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    @Override
    public String toString() {
        return "Neo page";
    }
}
