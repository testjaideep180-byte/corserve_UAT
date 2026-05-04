package Pages;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
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

    @FindBy(css=".text-center")
    WebElement pay;

    @FindBy(xpath="(//div[@class='row mx-0 invoice-list-row'])[1]")
    WebElement invoiceCard;

    @FindBy(css="#orderRef")
    WebElement reference;

    @FindBy(css="#orderAmount")
    WebElement amount;

    @FindBy(css=".add-basket-btn")
    List<WebElement> addToBasketBtn;

    @FindBy(css="#clearBasketBtn")
    WebElement clearBasket;

    By invoiceNumber = By.xpath("//label[contains(text(),'Invoice Number')]/following-sibling::div");
    By dueAmount = By.xpath("//label[contains(text(),'Due Amount')]/following-sibling::div");
    By payNowBtn = By.cssSelector(".pay-now-btn");

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

        public void invoiceCardDetails(String customerID, String password ){
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(customerID, password);
            LandingPage landingPage = new LandingPage(driver);
            landingPage.clickPayInvoiceCTA();


        }

        public String getInvoiceNumber(){
            return invoiceCard.findElement(invoiceNumber).getText();
        }
        public String getDueAmount()
        {
            scrollToElement(invoiceCard.findElement(dueAmount));
            return invoiceCard.findElement(dueAmount).getText();
        }
        public void clickPayNowBtn(){
            invoiceCard.findElement(payNowBtn).click();
        }
        public String getReference(){
            return reference.getText();
        }
        public String getAmount(){
            return amount.getText();
        }

        public void goToBasket(){
            LandingPage landingPage = new LandingPage(driver);
            landingPage.clickOnBasket();
            scrollToElement(clearBasket);
            click(clearBasket);
            Alert alert = driver.switchTo().alert();
            alert.accept();

            driver.navigate().back();
        }

        public void clickOnAddToBasketButton(){
            scrollToElement(invoiceCard.findElement(dueAmount));

            for(int i = 1;i<=3;i++){
                addToBasketBtn.get(i).click();

            }
        }



}
