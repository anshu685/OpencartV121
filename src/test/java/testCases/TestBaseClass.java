package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;


//Here we will be creating all the actions/methods which are commonly required for every test case or every test class
public class TestBaseClass {

    public Logger logger; // log print setup , creating Logger class veriable

    public static WebDriver driver;

    public Properties P; // creating properties class veriable

    @BeforeClass(groups = {"Sanity","Master","Regression"})
    @Parameters({"os","browser"})
    public void setUp(String os , String br) throws IOException {
        //Loading Config.properties
        FileReader file= new FileReader("./src//test//resources//config.properties");
        P = new Properties();
        P.load(file);

        logger = LogManager.getLogger(this.getClass()); // it will load the log4j2.xml file for each individual classes which we are running

        if(P.getProperty("execution_env").equalsIgnoreCase("remote"))
        {
            DesiredCapabilities cap = new DesiredCapabilities();
            //Because we are passing the os and browser from the xml file we cant specify the os and browser as we specified below
//            cap.setPlatform(Platform.MAC);
//            cap.setBrowserName("chrome");

            //for choosing os which is sent using the xml file
            if(os.equalsIgnoreCase("windows")){
                cap.setPlatform(Platform.WIN11);
            } else if (os.equalsIgnoreCase("mac")) {
                cap.setPlatform(Platform.MAC);
            } else if (os.equalsIgnoreCase("linux")) {
                cap.setPlatform(Platform.LINUX);

            } else {
                System.out.println("No matching browser");
                return;
            }

            //for opening browser which sent using xml file
            switch (br.toLowerCase())
            {
                case "chrome":
                    cap.setBrowserName("chrome");
                    break;

                case "edge":
                    cap.setBrowserName("MicrosoftEdge");
                    break;

                case "firefox":
                    cap.setBrowserName("firefox");

                default:
                    System.out.println("No matching browser");
                    return;
            }

            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
        }

        if (P.getProperty("execution_env").equalsIgnoreCase("local"))
        {
            switch (br.toLowerCase()){
                case "chrome":
                    driver=new ChromeDriver();
                    break;

                case "edge":
                    driver=new EdgeDriver();
                    break;

                case "firefox":
                    driver=new FirefoxDriver();
                    break;

                default:
                    System.out.println("It is Invalid Driver");
                    return;

            }
        }
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));

//        driver.get("https://tutorialsninja.com/demo/");
        driver.get(P.getProperty("appUrl")); // reading and open the url using config.properties file
        driver.manage().window().maximize();

    }

    public String randomString(){
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return generatedString;
    }

    public String randomNumber(){
        String generatedNumber = RandomStringUtils.randomNumeric(10);
        return generatedNumber;
    }

    //we can use this to generate random passwords
    public String randomAlphaNumeric(){
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        String generatedNumber = RandomStringUtils.randomNumeric(3);
        return (generatedString+"@"+generatedNumber);
    }

    @AfterClass(groups = {"Sanity","Master","Regrssion"})
    public void tearDown(){
        driver.quit();
    }

    public String captureScreen(String tname) throws IOException{
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile= takesScreenshot.getScreenshotAs(OutputType.FILE);


        String targetFilePath = System.getProperty("user.dir")+"\\screenshots"+tname+"_"+timeStamp+".png";
        File targetFile = new File(targetFilePath);

        sourceFile.renameTo(targetFile);
        return targetFilePath;
    }
}
