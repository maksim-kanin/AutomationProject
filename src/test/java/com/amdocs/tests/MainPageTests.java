package com.amdocs.tests;

import com.amdocs.core.lifecycle.UITestLifeCycleExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.open;

@ExtendWith(UITestLifeCycleExtension.class)
public class MainPageTests {
    @Test
    public void openMainPage() {
        open("");
    }
}
