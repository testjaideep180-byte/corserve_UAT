package TestComponents;

import Pages.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseTest {
  public WebDriver driver;
    public WebDriver initializeDriver(){



    ChromeOptions options = new ChromeOptions();
    // ✅ Disable password manager + breach popup
           Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("profile.password_manager_leak_detection", false);

            options.setExperimentalOption("prefs", prefs);

    // ✅ Additional stability
            options.addArguments("--disable-notifications");
            options.addArguments("--incognito");
            driver  = new ChromeDriver(options);
            driver.manage().window().maximize();   // Maximize browser window
            return driver;
    }

    public HashMap<String, String> getNextUnusedData() throws IOException {

        String path = System.getProperty("user.dir") + "//src//test//java//Data//CustomerData.json";

        String jsonContent = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();

        List<HashMap<String, Object>> data =
                mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, Object>>>() {});

        for (HashMap<String, Object> entry : data) {

            Boolean used = (Boolean) entry.get("used");

            if (!used) {
                entry.put("used", true); // mark as used

                // write back to JSON
                mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), data);

                // return only needed fields
                HashMap<String, String> result = new HashMap<>();
                result.put("customerId", (String) entry.get("customerId"));
                result.put("postalCode", (String) entry.get("postalCode"));

                return result;
            }
        }

        throw new RuntimeException("No unused data available");
    }

    public File getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        return file;

    }

    @BeforeMethod
    public LandingPage launchApplication(){
        driver = initializeDriver();
        LandingPage landingPage = new LandingPage(driver);

        landingPage.goTo();
        return landingPage;
    }
    @AfterMethod
    public void closeApplication(){
        driver.close();
    }

}