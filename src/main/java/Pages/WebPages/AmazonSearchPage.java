package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonSearchPage extends BasePage {
    private By searchBox = By.cssSelector("#twotabsearchtextbox");
    private By searchBoxSubmit = By.cssSelector("#nav-search-submit-button");
    private By result = By.cssSelector(".sg-col-inner");
    private By priceList = By.cssSelector(".a-price[data-a-size=\"xl\"] > span[class=a-offscreen]");

    public AmazonSearchPage(WebDriver driver, WebDriverWait wait)
    {
        super(driver,wait);
    }

    public By getSearchBox() {
        return searchBox;
    }

    public By getSearchBoxSubmit() {
        return searchBoxSubmit;
    }

    public By getResult() {
        return result;
    }

    public By getPriceList() {
        return priceList;
    }
}
