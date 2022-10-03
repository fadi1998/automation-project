package AmazonTests.ShoppingCartSuite;

import Extensions.Verifications;
import Pages.WebPages.AmazonCartPage;
import Utils.AllureListeners;
import WorkFlows.AmazonCartWorkFlows;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Listeners(AllureListeners.class)
public class ShoppingCartTest extends AmazonCartWorkFlows{

    @BeforeClass
    public void setUp()
    {
       visitAmazonHomePage();
    }

    @BeforeMethod()
    public void clearInput()
    {
        emptySearchInput();
    }

    @DataProvider(name = "my-data-provider-cart")
    Object[][] myDataProviderForCheckHowManyResults()
    {
        return new Object[][] {{"books best sellers new releases 2022",1}, {"socks organizer",2}};
    }
    @Test(priority = 0, dataProvider = "my-data-provider-cart")
    @Description("Shopping cart Description:Trying to add a product to the shopping cart")
    @Story("adding a product to the shopping cart test")
    public void addingProductToCart(String productName, int expected)
    {
        addProductToCart(productName);
        goToCart();
        int actual = ProductSubTotal();

        Verifications.verifyCartSubTotal(actual, expected);
    }

    @Test(priority = 1)
    @Description("shopping cart Description:Trying to delete a product from the shopping cart")
    @Severity(SeverityLevel.BLOCKER)
    @Story("deleting a product from shopping cart test")
    @Parameters({"expectedDeletedNumber"})
    public void deletingProductsFromCart(@Optional("1") int expected)
    {

        clickDeleteProductButton();
        int actual = ProductSubTotal();

        Verifications.verifyCartSubTotal(actual, expected);
    }

    @Test(priority = 2)
    @Description("Shopping cart Description:Trying to save item to add to cart for later")
    @Severity(SeverityLevel.BLOCKER)
    @Story("adding item to save for later test")
    @Parameters({"theSavedNumber"})
    public void saveItemForLater(@Optional("1") int expected)
    {
        clickSaveItemForLaterButton();
        int actual = saveForLaterSubTotal();
        Verifications.verifySaveForLaterSubTotal(actual, expected);
    }

    @AfterMethod
    public void takeScreenShotOnFaliure(ITestResult iTestResult)
    {
        if(iTestResult.getStatus() == ITestResult.FAILURE)
        {
            saveFullPageScreenShot(cartPage.getDriver());
        }
    }

    @AfterClass()
    public void terminate()
    {
        AmazonCartWorkFlows.terminateBrowser();
    }
}
