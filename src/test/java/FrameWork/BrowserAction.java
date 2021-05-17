package FrameWork;

import Data.ObjectRepo;
import com.relevantcodes.extentreports.LogStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class BrowserAction {

    protected WebDriver driver;
    static int DefaultTime = 60;
    public boolean runStatus;
    public String errorMessage;
    public String ResultMessage;
    public String exceptionBuffer = "";
    public String errorBuffer = "";
    public String failureSeverity;
    public String parentWindow;
    public Integer inputUniqueCount = 1;
    public Integer inputDynamicCount = 1;
    public boolean softAssertIndicator;
    public boolean captureScreenshot;
    public HashMap<Integer, String> mapUniqueInput;
    public HashMap<Integer, String> mapDynamicData;
    protected void EnterValue(Locator locator, String value) {
        WebDriverWait webdriverWait = new WebDriverWait(driver, 60);
        //webdriverWait.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
        driver.findElement(locator.getBy()).sendKeys(value);
        Reporter.log("Entered value  '" + value + "' in '" + locator.getName() + "'", true);
        TestListner.testing.get().log(LogStatus.INFO, "Entered value  '" + value + "' in '" + locator.getName() + "'");
    }

    protected boolean waitUntilDisplayed(Locator locator, int Timeout) {
        driver.manage().timeouts().implicitlyWait(Timeout, TimeUnit.SECONDS);
        WebDriverWait ww = new WebDriverWait(driver, Timeout);
        try {
            //   ww.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
            return true;
        } catch (Exception e) {

            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(DefaultTime, TimeUnit.SECONDS);
        }
    }

    protected void waitforPageReady() {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver browser) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };

    }

    protected void click(Locator locator) throws InstantiationException, IllegalAccessException {


        WebDriverWait webdriverWait = new WebDriverWait(driver, DefaultTime);
        // webdriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator.getBy()));
        driver.findElement(locator.getBy()).click();
        Reporter.log("Clicked On " + locator.getName() + "", true);
        TestListner.testing.get().log(LogStatus.INFO, "Clicked On " + locator.getName());


    }

    public Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
        Map<String, Object> retConvertedMap = new HashMap<String, Object>();

        if (json != JSONObject.NULL) {
            retConvertedMap = toMap(json);
        }
        return retConvertedMap;
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    public List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }
    public void closePopUp(By byLocator) {
        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.findElement(byLocator).click();

        } catch (Exception e) {
            runStatus = true;
            errorMessage = errorMessage + "\n\n" + ("Not an Error:: Pop up not available to close - " + byLocator
                    + ", Page URL -" + driver.getCurrentUrl());
        }
    }
    protected void mouseHover(Locator locator) throws InstantiationException, IllegalAccessException {
        try {
            Actions actObj = new Actions(driver);

            WebElement element = driver.findElement(locator.getBy());
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

            if (driver.findElement(locator.getBy()).isEnabled())
                ;
            WebElement mouseHoverElement = driver.findElement(locator.getBy());
            actObj.moveToElement(mouseHoverElement).perform();

        } catch (Exception e) {
            runStatus = false;
            errorMessage = errorMessage + "\n\n" + ("Object not found to perform mouse hover, - " + locator
                    + ", Page URL -" + driver.getCurrentUrl());
            failureSeverity = "P1";
            exceptionBuffer = e.getMessage();
            e.printStackTrace();
        }

    }
    public void GetTextAndWriteToResults(Locator locator) throws InstantiationException, IllegalAccessException {
        WebDriverWait webdriverWait = new WebDriverWait(driver, DefaultTime);
        // webdriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator.getBy()));
        driver.findElement(locator.getBy()).getText();
        Reporter.log("GetText " + locator.getName() + "", true);
        TestListner.testing.get().log(LogStatus.INFO, "GetText " + locator.getName());


    }
    public void VerifyTextPresent(Locator locator, String text) {
        System.out.println("String Passed: "+text);
        String strText = text.toLowerCase();
        try {
            // System.out.println(driver.findElement(By.xpath(xpathKey)).getText().toLowerCase());
            if (locator != null) {
                String presentText = driver.findElement(locator.getBy()).getText().toLowerCase();
                System.out.println("String from locator: "+presentText);
                Assert.assertTrue(presentText.contains(strText));
            } else {
                String presentText = driver.findElement(By.xpath("//body")).getText().toLowerCase();
                Assert.assertTrue(presentText.contains(strText));
            }
        } catch (Exception e) {
            runStatus = false;
            errorMessage = errorMessage + "\n\n" + "Object not found to perform VerifyText operation, - " + locator
                    + ", Page URL -" + driver.getCurrentUrl();
            failureSeverity = "P1";
            exceptionBuffer = e.getMessage();
            e.printStackTrace();
        } catch (AssertionError e) {
            runStatus = true;
            softAssertIndicator = true;
            captureScreenshot = true;
            errorMessage = errorMessage + "\n\n" + "VerifyText failed, Text to verify - " + text + ", - " + locator
                    + ", Page URL -" + driver.getCurrentUrl();
            failureSeverity = "P2";
            errorBuffer = e.getMessage();
            e.printStackTrace();
        }
    }

    public void contextMenu(Locator locator) {
        try {
            List<WebElement> drop = driver.findElement(locator.getBy()).findElements(By.tagName("li"));

            for (WebElement opt : drop) {
                //if (opt.getText().contains(text)) {
                System.out.println( opt.findElement(By.tagName("a")).getTagName());
                break;
            }
            //}
        } catch (Exception e) {
            runStatus = false;
            errorMessage = errorMessage + "\n\n" + "Object not found to select menu item, " + locator + ", Page URL -"
                    + driver.getCurrentUrl();
            failureSeverity = "P1";
            System.out.println("Object not found for input");
            exceptionBuffer = e.getMessage();
            e.printStackTrace();
        }
    }
