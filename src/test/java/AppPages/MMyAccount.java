package AppPages;

import Data.AccountData;
import FrameWork.AppAction;
import FrameWork.BrowserAction;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MMyAccount extends AppAction {
    public MMyAccount(AppiumDriver<MobileElement> driver) {

        this.driver = driver;
    }


    public boolean Login(AccountData accountData) throws InstantiationException, IllegalAccessException, InterruptedException {

            click(GetLocator("username"));
            EnterValue(GetLocator("username"),accountData.getUsername());
            click(GetLocator("password"));
            EnterValue(GetLocator("password"),accountData.getPassword());
            click(GetLocator("login"));



        return true;

    }

}
