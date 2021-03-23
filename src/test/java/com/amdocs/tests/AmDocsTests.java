package com.amdocs.tests;

import com.amdocs.core.lifecycle.UITestLifeCycleExtension;
import com.amdocs.steps.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(UITestLifeCycleExtension.class)
@Tag("web")
public class AmDocsTests {
    private final CloudPageSteps cloudSteps = new CloudPageSteps();
    private final MainPageSteps mainSteps = new MainPageSteps();
    private final ContactSteps contactSteps = new ContactSteps();
    private final CareersPageSteps careersPageSteps = new CareersPageSteps();
    private final VacancySearchPageSteps vacancySearchSteps = new VacancySearchPageSteps();
    private final SearchPageSteps searchSteps = new SearchPageSteps();
    private final NeoPageSteps neoSteps = new NeoPageSteps();

    @Test
    @DisplayName("Check that Customer Success block is visible after selection")
    public void selectCloudCustomerSuccessTest() {
        cloudSteps.openUrl();
        cloudSteps.selectTab("Customer Success");
        cloudSteps.checkCustomerSuccessBannerIsVisible();
    }

    @Test
    @DisplayName("Check that contact block is visible after selection")
    public void openContactsTest() {
        mainSteps.openUrl();
        mainSteps.selectFooterTab("Contact");
        contactSteps.checkGeneralInquiriesBlockShouldBeVisible();
    }

    @Test
    @DisplayName("Check 'Test Automation Specialist' is displayed in job search results")
    public void findQaJobTest() {
        mainSteps.openUrl();
        mainSteps.selectFooterTab("Careers");
        careersPageSteps.searchJob("qa");
        vacancySearchSteps.checkVacancyIsPresent("Test Automation Specialist");
    }

    @Test
    @DisplayName("Check 'Pipeline: Video Delivery in the Virtual World' is displayed in search results")
    public void searchDeliveryTest() {
        mainSteps.openUrl();
        mainSteps.search("delivery");
        searchSteps.checkSearchTextPresent("Pipeline: Video Delivery in the Virtual World");
    }

    @Test
    @DisplayName("Check 'Automate Service & Network Operations with Amdocs NEO' block is visible")
    public void learnMoreTest() {
        mainSteps.openUrl();
        mainSteps.openLearnMore();
        neoSteps.checkNeoBlockVisible();
    }
}
