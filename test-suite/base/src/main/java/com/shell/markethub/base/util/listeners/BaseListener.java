package com.shell.markethub.base.util.listeners;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.shell.markethub.base.pageobjects.BasePageObject;
import com.shell.markethub.base.util.extentreports.ExtentManager;

import net.bytebuddy.utility.privilege.GetSystemPropertyAction;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.screentaker.ViewportPastingStrategy;

/**
 * @author N.Kumar8@shell.com
 * @description extent report will be configured and instance will be created,
 * result will be updated based on the test results
 */
public class BaseListener implements ITestListener{
	//Extent Report Declarations
	private static ExtentReports extent = ExtentManager.createInstance("Extent Reports");
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static WebDriver driver;
    private static Logger logger = Logger.getLogger(BaseListener.class);
    protected BasePageObject basePageObject = new BasePageObject();
 
    public static ThreadLocal<ExtentTest> getTest() {
    	return test;
    }
    
    @Override
    public synchronized void onStart(ITestContext context) {
        extent = ExtentManager.addReporterName(context.getSuite().getName());
        extent.setSystemInfo("User Name : ", System.getProperty("user.name"));
        extent.setSystemInfo("OS Name : ", System.getProperty("os.name"));
    }
 
    @Override
    public synchronized void onFinish(ITestContext context) {
        extent.flush();
        try {
			basePageObject.copyFileFromOneLocToAnotherLoc(System.getProperty("user.dir")+ "\\allure-results", System.getProperty("user.dir")+"\\target\\testreport\\allure-results");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
 
    @Override
    public synchronized void onTestStart(ITestResult result) {
    	logger.info("======================================");
    	logger.info(result.getMethod().getMethodName() +" Execution Started");
    	logger.info("======================================");
    	String testEnvironment = (String) result.getTestContext().getAttribute("testEnvironment");
        extent.setSystemInfo(result.getMethod().getMethodName(), testEnvironment);
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),result.getMethod().getDescription());
        String category = (String)result.getTestContext().getAttribute("appType");
        extentTest.assignCategory(category);
        test.set(extentTest);
    }
 
    @Override
    public synchronized void onTestSuccess(ITestResult result) {	
    	logger.info("=====================================");
    	logger.info(result.getMethod().getMethodName() +" Execution Ended");
    	logger.info("=====================================");
        String methodName=result.getMethod().getMethodName();
        //driver = (WebDriver) result.getTestContext().getAttribute("driver");
        //takeScreenShot(driver, methodName);
        try {
        	test.get().log(Status.PASS, methodName+" Passed");
			//test.get().addScreenCaptureFromPath(System.getProperty("user.dir") + "\\target\\testreport\\screenshots\\" + methodName + ".png");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
 
    @Override
    public synchronized void onTestFailure(ITestResult result) {
    	logger.info("=====================================");
    	logger.info(result.getMethod().getMethodName() +" Execution Ended");
    	logger.info("=====================================");
        test.get().fail(result.getThrowable());
        String methodName=result.getMethod().getMethodName();
        String appType = (String)result.getTestContext().getAttribute("appType");
        driver = (WebDriver) result.getTestContext().getAttribute("driver");
        takeScreenShot(driver, appType, methodName);
        try {
        	test.get().log(Status.FAIL, methodName+" Failed");
        	test.get().addScreenCaptureFromPath(System.getProperty("user.dir") + "\\target\\testreport\\screenshots\\" + appType+ " "+ methodName + ".png");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
 
    @Override
    public synchronized void onTestSkipped(ITestResult result) {
    	logger.info(result.getMethod().getMethodName() +" skipped");
        test.get().skip(result.getThrowable());
    }
 
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
       
    }
    
	// The below method will save the screen shot 
	public void takeScreenShot(WebDriver driver, String appType, String methodName) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\target\\testreport\\screenshots\\"
					+ appType + " " + methodName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
