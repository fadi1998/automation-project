package Extensions;

import io.qameta.allure.Step;
import org.testng.Assert;


public class Verifications {

    //Login page Verifications
    @Step("Verify logging in with unregistered Phone or Email")
    public static void verifyUserLogInWithInvalidCredentials(String actual, String expected) {
        Assert.assertEquals(actual, expected);
    }

    @Step("Verify logging in successfully")
    public static void verifyUserLogInWithValidCredentials(String actual, String expected) {
        Assert.assertEquals(actual, expected);
    }

    //Register Page Verifications
    @Step("Verify registration With Unmatched Passwords")
    public static void verifyRegisterWithUnMatchedPasswords(String actual, String expected)
    {
        Assert.assertEquals(actual, expected);
    }

    @Step("Verify registration with password less than six digits")
    public static void verifyRegisterWithLessThanSixDigitPassword(String actual, String expected)
    {
        Assert.assertEquals(actual, expected);
    }

    @Step("Verify registration with invalid email/phone")
    public static void verifyRegisterWithInvalidEmailOrPhone(String actual, String expected)
    {
        Assert.assertEquals(actual, expected);
    }

    //Search Page Verifications
    @Step("Verify products amount result")
    public static void verifyResult(String actual, String expected)
    {
        Assert.assertEquals(actual, expected);
    }

    @Step("Verify cheapest product price in products list")
    public static void verifyCheapestPrice(double actual, double expected)
    {
        Assert.assertEquals(actual,expected);
    }

    //Cart Verification
    @Step("Verify if cart subTotal Increases/Decreases when adding/deleting a product")
    public static void verifyCartSubTotal(int actual, int expected)
    {
        Assert.assertEquals(actual, expected);
    }

    @Step("Verify if save for later subTotal Increases/Decreases when adding/deleting a product")
    public static void verifySaveForLaterSubTotal(int actual, int expected)
    {
        Assert.assertEquals(actual, expected);
    }

    //Calculator Verification
    @Step("Verify that eight plus two equals ten")
    public static void verifyEightPlusTwo_EqualTen(int actual, int expected)
    {
            Assert.assertEquals(actual, expected);
    }

    @Step("Verify that eight Multiplied by two equals sixteen")
    public static void verifyEightMulTwo_EqualSixteen(int actual, int expected)
    {
        Assert.assertEquals(actual, expected);
    }







}
