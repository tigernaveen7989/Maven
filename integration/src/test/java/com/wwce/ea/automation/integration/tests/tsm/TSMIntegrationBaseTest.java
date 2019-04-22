package com.wwce.ea.automation.integration.tests.tsm;

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
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpAccountManagementPage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpCaseConfirmationPage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpCaseInformationPage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpChannelSelectionPage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpGameLibraryPage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpHomePage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpLoginPage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpProductPage;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpUnAuthCaseInformationPage;
import com.ea.wwce.automation.eahelp.pageobjects.tsm.TSMEAHELPChannelSelectionPage;
import com.ea.wwce.automation.integration.config.IntegrationDataConstants;
import com.ea.wwce.automation.tsm.pageobjects.TSMAccountBasicInfoPage;
import com.ea.wwce.automation.tsm.pageobjects.TSMAccountChangeHistoryPage;
import com.ea.wwce.automation.tsm.pageobjects.TSMAddAccountNotesPage;
import com.ea.wwce.automation.tsm.pageobjects.TSMAdditionalInfoPage;
import com.ea.wwce.automation.tsm.pageobjects.TSMAdvisorStatesPage;
import com.ea.wwce.automation.tsm.pageobjects.TSMCaseDetailsPage;
import com.ea.wwce.automation.tsm.pageobjects.TSMChatPage;
import com.ea.wwce.automation.tsm.pageobjects.TSMCurrentInventoryPage;
import com.ea.wwce.automation.tsm.pageobjects.TSMGenerateCodesPage;
import com.ea.wwce.automation.tsm.pageobjects.TSMGrantItemsPacksCurrencyPage;
import com.ea.wwce.automation.tsm.pageobjects.TSMHistoryPage;
import com.ea.wwce.automation.tsm.pageobjects.TSMJobRoleSelectionPage;
import com.ea.wwce.automation.tsm.pageobjects.TSMLoginPage;
import com.ea.wwce.automation.tsm.pageobjects.TSMProductPage;
import com.wwce.ea.automation.integration.tests.IntegrationBaseTest;

/**
 * 
 * @author rgandham
 * @description Integration base test for integration scenarios
 */
public class TSMIntegrationBaseTest extends IntegrationBaseTest {

	public static Logger logger = Logger.getLogger(IntegrationBaseTest.class);

	protected EAHelpLoginPage eaHelpLoginPage;
	protected EAHelpChannelSelectionPage eaHelpChannelSelectionPage;
	protected TSMLoginPage tsmLoginPage;
	protected TSMAdvisorStatesPage tsmAdvisorStatesPage;
	protected TSMAccountBasicInfoPage tsmAccountBasicInfoPage;
	protected TSMAddAccountNotesPage tsmAddAccountNotesPage;
	protected TSMCaseDetailsPage tsmCaseDetailsPage;
	protected EAHelpCaseConfirmationPage eaHelpCaseConfirmationPage;
	protected EAHelpGameLibraryPage eaHelpGameLibraryPage;
	protected EAHelpProductPage eaHelpProductPage;
	protected EAHelpCaseInformationPage eaHelpCaseInformationPage;
	protected EAHelpUnAuthCaseInformationPage eaHelpUnAuthCaseInformationPage;
	protected TSMEAHELPChannelSelectionPage tsmeahelpChannelSelectionPage;
	protected EAHelpAccountManagementPage eaHelpAccountManagementPage;
	protected TSMAccountChangeHistoryPage tsmAccountChangeHistorypage;
	protected TSMChatPage tsmChatPage;
	protected EAHelpHomePage eaHelpHomePage;
	protected TSMProductPage tsmProductPage;
	protected TSMGenerateCodesPage tsmGenerateCodesPage;
	protected TSMAdditionalInfoPage tsmAdditionalInfoPage;
	protected TSMGrantItemsPacksCurrencyPage tsmGrantItemsPacksCurrency;
	protected TSMCurrentInventoryPage tsmCurrentInventorypage;
	protected TSMHistoryPage tsmHistoryPage;
	protected TSMJobRoleSelectionPage tsmJobRoleSelectionPage;
	
	
	

