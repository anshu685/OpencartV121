package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

    public MyAccountPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//div[@id='content']/h2[text()='My Account']")
    WebElement MyAccount;

    @FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']")
    WebElement Logoutbtn;

    public boolean isMyAccountPageExist(){
        try {
            return (MyAccount.isDisplayed());
        }catch (Exception e){
            return false;
        }
    }

    public void clickLogout(){
        Logoutbtn.click();
    }
}
