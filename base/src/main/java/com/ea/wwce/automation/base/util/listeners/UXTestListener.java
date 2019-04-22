package com.ea.wwce.automation.base.util.listeners;

import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.ea.wwce.automation.base.tests.BaseTest;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.support.GalenReportsContainer;

public class UXTestListener extends BaseListener {
		
	@Override
	public void onTestFailure(ITestResult result) 
	{		
		if (!result.getThrowable().toString().contains("LayoutValidationException"))
		{
			List<GalenTestInfo> tests = GalenReportsContainer.get().getAllTests();
			tests.get(tests.size()-1).getReport().error(result.getThrowable());
		}
	}
	
	@Override
	public void onFinish(ITestContext arg0) {}

	@Override
	public void onStart(ITestContext arg0) {}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {}

	@Override
	public void onTestSkipped(ITestResult arg0) {}

	@Override
	public void onTestStart(ITestResult arg0) {}

	@Override
	public void onTestSuccess(ITestResult arg0) {}
	
}

