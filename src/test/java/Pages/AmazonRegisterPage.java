package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonRegisterPage extends BasePage{
    private By customerName = By.cssSelector("#ap_customer_name");
    private By customerEmail = By.cssSelector("#ap_email");
    private By password = By.cssSelector("#ap_password");
    private By reEnteredPassword = By.cssSelector("#ap_password_check");
    private By submitButton = By.cssSelector("#auth-continue");
    private By missMatchAlert = By.cssSelector("#auth-password-mismatch-alert > div > div");
    private By lessThanSixDigitsAlert = By.cssSelector("#auth-password-invalid-password-alert > div > div");
    private By invalidEmailAlert = By.cssSelector("#auth-email-invalid-claim-alert > div > div");



    public AmazonRegisterPage(WebDriver driver, WebDriverWait wait)
    {
        super(driver, wait);
    }

    public By getCustomerName() {
        return customerName;
    }

    public By getCustomerEmail() {
        return customerEmail;
    }

    public By getPassword() {
        return password;
    }

    public By getReEnteredPassword() {
        return reEnteredPassword;
    }

    public By getSubmitButton() {
        return submitButton;
    }

    public By getMissMatchAlert() {
        return missMatchAlert;
    }

    public By getLessThanSixDigitsAlert() {
        return lessThanSixDigitsAlert;
    }

    public By getInvalidEmailAlert()
    {
        return invalidEmailAlert;
    }

}
