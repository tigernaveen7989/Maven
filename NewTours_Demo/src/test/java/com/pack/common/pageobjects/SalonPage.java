package com.pack.common.pageobjects;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.pack.functionlibrary.FunctionLibrary;
import com.pack.utils.Listeners.TestListener;

public class SalonPage {
	FunctionLibrary globalfunctions=new FunctionLibrary();
	private WebDriver driver;
	ThreadLocal<ExtentTest> test = TestListener.getTest();
	
	public SalonPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void pageTitle() throws Exception{
		String pageTitle = driver.getTitle();
		System.out.println(pageTitle);
	}
}
