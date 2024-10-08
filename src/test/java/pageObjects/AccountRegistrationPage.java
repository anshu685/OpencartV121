package pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
    WebDriver driver;
    public AccountRegistrationPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='input-firstname']")
    WebElement txtFirstName;

    @FindBy(xpath = "//*[@id='input-lastname']")
    WebElement txtLastName;

    @FindBy(xpath = "//*[@id='input-email']")
    WebElement txtEmail;

    @FindBy(xpath = "//*[@id='input-telephone']")
    WebElement txtTelephone;

    @FindBy(xpath = "//*[@id='input-password']")
    WebElement txtPassword;

    @FindBy(xpath = "//*[@id='input-confirm']")
    WebElement txtConfirmPassword;

    @FindBy(xpath = "//*[@name='agree']")
    WebElement clkPolicy;

    @FindBy(xpath = "//*[@value='Continue']")
    WebElement btnContinue;

    @FindBy(xpath = "//div[@id='content']//h1")
    WebElement msgConfirmation;


    public void setTxtFirstName(String firstName)
    {
        txtFirstName.sendKeys(firstName);
    }

    public void setTxtLastName(String lastName){
        txtLastName.sendKeys(lastName);
    }

    public void setTxtEmail(String email)
    {
        txtEmail.sendKeys(email);
    }

    public void setTxtTelephone(String telephone){
        txtTelephone.sendKeys(telephone);
    }

    public void setTxtPassword(String password)
    {
        txtPassword.sendKeys(password);
    }

    public void setTxtConfirmPassword(String confirmPassword)
    {
        txtConfirmPassword.sendKeys(confirmPassword);
    }

    public void btnPrivacyPolicy(){
        clkPolicy.click();
    }

    public void btnClickConfirm(){
        //sol1
        btnContinue.click();

        //sol2
//        Actions act =new Actions(driver);
//        act.moveToElement(btnContinue).click().build().perform();

        //sol3
//        btnContinue.submit();

        //sol4
//        JavascriptExecutor js = (JavascriptExecutor)driver;
//        js.executeScript("arguments[0].click();",btnContinue);

        //sol5
//        btnContinue.sendKeys(Keys.RETURN);

        //sol6
//        WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        myWait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();

    }

    public String getConfirmationMsg(){
        try{
            return (msgConfirmation.getText());
        }catch (Exception e){
            return e.getMessage();
        }
    }
}