//    public void storeDynamicData(String inputData) {
//        mapDynamicData.put(inputDynamicCount, inputData);
//        inputDynamicCount++;
//    }

    public void StoreDynamicDataOrderId(Locator locator) throws InstantiationException, IllegalAccessException {
        WebDriverWait webdriverWait = new WebDriverWait(driver, DefaultTime);
        // webdriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator.getBy()));
        String text =driver.findElement(locator.getBy()).getText();
        System.out.println(text);
        Reporter.log("OrderId " + locator.getName() + "", true);
        TestListner.testing.get().log(LogStatus.INFO, "OrderId " + locator.getName());



    }
    public void scrollIntoView(Locator locator) throws Exception {
        WebElement element;
        try {
            element = driver.findElement(locator.getBy());
            if (element != null) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                Thread.sleep(500);
            }
        } catch (Exception e) {
            runStatus = true;
            errorMessage = errorMessage + "\n\n" + "Object not found to perform scrollIntoView operation, " + locator
                    + ", Page URL -" + driver.getCurrentUrl();
            exceptionBuffer = e.getMessage();
            e.printStackTrace();
        }
    }

    public void getElementList(Locator locator) throws Exception {
        List<WebElement> SizeDropDownOptions = driver.findElements(locator.getBy());
        System.out.println(SizeDropDownOptions.size());
        for (WebElement webElement : SizeDropDownOptions) {
            String name = webElement.getText();
            System.out.println(name);
        }}


    public void SwitchBrowserWindow(String text) {
        try {

            Set<String> handles = driver.getWindowHandles();
            Iterator<String> it = handles.iterator();
            // iterate through your windows
            while (it.hasNext()) {
                String winhndl = it.next();
                if (!winhndl.equals(parentWindow)) {
                    driver.switchTo().window(winhndl);
                }
            }

        } catch (Exception e) {
            runStatus = false;
            errorMessage = errorMessage + "\n\n" + "SwitchBrowserWindow operation failed";
            failureSeverity = "P3";
            exceptionBuffer = e.getMessage();
            e.printStackTrace();
        }
    }

    // function  navigate to new window
    public void CloseChildBrowser(String text) {
        try {

            Set<String> handles = driver.getWindowHandles();

            Iterator<String> it = handles.iterator();
            // iterate through your windows
            while (it.hasNext()) {
                String winhndl = it.next();
                if (!winhndl.equals(parentWindow)) {
                    driver.switchTo().window(winhndl);
                    driver.close();
                }

            }

            driver.switchTo().window(parentWindow);

        } catch (Exception e) {
            runStatus = false;
            errorMessage = errorMessage + "\n\n" + "CloseChildBrowser operation failed";
            failureSeverity = "P3";
            exceptionBuffer = e.getMessage();
            e.printStackTrace();
        }
    }
