package Tests;

import Models.User;
import Pages.RegistrationPage;
import TestComponents.BaseTest;
import Utils.TestDataUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class RegistrationTest extends BaseTest {


    @Test
    public void registrationSuccess() throws IOException {
        RegistrationPage page = new RegistrationPage(driver);
        Map<String, String> data = getNextUnusedData();
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

    @Test
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

    @Test
    public void emptyFormSubmission(){
        RegistrationPage page = new RegistrationPage(driver);
        page.submitEmptyForm();
        Assert.assertEquals(page.errorMessageCount(),4);
    }

    @Test
    public void verifySigUpBtnFunctionality(){
        RegistrationPage page = new RegistrationPage(driver);
        Assert.assertEquals(page.signUpBtnFunctionality(), "Register | Corserv");
    }


}
