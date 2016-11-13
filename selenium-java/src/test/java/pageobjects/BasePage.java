package pageobjects;

import org.openqa.selenium.WebDriver;

/**
 * Created by tavete on 01/11/16.
 */

public class BasePage {
    private WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }
}
