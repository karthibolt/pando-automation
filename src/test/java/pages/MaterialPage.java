package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.UUID;

public class MaterialPage {

    private final WebDriver driver;

    public MaterialPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /* -------------------- MENU -------------------- */

    @FindBy(xpath = "//input[@placeholder='Select']")
    private WebElement dashboardDropdown;

    @FindBy(xpath = "//li[contains(@class,'el-select-dropdown__item')]//span[normalize-space()='Materials']")
    private WebElement materialModule;

    @FindBy(xpath = "//h1[normalize-space()='Materials']")
    private WebElement materialHeading;

    /* -------------------- ADD MATERIAL -------------------- */

    @FindBy(xpath = "//button[contains(@class,'header_pbtn') and normalize-space()='Add']")
    private WebElement addButton;

    @FindBy(xpath = "//input[@placeholder='Enter Name']")
    private WebElement materialName;

    @FindBy(xpath = "//input[@placeholder='Enter Code']")
    private WebElement materialCode;

    @FindBy(xpath = "//input[@placeholder='Enter Width']")
    private WebElement widthInput;

    @FindBy(xpath = "//input[@placeholder='Enter Length']")
    private WebElement lengthInput;

    @FindBy(xpath = "//input[@placeholder='Enter Pce per box']")
    private WebElement pcePerBoxInput;

    @FindBy(xpath = "//input[@placeholder='Enter Height']")
    private WebElement heightInput;

    @FindBy(xpath = "//input[@placeholder='Enter Weight']")
    private WebElement weightInput;

    @FindBy(xpath = "//input[@placeholder='Enter Division']")
    private WebElement divisionDropdown;


    @FindBy(xpath = "//button[@class= 'el-button el-button--primary el-button--small']//child::span")
    private WebElement createButtonInput;

    @FindBy(css = "div.el-message--success[role='alert']")
    private WebElement successToast;

    @FindBy(xpath = "//input[@placeholder='Enter Name']")
    private WebElement materialSearchInput;

    @FindBy(xpath = "//span[@class='inputtextlabel']")
    private WebElement SearchInput;


    /* -------------------- ACTIONS -------------------- */

    public void selectMaterialFromDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(dashboardDropdown)).click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", materialModule);
        wait.until(ExpectedConditions.elementToBeClickable(materialModule)).click();
    }

    public void clickAddMaterial() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    }

    public void enterMaterialDetails(
            String name,
            String code,
            String width,
            String length,
            String pcePerBox,
            String height,
            String weight,
            String division
    ) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        materialName.sendKeys(name + "-" + new SimpleDateFormat("HHmmss").format(new Date()));
        materialCode.sendKeys(code + "_" + UUID.randomUUID());

        widthInput.sendKeys(width);
        lengthInput.sendKeys(length);
        pcePerBoxInput.sendKeys(pcePerBox);
        heightInput.sendKeys(height);
        weightInput.sendKeys(weight);

        // Select division
        divisionDropdown.sendKeys(division);
       // wait.until(ExpectedConditions.elementToBeClickable(divisionOption)).click();

        // Click create
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", createButtonInput);
        wait.until(ExpectedConditions.elementToBeClickable(createButtonInput)).click();
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

    public void searchMaterial(String materialName) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // Wait for the success popup to disappear
        try {
            wait.until(ExpectedConditions.invisibilityOf(successToast));
        } catch (TimeoutException e) {
            System.out.println("Success popup still visible after 10s, continuing...");
        }

        SearchInput.click();
        wait.until(ExpectedConditions.elementToBeClickable(materialSearchInput));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", materialSearchInput);


        materialSearchInput.click();
        materialSearchInput.sendKeys(materialName);
        materialSearchInput.sendKeys(Keys.ENTER);
    }

    public void waitForPageToLoad(WebDriver driver, int timeoutSeconds) {
        new WebDriverWait(driver, timeoutSeconds).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete")
        );
    }
}
