package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;


public class AccountRegistrationTest extends TestBaseClass{

    @Test(groups = {"Regrassion","Master"})
    public void accountRegistration(){

        logger.info("********* Starting the AccountRegistrationTest *************");


            HomePage hp = new HomePage(driver);
            hp.clickMyAcc();
            logger.info("********* Clicked on MyAccount  *************");
            hp.clickLinkRegister();
            logger.info("********* Clicked on Register *************");

            logger.info("********* Providing Details for registration *************");
            AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
            regPage.setTxtFirstName(randomString().toUpperCase());
            regPage.setTxtLastName(randomString().toUpperCase());
//          regPage.setTxtEmail("abcansh@gmail.com");
            regPage.setTxtEmail(randomString() + "@gmail.com");
//        regPage.setTxtTelephone("989083637");
            regPage.setTxtTelephone(randomNumber());

            regPage.setTxtPassword("Admin@1234");
            regPage.setTxtConfirmPassword("Admin@1234");

            regPage.btnPrivacyPolicy();
            regPage.btnClickConfirm();

            logger.info("********* Validating expected message *************");
            String confirmationMessage = regPage.getConfirmationMsg();
            if(confirmationMessage.equals("Your Account Has Been Created!")){
                Assert.assertTrue(true);
            }
            else {
                logger.error("Test Failed");
                logger.debug("******************** Debug Logs *********************");
                Assert.fail();
            }

//            Assert.assertEquals(confirmationMessage, "Your Account Has Been Created!!!!");

        logger.info("******************** Finished test case AccountRegistrationTest ****************");

    }

}
