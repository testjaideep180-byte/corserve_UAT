package Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {
public static ExtentReports getReportsObject(){
    String path = System.getProperty("user.dir")+"/Reports/index.html";
    ExtentSparkReporter reporter = new ExtentSparkReporter(path);
    reporter.config().setReportName("Conserve_UAT Automation Report");
    reporter.config().setDocumentTitle("Test Results");


    ExtentReports extentReports = new ExtentReports();
    extentReports.attachReporter(reporter);
    extentReports.setSystemInfo("Tester", "Test Jaideep");
    return extentReports;


}
}
