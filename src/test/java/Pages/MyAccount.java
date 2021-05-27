package Pages;

import Data.AccountData;
import FrameWork.BrowserAction;
import FrameWork.Locator;
import FrameWork.Locator_Excel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class MyAccount extends BrowserAction {
    public MyAccount(WebDriver driver) {

        this.driver = driver;
    }


    public boolean newUserregistration(AccountData accountData) throws InstantiationException, IllegalAccessException, InterruptedException {
        click(GetLocator("myAccountOption"));
        waitUntilDisplayed(GetLocator("emailid"), 10);
        EnterValue(GetLocator("firstName"), accountData.getFirstName());
        Thread.sleep(2000);
        EnterValue(GetLocator("lastName"), accountData.getLastName());
        Thread.sleep(2000);
        //System.out.printf(accountData.getUsername());
        Thread.sleep(2000);
        EnterValue(GetLocator("signupMail"), accountData.getSignupMail());
        Thread.sleep(2000);
        EnterValue(GetLocator("signupPassword"), accountData.getSignupPassword());
        Thread.sleep(2000);
        EnterValue(GetLocator("confirmPassword"), accountData.getConfirmPassword());
        Thread.sleep(2000);
        //EnterValue(confirmPassword(), accountData.getConfirmPassword());
        EnterValue(GetLocator("phoneNumber"), accountData.getMobile());
        Thread.sleep(2000);
        click(GetLocator("RegisterBtn"));
        Thread.sleep(5000);
        VerifyPageTitle("MyAccount | AntHive Store");
        VerifyPageUrl("anthive");

        return true;

    }

    public boolean UserSignIn(AccountData accountData) throws IllegalAccessException, InstantiationException {
        click(GetLocator("myAccountOption"));
        waitUntilDisplayed(GetLocator("emailid"), 10);
        EnterValue(GetLocator("emailid"), accountData.getUsername());
        EnterValue(GetLocator("password"), accountData.getPassword());

        click(GetLocator("SubmitButton"));
        return true;

    }

    public boolean MyAccountValidation( ) throws IllegalAccessException, InstantiationException, InterruptedException {
        List<WebElement> SizeDropDownOptions = driver.findElements(GetLocator("MyAccountcontent").getBy());
        System.out.println(SizeDropDownOptions.size());
        for (WebElement webElement : SizeDropDownOptions) {
            String name = webElement.getText();
            System.out.println(name);
            VerifyTextPresent(GetLocator("MyProfileText"),"My Profile");
            Thread.sleep(2000);
            click(GetLocator("OrderHistory"));
            VerifyTextPresent(GetLocator("YourOrderText"),"Your Orders");
            Thread.sleep(2000);
            click(GetLocator("ManageAddress"));
            VerifyTextPresent(GetLocator("ManageAddressText"),"Manage Addresses");
            Thread.sleep(2000);
            click(GetLocator("PaymentMethod"));
            VerifyTextPresent(GetLocator("ManagePaymnetText"),"Payment Methods");
            Thread.sleep(2000);
            click(GetLocator("Wishlist"));
            VerifyTextPresent(GetLocator("ManageWishlistText"),"Wish List");
            Thread.sleep(2000);
            click(GetLocator("Logout"));
            VerifyTextPresent(GetLocator("LogoutText"),"Account");
            Thread.sleep(2000);

        }



        return true;

    }

}
