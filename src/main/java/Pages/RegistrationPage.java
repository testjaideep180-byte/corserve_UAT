package Pages;

import AbstractComponents.AbstractComponents;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Models.User;




public class RegistrationPage extends AbstractComponents {


    @FindBy (id = "FirstName")
    WebElement firstName;
    @FindBy (id = "LastName")
    WebElement lastName;
    @FindBy (id = "Email")
    WebElement email;
    @FindBy (id = "CustomerId")
    WebElement customerId;
    @FindBy (id = "PostalCode")
    WebElement postalCode;
    @FindBy (id = "Password")
    WebElement password;
    @FindBy (id = "VerifyPassword")
    WebElement confirmPassword;
    @FindBy (xpath = "//button[contains(text(),'Sign Up')]")
    WebElement submitBtn;
    @FindBy (css=".alert-success")
    WebElement successMessage;
    By failureMessage = By.cssSelector(".alert-danger");
    By errorMessage = By.cssSelector(".text-danger");



    public RegistrationPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void registerUser(User User){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickCreateAccountCTA();
        type(firstName, User.getFirstName());
         type(lastName,User.getLastName());
         type(email,User.getEmail());
         type(customerId,User.getCustomerId());
         type(postalCode,User.getPostalCode());
         type(password,User.getPassword());
         type(confirmPassword, User.getConfirmPassword());
         jsClick(submitBtn);
    }

    public String successMessage(){
        return waitForElementToVisible(successMessage).getText();
    }
    public String failureMessage(){
        return waitForElementToAppear(failureMessage).getText();
    }
    public void submitEmptyForm(){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickCreateAccountCTA();
        jsClick(submitBtn);
    }

    public int errorMessageCount(){
        waitForElementToAppear(errorMessage);
        return driver.findElements(errorMessage).size();
    }

    public String signUpBtnFunctionality(){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickSignUpCTA();
         return driver.getTitle();

    }
}

