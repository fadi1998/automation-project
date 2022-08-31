package AmazonTests.ShoppingCartSuite;

import Pages.AmazonCartPage;
import Utils.DriverUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ShoppingCartTest {
    AmazonCartPage amazonCartPage;

    @BeforeClass
    public void setUp()
    {
        WebDriver driver = DriverUtils.createDriverObj(2);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        amazonCartPage = new AmazonCartPage(driver, wait);
        amazonCartPage.visit("https://www.amazon.com/");

    }

    @BeforeMethod()
    public void clearInput()
    {
        amazonCartPage.clear(amazonCartPage.getSearchBox());
    }

    @DataProvider(name = "my-data-provider-cart")
    Object[][] myDataProviderForCheckHowManyResults()
    {
        return new Object[][] {{"books best sellers new releases 2022",1}, {"socks organizer",2}};
    }
    @Test(priority = 0, dataProvider = "my-data-provider-cart")
    @Description("Shopping cart Description:Trying to add a product to the shopping cart")
    @Severity(SeverityLevel.BLOCKER)
    @Story("adding a product to the shopping cart test")
    public void addingProductToCart(String text, int expected)
    {
        amazonCartPage.typeInto(text, amazonCartPage.getSearchBox());
        amazonCartPage.click(amazonCartPage.getSearchBoxSubmit());
        amazonCartPage.click(amazonCartPage.getProduct());
        //amazonCartPOM.waitForPresenceOfElementLocated(amazonCartPOM.getAddToCartButton());
        amazonCartPage.click(amazonCartPage.getAddToCartButton());
        amazonCartPage.click(amazonCartPage.getGoToCart());

        //System.out.println(amazonCartPOM.getText(amazonCartPOM.getTotalProducts()).split("")[10]);
        int actual = Integer.parseInt(amazonCartPage.getText(amazonCartPage.getTotalProducts()).split("")[10]);
        Assert.assertEquals(actual, expected);
    }

    @Test(priority = 1)
    @Description("shopping cart Description:Trying to delete a product from the shopping cart")
    @Severity(SeverityLevel.BLOCKER)
    @Story("deleting a product from shopping cart test")
    @Parameters({"expectedDeletedNumber"})
    public void deletingProductsFromCart(@Optional("1") int expected)
    {
        int actual = -1;
        amazonCartPage.click(amazonCartPage.getDeleteAllProducts());
        actual = Integer.parseInt(amazonCartPage.getText(amazonCartPage.getTotalProducts()).split("")[10]);
        Assert.assertEquals(actual, expected);
    }

    @Test(priority = 2)
    @Description("Shopping cart Description:Trying to save item to add to cart for later")
    @Severity(SeverityLevel.BLOCKER)
    @Story("adding item to save for later test")
    @Parameters({"theSavedNumber"})
    public void saveItemForLater(@Optional("1") int expected)
    {
        amazonCartPage.visit("https://www.amazon.com/gp/cart/view.html?ref_=nav_cart");
        int actual = -1;
        amazonCartPage.click(amazonCartPage.getSaveItemForLaterInput());
        String[] str = amazonCartPage.getText(amazonCartPage.getSaveItemForLaterCount()).split(" ");
        actual = Integer.parseInt(String.valueOf(str[3].charAt(1)));
        Assert.assertEquals(actual, expected);
    }
    @AfterMethod()
    public void takenScreenShot(ITestResult result)
    {
        if(result.getStatus() == ITestResult.FAILURE)
        {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy__hh_mm_ss");
            LocalDateTime now = LocalDateTime.now();
            String fileName = "failedShoppingCartTest"+result.getMethod().getMethodName()+"_"+dtf.format(now);
            amazonCartPage.takeScreenShot("captureScreenShots",fileName, amazonCartPage.getDriver());
        }
    }

    @AfterClass()
    public void terminate()
    {
        amazonCartPage.terminate();
    }
}
