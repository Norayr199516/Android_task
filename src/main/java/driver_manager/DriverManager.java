package driver_manager;

import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
    protected static final Logger logger = Logger.getLogger(DriverManager.class);
    static AndroidDriver driver;

    private DriverManager(){
    }

    public static AndroidDriver getDriverInstance(String uuid, String port){
        String appPath = System.getProperty("user.dir") + "/src/test/resources/test_data/org.wikipedia.apk";
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:deviceName", uuid);
        caps.setCapability("appium:udid", uuid);
        caps.setCapability("appium:app", appPath);
        caps.setCapability("appium:appPackage", "org.wikipedia");
        caps.setCapability("appium:appActivity", "org.wikipedia.main.MainActivity");
        caps.setCapability("appium:automationName", "UiAutomator2");
        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:" + port), caps);
            logger.info("Connect to 127.0.0.1:" + port);
        }
        catch (MalformedURLException e) {
            logger.error("Impossible to connect to - 127.0.0.1:" + port + " because of - " + e.getMessage());
        }
        return driver;
    }
}
