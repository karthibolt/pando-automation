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
    private Actions actions;

    public BulkUploadPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 15);
        this.actions = new Actions(driver);
    }


    @FindBy(xpath = "//button[contains(text(),'Upload')]")
    private WebElement uploadButton1;

    @FindBy(xpath = "//span[text()='Upload New Master']")
    private WebElement uploadNewMaster;

    @FindBy(xpath = "//span[text()='Download Sample File']")
    private WebElement downloadSampleFile;

    @FindBy(xpath = "(//input[@placeholder='Select Master'])[1]")
    private WebElement selectMasterDropdown;

    @FindBy(xpath = "(//div[contains(@class,'action-row')])[1]")
    private WebElement firstRowActionBar;

    @FindBy(xpath = "(//span[normalize-space(text())='UPLOAD'])[2]")
    private WebElement uploadBtn;

    @FindBy(xpath = "//span[normalize-space(text())='Confirm']")
    private WebElement confirmBtn;

    @FindBy(xpath = "//button//span[text()='Refresh']")
    private WebElement refreshBtn;


    public void uploadExcelFile(String filePath) throws AWTException {


        wait.until( ExpectedConditions.elementToBeClickable(uploadButton1)).click();

        if (!downloadSampleFile.isDisplayed()) {
            uploadNewMaster.click();
        }


        By masterCaret = By.xpath("//label[.='Select Master']/following::i[1]");
        By depotsOption = By.xpath("//span[normalize-space()='Depots']");

        try {
            wait.until(ExpectedConditions.elementToBeClickable(masterCaret)).click();
            wait.until(ExpectedConditions.elementToBeClickable(depotsOption)).click();
        } catch (Exception e) {
            // If already selected or not clickable, just skip
            System.out.println("Master already selected, moving to next actions.");
        }

        By uploadTypeCaret = By.xpath("(//div[contains(@class,'el-select')][.//input[@placeholder='Select Upload type']]//i[contains(@class,'el-select__caret')])[1]");
        wait.until(ExpectedConditions.elementToBeClickable(uploadTypeCaret)).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        By depotOptionsLocator = By.xpath("//div[contains(@class,'el-select-dropdown') and not(contains(@style,'display: none'))]//li/span[text()='Depot Bulk Upload']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(depotOptionsLocator));

        List<WebElement> options = driver.findElements(depotOptionsLocator);
        if (options.isEmpty()) {
            throw new RuntimeException("No Depot Bulk Upload options found!");
        }
        WebElement lastOption = options.get(options.size() - 1);

        ((JavascriptExecutor) driver).executeScript(
                "var evt = new MouseEvent('mousedown', {bubbles:true,cancelable:true}); arguments[0].dispatchEvent(evt);" +
                        "var evt2 = new MouseEvent('mouseup', {bubbles:true,cancelable:true}); arguments[0].dispatchEvent(evt2);" +
                        "var evt3 = new MouseEvent('click', {bubbles:true,cancelable:true}); arguments[0].dispatchEvent(evt3);",
                lastOption
        );

        System.out.println("Clicked Depot Bulk Upload successfully.");
        actions.moveToElement(firstRowActionBar).perform();

        wait.until(ExpectedConditions.elementToBeClickable(uploadBtn)).click();
        Robot robot = new Robot();
        robot.setAutoDelay(500);

        StringSelection ss = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        actions.moveToElement(firstRowActionBar).perform();
        wait.until(ExpectedConditions.elementToBeClickable(confirmBtn)).click();


        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("div.el-dialog__wrapper")));

        wait.until(ExpectedConditions.elementToBeClickable(refreshBtn)).click();

        By firstRowStatus = By.xpath("(//tr[1]/td[4]//span)");

        WebElement statusElement = wait.until(ExpectedConditions.visibilityOfElementLocated(firstRowStatus));

        String statusText = statusElement.getText().trim();
        System.out.println("First row status: " + statusText);

        if (statusText.equals("Validated")) {
            System.out.println("Status is Validated");
        } else {
            System.out.println("Status is NOT Validated");
        }

    }

    public void waitForPageToLoad(WebDriver driver, int timeoutSeconds) {
        new WebDriverWait(driver, timeoutSeconds).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete")
        );
    }




}
