package TestSuite;

import AppPages.MHomePage;
import AppPages.MListingPage;
import AppPages.MMyAccount;
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
        environmentData = framework.getData(EnvironmentParameterData.class, "demosite");
        //baseUrl = environmentData.getBaseurl();
        Awaitility.reset();
        Awaitility.setDefaultPollDelay(100, TimeUnit.MILLISECONDS);
        Awaitility.setDefaultPollInterval(3, TimeUnit.SECONDS);
        Awaitility.setDefaultTimeout(7, TimeUnit.SECONDS);
    }
    @AfterMethod(alwaysRun = true)
    public void aftermethod() throws InterruptedException {
        System.out.println("***********closing the driver*************");
        adb_device.clearDeviceLock();
        System.out.println("***********Release the device*************");
        driver.quit();
    }

//    @BeforeMethod(alwaysRun = true)
//    public void preSetup() {
//        environmentData = framework.getData(EnvironmentParameterData.class, "demosite");
//        baseUrl = environmentData.getBaseurl();
//        Awaitility.reset();
//        Awaitility.setDefaultPollDelay(100, TimeUnit.MILLISECONDS);
//        Awaitility.setDefaultPollInterval(3, TimeUnit.SECONDS);
//        Awaitility.setDefaultTimeout(7, TimeUnit.SECONDS);
//    }
//
//    @AfterMethod(alwaysRun = true)
//    public void flushExtent() {
//        Framework.extentReports.flush();
//    }

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
    @Test(groups = {"production", "MDemotest"},description = "verifying with register user")
    public void HomePage() throws IOException, InterruptedException {
        try {
            AccountData testdata = framework.getData(AccountData.class, "loginset1");
            Framework framework = new Framework();
            if (CommonConstant.Platfrom_name == "IOS") {
                driver = framework.getIOSDriver();
            } else {
                driver = framework.getAndroidDriver();
            }
//            ExtentTest childTest1 = testListener.startChild("Gender Selection");
//            childTest1.setDescription("Selecting Gender as Women.");
//            TestListner.extentMap.get().put("child1", childTest1);
            MHomePage homePage = new MHomePage(driver);
            MMyAccount maccount= new MMyAccount(driver);
            MListingPage mlisting = new MListingPage(driver);
            homePage.MHomePageData();
            maccount.Login(testdata);
            mlisting.NavigateToL2CategoryList();

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


        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }



