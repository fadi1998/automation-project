package Pages.WebPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonCartPage extends BasePage {
    @FindBy(css = "#nav-cart")
    private static WebElement goToCart;

    @FindBy(css = "#twotabsearchtextbox")
    private static WebElement SearchBoxInput;

    @FindBy(css = "#nav-search-submit-button")
    private static WebElement searchBoxSubmitButton;

    @FindBy(css = "[data-image-index=\"1\"]")
    private static WebElement product;

    @FindBy(css = "#add-to-cart-button")
    private static WebElement addToCartButton;

    @FindBy(css = "#sc-subtotal-label-buybox")
    private static WebElement totalProducts;

    @FindBy(css = "[data-feature-id=\"delete\"] > span >input")
    private static WebElement deleteProduct;

    @FindBy(css = "[aria-label=\"Save for later Things We Never Got Over (Knockemout Series)\"]")
    private static WebElement saveItemForLaterInput;

    @FindBy(css = "[id=\"sc-saved-cart-list-caption-text\"]")
    private static WebElement saveItemForLaterCount;

    public AmazonCartPage(WebDriver driver, WebDriverWait wait)
    {
        super(driver, wait);
    }

    //Getters
    public WebElement getGoToCart() {
        return goToCart;
    }

    public WebElement getProduct() {
        return product;
    }

    public WebElement getAddToCartButton() {
        return addToCartButton;
    }

    public WebElement getSearchBox() {
        return SearchBoxInput;
    }

    public WebElement getSearchBoxSubmit() {
        return searchBoxSubmitButton;
    }

    public WebElement getTotalProducts() {
        return totalProducts;
    }

    public WebElement getDeleteProduct() {
        return deleteProduct;
    }

    public WebElement getSaveItemForLaterInput() {
        return saveItemForLaterInput;
    }

    public WebElement getSaveItemForLaterCount() {
        return saveItemForLaterCount;
    }
}
