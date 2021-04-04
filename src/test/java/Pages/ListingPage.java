package Pages;

import Data.AccountData;
import FrameWork.BrowserAction;
import FrameWork.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListingPage extends BrowserAction {
    public ListingPage(WebDriver driver) {

        this.driver = driver;
    }

    Locator Tshirt_category(){
        return new Locator(By.xpath("//a[contains(text(),'T-shirts')]"), "T-shirts Categry");
    }
    Locator Add_to_bag(){
        return new Locator(By.xpath("//a[contains(text(),'Add to cart')]"), "Add to cart");
    }
    Locator ProceedToCheckout(){
        return new Locator(By.xpath("//a[contains(@title, 'Proceed to checkout')]"), "Proceed to Checkout");
    }



    public boolean AddToBagCategoryProduct () throws IllegalAccessException, InstantiationException {
        if(!waitUntilDisplayed(Tshirt_category(), 2)){
            waitforPageReady();
            click(Tshirt_category());
        }
        click(Tshirt_category());
        click(Add_to_bag());
        click(ProceedToCheckout());
        return true;
    }



}
