package com.ea.wwce.automation.ahq.tests;

import com.ea.wwce.automation.ahq.config.AHQDataConstants;
import com.ea.wwce.automation.ahq.pageobjects.AHQLoginPage;
import com.ea.wwce.automation.ahq.pageobjects.AHQSignInPage;
import com.ea.wwce.automation.base.tests.BaseTest;
import com.ea.wwce.automation.base.util.Assertions;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.IAssert;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import com.ea.wwce.automation.base.util.Driver;
import com.ea.wwce.automation.ahq.config.AHQDataConstants;

/**
 * 
 * @author rdronamraju
 *
 */
public  class AHQBaseTest extends BaseTest{
	
	public static Logger logger = Logger.getLogger(AHQBaseTest.class);
	AHQLoginPage ahqLoginPage;
	AHQSignInPage ahqSignInPage;
	
	@BeforeSuite
	public void beforeSuite(ITestContext context){		
		String testDataFilePath = AHQDataConstants.PROJECT_ROOT_LOCATION + 
				AHQDataConstants.AHQ_TEST_DATA_ROOT_PATH +
				AHQDataConstants.AHQ_TEST_DATA_FILE_NAME;	
		context.setAttribute("testDataFilePath", testDataFilePath);
		super.beforeSuite(context);
	}
	
	
	//Define the page objects relevant the application Under Test
	public void loadPageObjects(WebDriver driver){
		ahqLoginPage = new AHQLoginPage(driver);
		ahqSignInPage = new AHQSignInPage(driver);
	}
	
	//Create a driver instance and load the page objects needed for the test
	@BeforeClass
	public void beforeClass(ITestContext context){
		super.beforeClass(context); 
		loadPageObjects(driver);		
	}

}
