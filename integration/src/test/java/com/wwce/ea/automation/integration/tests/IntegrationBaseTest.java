package com.wwce.ea.automation.integration.tests;

import java.util.HashMap;
import java.util.Map;
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
import com.ea.wwce.automation.base.util.testmanagement.TestRailClient;
import com.ea.wwce.automation.eahelp.pageobjects.ChatWindowPage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpCaseConfirmationPage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpCaseInformationPage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpChannelSelectionPage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpGameLibraryPage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpHomePage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpLoginPage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpMyCasesPage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpProductPage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpUnAuthCaseInformationPage;
import com.ea.wwce.automation.integration.config.IntegrationDataConstants;
import com.ea.wwce.automation.omega.pageobjects.OmegaAgentHomePage;
import com.ea.wwce.automation.omega.pageobjects.OmegaCaseDetailsPage;
import com.ea.wwce.automation.omega.pageobjects.OmegaCasesPage;
import com.ea.wwce.automation.omega.pageobjects.OmegaLoginPage;
import com.ea.wwce.automation.omega.pageobjects.OmegaRolesContainerPage;
import com.ea.wwce.automation.omega.pageobjects.OmegaSaveCaseConfirmationPage;
import com.ea.wwce.automation.salesforce.pageobjects.SalesforceHomePage;
import com.ea.wwce.automation.salesforce.pageobjects.SalesforceLoginPage;

/**
 * 
 * @author sadabala
 * @description Integration base test for integration scenarios
 */
public class IntegrationBaseTest extends BaseTest {

	public static Logger logger = Logger.getLogger(IntegrationBaseTest.class);

	OmegaRolesContainerPage omegaRolesContainerPage;
	SalesforceLoginPage salesforceLoginPage;
	protected EAHelpChannelSelectionPage eaHelpChannelSelectionPage;
	protected EAHelpLoginPage eaHelpLoginPage;
	OmegaCaseDetailsPage omegaCaseDetailsPage;
	OmegaSaveCaseConfirmationPage omegaSaveCaseConfirmationPage;
	protected OmegaLoginPage omegaLoginPage;
	protected EAHelpCaseConfirmationPage eaHelpCaseConfirmationPage;
	protected OmegaAgentHomePage omegaAgentHomePage;
	OmegaCasesPage omegaCasesPage;
	EAHelpMyCasesPage eaHelpMyCasesPage;
	EAHelpHomePage eaHelpHomePage;
	protected EAHelpProductPage eaHelpProductPage;
	protected EAHelpCaseInformationPage eaHelpCaseInformationPage;
	protected EAHelpUnAuthCaseInformationPage eaHelpUnAuthCaseInformationPage;
	SalesforceHomePage salesforceHomePage;
	ChatWindowPage chatWindowPage;
	protected EAHelpGameLibraryPage eaHelpGameLibraryPage;

	// Define the page objects relevant the application Under Test
	public void loadPageObjects(WebDriver driver) {
		omegaRolesContainerPage = new OmegaRolesContainerPage(driver);
		salesforceLoginPage = new SalesforceLoginPage(driver);
		eaHelpChannelSelectionPage = new EAHelpChannelSelectionPage(driver);
		eaHelpLoginPage = new EAHelpLoginPage(driver);
		omegaCaseDetailsPage = new OmegaCaseDetailsPage(driver);
		omegaSaveCaseConfirmationPage = new OmegaSaveCaseConfirmationPage(driver);
		omegaLoginPage = new OmegaLoginPage(driver);
		eaHelpCaseConfirmationPage = new EAHelpCaseConfirmationPage(driver);
		omegaAgentHomePage = new OmegaAgentHomePage(driver);
		omegaCasesPage = new OmegaCasesPage(driver);
		eaHelpMyCasesPage = new EAHelpMyCasesPage(driver);
		eaHelpHomePage = new EAHelpHomePage(driver);
		eaHelpProductPage = new EAHelpProductPage(driver);
		eaHelpCaseInformationPage = new EAHelpCaseInformationPage(driver);
		eaHelpUnAuthCaseInformationPage = new EAHelpUnAuthCaseInformationPage(driver);
		salesforceHomePage = new SalesforceHomePage(driver);
		chatWindowPage= new ChatWindowPage(driver);
		eaHelpGameLibraryPage=new EAHelpGameLibraryPage (driver);
	}

