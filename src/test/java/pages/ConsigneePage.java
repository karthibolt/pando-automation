package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.UUID;

public class ConsigneePage {

    private final WebDriver driver;

    public ConsigneePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /* -------------------- MENU -------------------- */

    @FindBy(xpath = "//input[@placeholder='Select']")
    private WebElement dashboardDropdown;

    @FindBy(xpath = "//li[contains(@class,'el-select-dropdown__item')]//span[normalize-space()='Consignees']")
    private WebElement consigneeModule;

    @FindBy(xpath = "//h1[normalize-space()='QA Pando - Consignees']")
    private WebElement consigneesHeading;

    /* -------------------- ADD CONSIGNEE -------------------- */

    @FindBy(xpath = "//button[contains(@class,'header_pbtn') and normalize-space()='Add']")
    private WebElement addButton;

    @FindBy(xpath = "//input[@placeholder='Enter Consignee Name']")
    private WebElement consigneeName;

    @FindBy(xpath = "//input[@placeholder='Enter Reference Id']")
    private WebElement referenceId;

    @FindBy(xpath = "//input[@placeholder='Enter State']")
    private WebElement state;

    @FindBy(xpath = "//input[@placeholder='Enter Region']")
    private WebElement regionDropdown;

    @FindBy(xpath = "//input[@placeholder='Enter City']")
    private WebElement city;

    @FindBy(xpath = "//input[@placeholder='Enter Postal code']")
    private WebElement pinCode;

    @FindBy(xpath = "//input[@placeholder='Enter Logistics Representative']")
    private WebElement logisticsRepresentative;

    @FindBy(xpath = "//input[@placeholder='Enter Representative Mobile']")
    private WebElement representativeMobile;

    @FindBy(xpath = "//button[.//span[normalize-space()='Save']]")
    private WebElement saveButton;


    /* -------------------- SUCCESS TOAST -------------------- */

    @FindBy(css = "div.el-message--success[role='alert']")
    private WebElement successToast;

    /* -------------------- SEARCH -------------------- */

    @FindBy(xpath = "//div[@class='searchterm tagblock pointer']")
    private WebElement searchDropdown;

    @FindBy(xpath = "//input[@placeholder='Enter Name']")
    private WebElement consigneeTextBox;

//    @FindBy(xpath = "//div[@class='list-item']//child::span[contains(text(),'in Name')]")
//    private WebElement firstSearchResult;

    @FindBy(xpath = "//button[text()='Search']")
    private WebElement searchButton;

    /* -------------------- ACTIONS -------------------- */


    public boolean isConsigneesHeadingDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(consigneesHeading));
        return consigneesHeading.isDisplayed();
    }


    public void selectConsigneeFromDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // Click the dropdown
        wait.until(ExpectedConditions.elementToBeClickable(dashboardDropdown)).click();

        // Scroll into view and click "Consignees"
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", consigneeModule);
        wait.until(ExpectedConditions.elementToBeClickable(consigneeModule)).click();
    }


    public void clickAddConsignee() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    }

    public void enterConsigneeDetails(
            String consignee,
            String refId,
            String stateName,
            String region,
            String cityName,
            String pin,
            String representative,
            String mobile
    ) {

        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOf(consigneeName));
        wait.until(ExpectedConditions.elementToBeClickable(consigneeName));

        consigneeName.click();
        consigneeName.clear();
        consigneeName.sendKeys(
                consignee + "-" + new SimpleDateFormat("HHmmss").format(new Date())
        );

        referenceId.sendKeys(refId + "_" + UUID.randomUUID());

        state.sendKeys(stateName);
        regionDropdown.sendKeys(region);
        city.sendKeys(cityName);
        pinCode.sendKeys(pin);
        logisticsRepresentative.sendKeys(representative);
        representativeMobile.sendKeys(mobile);
    }

    public void clickSave() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public boolean isSuccessMessageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(successToast));
            return successToast.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public WebElement getSuccessToast() {
        return successToast;
    }

    /* -------------------- SEARCH FLOW -------------------- */

    public void clickSearchDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            wait.until(ExpectedConditions.invisibilityOf(successToast));
        } catch (Exception e) {
            // toast not present
        }

        wait.until(ExpectedConditions.elementToBeClickable(searchDropdown)).click();
    }

    public void enterConsigneeName(String consignee) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(consigneeTextBox));
        input.click();
        input.clear();
        input.sendKeys(consignee);
    }

    public void clickSearchButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }


    public void searchConsignee(String consigneeName) {
        clickSearchDropdown();
        enterConsigneeName(consigneeName);
        clickSearchButton();
    }
    public void waitForPageToLoad(WebDriver driver, int timeoutSeconds) {
        new WebDriverWait(driver, timeoutSeconds).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete")
        );
    }

}
