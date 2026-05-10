package TestComponents;

import Pages.LandingPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    public WebDriver driver;

    public WebDriver initializeDriver() {

        String browserName = System.getProperty("browser", "chrome");

        if (browserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            if(browserName.contains("headless")){
                options.addArguments("--headless");
            }
            driver = new ChromeDriver(options);

        } else if (browserName.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--disable-notifications");
            driver = new FirefoxDriver(options);

        } else if (browserName.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--disable-notifications");
            driver = new EdgeDriver(options);
        }

        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        return driver;
    }

    public File getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(
                System.getProperty("user.dir")
                        + "//reports//"
                        + testCaseName
                        + ".png");
        FileUtils.copyFile(source, file);
        return file;
    }

    @BeforeMethod
    public LandingPage launchApplication() {
        driver = initializeDriver();
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    @AfterMethod
    public void closeApplication() {
        if (driver != null) {
            driver.quit();
        }
    }
}