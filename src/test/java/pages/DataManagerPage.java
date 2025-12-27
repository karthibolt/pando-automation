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
import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class DataManagerPage {

    private final WebDriver driver;
    private String mainTabHandle;

    public DataManagerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "li.app-side-menu > span.pointer")
    private WebElement hamburgerMenu;

    @FindBy(xpath = "//a[normalize-space(text())='DATA MANAGER']")
    private WebElement dataManagerMenu;

    @FindBy(xpath = "(//span[contains(text(), 'Depot')])[3]")
    private WebElement depotModule;

    @FindBy(xpath = "//button[normalize-space(text())='Add']")
    private WebElement addButton;

    @FindBy(xpath = "//input[@placeholder='Enter Depot Name']")
    private WebElement depotName;

    @FindBy(xpath = "//input[@placeholder='Enter Reference Id']")
    private WebElement referenceId;

    @FindBy(xpath = "//input[@placeholder='Enter Depot Short Code']")
    private WebElement depotShortCode;

    @FindBy(xpath = "//input[@placeholder='Enter GSTIN']")
    private WebElement gstin;

    @FindBy(xpath = "//input[@placeholder='Enter Address']")
    private WebElement depotAddress;

    @FindBy(xpath = "//input[@placeholder='Enter City']")
    private WebElement city;

    @FindBy(xpath = "//input[@placeholder='Enter State']")
    private WebElement state;

    @FindBy(xpath = "//input[@placeholder='Enter Pin Code']")
    private WebElement pinCode;

    @FindBy(xpath = "(//input[@placeholder='Select'])[2]")
    private WebElement regionDropdown;


    @FindBy(xpath = "(//button[//span[normalize-space()='Create']])[2]")
    private WebElement createButton;


    @FindBy(css = "div.el-message--success[role='alert']")
    private WebElement successToast;

    @FindBy(xpath = "//div[@class='searchterm tagblock pointer']")
    private WebElement searchDropdown;

    @FindBy(xpath = "//input[@placeholder='Enter Depot']")
    private WebElement depotTextBox;

    @FindBy(xpath = "//div[@class='list-item']//child::span[contains(text(),'in Name')]")
    private WebElement firstSearchResult;

    @FindBy(xpath = "//button[text()='Search']")
    private WebElement searchButton;


    @FindBy(xpath = "(//span[@class='link-action'])[1]")
    private WebElement editButton;

    @FindBy(xpath = "//span[normalize-space()='Add Gate']")
    private WebElement addGateButton;

    @FindBy(xpath = "//input[@placeholder='Enter Gate Name']")
    private WebElement gateNameInput;

    @FindBy(xpath = "//input[@placeholder='Enter Reference Id']")
    private WebElement gateRefId;

    @FindBy(xpath = "//input[@placeholder='Enter Address']")
    private WebElement gateAddress;

    @FindBy(xpath = "//input[@placeholder='Enter City']")
    private WebElement gateCity;

    @FindBy(xpath = "//input[@placeholder='Enter State']")
    private WebElement gateState;

    @FindBy(xpath = "//input[@placeholder='Enter Pin Code']")
    private WebElement gatePinCode;

    @FindBy(xpath = "(//input[@placeholder='Select'])[2]")
    private WebElement gateRegionDropdown;


    @FindBy(xpath = "//li[@class='el-select-dropdown__item']//child::span[text()='South']")
    private WebElement gateRegion;

    @FindBy(xpath = "//input[@placeholder='Search user to add']")
    private WebElement assignUserDropdown;

    @FindBy(xpath = "//input[@placeholder='Enter User']")
    private WebElement assignUserTextBox;

    @FindBy(xpath = "//button[normalize-space()='Create']")
    private WebElement gateCreateButton;


    public void openDepotModule() {
        depotModule.click();

        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(mainTabHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    public void clickAddDepot() {
        addButton.click();
    }


    public void enterDepotDetails(String name, String refId,
                                  String gst, String address,
                                  String cityName, String stateName,
                                  String pin, String region) {

        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOf(depotName));
        wait.until(ExpectedConditions.elementToBeClickable(depotName));
        depotName.click();
        depotName.clear();
        depotName.sendKeys(name + new SimpleDateFormat("HHmmss").format(new Date()));

        System.out.println("Depot name entered: " + depotName);

        referenceId.sendKeys( refId + "_" + UUID.randomUUID().toString());
        depotShortCode.sendKeys("D" + new SimpleDateFormat("HHmmss").format(new Date()));
        gstin.sendKeys(gst);
        depotAddress.sendKeys(address);
        city.sendKeys(cityName);
        state.sendKeys(stateName);
        pinCode.sendKeys(pin);

        regionDropdown.click();

        String optionXpath =
                "//li[contains(@class,'el-select-dropdown__item')]//span[normalize-space()='" + region + "']";

        driver.findElement(By.xpath(optionXpath)).click();
    }




    public void clickCreate() {
        createButton.click();
    }

    public boolean isSuccessMessageDisplayed() {
        try {
            return successToast.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public WebElement getSuccessToast() {
        return successToast;
    }


    public void clickSearchDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            wait.until(ExpectedConditions.invisibilityOf(successToast));
        } catch (Exception e) {

        }

        wait.until(ExpectedConditions.elementToBeClickable(searchDropdown));


        searchDropdown.click();
    }




    public void enterDepotName() {

        depotTextBox.click();
    }
    public void enterDepotName(String depotname) {

        depotTextBox.sendKeys(depotname);
    }

    public void selectFirstSearchResult() {
        firstSearchResult.click();
    }

    public void clickSearchButton() {
        searchButton.click();
    }


    public void searchDepot(String depotName) {
        clickSearchDropdown();
        enterDepotName();
        selectFirstSearchResult();
        enterDepotName(depotName);
        clickSearchButton();
    }

    public void createGateForSearchedDepot(String gateName, String refId,
                                           String address, String city, String state,
                                           String pin, String region, String username) {

        editButton.click();
        addGateButton.click();
        gateNameInput.sendKeys(gateName);
        gateRefId.sendKeys(refId);
        gateAddress.sendKeys(address);
        gateCity.sendKeys(city);
        gateState.sendKeys(state);
        gatePinCode.sendKeys(pin);


        gateRegionDropdown.click();
        String optionXpath = "//li[contains(@class,'el-select-dropdown__item')]//span[normalize-space()='" + region + "']";
        driver.findElement(By.xpath(optionXpath)).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;


        assignUserDropdown.click();
        assignUserDropdown.sendKeys(username);
        js.executeScript("arguments[0].click();", assignUserDropdown);

        gateCreateButton.click();
    }

    public boolean isGateVisibleInDashboard(String gateName) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        By gateNameSpan = By.xpath(
                "//span[contains(@class,'text-blue-dark1') and normalize-space()='" + gateName + "']"
        );

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(gateNameSpan));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForPageToLoad(WebDriver driver, int timeoutSeconds) {
        new WebDriverWait(driver, timeoutSeconds).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete")
        );
    }



    }
