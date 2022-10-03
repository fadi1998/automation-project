package AmazonTests.AuthenticationTestSuite;

import Extensions.Verifications;
import Utils.AllureListeners;
import Utils.ExcelUtils;
import WorkFlows.AmazonRegisterWorkFlows;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

@Listeners(AllureListeners.class)
public class RegistrationTest extends AmazonRegisterWorkFlows{

    private int rowCnt = 1;
    private int colCnt = 5;
    private ExcelUtils eu;


    @BeforeClass
    public void setUp()
    {
        visitAmazonLoginPage();
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
    public  void emptyAllInputs()
    {
        emptyAllInputsInRegisterPage();
    }

    @DataProvider(name = "my-data-provider1")
    Object[][] myDataProviderForPasswordDoNotMatch() throws IOException
    {
        Object[][] obj = eu.getExcelData("finalProjectExcel.xlsx", "passwordDoNotMatchSheet");

        return obj;
    }


    @Test(dataProvider = "my-data-provider1")
    @Description("Register Description:Trying to register with passwords that does not match")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Unmatched passwords register test")
    public void passwordDoNotMatch(String name,String email,String password,String rePassword,String expected)
    {

        typeIntoAllFields(name, email, password, rePassword);
        clickSubmit();
        String actual = getPasswordMustMatchMessage();
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
        Verifications.verifyRegisterWithUnMatchedPasswords(actual, expected);
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
        typeIntoAllFields(name, email, password, rePassword);
        clickSubmit();
        String actual = getPasswordLessThanSixDigitsMessage();
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
        Verifications.verifyRegisterWithLessThanSixDigitPassword(actual, expected);
    }
    @DataProvider(name = "my-data-provider3")
    Object[][] myDataProviderForInvalidEmails() throws IOException {
        Object[][] obj = eu.getExcelData("finalProjectExcel.xlsx", "invalidEmailSheet");
        return obj;
    }

    @Test(dataProvider = "my-data-provider3")
    @Description("Register Description:Trying to register with invalid email/phone")
    @Severity(SeverityLevel.BLOCKER)
    @Story("invalid email/phone registration test")
    public void invalidEmailOrPhone(String name, String email, String password, String rePassword, String expected)
    {
        typeIntoAllFields(name, email, password, rePassword);
        clickSubmit();
        String actual = getWrongPhoneOrEmailMessage();
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
       Verifications.verifyRegisterWithInvalidEmailOrPhone(actual, expected);

    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult iTestResult)
    {
        if(iTestResult.getStatus() == ITestResult.FAILURE)
        {
            saveFullPageScreenShot(register.getDriver());
        }
    }

    @AfterClass()
    public void terminate()
    {
        AmazonRegisterWorkFlows.terminateBrowser();
    }
}
