package step_definition;

import BasePackage.Base;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.ConsigneePage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsigneeSteps {
    public  String createdConsigneeName;

    WebDriver driver = Base.getInstance();
    ConsigneePage consigneePage = new ConsigneePage(driver);


    @When("user navigates to the consignees module in dropdown")
    public void user_navigates_to_consignees_module() {
        driver.get("https://qa.pandostaging.in/mdmLanding");
        consigneePage.waitForPageToLoad(driver, 20);

        consigneePage.selectConsigneeFromDropdown();
        Assert.assertTrue(consigneePage.isConsigneesHeadingDisplayed(), "Heading not displayed");

    }

    @And("User clicks on Add consignee")
    public void user_clicks_on_add_consignee() {
        consigneePage.clickAddConsignee();
    }

    @And("user enters the consigneees mandate details")
    public void user_enters_consignee_mandatory_details() {

        createdConsigneeName =
                "Consignee-" + new SimpleDateFormat("HHmmss").format(new Date());

        consigneePage.enterConsigneeDetails(
                createdConsigneeName,
                "CONREF001",
                "Tamil Nadu",
                "South",
                "Chennai",
                "600001",
                "Ramesh Kumar",
                "9876543210"
        );
    }

    @And("user clicks on save button")
    public void user_clicks_on_save_button() {
        consigneePage.clickSave();
    }

    @Then("Consignee creation success message should be displayed")
    public void consignee_creation_success_message_should_be_displayed() {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(driver -> consigneePage.getSuccessToast().isDisplayed());

        Assert.assertTrue(
                consigneePage.isSuccessMessageDisplayed(),
                "Consignee creation failed!"
        );
    }

    @And("user searches the for the created consignee name in search textbox")
    public void user_searches_for_created_consignee() {
        consigneePage.searchConsignee(createdConsigneeName);
    }

}
