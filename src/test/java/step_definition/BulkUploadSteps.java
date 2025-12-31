    package step_definition;

    import BasePackage.Base;
    import io.cucumber.java.en.Then;
    import io.cucumber.java.en.When;
    import org.openqa.selenium.By;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.support.ui.ExpectedConditions;
    import org.openqa.selenium.support.ui.WebDriverWait;
    import org.testng.Assert;
    import pages.BulkUploadPage;

    public class BulkUploadSteps {

        WebDriver driver = Base.getInstance();
        BulkUploadPage bulkUploadPage = new BulkUploadPage(driver);


        @When("user uploads bulk excel file {string}")
        public void user_uploads_bulk_excel_file(String fileName) {

            driver.get("https://qa.pandostaging.in/mdm/depots");
            bulkUploadPage.waitForPageToLoad(driver, 20);
            String filePath = System.getProperty("user.dir")
                    + "/src/test/resources/excel/" + fileName;

            System.out.println("Uploading Excel File: " + filePath);

            bulkUploadPage.uploadExcelFile();
        }


        @Then("bulk excel file should be uploaded successfully")
        public void bulk_excel_file_should_be_uploaded_successfully() {

            WebDriverWait wait = new WebDriverWait(driver, 15);

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div.el-message--success")
            ));

            System.out.println("Bulk Upload success toast verified");
        }

    }
