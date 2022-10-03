package AmazonTests.AuthenticationTestSuite;

import Extensions.Verifications;
import Utils.AllureListeners;
import WorkFlows.AmazonLoginWorkFLows;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.ITestResult;
import org.testng.annotations.*;

@Listeners(AllureListeners.class)
public class LoginTest extends AmazonLoginWorkFLows{

    @BeforeClass
    public void setUp()
    {
        visitAmazonLoginPage();

    }

    @BeforeMethod
    public void emptyAllInputsInLoginPage()
    {
        emptyEmailInput();
    }

    @DataProvider(name = "my-data-provider-login")
    Object[][] myDataProviderForEmailOrPhoneDoesNotExists()
    {
        return new Object[][] {{"fadi@gma.com", "We cannot find an account with that email address"},
                {"asdasdas@stam.com", "We cannot find an account with that email address"},
                {"123455678", "We cannot find an account with that mobile number"},
                {"0521235467", "We cannot find an account with that mobile number"}};
    }

    @Test(dataProvider = "my-data-provider-login")
    @Description("Test Description: Trying to login with unregistered email")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Invalid email login test")
    public void loginWithUnValidCredentials(String user, String expected)
    {
        typeIntoEmailAndPhoneInput(user);
        clickSubmitButton();
        String actual = getAlertMessage();

        Verifications.verifyUserLogInWithInvalidCredentials(actual, expected);
    }

    @DataProvider(name = "my-data-provider-login2")
    Object[][] myDataProviderLoginWithValidCredentials()
    {
        return new Object[][] {{"pigaboy268@bongcs.com", "test11", "Hello, fadi"}};
    }

    @Test(dataProvider = "my-data-provider-login2")
    @Description("Login Description:trying to login with Invalid unregistered number")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Invalid phone login test")
    public void loginWithValidCredentials(String validEmail, String validPassword, String expected)
    {
        typeIntoEmailAndPhoneInput(validEmail);
        clickSubmitButton();
        typeIntoPasswordInput(validPassword);
        clickSubmitButton();
        String actual = getVerifyLoginText();

        Verifications.verifyUserLogInWithValidCredentials(actual,expected);
    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult iTestResult)
    {
        if(iTestResult.getStatus() == ITestResult.FAILURE)
        {
            saveFullPageScreenShot(login.getDriver());
        }
    }
    @AfterClass
    public void terminate()
    {
        AmazonLoginWorkFLows.terminateBrowser();
    }




}
