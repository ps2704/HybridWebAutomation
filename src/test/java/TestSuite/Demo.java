package TestSuite;

import Data.EnvironmentParameterData;
import Data.OR;
import Data.AccountData;
import FrameWork.Loginsync;
import Pages.Cart;
import Pages.Checkout;
import Pages.HomePage;
import Pages.ListingPage;
import com.relevantcodes.extentreports.ExtentTest;
import org.awaitility.Awaitility;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import Data.EnvironmentParameterData;
import FrameWork.Framework;
import FrameWork.TestListner;

import java.util.concurrent.TimeUnit;

public class Demo {
    String baseUrl = null;
    TestListner testListener = new TestListner();
    Framework framework = new Framework();
    @BeforeTest(alwaysRun=true)
    public void preSetup()
    {
        EnvironmentParameterData environmentData = framework.getData(EnvironmentParameterData.class, "demosite");
        baseUrl = environmentData.getBaseurl();
        Awaitility.reset();
        Awaitility.setDefaultPollDelay(100, TimeUnit.MILLISECONDS);
        Awaitility.setDefaultPollInterval(3, TimeUnit.SECONDS);
        Awaitility.setDefaultTimeout(7, TimeUnit.SECONDS);
    }

    @AfterTest(alwaysRun=true)
    public void flushExtent()
    {
        Framework.extentReports.flush();
    }

    @Test(groups = { "production","Demo" } )
    public void Demo_on_Web() throws Throwable {
        AccountData Login = Loginsync.getInstance().getLogin();
        WebDriver browser = framework.getBrowser("demosite");
        try {
            browser.get(baseUrl);
           // AccountData testdata = framework.getData(AccountData.class, "guest");
            HomePage homePage = new HomePage(browser);
            ListingPage listingPage = new ListingPage(browser);
            Cart cart = new Cart(browser);
           // Checkout checkout = new Checkout(browser);
            ExtentTest childTest = testListener.startChild("Existing User Login test");
            childTest.setDescription("This test verifies that user is able to login with existing credentials.");
            TestListner.extentMap.get().put("child", childTest);
            Boolean isSignIn = homePage.UserSignIn(Login);

            //Boolean isProductAdded = listingPage.AddToBagCategoryProduct();
            //Boolean isProceedforCheckout = cart.Proceedforcheckout();
            //Boolean ischeckoutDone = checkout.checkout();


        }catch (Exception e){
            System.out.println(e);
        }finally {
            browser.close();
        }

    }
}
