package com.pack.common.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pack.base.TestBaseSetup;
import com.pack.common.pageobjects.HomePage;

public class SalonPageTest extends TestBaseSetup{
	private WebDriver driver;
	private HomePage homePage;
	String TCName;
		
		@BeforeMethod
		public void setUp() {
			driver=getDriver();
		}
}
