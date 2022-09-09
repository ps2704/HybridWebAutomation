package TestSuite;

import AppPages.*;
import Data.APIData;
import Data.APIDetailData;
import Data.AccountData;
import Data.EnvironmentParameterData;
import FrameWork.*;
import Pages.*;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.awaitility.Awaitility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class MDemo extends AppAction {
    String baseUrl = null;
    int count = 0;
    TestListner testListener = new TestListner();
    Framework framework = new Framework();
    EnvironmentParameterData environmentData = null;
    Map<String, String> keyValuePairMap = new HashMap<>();

    SoftAssert softassert = new SoftAssert();
    //Framework Mframework = new Framework();
    AdbDevice adb_device = new AdbDevice();
    TestListner MtestListener = new TestListner();
    AppiumDriver<MobileElement> driver;

    public void preSetup() {
        environmentData = framework.getData(EnvironmentParameterData.class, "demosite2");
        //baseUrl = environmentData.getBaseurl();
        Awaitility.reset();
        Awaitility.setDefaultPollDelay(100, TimeUnit.MILLISECONDS);
        Awaitility.setDefaultPollInterval(3, TimeUnit.SECONDS);
        Awaitility.setDefaultTimeout(7, TimeUnit.SECONDS);
    }

    @AfterMethod(alwaysRun = true)
    public void aftermethod() throws InterruptedException {
//        System.out.println("***********closing the driver*************");
//        adb_device.clearDeviceLock();
//        System.out.println("***********Release the device*************");
        Framework.extentReports.flush();
       // driver.quit();



    }

    @DataProvider(name = "Data Provide for Independent API", parallel = true)
    public Iterator<Object[]> dataListForSearch() {
        Framework framework = new Framework();
        List<Object[]> apiDataList = framework.getIndependentAPIdata(APIData.class, "TRUE");
        return apiDataList.listIterator();

    }

    @JiraPolicy(logTicketReady = false)
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: Verify login page title test on Login Page")
    @Story("Story Name: To check login page title")
    @Test(groups = {"production", "MDemotest"}, description = "verifying with Checkout with login flow")
    public void CheckoutWithLogin() throws IOException, InterruptedException {
        try {
            AccountData testdata = framework.getData(AccountData.class, "loginset1");
            Framework framework = new Framework();
            if (CommonConstant.Platfrom_name == "IOS") {
                driver = framework.getIOSDriver();
            } else {
                driver = framework.getAppDevice("demosite2");
            }
            MHomePage homePage = new MHomePage(driver);
            MMyAccount maccount = new MMyAccount(driver);
            MListingPage mlisting = new MListingPage(driver);
            MCart mcart = new MCart(driver);
            MCheckout mcheckout = new MCheckout(driver);
            MOrderConfirmation mOrderConfirmation = new MOrderConfirmation(driver);

            ExtentTest childTest1 = testListener.startChild("Mhome page ");
            childTest1.setDescription("This test verifies that user is able to verify homepage content");
            TestListner.extentMap.get().put("childTest1", childTest1);
            Boolean Ishomedataverified= homePage.MHomePageData(childTest1);

            ExtentTest childTest2 = testListener.startChild("MLogin page ");
            childTest2.setDescription("This test verifies that user is able to login with valid creds");
            TestListner.extentMap.get().put("childTest2", childTest2);
            Boolean LoginVerifyied=  maccount.Login(childTest2,testdata);

            ExtentTest childTest3 = testListener.startChild("MListing  page ");
            childTest3.setDescription("This test verifies that user is able to Navigate Listing Page");
            TestListner.extentMap.get().put("childTest3", childTest3);
            Boolean IsFilterandView=  mlisting.FilterandView(childTest3);
            Boolean IsNavigateToL2CategoryList=  mlisting.NavigateToL2CategoryList(childTest3);

            ExtentTest childTest4 = testListener.startChild("HamburgerMenu  Naviagtion ");
            childTest4.setDescription("This test verifies that user is able to click on HamburgerMenu");
            TestListner.extentMap.get().put("childTest4", childTest4);
           Boolean IsHamburgerMenuVerified=  mlisting.HamburgerMenu(childTest4);

            ExtentTest childTest5 = testListener.startChild("HamburgerMenu  Naviagtion ");
            childTest5.setDescription("This test verifies that user is able to verify all HamburgerMenu Option");
            TestListner.extentMap.get().put("childTest5", childTest5);
            Boolean IsHamburgerMenuVerifi= mlisting.HamburgerMenuVerification(childTest5);
            Boolean IsLogin= maccount.Login(childTest5,testdata);
            mlisting.NavigateToL2CategoryList(childTest5);

            ExtentTest childTest6 = testListener.startChild("MCart  page ");
            childTest6.setDescription("This test verifies that user is verify cart screen");
            TestListner.extentMap.get().put("childTest6", childTest6);
            Boolean IsLProceedforcheckout=  mcart.Proceedforcheckout();

            ExtentTest childTest7 = testListener.startChild("MCheckout  page ");
            childTest7.setDescription("This test verifies that user is able to fill details");
            TestListner.extentMap.get().put("childTest7", childTest7);
            Boolean IscheckoutFieldsFilled= mcheckout.checkoutfileds(testdata);

            ExtentTest childTest8 = testListener.startChild("Order Summary Validation");
            childTest8.setDescription("This test verifies that user is able to verify Order Summary");
            TestListner.extentMap.get().put("childTest8", childTest8);
            Boolean IsOrderSummryvalidate=   mcheckout.VerifySubtotalsummary(childTest8);

            ExtentTest childTest9 = testListener.startChild("MCheckout page  page ");
            childTest9.setDescription("This test verifies that user is able to verify checkout screen");
            TestListner.extentMap.get().put("childTest9", childTest9);
             mcheckout.checkout(childTest9);

            ExtentTest childTest10 = testListener.startChild("MOrderCOnfirmation  page ");
            childTest10.setDescription("This test verifies that user is able to verify Order confrimation screen");
            TestListner.extentMap.get().put("childTest10", childTest10);
            Boolean Isorderconfirm= mOrderConfirmation.OrderConfirmNumber(childTest10);


        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("***********closing the driver*************");
            adb_device.clearDeviceLock();
            System.out.println("***********Release the device*************");
                        driver.quit();
        }
    }



    @JiraPolicy(logTicketReady = false)
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: Verify login page title test on Login Page")
    @Story("Story Name: To check login page title")
    @Test(groups = {"production", "MDemotest"}, description = "verifying with the checkout with Registration flow ")
    public void RegisterPage() throws IOException, InterruptedException {
        try {
            AccountData testdata = framework.getData(AccountData.class, "loginset1");
            Framework framework = new Framework();
            if (CommonConstant.Platfrom_name == "IOS") {
                driver = framework.getIOSDriver();
            } else {
                driver = framework.getAppDevice("demosite2");
            }
//            ExtentTest childTest1 = testListener.startChild("Gender Selection");
//            childTest1.setDescription("Selecting Gender as Women.");
//            TestListner.extentMap.get().put("child1", childTest1);
            MHomePage homePage = new MHomePage(driver);
            MMyAccount maccount= new MMyAccount(driver);
            MListingPage mlisting = new MListingPage(driver);
            MCart mcart= new MCart(driver);
            MCheckout mcheckout = new MCheckout(driver);
            MOrderConfirmation mOrderConfirmation = new MOrderConfirmation(driver);

            ExtentTest childTest1 = testListener.startChild("Mhome page ");
            childTest1.setDescription("This test verifies that user is able to verify homepage content");
            TestListner.extentMap.get().put("childTest1", childTest1);
           boolean isMHomepageValidation = homePage.MHomePageData(childTest1);

            ExtentTest childTest2 = testListener.startChild("MLogin page ");
            childTest2.setDescription("This test verifies that user is able to login with valid creds");
            TestListner.extentMap.get().put("childTest2", childTest2);
            boolean isLoginepageValidation= maccount.Login(childTest2,testdata);

            ExtentTest childTest3 = testListener.startChild("MListing  page ");
            childTest3.setDescription("This test verifies that user is able to Navigate Listing Page");
            TestListner.extentMap.get().put("childTest3", childTest3);
            boolean isL2categoryValidation=  mlisting.NavigateToL2CategoryList(childTest3);

            ExtentTest childTest4 = testListener.startChild("MCart  page ");
            childTest4.setDescription("This test verifies that user is verify cart screen");
            TestListner.extentMap.get().put("childTest4", childTest4);
            boolean isproceedtocheckoutValidation=  mcart.Proceedforcheckout();

            ExtentTest childTest5 = testListener.startChild("MCheckout page  page ");
            childTest5.setDescription("This test verifies that user is able to fill details");
            TestListner.extentMap.get().put("childTest5", childTest5);
            boolean isproceedtocheckoutfieldsValidation=  mcheckout.checkoutfileds(testdata);

            ExtentTest childTest6 = testListener.startChild("Order Summry validation ");
            childTest6.setDescription("This test verifies that user is able to verify Order Summry validation");
            TestListner.extentMap.get().put("childTest6", childTest6);
            mcheckout.VerifySubtotalsummary(childTest6);

            ExtentTest childTest7 = testListener.startChild("MCheckout page  page ");
            childTest7.setDescription("This test verifies that user is able to verify checkout screen");
            TestListner.extentMap.get().put("childTest7", childTest7);
            mcheckout.checkout(childTest7);

            ExtentTest childTest8 = testListener.startChild("MOrderCOnfirmation  page ");
            childTest8.setDescription("This test verifies that user is able to verify Order confrimation screen");
            TestListner.extentMap.get().put("childTest8", childTest8);
            boolean isorderconfrim=  mOrderConfirmation.OrderConfirmNumber(childTest8);

//            boolean isGlobalSearchWork = page.SearchGlobally();
//            HashMap<String,String> getPLPDetail = plp.GlobalSearchWithData();
//            HashMap<String,String> getCartDetail = view.getviewCheckoutAllData();
//            HashMap<String,String> getcheckoutDetail = verify.getCheckoutAllDataForCOD();
//            ExtentTest childTest2 = testListener.startChild("Compare Subtotal");
//            childTest2.setDescription("Comparing subtotal");
//            TestListner.extentMap.get().put("child2", childTest2);
//            if(getCartDetail.get("Subtotal").equals(getcheckoutDetail.get("Subtotal"))) {
//                childTest2.log(LogStatus.PASS, "Both Compare And pass" );
//            }
//            else {
//                childTest2.log(LogStatus.FAIL, "Both Compare And Fail");
//            }
//            HashMap<String, String> getCheckoutCalculation = checkoutcal.VerifyPricesForKW(getcheckoutDetail,driver);
//            ExtentTest childTest3 = testListener.startChild("Compare Grantotal");
//            childTest3.setDescription("Comparing Grandtotal");
//            TestListner.extentMap.get().put("child3", childTest3);
//            HashMap<String,String> getOrderno = Placeorder.GetOrderNumber();
//            Common_Constants.orderid=getOrderno.get("ordernumber");
//            String Exp_order = Common_Constants.orderid;
//            Exp_order = ML_query_order.replace("$orderid",getOrderno.get("ordernumber"));
//            String  Exp_magento_order= Common_Constants.orderid;
//            Exp_magento_order  = Magento_query_order.replace("$orderid",getOrderno.get("ordernumber"));
//            getCartDetail.putAll(getPLPDetail);
//            getCheckoutCalculation.putAll(getCartDetail);
//            getcheckoutDetail.putAll(getCartDetail);
//            // getCheckoutCalculation.putAll(getcheckoutDetail);
//            getOrderno.putAll(getcheckoutDetail);
//            System.out.println("All value added in getorderno map are: "+getOrderno);
//            HashMap<String,String> Finalmap = new HashMap<String, String>();
//            Finalmap.putAll(getOrderno);

//            HashMap<String,String> DB_ML = db_connection.DBConnection(Common_Constants.MLDB_url,Common_Constants.username,Common_Constants.Password,Exp_order);
//
//            if(DB_ML.equals(Finalmap)) {
//
//                System.out.println("DB_ML values are matched with App values");
//            }
//            else {
//                System.out.println("DB_ML values are not mnatched with App values");
//            }

//            HashMap<String, String> DB_Magento = db_connection.DBConnection(Common_Constants.MLDB_url, Common_Constants.username, Common_Constants.Password, Exp_magento_order);
//            if(DB_ML.equals(Finalmap)) {
//                System.out.println("DB_Magento values are mnatched with App values");
//            }
//            else {
//                System.out.println("DB_Magento values are not mnatched with App values");
//            }




        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("***********closing the driver*************");
            adb_device.clearDeviceLock();
            System.out.println("***********Release the device*************");
            driver.quit();
        }
    }


    @JiraPolicy(logTicketReady = false)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Case Description: Verify login page title test on Login Page")
    @Story("Story Name: To check login page title")
   // @Test(groups = {"production", "MDemotest"},description = "verifying the invalid login flow")
    public void Invalidloginscenrio() throws IOException, InterruptedException {
        try {
            AccountData testdata = framework.getData(AccountData.class, "loginset2");
            Framework framework = new Framework();
            if (CommonConstant.Platfrom_name == "IOS") {
                driver = framework.getIOSDriver();
            } else {
                driver = framework.getAppDevice("demosite2");
            }
//            ExtentTest childTest1 = testListener.startChild("Gender Selection");
//            childTest1.setDescription("Selecting Gender as Women.");
//            TestListner.extentMap.get().put("child1", childTest1);
            MHomePage homePage = new MHomePage(driver);
            MMyAccount maccount= new MMyAccount(driver);
            MListingPage mlisting = new MListingPage(driver);
            MCart mcart= new MCart(driver);
            MCheckout mcheckout = new MCheckout(driver);
            MOrderConfirmation mOrderConfirmation = new MOrderConfirmation(driver);

            ExtentTest childTest1 = testListener.startChild("Mhome page ");
            childTest1.setDescription("This test verifies that user is able to verify homepage content");
            TestListner.extentMap.get().put("childTest1", childTest1);
            homePage.MHomePageData(childTest1);

            ExtentTest childTest2 = testListener.startChild("MLogin page ");
            childTest2.setDescription("This test verifies that user is able to login with valid creds");
            TestListner.extentMap.get().put("childTest2", childTest2);
            maccount.Login(childTest2,testdata);

//             testdata = framework.getData(AccountData.class, "loginset3");
//            ExtentTest childTest2 = testListener.startChild("MLogin page ");
//            childTest2.setDescription("This test verifies that user is able to right username with valid creds");
//            TestListner.extentMap.get().put("child2", childTest2);
//
//            maccount.Login(testdata);



        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("***********closing the driver*************");
            adb_device.clearDeviceLock();
            System.out.println("***********Release the device*************");
            driver.quit();
        }
    }
}




