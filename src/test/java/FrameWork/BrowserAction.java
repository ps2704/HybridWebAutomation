package FrameWork;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BrowserAction {

    protected WebDriver driver;
    static int DefaultTime = 60;

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
}