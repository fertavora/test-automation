package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by tavete on 01/11/16.
 */
public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id="username")
    WebElement inputUsername;

    @FindBy(id="password")
    WebElement inputPassword;

    @FindBy(xpath="//*[@id=\"login\"]/button")
    WebElement btnLogin;

    @FindBy(id="flash")
    WebElement errorMessage;

    public void loginAs(String u, String p){
        inputUsername.sendKeys(u);
        inputPassword.sendKeys(p);
        btnLogin.click();
    }

    public String getErrorMessage(){
        return errorMessage.getText();
    }
}
