package Pages;

import Data.AccountData;
import FrameWork.BrowserAction;
import FrameWork.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ListingPage extends BrowserAction {
    public ListingPage(WebDriver driver) {

        this.driver = driver;
    }

//    Locator Men_category(){
//        return new Locator(By.xpath("//a[@class='nav-link dropdown-toggle' and text()='Men']"), "Men Category");
//    }
//    Locator L2_category(){
//        return new Locator(By.xpath("//*[@id=\"navbarCollapse\"]/ul/li[3]/div/div/div[1]/ul/li[1]"), "L2 Category");
//    }
//    Locator L2_categoryItems (){
//        return new Locator(By.xpath("//*[@id=\"__next\"]/main/div/div/div/div[2]/div[2]/ul/a/li"), "L2_CategoryItems");
//    }
//    Locator L2_Item (){
//        return new Locator(By.xpath("//*[@id=\"__next\"]/main//div[text()='Sweater Paolo Pecora grey'][1]"), "L2_Items");
//    }
//    ////*[@id="__next"]/main//div[text()='Sweater Moncler blue'][1]
//    ////*[@id="navbarCollapse"]/ul/li[3]/div/div/div[1]/ul/li[1]
//    Locator Add_to_bag(){
//        return new Locator(By.xpath("//button[contains(text(),'Add to cart')]"), "Add to cart");
//    }
//    Locator ClickOnCartIcon(){
//        return new Locator(By.xpath("//a[@href='/cart' and @class='inline']"), "Click on Cart Icon");
//    }
//    Locator ProductName(){
//        return new Locator(By.xpath("//div[@class='col-sm-12 col-md-6 col-lg-6']/h3 "), "Product Name");
//    }
//    Locator ItemId(){
//        return new Locator(By.xpath("//div[@class='itemcode text-capitalize']"), "ItemId details");
//    }
//    Locator ProductPrice(){
//        return new Locator(By.xpath("//div[@class='price']//strong"), "ProductPrice");
//    }
//    Locator PDPStockAvailibilty(){
//        return new Locator(By.xpath("//span[@class='instock text-capitalize']"), "PDPStockAvailibilty");
//    }
//    Locator ProceedToCheckout(){
//        return new Locator(By.xpath("//a[contains(@title, 'Proceed to checkout')]"), "Proceed to Checkout");
//    }
   // __privateStripeMetricsController1210


    public boolean NavigateToL2CategoryList () throws IllegalAccessException, InstantiationException {
        mouseHover(GetLocator("MenCategory"));
        click(GetLocator("SubCategory"));
        return true;
    }
    public boolean PlpItems(){
        ////*[@id="__next"]/main/div/div/div/div[2]/div[2]/ul/a/li
        if(!waitUntilDisplayed(GetLocator("SubCategoryItems"), 2)){
            waitforPageReady();

        }
        driver.navigate().refresh();
        List<WebElement> SizeDropDownOptions = driver.findElements(GetLocator("SubCategoryItems").getBy());
        System.out.println(SizeDropDownOptions.size());
        for (WebElement webElement : SizeDropDownOptions) {
            String name = webElement.getText();
            System.out.println(name);
        }

        return true;
    }
    public boolean ClickPlpItems( ) throws IllegalAccessException, InstantiationException, InterruptedException {
        if(!waitUntilDisplayed(GetLocator("SubCategoryItems"), 2)){
            waitforPageReady();
            //click(L2_Item());
        }
        click(GetLocator("Product"));
        Thread.sleep(5000);
        System.out.println("Product Name ==>> " + driver.findElement(GetLocator("ProductName").getBy()).getText());
        System.out.println("Item Id ==>> " + driver.findElement(GetLocator("ItemIddetails").getBy()).getText());
        System.out.println("Product Price ==>> " + driver.findElement(GetLocator("ProductPrice").getBy()).getText());
        System.out.println("Product Stock Vailibilty ==>> " + driver.findElement(GetLocator("PDPStockAvailibilty").getBy()).getText());
        click(GetLocator("Addtocart"));
        Thread.sleep(5000);
        //SwitchToFrameById("__privateStripeMetricsController1210");
       // Thread.sleep(2000);
        click(GetLocator("GoToCart"));

//        driver.findElement(GetLocator("ProductName").getBy()).getText();
//        click(GetLocator("ClickOnCartIcon"));
//        Thread.sleep(5000);

        return true;
    }




}
