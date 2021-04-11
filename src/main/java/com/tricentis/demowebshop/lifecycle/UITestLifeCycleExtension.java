package com.tricentis.demowebshop.lifecycle;

import com.tricentis.demowebshop.driver.DriverFactory;
import com.tricentis.demowebshop.driver.LocalDriverFactory;
import com.tricentis.demowebshop.driver.RemoteDriverFactory;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.tricentis.demowebshop.config.UITestsConfig.DEV_CONFIG;
import static core.helpers.AttachmentsHelpers.*;

public class UITestLifeCycleExtension implements BeforeAllCallback, AfterEachCallback {
    private static final String SUCCESS = "Success";
    private static final String FAILED = "Failed";

    @Override
    public void beforeAll(ExtensionContext context) {
        getFactory().createDriver().setupConfiguration();
    }

    @Override
    public void afterEach(ExtensionContext context) {
        if (getTestStatus(context).equals(FAILED)) {
            attachScreenshot("Page screenshot");
            attachPageSource();
            attachVideo();
        }
        closeWebDriver();
    }

    private DriverFactory getFactory() {
        if (DEV_CONFIG.getBrowserInstantiationType().equals("remote")) {
            return new RemoteDriverFactory();
        } else {
            return new LocalDriverFactory();
        }
    }

    private String getTestStatus(ExtensionContext context) {
        return context.getExecutionException().isPresent() ? FAILED : SUCCESS;
    }
}
