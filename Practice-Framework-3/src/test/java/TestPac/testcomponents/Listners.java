package TestPac.testcomponents;

import GlobleProperties.Resources.Extentreports;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class Listners extends BaseTest implements ITestListener {
    //com.aventstack.extentreports.ExtentReports extent =ExtentReports.reportsConfig();
    ExtentReports extent = Extentreports.reportsConfig();
    ExtentTest test;
    WebDriver driver;
    ThreadLocal<ExtentTest> threadTest = new ThreadLocal<ExtentTest>();
    @Override
    public void onTestStart(ITestResult result) {
        test =extent.createTest(result.getMethod().getMethodName());
        threadTest.set(test);//unique thread id(ErrorValidations)->test

    }
    @Override
    public void onTestSuccess(ITestResult result) {
        threadTest.get().log(Status.PASS, "Test pass yes yes");

    }
    @Override
    public void onTestFailure(ITestResult result) {
        // screen short on failure
        threadTest.get().log(Status.FAIL, "test got fail");
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
                    .get(result.getInstance());
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        String ScreenShotpath = null;
        try {
            ScreenShotpath =getScreenshot(result.getMethod().getMethodName(), driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        threadTest.get().addScreenCaptureFromPath(ScreenShotpath,result.getMethod().getMethodName());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Implement if needed
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    // Implement other methods of ITestListener if needed
}


