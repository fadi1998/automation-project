package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonCartPage extends BasePage {
    private By goToCart = By.cssSelector("#nav-cart");
    private By searchBox = By.cssSelector("#twotabsearchtextbox");
    private By searchBoxSubmit = By.cssSelector("#nav-search-submit-button");
    private By product = By.cssSelector("[data-image-index=\"2\"]");
    //[data-component-id="3"] > div > div > div > span >a
    private By addToCartButton = By.cssSelector("#add-to-cart-button");
    private By totalProducts = By.cssSelector("#sc-subtotal-label-buybox");
    private By deleteAllProducts = By.cssSelector("[data-feature-id=\"delete\"] > span>input");
    private By saveItemForLaterInput = By.cssSelector("input[value=\"Save for later\"]");
    private By saveItemForLaterCount = By.cssSelector("[id=\"sc-saved-cart-list-caption-text\"]");

    public AmazonCartPage(WebDriver driver, WebDriverWait wait)
    {
        super(driver, wait);
    }

    public By getGoToCart() {
        return goToCart;
    }

    public By getProduct() {
        return product;
    }

    public By getAddToCartButton() {
        return addToCartButton;
    }

    public By getSearchBox() {
        return searchBox;
    }

    public By getSearchBoxSubmit() {
        return searchBoxSubmit;
    }

    public By getTotalProducts() {
        return totalProducts;
    }

    public By getDeleteAllProducts() {
        return deleteAllProducts;
    }

    public By getSaveItemForLaterInput() {
        return saveItemForLaterInput;
    }

    public By getSaveItemForLaterCount() {
        return saveItemForLaterCount;
    }
}
