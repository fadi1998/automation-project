package Pages.WebPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonLoginPage extends BasePage {
    @FindBy(css = "#ap_email")
    private static WebElement inputUsernameField;

    @FindBy(css = "#ap_password")
    private static WebElement inputPasswordFiled;

    @FindBy(css = "#auth-error-message-box > div > div > ul > li > span")
    private static WebElement AlertBoxMessage;

    @FindBy(css = "input[type=submit]")
    private static WebElement submit;

    @FindBy(css = "#nav-link-accountList-nav-line-1")
    private static WebElement verifyLoginText;

    public AmazonLoginPage(WebDriver driver, WebDriverWait wait)
    {
        super(driver, wait);
    }

    //Getters
    public WebElement getInputUsernameField() {
        return inputUsernameField;
    }

    public  WebElement getInputPasswordFiled() {
        return inputPasswordFiled;
    }

    public WebElement getAlertBoxMessage() {
        return AlertBoxMessage;
    }

    public WebElement getSubmit() {
        return submit;
    }

    public WebElement getVerifyLoginText() {
        return verifyLoginText;
    }
}
