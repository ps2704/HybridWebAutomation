package Pages;

import Data.AccountData;
import FrameWork.BrowserAction;
import FrameWork.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderConfirmation extends BrowserAction {
    public OrderConfirmation(WebDriver driver) {

        this.driver = driver;
   }
    public boolean OrderConfirmNumber( ) throws IllegalAccessException, InstantiationException {
        if(!waitUntilDisplayed(GetLocator("OrderConfirmationText"), 2)){
            waitforPageReady();
           // click(PaymentOption());
        }
        // click(OrderConfirmationNumber());
        //click(OrderConfirmationText());
       GetTextAndWriteToResults(GetLocator("OrderConfirmationText"));
        StoreDynamicDataOrderId(GetLocator("OrderConfirmationNumber"));

        return true;
    }
}
