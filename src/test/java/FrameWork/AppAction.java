package FrameWork;

import Data.AppObjectRepo;
import Data.ObjectRepo;
import FrameWork.*;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;


import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AppAction {
    public boolean runStatus;
    public String errorMessage;
    public String ResultMessage;
    public String exceptionBuffer = "";
    public String errorBuffer = "";
    public String failureSeverity;
SoftAssert Assert = new SoftAssert();
    protected  AppiumDriver<MobileElement> driver;

    static int DefaultTime = 30;
    protected boolean click(Locator locator){
        WebDriverWait webdriverWait = new WebDriverWait(driver, DefaultTime);
        if (locator.getElement() == null) {
            webdriverWait.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
            driver.findElement(locator.getBy()).click();
        } else {
            System.out.println(locator.getBy());
            System.out.println(locator.getElement());
            WebElement element = locator.getElement().findElement(locator.getBy());
            webdriverWait.until(ExpectedConditions.elementToBeClickable(element));
            driver.manage().timeouts().implicitlyWait(DefaultTime, TimeUnit.SECONDS);
            element.click();
        }
        TestListner.testing.get().log(LogStatus.INFO, "Clicked On " + locator.getName());
        return  true;
    }



    protected boolean waitUntilDisplayed(Locator locator, int Timeout) {
        WebDriverWait webdriverWait = new WebDriverWait(driver, Timeout);
        driver.manage().timeouts().implicitlyWait(Timeout,TimeUnit.SECONDS);
        try {
            if (locator.getElement() == null) {
                webdriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator.getBy()));
            } else {
                System.out.println(locator.getBy());
                System.out.println(locator.getElement());
                WebElement element = locator.getElement().findElement(locator.getBy());
                webdriverWait.until(ExpectedConditions.elementToBeClickable(element));
            }
            return true;

        }catch(Exception e){
            return false;
        }

        finally {
            driver.manage().timeouts().implicitlyWait(DefaultTime, TimeUnit.SECONDS);
        }
    }
    //   WebElement TVprod = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='BUY NOW']"));
//                Thread.sleep(15000);
//                Actions TVaction = new Actions(driver);
//                TVaction.moveToElement(TVprod).build().perform();
    protected void Action(Locator locator){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(locator.getBy())).build().perform();

    }

    protected void Javascript(Locator locator){

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", driver.findElement(locator.getBy()));
    }


