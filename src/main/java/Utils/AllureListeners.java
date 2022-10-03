package Utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureListeners extends CaptureScreenShots implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult)
    {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    // Text attaxhments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message)
    {
        return message;
    }

    @Override
    public void onStart(ITestContext iTestContext)
    {
        System.out.println("I am in onStart method" + iTestContext.getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext)
    {
        System.out.println("I am in onFinish method" + iTestContext.getName());
    }

    @Override
    public void onTestStart(ITestResult iTestResult)
    {
        System.out.println("I am in onTestStart method" + getTestMethodName(iTestResult) + " start");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult)
    {
        System.out.println("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
    }

    public void onTestFailure(ITestResult iTestResult)
    {
        System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
    }


}