	// Define the page objects relevant the application Under Test
	public void loadPageObjects(WebDriver driver) {

		eaHelpLoginPage = new EAHelpLoginPage(driver);
		eaHelpChannelSelectionPage = new EAHelpChannelSelectionPage(driver);
		tsmLoginPage = new TSMLoginPage(driver);
		tsmAdvisorStatesPage = new TSMAdvisorStatesPage(driver);
		tsmAccountBasicInfoPage = new TSMAccountBasicInfoPage(driver);
		tsmAddAccountNotesPage = new TSMAddAccountNotesPage(driver);
		tsmCaseDetailsPage = new TSMCaseDetailsPage(driver);
		eaHelpCaseConfirmationPage = new EAHelpCaseConfirmationPage(driver);
		eaHelpGameLibraryPage=new EAHelpGameLibraryPage (driver);
		eaHelpProductPage = new EAHelpProductPage(driver);
		eaHelpCaseInformationPage = new EAHelpCaseInformationPage(driver);
		eaHelpUnAuthCaseInformationPage = new EAHelpUnAuthCaseInformationPage(driver);
		tsmeahelpChannelSelectionPage = new TSMEAHELPChannelSelectionPage(driver);
		eaHelpAccountManagementPage = new EAHelpAccountManagementPage(driver);
		tsmAccountChangeHistorypage = new TSMAccountChangeHistoryPage(driver);
		tsmChatPage = new TSMChatPage(driver);
		eaHelpHomePage = new EAHelpHomePage(driver);
		tsmProductPage = new TSMProductPage(driver);
		tsmGenerateCodesPage = new TSMGenerateCodesPage(driver);
		tsmAdditionalInfoPage = new TSMAdditionalInfoPage(driver);
		tsmGrantItemsPacksCurrency = new TSMGrantItemsPacksCurrencyPage(driver);
		tsmCurrentInventorypage = new TSMCurrentInventoryPage(driver);
		tsmHistoryPage = new TSMHistoryPage(driver);
		tsmJobRoleSelectionPage = new TSMJobRoleSelectionPage(driver);

	}

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite(ITestContext context) {

		// Load the test data needed for the suite
		String testDataFilePath = IntegrationDataConstants.PROJECT_ROOT_LOCATION
				+ IntegrationDataConstants.INTEGRATION_TEST_DATA_ROOT_PATH
				+ IntegrationDataConstants.INTEGRATION_TEST_DATA_FILE_NAME;

		context.setAttribute("testDataFilePath", testDataFilePath);
		super.beforeSuite(context);

		// Create Test Rail Client connection object and pass the plan id
		TestRailClient testRailClient = new TestRailClient(IntegrationDataConstants.INTEGRATION_TESTRAIL_CONNECTION_URL,
				IntegrationDataConstants.INTEGRATION_TESTRAIL_USERNAME,
				IntegrationDataConstants.INTEGRATION_TESTRAIL_PASSWORD);

		context.setAttribute("testRailClientObject", testRailClient);

	}

	// Create a driver instance and load the page objects needed for the test
	@BeforeClass(alwaysRun = true)
	public void beforeClass(ITestContext context) {

		super.beforeClass(context);
		loadPageObjects(driver);

		driver.manage().timeouts().pageLoadTimeout(IntegrationDataConstants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(IntegrationDataConstants.IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

		String environment = BaseTest.testEnvironment;

		if (environment.equalsIgnoreCase("QA3PC")) {
			
			context.setAttribute("EAHELP_SERVICE_URL", IntegrationDataConstants.INTEGRATION_EAHELP_BASE_URL_QAPC);
			context.setAttribute("TSM_SERVICE_URL", IntegrationDataConstants.INTEGRATION_TSM_BASE_URL_QA3PC);
			context.setAttribute("EAHELP_TSM_SERVICE_URL",
					IntegrationDataConstants.INTEGRATION_EAHELP_TSM_BASE_URL_QA3PC);
		} else if (environment.equalsIgnoreCase("RUNFC")) {
			
			context.setAttribute("EAHELP_SERVICE_URL", IntegrationDataConstants.INTEGRATION_EAHELP_BASE_URL_RUNFC);
			context.setAttribute("TSM_SERVICE_URL", IntegrationDataConstants.INTEGRATION_TSM_BASE_URL_RUNFC);
			context.setAttribute("EAHELP_TSM_SERVICE_URL",
					IntegrationDataConstants.INTEGRATION_EAHELP_TSM_BASE_URL_RUNFC);
		} else if (environment.equalsIgnoreCase("UATPC")) {
			context.setAttribute("EAHELP_SERVICE_URL", IntegrationDataConstants.INTEGRATION_EAHELP_BASE_URL_UATPC);
			context.setAttribute("TSM_SERVICE_URL", IntegrationDataConstants.INTEGRATION_TSM_BASE_URL_UATPC);
			context.setAttribute("EAHELP_TSM_SERVICE_URL",
					IntegrationDataConstants.INTEGRATION_EAHELP_TSM_BASE_URL_UATPC);
		} else if (environment.equalsIgnoreCase("PROD")) {
			
			context.setAttribute("EAHELP_SERVICE_URL", IntegrationDataConstants.INTEGRATION_EAHELP_BASE_URL_PROD);
			
			context.setAttribute("TSM_SERVICE_URL", IntegrationDataConstants.INTEGRATION_TSM_BASE_URL_PROD);
			context.setAttribute("EAHELP_TSM_SERVICE_URL",
					IntegrationDataConstants.INTEGRATION_EAHELP_TSM_BASE_URL_PROD);
		} else {
			
			context.setAttribute("EAHELP_SERVICE_URL", IntegrationDataConstants.INTEGRATION_EAHELP_BASE_URL_QAPC);
			
			context.setAttribute("TSM_SERVICE_URL", IntegrationDataConstants.INTEGRATION_TSM_BASE_URL_QA3PC);
			context.setAttribute("EAHELP_TSM_SERVICE_URL",
					IntegrationDataConstants.INTEGRATION_EAHELP_TSM_BASE_URL_QA3PC);
		}

		
		IntegrationDataConstants.INTEGRATION_EAHELP_AUT_URL = context.getAttribute("EAHELP_SERVICE_URL").toString();
		IntegrationDataConstants.INTEGRATION_TSM_AUT_URL = context.getAttribute("TSM_SERVICE_URL").toString();
		IntegrationDataConstants.INTEGRATION_EAHELP_TSM_AUT_URL = context.getAttribute("EAHELP_TSM_SERVICE_URL")
				.toString();

	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(ITestContext context) {
		super.beforeMethod(context);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestContext context) {
		super.afterMethod(context);
	}

	@SuppressWarnings("unchecked")
	@AfterClass(alwaysRun = true)
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

	@AfterTest(alwaysRun = true)
	public void afterTest(ITestContext context) {
		super.afterTest(context);
	}

	@AfterSuite(alwaysRun = true)
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
