package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


//Here we will be creating all the actions/methods which are commonly required for every page object class
public class BasePage {
    WebDriver driver ;
    public BasePage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
}
