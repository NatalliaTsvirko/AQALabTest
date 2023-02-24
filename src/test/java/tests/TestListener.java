package tests;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.AllureUtils;

@Log4j2
public class TestListener implements ITestListener {


    @Override
    public void onTestStart(ITestResult result) {
        log.info("Test started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info(String.format("Test '%s' succeed", result.getName()));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info(String.format("Test '%s' failed", result.getName()));
        WebDriver driver = (WebDriver) (result.getTestContext().getAttribute("driver"));
        AllureUtils.attachScreenshot(driver);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info(String.format("Test '%s' skipped", result.getName()));

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        context.getPassedTests();
        context.getFailedTests();
    }
}
