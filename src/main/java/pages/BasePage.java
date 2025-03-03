package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public abstract class BasePage {
    public final Logger logger;
    public final AppiumDriver driver;
    public final WebDriverWait wait, shortWait;
    protected final AjaxElementLocatorFactory factory;

    public BasePage(AppiumDriver driver) {
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        logger = Logger.getLogger(this.getClass().getName());
        factory = new AjaxElementLocatorFactory(driver, 20);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        shortWait = new WebDriverWait(driver, Duration.ofSeconds(1));
    }

    public void waitForElementToBeVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void waitForListToBeVisible(List<WebElement> elements){
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void fillIn(WebElement element, String text) {
        try {
            waitForElementToBeVisible(element);
            element.sendKeys(text);
        } catch (Exception e) {
            logger.error("SendKeys isn't performed - " + e.getMessage());
        }
    }

    public void clickOn(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            logger.error("Click on isn't performed - " + e.getMessage());
        }
    }

    public void scroll(){
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 500, 1500));  // Start at (x, y)
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));  // Press down
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), 500, 300));  // Move to (x, y)
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));  // Release

        driver.perform(Arrays.asList(swipe));
    }

    public void scrollTo(String xpath) {
        boolean isElementFound = false;

        while (!isElementFound) {
            try {
                shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
                isElementFound = true;
                logger.info("Element is found.");
            } catch (Exception e) {
                logger.info("Element not found, scrolling...");
                scroll();
            }
        }
    }

    public File takeScreenshot(String fileName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("src/test/resources/screenshots/" + fileName));
            logger.info("The screenshot has been taken successfully!");
        } catch (Exception e) {
            logger.error("Impossible to do screenshot because of - " + e.getMessage());
        }
        return new File(String.valueOf(screenshot));
    }

}
