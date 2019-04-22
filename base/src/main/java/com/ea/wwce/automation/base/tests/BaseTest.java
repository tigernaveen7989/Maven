package com.ea.wwce.automation.base.tests;

import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.safari.SafariDriverService;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.IAssert;

import com.ea.wwce.automation.base.config.BaseDataConstants;
import com.ea.wwce.automation.base.util.Assertions;
import com.ea.wwce.automation.base.util.Driver;
import com.ea.wwce.automation.base.util.DriverServiceManager;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.base.util.jsonhandlers.JsonFileProcessor;
import com.ea.wwce.automation.base.util.testmanagement.TestRailClient;
import com.galenframework.testng.GalenTestNgTestBase;

import io.qameta.allure.Step;

/**
 * 
 * @author rdronamraju
 * @description This class contains all the methods needed for the child tests
 *              across applications
 */
//extends GalenTestNgTestBase

public class BaseTest {

	protected Assertions asserts;
	protected WebDriver driver;
	protected Driver driverInstance;
	protected static String executionMode;
	protected static String platform;
	protected static String operatingSystem;
	protected static String browserName;
	protected static String browserVersion;
	protected static String automationName;
	protected static String testEnvironment;
	protected static String deviceName;
	protected static String appPackage;
	protected static String appActivity;
	protected static String applicationId;
	protected static String bundleId;
	protected static String appiumURL;
	protected static String testType;
	protected DataProviders dataProvider;
	protected JsonFileProcessor fileProcessor;
	protected TestRailClient testRailClient;
	protected static String planId;
	protected ChromeDriverService chromeDriverService;
	protected GeckoDriverService geckoDriverService;
	protected InternetExplorerDriverService ieDriverService;
	protected EdgeDriverService edgeDriverService;
	protected SafariDriverService safariDriverService;
	private DriverServiceManager driverServiceManager;
	protected URL driverServiceURL;

	private static final Logger logger = Logger.getLogger(BaseTest.class);

	@BeforeSuite(alwaysRun=true)
	public void beforeSuite(ITestContext context) {

		System.setProperty("PROJECT_ROOT_HOME", BaseDataConstants.PROJECT_ROOT_LOCATION);
		logger.info("---------------------------------------------------");
		logger.info("Loading the before suite conditions");
		logger.info("---------------------------------------------------");
		PropertyConfigurator.configure(BaseDataConstants.PROJECT_ROOT_LOCATION + BaseDataConstants.LOG_PROPERTIES_FILE);

		logger.info("Enabling logging...");

		logger.info("Enabling master test dataproviders...");

		/*
		 * dataProvider = new
		 * DataProviders(context.getAttribute("testDataFilePath").toString());
		 * context.setAttribute("dataProvider", dataProvider); fileProcessor = new
		 * JsonFileProcessor(context.getAttribute("testDataFilePath").toString());
		 * context.setAttribute("fileProcessor", fileProcessor);
		 */
		// Create Test Rail Client connection object and pass the plan id - commented it
		// as we use project specific test rail credentials

		logger.info("Enabling master test dataproviders...");		
		dataProvider = new DataProviders(context.getAttribute("testDataFilePath").toString());
		context.setAttribute("dataProvider", dataProvider);
		fileProcessor = new JsonFileProcessor(context.getAttribute("testDataFilePath").toString());
		context.setAttribute("fileProcessor", fileProcessor);

		testRailClient = new TestRailClient(BaseDataConstants.TESTRAIL_CONNECTION_URL,
				BaseDataConstants.TESTRAIL_USERNAME, BaseDataConstants.TESTRAIL_PASSWORD);

		context.setAttribute("testRailClientObject", testRailClient);

	}

