package tests.app_functional_tests;

import io.qameta.allure.Step;
import org.testng.annotations.BeforeClass;
import tests.BaseTest;

public class LandingPageSkipping extends BaseTest {

    @Step("Skip the landing page.")
    @BeforeClass(alwaysRun = true, description = "Skip the landing page.")
    public void skipTheLandingPage(){
        landingPage
                .waitForMainContentToBeVisible()
                .clickOnSkipButton()
                .waitForHomePageToBeAvailable();
    }
}
