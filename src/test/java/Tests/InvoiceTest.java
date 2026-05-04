package Tests;

import Pages.InvoicePage;
import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class InvoiceTest extends BaseTest {

    @Test(dataProvider = "testData")
    public void verifyInvoiceSearchFunctionality(String invoiceNumber, String customerID, String password ) {
        InvoicePage invoicePage = new InvoicePage(driver);
        invoicePage.searchInvoice(invoiceNumber, customerID, password);
        if (customerID.equals("CUS-006053")) {
            Assert.assertEquals(invoicePage.getInvoiceID(), "CINV-068223");
        } else {
            Assert.assertEquals(invoicePage.getInvoiceID(), "CINV-126039");
        }
    }

    @Test(dataProvider = "testData")
    public void verifyInvoicePaymentFunctionality(String invoiceNumber, String customerID, String password ) {
        InvoicePage invoicePage = new InvoicePage(driver);
        invoicePage.searchInvoice(invoiceNumber, customerID, password);
        if(invoicePage.getInvoiceStatus().equals("Unpaid")){
            invoicePage.clickOnPayButton();
        }
        else{
            Assert.assertEquals(invoicePage.getInvoiceStatus(), "Paid");
        }
    }

    @Test
    public void verifyInvoiceCardFunctionality(){
        InvoicePage invoicePage = new InvoicePage(driver);
        invoicePage.invoiceCardDetails("CUS-006053", "Dots@123");
        String dueAmount = invoicePage.getDueAmount();
       String invoiceNumber = invoicePage.getInvoiceNumber();
       invoicePage.clickPayNowBtn();
       Assert.assertEquals(dueAmount, invoicePage.getAmount(), "Amount and Due Amount matched");
       Assert.assertTrue(invoicePage.getReference().contains(invoiceNumber), "Amount and Due Amount matched");

    }


    @DataProvider(name = "testData")
    public Object[][] getData(){
        return new Object[][]{
                {"CINV-068223","CUS-006053", "Dots@123"},
                {"CINV-126039","CUS-003692", "Test@123"},
        };
    }

}