package AmazonTests.SearchSuite;

import Extensions.Verifications;
import Pages.WebPages.AmazonSearchPage;
import Utils.AllureListeners;
import WorkFlows.AmazonSearchWorkFlows;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.ITestResult;
import org.testng.annotations.*;

@Listeners(AllureListeners.class)
public class SearchTest extends AmazonSearchWorkFlows{

    @BeforeClass
    public void setUp()
    {
        AmazonSearchWorkFlows.visitAmazonHomePage();
    }

    @BeforeMethod
    public void emptySearchInput()
    {
        emptyInput();
    }

    @DataProvider(name = "my-data-provider-search")
    Object[][] myDataProviderForCheckHowManyResults()
    {
        return new Object[][] {{"playstation 5","30,000"},{"airpods pro case cover","7,000"}};
    }
    @Test(dataProvider = "my-data-provider-search")
    @Description("Search Description:Trying to search for a product and checking how many results we got")
    @Story("checking how many results search test")
    public void checkHowManyResultsWeGot(String productName,String actual)
    {
        typeIntoSearchBox(productName);
        clickSearchButton();
        String expected = getProductsResult();

        Verifications.verifyResult(actual, expected);
    }

    @Test()
    @Parameters({"minPrice", "searchForResult"})
    @Description("Search Description:Trying to search for a product with the minimum price")
    @Story("search for the cheapest product")
    public void checkTheCheapestPriceForAProduct(@Optional("5.51") double expected, @Optional("airpods pro case cover") String productName)
    {
        typeIntoSearchBox(productName);
        clickSearchButton();
        double actual = getCheapestPrice();

        Verifications.verifyCheapestPrice(actual, expected);
    }

    @AfterMethod
    public void takeScreenShotOnFaliure(ITestResult iTestResult)
    {
        if(iTestResult.getStatus() == ITestResult.FAILURE)
        {
            saveFullPageScreenShot(amazonSearchPage.getDriver());
        }
    }

    @AfterClass
    public void terminate()
    {
        AmazonSearchWorkFlows.terminateBrowser();
    }
}

