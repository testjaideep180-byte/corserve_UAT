package Tests;

import Models.User;
import Pages.InvoicePage;
import Pages.LoginPage;
import Pages.RegistrationPage;
import TestComponents.BaseTest;
import TestComponents.DataUtils;
import Utils.TestDataUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class RegistrationTest extends BaseTest {

    RegistrationPage page;
    DataUtils utils;

    @BeforeMethod
    public void setupPage() {
         page = new RegistrationPage(driver);
         utils = new DataUtils();

    }

    @Test(groups = "Regression",priority = 1)
    public void registrationSuccess() throws IOException {
        RegistrationPage page = new RegistrationPage(driver);
        Map<String, String> data = utils.getNextUnusedData();
        String customerID = data.get("customerId");
        String postalCode = data.get("postalCode");
        String emailID = TestDataUtil.generateEmail();
        User user = new User(

                "John",
                "Doe",
                emailID,
                customerID,
                postalCode,
                "Password@123",
                "Password@123"
        );
        page.registerUser(user);
        Assert.assertEquals(page.successMessage(), "Registration successful! Please login with your credentials.");
    }

    @Test(groups = "Regression",priority = 2)
    public void registrationFailure() {
        RegistrationPage page = new RegistrationPage(driver);
        User user = new User(
                "John",
                "Doe",
                "test34543@yopmail.com",
                "CUS-873247",
                "TR3 7PL",
                "Password@123",
                "Password@123"
        );
        page.registerUser(user);
        Assert.assertEquals(page.failureMessage(), "Customer ID and Postal Code do not match.");
    }

    @Test(groups = "Regression")
    public void emptyFormSubmission(){
        RegistrationPage page = new RegistrationPage(driver);
        page.submitEmptyForm();
        Assert.assertEquals(page.errorMessageCount(),7);
    }

    @Test(groups = "Regression")
    public void verifySigUpBtnFunctionality(){
        RegistrationPage page = new RegistrationPage(driver);
        Assert.assertEquals(page.signUpBtnFunctionality(), "Register | Corserv");
    }


}
