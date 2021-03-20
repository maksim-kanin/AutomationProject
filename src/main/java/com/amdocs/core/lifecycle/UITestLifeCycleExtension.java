package com.amdocs.core.lifecycle;

import com.amdocs.core.notifications.bots.MessageProvider;
import com.amdocs.core.notifications.bots.SlackBot;
import com.amdocs.core.notifications.bots.TelegramBot;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static com.amdocs.core.config.UITestsConfig.DEV_CONFIG;

public class UITestLifeCycleExtension implements BeforeAllCallback, AfterEachCallback, AfterAllCallback {
    private static final String SUCCESS = "Success";
    private static final String FAILED = "Failed";

    @Override
    public void beforeAll(ExtensionContext context) {

    }

    @Override
    public void afterEach(ExtensionContext context) {

    }

    @Override
    public void afterAll(ExtensionContext context) {
        messageProvider().send();
    }

    private MessageProvider messageProvider() {
        final String botType = DEV_CONFIG.getNotificationBot();
        switch (botType) {
            case "telegram":
                return new TelegramBot();
            case "slack":
                return new SlackBot();
            default:
                throw new RuntimeException("Unsupported botType type " + botType);
        }
    }

    private String getTestStatus(ExtensionContext context) {
        return context.getExecutionException().isPresent() ? FAILED : SUCCESS;
    }
}
