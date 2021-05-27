package AppPages;

import FrameWork.AppAction;
import FrameWork.BrowserAction;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Locale;

public class MListingPage extends AppAction {
    public MListingPage(AppiumDriver<MobileElement> driver) {

        this.driver = driver;
    }

    public boolean NavigateToL2CategoryList () throws Exception {
        VerifyTextPresent(GetLocator("products"),"PRODUCTS");
        isDisplayed(GetLocator("plplogo"));
        //getMultipleText(GetLocator("plpitem"));
        getElementList(GetLocator("plpitem"));
       // getElementList(GetLocator("item"));
        click(GetLocator("item"));



        return true;
    }




}
