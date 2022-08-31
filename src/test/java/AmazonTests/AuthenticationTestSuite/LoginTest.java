package AmazonTests.AuthenticationTestSuite;

import Pages.AmazonLoginPage;
import Utils.DriverUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoginTest {
    AmazonLoginPage login;
    @BeforeClass
    public void setUp()
    {
        WebDriver driver = DriverUtils.createDriverObj(2);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        login = new AmazonLoginPage(driver, wait);
        login.visit("https://www.amazon.com/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2Fgp%2Fcart%2Fview.html%3Fref_%3Dnav_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=usflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&");
    }

    @BeforeMethod
    public void emptyAllInputsInLoginPage()
    {
        login.clear(login.getEmail());
    }


    @DataProvider(name = "my-data-provider-login")
    Object[][] myDataProviderForEmailDoesNotExists()
    {
        return new Object[][] {{"fadi@gma.com", "We cannot find an account with that email address"},
                {"asdasdas@stam.com", "We cannot find an account with that email address"}};
    }

    @Test(dataProvider = "my-data-provider-login")
    @Description("Test Description: Trying to login with unregistered email")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Invalid email login test")
    public void EmailThatDoesNotExists(String email,String expected)
    {
        login.typeInto(email, login.getEmail());
        login.click(login.getSubmit());
        String actual = login.getText(login.getEmailAlertBoxEmail());
        Assert.assertEquals(actual, expected);

    }

    @DataProvider(name = "my-data-provider-login2")
    Object[][] myDataProviderForPhoneDoesNotExists()
    {
        return new Object[][] {{"123455678", "We cannot find an account with that mobile number"},
                {"0521235467", "We cannot find an account with that mobile number"}};
    }
    @Test(dataProvider = "my-data-provider-login2")
    @Description("Login Description:trying to login with Invalid unregistered number")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Invalid phone login test")
    public void PhoneThatDoesNotExists(String phone, String expected)
    {
        login.typeInto(phone, login.getEmail());
        login.click(login.getSubmit());
        String actual = login.getText(login.getEmailAlertBoxPhone());
        Assert.assertEquals(actual, expected);
    }

    @AfterMethod
    public void takenScreenShot(ITestResult result)
    {
        if(result.getStatus() == ITestResult.FAILURE)
        {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy__hh_mm_ss");
            LocalDateTime now = LocalDateTime.now();
            String fileName = "failedLoginsTest"+result.getMethod().getMethodName()+ "_"+ dtf.format(now);
            login.takeScreenShot("captureScreenShots", fileName, login.getDriver());
        }
    }

    @AfterClass
    public void terminate()
    {
        login.terminate();
    }


}
