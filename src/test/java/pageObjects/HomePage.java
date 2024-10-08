package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "//span[text()='My Account']")
    WebElement linkMyAcc;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement linkRegister;

    @FindBy(xpath = "//a[text() ='Login']")
    WebElement loginLink;

    public void clickMyAcc(){
        linkMyAcc.click();
    }

    public void clickLinkRegister(){
        linkRegister.click();
    }

    public void clickLogin(){
        loginLink.click();
    }

}
