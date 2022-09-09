package AppPages;

import FrameWork.AppAction;
import FrameWork.BrowserAction;
import com.relevantcodes.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Locale;

public class MListingPage extends AppAction {
    Alert alert ;
    public MListingPage(AppiumDriver<MobileElement> driver) {

        this.driver = driver;
    }

    public boolean NavigateToL2CategoryList (ExtentTest extentTest) throws Exception {
        VerifyTextPresent( extentTest,GetLocator("products"),"PRODUCTS");
        Boolean plplogodisplayed = isDisplayed(GetLocator("plplogo"));
        BooleanAsseration(plplogodisplayed, extentTest, "PLPLogo Present", "PLP Logo Not Present");

        //getMultipleText(GetLocator("plpitem"));
        Boolean Plpitem =   getElementList(GetLocator("plpitem"));
        BooleanAsseration(Plpitem, extentTest, "PLPItem Displayed", "PLP item Not Present");
       // getElementList(GetLocator("item"));

        Boolean clickoncart =   click(GetLocator("plpadd"));
        BooleanAsseration(clickoncart, extentTest, "PLPItem clicked", "PLP item Not clicked");
        Thread.sleep(2000);
         getText(GetLocator("CartIcon"));
        Thread.sleep(2000);

        Boolean productremove =  click(GetLocator("plpremove"));
        BooleanAsseration(productremove, extentTest, "PLPItem removed", "PLP item Not removed");
        getText(GetLocator("CartIcon"));
        Thread.sleep(2000);

        Boolean productName =  click(GetLocator("item"));
        BooleanAsseration(productName, extentTest, "Get product name", "Not able to get product Name");
        getText(GetLocator("pdpname"));
        //bringElementIntoViewDown(GetLocator("pdpprice"),100);
        Thread.sleep(2000);

        ScrollDown(1);
        getText(GetLocator("pdpprice"));
        Boolean addtocart = click(GetLocator("Addtocart"));
        BooleanAsseration(addtocart, extentTest, "Item added to cart", "Not able to add product in cart");
        Thread.sleep(2000);

        getText(GetLocator("CartIcon"));
        Boolean carticone = click(GetLocator("CartIcon"));
        BooleanAsseration(carticone, extentTest, "click on cart icon", "Not able to click in cart icon");
        return true;
    }

    public boolean HamburgerMenu (ExtentTest extentTest) throws Exception {
        Boolean Menuicon =  click(GetLocator("Menu"));
        BooleanAsseration(Menuicon, extentTest, "click on Hamburger Menu", "Not able to click in Hamburger Menu");
        Thread.sleep(2000);

        Boolean closeMenuicon =  click(GetLocator("CloseMenu"));
        BooleanAsseration(closeMenuicon, extentTest, "close on Hamburger Menu", "Not able to close in Hamburger Menu");
        Thread.sleep(2000);

        click(GetLocator("Menu"));
        Thread.sleep(2000);

        Boolean getelemtlist= getElementList(GetLocator("MenuItems"));
        BooleanAsseration(getelemtlist, extentTest, "Get menu list of Element", "Not able to Get menu list of Element");
        Thread.sleep(2000);

        click(GetLocator("CloseMenu"));
        return true;
    }
    public boolean HamburgerMenuVerification (ExtentTest extentTest) throws Exception {
        Boolean Menuicon =  click(GetLocator("Menu"));
        BooleanAsseration(Menuicon, extentTest, "click on Hamburger Menu", "Not able to click in Hamburger Menu");
        Thread.sleep(2000);

        Boolean Allitem = click(GetLocator("AllItem"));
        BooleanAsseration(Allitem, extentTest, "Get All Hamburger Menu", "Not able to get item in Hamburger Menu");

        Boolean Products1 =  VerifyTextPresent(extentTest,GetLocator("products"),"PRODUCTS");
        BooleanAsseration(Products1, extentTest, "Verify Products Name", "Not able to get item in Products Name");

        Thread.sleep(2000);
        click(GetLocator("Menu"));
        Boolean Webview =  click(GetLocator("Webview"));
        Thread.sleep(3000);
         //driver.switchTo().alert();
        //alert.dismiss();
        Boolean Webviewtext  = VerifyTextPresent(extentTest,GetLocator("webview"),"WEBVIEW");
        BooleanAsseration(Webviewtext, extentTest, "User able to clicked and validate web view", "Not able to User able to clicked and validate web view");

        Thread.sleep(2000);
        click(GetLocator("Menu"));
        Thread.sleep(2000);
//        click(GetLocator("Qrcode"));
//        Thread.sleep(2000);
//        click(GetLocator("Menu"));

        //Thread.sleep(2000);
        //alert.dismiss();
      //  Boolean GeoLoc = click(GetLocator("GeoLoc"));
        //driver.switchTo().alert();
       // alert.dismiss();
        //BooleanAsseration(GeoLoc, extentTest, "User able to clicked and validate GeoLoc view", "Not able to User able to clicked and validate GeoLoc view");

        //Thread.sleep(2000);
      //  click(GetLocator("Menu"));
       // Thread.sleep(2000);
        Boolean Drawing = click(GetLocator("Drawing"));
        Thread.sleep(2000);
        click(GetLocator("Menu"));
        Thread.sleep(2000);
//        click(GetLocator("About"));
//        Thread.sleep(2000);
//        click(GetLocator("Menu"));
//        Thread.sleep(2000);
        Boolean Reset =  click(GetLocator("Reset"));
        BooleanAsseration(Reset, extentTest, "User able to clicked and validate Reset view", "Not able to User able to clicked and validate Reset view");
        Thread.sleep(2000);

        click(GetLocator("Menu"));
        Thread.sleep(2000);
        Boolean Logout = click(GetLocator("Logout"));
        BooleanAsseration(Logout, extentTest, "User able to clicked and validate Logout view", "Not able to User able to clicked and validate Logout view");




        return true;
    }

    public boolean FilterandView (ExtentTest extentTest) throws Exception {
        Boolean Listview =   click(GetLocator("TestToggle"));
        BooleanAsseration(Listview, extentTest, "User able to Listview", "Not able to Listview");
        Thread.sleep(2000);
        Boolean Gridview =   click(GetLocator("TestToggle"));
        BooleanAsseration(Gridview, extentTest, "User able to Gridview", "Not able to Gridview");
        Thread.sleep(2000);
        Boolean ModelSelector =   click(GetLocator("ModelSelector"));
        BooleanAsseration(ModelSelector, extentTest, "User able to ModelSelector", "Not able to ModelSelector");
        Thread.sleep(2000);
        Boolean Filteroptions =  getElementList(GetLocator("Filteroptions"));
        BooleanAsseration(Filteroptions, extentTest, "User able to Filteroptions", "Not able to Filteroptions");
        Boolean cancelFilteroptions =    click(GetLocator("Cancel"));
        BooleanAsseration(cancelFilteroptions, extentTest, "User able to cancelFilteroptions", "Not able to cancelFilteroptions");


        return true;
    }




}
