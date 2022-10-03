package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonLoginPage extends BasePage{
    private By email = By.cssSelector("#ap_email");
    private By emailAlertBoxEmail = By.cssSelector("#auth-error-message-box > div > div > ul > li > span");
    private By emailAlertBoxPhone = By.cssSelector("#auth-error-message-box > div > div > ul > li > span");
    private By submit = By.cssSelector("input[type=submit]");

    public AmazonLoginPage(WebDriver driver, WebDriverWait wait)
    {
        super(driver, wait);
    }

    public By getEmail() {
        return email;
    }

    public By getEmailAlertBoxEmail() {
        return emailAlertBoxEmail;
    }

    public By getEmailAlertBoxPhone(){
        return emailAlertBoxPhone;
    }

    public By getSubmit() {
        return submit;
    }
}
