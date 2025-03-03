package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    private final String PICTURE_OF_THE_DAY_XPATH = "//android.widget.TextView[@resource-id='org.wikipedia:id/view_card_header_title' and @text='Picture of the day']";
    private final String DOWNLOAD_BUTTON_XPATH = "//android.widget.Button[@resource-id='org.wikipedia:id/view_featured_image_card_download_button']";
    private final String SHARE_BUTTON_XPATH = "//android.widget.Button[@resource-id='org.wikipedia:id/view_featured_image_card_share_button']";

    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.LinearLayout")
    protected WebElement homePage;

    @FindBy(xpath = "//android.widget.ImageView[@resource-id='org.wikipedia:id/main_toolbar_wordmark']")
    protected WebElement homePageTitle;

    @FindBy(xpath = "//androidx.cardview.widget.CardView[@resource-id='org.wikipedia:id/search_container']/android.widget.TextView")
    protected WebElement searchField;

    @FindBy(xpath = "//android.widget.ImageView[@content-desc='Search Wikipedia']")
    protected WebElement searchButton;

    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id='org.wikipedia:id/main_nav_tab_layout']")
    protected WebElement navigationBar;

    @FindBy(xpath = "//android.widget.TextView[@resource-id='org.wikipedia:id/footerActionButton']")
    protected WebElement todayOnWikipedia;

    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    public HomePage waitForHomePageToBeAvailable() {
        waitForElementToBeVisible(homePage);
        return this;
    }

    public String getHomePageTitle() {
        logger.info("Home page title is - " + homePageTitle.getText());
        return homePageTitle.getText();
    }

    public String getSearchBarPlaceholder() {
        logger.info("Search bar placeholder is - " + searchField.getText());
        return searchField.getText();
    }

    public boolean isSearchBarDisplayed() {
        return searchField.isDisplayed();
    }

    public boolean isNavigationBarDisplayed() {
        return navigationBar.isDisplayed();
    }

    public HomePage scrollToPictureOfTheDay() {
        scrollTo(PICTURE_OF_THE_DAY_XPATH);
        return this;
    }

    public boolean doesDownloadPictureOfTheDayButtonExist() {
        boolean isDownloadButtonExists = false;
        for (int i = 0; i < 2; i++) {
            try {
                driver.findElement(By.xpath(DOWNLOAD_BUTTON_XPATH));
                isDownloadButtonExists = true;
                logger.info("Download button is found.");
            } catch (Exception e) {
                scroll();
            }
        }
        return isDownloadButtonExists;
    }

    public boolean doesSharePictureOfTheDayButtonExist() {
        boolean isShareButtonExists = false;
        for (int i = 0; i < 2; i++) {
            try {
                driver.findElement(By.xpath(SHARE_BUTTON_XPATH));
                isShareButtonExists = true;
                logger.info("Share button is found.");
            } catch (Exception e) {
                scroll();
            }
        }
        return isShareButtonExists;
    }

    public SearchResultsPage clickOnSearchButton() {
        clickOn(searchButton);
        logger.info("Click on Search Button.");
        return new SearchResultsPage(driver);
    }

}
