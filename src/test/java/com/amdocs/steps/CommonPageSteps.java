package com.amdocs.steps;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;

public class CommonPageSteps {
    // Remove the method, when desired capabilities will be found
    protected void clickAcceptCookieIfNeeded() {
        ElementsCollection acceptButtons = $$("button#onetrust-accept-btn-handler");
        if (!acceptButtons.isEmpty()) {
            acceptButtons.first().click();
        }
    }
}
