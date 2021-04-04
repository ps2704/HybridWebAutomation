package Pages;

import FrameWork.BrowserAction;
import FrameWork.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Cart extends BrowserAction {
    public Cart(WebDriver driver) {

        this.driver = driver;
    }

    Locator ProceedToCheckout(){
        return new Locator(By.xpath("//a[contains(@title, 'Proceed to checkout')]"), "Proceed to Checkout");
    }
    Locator Terms_condition_checkbox(){
        return new Locator(By.xpath("//input[@type='checkbox']"), "Terms and Condition checkbox");
    }

    public boolean Proceedforcheckout() throws IllegalAccessException, InstantiationException {
        if(!waitUntilDisplayed(ProceedToCheckout(), 2)){
            waitforPageReady();
            click(ProceedToCheckout());
        }
        click(ProceedToCheckout());
        //In Address Selection window
        click(ProceedToCheckout());
        //In Delivery Selection window
        click(Terms_condition_checkbox());
        click(ProceedToCheckout());

        return true;
    }
}
