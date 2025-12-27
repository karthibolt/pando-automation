package step_definition;

import BasePackage.Base;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.TransporterPage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TransporterSteps {

    private String createdTransporterName;

    WebDriver driver = Base.getInstance();
    TransporterPage transporterPage = new TransporterPage(driver);



    @When("user navigates to the transporter module in dropdown")
    public void user_navigates_to_transporter_module() {
        driver.get("https://qa.pandostaging.in/mdmLanding");
        transporterPage.waitForPageToLoad(driver, 20);

        transporterPage.selectTransporterFromDropdown();
    }

    @When("user clicks on Add Transporter")
    public void user_clicks_on_add_transporter() {
        transporterPage.clickAddTransporter();
    }

    @When("user enters transporter details")
    public void user_enters_transporter_details() {

        createdTransporterName = "Transporter-" + new SimpleDateFormat("HHmmss").format(new Date());

        transporterPage.enterTransporterDetails(
                createdTransporterName,
                "REF001",
                "FCM",
                "18",
                "Active",
                "John Doe",
                "9876543210",
                "john.doe@example.com",
                "CreateSomething"
        );
    }

    @Then("Transporter creation success message should be displayed")
    public void transporter_creation_success_message_should_be_displayed() {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(driver -> transporterPage.getSuccessToast().isDisplayed());

        Assert.assertTrue(
                transporterPage.isSuccessMessageDisplayed(),
                "Transporter creation failed!"
        );
    }

    @And("user searches for transporter")
    public void user_searches_for_transporter() {
        transporterPage.searchTransporter(createdTransporterName);
    }


}
