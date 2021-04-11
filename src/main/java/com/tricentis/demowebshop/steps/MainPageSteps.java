package com.tricentis.demowebshop.steps;

import com.tricentis.demowebshop.elements.HeaderMenu;
import com.tricentis.demowebshop.steps.common.CommonPageSteps;

import static com.tricentis.demowebshop.config.UITestsConfig.DEV_CONFIG;

public class MainPageSteps {
    public void open() {
        new CommonPageSteps().openPage(DEV_CONFIG.getBaseUrl());
    }

    public void selectTab(String tabName) {
        new HeaderMenu().selectTab(tabName);
    }
}

