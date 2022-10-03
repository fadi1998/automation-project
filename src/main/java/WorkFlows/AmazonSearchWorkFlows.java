package WorkFlows;

import Pages.WebPages.AmazonSearchPage;
import Utils.CaptureScreenShots;
import Utils.DriverUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AmazonSearchWorkFlows extends CaptureScreenShots {

    protected static AmazonSearchPage amazonSearchPage;

    @Step("Initialize web driver")
    private static void init()
    {
        WebDriver driver = DriverUtils.createDriverObj(2);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        amazonSearchPage = new AmazonSearchPage(driver, wait);
    }

    @Step("Visit amazon home page")
    public static void visitAmazonHomePage()
    {
        init();
        amazonSearchPage.visit("https://www.amazon.com/");
        saveFullPageScreenShot(amazonSearchPage.getDriver());
    }

    @Step("Empty search box before typing")
    public static void emptyInput()
    {
        amazonSearchPage.clear(amazonSearchPage.getSearchBox());
        saveElementScreenShot(amazonSearchPage.getSearchBox());

    }

    @Step("Type into search box")
    public static void typeIntoSearchBox(String productName)
    {
        amazonSearchPage.typeInto(productName, amazonSearchPage.getSearchBox());
        saveElementScreenShot(amazonSearchPage.getSearchBox());

    }

    @Step("Click submit search button")
    public static void clickSearchButton()
    {
        saveElementScreenShot(amazonSearchPage.getSearchBoxSubmit());
        amazonSearchPage.click(amazonSearchPage.getSearchBoxSubmit());

    }

    @Step("Get products found result")
    public static String getProductsResult()
    {
        return amazonSearchPage.getText(amazonSearchPage.getResult()).split(" ")[3];
    }

    @Step("Get 'cheapest product price'")
    public static double getCheapestPrice()
    {
        double price;
        List<WebElement> priceList = amazonSearchPage.getPriceList();
        WebElement lowestElmPrice = null;

        double actual = Double.parseDouble(priceList.get(0).getAttribute("innerText").replace("$",""));
        for(WebElement curr : priceList)
        {
            price = Double.parseDouble(curr.getAttribute("innerText").replace("$", ""));
            if( price < actual) {
                actual = price;
                lowestElmPrice = curr;
            }
        }

        if(lowestElmPrice != null)
            saveElementScreenShot(lowestElmPrice);

        return actual;
    }

    @Step("Close the browser")
    public static void terminateBrowser()
    {
        amazonSearchPage.terminate();
    }

}