//    public void VerifyCartCountAfterAdd(Locator locator, int count)   {
//        try {
//
//            System.out.println("Count before add " + count);
//            //int intcount = Integer.parseInt(count);
//            int CountafterAdd = Integer.parseInt(driver.findElement(locator.getBy()).getAttribute("value"));
//            System.out.println("Count after add " + CountafterAdd );
//            Assert.assertEquals(CountafterAdd, count + 1);
//            System.out.println("Count after add " + CountafterAdd );
//
//        }
//        catch (NumberFormatException e){
//
//        };
//        //return true;
//
//    }



    public void VerifyAlert(String text) {
        try {

            if (text != "") {
                WebDriverWait wait = new WebDriverWait(driver, 2);
                wait.until(ExpectedConditions.alertIsPresent());
                Alert alert = driver.switchTo().alert();
                String presentText = alert.getText();
                alert.accept();
                Assert.assertTrue(presentText.contains(text));

            } else {
                runStatus = false;
                errorMessage = errorMessage + "\n\n" + "Object xpath or id not found in excel";
            }
        } catch (Exception e) {
            runStatus = false;
            errorMessage = errorMessage + "\n\n" + "Object not found to perform Verify Alert operation, " + text
                    + ", Page URL -" + driver.getCurrentUrl();
            failureSeverity = "P1";
            exceptionBuffer = e.getMessage();
            e.printStackTrace();
        } catch (AssertionError e) {
            runStatus = false;
            errorMessage = errorMessage + "\n\n" + "Verify Alert failed, Text to verify - " + text + ", Xpath - " + text
                    + ", Page URL -" + driver.getCurrentUrl();
            failureSeverity = "P3";
            errorBuffer = e.getMessage();
            e.printStackTrace();
        }
    }
    public void SwitchToFrameById(String text) {
        try {
            driver.switchTo().frame(text);
        } catch (Exception e) {
            runStatus = false;
            errorMessage = errorMessage + "\n\n" + "No Frame present, Page URL -" + driver.getCurrentUrl();
            failureSeverity = "P1";
            exceptionBuffer = e.getMessage();
            e.printStackTrace();
        }
    }

    public void switchToDefaultContent() {
        try {
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            runStatus = false;
            errorMessage = errorMessage + "\n\n" + "Couldn't switch to Default Frame, Page URL -"
                    + driver.getCurrentUrl();
            failureSeverity = "P1";
            exceptionBuffer = e.getMessage();
            e.printStackTrace();
        }
    }


    public void SwitchToFrameByIndex(String text) {
        try {
            driver.switchTo().frame(Integer.parseInt(text));

        } catch (Exception e) {
            runStatus = false;
            errorMessage = errorMessage + "\n\n" + "No Frame present, Page URL -" + driver.getCurrentUrl();
            failureSeverity = "P1";
            exceptionBuffer = e.getMessage();
            e.printStackTrace();
        }
    }
    public void backPage() {
        driver.navigate().back();
    }

    public void clearCookies() {
        try {
            driver.manage().deleteAllCookies();
        } catch (Exception e) {
            runStatus = false;
            errorMessage = errorMessage + "\n\n" + ("Clear cookies operation failed");
            failureSeverity = "P3";
            exceptionBuffer = e.getMessage();
            e.printStackTrace();
        }
    }
    public void VerifyPageTitle(String text) {
        try {

            String presentText = driver.getTitle();
            System.out.println("presentTitle ==>> " + presentText);

            Assert.assertTrue(presentText.contains(text));

        } catch (Exception e) {
            runStatus = false;
            errorMessage = errorMessage + "\n\n"
                    + "Object not found to perform VerifyPageTitle operation, Page Title - " + text + ", Page URL -"
                    + driver.getCurrentUrl();
            failureSeverity = "P1";
            exceptionBuffer = e.getMessage();
            e.printStackTrace();
        } catch (AssertionError e) {
            runStatus = false;
            errorMessage = errorMessage + "\n\n" + "VerifyPageTitle failed, Page Title to verify - " + text
                    + ", Page URL -" + driver.getCurrentUrl();
            failureSeverity = "P3";
            errorBuffer = e.getMessage();
            e.printStackTrace();
        }
    }

    // added by  verifying page title
    public void VerifyPageUrl(String text) {
        try {
            String presentText = driver.getCurrentUrl();
            System.out.println("presentText ==>> " + presentText);
            Assert.assertTrue(presentText.contains(text));

        } catch (Exception e) {
            runStatus = false;
            errorMessage = errorMessage + "\n\n" + "Object not found to perform VerifyPageUrl operation, Page Title - "
                    + text + ", Page URL -" + driver.getCurrentUrl();
            failureSeverity = "P1";
            exceptionBuffer = e.getMessage();
            e.printStackTrace();
        } catch (AssertionError e) {
            runStatus = false;
            errorMessage = errorMessage + "\n\n" + "VerifyPageUrl failed, Page Url to verify - " + text + ", Page URL -"
                    + driver.getCurrentUrl();
            failureSeverity = "P2";
            errorBuffer = e.getMessage();
            e.printStackTrace();
        }
    }


    public Locator GetLocator(String object_Name) {
        Framework framework = new Framework();
        ObjectRepo locatordata = framework.getOR(ObjectRepo.class, object_Name);
        if (locatordata.getlocator_type().equals("id") ) {
            return new Locator(By.id(locatordata.getlocator_value()), locatordata.getlocator_name_for_reporting());
        } else {
            return new Locator(By.xpath(locatordata.getlocator_value()), locatordata.getlocator_name_for_reporting());
        }
    }


}