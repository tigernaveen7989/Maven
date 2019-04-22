package com.ea.wwce.automation.base.util.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.ea.wwce.automation.base.tests.BaseTest;
import com.ea.wwce.automation.base.util.testmanagement.TestRailClient;

public class BaseListener extends BaseTest implements ITestListener {

	TestRailClient testRailClient;
	
	@Override
	public void onTestFailure(ITestResult result) {		
		testRailClient = (TestRailClient)result.getTestContext().getAttribute("testRailClientObject");
		testRailClient.addTestResult(result);
	}

	@Override
	public void onFinish(ITestContext arg0) {
	}

	@Override
	public void onStart(ITestContext arg0) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		testRailClient = (TestRailClient)result.getTestContext().getAttribute("testRailClientObject");
		testRailClient.addTestResult(result);
	}

	@Override
	public void onTestStart(ITestResult arg0) {
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		testRailClient = (TestRailClient)result.getTestContext().getAttribute("testRailClientObject");
		testRailClient.addTestResult(result);
	}
	
}
