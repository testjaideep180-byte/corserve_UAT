package Pages;


import AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponents {

    @FindBy(xpath = "//div[@class='banner-btn']/a[2]")
    WebElement createAccountCTA;

    @FindBy(css = ".sign-btn")
    WebElement signBtn;                         // Class for Signup is "btn sign-btn"

    @FindBy(css = ".login-btn")
    WebElement loginBtn;

    @FindBy(xpath = "//div[@class='banner-btn']/a[1]")
    WebElement payInvoiceCTA;

    @FindBy(css = ".col-auto")
    WebElement findInvoiceCTA;

    @FindBy(css = "#EnterInvoice")
    WebElement invoiceNumField;

    @FindBy(css = "#basketNavLink")
    WebElement basket;

    public LandingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickCreateAccountCTA() {
        click(createAccountCTA);
    }

    public void clickPayInvoiceCTA() {
        click(payInvoiceCTA);
    }

    public void clickSignUpCTA() {
        click(signBtn);
    }

    public void clickSignInCTA() {
        click(loginBtn);
    }

    public void clickOnBasket() {
        jsClick(basket);
    }


    public void findInvoice(String invoiceNumber) throws InterruptedException {
        scrollToElement(findInvoiceCTA);
        Thread.sleep(1000);
        type(invoiceNumField, invoiceNumber);
        click(findInvoiceCTA);
    }

    public void goTo() {
        driver.get("https://corservdemo.24livehost.com/");
    }
}
