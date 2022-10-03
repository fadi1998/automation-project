package Pages.WebPages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);

    }

    public WebDriver getDriver() {
        return driver;
    }

    public void waitForPresenceOfElementLocated(By locator)
    {
             wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitUntilVisibilityElementLocated(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitUntilElmClickable(By locator)
    {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitUntilUrlContains(String url)
    {
        wait.until(ExpectedConditions.urlContains(url));
    }

    public WebElement findElem(By locator) {
        return driver.findElement(locator);
    }

    public List<WebElement> findElems(By locator) {
        return driver.findElements(locator);
    }

    public String getText(WebElement element) {
        return element.getText();
    }

    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    // --------------------------------------------------
    // adding ability to concatenate a few methods on a webelement e.g:
    // typeInto("hello", By.cssSelector("[name=\"q\"]")).click();
    public WebElement typeInto(String inputText, By locator) {
        findElem(locator).sendKeys(inputText);
        return findElem(locator);
    }

    public WebElement typeInto(String inputText, WebElement element) {
        element.sendKeys(inputText);
        return element;
    }

    public WebElement click(WebElement element) {
        element.click();
        return element;
    }

    // --------------------------------------------------

    public boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clear(WebElement element) {
        element.clear();
    }

    public void selectFromDropDownListByValue(By locator, String value) {
        Select select = new Select(driver.findElement(locator));
        select.selectByValue(value);
    }

    public void selectFromDropDownListByVisibleText(By locator, String text) {
        Select select = new Select(driver.findElement(locator));
        select.selectByVisibleText(text);
    }

    public void selectFromDropDownListByIndex(By locator, int indx) {
        Select select = new Select(driver.findElement(locator));
        select.selectByIndex(indx);
    }
    // --------------------------------------------------

    public void visit(String url) {
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    public void terminate()
    {
        driver.quit();
    }
    // --------------------------------------------------

    // ------------------------------------------------------------------

    public void takeScreenShot(String filePathStr, String nameForTheImageFile, WebDriver driver) {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // Save the file option 1:
        try {
            FileUtils.copyFile(file, new File(filePathStr + "/" + nameForTheImageFile + "_1" + ".png"));
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // Save the file option 2:
        try {
            // Two different ways to save a file (both work similarly)
            // Use this:
            FileHandler.copy(file, new File(filePathStr + "/" + nameForTheImageFile + "_2" + ".png"));
            // or use this:
            // FileUtils.copyFile(file, new File(filePathStr + "/" + nameForTheImageFile +
            // ".png"));
            System.out.println("*** Placed screenshot in " + filePathStr + " ***");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // ----------------------------------------------------------------------
    // ---------------------------------------------------------------------
}