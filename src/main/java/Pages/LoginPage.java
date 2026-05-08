package Pages;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractComponents {


    @FindBy(id="CustomerId")
    WebElement customerIDField;
    @FindBy(id="Password")
    WebElement passwordField;
    @FindBy(css="[type='submit']")
    WebElement loginBtn;
    @FindBy(css="a[id='dropdownMenuLink']")
    WebElement dropdownMenu;
    @FindBy(css=".alert-danger")
    WebElement failureMessage;
    @FindBy(css=".invoice-list-row")
    WebElement invoiceList;
    @FindBy(css=".col-lg-8")
    WebElement invoiceDetails;

    By errorMessage = By.cssSelector(".text-danger");



    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void login(String customerID, String password) {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickSignInCTA();
        type(customerIDField, customerID);
        type(passwordField, password);
        click(loginBtn);
    }

    public void loginWithPayInvoiceCTA(String customerID, String password) {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickPayInvoiceCTA();
        type(customerIDField, customerID);
        type(passwordField, password);
        click(loginBtn);
    }
    public String validateLogin(){
        waitForElementToVisible(dropdownMenu);
        return dropdownMenu.getText();
    }

   public String invalidCredentials(){
       waitForElementToVisible(failureMessage);
      return failureMessage.getText();
    }

    public int loginWithoutCredentials(){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickSignInCTA();
        click(loginBtn);
        waitForElementToAppear(errorMessage);
        return driver.findElements(errorMessage).size();
    }

    public String customerIDFormat(){
        waitForElementToAppear(errorMessage);
        return driver.findElement(errorMessage).getText();
    }

    public boolean redirectionToInvoiceList(){
        waitForElementToVisible(invoiceList);
        return invoiceList.isDisplayed();
    }

    public void findInvoice(String customerID, String password){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.findInvoice("CINV-068835");
        type(customerIDField, customerID);
        type(passwordField, password);
        click(loginBtn);

    }

    public boolean invoiceDetails(){
        waitForElementToVisible(invoiceDetails);
        return invoiceDetails.isDisplayed();
    }
}
