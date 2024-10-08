package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class LoginDDTTest extends TestBaseClass{

    @Test(dataProvider = "LoginData" , dataProviderClass = DataProviders.class , groups = "Datadriven") //because we have dataprovider in other  class and in other package
    public void verify_loginDDT(String email , String password , String exp){

        logger.info("****** Started LoginDDT started ********");
        try {
            //Home Page
            HomePage hp = new HomePage(driver);
            hp.clickMyAcc();
            hp.clickLogin();

            //Login Page
            LoginPage login = new LoginPage(driver);
            logger.info("******** Entering the Valid Data to login to the Account ********");
            login.setEmail(email);
            login.setPassword(password);
            login.clickLogin();

            //My Account PAge after login
            MyAccountPage myAcc = new MyAccountPage(driver);
            boolean tragetPage = myAcc.isMyAccountPageExist();


            if (exp.equalsIgnoreCase("valid")) {
                if (tragetPage == true) {
                    Assert.assertTrue(true);
                    myAcc.clickLogout();
                } else {
                    Assert.assertTrue(false);
                }
            }

            if (exp.equalsIgnoreCase("invalid")) {
                if (tragetPage == true) {
                    myAcc.clickLogout();
                    Assert.assertTrue(false);
                } else {
                    Assert.assertTrue(true);
                }
            }
        }catch (Exception e){
            Assert.fail();
        }

        logger.info("****** Finished LoginDDT started ********");
    }
}
