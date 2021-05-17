package Pages;

import FrameWork.BrowserAction;
import FrameWork.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Cart extends BrowserAction {

    public Cart(WebDriver driver) {

        this.driver = driver;
    }

//    Locator ProceedToCheckout(){
//        return new Locator(By.xpath("//a[contains(text(), 'Checkout')]"), "Proceed to Checkout");
//    }
//    Locator CartQty(){
//        return new Locator(By.xpath("//input[@class='input-qty']"), "cartQty");
//    }
//    Locator RemoveCartItem(){
//        return new Locator(By.xpath("//i[@class='fa fa-trash']"), "removeCartq");
//    }
//    Locator Increaseqty(){
//        return new Locator(By.xpath("//div[@class='btn-plus']"), "Increaseqty");
//    }
//    Locator decreaseqty(){
//        return new Locator(By.xpath("//div[@class='btn-minus']"), "Decreaseqty");
//    }
//    Locator EmptyCart(){
//        return new Locator(By.xpath("//p[@class='cart-msg']"), "Emptycart Message");
//    }

    //input[@class='input-qty']


        public boolean Proceedforcheckout() throws IllegalAccessException, InstantiationException, InterruptedException {
        if(!waitUntilDisplayed(GetLocator("Checkout"), 2)){
            waitforPageReady();
            click(GetLocator("Checkout"));
        }
        int count = 0;
        //VerifyCartCountAfterAdd(CartQty(),count);
        Thread.sleep(5000);
        click(GetLocator("Checkout"));
        Thread.sleep(5000);

        return true;
    }
     public boolean UpdateCart(Locator cartqty ,int count) throws IllegalAccessException, InstantiationException, InterruptedException {
        if(count>0){
            int currentqty = Integer.parseInt(driver.findElement(GetLocator("CartQty").getBy()).getAttribute("value"));
           click(GetLocator("Increaseqty"));
           Thread.sleep(5000);
            int CountafterAdd = Integer.parseInt(driver.findElement(GetLocator("CartQty").getBy()).getAttribute("value"));
            System.out.println("Count after Increase Qty " + CountafterAdd );
            count = currentqty + count;
            Assert.assertEquals(CountafterAdd, count);
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
            return true;
         }
        else if (count==0) {
            click(GetLocator("RemoveCartItem"));
            String Actualmsg = (driver.findElement(GetLocator("EmptyCart").getBy()).getText());
            String Expectedmsg ="Your Shopping Cart is Empty";
            Assert.assertEquals(Actualmsg, Expectedmsg);
            return true;
        }


        return false;
     }
    public boolean CartVerification(int count) throws IllegalAccessException, InstantiationException, InterruptedException {
        // int count = 0;
        UpdateCart(GetLocator("CartQty"),count);

        //In Address Selection window
        // click(ProceedToCheckout());
        //In Delivery Selection window
        //click(Terms_condition_checkbox());
        //click(ProceedToCheckout());

        return true;
    }
}
