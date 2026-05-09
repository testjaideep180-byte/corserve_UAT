package Tests;

import Pages.CartPage;
import Pages.InvoicePage;
import Pages.LoginPage;
import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    LoginPage loginPage;
    CartPage cartPage;
    InvoicePage invoicePage;

    @BeforeMethod
    public void setupPage() {
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
        invoicePage = new InvoicePage(driver);
    }

    @Test(groups = "Regression",priority = 1)
    public void verifyProceedToCheckoutFunctionality() throws InterruptedException {
       loginPage.login("CUS-006053", "Dots@123");
       cartPage.goToBasket();
       cartPage.addInvoiceToCart();
       String totalPrice = cartPage.getTotalAmount();
       cartPage.goToCheckout();
       Assert.assertEquals(invoicePage.getAmount(),totalPrice);
    }

    @Test(groups = "Regression",priority = 2)
    public void verifyRemoveInvoiceFromCartFunctionality() throws InterruptedException {
        loginPage.login("CUS-006053", "Dots@123");
        cartPage.goToBasket();
        cartPage.addInvoiceToCart();
        int invoiceCount = cartPage.invoiceCount();
        cartPage.removeInvoice();
        Assert.assertEquals(cartPage.invoiceCount(), invoiceCount-1);

    }

    @Test(groups = "Regression",priority = 3)
    public void verifyTotalInvoiceAmount() throws InterruptedException {
        loginPage.login("CUS-006053", "Dots@123");
        cartPage.goToBasket();
        cartPage.addInvoiceToCart();
        double getTotalAmount = Double.parseDouble(cartPage.getTotalAmount());
        Assert.assertEquals(getTotalAmount, cartPage.invoiceTotalAmount());;

    }

    @Test(groups = "Regression",priority = 4)
    public void verifyAddMoreInvoiceCTAFunctionality(){
        loginPage.login("CUS-006053", "Dots@123");
        cartPage.goToBasket();
        cartPage.clickAddMoreInvoicesCTA();
        Assert.assertTrue(invoicePage.isBannerDisplayed(), "User is navigated to invoice listing page");
    }
}
