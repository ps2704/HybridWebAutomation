package Pages;

import Data.AccountData;
import FrameWork.BrowserAction;
import FrameWork.Locator;
import FrameWork.TestListner;
import FrameWork.TestRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import java.util.List;
@Listeners(TestListner.class)
public class HomePage extends BrowserAction {
    public HomePage(WebDriver driver) {

        this.driver = driver;
    }

@Step("Logo validation || Promo validation ||Footer validation || Cart qty validation ")
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
