package com.shell.markethub.base.util;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

import com.shell.markethub.base.pageobjects.BasePageObject;
import com.shell.markethub.base.util.config.BaseDataConstants;
import com.shell.markethub.base.util.dataprovider.DataProviders;
import com.shell.markethub.base.util.jsonhandlers.JsonFileProcessor;

import io.qameta.allure.Step;

/**
 * @author N.Kumar8@shell.com
 */
public class BaseTest extends Driver{

	protected Assertions asserts = new Assertions();
	protected DataProviders dataProvider;
	protected JsonFileProcessor fileProcessor;
	protected String testDataContext="";
	private static Logger logger = Logger.getLogger(BaseTest.class);
	protected Map<String, Object> testData = new HashMap<String, Object>();
	protected Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
	BasePageObject basePageObject = new BasePageObject();
	public String testEnvironment;
	protected SoftAssert assertion = new SoftAssert();
	
	/**
	 * @description getDriver
	 * @return driver
	 */
	public WebDriver getDriver() {
		return driver;
	}
	
	/**
	 * @param deviceName
	 * @param moduleName
	 * @param context
	 * @description browser will be initialized, 
	 * dataprovider and json file processor will be called and testdata will be setup
	 */
	@Parameters({ "appType","moduleName","node","deviceName", "platformVersion"})
	@BeforeMethod
	public void initializeTestBaseSetup(@Optional String appType, @Optional String moduleName, @Optional String node, @Optional String deviceName, @Optional String platformVersion, ITestContext context, Method method) {
		try {
			// Get Test case name
			String TCName = method.getName();
			// Get Test case row number
			int varRowNumber = basePageObject.getRowNumber(TCName, moduleName);
			// Get Test case environment
			testEnvironment = basePageObject.getCellValue("Environment", varRowNumber, moduleName).toString();			 
			// Set driver instance
			driver = setDriver(appType, node, deviceName, platformVersion);
			// Set deviceName Attribute
			context.setAttribute("appType", appType);
			//Set ModuleName Attribute
			context.setAttribute("moduleName", moduleName);
			//Set Environment Attribute
			context.setAttribute("testEnvironment", testEnvironment);
			// Set driver attribute
			context.setAttribute("driver", driver);			
			// Load the test data needed for the method 
			String testDataFilePath = BaseDataConstants.PROJECT_ROOT_LOCATION + moduleName + "\\"
					+ BaseDataConstants.TEST_DATA_ROOT_PATH + BaseDataConstants.TEST_DATA_FILE_NAME;
			context.setAttribute("testDataFilePath", testDataFilePath);
			// Set Project root home system property
			System.setProperty("PROJECT_ROOT_HOME", BaseDataConstants.PROJECT_ROOT_LOCATION);
			// test data file path
			testDataContext = context.getAttribute("testDataFilePath").toString();
			// updating test data file with env. specific path
			testDataFilePath = testDataContext.substring(0,
					context.getAttribute("testDataFilePath").toString().length()
							- BaseDataConstants.TEST_DATA_FILE_NAME.length())
					+ testEnvironment.toLowerCase() + "\\" + BaseDataConstants.TEST_DATA_FILE_NAME;
			// test data 
			dataProvider = new DataProviders(testDataFilePath);
			// Set data provider attribute
			context.setAttribute("dataProvider", dataProvider);
			fileProcessor = new JsonFileProcessor(testDataFilePath);
			// Set fileProcessor attribute
			context.setAttribute("fileProcessor", fileProcessor);
			// Set TCName attribute
			context.setAttribute("TCName", TCName);
			logger.info("validating Verify Login Test ----" + TCName);
			// Load the test data for the test case name
			DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
			// Get the test case for TCName
			testData = dataProvider.getTestData(TCName);
			logger.info("*************************************");
			logger.info(TCName +" Test Data Loaded Successfully");
			logger.info("*************************************");	
			//set the environment
			if(testEnvironment.equalsIgnoreCase("PERF")) {
				context.setAttribute("MARKETHUB_URL", BaseDataConstants.PERF_MARKETHUB_URL);
			}else if(testEnvironment.equalsIgnoreCase("PROD")) {
				context.setAttribute("MARKETHUB_URL", BaseDataConstants.PROD_MARKETHUB_URL);
			}
			BaseDataConstants.MARKETHUB_AUT_URL = context.getAttribute("MARKETHUB_URL").toString();
			
		} catch (Exception e) {
			logger.info("Error....." + e.getStackTrace());
		}
	}
	
	/**
	 * @description driver will shutdown
	 */
	@AfterMethod
	public void tearDown() {
		if(driver == null) {	
		}else {
			driver.quit();
			logger.info("-------------------------------------");
			logger.info(" Quit Driver ");
			logger.info("-------------------------------------");
		}
	}	
	
	@Step("Verify Condition - {message}")
	protected void assertEquals(String actual, String expected, String message) {
		asserts.assertEquals(actual, expected, message);
	}

	@Step("Verify Condition - {message}")
	protected void assertEquals(boolean actual, boolean expected, String message) {
		asserts.assertEquals(actual, expected, message);
	}

	@Step("Verify Condition - {message}")
	protected void assertEquals(Object actual, Object expected, String message) {
		asserts.assertEquals(actual, expected, message);
	}

	@Step("Verify Condition - {message}")
	protected void assertNotEquals(String actual, String expected, String message) {
		asserts.assertNotEquals(actual, expected, message);
	}

	@Step("Verify Condition - {message}")
	protected void assertFalse(Boolean condition, String message) {
		asserts.assertFalse(condition, message);
	}

	@Step("Verify Condition - {message}")
	protected void assertTrue(Boolean condition, String message) {
		asserts.assertTrue(condition, message);
	}

	@Step("Verify Condition - {message}")
	protected void assertNotNull(String condition, String message) {
		asserts.assertNotNull(condition, message);
	}

	@Step("Verify Condition - {message}")
	protected void assertNull(String condition, String message) {
		asserts.assertNull(condition, message);		
	}

	protected void doAssert(IAssert assertion) {
		asserts.doAssert(assertion);
	}

	protected void assertAll() {
		asserts.assertAll();
	}
}
