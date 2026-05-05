package Pages;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponents {
    WebDriver driver;

    @FindBy(css = ".basket-item-row")
    List<WebElement> cartItems;

    @FindBy(css = "#clearBasketBtn")
    WebElement clearBasket;

    @FindBy(css = ".mt-3")
    WebElement viewInvoiceCTA;

    @FindBy(css = ".btn-primary")
    WebElement proceedToCheckout;

    @FindBy(css = ".btn-blue")
    WebElement addMoreInvoiceButton;

    @FindBy(xpath = "//div[@class='basket-item-row']/div[2]/button")
    WebElement removeButton;

    @FindBy(css = ".basket-item-row")
    WebElement invoiceRecords;

    @FindBy(css = ".basket-item-amount")
    WebElement invoiceAmount;

    @FindBy(css = "#basketTotal")
    WebElement invoiceTotalAmount;

    public CartPage(WebDriver driver){
        super(driver);
        this.driver =driver;
        PageFactory.initElements(driver, this);

    }

    public void goToBasket() {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnBasket();
        if(!cartItems.isEmpty()){
            scrollToElement(clearBasket);
        }
        System.out.println("Basket is empty");

    }

    public void clearCartBasket() {

        if (!cartItems.isEmpty()) {
            waitForElementToVisible(clearBasket);
            click(clearBasket);
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }
        System.out.println("Basket is empty");
    }

}
