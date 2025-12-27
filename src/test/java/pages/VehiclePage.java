package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VehiclePage {

    private final WebDriver driver;

    public VehiclePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    @FindBy(xpath = "//input[@placeholder='Select']")
    private WebElement dashboardDropdown;

    @FindBy(xpath = "//li[contains(@class,'el-select-dropdown__item')]//span[normalize-space()='Vehicles']")
    private WebElement vehicleModule;

    @FindBy(xpath = "//h1[normalize-space()='Vehicles']")
    private WebElement vehicleHeading;


    @FindBy(xpath = "//button[contains(@class,'header_pbtn') and normalize-space()='Add']")
    private WebElement addButton;

    @FindBy(xpath = "//input[@placeholder='Enter Vehicle Name here']")
    private WebElement vehicleNameInput;

    @FindBy(xpath = "//input[@placeholder='Enter CFT here']")
    private WebElement cftInput;

    @FindBy(xpath = "//input[@placeholder='Enter KG here']")
    private WebElement kgInput;

    @FindBy(xpath = "(//input[@placeholder='Select'])[3]")
    private WebElement vehicleTypeDropdown;

    @FindBy(xpath = "(//li[contains(@class,'el-select-dropdown__item')]//span[text()='FTL'])[2]")
    private WebElement vehicleTypeFTLOption;

    @FindBy(xpath = "(//input[@placeholder='Select'])[3]")
    private WebElement fuelTypeDropdown;

    @FindBy(xpath = "//li[contains(@class,'el-select-dropdown__item')]//span[normalize-space()='Diesel']")
    private WebElement fuelTypeDieselOption;

    @FindBy(xpath = "//button[@class='el-button el-button--primary el-button--mini']//child::span[normalize-space()='Create']")
    private WebElement createButton;

    @FindBy(css = "div.el-message--success[role='alert']")
    private WebElement successToast;

    @FindBy(xpath = "//input[@placeholder='Enter Name']")
    private WebElement vehicleSearchInput;

    @FindBy(xpath = "//span[@class='inputtextlabel']")
    private WebElement SearchInput;



    public void selectVehicleFromDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(dashboardDropdown)).click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", vehicleModule);
        wait.until(ExpectedConditions.elementToBeClickable(vehicleModule)).click();
    }

    public void clickAddVehicle() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    }

    public void enterVehicleDetails(
            String name,
            String cft,
            String kg,
            String vehicleType,
            String fuelType
    ) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        vehicleNameInput.sendKeys(name + "-" + new SimpleDateFormat("HHmmss").format(new Date()));

        cftInput.sendKeys(cft);
        kgInput.sendKeys(kg);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].click();", vehicleTypeDropdown);
        js.executeScript("arguments[0].click();", vehicleTypeFTLOption);


        js.executeScript("arguments[0].click();", fuelTypeDropdown);
        js.executeScript("arguments[0].click();", fuelTypeDieselOption);


        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", createButton);
        wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();
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

    public void searchVehicle(String vehicleName) {
        WebDriverWait wait = new WebDriverWait(driver, 10);


        try {
            wait.until(ExpectedConditions.invisibilityOf(successToast));
        } catch (TimeoutException e) {
            System.out.println("Success popup still visible after 10s");
        }

        SearchInput.click();
        vehicleSearchInput.click();
        vehicleSearchInput.sendKeys(vehicleName);
        vehicleSearchInput.sendKeys(Keys.ENTER);
    }

    public void waitForPageToLoad(WebDriver driver, int timeoutSeconds) {
        new WebDriverWait(driver, timeoutSeconds).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete")
        );
    }
}
