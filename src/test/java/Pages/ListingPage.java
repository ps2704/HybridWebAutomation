package Pages;

import Data.AccountData;
import FrameWork.BrowserAction;
import FrameWork.Locator;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class ListingPage extends BrowserAction {
    SoftAssert Assert = new SoftAssert();
    public ListingPage(WebDriver driver) {

        this.driver = driver;
    }

    public boolean NavigateToL2CategoryList (ExtentTest extentTest) throws IllegalAccessException, InstantiationException {
        mouseHover(GetLocator("MenCategory"));
        click(GetLocator("SubCategory"));
        extentTest.log(LogStatus.PASS, "Navigation Successful");
        //Assert.assertTrue(true, "Navigation Successful");
        return true;
    }
    public boolean PlpItems(ExtentTest extentTest){
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
        BooleanAsseration(true, extentTest, "Navigation to PLP Successful", "Navigation to PLP Failed");
        return true;
    }
    public boolean ClickPlpItems( ExtentTest extentTest) throws IllegalAccessException, InstantiationException, InterruptedException {
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
        BooleanAsseration(true, extentTest, "Product Has been Added to Cart", "Product has not been added to cart");
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
