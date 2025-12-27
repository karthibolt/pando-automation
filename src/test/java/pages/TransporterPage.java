package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class TransporterPage {

    private final WebDriver driver;

    public TransporterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    @FindBy(xpath = "//input[@placeholder='Select']")
    private WebElement dashboardDropdown;

    @FindBy(xpath = "//li[contains(@class,'el-select-dropdown__item')]//span[normalize-space()='Transporter']")
    private WebElement transporterModule;

    @FindBy(xpath = "//h1[normalize-space()='Transporter']")
    private WebElement transporterHeading;

    @FindBy(xpath = "//button[contains(@class,'header_pbtn') and normalize-space()='Add']")
    private WebElement addButton;

    @FindBy(xpath = "//input[@placeholder='Enter Transporter Name here']")
    private WebElement transporterName;

    @FindBy(xpath = "//input[@placeholder='Enter Reference Id here']")
    private WebElement referenceId;

    @FindBy(xpath = "(//input[@placeholder='Select'])[4]")
    private WebElement gstDropdownInput;

    @FindBy(xpath = "(//li[@class='el-select-dropdown__item']//span[text()='FCM'])[2]")
    private WebElement gstOptionFCM;

    @FindBy(xpath = "//input[@placeholder='Enter GST Percentage here']")
    private WebElement gstPercentage;

    @FindBy(xpath = "(//input[@placeholder='Select'])[5]")
    private WebElement statusDropdownInput;

    @FindBy(xpath = "(//ul[@class='el-scrollbar__view el-select-dropdown__list']//child::li//span[contains(text(),'Active')])[2]")
    private WebElement statusActive;

    @FindBy(xpath = "(//h5[text() ='MANAGER']//following::div//child::div//child::button//child::span)[2]")
    private WebElement managerAddButton;

    @FindBy(xpath = "(//input[@placeholder='Enter'])[1]")
    private WebElement managerName;

    @FindBy(xpath = "(//input[@placeholder='Enter'])[2]")
    private WebElement representativeMobile;

    @FindBy(xpath = "(//input[@placeholder='Enter *'])[1]")
    private WebElement representativeEmail;

    @FindBy(xpath = "//button[@class= 'el-button el-button--primary el-button--small']//child::span")
    private WebElement createButtonInput;

    @FindBy(css = "div.el-message--success[role='alert']")
    private WebElement successToast;

    @FindBy(xpath = "//span[@type='text']")
    private WebElement searchInput;

    @FindBy(xpath = "//input[@placeholder='Enter Transporter Name']")
    private WebElement transporterNamesearch;




    public void selectTransporterFromDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(dashboardDropdown)).click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", transporterModule);
        wait.until(ExpectedConditions.elementToBeClickable(transporterModule)).click();
    }

    public void clickAddTransporter() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    }

    public void enterTransporterDetails(
            String name,
            String refId,
            String gst,
            String gstPerc,
            String status,
            String manager,
            String repMobile,
            String repEmail,
            String create
    ) {
        WebDriverWait wait = new WebDriverWait(driver, 10);


        transporterName.sendKeys(name + "-" + new SimpleDateFormat("HHmmss").format(new Date()));
        referenceId.sendKeys(refId + "_" + UUID.randomUUID());


        gstDropdownInput.click();
        gstOptionFCM.click();

        gstPercentage.sendKeys(gstPerc);


        statusDropdownInput.click();
        statusActive.click();

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", managerAddButton);
        wait.until(ExpectedConditions.elementToBeClickable(managerAddButton));

        managerAddButton.click();
        managerName.sendKeys(manager);
        representativeMobile.sendKeys(repMobile);
        representativeEmail.sendKeys("test" + System.currentTimeMillis() + "@example.com");


        createButtonInput.click();
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


    public void searchTransporter(String transporterName) {
        WebDriverWait wait = new WebDriverWait(driver, 10);


        try {
            wait.until(ExpectedConditions.invisibilityOf(successToast));
        } catch (TimeoutException e) {
            System.out.println("Success popup still visible after 10s, continuing...");
        }
        wait.until(ExpectedConditions.elementToBeClickable(searchInput));


        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchInput);


        searchInput.click();
        transporterNamesearch.sendKeys(transporterName);
        transporterNamesearch.sendKeys(Keys.ENTER);
    }

    public void waitForPageToLoad(WebDriver driver, int timeoutSeconds) {
        new WebDriverWait(driver, timeoutSeconds).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete")
        );
    }


}
