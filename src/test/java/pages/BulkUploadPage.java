package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

public class BulkUploadPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public BulkUploadPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 15);
    }


    @FindBy(xpath = "//button[contains(text(),'Upload')]")
    private WebElement uploadButton1;
    @FindBy(xpath = "//input[@type='file']")
    private WebElement fileInput;

    @FindBy(css = "div.el-message--success[role='alert']")
    private WebElement successToast;

    @FindBy(xpath = "//table//tr")
    private List<WebElement> tableRows;

    @FindBy(xpath = "(//input[@placeholder='Select Upload type'])[1]")
    private WebElement selectUploadType;
    @FindBy(xpath = "(//span[text()='Depot Bulk Upload'])[4]")
    private WebElement selectDepotBulkupload;


    public void uploadExcelFile() {


        WebDriverWait wait = new WebDriverWait(driver, 15);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        wait.until(
                ExpectedConditions.elementToBeClickable(uploadButton1)
        ).click();


        WebElement caret = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(//input[@placeholder='Select Upload type']/following-sibling::span//i[contains(@class,'el-select__caret')])[1]")
                )
        );
        caret.click();
        WebElement depotBulkUpload =
                wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("(//ul[contains(@class,'el-select-dropdown__list')]//span[normalize-space()='Depot Bulk Upload'])[4]")
                ));

        depotBulkUpload.click();

//        wait.until( ExpectedConditions.elementToBeClickable(selectUploadType) ).click();
//        wait.until( ExpectedConditions.elementToBeClickable(selectDepotBulkupload) ).click();


        try {
            List<WebElement> parentDivs = driver.findElements(By.cssSelector("div.d-flex.action-row.mt-m15.no-bg"));

            WebElement parentMenu = parentDivs.get(0);
            actions.moveToElement(parentMenu).perform();


            WebElement uploadButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("(//button[@type='button']//span)[3]"))
            );

         //  uploadButton.click();

            String filePath = "C:\\Users\\Karthick\\IdeaProjects\\Pando_project\\src\\test\\resources\\excel\\IndentUpload.xlsx";
            fileInput.sendKeys(filePath);
            System.out.println("File path sent: " + filePath);

          // wait.until(  ExpectedConditions.elementToBeClickable(uploadButton1)).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.el-message--success")));
            System.out.println("File uploaded successfully!");

        } catch (Exception e) {
            System.err.println("Failed to upload file: " + e.getMessage());
            e.printStackTrace();
        }
    }



    public String getUploadToastMessage() {
        return wait.until(ExpectedConditions.visibilityOf(successToast)).getText();
    }

    public boolean isDataPresentInTable(String expectedValue) {
        for (WebElement row : tableRows) {
            if (row.getText().contains(expectedValue)) {
                return true;
            }
        }
        return false;
    }
    public void waitForPageToLoad(WebDriver driver, int timeoutSeconds) {
        new WebDriverWait(driver, timeoutSeconds).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete")
        );
    }


    private void uploadFileWithRobot(String filePath) throws AWTException, InterruptedException {
        StringSelection selection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        Robot robot = new Robot();
        robot.setAutoDelay(500);


        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);


        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        Thread.sleep(1000);
    }


}
