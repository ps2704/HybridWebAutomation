package AppPages;

import Data.AccountData;
import FrameWork.AppAction;
import FrameWork.BrowserAction;
import com.relevantcodes.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MMyAccount extends AppAction {
    public MMyAccount(AppiumDriver<MobileElement> driver) {

        this.driver = driver;
    }


    public boolean Login(ExtentTest extentTest,AccountData accountData) throws InstantiationException, IllegalAccessException, InterruptedException {
boolean isLogin= false;
          Boolean username=  click(GetLocator("username"));
            EnterValue(GetLocator("username"),accountData.getUsername());
        Boolean password=  click(GetLocator("password"));
            EnterValue(GetLocator("password"),accountData.getPassword());
        Boolean login=  click(GetLocator("login"));
            driver.hideKeyboard();
        BooleanAsseration(login, extentTest, "User able to loin with valid creds", "Login not working");
            if (! (isDisplayed(GetLocator("Invalidcreds")))){
                isLogin = true;

            }

        return isLogin;

    }
    public boolean InValidLogin(ExtentTest extentTest,AccountData accountData) throws InstantiationException, IllegalAccessException, InterruptedException {

        Boolean username= click(GetLocator("username"));
        EnterValue(GetLocator("username"),accountData.getUsername());
        Boolean password=click(GetLocator("password"));
        EnterValue(GetLocator("password"),accountData.getPassword());
        Boolean login= click(GetLocator("login"));
        driver.hideKeyboard();
        Boolean invalidcreds= VerifyTextPresent(extentTest,GetLocator("Invalidcreds"),"Username");
         BooleanAsseration(login, extentTest, "Wrong email or password", "Login not working");

        return true;

    }

}
