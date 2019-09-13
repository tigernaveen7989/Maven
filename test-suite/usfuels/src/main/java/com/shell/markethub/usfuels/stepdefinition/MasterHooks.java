package com.shell.markethub.usfuels.stepdefinition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;

import com.shell.markethub.base.pageobjects.BasePageObject;
import com.shell.markethub.base.util.Driver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class MasterHooks extends Driver{
	/*
	 * ReadConfigFile file = new ReadConfigFile(); String browserName =
	 * file.getBrowser();
	 */
	@Before()
	public void setup() throws Exception {
		driver = setDriver("chrome","","","");
	}
	
	@After()
	public void tearDown(Scenario scenario) throws Exception{
		try {
			if(driver != null && scenario.isFailed()) {
				scenario.embed(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png");
				driver.manage().deleteAllCookies();
				driver.quit();
			}
			
			if(driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
