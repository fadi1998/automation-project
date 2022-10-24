package WorkFlows;

import Pages.MobilePages.CalculatorPage;
import Utils.CaptureScreenShots;
import Utils.DriverUtils;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;


import java.time.Duration;

public class CalculatorWorkFlow extends CaptureScreenShots {
    protected static CalculatorPage calculatorPage;

    @Step("Initialize the appium driver and opens the calculator application")
    public static void openCalculatorApp()
    {
        AppiumDriver appiumDriver = DriverUtils.createAppiumDriver("sdk_gphone_x86", "emulator-5554", "Android", "11", "com.bng.calculator", "com.bng.calc.MainActivity");
        WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(5));
         calculatorPage = new CalculatorPage(appiumDriver, wait);
    }

    @Step("Clicks the plus sign")
    public static void clickPlusSign()
    {
        calculatorPage.click(calculatorPage.getPlusSign());
        saveElementScreenShot(calculatorPage.getPlusSign());
    }

    @Step("Clicks the multiplication sign")
    public static void clickMulSign()
    {
        calculatorPage.click(calculatorPage.getMulSign());
        saveElementScreenShot(calculatorPage.getMulSign());

    }

    @Step("Clicks the number eight")
    public static void clickNumberEight()
    {
        calculatorPage.click(calculatorPage.getNumberEight());
        saveElementScreenShot(calculatorPage.getNumberEight());

    }

    @Step("Clicks the number two")
    public static void clickNumberTwo()
    {
        calculatorPage.click(calculatorPage.getNumberTwo());
        saveElementScreenShot(calculatorPage.getNumberTwo());

    }

    @Step("get the result")
    public static String getResult()
    {
        saveElementScreenShot(calculatorPage.getFormula());
        return calculatorPage.getText(calculatorPage.getFormula());

    }

    @Step("click clear button")
    public static void getClearResult()
    {
        calculatorPage.click(calculatorPage.getClear());
        saveElementScreenShot(calculatorPage.getClear());
    }

    @Step("click equals sign")
    public static void clickEquals()
    {
        calculatorPage.click(calculatorPage.getEqualSign());
        saveElementScreenShot(calculatorPage.getEqualSign());

    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult iTestResult)
    {
        if(iTestResult.getStatus() == ITestResult.FAILURE)
        {
            saveFullPageScreenShot(calculatorPage.getDriver());
        }
    }
}
