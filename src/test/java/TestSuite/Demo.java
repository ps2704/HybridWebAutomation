package TestSuite;

import Data.*;
import FrameWork.*;
//import Pages.Cart;
import Pages.*;
//import Pages.ListingPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.awaitility.Awaitility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import Data.EnvironmentParameterData;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Demo {
    String baseUrl = null;
    int count = 0;
    TestListner testListener = new TestListner();
    Framework framework = new Framework();
    static String Token = "";
    String Lang = "en";
    String Country = "kw";
    EnvironmentParameterData environmentData = null;
    Map<String, String> keyValuePairMap = new HashMap<>();
    String pathMaster = System.getProperty("user.dir") + "\\src\\test\\java\\Api\\Response\\Master";
    String pathPreProd = System.getProperty("user.dir") + "\\src\\test\\java\\Api\\Response\\PreProd";

    @BeforeMethod(alwaysRun = true)
    public void preSetup() {
        environmentData = framework.getData(EnvironmentParameterData.class, "api");
        baseUrl = environmentData.getBaseurl();
        Awaitility.reset();
        Awaitility.setDefaultPollDelay(100, TimeUnit.MILLISECONDS);
        Awaitility.setDefaultPollInterval(3, TimeUnit.SECONDS);
        Awaitility.setDefaultTimeout(7, TimeUnit.SECONDS);
    }

    @AfterMethod(alwaysRun = true)
    public void flushExtent() {
        Framework.extentReports.flush();
    }

    @DataProvider(name = "Data Provide for Independent API", parallel = true)
    public Iterator<Object[]> dataListForSearch() {
        Framework framework = new Framework();
        List<Object[]> apiDataList = framework.getIndependentAPIdata(APIData.class, "TRUE");
        return apiDataList.listIterator();

    }

    @JiraPolicy(logTicketReady = true)
    @Test(groups = {"production", "Demo"},description = "verifying with register checkout  user flow")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: Verify login page title test on Login Page")
    @Story("Story Name: To check login page title")
    public void Demo_on_Web() throws Throwable {
        environmentData = framework.getData(EnvironmentParameterData.class, "demosite");
        baseUrl = environmentData.getBaseurl();
        AccountData Login = Loginsync.getInstance().getLogin();
        WebDriver browser = framework.getWebBrowser("demosite");
        try {
            browser.get(baseUrl);
            AccountData testdata = framework.getData(AccountData.class, "loginset1");
            //  AccountData testdata1 = framework.getData(AccountData.class, "loginset1");
            // HomePage homePage = new HomePage(browser);
            MyAccount myaccount = new MyAccount(browser);
            HomePage homePageData = new HomePage(browser);
            ListingPage listingPage = new ListingPage(browser);
            Cart cart = new Cart(browser);
            OrderConfirmation ordercon = new OrderConfirmation(browser);
            Checkout checkout = new Checkout(browser);

            ExtentTest childTest1 = testListener.startChild("Home Page validation");
            childTest1.setDescription("This test verifies that user is able see logo and promo text.");
            TestListner.extentMap.get().put("child1", childTest1);
            Boolean Ishomedataverified = homePageData.HomePageData(childTest1);

          //  Sancs.assertTrue(Ishomedataverified, "Yes It's Verify");
            ExtentTest childTest2 = testListener.startChild("New registration");
            childTest2.setDescription("This test verifies that user is able to register.");
            TestListner.extentMap.get().put("child2", childTest2);
            Boolean IsUserRegistered = myaccount.newUserregistration(childTest2,testdata);

            ExtentTest childTest13 = testListener.startChild("My Account validation");
            childTest13.setDescription("This test verifies that user is able validate my account.");
            TestListner.extentMap.get().put("childTest13", childTest2);
            Boolean IsMyaccountvalidate = myaccount.MyAccountValidation(childTest13);

            ExtentTest childTest4 = testListener.startChild("Naviagting to l2 category");
            childTest4.setDescription("This test verifies that user is able to Naviagate to l2 category.");
            TestListner.extentMap.get().put("child4", childTest4);
            Boolean IsNavigationtocat2done = listingPage.NavigateToL2CategoryList(childTest4);
            //Sancs.assertTrue(IsNavigationtocat2done, "Navigation Successfully");

            ExtentTest childTest5 = testListener.startChild("Navigate to Listing page");
            childTest5.setDescription("This test verifies that user is able to Navigate to Listing page.");
            TestListner.extentMap.get().put("child5", childTest5);
           Boolean IsNavigationtolistingPagedone = listingPage.PlpItems(childTest5);
           // Sancs.assertTrue(IsNavigationtolistingPagedone, "Navigation Successful");

            ExtentTest childTest6 = testListener.startChild("Navigate to PDP page");
            childTest6.setDescription("This test verifies that user is able to Navigate to PDP page.");
            TestListner.extentMap.get().put("child6", childTest6);
            Boolean IsPDPNavigationDone = listingPage.ClickPlpItems(childTest6);
            //Sancs.assertTrue(IsPDPNavigationDone, "Navigation Successful");
            //cart.VerifyCartCountAfterAdd();
            ExtentTest childTest7 = testListener.startChild("Increase the Cart Qty");
            childTest7.setDescription("This test verifies that user is able to Increase the Cart Qty.");
            TestListner.extentMap.get().put("child7", childTest7);
            Boolean IsPDPQTYincreased= cart.CartVerification(childTest7,1);

            ExtentTest childTest8 = testListener.startChild("Decrease the Cart Qty");
            childTest8.setDescription("This test verifies that user is able to Decrease the Cart Qty.");
            TestListner.extentMap.get().put("child8", childTest8);
            Boolean IsPDPQTYDecreased=cart.CartVerification(childTest8,-1);
            //Boolean IsPDPQTYDecreased= cart.CartVerification(childTest8,);

            ExtentTest childTest9 = testListener.startChild("Remove the Cart Qty");
            childTest9.setDescription("This test verifies that user is able to Remove the Cart Qty.");
            TestListner.extentMap.get().put("child9", childTest9);
            //cart.CartVerification(childTest9,0);
            Boolean IsPDPQTYdeleted= cart.CartVerification(childTest9,0);

            Boolean IsNavigationtocat2done2 = listingPage.NavigateToL2CategoryList(childTest9);
            Boolean Isplpitemclicked2=listingPage.ClickPlpItems(childTest9);

            ExtentTest childTest10 = testListener.startChild("Proceed to Checkout");
            childTest10.setDescription("This test verifies that user is able to Proceed to Checkout.");
            TestListner.extentMap.get().put("child10", childTest10);
            Boolean IsProceedForCheckout= cart.Proceedforcheckout(childTest10);


            ExtentTest childTest11 = testListener.startChild("Proceed to Shipping and payment page");
            childTest11.setDescription("This test verifies that user is able to Proceed to Shipping and payment page.");
            TestListner.extentMap.get().put("child11", childTest11);
            Boolean IsProceedForshippingPage= checkout.checkoutPage(testdata);

            ExtentTest childTest12 = testListener.startChild("Order Confirmation Page");
            childTest12.setDescription("This test verifies that user is able to Proceed to Order Confirmation Page. ");
            TestListner.extentMap.get().put("child12", childTest12);
            Boolean OrderConfrimation= ordercon.OrderConfirmNumber();
            // ordercon.StoreDynamicDataOrderId();
            // Cart cart = new Cart(browser);
            // Checkout checkout = new Checkout(browser);
//            ExtentTest childTest2 = testListener.startChild("Existing User Login test");
//            childTest2.setDescription("This test verifies that user is able to login with existing credentials.");
//            TestListner.extentMap.get().put("child", childTest2);
            //  myaccount.UserRegistration(testdata1);
            // Boolean isSignIn = myaccount.UserSignIn(Login);

            //Boolean isProductAdded = listingPage.AddToBagCategoryProduct();
            //Boolean isProceedforCheckout = cart.Proceedforcheckout();
            //Boolean ischeckoutDone = checkout.checkout();


        } catch (Exception e) {
            System.out.println(e);
            framework.logErrorWithSnapshot(browser,e);
        } finally {
            browser.close();
        }

    }

    @JiraPolicy(logTicketReady = false)
    @Test(groups = {"production", "Demo1"},description = "verifying with guest user flow")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: Verify login page title test on Login Page")
    @Story("Story Name: To check login page title")
    public void Demo_on_WebWithGuestCheckout()  throws InterruptedException, IOException {
        //  AccountData Login = Loginsync.getInstance().getLogin();
        //  WebDriver browser = framework.getBrowser("demosite");
       // Thread.sleep(5000);
        environmentData = framework.getData(EnvironmentParameterData.class, "demosite1");
        baseUrl = environmentData.getBaseurl();
        WebDriver browser = framework.getWebBrowser("demosite1");
        try {
            browser.get(baseUrl);
            AccountData testdata = framework.getData(AccountData.class, "loginset");
            //  AccountData testdata1 = framework.getData(AccountData.class, "loginset1");
            // HomePage homePage = new HomePage(browser);
            MyAccount myaccount = new MyAccount(browser);
            HomePage homePageData = new HomePage(browser);
            ListingPage listingPage = new ListingPage(browser);
            Cart cart = new Cart(browser);
            OrderConfirmation ordercon = new OrderConfirmation(browser);
            Checkout checkout = new Checkout(browser);

            ExtentTest childTest1 = testListener.startChild("Existing User Login test");
            childTest1.setDescription("This test verifies that user is able to login with existing credentials.");
            TestListner.extentMap.get().put("child1", childTest1);
            Boolean Ishomedataverified = homePageData.HomePageData(childTest1);

            Thread.sleep(5000);
            ExtentTest childTest2 = testListener.startChild("Naviagting to l2 category");
            childTest2.setDescription("This test verifies that user is able to Naviagate to l2 category.");
            TestListner.extentMap.get().put("childTest2", childTest2);
            Boolean IsNavigationtocat2done = listingPage.NavigateToL2CategoryList(childTest2);
            //Sancs.assertTrue(IsNavigationtocat2done, "Navigation Successfully");

            ExtentTest childTest3 = testListener.startChild("Navigate to Listing page");
            childTest3.setDescription("This test verifies that user is able to Navigate to Listing page.");
            TestListner.extentMap.get().put("childTest3", childTest3);
            Boolean IsNavigationtolistingPagedone = listingPage.PlpItems(childTest3);
            // Sancs.assertTrue(IsNavigationtolistingPagedone, "Navigation Successful");

            ExtentTest childTest4 = testListener.startChild("Navigate to PDP page");
            childTest4.setDescription("This test verifies that user is able to Navigate to PDP page.");
            TestListner.extentMap.get().put("childTest4", childTest4);
            Boolean IsPDPNavigationDone = listingPage.ClickPlpItems(childTest4);
            //Sancs.assertTrue(IsPDPNavigationDone, "Navigation Successful");
            //cart.VerifyCartCountAfterAdd();
            ExtentTest childTest5 = testListener.startChild("Increase the Cart Qty");
            childTest5.setDescription("This test verifies that user is able to Increase the Cart Qty.");
            TestListner.extentMap.get().put("childTest5", childTest5);
            Boolean IsPDPQTYincreased= cart.CartVerification(childTest5,1);

            ExtentTest childTest6 = testListener.startChild("Decrease the Cart Qty");
            childTest6.setDescription("This test verifies that user is able to Decrease the Cart Qty.");
            TestListner.extentMap.get().put("childTest6", childTest6);
            //cart.CartVerification(childTest6,-1);
            Boolean IsPDPQTYDecreased= cart.CartVerification(childTest6,1);

            ExtentTest childTest7 = testListener.startChild("Remove the Cart Qty");
            childTest7.setDescription("This test verifies that user is able to Remove the Cart Qty.");
            TestListner.extentMap.get().put("childTest7", childTest7);
            //cart.CartVerification(childTest7,0);
            Boolean IsPDPQTYdeleted= cart.CartVerification(childTest7,1);

            listingPage.NavigateToL2CategoryList(childTest7);

            listingPage.ClickPlpItems(childTest7);

            ExtentTest childTest8 = testListener.startChild("Proceed to Checkout");
            childTest8.setDescription("This test verifies that user is able to Proceed to Checkout.");
            TestListner.extentMap.get().put("childTest8", childTest8);
            Boolean IsProceedForCheckout= cart.Proceedforcheckout(childTest8);

            ExtentTest childTest9 = testListener.startChild("Proceed to Shipping and payment page");
            childTest9.setDescription("This test verifies that user is able to Proceed to Shipping and payment page.");
            TestListner.extentMap.get().put("childTest9", childTest9);
            Boolean IsProceedForshippingPage= checkout.checkoutPage(testdata);

            ExtentTest childTest10 = testListener.startChild("Order Confirmation Page");
            childTest10.setDescription("This test verifies that user is able to Proceed to Order Confirmation Page. ");
            TestListner.extentMap.get().put("childTest10", childTest10);
            Boolean OrderConfrimation= ordercon.OrderConfirmNumber();
            // ordercon.StoreDynamicDataOrderId();
            // Cart cart = new Cart(browser);
            // Checkout checkout = new Checkout(browser);
//            ExtentTest childTest2 = testListener.startChild("Existing User Login test");
//            childTest2.setDescription("This test verifies that user is able to login with existing credentials.");
//            TestListner.extentMap.get().put("child", childTest2);
            //  myaccount.UserRegistration(testdata1);
            // Boolean isSignIn = myaccount.UserSignIn(Login);

            //Boolean isProductAdded = listingPage.AddToBagCategoryProduct();
            //Boolean isProceedforCheckout = cart.Proceedforcheckout();
            //Boolean ischeckoutDone = checkout.checkout();


        } catch (Exception e) {
            System.out.println(e);
        } finally {
            browser.close();

        }

    }

    @Test(groups = {"production", "APIDemo"}, dataProvider = "Data Provide for Independent API")
    public void Indepenet_API(APIData apiData) {
        Framework framework = new Framework();
        String parent = apiData.getName();
        ExtentTest parentTest1 = testListener.startParent(apiData.getName() + " API Test on " + baseUrl);
        parentTest1.setDescription("This test verifies that user is able to verify " + apiData.getName());
        TestRunner.parentExtentMap.put("parentTest1", parentTest1);
        String apiDetaildataId = apiData.getTestcaseid();
        List<APIDetailData> apiDetailDataList = framework.getDataList(APIDetailData.class, apiDetaildataId);
        for (APIDetailData apiDetailData : apiDetailDataList) {
            String envJsonFilePath = pathPreProd + "\\" + apiData.getName() + "_" + apiData.getTestcaseid() + "\\" + apiData.getName() + "Response" + apiDetailData.getTestDataId();
            ExtentTest childTest1 = testListener.startChild(apiDetailData.getTestDataId() + " : " + apiData.getName() + " API test with " + apiDetailData.getScenario());
            childTest1.setDescription("This test verifies that user is able to verify " + apiData.getName() + apiDetailData.getScenario());
            TestListner.extentMap.get().put(apiDetailData.getTestDataId() + apiData.getName(), childTest1);
            try {
                String basePath = apiDetailData.getPath();
                if (System.getProperty("Language") == null) {
                    basePath = basePath.replace("$lang", Lang);
                } else {
                    basePath = basePath.replace("$lang", System.getProperty("Language"));
                }
                if (System.getProperty("Country") == null) {
                    if (Country.equalsIgnoreCase("kw")) {
                        basePath = basePath.replace("$country_", "");
                    } else {
                        basePath = basePath.replace("$country", Country);
                    }
                } else {
                    if (System.getProperty("Country").equalsIgnoreCase("kw")) {
                        basePath = basePath.replace("$country_", "");
                    } else {
                        basePath = basePath.replace("$country", System.getProperty("Country"));
                    }
                }
                apiDetailData.setPath(basePath);
                //CustomSoftAssert CS = new CustomSoftAssert(APIAssertData);
                childTest1.log(LogStatus.INFO, "BaseURL: " + baseUrl);
                childTest1.log(LogStatus.INFO, "BasePath: " + apiDetailData.getPath());
                childTest1.log(LogStatus.INFO, "API Parameters: " + apiDetailData.getParameter());
                Method method = new Method();
                String MethodName = apiDetailData.getMethod();
                String Header = apiDetailData.getHeader();
                String Setfield = apiData.getSetThisField();
                Response respEnv;
                String Pareameter = apiDetailData.getParameter();


                String parameter = apiDetailData.getParameter();
                if (System.getProperty("Language") == null) {
                    parameter = parameter.replace("$lang", Lang);
                } else {
                    parameter = parameter.replace("$lang", System.getProperty("Language"));
                }
                if (System.getProperty("Country") == null) {
                    if (Country.equalsIgnoreCase("kw")) {
                        parameter = parameter.replace("$country_", "");
                    } else {
                        parameter = parameter.replace("$country", Country);
                    }
                } else {
                    if (System.getProperty("Country").equalsIgnoreCase("kw")) {
                        parameter = parameter.replace("$country_", "");
                    } else {
                        parameter = parameter.replace("$country", System.getProperty("Country"));
                    }
                }
                apiDetailData.setParameter(parameter);
                if (Token == "") {
                   // Token = method.GetToken(environmentData, framework);
                    Token = Token.replaceAll("\"", "");
                    //Token = URLEncoder.encode(Token,"UTF-8");
                }
                if (keyValuePairMap.containsKey(Setfield)) {
                    String value = keyValuePairMap.get(Setfield);
                    if (Pareameter.contains(Setfield)) {
                        Pareameter = Pareameter.replace(Setfield, value);
                        System.out.println(Pareameter);
                        apiDetailData.setParameter(Pareameter);
                    }

                }
					/*if (apiData.getStatus().contains("TRUE")) {
						if (Token == "") {
							String result = method.GetToken(environmentData, frameWork);
							String[] temp = result.split("abcd123",2);
							Token = temp[0];
							customerid =temp[1];
							Token = URLEncoder.encode(Token,"UTF-8");
							//Token = "pp4m4+5-e=uzku$$15397579461307792398";
						}
						Pareameter = apiDetailData.getParameter().replace("TokenValue", Token);
					}
					if(Pareameter.contains("customer_id")){
						Pareameter=Pareameter.replace("customerid",customerid);
					}
                    if(Pareameter.contains("customer_address_id")){
						Pareameter=Pareameter.replace("customerAddId",customerAddId);
					}
					System.out.println(apiData.getName());*/
                if (MethodName.contains("GET")) {
                    respEnv = method.GetMethod(apiDetailData, environmentData, basePath, Token);
                } else if (MethodName.contains("PUT")) {
                    respEnv = method.PutMethod(apiDetailData, environmentData, basePath, Token);
                } else {
                    respEnv = method.NewPostmethod(apiDetailData, environmentData, basePath, Token);
                }
                if (respEnv.getStatusCode() == 200) {
                    childTest1.log(LogStatus.PASS, "Status Code is " + respEnv.getStatusCode());
                    if (respEnv.getHeader("X-Magento-Cache-Debug") != null) {
                        String CacheResult = respEnv.getHeader("X-Magento-Cache-Debug");
                        childTest1.log(LogStatus.INFO, "Cache Result " + CacheResult);
                    }
                    if ((apiData.getUseThisField().contains(""))) {
                        if (Token == "") {
                            String temp = apiData.getUseThisField();
                            String Test = apiData.getGetThisField();
                            String[] test1 = Test.split(",", 5);
                            String[] temp1 = temp.split(",", 5);
                            for (int i = 0; i < temp1.length; i++) {
                                Token = method.GetField(respEnv, temp1[i]);
                                String key = test1[i];
                                keyValuePairMap.put(key, Token);
                            }
                            System.out.println(Arrays.asList(keyValuePairMap));
                        }

                    }

                    //childTest1.log(LogStatus.FAIL, "Exception or Assert Error " + respEnv.asString());
                    // System.out.println("you are in Prerequisite Zone");
                    // Assert.fail("Failed Testcase Please check log");
                } else if (respEnv.getStatusCode() == 500) {
                    childTest1.log(LogStatus.FAIL, "status code is " + respEnv.getStatusCode());
                    System.out.println("!! server error !!");
                } else if (respEnv.getStatusCode() == 501) {
                    childTest1.log(LogStatus.FAIL, "status code is " + respEnv.getStatusCode());
                    System.out.println("!! Not Implemented !!");
                } else if (respEnv.getStatusCode() == 502) {
                    childTest1.log(LogStatus.FAIL, "status code is " + respEnv.getStatusCode());
                    System.out.println("!!  Bad GateWay  !!");
                } else if (respEnv.getStatusCode() == 503) {
                    childTest1.log(LogStatus.FAIL, "status code is " + respEnv.getStatusCode());
                    System.out.println("!!  Service Unavailable  !!");
                } else if (respEnv.getStatusCode() == 504) {
                    childTest1.log(LogStatus.FAIL, "status code is " + respEnv.getStatusCode());
                    System.out.println("!!  Gateway Timeout !!");
                } else if (respEnv.getStatusCode() == 400) {
                    childTest1.log(LogStatus.FAIL, "status code is " + respEnv.getStatusCode());
                    System.out.println("!!  Bad Request !!");
                } else if (respEnv.getStatusCode() == 401) {
                    childTest1.log(LogStatus.FAIL, "status code is " + respEnv.getStatusCode());
                    System.out.println("!!  user is Unauthorized !!");
                } else if (respEnv.getStatusCode() == 403) {
                    childTest1.log(LogStatus.FAIL, "status code is " + respEnv.getStatusCode());
                    System.out.println("!!  Forbidden !!");
                } else if (respEnv.getStatusCode() == 404) {
                    childTest1.log(LogStatus.FAIL, "status code is " + respEnv.getStatusCode());
                    System.out.println("!!  Page Not Foound !!");
                }
                String responseStringEnv = respEnv.asString();
					/*if(customerAddId==""&&responseStringEnv.contains("customer_address_id")&&apiData.getTestcaseid().contains("TC30")){
						if (!responseStringEnv.startsWith("{")) {
							responseStringEnv = "{" + responseStringEnv;
						}
						JSONObject test = new JSONObject(responseStringEnv);
						String tempresponse= test.getString("response");
						System.out.println(tempresponse);
						//--------------Customer address id exist in address details of Login api (TC1) response----
						if(tempresponse.contains("address_details"))
						{
							JSONObject test1 = new JSONObject(tempresponse);
							tempresponse= test1.getString("address_details");
						}
						JsonObject tempresponseNew = new JsonParser().parse(tempresponse).getAsJsonObject();
						customerAddId = tempresponseNew.get("customer_address_id").toString();
					}*/

                System.out.println("Request Sent:" + baseUrl + "/" + apiDetailData.getPath() + "?" + Pareameter);
                childTest1.log(LogStatus.INFO, apiData.getMethod());
                childTest1.log(LogStatus.INFO, "Request Sent: " + baseUrl + "/" + apiDetailData.getPath() + "?" + Pareameter);
                childTest1.log(LogStatus.INFO, "Response Received: " + respEnv.asString());
                childTest1.log(LogStatus.INFO, "Response Time :" + respEnv.getTime());
                String gender = System.getProperty("Gender");
                if (gender != null) {
                    if ((apiDetailData.getExpected().contains("null"))) {
                        if ((respEnv.asString().contains(apiDetailData.getExpected()) || (apiDetailData.getExpected().equalsIgnoreCase("null")))) {
                            childTest1.log(LogStatus.PASS, "Expected Condition " + apiDetailData.getExpected() + "Match with Current Response");
                        } else {
                            childTest1.log(LogStatus.FAIL, "Excepted Condition " + apiDetailData.getExpected() + "Not Match with Current Response");
                        }
                    }
                } else {
                    if (apiDetailData.getExpected().contains("")) {
                        if (respEnv.asString().contains(apiDetailData.getExpected())) {
                            childTest1.log(LogStatus.PASS, "Expected Condition " + apiDetailData.getExpected() + "Match with Current Response");
                        } else {
                            childTest1.log(LogStatus.FAIL, "Excepted Condition " + apiDetailData.getExpected() + "Not Match with Current Response");
                        }
                    }
                }

                //CS.assertTrue(respEnv.asString().contains(apiDetailData.getExpected()), apiDetailData.getExpected());
                File PreprodJsonFile = new File(envJsonFilePath);
                FileUtils.writeStringToFile(PreprodJsonFile, responseStringEnv);

            } catch (Exception e) {
                childTest1.log(LogStatus.FAIL, "Exception or Assert Error " + e.getMessage());
                //Assert.fail("Failed Testcase Please check log");
            }
            System.out.println(apiDetailData.getTestDataId() + " Child Test Finished");
        }
        TestListner testListner = new TestListner();
        testListner.childParentLink(parent);
        Framework.extentReports.endTest(parentTest1);
        Framework.extentReports.flush();
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: Verify login page title test on Login Page")
    @Story("Story Name: To check login page title")
    @Test(groups = {"production", "BrokenLink"})
    public void brokenLinks() throws MalformedURLException,IOException {
        //   WebDriverManager driver = new ChromeDriverManager();
        WebDriver browser = framework.getWebBrowser("demosite");

        browser.get(baseUrl);
        //WebDriverManager.chromedriver().setup();

        //   driver.manage().window().maximize();
        //WebDriver driver = new ChromeDriver();
        //driver.get("https://ctdemob2c.anthive.tech/");

        List<WebElement> links = browser.findElements(By.tagName("a"));
        links.addAll(browser.findElements(By.tagName("img")));
        List<WebElement> activelinks = new ArrayList<WebElement>();

        System.out.println("Total links are " + links.size());

        for (int i = 0; i < links.size(); i++) {
            System.out.println(links.get(i).getAttribute("href"));

            if (links.get(i).getAttribute("href") != null && (! links.get(i).getAttribute("href").contains(" "))) {
                activelinks.add(links.get(i));
            }
//                WebElement ele= links.get(i);
//
//                String url=ele.getAttribute("href");

            //  verifyLinkActive(url);

        }
        System.out.println("size of active links and images----->>" + activelinks.size());

        for (int j = 0; j < activelinks.size(); j++) {
            HttpURLConnection connection = (HttpURLConnection) new URL(activelinks.get(j).getAttribute("href")).openConnection();
            connection.connect();
            String response = connection.getResponseMessage();
            connection.disconnect();
            System.out.println(activelinks.get(j).getAttribute("href") + "----->>" + response);
        }

    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: Verify login page title test on Login Page")
    @Story("Story Name: To check login page title")
    @Test(groups = {"production", "BrokenLink1"})
    public void brokenLinks1() throws Throwable {
        //   WebDriverManager driver = new ChromeDriverManager();
        WebDriver browser = framework.getWebBrowser("demosite");

        browser.get(baseUrl);
        List <WebElement> allLinks = browser.findElements(By.tagName("a"));
        System.out.println("# Links: " + allLinks.size());

try {
    int i = 1;

    for (WebElement link : allLinks) {
        String url = link.getAttribute("href");
       // System.out.println("# Links: " + url);

        if (url != null && !url.contains("javascript")) {
            // Establish A Connection To The URL
            HttpURLConnection connection =
                    (HttpURLConnection) new URL(url).openConnection();
            connection.connect();

            // Get The Response Codes & Response Messages
            int responseCode = connection.getResponseCode();
            String responseMessage = connection.getResponseMessage();

            System.out.println(i + ". " + url +
                    "\n \t" + responseCode + "\n \t" + responseMessage);
            i++;
            connection.disconnect();
        }
    }
} catch (MalformedURLException  e) {
    e.printStackTrace();
}
    }


    @Test(groups = {"production", "APIDemo"}, priority = 1)
    public void Dependent_APIData() {
        Framework frameWork = new Framework();
        List<APIData> apiData1 = frameWork.getdependentAPIdata(APIData.class, "TRUE");
        for (APIData apiData : apiData1) {
            String parent = apiData.getName();
            ExtentTest parentTest1 = testListener.startParent(apiData.getName() + " API Test " + " Preprod");
            parentTest1.setDescription("This test verifies that user is able to verify " + apiData.getName());
            TestRunner.parentExtentMap.put("parentTest1", parentTest1);

            String apiDetaildataId = apiData.getTestcaseid();
            List<APIDetailData> apiDetailDataList = frameWork.getDataList(APIDetailData.class, apiDetaildataId);
            for (APIDetailData apiDetailData : apiDetailDataList) {

                String envJsonFilePath = pathPreProd + "\\" + apiData.getName() + "_" + apiData.getTestcaseid() + "\\" + apiData.getName() + "Response" + apiDetailData.getTestDataId();
                String pathMasterFilePath = pathMaster + "\\" + apiData.getName() + "_" + apiData.getTestcaseid() + "\\" + apiData.getName() + "Response" + apiDetailData.getTestDataId();
                ExtentTest childTest1 = testListener.startChild(apiDetailData.getTestDataId() + " : " + apiData.getName() + " API test with " + apiDetailData.getScenario());
                childTest1.setDescription("This test verifies that user is able to verify " + apiData.getName() + apiDetailData.getScenario());
                TestListner.extentMap.get().put(apiDetailData.getTestDataId() + apiData.getName(), childTest1);
                try {
                    String basePath = apiDetailData.getPath();
                    if (System.getProperty("Language") == null) {
                        basePath = basePath.replace("$lang", Lang);
                    } else {
                        basePath = basePath.replace("$lang", System.getProperty("Language"));
                    }
                    if (System.getProperty("Country") == null) {
                        if (Country.equalsIgnoreCase("kw")) {
                            basePath = basePath.replace("$country_", "");
                        } else {
                            basePath = basePath.replace("$country", Country);
                        }
                    } else {
                        if (System.getProperty("Country").equalsIgnoreCase("kw")) {
                            basePath = basePath.replace("$country_", "");
                        } else {
                            basePath = basePath.replace("$country", System.getProperty("Country"));
                        }
                    }
                    String parameter = apiDetailData.getParameter();
                    if (System.getProperty("Language") == null) {
                        parameter = parameter.replace("$lang", Lang);
                    } else {
                        parameter = parameter.replace("$lang", System.getProperty("Language"));
                    }
                    if (System.getProperty("Country") == null) {
                        if (Country.equalsIgnoreCase("kw")) {
                            parameter = parameter.replace("$country_", "");
                        } else {
                            parameter = parameter.replace("$country", Country);
                        }
                    } else {
                        if (System.getProperty("Country").equalsIgnoreCase("kw")) {
                            parameter = parameter.replace("$country_", "");
                        } else {
                            parameter = parameter.replace("$country", System.getProperty("Country"));
                        }
                    }
                    // CustomSoftAssert CS = new CustomSoftAssert(APIAssertData);
                    Method method = new Method();
                    String Header = apiDetailData.getHeader();
                    String MethodName = apiDetailData.getMethod();
                    String Setfield = apiData.getSetThisField();
                    Response respEnv;
                    String Pareameter = apiDetailData.getParameter();
                    if (!(Setfield == "null" || Setfield.equalsIgnoreCase(""))) {
                        String[] setfield = Setfield.split(",");
                        for (int i = 0; i < setfield.length; i++) {
                            String value = keyValuePairMap.get(setfield[i]);
                            if (basePath.contains(setfield[i])) {
                                basePath = basePath.replace(setfield[i], value);
                                apiDetailData.setPath(basePath);
                                System.out.println(apiDetailData.getPath());
                            } else if (Pareameter.contains(setfield[i])) {
                                Pareameter = Pareameter.replace(setfield[i], value);
                                System.out.println(Pareameter);
                                apiDetailData.setParameter(Pareameter);
                            }
                        }
                    }
                    if (keyValuePairMap.containsKey(Setfield)) {
                        String value = keyValuePairMap.get(Setfield);
                        if (basePath.contains(Setfield)) {
                            basePath = basePath.replace(Setfield, value);
                            apiDetailData.setPath(basePath);
                            System.out.println(apiDetailData.getPath());
                        } else if (Pareameter.contains(Setfield)) {
                            Pareameter = Pareameter.replace(Setfield, value);
                            System.out.println(Pareameter);
                        }

                    }
                    if (Token == "") {
                       // Token = method.GetToken(environmentData, frameWork);
                        Token = Token.replaceAll("\"", "");
                        //Token = URLEncoder.encode(Token,"UTF-8");
                    }
					/*if (apiData.getStatus().contains("TRUE")) {
						if (Token == "") {
							String result = method.GetToken(environmentData, frameWork);
							String[] temp = result.split("abcd123",2);
							Token = temp[0];
							customerid =temp[1];
							Token = URLEncoder.encode(Token,"UTF-8");
							//Token = "pp4m4+5-e=uzku$$15397579461307792398";
						}
						Pareameter = apiDetailData.getParameter().replace("TokenValue", Token);
					}
					if(Pareameter.contains("customer_id")){
						Pareameter=Pareameter.replace("customerid",customerid);
					}
                    if(Pareameter.contains("customer_address_id")){
						Pareameter=Pareameter.replace("customerAddId",customerAddId);
					}
					System.out.println(apiData.getName());*/
                    if (MethodName.contains("GET")) {
                        respEnv = method.GetMethod(apiDetailData, environmentData, basePath, Token);
                    } else {
                        respEnv = method.NewPostmethod(apiDetailData, environmentData, basePath, Token);
                    }

                    String responseStringEnv = respEnv.asString();
                    if (respEnv.getStatusCode() == 200) {
                        childTest1.log(LogStatus.PASS, "Status Code is " + respEnv.getStatusCode());
                        if ((!apiData.getUseThisField().isEmpty()) && (!(apiData.getUseThisField().contains("null"))) && (keyValuePairMap.get(apiData.getGetThisField())) == null) {
                            String temp = apiData.getUseThisField();
                            String Test = apiData.getGetThisField();
                            String[] test1 = Test.split(",", 5);
                            String[] temp1 = temp.split(",", 5);
                            for (int i = 0; i < temp1.length; i++) {
                                String value = method.GetField(respEnv, temp1[i]);
                                String key = test1[i];
                                keyValuePairMap.put(key, value);
                            }
                            System.out.println(Arrays.asList(keyValuePairMap));
                        }
                       /* if ((!apiData.getUseThisField().isEmpty())&& (!(apiData.getUseThisField().contains("null")))) {
                            if ((customerid == null || customerid == "") ) {
                                String temp = apiData.getUseThisField();
                                String Test = apiData.getGetThisField();
                                String[] test1 = Test.split(",", 5);
                                String[] temp1 = temp.split(",", 5);
                                for (int i = 0; i < temp1.length; i++) {
                                    String Value = method.GetField(respEnv, temp1[i]);
                                    String key = test1[i];
                                    keyValuePairMap.put(key, Value);
                                }
                                System.out.println(Arrays.asList(keyValuePairMap));
                            }

                        }*/
					/*if(customerAddId==""&&responseStringEnv.contains("customer_address_id")&&apiData.getTestcaseid().contains("TC30")){
						if (!responseStringEnv.startsWith("{")) {
							responseStringEnv = "{" + responseStringEnv;
						}
						JSONObject test = new JSONObject(responseStringEnv);
						String tempresponse= test.getString("response");
						System.out.println(tempresponse);
						//--------------Customer address id exist in address details of Login api (TC1) response----
						if(tempresponse.contains("address_details"))
						{
							JSONObject test1 = new JSONObject(tempresponse);
							tempresponse= test1.getString("address_details");
						}
						JsonObject tempresponseNew = new JsonParser().parse(tempresponse).getAsJsonObject();
						customerAddId = tempresponseNew.get("customer_address_id").toString();
					}*/


                        //childTest1.log(LogStatus.FAIL, "Exception or Assert Error " + respEnv.asString());
                        // System.out.println("you are in Prerequisite Zone");
                        // Assert.fail("Failed Testcase Please check log");
                    } else if (respEnv.getStatusCode() == 500) {
                        childTest1.log(LogStatus.FAIL, "status code is " + respEnv.getStatusCode());
                        System.out.println("!! server error !!");
                    } else if (respEnv.getStatusCode() == 501) {
                        childTest1.log(LogStatus.FAIL, "status code is " + respEnv.getStatusCode());
                        System.out.println("!! Not Implemented !!");
                    } else if (respEnv.getStatusCode() == 502) {
                        childTest1.log(LogStatus.FAIL, "status code is " + respEnv.getStatusCode());
                        System.out.println("!!  Bad GateWay  !!");
                    } else if (respEnv.getStatusCode() == 503) {
                        childTest1.log(LogStatus.FAIL, "status code is " + respEnv.getStatusCode());
                        System.out.println("!!  Service Unavailable  !!");
                    } else if (respEnv.getStatusCode() == 504) {
                        childTest1.log(LogStatus.FAIL, "status code is " + respEnv.getStatusCode());
                        System.out.println("!!  Gateway Timeout !!");
                    } else if (respEnv.getStatusCode() == 400) {
                        childTest1.log(LogStatus.FAIL, "status code is " + respEnv.getStatusCode());
                        System.out.println("!!  Bad Request !!");
                    } else if (respEnv.getStatusCode() == 401) {
                        childTest1.log(LogStatus.FAIL, "status code is " + respEnv.getStatusCode());
                        System.out.println("!!  user is Unauthorized !!");
                    } else if (respEnv.getStatusCode() == 403) {
                        childTest1.log(LogStatus.FAIL, "status code is " + respEnv.getStatusCode());
                        System.out.println("!!  Forbidden !!");
                    } else if (respEnv.getStatusCode() == 404) {
                        childTest1.log(LogStatus.FAIL, "status code is " + respEnv.getStatusCode());
                        System.out.println("!!  Page Not Found !!");
                    }

                    childTest1.log(LogStatus.INFO, "BaseURL: " + baseUrl);
                    childTest1.log(LogStatus.INFO, "BasePath: " + apiDetailData.getPath());
                    childTest1.log(LogStatus.INFO, "API Parameters: " + apiDetailData.getParameter());
                    System.out.println("Request Sent:" + baseUrl + "/" + apiDetailData.getPath() + "?" + Pareameter);
                    childTest1.log(LogStatus.INFO, apiData.getMethod());
                    childTest1.log(LogStatus.INFO, "Request Sent: " + baseUrl + "/" + apiDetailData.getPath() + "?" + Pareameter);
                    childTest1.log(LogStatus.INFO, "Response Received: " + respEnv.asString());
                    childTest1.log(LogStatus.INFO, "Response Time :" + respEnv.getTime());
                    String gender = System.getProperty("Gender");
                    if (gender != null) {
                        if ((apiDetailData.getExpected().contains("null"))) {
                            if ((respEnv.asString().contains(apiDetailData.getExpected()) || (apiDetailData.getExpected().equalsIgnoreCase("null")))) {
                                childTest1.log(LogStatus.PASS, "Expected Condition " + apiDetailData.getExpected() + "Match with Current Response");
                            } else {
                                childTest1.log(LogStatus.FAIL, "Excepted Condition " + apiDetailData.getExpected() + "Not Match with Current Response");
                            }
                        }
                    } else {
                        if (apiDetailData.getExpected().contains("")) {
                            if (respEnv.asString().contains(apiDetailData.getExpected())) {
                                childTest1.log(LogStatus.PASS, "Expected Condition " + apiDetailData.getExpected() + "Match with Current Response");
                            } else {
                                childTest1.log(LogStatus.FAIL, "Excepted Condition " + apiDetailData.getExpected() + "Not Match with Current Response");
                            }
                        }
                    }

                    //CS.assertTrue(respEnv.asString().contains(apiDetailData.getExpected()), apiDetailData.getExpected());
                    File PreprodJsonFile = new File(envJsonFilePath);
                    FileUtils.writeStringToFile(PreprodJsonFile, responseStringEnv);

                } catch (Exception e) {
                    childTest1.log(LogStatus.FAIL, "Exception or Assert Error " + e.getMessage());
                    Assert.fail("Failed Testcase Please check log");
                }
                System.out.println(apiDetailData.getTestDataId() + " Child Test Finished");
            }
            TestListner testListner = new TestListner();
            testListner.childParentLink(parent);
            Framework.extentReports.endTest(parentTest1);
        }
        Framework.extentReports.flush();
    }
}
