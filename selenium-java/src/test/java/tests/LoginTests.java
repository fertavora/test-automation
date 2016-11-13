package tests;

import com.sun.org.apache.regexp.internal.RE;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.LoginPage;

/**
 * Created by tavete on 22/10/16.
 */

public class LoginTests {
    final static Logger logger = Logger.getLogger(LoginTests.class);

    private WebDriver driver;
    private String appUrl = "http://the-internet.herokuapp.com/login";

    @DataProvider(name = "loginCredentials")
    public Object[][] testData() {
        return new Object[][] {
            { "tomsmith", "SuperSecretPassword!", "You logged into a secure area!"},
            { "user", "password", "Your username is invalid!"},
        };
    }

    @BeforeMethod
    public void start(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(appUrl);
    }

    @AfterMethod
    public void stop(ITestResult result){
        driver.quit();
        logger.info(result.getMethod().getMethodName() + " "+result.getParameters()+" completed!");
        switch(result.getStatus()){
            case ITestResult.SUCCESS:
                logger.info("PASSED!");
                break;
            case ITestResult.FAILURE:
                logger.error("FAILED!");
                break;
            case ITestResult.SKIP:
                logger.warn("SKIPED!");
        }
    }

    @Test(dataProvider = "loginCredentials")
    public void loginOkTest(String username, String password, String expected){
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.loginAs(username, password);
        Assert.assertTrue(loginPage.getErrorMessage().contains(expected), "Login message is not correct");
    }
}
