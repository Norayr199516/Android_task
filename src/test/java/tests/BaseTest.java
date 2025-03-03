package tests;

import driver_manager.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LandingPage;
import pages.SearchResultsPage;


public class BaseTest {
    protected static final ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();
    protected final Logger logger = Logger.getLogger(BaseTest.class);
    protected LandingPage landingPage;
    protected HomePage homePage;
    protected SearchResultsPage searchResultsPage;
    protected SoftAssert softAssert;

    protected AndroidDriver getDriver() {
        return driver.get();
    }

    @BeforeClass(alwaysRun = true)
    @Parameters({"udid", "port"})
    public void setup(String udid, String port) {
        driver.set(DriverManager.getDriverInstance(udid, port));
        logger.info("Set new driver for - " + udid);
        landingPage = new LandingPage(getDriver());
        homePage = new HomePage(getDriver());
        searchResultsPage = new SearchResultsPage(getDriver());

    }

    @BeforeMethod
    public void initSoftAssert() {
        softAssert = new SoftAssert();
    }

    @Parameters({"udid"})
    @AfterMethod()
    public void takeScreenshotForFailedTests(ITestResult result, String udid) {
        if (ITestResult.FAILURE == result.getStatus()) {
            homePage.takeScreenshot(udid + "_" + result.getMethod().getMethodName() + "_failed.png");
        }
    }

    @AfterClass
    public void cleanUp() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
            logger.info("The Driver successfully quited!");
        }
    }

}
