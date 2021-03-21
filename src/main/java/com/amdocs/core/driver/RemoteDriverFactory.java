package com.amdocs.core.driver;

public class RemoteDriverFactory extends DriverFactory {
    @Override
    public DriverProvider createDriver() {
        return new RemoteDriver();
    }
}
