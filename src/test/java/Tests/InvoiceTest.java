package Tests;

import Pages.CartPage;
import Pages.InvoicePage;
import Pages.LoginPage;
import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class InvoiceTest extends BaseTest {

    String invoiceID = "CINV-097378";
    LoginPage loginPage;
    InvoicePage invoicePage;

    @BeforeMethod
    public void setupPage() {
        loginPage = new LoginPage(driver);
        invoicePage = new InvoicePage(driver);
    }

    @Test(dataProvider = "testData")
    public void verifyInvoiceSearchFunctionality(String invoiceNumber, String customerID, String password ) {
        invoicePage.searchInvoice(invoiceNumber, customerID, password);
        if (customerID.equals("CUS-006053")) {
            Assert.assertEquals(invoicePage.getInvoiceID(), "CINV-068223");
        } else {
            Assert.assertEquals(invoicePage.getInvoiceID(), "CINV-126039");
        }
    }

    @Test(dataProvider = "testData") /*First Search a Invoice then click on the Payment button if it is
    unpaid and it is paid then check the payment status id paid*/
    public void verifyInvoicePaymentFunctionality(String invoiceNumber, String customerID, String password ) {
        invoicePage.searchInvoice(invoiceNumber, customerID, password);
        if(invoicePage.getInvoiceStatus().equals("Unpaid")){
            invoicePage.clickOnPayButton();
        }
        else{
            Assert.assertEquals(invoicePage.getInvoiceStatus(), "Paid");
        }
    }

    @Test/* First go to the all invoice list then pick first Invoice click on the Pay button*/
    public void verifyInvoiceCardPaymentFunctionality(){
        invoicePage.invoiceCardDetails("CUS-006053", "Dots@123");
        String dueAmount = invoicePage.getDueAmount();
        String invoiceNumber = invoicePage.getInvoiceNumber();
        invoicePage.clickPayNowBtn();
        Assert.assertEquals(dueAmount, invoicePage.getAmount(), "Amount and Due Amount matched");
        Assert.assertTrue(invoicePage.getReference().contains(invoiceNumber), "Invoice number do not matched");

    }

    @Test
    public void verifyCartClearFunctionality()  {
        LoginPage loginPage = new LoginPage(driver);
        CartPage cartPage = new CartPage(driver);
        cartPage.goToBasket();
        cartPage.scrollToClearBasket();
        cartPage.clearCartBasket();
    }

    @Test(dependsOnMethods = "verifyCartClearFunctionality")
        public void verifyInvoiceCardCartFunctionality() throws InterruptedException {
        invoicePage.invoiceCardDetails("CUS-006053", "Dots@123");
        invoicePage.clickOnAddToBasketButton();
        Assert.assertEquals(invoicePage.getDueAmountsOfCartAddedInvoices(), invoicePage.getTotalDueAmountsOfCartAddedInvoices(), "Amount Matches");
        Assert.assertEquals(invoicePage.getTotalInvoiceCount(), "3 invoices");
        invoicePage.clickCheckoutButton();
    }

    @Test
    public void verifyViewInvoiceDetailsFunctionality(){
        invoicePage.invoiceCardDetails("CUS-006053", "Dots@123");
        invoicePage.clickOnViewInvoiceDetails(invoiceID);
        Assert.assertEquals(invoicePage.getInvoiceID(), invoiceID);

    }


    @DataProvider(name = "testData")
    public Object[][] getData(){
        return new Object[][]{
                {"CINV-068223","CUS-006053", "Dots@123"},
                {"CINV-126039","CUS-003692", "Test@123"},
        };
    }

}