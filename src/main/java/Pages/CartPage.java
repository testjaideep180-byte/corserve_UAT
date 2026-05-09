package Pages;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponents {


    @FindBy(css = ".basket-item-row")
    List<WebElement> cartItems;

    @FindBy(css = "#clearBasketBtn")
    WebElement clearBasket;

    @FindBy(css = ".mt-3")
    WebElement viewInvoiceCTA;

    @FindBy(css = ".btn-blue")
    WebElement addMoreInvoiceButton;

    @FindBy(xpath = "(//button[@class='basket-remove-btn'])[1]")
    WebElement removeButton;

    @FindBy(css = "#basketTotal")
    WebElement invoiceTotalAmount;

    By  invoiceAmount = By.cssSelector(".basket-item-amount");
    By  proceedToCheckout = By.cssSelector(".btn-primary");

    public CartPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);

    }

    public void goToBasket() {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnBasket();
    }


    public void scrollToClearBasket() {
        if (!cartItems.isEmpty()) {
                scrollToElement(clearBasket);
            }
        else {
                System.out.println("Basket is empty");
            }
        }

    public void clearCartBasket() {

        if (!cartItems.isEmpty()) {
            waitForElementToVisible(clearBasket);
            jsClick(clearBasket);
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }
        else{
            System.out.println("Basket is empty");
        }
    }


    public void addInvoiceToCart() throws InterruptedException {

        if(cartItems.isEmpty()) {
            scrollToElement(viewInvoiceCTA);
            jsClick(viewInvoiceCTA);
            InvoicePage invoicePage = new InvoicePage(driver);
            invoicePage.clickOnAddToBasketButton();
            driver.navigate().back();
            driver.navigate().refresh();
        }
        else{
            System.out.println("Invoices are present in Cart");
        }
    }


    public void goToCheckout() throws InterruptedException {
        scrollToElement(driver.findElement(proceedToCheckout));
        waitForElementToBeClickable(driver.findElement(proceedToCheckout));
        jsClick(driver.findElement(proceedToCheckout));
    }

    public String getTotalAmount(){
        scrollToElement(invoiceTotalAmount);
        waitForElementToVisible(invoiceTotalAmount);
        return invoiceTotalAmount.getText().replaceAll("[^0-9.]", "");
    }

    public void removeInvoice(){
        waitForElementToVisible(removeButton);
        click(removeButton);
    }

    public int invoiceCount(){
        driver.navigate().refresh();
        waitVisibilityOfAllElements(cartItems);
        return cartItems.size();
    }

    public double invoiceTotalAmount(){
        return addAmounts(invoiceAmount,driver.findElements(invoiceAmount).size());

    }

    public void clickAddMoreInvoicesCTA(){
        waitForElementToVisible(addMoreInvoiceButton);
        jsClick(addMoreInvoiceButton);
    }


}
