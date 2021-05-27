package AppPages;

import Data.AccountData;
import FrameWork.AppAction;
import FrameWork.BrowserAction;
import FrameWork.CommonConstant;
import FrameWork.Locator;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class MHomePage extends AppAction {

    public MHomePage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }


    //  //XCUIElementTypeButton[@name="Don’t Allow"] , //XCUIElementTypeButton[@name="WOMEN"]
    Locator dynamicButton(String variable) {
        return new Locator(By.xpath("//android.widget.TextView[contains(@text,'" + variable + "')]|//android.widget.ImageButton[contains(@content-desc,'" + variable + "')] | //android.widget.Button[contains(@text,'" + variable + "')]|//android.widget.ImageView[contains(@content-desc,'" + variable + "')] | //XCUIElementTypeButton[@name='" + variable + "']"), "Dynamic button");
    }

    public boolean MHomePageData() throws Exception {
        Boolean logoPresent = driver.findElement(GetLocator("Logo").getBy()).isDisplayed();
        Assert.assertTrue(logoPresent);
        System.out.println("Logo validation success");
        Thread.sleep(3000);


        Boolean mimage = driver.findElement(GetLocator("image").getBy()).isDisplayed();
        Assert.assertTrue(mimage);
        System.out.println("Logo validation success");
        Thread.sleep(3000);


return true;
    }
}






//       }




