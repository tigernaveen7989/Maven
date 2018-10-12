package com.pack.utils.Listeners;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.pack.base.TestBaseSetup;
import com.pack.utils.ExtentReports.ExtentManager;

public class TestListener implements ITestListener{

	//Extent Report Declarations
    public static ExtentReports extent = ExtentManager.createInstance();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    static WebDriver driver;
 
    @Override
    public synchronized void onStart(ITestContext context) {
        System.out.println("Extent Reports Version 3 Test Suite started!");
    }
 
    @Override
    public synchronized void onFinish(ITestContext context) {
        System.out.println(("Extent Reports Version 3  Test Suite is ending!"));
        extent.flush();
    }
 
    @Override
    public synchronized void onTestStart(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " started!!!"));
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),result.getMethod().getDescription());
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        test.set(extentTest);
    }
 
    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " passed!"));
        test.get().pass("Test passed");
        String methodName=result.getMethod().getMethodName();
        takeScreenShot(result, methodName);
        try {
        	test.get().log(Status.PASS, methodName+" Passed");
			test.get().addScreenCaptureFromPath(System.getProperty("user.dir") + "/src/test/java/com/screenshots/" + methodName + ".png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    @Override
    public synchronized void onTestFailure(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " failed!"));
        test.get().fail(result.getThrowable());
        String methodName=result.getMethod().getMethodName();
        takeScreenShot(result, methodName);
        try {
        	test.get().log(Status.FAIL, methodName+" Failed");
        	test.get().addScreenCaptureFromPath(System.getProperty("user.dir") + "/src/test/java/com/screenshots/" + methodName + ".png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " skipped!"));
        test.get().skip(result.getThrowable());
    }
 
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }
    
    public static ThreadLocal<ExtentTest> getTest() {
    	return test;
    }
    
	public void takeScreenShot(ITestResult result, String methodName) {
		Object testClass = result.getInstance();
		// get the driver
		driver = ((TestBaseSetup) testClass).getDriver();
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// The below method will save the screen shot in d drive with test
		// method name
		try {
			FileUtils.copyFile(scrFile,new File(System.getProperty("user.dir") + "/src/test/java/com/screenshots/" + methodName + ".png"));
			System.out.println("***Placed screen shot in " + System.getProperty("user.dir")+"/src/test/java/com/screenshots/" + methodName + ".png" + " ***");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
