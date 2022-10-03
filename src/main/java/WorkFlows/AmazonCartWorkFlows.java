package WorkFlows;

import Pages.WebPages.AmazonCartPage;
import Utils.CaptureScreenShots;
import Utils.DriverUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AmazonCartWorkFlows extends CaptureScreenShots {
    protected static AmazonCartPage cartPage;

    @Step("Initialize web driver")
    private static void init()
    {
        WebDriver driver = DriverUtils.createDriverObj(2);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        cartPage = new AmazonCartPage(driver, wait);
    }
    @Step("Visit Amazon Home Page")
    public static void visitAmazonHomePage()
    {
        AmazonCartWorkFlows.init();
        cartPage.visit("https://www.amazon.com/");
        saveFullPageScreenShot(cartPage.getDriver());
    }

    @Step("Empty search box before typing")
    public static void emptySearchInput()
    {
        cartPage.clear(cartPage.getSearchBox());
        saveElementScreenShot(cartPage.getSearchBox());
    }

    @Step("Type Into Search box {0}")
    private static void typeIntoSearchBox(String productName)
    {
        cartPage.typeInto(productName, cartPage.getSearchBox());
        saveElementScreenShot(cartPage.getSearchBox());
    }

    @Step("Click search button")
    private static void clickSearchButton()
    {
        saveElementScreenShot(cartPage.getSearchBoxSubmit());
        cartPage.click(cartPage.getSearchBoxSubmit());
    }

    @Step("Click a product")
    private static void pickProduct()
    {
        saveElementScreenShot(cartPage.getProduct());
        cartPage.click(cartPage.getProduct());

    }

    @Step("Click 'add to cart' button")
    private static void clickAddToCartButton()
    {
        saveElementScreenShot(cartPage.getAddToCartButton());
        cartPage.click(cartPage.getAddToCartButton());
    }

    @Step("click 'shopping cart' button")
    public static void goToCart()
    {
        saveElementScreenShot(cartPage.getGoToCart());
        cartPage.click(cartPage.getGoToCart());
        saveFullPageScreenShot(cartPage.getDriver());
    }

    @Step
    public static void addProductToCart(String productName)
    {
        typeIntoSearchBox(productName);
        clickSearchButton();
        pickProduct();
        clickAddToCartButton();
        goToCart();
    }

    @Step("Get cart Subtotal")
    public static int ProductSubTotal()
    {
        saveElementScreenShot(cartPage.getTotalProducts());
       return Integer.parseInt(cartPage.getText(cartPage.getTotalProducts()).split("")[10]);
    }

    @Step("Click 'delete' button")
    public static void clickDeleteProductButton()
    {
        saveElementScreenShot(cartPage.getDeleteProduct());
        cartPage.click(cartPage.getDeleteProduct());
    }

    @Step("Click 'save for later' button")
    public static void clickSaveItemForLaterButton()
    {
        saveElementScreenShot(cartPage.getSaveItemForLaterInput());
        cartPage.click(cartPage.getSaveItemForLaterInput());
    }

    @Step("Get save for later Subtotal")
    public static int saveForLaterSubTotal()
    {
        int actual;
        saveElementScreenShot(cartPage.getSaveItemForLaterCount());
        String[] str = cartPage.getText(cartPage.getSaveItemForLaterCount()).split(" ");
        return actual = Integer.parseInt(String.valueOf(str[3].charAt(1)));
    }

    @Step("Close The Browser")
    public static void terminateBrowser()
    {
        cartPage.terminate();
    }


}
