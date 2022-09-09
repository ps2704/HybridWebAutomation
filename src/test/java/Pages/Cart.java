package Pages;

import FrameWork.BrowserAction;
import FrameWork.Locator;
import FrameWork.TestListner;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;

@Listeners(TestListner.class)
public class Cart extends BrowserAction {

    public Cart(WebDriver driver) {

        this.driver = driver;
    }

    @Step(" Checkout verification ")
        public boolean Proceedforcheckout(ExtentTest extentTest) throws IllegalAccessException, InstantiationException, InterruptedException {
        if(!waitUntilDisplayed(GetLocator("Checkout"), 2)){
            waitforPageReady();
            click(GetLocator("Checkout"));
        }
        int count = 0;
        //VerifyCartCountAfterAdd(CartQty(),count);
        Thread.sleep(5000);
        click(GetLocator("Checkout"));
        Thread.sleep(5000);
        extentTest.log(LogStatus.PASS, "Able to click on checkout  Successful");
        BooleanAsseration(true, extentTest, "Able to click on checkout  Successful", " checkout  UnSuccessfult");
        return true;
    }
    @Step("Verifying update cart funcitonality ")
     public boolean UpdateCart(Locator cartqty, int count) throws IllegalAccessException, InstantiationException, InterruptedException {
        if(count>0){
            int currentqty = Integer.parseInt(driver.findElement(GetLocator("CartQty").getBy()).getAttribute("value"));
           click(GetLocator("Increaseqty"));
           Thread.sleep(5000);
            int CountafterAdd = Integer.parseInt(driver.findElement(GetLocator("CartQty").getBy()).getAttribute("value"));
            System.out.println("Count after Increase Qty " + CountafterAdd );
            count = currentqty + count;
            Assert.assertEquals(CountafterAdd, count);
           // BooleanAsseration(true, extentTest, "Product QTY has been in crease successfully", "Product QTY  increase validation failed");
            return true;
        }
        else if(count<0){
            int currentqty = Integer.parseInt(driver.findElement(GetLocator("CartQty").getBy()).getAttribute("value"));
             click(GetLocator("decreaseqty"));
            Thread.sleep(5000);
            int Countafterdecrease = Integer.parseInt(driver.findElement(GetLocator("CartQty").getBy()).getAttribute("value"));
            System.out.println("Count after decrease Qty " + Countafterdecrease );
            count= currentqty + count;
           Assert.assertEquals(Countafterdecrease, count);
           // BooleanAsseration(true, extentTest, "Product QTY has been in Decrease successfully", "Product QTY  Decrease validation failed");
            return true;
         }
        else if (count==0) {
            click(GetLocator("RemoveCartItem"));
            String Actualmsg = (driver.findElement(GetLocator("EmptyCart").getBy()).getText());
            String Expectedmsg ="Your Shopping Cart is Empty";
          Assert.assertEquals(Actualmsg, Expectedmsg);
          //  BooleanAsseration(true, extentTest, "Product has been removed successfully", "Product delete functioanlity got failed");
            return true;
        }


        return false;
     }
    @Step("cart verificaiton ")
    public boolean CartVerification(ExtentTest extentTest,int count) throws IllegalAccessException, InstantiationException, InterruptedException {
        // int count = 0;
        UpdateCart(GetLocator("CartQty"),count );

        //In Address Selection window
        // click(ProceedToCheckout());
        //In Delivery Selection window
        //click(Terms_condition_checkbox());
        //click(ProceedToCheckout());

        return true;
    }
}
