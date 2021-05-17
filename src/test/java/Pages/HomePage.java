package Pages;

import Data.AccountData;
import FrameWork.BrowserAction;
import FrameWork.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class HomePage extends BrowserAction {
    public HomePage(WebDriver driver) {

        this.driver = driver;
    }

//    Locator emailid(){
//        return new Locator(By.id("email"),"Email");
//    }
//    Locator password(){
//        return new Locator(By.id("passwd"),"password");
//    }
//    Locator SubmitButton(){
//        return new Locator(By.id("SubmitLogin"),"password");
//    }
//    Locator Signup_Button(){
//        return new Locator(By.xpath("//a[contains(text(),'Sign in')]"), "SignIn Button");
//    }

    public boolean HomePageData() throws Exception {
        Boolean logoPresent = driver.findElement(GetLocator("Logo").getBy()).isDisplayed();
        Assert.assertTrue(logoPresent);
        System.out.println("Logo validation success");
        Thread.sleep(3000);

        Boolean promoPresent = driver.findElement(GetLocator("Promo").getBy()).isDisplayed();
        Assert.assertTrue(promoPresent);
        System.out.println("Promo validation success");
        VerifyTextPresent(GetLocator("LogoutText"),"Account");
        Boolean account = driver.findElement(GetLocator("LogoutText").getBy()).isDisplayed();
        Assert.assertTrue(account);
        System.out.println("account validation success");

        String ele = driver.findElement(GetLocator("Homepagecart").getBy()).getText();
      //  String text= ele.getText();
        // Assert.assertTrue(homecarticon);
        System.out.println("carticon validation success and current qty : "  + ele);
//
//        List<WebElement> SizeDropDownOptions = driver.findElements(GetLocator("footerlink").getBy());
//        System.out.println(SizeDropDownOptions.size());
//        for (WebElement webElement : SizeDropDownOptions) {
//            String name = webElement.getText();
        // System.out.println(name);
        getElementList(GetLocator("footerlink"));
        getElementList(GetLocator("MegaMenu"));

        VerifyTextPresent(GetLocator("Reservetext"), "All Rights Reserved");

//        contextMenu(GetLocator("MegaMenu"),"Men");
//        contextMenu(GetLocator("MegaMenu"),"Women");
        contextMenu(GetLocator("Footlogo"));
        return true;
    }

}
