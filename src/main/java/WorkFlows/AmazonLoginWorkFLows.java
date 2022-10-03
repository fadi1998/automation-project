package WorkFlows;

import Pages.WebPages.AmazonLoginPage;
import Utils.CaptureScreenShots;
import Utils.DriverUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AmazonLoginWorkFLows extends CaptureScreenShots {
    protected static AmazonLoginPage login;

    @Step("Initialize web driver")
    private static void init()
    {
        WebDriver driver = DriverUtils.createDriverObj(2);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        login = new AmazonLoginPage(driver, wait);
    }

    @Step("visit amazon login website")
    public static void visitAmazonLoginPage()
    {
        init();
        login.visit("https://www.amazon.com/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2Fgp%2Fcart%2Fview.html%3Fref_%3Dnav_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=usflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&");
        saveFullPageScreenShot(login.getDriver());
    }

    @Step("Empty Email/Phone Input Before Typing")
    public static void emptyEmailInput()
    {
        login.clear(login.getInputUsernameField());
        saveElementScreenShot(login.getInputUsernameField());
    }

    @Step("Type Into Email/Phone Input field: {0}")
    public static void typeIntoEmailAndPhoneInput(String userDetails)
    {
        login.typeInto(userDetails, login.getInputUsernameField());
        saveElementScreenShot(login.getInputUsernameField());
    }

    @Step("Type Into Password Input field: {0}")
    public static void typeIntoPasswordInput(String password)
    {
        login.typeInto(password, login.getInputPasswordFiled());
        saveElementScreenShot(login.getInputPasswordFiled());
    }

    @Step("Get a Text that verify the login was successful")
    public static String getVerifyLoginText()
    {
        saveElementScreenShot(login.getVerifyLoginText());
        return login.getText(login.getVerifyLoginText());
    }

    @Step("Clicks The Submit Button")
    public static void clickSubmitButton()
    {
        saveElementScreenShot(login.getSubmit());
        login.click(login.getSubmit());
    }

    @Step("Get Invalid Alert Message")
    public static String getAlertMessage()
    {
            saveElementScreenShot(login.getAlertBoxMessage());
            return login.getText(login.getAlertBoxMessage());
    }

    @Step("Close the Browser")
    public static void terminateBrowser()
    {
        login.terminate();
    }
}
