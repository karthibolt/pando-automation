package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class IndentPage {

    private final WebDriver driver;

    public IndentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//*[local-name()='svg' and contains(@class,'svg-icon')])[3]")
    private WebElement dashboardDropdown;

    @FindBy(xpath = "//a[@href='/indents/v2/outbound']")
    private WebElement indentModule;

    @FindBy(xpath = "//h1[normalize-space()='Indent']")
    private WebElement indentHeading;

    @FindBy(xpath = "//button[contains(@class,'header_pbtn') and normalize-space()='Add']")
    private WebElement addIndentButton;


    @FindBy(xpath = "//span[text()='Add']")
    private WebElement depotAddbutton;

    @FindBy(xpath = "//span[text()='Add Source location']")
    private WebElement addSourceLocation;

    @FindBy(xpath = "//input[@placeholder='Search by DEPOT Name, city or ID']")
    private WebElement addSearchButton;


    @FindBy(xpath = "//div[@class='add-block']")
    private WebElement addDropOffButton;


@FindBy(xpath = "//input[@placeholder='Search by Consignee Name, City or ID']")
private WebElement consigneeSearchButton;


    @FindBy(xpath = "(//span[@class='el-checkbox__inner'])[2]")
    private WebElement selectconsigneeButton;

    @FindBy(xpath = "//div[text()='Consignee']")
    private WebElement clickConsigneeButton;

    @FindBy(xpath = "//span[@class='el-radio__inner']")
    private WebElement checkRadioButton;

    @FindBy(xpath = "//input[@placeholder='Enter Indent Number']")
    private WebElement indentNumberField;

    @FindBy(xpath = "//input[@placeholder='Enter Customer Name']")
    private WebElement customerNameField;

    @FindBy(xpath = "//input[@placeholder='Enter Source Location']")
    private WebElement sourceLocationField;

    @FindBy(xpath = "//input[@placeholder='Enter Destination Location']")
    private WebElement destinationLocationField;

    @FindBy(xpath = "//input[@placeholder='Select Mode']")
    private WebElement modeDropdown;

    @FindBy(xpath = "//span[normalize-space()='Road']")
    private WebElement modeRoadOption;

    @FindBy(xpath = "//input[@placeholder='Select Indent Date']")
    private WebElement indentDateField;

    @FindBy(xpath = "//button[@class='el-button el-button--primary el-button--small']//span")
    private WebElement createIndentButton;

    @FindBy(css = "div.el-message--success[role='alert']")
    private WebElement successToast;

    @FindBy(xpath = "//span[@type='text']")
    private WebElement searchBarButton;

    @FindBy(xpath = "//input[@placeholder='Search Indent']")
    private WebElement indentSearchField;


    @FindBy(xpath = "(//input[@placeholder='Select'])[5]")
    private WebElement deliveryTypeDropdown;

    @FindBy(xpath = "//li[@class='el-select-dropdown__item']//child::span[contains(text(), 'FTL')]")
    private WebElement ftlOption;

    @FindBy(xpath = "(//input[@placeholder='Select'])[6]")
    private WebElement vehicleTypeDropdown;

    @FindBy(xpath = "(//ul[contains(@class,'el-scrollbar__view')])[8]//li[1]")
    private WebElement firstVehicleOption;

    @FindBy(xpath = "//input[@placeholder='INR']")
    private WebElement inrInput;

    @FindBy(xpath = "//div[contains(.,'TRANSPORTER') and contains(@style,'flex')]")
    private WebElement transporterDropdown;

    @FindBy(xpath = "(//span[@class='el-radio__inner'])[1]")
    private WebElement firstTransporterCheckbox;


    @FindBy(xpath = "(//span[normalize-space(text())='Submit'])[3]")
    private WebElement submitbutton;

    @FindBy(xpath = "//span[normalize-space()='Indent']")
    private WebElement indentButton;

    @FindBy(xpath = "//div[contains(@class,'el-message') and contains(@class,'success')]")
    private WebElement indentToast;

    @FindBy(xpath = "//span[normalize-space(text())='Filter']")
    private WebElement filterButton;


    @FindBy(xpath = "//div[@class='cardlist-dropdown']")
    private WebElement dropdowndepot;


    @FindBy(css = "input.el-input__inner")
    private WebElement enterdepotvalue;
@FindBy(xpath = "//span[text()='APPLY']")
private WebElement applyButton;

    @FindBy(css = "div.el-message--success[role='alert']")
    private WebElement toastMessage;


    @FindBy(xpath = "//div[@class='shipment-id']")
    private WebElement clickindentvalue;

    @FindBy(xpath = "//div[text()='Assign Truck']")
    private WebElement assignTruck;

    @FindBy(xpath = "(//input[@placeholder='XX00'])[2]")
    private WebElement vehicleNumber;

    @FindBy(xpath = "(//input[@placeholder='0000'])[2]")
    private WebElement vehicleNumber1;

    @FindBy(xpath = "//input[@placeholder='99XXXXXXXX']")
    private WebElement drivernumber;
    @FindBy(xpath = "//input[@placeholder='Enter Driver Name']")
    private WebElement driverName;
    @FindBy(xpath = "//input[@placeholder='Enter Vehicle Length']")
    private WebElement vehicleLength;
    @FindBy(xpath = "//input[@placeholder='Enter Vehicle Width']")
    private WebElement vehicleWidth;
    @FindBy(xpath = "//input[@placeholder='Enter Vehicle height']")
    private WebElement vehicleHeight;
    @FindBy(xpath = "(//span[normalize-space(text())='Submit'])[1]")
    private WebElement submit;
    @FindBy(xpath = "//span[text()='Reported']")
    private WebElement report;
    @FindBy(xpath = "//span[normalize-space()='Yes']")
    private WebElement yesbutton;


    public void selectIndentFromDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(dashboardDropdown)).click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", indentModule);
        wait.until(ExpectedConditions.elementToBeClickable(indentModule)).click();
    }

    public void clickAddIndent() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(addIndentButton)).click();
        addSourceLocation.click();
    }

    public void clickSearchDepotname(String depotName) {
        WebDriverWait wait = new WebDriverWait(driver, 15);

        wait.until(ExpectedConditions.visibilityOf(addSearchButton));
        addSearchButton.sendKeys(depotName);


        String radioXpath = "//tr[.//td[contains(@class,'el-table_2_column_6')]//span[contains(text(),'" + depotName + "')]]//span[@class='el-radio__inner']";

        System.out.println("Clicking radio button for depot using XPath: " + radioXpath);


        WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(radioXpath)));


        radioButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(depotAddbutton)).click();
    }

