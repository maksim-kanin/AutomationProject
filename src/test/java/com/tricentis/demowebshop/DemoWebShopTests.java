package com.tricentis.demowebshop;

import com.tricentis.demowebshop.lifecycle.UITestLifeCycleExtension;
import com.tricentis.demowebshop.steps.CompareProductsSteps;
import com.tricentis.demowebshop.steps.MainPageSteps;
import com.tricentis.demowebshop.steps.ProductCardSteps;
import com.tricentis.demowebshop.steps.cards.SearchProductCardSteps;
import com.tricentis.demowebshop.steps.common.HeaderSteps;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(UITestLifeCycleExtension.class)
@Tag("web")
@Epic("UI tests")
public class DemoWebShopTests {
    private final MainPageSteps mainPageSteps = new MainPageSteps();
    private final HeaderSteps headerSteps = new HeaderSteps();
    private final SearchProductCardSteps searchProductCardSteps = new SearchProductCardSteps();
    private final CompareProductsSteps compareProductsSteps = new CompareProductsSteps();
    private final ProductCardSteps productCardSteps = new ProductCardSteps();

    @Test
    @Feature("Registration")
    @DisplayName("Register new qa_guru_mkanin_17 male user")
    public void registerNewUserTest() {
        mainPageSteps.open();
        headerSteps.openRegisterForm()
                .gender("male")
                .firstName("Maksim")
                .lastName("Kanin")
                .email("qa_guru_mkanin_17@qaguru.ru")
                .password("qwerty")
                .confirmPassword("qwerty")
                .register();
        headerSteps.assertLinkIsVisible("qa_guru_mkanin_17@qaguru.ru");
    }

    @Test
    @Feature("Search")
    @DisplayName("Search Adobe Photoshop Elements 7 text in header search panel")
    public void searchAdobePhotoshopTest() {
        mainPageSteps.open();
        headerSteps.searchText("Adobe Photoshop Elements 7");
        searchProductCardSteps.assertCardIsVisible("Adobe Photoshop Elements 7");
    }

    @Test
    @Feature("Shopping card")
    @DisplayName("Add a featured '14.1-inch Laptop' to shopping card for registered user")
    public void addFeaturedToCardForRegisteredUserTest() {
        mainPageSteps.open();
        headerSteps.openReturningCustomerForm("ShoppingCard")
                .email()
                .password()
                .login();
        mainPageSteps.addFeaturedProductToCart("14.1-inch Laptop");
        headerSteps.assertLinkIsVisible("Shopping cart", 1);
    }

    @Test
    @Feature("Compare list")
    @DisplayName("Remove a product from compare list for registered user")
    public void removeFromCompareForRegisteredUserTest() {
        mainPageSteps.open();
        headerSteps.openReturningCustomerForm("CompareList")
                .email()
                .password()
                .login();
        productCardSteps.addCardToCompare("141-inch-laptop");
        productCardSteps.addCardToCompare("build-your-cheap-own-computer");
        compareProductsSteps.removeProduct("Build your own cheap computer");
        compareProductsSteps.assertProductIsInvisible("Build your own cheap computer");
    }

    @Test
    @Feature("Subscribe")
    @DisplayName("Subscribe on news")
    public void sortAwesomeProductsTest() {
        mainPageSteps.open();
        mainPageSteps.subscribe("subscribe@yandex.ru");
        mainPageSteps.assertSubscriptionIsSuccessful();
    }
}
