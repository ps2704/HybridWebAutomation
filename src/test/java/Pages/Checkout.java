package Pages;

import FrameWork.BrowserAction;
import FrameWork.Locator;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Checkout extends BrowserAction {
    public Checkout(WebDriver driver) {

        this.driver = driver;
    }
    Locator PaymentOption(){
        return new Locator(By.xpath("//a[contains(@title, 'Pay by check.')]"), "Proceed to Checkout");
    }
    Locator ConfirmOrder(){
        return new Locator(By.xpath("//a[contains(text(),'I confirm my order')]"), "I confirm my order");
    }


    public boolean checkout() throws IllegalAccessException, InstantiationException {
        if(!waitUntilDisplayed(PaymentOption(), 2)){
            waitforPageReady();
            click(PaymentOption());
        }
        click(PaymentOption());
        click(ConfirmOrder());
        return true;
    }
}
