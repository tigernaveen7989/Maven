package com.ea.wwce.automation.eahelp.tests;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.ea.wwce.automation.base.tests.BaseTest;
import com.ea.wwce.automation.base.util.Driver;
import com.ea.wwce.automation.base.util.jsonhandlers.JsonFileProcessor;
import com.ea.wwce.automation.base.util.testmanagement.TestRailClient;
import com.ea.wwce.automation.eahelp.config.EAHELPDataConstants;
import com.ea.wwce.automation.eahelp.pageobjects.AHQLoginPage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpAccountManagementPage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpArticlePage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpCSATSurveyPage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpCaseConfirmationPage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpCaseInformationPage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpChannelSelectionPage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpContactInformationPage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpGameLibraryPage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpHomePage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpLoginPage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpMyCasesPage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpMyCasesUnAuthPage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpProductPage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpUnAuthCaseInformationPage;
import com.ea.wwce.automation.eahelp.pageobjects.OriginLoginPage;

/**
 * 
 * @author rdronamraju
 *
 */
public class EAHelpBaseTest extends BaseTest {

	public static Logger logger = Logger.getLogger(EAHelpBaseTest.class);
	protected EAHelpLoginPage eaHelpLoginPage;
	protected EAHelpHomePage eaHelpHomePage;
	protected JsonFileProcessor fileProcessor;
	protected EAHelpCaseInformationPage eaHelpCaseInformationPage;
	protected EAHelpProductPage eaHelpProductPage;
	protected EAHelpChannelSelectionPage eaHelpChannelSelectionPage;
	protected EAHelpCaseConfirmationPage eaHelpCaseConfirmationPage;
	protected EAHelpContactInformationPage eaHelpContactInformationPage;
	protected EAHelpMyCasesPage eaHelpMyCasesPage;
	protected EAHelpUnAuthCaseInformationPage eaHelpUnAuthCaseInformationPage;
	protected EAHelpCSATSurveyPage eaHelpCSATSurveyPage;
	protected EAHelpAccountManagementPage eaHelpAccountManagementPage;
	protected EAHelpMyCasesUnAuthPage eaHelpMyCasesUnAuthPage;
	protected AHQLoginPage ahqLoginPage;
	protected OriginLoginPage originLoginPage;
	protected EAHelpArticlePage eaHelpArticlePage;
	protected EAHelpGameLibraryPage eaHelpGameLibraryPage;

	// Define the page objects relevant the application Under Test
	public void loadPageObjects(WebDriver driver) {
		eaHelpLoginPage = new EAHelpLoginPage(driver);
		eaHelpHomePage = new EAHelpHomePage(driver);
		eaHelpCaseInformationPage = new EAHelpCaseInformationPage(driver);
		eaHelpProductPage = new EAHelpProductPage(driver);
		eaHelpChannelSelectionPage = new EAHelpChannelSelectionPage(driver);
		eaHelpCaseConfirmationPage = new EAHelpCaseConfirmationPage(driver);
		eaHelpContactInformationPage = new EAHelpContactInformationPage(driver);
		eaHelpMyCasesPage = new EAHelpMyCasesPage(driver);
		eaHelpUnAuthCaseInformationPage = new EAHelpUnAuthCaseInformationPage(driver);
		eaHelpCSATSurveyPage = new EAHelpCSATSurveyPage(driver);
		eaHelpAccountManagementPage = new EAHelpAccountManagementPage(driver);
		eaHelpMyCasesUnAuthPage = new EAHelpMyCasesUnAuthPage(driver);
		ahqLoginPage = new AHQLoginPage(driver);
		originLoginPage = new OriginLoginPage(driver);
		eaHelpArticlePage = new EAHelpArticlePage(driver);
		eaHelpGameLibraryPage = new EAHelpGameLibraryPage(driver);
	}

	@BeforeSuite
	public void beforeSuite(ITestContext context) {

		// Load the test data needed for the suite
		String testDataFilePath = EAHELPDataConstants.PROJECT_ROOT_LOCATION
				+ EAHELPDataConstants.EAHELP_TEST_DATA_ROOT_PATH + EAHELPDataConstants.EAHELP_TEST_DATA_FILE_NAME;

		/*
		 * String attachmentFilePath = EAHELPDataConstants.PROJECT_ROOT_LOCATION
		 * +EAHELPDataConstants.EAHELP_TEST_DATA_ATTACHMENT_PATH;
		 */

		context.setAttribute("testDataFilePath", testDataFilePath);
		/* context.setAttribute("testDataFilePath", attachmentFilePath); */

		// Call base before suite using super as testrail connection needs to be
		// override
		super.beforeSuite(context);

		// Create Test Rail Client connection object and pass the plan id
		TestRailClient testRailClient = new TestRailClient(EAHELPDataConstants.EAHelp_TESTRAIL_CONNECTION_URL,
				EAHELPDataConstants.EAHelp_TESTRAIL_USERNAME, EAHELPDataConstants.EAHelp_TESTRAIL_PASSWORD);

		context.setAttribute("testRailClientObject", testRailClient);

		// loadPageObjects(super.createDriver());
	}

	@BeforeClass
	public void beforeClass(ITestContext context) {
		// super.beforeClass();
		super.beforeClass(context);
		loadPageObjects(driver);
		driver.manage().timeouts().pageLoadTimeout(EAHELPDataConstants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(EAHELPDataConstants.IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

		String environment = this.testEnvironment;

		if (environment.equalsIgnoreCase("QAPC")) {
			context.setAttribute("BASE_SERVICE_URL", EAHELPDataConstants.EAHELP_BASE_URL_QAPC);
		} else if (environment.equalsIgnoreCase("RUNFC")) {
			context.setAttribute("BASE_SERVICE_URL", EAHELPDataConstants.EAHELP_BASE_URL_RUNFC);
		} else if (environment.equalsIgnoreCase("UATPC")) {
			context.setAttribute("BASE_SERVICE_URL", EAHELPDataConstants.EAHELP_BASE_URL_UATPC);
		} else if (environment.equalsIgnoreCase("PROD")) {
			context.setAttribute("BASE_SERVICE_URL", EAHELPDataConstants.EAHELP_BASE_URL_PROD);
		} else {
			context.setAttribute("BASE_SERVICE_URL", EAHELPDataConstants.EAHELP_BASE_URL_QAPC);
		}

		EAHELPDataConstants.EAHELP_AUT_URL = context.getAttribute("BASE_SERVICE_URL").toString();
	}

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		super.beforeMethod(context);
	}

	@AfterMethod
	public void afterMethod(ITestContext context) {
		super.afterMethod(context);
	}

	@AfterClass
	public void afterClass(ITestContext context) {
		super.afterClass(context);
	}

	@AfterTest
	public void afterTest(ITestContext context) {
		super.afterTest(context);
	}

	@AfterSuite
	public void afterSuite(ITestContext context) {
		super.afterSuite(context);
	}

	// Use this method to create a new driver instance
	public WebDriver loadNewInstance(ITestContext context) {
		Driver driverInstance = (Driver) context.getAttribute("driverInstance");
		WebDriver newInstance = driverInstance.createDriver();
		this.loadPageObjects(newInstance);
		newInstance.manage().window().maximize();
		return newInstance;
	}

	// Use this method to switch to a driver instance
	public WebDriver switchToInstance(WebDriver driver) throws InterruptedException {
		this.loadPageObjects(driver);
		this.driver = driver;
		logger.info(driver.getTitle());
		Thread.sleep(1000);
		return driver;
	}

}
