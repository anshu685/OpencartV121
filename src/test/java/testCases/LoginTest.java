package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

import java.util.Properties;

public class LoginTest extends TestBaseClass{

    @Test(groups = {"Sanity","Master"})
    public void verifyLogin(){
        logger.info("************* Started verifyLogin method **********************");

        //Home Page
        HomePage hp = new HomePage(driver);
        hp.clickMyAcc();
        hp.clickLogin();

        //Login Page
        LoginPage login=new LoginPage(driver);
        logger.info("******** Entering the Valid Data to login to the Account ********");
        login.setEmail(P.getProperty("email"));
        login.setPassword(P.getProperty("password"));
        login.clickLogin();

        //My Account PAge after login
        MyAccountPage myAcc = new MyAccountPage(driver);
        Assert.assertTrue(myAcc.isMyAccountPageExist());

        logger.info("******* completed Login Method ********");
    }

}
