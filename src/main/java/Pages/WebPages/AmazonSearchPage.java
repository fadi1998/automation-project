package Pages.WebPages;

import Pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AmazonSearchPage extends BasePage {

    @FindBy(css = "#twotabsearchtextbox")
    private WebElement searchBox;

    @FindBy(css = "#nav-search-submit-button")
    private WebElement searchBoxSubmit;

    @FindBy(css = ".sg-col-inner")
    private WebElement result;

    @FindBy(css  = ".a-price[data-a-size=\"xl\"] > span[class=a-offscreen]")
    private List<WebElement> priceList;

    public AmazonSearchPage(WebDriver driver, WebDriverWait wait)
    {
        super(driver,wait);
    }

    //Getters
    public WebElement getSearchBox() {
        return searchBox;
    }

    public WebElement getSearchBoxSubmit() {
        return searchBoxSubmit;
    }

    public WebElement getResult() {
        return result;
    }

    public List<WebElement> getPriceList() {
        return priceList;
    }
}
