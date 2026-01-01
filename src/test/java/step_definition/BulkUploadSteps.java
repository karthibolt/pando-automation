    package step_definition;

    import BasePackage.Base;
    import io.cucumber.java.en.When;
    import org.openqa.selenium.WebDriver;
    import pages.BulkUploadPage;

    import java.awt.*;

    public class BulkUploadSteps {

        WebDriver driver = Base.getInstance();
        BulkUploadPage bulkUploadPage = new BulkUploadPage(driver);


        @When("user uploads bulk excel file {string}")
        public void user_uploads_bulk_excel_file(String fileName) throws AWTException {

            driver.get("https://qa.pandostaging.in/mdm/depots");
            bulkUploadPage.waitForPageToLoad(driver, 20);
            String filePath = "C:\\Users\\Karthick\\IdeaProjects\\Pando_project\\src\\test\\resources\\excel\\IndentUpload.xlsx";
             System.out.println("Uploading Excel File: " + filePath);

            bulkUploadPage.uploadExcelFile(filePath);
        }


    }
