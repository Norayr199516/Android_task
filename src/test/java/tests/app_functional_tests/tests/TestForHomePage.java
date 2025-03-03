package tests.app_functional_tests.tests;

import constants.Constants;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import tests.app_functional_tests.LandingPageSkipping;

public class TestForHomePage extends LandingPageSkipping {

    @Story("Home Page view")
    @Test(description = "Verify that the home screen is displayed correctly.")
    public void homeScreenTest() {
        // Check if the search bar is displayed
        softAssert.assertTrue(homePage.isSearchBarDisplayed(),
                "The search bar is not displayed on the home screen.");

        // Check if the navigation bar is displayed
        softAssert.assertTrue(homePage.isNavigationBarDisplayed(),
                "The navigation bar is not displayed on the home screen.");

        // Validate the placeholder text in the search bar
        softAssert.assertEquals(homePage.getSearchBarPlaceholder(), Constants.SEARCH_BAR_PLACEHOLDER,
                "The placeholder text in the search bar is incorrect.");

        // Perform final assertion to verify all checks
        softAssert.assertAll();
    }

}
