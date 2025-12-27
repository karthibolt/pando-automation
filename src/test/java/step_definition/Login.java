package step_definition;
import BasePackage.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

public class Login  {

    private static final Logger log = LoggerFactory.getLogger(Login.class);
    WebDriver driver;
LoginPage loginPage;

    @Given("User launches the URL")
    public void user_launches_the_URL() {

        driver = Base.getInstance();
        driver.get("https://qa.pandostaging.in/login/azure");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = new LoginPage(driver);
    }

    @When("user enter the username and password")
    public void user_enter_the_username_and_password() {

        loginPage.enterUsername("arunkumarannadurai+karthikeyan@pando.in");
        loginPage.enterPassword("Test@1234");

    }

    @When("user clicks the submit button")
    public void user_clicks_the_submit_button() {

        loginPage.clickSubmit();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


    }

    @Then("user login successfully")
    public void user_login_successfully() {

        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("/indents"),
                ExpectedConditions.urlContains("/dashboard")
        ));

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/indents")
                        || driver.getCurrentUrl().contains("/dashboard"),
                "Login failed!"
        );
    }



}
