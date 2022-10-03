package Utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CaptureScreenShots{

    @Attachment(value = "Page screenshot", type= "image/png")
    protected static byte[] saveFullPageScreenShot(WebDriver driver)
    {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Section screenshot", type= "image/png")
    protected static byte[] saveSectionScreenShot(WebElement section)
    {
        return section.getScreenshotAs(OutputType.BYTES);

    }

    @Attachment(value = "Element screenshot", type= "image/png")
    protected static byte[] saveElementScreenShot(WebElement elm)
    {
        return elm.getScreenshotAs(OutputType.BYTES);
    }
}