public void clickAddDropOffbutton(String createdConsigneeName) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    WebDriverWait wait = new WebDriverWait(driver, 10);
    js.executeScript("arguments[0].click();", addDropOffButton);


    js.executeScript("arguments[0].click();", clickConsigneeButton);

    consigneeSearchButton.sendKeys(createdConsigneeName);


    wait.until(ExpectedConditions.elementToBeClickable(selectconsigneeButton));
    js.executeScript("arguments[0].click();", selectconsigneeButton);

    By switchToOpenIndentPopup =
            By.xpath("//span[normalize-space()='Switch to Open Indent']");

    WebElement popupBtn = wait.until(
            ExpectedConditions.elementToBeClickable(switchToOpenIndentPopup)
    );
    js.executeScript("arguments[0].click();", popupBtn);

    wait.until(ExpectedConditions.elementToBeClickable(depotAddbutton));
    js.executeScript("arguments[0].click();", depotAddbutton);


}


    public void enterIndentDetails() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.elementToBeClickable(deliveryTypeDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(ftlOption)).click();

        wait.until(ExpectedConditions.elementToBeClickable(vehicleTypeDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(firstVehicleOption)).click();

        wait.until(ExpectedConditions.visibilityOf(inrInput)).clear();
        inrInput.sendKeys("1000");


        wait.until(ExpectedConditions.elementToBeClickable(transporterDropdown)).click();
        js.executeScript("arguments[0].click();", firstTransporterCheckbox);

        wait.until(ExpectedConditions.elementToBeClickable(indentButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(submitbutton)).click();

        wait.until(ExpectedConditions.visibilityOf(indentToast));
        String toastText = indentToast.getText();
        System.out.println("Toast Message: " + toastText);

        String indentId = toastText.replaceAll("\\D+", "");
        System.out.println("Indent ID: " + indentId);


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

    public void searchIndent(String depotName) {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(filterButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(dropdowndepot)).click();

        List<WebElement> inputs = wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(
                        By.xpath("//div[contains(@class,'el-select')]//input[contains(@class,'el-input__inner')]")
                ));
        WebElement input = inputs.get(5);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].focus();" +
                        "arguments[0].removeAttribute('readonly');" +
                        "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                        "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                input, depotName
        );
        By firstOptionLocator = By.xpath("//ul[contains(@class,'el-select-dropdown__list')]//li[contains(@class,'el-select-dropdown__item') and contains(span/text(), '" + depotName + "')]");

        WebElement firstOption = wait.until(ExpectedConditions.elementToBeClickable(firstOptionLocator));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstOption);
        wait.until(ExpectedConditions.elementToBeClickable(applyButton)).click();
    }

    public void waitForPageToLoad(WebDriver driver, int timeoutSeconds) {
        new WebDriverWait(driver, timeoutSeconds).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );
    }

    public void enterAssignTruck() {
        WebDriverWait wait = new WebDriverWait(driver, 10);


        wait.until(ExpectedConditions.elementToBeClickable(clickindentvalue)).click();


        wait.until(ExpectedConditions.elementToBeClickable(assignTruck)).click();


        wait.until(ExpectedConditions.visibilityOf(vehicleNumber)).sendKeys("TN11");
        wait.until(ExpectedConditions.visibilityOf(vehicleNumber1)).sendKeys("4447");


        wait.until(ExpectedConditions.visibilityOf(drivernumber)).sendKeys("9940423989");
        wait.until(ExpectedConditions.visibilityOf(driverName)).sendKeys("jack");


        wait.until(ExpectedConditions.visibilityOf(vehicleLength)).sendKeys("10");
        wait.until(ExpectedConditions.visibilityOf(vehicleWidth)).sendKeys("10");
        wait.until(ExpectedConditions.visibilityOf(vehicleHeight)).sendKeys("10");


        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submit);

        wait.until(ExpectedConditions.elementToBeClickable(submit)).click();

        try {
            WebElement toast = wait.until(ExpectedConditions.visibilityOf(toastMessage));
            System.out.println("Toast message appeared: " + toast.getText());
        } catch (TimeoutException e) {
            System.out.println("Toast message did not appear");
        }

    }

    public void enterReportTruck() {
        WebDriverWait wait = new WebDriverWait(driver, 10);


        try {
            wait.until(ExpectedConditions.invisibilityOf(toastMessage));
        } catch (TimeoutException e) {
            System.out.println("Success popup still visible after 10s, continuing...");
        }

        wait.until(ExpectedConditions.elementToBeClickable(clickindentvalue)).click();

        wait.until(ExpectedConditions.elementToBeClickable(report)).click();
        wait.until(ExpectedConditions.elementToBeClickable(yesbutton)).click();

        WebElement reportToast = wait.until(ExpectedConditions.visibilityOf(toastMessage));
        System.out.println("Toast Message after report: " + reportToast.getText());


        wait.until(ExpectedConditions.elementToBeClickable(clickindentvalue)).click();


        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='TRUCKIN']"))).click();
        try {
            wait.until(ExpectedConditions.invisibilityOf(toastMessage)); // wait for toast to disappear
        } catch (TimeoutException e) {
            System.out.println("Toast still visible, trying to click submit anyway...");
        }

        wait.until(ExpectedConditions.elementToBeClickable(submit)).click();

        try {

            WebElement reportToast1 = wait.until(ExpectedConditions.visibilityOf(toastMessage));
            System.out.println("Toast Message after report: " + reportToast1.getText());
        } catch (TimeoutException e) {
            System.out.println("Toast message not visible, proceeding...");
        }

    }
    }
