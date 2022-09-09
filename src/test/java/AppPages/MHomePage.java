package AppPages;

import Data.AccountData;
import FrameWork.AppAction;
import FrameWork.BrowserAction;
import FrameWork.CommonConstant;
import FrameWork.Locator;
import com.relevantcodes.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class MHomePage extends AppAction {

    SoftAssert Assert = new SoftAssert();
    public MHomePage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }


    //  //XCUIElementTypeButton[@name="Don’t Allow"] , //XCUIElementTypeButton[@name="WOMEN"]
    Locator dynamicButton(String variable) {
        return new Locator(By.xpath("//android.widget.TextView[contains(@text,'" + variable + "')]|//android.widget.ImageButton[contains(@content-desc,'" + variable + "')] | //android.widget.Button[contains(@text,'" + variable + "')]|//android.widget.ImageView[contains(@content-desc,'" + variable + "')] | //XCUIElementTypeButton[@name='" + variable + "']"), "Dynamic button");
    }

    public boolean MHomePageData(ExtentTest extentTest) throws Exception {
        Boolean logoPresent = driver.findElement(GetLocator("Logo").getBy()).isDisplayed();
        //Assert.assertTrue(logoPresent);
        BooleanAsseration(logoPresent, extentTest, "Logo Present", "Logo Not Present");
        System.out.println("Logo validation success");
        Thread.sleep(3000);


        Boolean mimage = driver.findElement(GetLocator("image").getBy()).isDisplayed();
        BooleanAsseration(mimage, extentTest, "Image Present", "Image Not Present");
       // Assert.assertTrue(mimage);
        System.out.println("Image validation success");
        Thread.sleep(3000);


return true;
    }
}






//       }




