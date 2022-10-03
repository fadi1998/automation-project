package WorkFlows;

import Pages.WebPages.AmazonLoginPage;
import Pages.WebPages.AmazonRegisterPage;
import Utils.CaptureScreenShots;
import Utils.DriverUtils;
import com.beust.jcommander.Parameter;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class AmazonRegisterWorkFlows extends CaptureScreenShots {
    protected static AmazonRegisterPage register;

    @Step("Initialize web driver")
    private static void init()
    {
        WebDriver driver = DriverUtils.createDriverObj(2);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        register = new AmazonRegisterPage(driver, wait);

    }

    @Step("visit Amazon Register Website")
    public static void visitAmazonLoginPage()
    {
        init();
        register.visit("https://www.amazon.com/ap/register?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2F%3F_encoding%3DUTF8%26ref_%3Dnav_newcust&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=usflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&");
        saveFullPageScreenShot(register.getDriver());
    }

    @Step("empty all inputs before typing into")
    public static void emptyAllInputsInRegisterPage()
    {
        register.clear(register.getCustomerName());
        register.clear(register.getPassword());
        register.clear(register.getReEnteredPassword());
        register.clear(register.getCustomerName());
        register.clear(register.getCustomerEmail());
        saveSectionScreenShot(register.getRegisterForm());
    }


    @Step("Type Into 'Your name' Input Field: {0}")
    private static void typeIntoNameFiled(String name)
    {
        register.typeInto(name,register.getCustomerName());
        saveElementScreenShot(register.getCustomerName());

    }

    @Step("Type Into 'Mobile Or Mail' Input Field: {0}")
    private static void typeIntoMobileOrMailField(String emailOrPhone)
    {
        register.typeInto(emailOrPhone, register.getCustomerEmail());
        saveElementScreenShot(register.getCustomerEmail());

    }

    @Step("Type Into 'Password' Input Field: {0}")
    private static void typeIntoPasswordField(String password)
    {
        register.typeInto(password, register.getPassword());
        saveElementScreenShot(register.getPassword());

    }

    @Step("Type Into 'Re-enter password' Input Field: {0}")
    private static void typeIntoRePasswordField(String rePassword)
    {
        register.typeInto(rePassword, register.getReEnteredPassword());
        saveElementScreenShot(register.getReEnteredPassword());


    }
    @Step()
    @Parameter(hidden = true)
    public static void typeIntoAllFields(String name,String emailOrPhone,String password,String rePassword)
    {
        AmazonRegisterWorkFlows.typeIntoNameFiled(name);
        AmazonRegisterWorkFlows.typeIntoMobileOrMailField(emailOrPhone);
        AmazonRegisterWorkFlows.typeIntoPasswordField(password);
        AmazonRegisterWorkFlows.typeIntoRePasswordField(rePassword);
        saveSectionScreenShot(register.getRegisterForm());


    }

    @Step("Clicks The Submit Button")
    public static void clickSubmit()
    {
        register.click(register.getSubmitButton());
        saveElementScreenShot(register.getSubmitButton());
    }

    @Step("Get 'Passwords must match' Error Message")
    public static String getPasswordMustMatchMessage()
    {
        saveElementScreenShot(register.getMustMatchAlert());
        return register.getText(register.getMustMatchAlert());

    }

    @Step("Get 'Minimum 6 characters required' Error Message")
    public static String getPasswordLessThanSixDigitsMessage()
    {
        saveElementScreenShot(register.getLessThanSixDigitsAlert());
        return register.getText(register.getLessThanSixDigitsAlert());
    }

    @Step("Get 'Wrong or Invalid email address or mobile phone number. Please correct and try again.' Error Message")
    public static String getWrongPhoneOrEmailMessage()
    {
        saveElementScreenShot(register.getInvalidEmailAlert());
        return register.getText(register.getInvalidEmailAlert());
    }

    @Step("Close The Browser")
    public static void terminateBrowser()
    {
        register.terminate();
    }
}
