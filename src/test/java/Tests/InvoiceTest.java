package Tests;

import Pages.InvoicePage;
import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InvoiceTest extends BaseTest {
    String InvoiceNum = "CINV-068223";

    @Test
    public void verifyInvoiceSearchFunctionality() {
        InvoicePage invoicePage = new InvoicePage(driver);
        invoicePage.searchInvoice(InvoiceNum, "CUS-006053", "Dots@123");
        Assert.assertEquals(invoicePage.getInvoiceID(), "CINV-068223");
    }

    @Test
    public void verifyInvoicePaymentFunctionality() {
        InvoicePage invoicePage = new InvoicePage(driver);
        invoicePage.searchInvoice(InvoiceNum, "CUS-006053", "Dots@123");
        if(invoicePage.getInvoiceStatus().equals("Unpaid")){
            invoicePage.clickOnPayButton();
        }
        else{
            Assert.assertEquals(invoicePage.getInvoiceStatus(), "Paid");
            Assert.assertFalse(invoicePage.pay.isEnabled(), "Pay button should be disabled for paid invoice");
        }

    }
}