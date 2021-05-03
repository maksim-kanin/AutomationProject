package com.tricentis.demowebshop.lifecycle;

import com.tricentis.demowebshop.driver.DriverFactory;
import com.tricentis.demowebshop.driver.LocalDriverFactory;
import com.tricentis.demowebshop.driver.RemoteDriverFactory;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.tricentis.demowebshop.config.DevConfig.DEV_CONFIG;
import static com.tricentis.demowebshop.helpers.AttachmentsHelpers.attachScreenshot;
import static com.tricentis.demowebshop.helpers.AttachmentsHelpers.attachVideo;
import static com.tricentis.demowebshop.utils.credentials.CredentialsService.LOCK_USERS_MAP;

public class UITestLifeCycleExtension implements BeforeEachCallback, AfterEachCallback, AfterAllCallback {
    private static final String SUCCESS = "Success";
    private static final String FAILED = "Failed";

    @Override
    public void beforeEach(ExtensionContext context) {
        getFactory().createDriver().setupConfiguration();
    }

    @Override
    public void afterEach(ExtensionContext context) {
        if (getTestStatus(context).equals(FAILED)) {
            attachScreenshot("Page screenshot");
            attachVideo();
        }
        closeWebDriver();
    }

    @Override
    public void afterAll(ExtensionContext context) {
        LOCK_USERS_MAP.clear();
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
