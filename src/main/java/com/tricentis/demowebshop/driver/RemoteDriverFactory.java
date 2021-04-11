package com.tricentis.demowebshop.driver;

public class RemoteDriverFactory extends DriverFactory {
    @Override
    public DriverProvider createDriver() {
        return new RemoteDriver();
    }
}