	@BeforeTest(alwaysRun=true)
	@Parameters({ "executionMode", "platform", "operatingSystem", "browserName", "browserVersion", "automationName",
			"testEnvironment", "deviceName", "appPackage", "appActivity", "applicationId", "bundleId", "appiumURL",
			"planId", "testType" })
	public void beforeTest(String executionMode, String platform, String operatingSystem, String browserName,
			String browserVersion, String automationName, String testEnvironment, String deviceName, String appPackage,
			String appActivity, String applicationId, String bundleId, String appiumURL, String planId, String testType,
			ITestContext context) {

		// Load the test configuration once before each test.
		String configurationSource = "";
		String testDataFilePath="";
		String testDataContext="";

		if (System.getProperty("PICK_CONFIGURATION_FROM") == null) {
			logger.info("No test configuration chosen,picking the TestNG.xml configuration as default");
			configurationSource = "TestNG";
		} else {
			configurationSource = System.getProperty("PICK_CONFIGURATION_FROM");
			logger.info("Test configuration will be picked from " + configurationSource);
		}

		// If the configuraton source is TestNG.xml
		if (configurationSource.contains("TestNG")) {
			logger.info("Loading configuration from TestNG file");
			BaseTest.executionMode = executionMode;
			BaseTest.platform = platform;
			BaseTest.operatingSystem = operatingSystem;
			BaseTest.browserName = browserName;
			BaseTest.browserVersion = browserVersion;
			BaseTest.automationName = automationName;
			BaseTest.testEnvironment = testEnvironment;
			BaseTest.deviceName = deviceName;
			BaseTest.appPackage = appPackage;
			BaseTest.appActivity = appActivity;
			BaseTest.applicationId = applicationId;
			BaseTest.bundleId = bundleId;
			BaseTest.appiumURL = appiumURL;

			// Ability to capture the plan id via TESTNG parameter
			BaseTest.planId = planId;
			context.setAttribute("test_plan_id", planId);

			BaseTest.testType = testType;
			context.setAttribute("testType", BaseTest.testType);

		} else if (configurationSource.contains("JENKINS")) {
			logger.info("Loading the configuration chosen from Jenkins");
			// If the configuration source is Jenkins, then override TestNG.xml
			// parameters
			BaseTest.executionMode = System.getProperty("EXECUTION_MODE");
			BaseTest.platform = System.getProperty("PLATFORM");
			BaseTest.operatingSystem = System.getProperty("OPERATING_SYSTEM");
			BaseTest.browserName = System.getProperty("BROWSER");
			BaseTest.browserVersion = "";
			BaseTest.automationName = System.getProperty("COMPUTERNAME");
			BaseTest.testEnvironment = System.getProperty("TEST_ENVIRONMENT");
			BaseTest.deviceName = System.getProperty("DEVICE_NAME");
			BaseTest.appPackage = System.getProperty("APPLICATION_PACKAGE");
			BaseTest.appActivity = System.getProperty("APPLICATION_ACTIVITY");
			BaseTest.applicationId = "";
			BaseTest.bundleId = "";
			BaseTest.appiumURL = "";

			BaseTest.testType = System.getProperty("TEST_TYPE");
			context.setAttribute("testType", BaseTest.testType);

			// pull the plan ID from TestNG
			BaseTest.planId = planId;
			context.setAttribute("test_plan_id", planId);

		}

		// Load the Driver object with chosen test configuration capabilities
		this.driverInstance = new Driver(BaseTest.executionMode, BaseTest.platform, BaseTest.operatingSystem,
				BaseTest.browserName, BaseTest.browserVersion, BaseTest.automationName, BaseTest.testEnvironment,
				BaseTest.deviceName, BaseTest.appPackage, BaseTest.appActivity, BaseTest.applicationId,
				BaseTest.bundleId, BaseTest.appiumURL);

		// Set the driver instance context
		// Set the driver service URL for creation of driver instances
		this.driverInstance.setDriverServiceURL();
		context.setAttribute("driverInstance", this.driverInstance);

		// Load the driver Service Manager object
		this.driverServiceManager = driverInstance.getDriverServiceManager();
		context.setAttribute("driverServiceManager", this.driverServiceManager);
		
		//test data file path
		testDataContext=context.getAttribute("testDataFilePath").toString();

		//updating test data file with env. specific path
		testDataFilePath = testDataContext.substring(0,context.getAttribute("testDataFilePath").toString().length()
				- BaseDataConstants.TEST_DATA_FILE_NAME.length()) + BaseTest.testEnvironment.toLowerCase()+"\\"
				+ BaseDataConstants.TEST_DATA_FILE_NAME;

		// moved this code from beforesuite as we need to access environment specific
		// test data
		dataProvider = new DataProviders(testDataFilePath);
		context.setAttribute("dataProvider", dataProvider);
		fileProcessor = new JsonFileProcessor(testDataFilePath);
		context.setAttribute("fileProcessor", fileProcessor);
	}

	@BeforeClass(alwaysRun=true)
	public void beforeClass(ITestContext context) {
		Driver driverInstance = (Driver) context.getAttribute("driverInstance");
		driver = driverInstance.createDriver();
		// Refresh the soft assert object after each method
		asserts = new Assertions();
	}

	@BeforeMethod(alwaysRun=true)
	public void beforeMethod(ITestContext context) {
	}

	@AfterMethod(alwaysRun=true)
	public void afterMethod(ITestContext context) {
	}

	// Close the driver services during after test
	@AfterClass
	public void afterClass(ITestContext context) {
		driver.quit();
		logger.info("------------------------------------------------------------------------");
		logger.info("Closed driver instance ... ");
		logger.info("------------------------------------------------------------------------");
	}

	@AfterTest(alwaysRun=true)
	public void afterTest(ITestContext context) {
		// Shutdown the driver instance on completion of test suite.
		logger.info("------------------------------------------------------------------------");
		DriverServiceManager driverServiceManager = (DriverServiceManager) context.getAttribute("driverServiceManager");
		if (BaseTest.browserName.equalsIgnoreCase("CHROME")) {
			driverServiceManager.stopChromeDriverService();
		} else if (BaseTest.browserName.equalsIgnoreCase("FIREFOX")) {
			driverServiceManager.stopGeckoDriverService();
		} else if (BaseTest.browserName.equalsIgnoreCase("EDGE")) {
			driverServiceManager.stopEdgeDriverService();
		} else if (BaseTest.browserName.equalsIgnoreCase("IE")) {
			driverServiceManager.stopIEDriverService();
		} else if (BaseTest.browserName.equalsIgnoreCase("SAFARI")) {
			driverServiceManager.stopSafariDriverService();
		}
		logger.info("Closed driver Service ... ");
		logger.info("------------------------------------------------------------------------");
	}

	@AfterSuite(alwaysRun=true)
	public void afterSuite(ITestContext context) {
	}

	// Use below method to create Driver object in case of Galen tests.
	public WebDriver createDriver(Object[] args) {
		return this.driver;
	}

	// Build reusable assert methods
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

	// close driver instances
	public void closeDriverInstance(WebDriver driver) {
		driver.quit();
	}

}