package com.amdocs.tests;

import com.amdocs.core.lifecycle.UITestLifeCycleExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(UITestLifeCycleExtension.class)
public class MainPageTests {
    @Test
    public void test() {
        assertThat(true).isTrue();
    }
}
