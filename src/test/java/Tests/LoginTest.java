package Tests;

import Pages.LoginPage;
import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {


    @Test
    public void verifyLoginFunctionality(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("CUS-006053", "Dots@123");
        Assert.assertEquals(loginPage.validateLogin(), "CUS-006053");
    }
    @Test
    public void verifyLoginWithInvalidCredentialsFunctionality(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("CUS-006051", "Dots@121");
        Assert.assertEquals(loginPage.invalidCredentials(), "Invalid credentials");
    }
    @Test
    public void verifyLoginWithoutCredentials(){
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertEquals(loginPage.loginWithoutCredentials(), 2);
    }
    @Test
    public void verifyLoginWithInvalidCustomerIDFormat(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("CUS006051", "Dots@121");
        Assert.assertEquals(loginPage.customerIDFormat(), "Customer ID must be in format CUS-XXXXXX (e.g., CUS-002150)");
    }
    @Test
    public void verifyRedirectionToInvoiceList(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithPayInvoiceCTA("CUS-006053", "Dots@123");
        Assert.assertTrue(loginPage.redirectionToInvoiceList(), "INVOICE LIST is Displayed");
    }
    @Test
    public void verifyFindInvoice(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.findInvoice("CUS-006053", "Dots@123");
        Assert.assertTrue(loginPage.invoiceDetails(), "INVOICE Details are Displayed");
    }
}
