package Pages;

import Data.AccountData;
import FrameWork.BrowserAction;
import FrameWork.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BrowserAction {
    public HomePage(WebDriver driver) {

        this.driver = driver;
    }

    Locator emailid(){
        return new Locator(By.id("email"),"Email");
    }
    Locator password(){
        return new Locator(By.id("passwd"),"password");
    }
    Locator SubmitButton(){
        return new Locator(By.id("SubmitLogin"),"password");
    }
    Locator Signup_Button(){
        return new Locator(By.xpath("//a[contains(text(),'Sign in')]"), "SignIn Button");
    }

    public boolean UserSignIn (AccountData accountData) throws IllegalAccessException, InstantiationException {
        if(!waitUntilDisplayed(emailid(), 2)){
            waitforPageReady();
            click(Signup_Button());
        }
        click(Signup_Button());
        waitUntilDisplayed(emailid(),10);
        System.out.printf(accountData.getUsername());
        EnterValue(emailid(), accountData.getUsername());
        EnterValue(password(), accountData.getPassword());
        click(SubmitButton());
        return true;

    }

}
