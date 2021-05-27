package Pages;

import Data.AccountData;
import FrameWork.BrowserAction;
import FrameWork.Locator;
import FrameWork.TestListner;
import io.qameta.allure.Step;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;

@Listeners(TestListner.class)
public class Checkout extends BrowserAction {
    public Checkout(WebDriver driver) {

        this.driver = driver;
    }
    @Step("Shipping information")
    public boolean checkoutPage(AccountData accountData) throws Exception {
        if(!waitUntilDisplayed(GetLocator("PaymentOption"), 2)){
            waitforPageReady();
           // click(PaymentOption());
        }
        EnterValue(GetLocator("cEmail"),accountData.getcEmail());
        EnterValue(GetLocator("ShippingName"),accountData.getShippingName());
        click(GetLocator("checkoutEnterManuallyAddress"));
        EnterValue(GetLocator("shippingAddressLine"),accountData.getShippingAddressLine());
        EnterValue(GetLocator("ShippingAddressLineII"),accountData.getShippingAddressLineII());
        EnterValue(GetLocator("shippingLocality"),accountData.getShippingLocality());
        EnterValue(GetLocator("ShippingPostalCode"),accountData.getShippingPostalCode());
        EnterValue(GetLocator("CardNumber"),accountData.getCardNumber());
        EnterValue(GetLocator("shippingCardExpiry"),accountData.getShippingCardExpiry());
        Thread.sleep(5000);
        EnterValue(GetLocator("CardCvc"),accountData.getCardCvc());
        Thread.sleep(5000);
        scrollIntoView(GetLocator("PaymentOption"));
        click(GetLocator("PaymentOption"));
        Thread.sleep(5000);



        return true;
    }
}
