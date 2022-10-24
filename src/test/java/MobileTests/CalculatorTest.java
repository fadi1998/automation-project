package MobileTests;

import Extensions.Verifications;
import Utils.AllureListeners;
import WorkFlows.CalculatorWorkFlow;
import org.testng.annotations.*;

@Listeners(AllureListeners.class)
public class CalculatorTest extends CalculatorWorkFlow {

    @BeforeClass
    public void setUp()
    {
        openCalculatorApp();
    }

    @BeforeMethod
    public static void clearResult()
    {
        getClearResult();
    }

    @Test
    @Parameters({"result"})
    public static void TwoPlusEightExpectedTen(@Optional("10") int expected)
    {
        clickNumberTwo();
        clickPlusSign();
        clickNumberEight();
        clickEquals();
        int actual = Integer.parseInt(getResult());

        Verifications.verifyEightPlusTwo_EqualTen(actual, expected);
    }

    @Test
    @Parameters({"result"})
    public static void TwoMulEightExpectedSixteen(@Optional("16") int expected)
    {
        clickNumberTwo();
        clickMulSign();
        clickNumberEight();
        clickEquals();
        int actual = Integer.parseInt(getResult());

        Verifications.verifyEightMulTwo_EqualSixteen(actual, expected);
    }



}
