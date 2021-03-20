package com.amdocs.core.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

public interface UITestsConfig extends Config {
    UITestsConfig DEV_CONFIG = ConfigFactory.create(UITestsConfig.class);

    @Key("baseUrl")
    String getBaseUrl();

    @Key("browser.name")
    @DefaultValue("Chrome")
    String getBrowserName();

    @Key("browser.version")
    @DefaultValue("87")
    int getBrowserVersion();

    @Key("notification.bot")
    @DefaultValue("telegram")
    String getNotificationBot();
}
