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
        driver.navigate().refresh();
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
