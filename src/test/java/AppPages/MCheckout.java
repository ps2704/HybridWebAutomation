package AppPages;

import Data.AccountData;
import FrameWork.AppAction;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.Assert;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;

public class MCheckout extends AppAction {
    public static BigDecimal totalcheck;
    public static BigDecimal tx1;
    public static BigDecimal item1;

    HashMap<String, String> verifymap = new HashMap<>();

    public MCheckout(AppiumDriver<MobileElement> driver) {

        this.driver = driver;
    }

    public boolean checkoutfileds(AccountData accountData) throws Exception {
        if (!waitUntilDisplayed(GetLocator("checkoutbtn"), 2)) {
            //waitforPageReady();
            // click(PaymentOption());
        }
        click(GetLocator("firstName"));
        EnterValue(GetLocator("firstName"), accountData.getFirstName());
        click(GetLocator("lastName"));
        EnterValue(GetLocator("lastName"), accountData.getLastName());
        Thread.sleep(5000);
        EnterValue(GetLocator("ShippingPostalCode"), accountData.getShippingPostalCode());
        driver.hideKeyboard();
        click(GetLocator("Contnuebtn"));


        return true;
    }

    public HashMap<String, String> checkout(ExtentTest extentTest) throws Exception {
        if (!waitUntilDisplayed(GetLocator("checkoutbtn"), 2)) {
            //waitforPageReady();
            // click(PaymentOption());
        }
        String PaymentInfo = getText(GetLocator("PaymentInfo"));
        String ShippingInfo = getText(GetLocator("ShippingInfo"));
        String Itemtotal = getText(GetLocator("Itemtotal"));
        String tax = getText(GetLocator("tax"));
        String total = getText(GetLocator("total"));
        verifymap.put("PaymentInfo", PaymentInfo);
        verifymap.put("ShippingInfo", ShippingInfo);
        verifymap.put("Itemtotal", Itemtotal);
        ScrollDown(1);
        verifymap.put("tax", tax);
        verifymap.put("total", total);

        boolean finishbtn = click(GetLocator("finishbtn"));
        BooleanAsseration(finishbtn, extentTest, "CLicked finishbtn", " Not able to clicked finishbtn");
        //click(GetLocator("cancelbtn"));

        return verifymap;
    }
    //public HashMap<String, String> VerifySubtotalsummary() throws Exception {
        public boolean VerifySubtotalsummary(ExtentTest extentTest) throws Exception {

        if (!waitUntilDisplayed(GetLocator("checkoutbtn"), 2)) {
            //waitforPageReady();
            // click(PaymentOption());
        }
        DecimalFormat df = new DecimalFormat("0.00");
        df.setMaximumFractionDigits(2);
        String PaymentInfo = getText(GetLocator("PaymentInfo"));
        String ShippingInfo = getText(GetLocator("ShippingInfo"));
        String Itemtotal = getText(GetLocator("Itemtotal"));
        String item = Itemtotal.replaceAll("[^\\d.]", "");
        BigDecimal item1 = new BigDecimal(item);
        System.out.println("Big decimal item value *** " + item1);
Thread.sleep(3000);
        String tax = getText(GetLocator("tax"));
        String tx = tax.replaceAll("[^\\d.]", "");
        BigDecimal tx1 = new BigDecimal(tx);
        System.out.println("Big decimal Tax value *** " + tx);


        String total = getText(GetLocator("total"));
        String ttl = total.replaceAll("[^\\d.]", "");
        BigDecimal ttl1 = new BigDecimal(ttl);
        System.out.println("Big decimal Subtotal value *** " + ttl1);

        // int ttl = Integer.parseInt(Itemtotal.substring(7,12));
       verifymap.put("PaymentInfo",PaymentInfo);
       verifymap.put("ShippingInfo",ShippingInfo);
        // verifymap.put("Itemtotal",Itemtotal);
       //int Itemtotalvalue = Integer.parseInt(Itemtotal);
//        verifymap.put("tax",tax);
//        //int taxvalue = Integer.parseInt(tax);
//        verifymap.put("total",total);
        if (ttl != null) {
            //int totalcheck2 = Integer.parseInt(tx + item);
             totalcheck = tx1.add(item1);
            System.out.println("Total is ===>>" + totalcheck);
            Assert.assertEquals(totalcheck, ttl1);
            extentTest.log(LogStatus.PASS, "totalcheck");
//            Boolean ordersummary
//            BooleanAsseration(ordersummary, extentTest, "User able to cancelFilteroptions", "Not able to cancelFilteroptions");

        }

        return true;
    }

}


