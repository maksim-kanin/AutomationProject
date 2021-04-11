package com.tricentis.demowebshop.driver;

public class LocalDriverFactory extends DriverFactory {
    @Override
    public DriverProvider createDriver() {
        return new LocalDriver();
    }
}
