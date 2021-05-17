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
//       Locator OrderConfirmationText(){
//        return new Locator(By.xpath("//div[@class='col-sm-12']//strong"), "Order Confirmation");
//    }
//    Locator OrderConfirmationNumber(){
//        return new Locator(By.xpath("//div[@class='col-sm-12 col-md-5 col-lg-5  border']/p"), "OrderConfirmationNumber");
//    }
//*[@id="__next"]/main/div/div[1]/h3/strong
//*[@id="__next"]/main/div/div[2]/p/text()[1]
//*[@id="__next"]/main/div/div[2]/p/text()[2]

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
