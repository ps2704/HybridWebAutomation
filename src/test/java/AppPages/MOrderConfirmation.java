package AppPages;

import FrameWork.AppAction;
import FrameWork.BrowserAction;
import com.relevantcodes.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;

public class MOrderConfirmation extends AppAction {
    public MOrderConfirmation(AppiumDriver<MobileElement> driver) {

        this.driver = driver;
   }
    public boolean OrderConfirmNumber(ExtentTest extentTest) throws IllegalAccessException, InstantiationException {
        getText(GetLocator("checkProcessbar"));
        getText(GetLocator("Orderstate"));
       getText(GetLocator("Ordertext"));
        click(GetLocator("backhomebtn"));


        return true;
        //            checkProcessbar

    }
}
