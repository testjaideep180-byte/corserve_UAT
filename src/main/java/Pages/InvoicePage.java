package Pages;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InvoicePage extends AbstractComponents {
    WebDriver driver;

    @FindBy(css=".pay-now-btn")
    List<WebElement> payNow;

    @FindBy(css=".add-basket-btn")
    List<WebElement> addToCart;

    @FindBy(css=".fa-eye")
    List<WebElement> viewInvoice;

    @FindBy(xpath = "//label[contains(text(),'Invoice Number')]/following-sibling::div")
    WebElement invoiceNum;

    @FindBy(xpath = "//label[contains(text(),'Payment Status')]/following-sibling::div")
    WebElement paymentStatus;

    public @FindBy(css="button[type='submit']")
    WebElement pay;

    public InvoicePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

        public void searchInvoice(String invoiceNumber, String customerID, String password ) {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(customerID, password);
            LandingPage landingPage = new LandingPage(driver);
            landingPage.findInvoice(invoiceNumber);

        }
        public String getInvoiceID(){
        waitForElementToVisible(invoiceNum);
        return invoiceNum.getText();
    }

        public String getInvoiceStatus(){
        waitForElementToVisible(paymentStatus);
        return paymentStatus.getText();
    }

        public void clickOnPayButton(){
            scrollToElement(pay);
            waitForElementToBeClickable(pay);
            click(pay);


        }
}
