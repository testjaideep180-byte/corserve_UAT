package Pages;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class InvoicePage extends AbstractComponents {
    WebDriver driver;


    @FindBy(xpath = "//label[contains(text(),'Invoice Number')]/following-sibling::div")
    WebElement invoiceNum;

    @FindBy(xpath = "//label[contains(text(),'Payment Status')]/following-sibling::div")
    WebElement paymentStatus;

    @FindBy(css = ".text-center")
    WebElement pay;

    @FindBy(xpath = "(//div[@class='row mx-0 invoice-list-row'])[1]")
    WebElement invoiceCard;

    @FindBy(css = ".invoice-list-row")
    List<WebElement> invoiceCardsList;

    @FindBy(css = "#orderRef")
    WebElement reference;

    @FindBy(css = "#orderAmount")
    WebElement amount;

    @FindBy(css = "#basketBarTotal")
    WebElement cartBarTotalAmount;

    @FindBy(css = "#basketBarCount")
    WebElement cartBarInvoiceCount;

    @FindBy(css = ".btn-sm")
    WebElement checkoutButton;

    @FindBy(css = ".inner-banner-cont")
    WebElement invoiceBanner;

    By invoiceNumber = By.xpath(".//div[contains(text(),'CINV')]");
    By dueAmount = By.xpath("//label[contains(text(),'Due Amount')]/following-sibling::div");
    By payNowBtn = By.cssSelector(".pay-now-btn");
    By addToBasketBtn = By.cssSelector(".add-basket-btn");
    By viewInvoice = By.cssSelector(".fa-eye");

        public InvoicePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void searchInvoice(String invoiceNumber, String customerID, String password) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(customerID, password);
        LandingPage landingPage = new LandingPage(driver);
        landingPage.findInvoice(invoiceNumber);

    }

    public String getInvoiceID() {
        waitForElementToVisible(invoiceNum);
        return invoiceNum.getText();
    }

    public String getInvoiceStatus() {
        waitForElementToVisible(paymentStatus);
        return paymentStatus.getText();
    }

    public void clickOnPayButton() {
        scrollToElement(pay);
        click(pay);

    }

    public void invoiceCardDetails(String customerID, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(customerID, password);
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickPayInvoiceCTA();
    }

    public String getInvoiceNumber() {
        return invoiceCard.findElement(invoiceNumber).getText();
    }

    public String getDueAmount() {
        scrollToElement(invoiceCard.findElement(dueAmount));
        return invoiceCard.findElement(dueAmount).getText().replaceAll("£","");

    }

    public void clickPayNowBtn() {
            scrollToElement(driver.findElement(payNowBtn));
            jsClick(invoiceCard.findElement(payNowBtn));
    }

    public String getReference() {
        return reference.getText();
    }

    public String getAmount() {
        return amount.getText().replaceAll("£", "");
    }


    public void clickOnAddToBasketButton() throws InterruptedException {
        scrollToElement(invoiceCard);
        Thread.sleep(2000);
        waitForElementToVisible(invoiceCard);
        int count = Math.min(driver.findElements(addToBasketBtn).size(), 3);
        for (int i = 0; i < count; i++) {
            List<WebElement> elements = driver.findElements(addToBasketBtn);
            WebElement element = elements.get(i); // always first available
            waitForElementToBeClickable(element);
            jsClick(element);
        }
    }

    public double getDueAmountsOfCartAddedInvoices() {
        return addAmounts(dueAmount,3);
    }

    public double getTotalDueAmountsOfCartAddedInvoices() {
        return Double.parseDouble(cartBarTotalAmount.getText().replaceAll("£", ""));
    }


    public String getTotalInvoiceCount() {
        return cartBarInvoiceCount.getText();
    }

    public void clickCheckoutButton() {
        click(checkoutButton);

    }

    public List<WebElement> getAllInvoicesList() {
        return invoiceCardsList;
    }



    private WebElement getInvoiceByNumber(String invoiceNum) {

        for (WebElement card : getAllInvoicesList()) {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView({block: 'center'});", card);
                     String text = card.findElement(invoiceNumber).getText().trim();
            if (text.equals(invoiceNum)) {
                return card; // return the matched invoice card
            }
        }

        throw new RuntimeException("Invoice not found: " + invoiceNum);
    }

     public void clickOnViewInvoiceDetails(String invoiceNum) throws InterruptedException {
          WebElement prod = getInvoiceByNumber(invoiceNum);
          Thread.sleep(2000);
          prod.findElement(viewInvoice).click();
     }

     public boolean isBannerDisplayed(){
        return invoiceBanner.isDisplayed();
     }
    }


