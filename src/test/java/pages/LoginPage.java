package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {


    private static WebDriver driver;


    public LoginPage(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//input[@type='text']")
    private WebElement username;


    @FindBy (xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy (xpath = "//button[@type='submit']")
    private WebElement submit;



    public void enterUsername(String userInput){

        username.sendKeys(userInput);
    }

    public void enterPassword(String passInput){

        password.sendKeys(passInput);
    }

    public void clickSubmit(){

        submit.click();
    }

}
