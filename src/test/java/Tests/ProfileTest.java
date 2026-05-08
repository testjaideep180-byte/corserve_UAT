package Tests;

import Pages.LoginPage;
import Pages.ProfilePage;
import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProfileTest extends BaseTest {

    ProfilePage profilePage;
    LoginPage loginPage;

    @BeforeMethod
    public void setupPage() {
        loginPage = new LoginPage(driver);
        profilePage  = new ProfilePage(driver);
    }

    @Test
    public void verifyUpdateProfileFunctionality(){
        loginPage.login("CUS-006053", "Dots@123");
        profilePage.clickProfileMenu();
        profilePage.updateProfile("John", "Doe","test331@yopmail.com");
        Assert.assertEquals(profilePage.getSuccessMessage(), "Profile updated successfully!");
    }

    @Test
    public void verifyUpdateProfileFunctionalityWithoutDetails(){
        loginPage.login("CUS-006053", "Dots@123");
        profilePage.clickProfileMenu();
        profilePage.clearProfileDetails();
        Assert.assertEquals(profilePage.getErrorMessage(), 3);
    }
}
