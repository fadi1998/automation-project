package Pages.WebPages;

import Pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonRegisterPage extends BasePage {

    @FindBy(css = "#ap_customer_name")
    private static WebElement customerName;

    @FindBy(css = "#ap_email")
    private static WebElement customerEmail ;

    @FindBy(css = "#ap_password")
    private static WebElement password;

    @FindBy(css = "#ap_password_check")
    private static WebElement reEnteredPassword;

    @FindBy(css = "#auth-continue")
    private static WebElement submitButton;

    @FindBy(css = "#auth-password-mismatch-alert > div > div")
    private static WebElement mustMatchAlert;

    @FindBy(css = "#auth-password-invalid-password-alert > div > div")
    private static WebElement lessThanSixDigitsAlert ;

    @FindBy(css  = "#auth-email-invalid-claim-alert > div > div")
    private static  WebElement invalidEmailAlert;

    @FindBy(css = "[id=\"ap_register_form\"]")
    private static WebElement registerForm;



    public AmazonRegisterPage(WebDriver driver, WebDriverWait wait)
    {
        super(driver, wait);
    }

    //Getters
    public WebElement getCustomerName() {
        return customerName;
    }

    public WebElement getCustomerEmail() {
        return customerEmail;
    }

    public WebElement getPassword() {
        return password;
    }

    public WebElement getReEnteredPassword() {
        return reEnteredPassword;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }

    public WebElement getMustMatchAlert() {
        return mustMatchAlert;
    }

    public WebElement getLessThanSixDigitsAlert() {
        return lessThanSixDigitsAlert;
    }

    public WebElement getInvalidEmailAlert()
    {
        return invalidEmailAlert;
    }

    public  WebElement getRegisterForm() {
        return registerForm;
    }
}
