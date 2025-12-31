package step_definition;

import BasePackage.Base;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;;
import pages.IndentPage;
import utils.SharedData;

public class IndentSteps {

    private String createdIndentNumber;

    WebDriver driver = Base.getInstance();
    IndentPage indentPage = new IndentPage(driver);

    @When("user navigates to the indent module in dropdown")
    public void user_navigates_to_indent_module() {
        driver.get("https://qa.pandostaging.in/mdmLanding");
        indentPage.waitForPageToLoad(driver, 20);

        indentPage.selectIndentFromDropdown();
    }

    @When("user clicks on Add Indent")
    public void user_clicks_on_add_indent() {
        indentPage.clickAddIndent();
    }

    @When("user search for Depot name")
    public void user_search_for_Depot_name() {
        String depotName = SharedData.createdDepotName;
        System.out.println("Using depot: " + depotName);

        indentPage.clickSearchDepotname(depotName);
    }


    @When("user search for Consignee  name")
    public void user_search_for_Consignee_name() {
        String createdConsigneeName = SharedData.createdConsigneeName;
        System.out.println("Using consignee name : " + createdConsigneeName);

        indentPage.clickAddDropOffbutton(createdConsigneeName);
    }

    @When("user enters indent details")
    public void user_enters_indent_details() {


        indentPage.enterIndentDetails();
    }

    @And("user searches for indent")
    public void user_searches_for_indent() {
            String depotName = SharedData.createdDepotName;
            indentPage.searchIndent(depotName);

    }
    @When("user enters truck assignment details and submits")
    public void user_enters_truck_assignment_details_and_submits() {

        indentPage.enterAssignTruck();
    }
    @When("user reports the truck and marks it arrived")
    public void user_reports_the_truck_and_marks_it_arrived() {

        indentPage.enterReportTruck();
    }

}
