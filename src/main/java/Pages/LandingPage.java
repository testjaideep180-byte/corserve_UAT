package Pages;


import AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponents {
    WebDriver driver;

    @FindBy(xpath = "//div[@class='banner-btn']/a[2]")
    WebElement createAccountCTA;

    @FindBy(css = ".sign-btn")
    WebElement signBtn;                         // Class for Signup is "btn sign-btn"

    @FindBy(css = ".login-btn")
    WebElement loginBtn;

    @FindBy(xpath = "//div[@class='banner-btn']/a[1]")
    WebElement payInvoiceCTA;

    @FindBy(xpath = "//button[contains(text(),'Find Invoice')]")
    WebElement findInvoiceCTA;

    @FindBy(css = "#EnterInvoice")
    WebElement invoiceNumField;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
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

    public void findInvoice(String invoiceNumber) {
        type(invoiceNumField, invoiceNumber);
        click(findInvoiceCTA);
    }

    public void goTo() {
        driver.get("https://corservdemo.24livehost.com/");
    }
}
