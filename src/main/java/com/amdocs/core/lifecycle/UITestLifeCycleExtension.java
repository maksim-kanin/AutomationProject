package com.amdocs.core.lifecycle;

import com.amdocs.core.driver.DriverFactory;
import com.amdocs.core.driver.LocalDriverFactory;
import com.amdocs.core.driver.RemoteDriverFactory;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static com.amdocs.core.helpers.AttachmentsHelpers.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class UITestLifeCycleExtension implements BeforeAllCallback, AfterEachCallback {
    private static final String SUCCESS = "Success";
    private static final String FAILED = "Failed";

    @Override
    public void beforeAll(ExtensionContext context) {
        getFactory().createDriver().init();
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
        if (System.getProperty("environment").equals("remote")) {
            return new RemoteDriverFactory();
        } else {
            return new LocalDriverFactory();
        }
    }

    private String getTestStatus(ExtensionContext context) {
        return context.getExecutionException().isPresent() ? FAILED : SUCCESS;
    }
}
