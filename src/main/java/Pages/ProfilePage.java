package Pages;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProfilePage extends AbstractComponents {


    @FindBy(css="#dropdownMenuLink")
    WebElement dropdownMenuLink;

    @FindBy(xpath="//ul[@class='dropdown-menu show']/li[1]")
    WebElement profileMenu;

    @FindBy(css="#FirstName")
    WebElement firstNameField;

    @FindBy(css="#LastName")
    WebElement lastNameField;

    @FindBy(css="#Email")
    WebElement emailField;

    @FindBy(xpath="//button[contains(text(),'Update Profile')]")
    WebElement updateButton;

    @FindBy(css=".alert-success")
    WebElement successMessage;

    @FindBy(css=".field-validation-error")
    List<WebElement> errorMessage;




    public ProfilePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickProfileMenu(){
        click(dropdownMenuLink);
        click(profileMenu);
    }

    public void updateProfile(String firstName, String lastName, String email) throws InterruptedException {
        type(firstNameField,firstName);
        type(lastNameField,lastName);
        type(emailField, email);
        scrollToElement(updateButton);
        waitForElementToVisible(updateButton);
        jsClick(updateButton);
    }

    public String getSuccessMessage(){
        return successMessage.getText();
    }

    public void clearProfileDetails(){
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        scrollToElement(updateButton);
        waitForElementToVisible(updateButton);
        jsClick(updateButton);
    }

    public int getErrorMessage(){
        waitForElementToVisible(errorMessage.get(2));
        scrollToElement(errorMessage.get(2));
        return errorMessage.size();
    }


}
