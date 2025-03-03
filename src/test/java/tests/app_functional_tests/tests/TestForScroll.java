package tests.app_functional_tests.tests;

import io.qameta.allure.Story;
import org.testng.annotations.Test;
import tests.app_functional_tests.LandingPageSkipping;

public class TestForScroll extends LandingPageSkipping {

    @Story("Scroll Functionality")
    @Test(description = "Check the scroll functionality, including scrolling through an article and validating references.")
    public void testForScrollFunctionality() {
        // Scroll to the 'Picture of the Day' section
        homePage.scrollToPictureOfTheDay();

        // Validate the existence of buttons for the 'Picture of the Day' article
        softAssert.assertTrue(homePage.doesDownloadPictureOfTheDayButtonExist(),
                "Download button for the 'Picture of the Day' article is not present.");

        softAssert.assertTrue(homePage.doesSharePictureOfTheDayButtonExist(),
                "Share button for the 'Picture of the Day' article is not present.");

        // Final assertion to aggregate all soft assertions
        softAssert.assertAll();
    }

}
