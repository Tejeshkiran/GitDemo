package GlobleProperties.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Extentreports {
    public static ExtentReports reportsConfig()
    {
        //Extentreports, ExtentSparkReporter
        String path  = System.getProperty("user.dir")+"//reports//index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test result");

        //main class which gets information of ExtentSparkReporter
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Tejesh");
        return extent;
    }

}