	@BeforeSuite(alwaysRun=true)
	public void beforeSuite(ITestContext context) {

		// Load the test data needed for the suite
		String testDataFilePath = IntegrationDataConstants.PROJECT_ROOT_LOCATION
				+ IntegrationDataConstants.INTEGRATION_TEST_DATA_ROOT_PATH
				+ IntegrationDataConstants.INTEGRATION_TEST_DATA_FILE_NAME;
		context.setAttribute("testDataFilePath", testDataFilePath);

		// Call base before suite using super as testrail connection needs to be
		// override
		super.beforeSuite(context);

		// Create Test Rail Client connection object and pass the plan id
		TestRailClient testRailClient = new TestRailClient(IntegrationDataConstants.INTEGRATION_TESTRAIL_CONNECTION_URL,
				IntegrationDataConstants.INTEGRATION_TESTRAIL_USERNAME,
				IntegrationDataConstants.INTEGRATION_TESTRAIL_PASSWORD);

		context.setAttribute("testRailClientObject", testRailClient);

	}

	// Create a driver instance and load the page objects needed for the test
	@BeforeClass(alwaysRun=true)
	public void beforeClass(ITestContext context) {

		super.beforeClass(context);
		loadPageObjects(driver);

		driver.manage().timeouts().pageLoadTimeout(IntegrationDataConstants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(IntegrationDataConstants.IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

		String environment = BaseTest.testEnvironment;

		if (environment.equalsIgnoreCase("QAPC")) {
			context.setAttribute("OMEGA_SERVICE_URL", IntegrationDataConstants.INTEGRATION_OMEGA_BASE_URL_QAPC);
			context.setAttribute("EAHELP_SERVICE_URL", IntegrationDataConstants.INTEGRATION_EAHELP_BASE_URL_QAPC);
			context.setAttribute("SF_SERVICE_URL", IntegrationDataConstants.INTEGRATION_SF_BASE_URL_QAPC);
		} else if (environment.equalsIgnoreCase("RUNFC")) {
			context.setAttribute("OMEGA_SERVICE_URL", IntegrationDataConstants.INTEGRATION_OMEGA_BASE_URL_RUNFC);
			context.setAttribute("EAHELP_SERVICE_URL", IntegrationDataConstants.INTEGRATION_EAHELP_BASE_URL_RUNFC);
			context.setAttribute("SF_SERVICE_URL", IntegrationDataConstants.INTEGRATION_SF_BASE_URL_RUNFC);
		} else if (environment.equalsIgnoreCase("UATPC")) {
			context.setAttribute("OMEGA_SERVICE_URL", IntegrationDataConstants.INTEGRATION_OMEGA_BASE_URL_UATPC);
			context.setAttribute("EAHELP_SERVICE_URL", IntegrationDataConstants.INTEGRATION_EAHELP_BASE_URL_UATPC);
			context.setAttribute("SF_SERVICE_URL", IntegrationDataConstants.INTEGRATION_SF_BASE_URL_UATPC);
		} else if (environment.equalsIgnoreCase("PROD")) {
			context.setAttribute("OMEGA_SERVICE_URL", IntegrationDataConstants.INTEGRATION_OMEGA_BASE_URL_PROD);
			context.setAttribute("EAHELP_SERVICE_URL", IntegrationDataConstants.INTEGRATION_EAHELP_BASE_URL_PROD);
			context.setAttribute("SF_SERVICE_URL", IntegrationDataConstants.INTEGRATION_SF_BASE_URL_PROD);
		} else {
			context.setAttribute("OMEGA_SERVICE_URL", IntegrationDataConstants.INTEGRATION_OMEGA_BASE_URL_QAPC);
			context.setAttribute("EAHELP_SERVICE_URL", IntegrationDataConstants.INTEGRATION_EAHELP_BASE_URL_QAPC);
			context.setAttribute("SF_SERVICE_URL", IntegrationDataConstants.INTEGRATION_SF_BASE_URL_QAPC);
		}

		IntegrationDataConstants.INTEGRATION_OMEGA_AUT_URL = context.getAttribute("OMEGA_SERVICE_URL").toString();
		IntegrationDataConstants.INTEGRATION_EAHELP_AUT_URL = context.getAttribute("EAHELP_SERVICE_URL").toString();
		IntegrationDataConstants.INTEGRATION_SF_AUT_URL = context.getAttribute("SF_SERVICE_URL").toString();

	}

	@BeforeMethod(alwaysRun=true)
	public void beforeMethod(ITestContext context) {
		super.beforeMethod(context);
	}

	@AfterMethod(alwaysRun=true)
	public void afterMethod(ITestContext context) {
		super.afterMethod(context);
	}

	@SuppressWarnings("unchecked")
	@AfterClass(alwaysRun=true)
	public void afterClass(ITestContext context) {

		Map<String, WebDriver> driverInstance = (HashMap<String, WebDriver>) context.getAttribute("mDriverInstances");
		if (driverInstance != null && driverInstance.size() > 0) {
			try {
				for (Map.Entry<String, WebDriver> dr : driverInstance.entrySet()) {
					dr.getValue().quit();
					Thread.sleep(3000);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		super.afterClass(context);
	}

	@AfterTest(alwaysRun=true)
	public void afterTest(ITestContext context) {
		super.afterTest(context);
	}

	@AfterSuite(alwaysRun=true)
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
