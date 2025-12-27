package step_definition;

import BasePackage.Base;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.VehiclePage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VehicleSteps {

    private String createdVehicleName;

    WebDriver driver = Base.getInstance();
    VehiclePage vehiclePage = new VehiclePage(driver);

    @When("user navigates to the vehicle module in dropdown")
    public void user_navigates_to_vehicle_module() {
        driver.get("https://qa.pandostaging.in/mdmLanding");
        vehiclePage.waitForPageToLoad(driver, 20);

        vehiclePage.selectVehicleFromDropdown();
    }

    @When("user clicks on Add Vehicle")
    public void user_clicks_on_add_vehicle() {
        vehiclePage.clickAddVehicle();
    }

    @When("user enters vehicle details")
    public void user_enters_vehicle_details() {
        createdVehicleName = "Vehicle-" + new SimpleDateFormat("HHmmss").format(new Date());


        vehiclePage.enterVehicleDetails(
                createdVehicleName,
                "10",
                "2000",
                "FTL",
                "Diesel"
        );
    }

    @Then("Vehicle creation success message should be displayed")
    public void vehicle_creation_success_message_should_be_displayed() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(driver -> vehiclePage.getSuccessToast().isDisplayed());

        Assert.assertTrue(
                vehiclePage.isSuccessMessageDisplayed(),
                "Vehicle creation failed!"
        );
    }

    @And("user searches for vehicle")
    public void user_searches_for_vehicle() {
        vehiclePage.searchVehicle(createdVehicleName);
    }
}
