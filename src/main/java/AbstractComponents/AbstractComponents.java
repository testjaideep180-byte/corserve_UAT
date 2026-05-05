package AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponents {

    WebDriver driver;

    public AbstractComponents(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementToAppear(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementToVisible(WebElement FindBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(FindBy));
        return FindBy;
    }
    public void waitForElementToBeClickable(WebElement FindBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(FindBy));
    }

    public void scrollToElement(WebElement FindBy) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", FindBy);
    }

    public void scrollToCoordinates(int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(arguments[0], arguments[1]);", x, y);
    }

    public void click(WebElement element) {
        waitForElementToVisible(element).click();
    }



    public void type(WebElement element, String text) {
        WebElement el = waitForElementToVisible(element);
        el.clear();
        el.sendKeys(text);
    }

    public void getcoodrinates(){
        WebElement element = driver.findElement(By.xpath("(//div[@class='row mx-0 invoice-list-row'])[4]"));
        int xCoordinate = element.getLocation().getX();
        int yCoordinate = element.getLocation().getY();

        System.out.println("X Coordinate: " + xCoordinate);
        System.out.println("Y Coordinate: " + yCoordinate);
    }

}