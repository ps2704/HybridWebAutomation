package Pages;

import Data.AccountData;
import FrameWork.BrowserAction;
import FrameWork.Locator;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Checkout extends BrowserAction {
    public Checkout(WebDriver driver) {

        this.driver = driver;
    }
//    Locator cEmail(){
//        return new Locator(By.xpath("//input[@id='email']"), "Checkout Email");
//    }
//    Locator ShippingName(){
//        return new Locator(By.xpath("//input[@name='shippingName']"), "Checkout SippingName");
//    }
//    Locator shippingAddressLine(){
//        return new Locator(By.xpath("//input[@id='shippingAddressLine1']"), "Checkout ShippingAddressLine1");
//    }
//    Locator ShippingAddressLineII(){
//        return new Locator(By.xpath("//input[@id='shippingAddressLine2']"), "Checkout ShippingAddressLine2");
//    }
//    Locator shippingLocality(){
//        return new Locator(By.xpath("//input[@id='shippingLocality']"), "Checkout shippingLocality");
//    }
//    Locator ShippingPostalCode(){
//        return new Locator(By.xpath("//input[@id='shippingPostalCode']"), "Checkout shippingPostalCode");
//    }
//    Locator CardNumber(){
//        return new Locator(By.xpath("//input[@id='cardNumber']"), "Checkout cardNumber");
//    }
//    Locator shippingCardExpiry(){
//        return new Locator(By.xpath("//input[@id='cardExpiry']"), "Checkout cardExpiry");
//    }
//    Locator CardCvc(){
//        return new Locator(By.xpath("//input[@id='cardCvc']"), "Checkout CheckoutCardCvc");
//    }
//    Locator checkoutEnterManuallyAddress(){
//        return new Locator(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/form/div[1]/div[3]/div[2]/button/div/span"), "Checkout EnterManuallyAddress");
//    }
//
//    Locator PaymentOption(){
//        return new Locator(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/form/div[2]/div[5]/button/div[3]"), "Proceed to Checkout");
//    }
//    Locator ConfirmOrder(){
//        return new Locator(By.xpath("//a[contains(text(),'I confirm my order')]"), "I confirm my order");
//    }


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
