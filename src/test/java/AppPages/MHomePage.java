package AppPages;

import FrameWork.BrowserAction;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage extends BrowserAction {
    public HomePage(WebDriver driver) {

        this.driver = driver;
    }


    public boolean HomePageData() throws Exception {

        return true;
    }

}
