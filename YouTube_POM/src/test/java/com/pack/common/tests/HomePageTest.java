package com.pack.common.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pack.base.TestBaseSetup;
import com.pack.common.pageobjects.HomePage;
import com.pack.functionlibrary.FunctionLibrary;
import com.pack.utils.*;
import com.pack.utils.Listeners.TestListener;

import io.appium.java_client.android.AndroidDriver;

public class HomePageTest extends TestBaseSetup{
	private WebDriver driver;

	private HomePage homePage;
		
		@BeforeMethod
		public void setUp() {
			driver=getDriver();
		}
			
		@Test(description="Search functionality")
		public void verifySearchFunction() throws Exception {
			homePage = new HomePage(driver);
			homePage.verifySearchIcon();
			homePage.clickSearchIcon();
			homePage.enterValue("Apple");
			homePage.clickSearch();
		}
}
