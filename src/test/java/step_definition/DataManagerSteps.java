package step_definition;

import BasePackage.Base;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.DataManagerPage;
import utils.SharedData;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataManagerSteps {
    private String createdDepotName;
    private String createdGateName;

    WebDriver driver = Base.getInstance();
    DataManagerPage dataManagerPage = new DataManagerPage(driver);

    @When("User navigates to the Data Manager module")
    public void user_navigates_to_data_manager() {

        driver.get("https://qa.pandostaging.in/mdmLanding");
        dataManagerPage.waitForPageToLoad(driver, 20);

    }

    @When("User opens the Depot module")
    public void user_opens_the_depot_module() {
        dataManagerPage.openDepotModule();

        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    @When("User clicks on Add Depot")
    public void user_clicks_add_depot() {
        dataManagerPage.clickAddDepot();
    }

    @When("User enters depot details")
    public void user_enters_depot_details() {

        createdDepotName = "Depot-" + new SimpleDateFormat("HHmmss").format(new Date());
        SharedData.createdDepotName = createdDepotName;

        dataManagerPage.enterDepotDetails(
                createdDepotName,
                "REF001",
                "29ABCDE1234F",
                "Test Address 1",
                "Chennai",
                "Tamil Nadu",
                "600001",
                "North"
        );
    }

    @When("User clicks Create")
    public void user_clicks_create() {
        dataManagerPage.clickCreate();
    }

    @Then("Depot creation success message should be displayed")
    public void depot_creation_success_message_should_be_displayed() {

        WebDriverWait wait = new WebDriverWait(driver, 20);

        wait.until(driver -> dataManagerPage.getSuccessToast().isDisplayed());

        Assert.assertTrue(
                dataManagerPage.isSuccessMessageDisplayed(),
                "Depot creation failed!"
        );
    }
    @And("User searches for depot")
    public void user_searches_for_depot() {
        dataManagerPage.searchDepot(createdDepotName);
    }



    @When("User creates a gate for the searched depot")
    public void user_creates_gate_for_searched_depot() {

        createdGateName = "Gate-" + new SimpleDateFormat("HHmmss").format(new Date());

        dataManagerPage.createGateForSearchedDepot(
                createdGateName,
                "REF_GATE_001"+ new SimpleDateFormat("HHmmss").format(new Date()),
                "Gate Address 1",
                "Chennai",
                "Tamil Nadu",
                "600001",
                "North",
                "Pavithra"
        );

        Assert.assertTrue(
                dataManagerPage.isGateVisibleInDashboard(createdGateName),
                " Gate not visible in dashboard"
        );
    }


}
