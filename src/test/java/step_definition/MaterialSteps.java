package step_definition;

import BasePackage.Base;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.MaterialPage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MaterialSteps {

    private String createdMaterialName;

    WebDriver driver = Base.getInstance();
    MaterialPage materialPage = new MaterialPage(driver);

    @When("user navigates to the material module in dropdown")
    public void user_navigates_to_material_module() {
        driver.get("https://qa.pandostaging.in/mdmLanding");
        materialPage.waitForPageToLoad(driver, 20);
        materialPage.selectMaterialFromDropdown();
    }

    @When("user clicks on Add Material")
    public void user_clicks_on_add_material() {
        materialPage.clickAddMaterial();
    }

    @When("user enters material details")
    public void user_enters_material_details() {

        createdMaterialName = "Material-" + new SimpleDateFormat("HHmmss").format(new Date());
        materialPage.enterMaterialDetails(
                createdMaterialName,
                "MAT001",
                "10",
                "20",
                "5",
                "15",
                "2",
                "Electronics"
        );
    }


    @Then("Material creation success message should be displayed")
    public void material_creation_success_message_should_be_displayed() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(driver -> materialPage.getSuccessToast().isDisplayed());

        Assert.assertTrue(
                materialPage.isSuccessMessageDisplayed(),
                "Material creation failed!"
        );
    }

    @And("user searches for material")
    public void user_searches_for_material() {
        materialPage.searchMaterial(createdMaterialName);
    }
}
