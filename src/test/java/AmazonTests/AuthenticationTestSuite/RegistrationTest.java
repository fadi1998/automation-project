package AmazonTests.AuthenticationTestSuite;

import Pages.AmazonRegisterPage;
import Utils.DriverUtils;
import Utils.ExcelUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class RegistrationTest{
    private int rowCnt = 1;
    private int colCnt = 5;
    private AmazonRegisterPage registerPom;
    private ExcelUtils eu;

    @BeforeClass
    public void setUp()
    {
        WebDriver driver = DriverUtils.createDriverObj(2);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        registerPom = new AmazonRegisterPage(driver, wait);
        registerPom.visit("https://www.amazon.com/ap/register?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2Fgp%2Fcart%2Fview.html%2F%3Fie%3DUTF8%26_encoding%3DUTF8%26redirectDevice%3Ddesktop%26redirectToFullPage%3D1%26ref_%3Dnav_newcust&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=usflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&");
        eu = new ExcelUtils();
    }

    @BeforeMethod()
    public void resetRowAndCol()
    {
        if(rowCnt > 2)
        {
            rowCnt = 1;
            colCnt = 5;
        }
    }

    @BeforeMethod
    public void emptyAllInputs()
    {
        registerPom.clear(registerPom.getCustomerName());
        registerPom.clear(registerPom.getPassword());
        registerPom.clear(registerPom.getReEnteredPassword());
        registerPom.clear(registerPom.getCustomerName());
        registerPom.clear(registerPom.getCustomerEmail());
    }

    @DataProvider(name = "my-data-provider1")
    Object[][] myDataProviderForPasswordDoNotMatch() throws IOException
    {
        Object[][] obj = eu.getExcelData("finalProjectExcel.xlsx", "passwordDoNotMatchSheet");

        return obj;
//  example:      return new Object[][] {{"fadi","fadi@gma.com","d2345678","d234567","Passwords must match"},
//                                {"stam","stam@stam.com","d1111111","d2222222","Passwords must match"}};
    }


    @Test(dataProvider = "my-data-provider1")
    @Description("Register Description:Trying to register with passwords that does not match")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Unmatched passwords register test")
    public void passwordDoNotMatch(String name,String email,String password,String rePassword,String expected)
    {

        registerPom.typeInto(name,registerPom.getCustomerName());
        registerPom.typeInto(email, registerPom.getCustomerEmail());
        registerPom.typeInto(password, registerPom.getPassword());
        registerPom.typeInto(rePassword, registerPom.getReEnteredPassword());
        registerPom.click(registerPom.getSubmitButton());
        String actual = registerPom.getText(registerPom.getMissMatchAlert());
        if(actual.equals(expected))
        {
            eu.setCellValue(rowCnt, colCnt , "finalProjectExcel.xlsx", "passwordDoNotMatchSheet", actual);
            eu.setCellValue(rowCnt, (colCnt + 1) , "finalProjectExcel.xlsx", "passwordDoNotMatchSheet", "Pass");
        }
        else
        {
            eu.setCellValue(rowCnt, colCnt , "finalProjectExcel.xlsx", "passwordDoNotMatchSheet", actual);
            eu.setCellValue(rowCnt, (colCnt + 1) , "finalProjectExcel.xlsx", "passwordDoNotMatchSheet", "Pass");
        }
        rowCnt++;
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "my-data-provider2")
    Object[][] myDataProviderForPasswordLessThanSixDigits() throws IOException {
        Object[][] obj = eu.getExcelData("finalProjectExcel.xlsx", "passwordLessThanSixDigitsSheet");
        return obj;
//        return new Object[][] {{"fadi","fadi@gma.com","h23","h23","Minimum 6 characters required"},
//                {"stam","stam@stam.com","f5555","f5555","Minimum 6 characters required"}};
    }

    @Test(dataProvider = "my-data-provider2")
    @Description("Register Description:Trying to register with password less than six digits")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Password with less than six digits registration test")
    public void passwordLessThanSixDigits(String name, String email, String password, String rePassword, String expected)
    {
        registerPom.typeInto(name,registerPom.getCustomerName());
        registerPom.typeInto(email, registerPom.getCustomerEmail());
        registerPom.typeInto(password, registerPom.getPassword());
        registerPom.typeInto(rePassword, registerPom.getReEnteredPassword());
        registerPom.click(registerPom.getSubmitButton());
        String actual = registerPom.getText(registerPom.getLessThanSixDigitsAlert());
        if(actual.equals(expected))
        {
            eu.setCellValue(rowCnt, colCnt , "finalProjectExcel.xlsx", "passwordLessThanSixDigitsSheet", actual);
            eu.setCellValue(rowCnt, (colCnt + 1) , "finalProjectExcel.xlsx", "passwordLessThanSixDigitsSheet", "Pass");
        }
        else
        {
            eu.setCellValue(rowCnt, colCnt , "finalProjectExcel.xlsx", "passwordLessThanSixDigitsSheet", actual);
            eu.setCellValue(rowCnt, colCnt + 1 , "finalProjectExcel.xlsx", "passwordLessThanSixDigitsSheet", "Pass");
        }
        rowCnt++;
        Assert.assertEquals(actual, expected);

    }
    @DataProvider(name = "my-data-provider3")
    Object[][] myDataProviderForInvalidEmails() throws IOException {
        Object[][] obj = eu.getExcelData("finalProjectExcel.xlsx", "invalidEmailSheet");
        return obj;
    }

    @Test(dataProvider = "my-data-provider3")
    @Description("Register Description:Trying to register with invalid email")
    @Severity(SeverityLevel.BLOCKER)
    @Story("invalid email registration test")
    public void invalidEmail(String name, String email, String password, String rePassword, String expected)
    {
        registerPom.typeInto(name,registerPom.getCustomerName());
        registerPom.typeInto(email, registerPom.getCustomerEmail());
        registerPom.typeInto(password, registerPom.getPassword());
        registerPom.typeInto(rePassword, registerPom.getReEnteredPassword());
        registerPom.click(registerPom.getSubmitButton());
        String actual = registerPom.getText(registerPom.getInvalidEmailAlert());
        if(actual.equals(expected))
        {
            eu.setCellValue(rowCnt, colCnt , "finalProjectExcel.xlsx", "invalidEmailSheet", actual);
            eu.setCellValue(rowCnt, (colCnt + 1) , "finalProjectExcel.xlsx", "invalidEmailSheet", "Pass");
        }
        else
        {
            eu.setCellValue(rowCnt, colCnt , "finalProjectExcel.xlsx", "invalidEmailSheet", actual);
            eu.setCellValue(rowCnt, colCnt + 1 , "finalProjectExcel.xlsx", "invalidEmailSheet", "Pass");
        }
        rowCnt++;
        Assert.assertEquals(actual, expected);

    }
    @AfterMethod()
    public void takenScreenShot(ITestResult result)
    {
        if(result.getStatus() == ITestResult.FAILURE)
        {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy__hh_mm_ss");
            LocalDateTime now = LocalDateTime.now();
            String fileName = "registrationFailed_"+result.getMethod().getMethodName()+"_"+now.format(dtf);
            registerPom.takeScreenShot("captureScreenShots", fileName ,registerPom.getDriver());
        }
    }

    @AfterClass()
    public void terminate()
    {
        registerPom.terminate();
    }
}
