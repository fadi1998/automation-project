package AmazonTests.SearchSuite;

import Pages.AmazonSearchPage;
import Utils.DriverUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SearchTest {
    AmazonSearchPage amazonSearchPage;

    @BeforeClass
    public void setUp()
    {
        WebDriver driver = DriverUtils.createDriverObj(2);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        amazonSearchPage = new AmazonSearchPage(driver, wait);
        amazonSearchPage.visit("https://www.amazon.com/");
    }

    @BeforeMethod
    public void EmptySearchInput()
    {
        amazonSearchPage.clear(amazonSearchPage.getSearchBox());
    }

    @DataProvider(name = "my-data-provider-search")
    Object[][] myDataProviderForCheckHowManyResults()
    {
        return new Object[][] {{"playstation 5","30,000"},{"airpods pro case cover","7,000"}};
    }
    @Test(dataProvider = "my-data-provider-search")
    @Description("Search Description:Trying to search for a product and checking how many results we got")
    @Severity(SeverityLevel.BLOCKER)
    @Story("checking how many results search test")
    public void checkHowManyResultsWeGot(String searchText,String actual)
    {
        amazonSearchPage.typeInto(searchText, amazonSearchPage.getSearchBox());
        amazonSearchPage.click(amazonSearchPage.getSearchBoxSubmit());

        String[] resultExpected = amazonSearchPage.getText(amazonSearchPage.getResult()).split(" ");
        Assert.assertEquals(actual, resultExpected[3]);
    }

    @Test()
    @Parameters({"minPrice", "searchForResult"})
    @Description("Search Description:Trying to search for a product with the minimum price")
    @Severity(SeverityLevel.BLOCKER)
    @Story("search for the cheapest product")
    public void checkTheCheapestPriceForAProduct(@Optional("5.59") double expectedPrice, @Optional("airpods pro case cover") String searchText)
    {
        amazonSearchPage.typeInto(searchText, amazonSearchPage.getSearchBox());
        amazonSearchPage.click(amazonSearchPage.getSearchBoxSubmit());
        double actualPrice =0;
        List<WebElement> priceList = amazonSearchPage.findElems(amazonSearchPage.getPriceList());

        double price = Double.parseDouble(priceList.get(0).getAttribute("innerText").replace("$",""));
        for(WebElement curr : priceList)
        {
            actualPrice = Double.parseDouble(curr.getAttribute("innerText").replace("$", ""));
            if( actualPrice < price)
                price = actualPrice;
        }
        Assert.assertEquals(price,expectedPrice);
    }

    @AfterMethod
    public void takenScreenShot(ITestResult result)
    {
        if(result.getStatus() == ITestResult.FAILURE)
        {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy__hh_mm_ss");
            LocalDateTime now = LocalDateTime.now();
            String fileName = "search failed test "+result.getMethod().getMethodName()+"_"+ now.format(dtf);
            amazonSearchPage.takeScreenShot("captureScreenShots", fileName, amazonSearchPage.getDriver());
        }
    }

    @AfterClass
    public void terminate()
    {
        amazonSearchPage.terminate();
    }
}

