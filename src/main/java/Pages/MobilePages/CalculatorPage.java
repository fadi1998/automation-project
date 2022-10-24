package Pages.MobilePages;

import Pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalculatorPage extends BasePage {
    @FindBy(id = "com.bng.calculator:id/multi")
    private static WebElement mulSign;

    @FindBy(id = "com.bng.calculator:id/btn_8")
    private static WebElement numberEight;

    @FindBy(id = "com.bng.calculator:id/btn_2")
    private static WebElement numberTwo;

    @FindBy(id = "com.bng.calculator:id/plus")
    private static WebElement plusSign;

    @FindBy(id = "com.bng.calculator:id/equal")
    private static WebElement equalSign;

    @FindBy(id = "com.bng.calculator:id/result")
    private static WebElement result;

    @FindBy(id = "com.bng.calculator:id/btn_clear")
    private static WebElement clear;

    @FindBy(id = "com.bng.calculator:id/formula")
    private static WebElement formula;

    public CalculatorPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public  WebElement getMulSign() {
        return mulSign;
    }

    public  WebElement getNumberEight() {
        return numberEight;
    }

    public  WebElement getNumberTwo() {
        return numberTwo;
    }

    public  WebElement getPlusSign() {
        return plusSign;
    }

    public  WebElement getEqualSign() {
        return equalSign;
    }

    public  WebElement getResult() {
        return result;
    }

    public  WebElement getFormula() {
        return formula;
    }

    public  WebElement getClear() {
        return clear;
    }
}
