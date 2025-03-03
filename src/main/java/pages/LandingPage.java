package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends BasePage {

    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id='org.wikipedia:id/fragment_container']")
    WebElement mainContent;

    @FindBy(xpath = "//android.widget.Button[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']")
    WebElement skipButton;

    public LandingPage(AppiumDriver driver) {
        super(driver);
    }

    public LandingPage waitForMainContentToBeVisible() {
        waitForElementToBeVisible(mainContent);
        logger.info("The main content of the landing page is loaded.");
        return this;
    }

    public HomePage clickOnSkipButton() {
        clickOn(skipButton);
        logger.info("Click on Skip Button.");
        return new HomePage(driver);
    }
}