//    protected void EnterValue(Locator locator, String value) {
//
//        WebDriverWait webdriverWait = new WebDriverWait(driver, 60);
//
//        webdriverWait.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
//        if(locator.getElement()==null) {
//            driver.findElement(locator.getBy()).sendKeys(value);
//        }else{
//            locator.getElement().findElement(locator.getBy()).sendKeys(value);
//        }
//        Reporter.log("Entered value  '" + value + "' in '" + locator.getName() + "'",true);
//        TestListner.testing.get().log(LogStatus.INFO,"Entered value  '" + value + "' in '" + locator.getName() + "'");
//    }


    protected void EnterValue(Locator locator, String value) {
        WebDriverWait webdriverWait = new WebDriverWait(driver, 60);
        //webdriverWait.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
        driver.findElement(locator.getBy()).sendKeys(value);
        Reporter.log("Entered value  '" + value + "' in '" + locator.getName() + "'", true);
        TestListner.testing.get().log(LogStatus.INFO, "Entered value  '" + value + "' in '" + locator.getName() + "'");
    }

    protected void EnterValueAndClick(Locator locator,String value) {
        WebDriverWait webdriverWait = new WebDriverWait(driver, 60);

        webdriverWait.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
        if(locator.getElement()==null) {
            driver.findElement(locator.getBy()).sendKeys(value);
        }else{
            locator.getElement().findElement(locator.getBy()).sendKeys(value);
        }
        driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
        Reporter.log("Entered value  '" + value + "' in '" + locator.getName() + "'",true);
        TestListner.testing.get().log(LogStatus.INFO,"Entered value  '" + value + "' in '" + locator.getName() + "'");
    }
    protected void ClearValue(Locator locator) {

        WebDriverWait webdriverWait = new WebDriverWait(driver, 60);

        webdriverWait.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
        if(locator.getElement()==null) {
            driver.findElement(locator.getBy()).clear();
            // driver.findElement(locator.getBy()).sendKeys(Keys.DELETE);
        }else{
            locator.getElement().findElement(locator.getBy()).clear();
        }
    }

    protected void DeleteText(Locator locator) {

        WebDriverWait webdriverWait = new WebDriverWait(driver, 60);

        webdriverWait.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
        if(locator.getElement()==null) {

            driver.findElement(locator.getBy()).sendKeys(Keys.DELETE);

        }else{
            driver.findElement(locator.getBy()).sendKeys(Keys.DELETE);
        }
    }

    protected String getText(Locator locator){
        String Text = "No data";
        try {
            WebDriverWait wait = new WebDriverWait(driver, DefaultTime);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator.getBy()));
            if(locator.getElement()==null) {
                WebElement webElement = driver.findElement(locator.getBy());
                Text = webElement.getText();
                System.out.println("*** Data is ** == >> " + Text);
            }else{
                WebElement webElement = locator.getElement().findElement(locator.getBy());
                Text = webElement.getText();
                System.out.println("*** Data is ** == >> " + Text);
            }
            return Text;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Text;
    }
    public boolean VerifyTextPresent(ExtentTest extentTest,Locator locator, String text) {
        System.out.println("String Passed: "+text);
        String strText = text.toLowerCase();
        String presentText;
        try {
            // System.out.println(driver.findElement(By.xpath(xpathKey)).getText().toLowerCase());
            if (locator != null) {
                 presentText = driver.findElement(locator.getBy()).getText().toLowerCase();
                System.out.println("String from locator: "+presentText);
              //  Assert.assertTrue(presentText.contains(strText));
            } else {
                 presentText = driver.findElement(By.xpath("//body")).getText().toLowerCase();
               // Assert.assertTrue(presentText.contains(strText));
            }
            BooleanAsseration(presentText.contains(strText),extentTest, "Text is present", "Text is not Present");
        } catch (Exception e) { e.printStackTrace();
        } catch (AssertionError e) {
            e.printStackTrace();
        }return true;
    }

    protected List<String> getMultipleText(Locator locator){
        List<String> Text= new ArrayList<String>();
        try {
            WebDriverWait wait = new WebDriverWait(driver, DefaultTime);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator.getBy()));
            if(locator.getElement()==null) {
                List<MobileElement> webElement = driver.findElements(locator.getBy());
                for (MobileElement element:webElement) {
                   System.out.println("Item list === >> " + Text.add(element.getText()));
                }

            }else{
              //  List<MobileElement> webElement = locator.getElement().findElements(locator.getBy());
//                List<MobileElement> webElement = locator.getElement().findElements(locator.getBy());
//                for (MobileElement element: webElement) {
//                    Text.add(element.getText());
//                }
            }
            return Text;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Text;
    }


    public boolean getElementList(Locator locator) throws Exception {
        List<MobileElement> SizeDropDownOptions = driver.findElements(locator.getBy());
        System.out.println(SizeDropDownOptions.size());
        for (MobileElement mElement : SizeDropDownOptions) {
            System.out.println("**** Item List **** " + mElement.getText());

                    }return true;
    }

    //List<MobileElement> allCheckBoxes=driver.findElementsByXPath("//*[@class='android.widget.CheckBox']");
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


    protected boolean isDisplayed(Locator locator){

        WebDriverWait webdriverWait = new WebDriverWait(driver, 1);
        driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
        try {
            if (locator.getElement() == null) {
                webdriverWait.until(ExpectedConditions.presenceOfElementLocated(locator.getBy()));
                driver.findElement(locator.getBy()).isDisplayed();
            } else {
                System.out.println(locator.getBy());
                System.out.println(locator.getElement());
                WebElement element = locator.getElement().findElement(locator.getBy());
                webdriverWait.until(ExpectedConditions.elementToBeClickable(element));
            }
            return true;

        }catch(Exception e){
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(DefaultTime, TimeUnit.SECONDS);
        }
    }



    public void bringElementIntoViewUp(Locator locator, int ScrollCount){
        Dimension dimensions = driver.manage().window().getSize();

//    System.out.println(dimensions);

        Double screenHeightStart = dimensions.getHeight() * 0.9;

        int scrollStart = screenHeightStart.intValue();

        Double screenHeightEnd = dimensions.getHeight() * 0.4;

        int scrollEnd = screenHeightEnd.intValue();
        int i = 1;
        while (!waitUntilDisplayed(locator, 3)) {
            TouchAction ta = new TouchAction(driver);
            ta.moveTo(PointOption.point(scrollStart, scrollEnd)).perform().release();
            /*driver.swipe(0, scrollEnd, 0, scrollStart, 1000);
             /i++;
            if (i >= ScrollCount) {
                if(!locator.getName().contains("XYZZZ")) {
                    //TestListner.testing.get().log(LogStatus.WARNING, "Not Able To Find " + locator.getName() + ".");
                    Reporter.log("Not Able To Find " + locator.getName() + ".", true);
                }
            }
        }

        if(i>=2) {
            TestListner.testing.get().log(LogStatus.INFO, "Scrolled " + locator.getName() + " into view.");
            Reporter.log("Scrolled " + locator.getName() + " into view.", true);
        }*/
        }
        return;
    }



    public boolean bringElementIntoViewDown(Locator locator, int ScrollCount) throws InterruptedException, IllegalAccessException, InstantiationException {
        Dimension dimensions = driver.manage().window().getSize();
        int size = dimensions.width/2;
        int scrollStartt = (int) (dimensions.getHeight() * 0.9);
        int scrollEnd = (int)(dimensions.getHeight() * 0.25);
        int i = 1;
        while (!waitUntilDisplayed(locator, 3)) {
            TouchAction ta = new TouchAction(driver);
            ta.press(PointOption.point(size, scrollStartt)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(size, scrollEnd)).release().perform();
            if (i >= ScrollCount) {
                if(!locator.getName().contains("XYZZ")) {
                    Reporter.log("Not Able To Find " + locator.getName() + ".", true);
                }
                return false;
            }
            i++;
        }
        if(i>=1) {
            TestListner.testing.get().log(LogStatus.INFO, "Scrolled " + locator.getName() + " into view.");
            Reporter.log("Scrolled " + locator.getName() + " into view.", true);

        }
        return true;
    }

    public void ScrollDown(int ScrollCount) {
        Dimension dimensions = driver.manage().window().getSize();
        int size = dimensions.width/4;
        int scrollStartt = (int) (dimensions.getHeight() * 0.9);
        int scrollEnd = (int)(dimensions.getHeight() * 0.25);
        for (int x = 1; x <= ScrollCount; x++) {
            TouchAction ta = new TouchAction(driver);
            ta.press(PointOption.point(size, scrollStartt)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(size, scrollEnd)).release().perform();
        }
    }

    public Locator GetLocator(String object_Name) {
        Framework framework = new Framework();
        AppObjectRepo locatordata = framework.getOR(AppObjectRepo.class, object_Name);
        if (locatordata.getlocator_type().equals("id") ) {
            return new Locator(By.id(locatordata.getlocator_value()), locatordata.getlocator_name_for_reporting());
        } else {
            return new Locator(By.xpath(locatordata.getlocator_value()), locatordata.getlocator_name_for_reporting());
        }
    }
    public void BooleanAsseration(Boolean condition, ExtentTest extentTest, String pass, String fail){
        if(condition){
            extentTest.log(LogStatus.PASS, pass);
        }else {
            extentTest.log(LogStatus.FAIL, fail);
        }
    }

}
