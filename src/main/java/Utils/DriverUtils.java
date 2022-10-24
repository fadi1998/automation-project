package Utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverUtils {
    // 1 = firefox, 2 = chrome, 3 = edge
    public static WebDriver createDriverObj(int browserType) {
        WebDriver driver;
        switch (browserType) {
            case 1:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case 2:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case 3:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                driver = null;
                break;
        }
        return driver;
    }

    public static AppiumDriver createAppiumDriver(String deviceName, String udid, String platformName, String platformVersion, String appPackage, String appActivity) {
        AppiumDriver driver = null;

        try {
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("deviceName", deviceName);
            capabilities.setCapability("udid", udid);
            capabilities.setCapability("platformName", platformName);
            capabilities.setCapability("platformVersion", "11");
            capabilities.setCapability("appPackage", appPackage);
            capabilities.setCapability("appActivity", appActivity);

            driver = new AppiumDriver(url, capabilities);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return driver;
    }
}
