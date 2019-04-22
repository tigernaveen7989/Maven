package com.ea.wwce.automation.gcn.tests;

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

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.ea.wwce.automation.base.tests.BaseTest;

import com.ea.wwce.automation.base.util.testmanagement.TestRailClient;
import com.ea.wwce.automation.gcn.config.GcnDataConstants;
import com.ea.wwce.automation.gcn.pageobjects.AdminOppBudgetPage;
import com.ea.wwce.automation.gcn.pageobjects.AdminOppDetailsPage;
import com.ea.wwce.automation.gcn.pageobjects.AdminOppPaymentsPage;
import com.ea.wwce.automation.gcn.pageobjects.AdminOppPaymentsReviewPage;
import com.ea.wwce.automation.gcn.pageobjects.AdminOppSettingsPage;
import com.ea.wwce.automation.gcn.pageobjects.AdminOppUsersPage;
import com.ea.wwce.automation.gcn.pageobjects.AdminOpportunityListPage;
import com.ea.wwce.automation.gcn.pageobjects.AdminUsersPage;
import com.ea.wwce.automation.gcn.pageobjects.AdminViewUserProfile;
import com.ea.wwce.automation.gcn.pageobjects.GcEulaPage;
import com.ea.wwce.automation.gcn.pageobjects.GcLegalNoticesPage;
import com.ea.wwce.automation.gcn.pageobjects.GcOppDetailsPage;
import com.ea.wwce.automation.gcn.pageobjects.GcOppJoinPage;
import com.ea.wwce.automation.gcn.pageobjects.GcOpportunityPage;
import com.ea.wwce.automation.gcn.pageobjects.GcPrivacyPage;
import com.ea.wwce.automation.gcn.pageobjects.GcProgramPage;
import com.ea.wwce.automation.gcn.pageobjects.GcServiceUpdatesPage;
import com.ea.wwce.automation.gcn.pageobjects.GcTermsPage;
import com.ea.wwce.automation.gcn.pageobjects.GcnAdminContentPage;
import com.ea.wwce.automation.gcn.pageobjects.GcnAdminHomePage;
import com.ea.wwce.automation.gcn.pageobjects.GcnAdminLoginPage;
import com.ea.wwce.automation.gcn.pageobjects.GcnAdminOperationsPage;
import com.ea.wwce.automation.gcn.pageobjects.GcnAdminPage;
import com.ea.wwce.automation.gcn.pageobjects.GcnAdminReportsPage;
import com.ea.wwce.automation.gcn.pageobjects.GcnGcHomePage;
import com.ea.wwce.automation.gcn.pageobjects.GcnGcLoginPage;
import com.ea.wwce.automation.gcn.pageobjects.GcnGcPage;
import com.ea.wwce.automation.gcn.pageobjects.GcnGcPaymentSettingsPage;
import com.ea.wwce.automation.gcn.pageobjects.GcnGcProfilePage;
import com.ea.wwce.automation.gcn.pageobjects.NewOpportunityEventPage;
import com.ea.wwce.automation.gcn.pageobjects.NewOpportunityGeneralPage;

public class GcnBaseTest extends BaseTest{
	
	public static Logger logger=Logger.getLogger(GcnBaseTest.class);
	
	//POM Classess


	protected GcnAdminHomePage gcnAdminHomePage;
	protected GcnAdminLoginPage gcnAdminLoginPage;

	GcnAdminPage gcnAdminPage;
	AdminOpportunityListPage adminOpportunityListPage;
	//AdminNewOpportunityPage adminNewOpportunityPage;
	NewOpportunityGeneralPage newOpportunityGeneralPage;
	NewOpportunityEventPage newOpportunityEventPage;
	AdminOppDetailsPage adminOppDetailsPage;
	AdminOppPaymentsPage adminOppPaymentsPage;
	AdminOppPaymentsReviewPage adminOppPaymentsReviewPage;
	AdminOppSettingsPage adminOppSettingsPage;
	AdminOppUsersPage adminOppUsersPage;
	AdminUsersPage adminUsersPage;
	GcnAdminContentPage gcnAdminContentPage;
	GcnAdminReportsPage gcnAdminReportsPage;
	GcnAdminOperationsPage gcnAdminOperationsPage;
	AdminOppBudgetPage adminOppBudgetPage;
	AdminViewUserProfile adminViewUserProfile;
	
	
	//Test Classes
	

	protected GcnGcHomePage gcnGcHomePage;
	protected GcnGcLoginPage gcnGcLoginPage;

	protected GcnGcPage gcnGcPage;
	GcnGcPaymentSettingsPage gcnGcPaymentSettingsPage;
	protected GcOppDetailsPage gcOppDetailsPage;
	protected GcOpportunityPage gcOpportunityPage;
	GcnGcProfilePage gcnGcProfilePage;
	GcProgramPage gcProgramPage;
	GcEulaPage gcEulaPage;
	GcLegalNoticesPage gcLegalNoticesPage;
	GcPrivacyPage gcPrivacyPage;
	GcServiceUpdatesPage gcServiceUpdatesPage;
	GcTermsPage gcTermsPage;
	protected GcOppJoinPage gcOppJoinPage;
	

	// Define Page Objects
	public void loadPageObjects(WebDriver driver) {
		gcnAdminHomePage=new GcnAdminHomePage(driver);
		gcnAdminLoginPage=new GcnAdminLoginPage(driver);
		gcnAdminPage=new GcnAdminPage(driver);
		adminOpportunityListPage=new AdminOpportunityListPage(driver);
		//adminNewOpportunityPage=new AdminNewOpportunityPage(driver);
		newOpportunityGeneralPage= new NewOpportunityGeneralPage(driver);
		newOpportunityEventPage=new NewOpportunityEventPage(driver);
		adminOppDetailsPage=new AdminOppDetailsPage(driver);
		adminOppPaymentsPage=new AdminOppPaymentsPage(driver);
		adminOppPaymentsReviewPage=new AdminOppPaymentsReviewPage(driver);
		adminOppSettingsPage= new AdminOppSettingsPage(driver);
		adminOppUsersPage=new AdminOppUsersPage(driver);
		adminUsersPage=new AdminUsersPage(driver);
		gcnAdminContentPage= new GcnAdminContentPage(driver);
		gcnAdminReportsPage= new GcnAdminReportsPage(driver);
		gcnAdminOperationsPage = new GcnAdminOperationsPage(driver);
		adminOppBudgetPage = new AdminOppBudgetPage(driver);
		adminViewUserProfile= new AdminViewUserProfile(driver);
		
		gcnGcHomePage=new GcnGcHomePage(driver);
		gcnGcLoginPage=new GcnGcLoginPage(driver);
		gcnGcPage=new GcnGcPage(driver);
		gcnGcPaymentSettingsPage=new GcnGcPaymentSettingsPage(driver);
		gcOppDetailsPage=new GcOppDetailsPage(driver);
		gcOpportunityPage=new GcOpportunityPage(driver);
		gcnGcProfilePage= new GcnGcProfilePage(driver);
		gcProgramPage=new GcProgramPage(driver);
		gcEulaPage = new GcEulaPage(driver);
		gcLegalNoticesPage = new GcLegalNoticesPage(driver);
		gcPrivacyPage = new GcPrivacyPage(driver);
		gcServiceUpdatesPage = new GcServiceUpdatesPage(driver);
		gcTermsPage = new GcTermsPage(driver);
		gcOppJoinPage=new GcOppJoinPage(driver);
	}
	
	@BeforeSuite(alwaysRun=true)
	public void beforeSuite(ITestContext context) {
		
		String testDataFilePath=GcnDataConstants.PROJECT_ROOT_LOCATION + GcnDataConstants.GCN_TEST_DATA_ROOT_PATH +
				GcnDataConstants.GCN_TEST_DATA_FILE_NAME;

		//Load the test data needed for the suite
				
		context.setAttribute("testDataFilePath", testDataFilePath);
		
		super.beforeSuite(context);
		
		// Configuration of Test Rail
		// Create Test Rail Client connection object and pass the plan id in TestNg.xml
		
		TestRailClient testRailClient = new TestRailClient(GcnDataConstants.GCN_TESTRAIL_CONNECTION_URL,
				GcnDataConstants.GCN_TESTRAIL_USERNAME, GcnDataConstants.GCN_TESTRAIL_PASSWORD);

		context.setAttribute("testRailClientObject", testRailClient);
		
	}
	
	// Load URL as per Test Environment
	@BeforeClass
	public void beforeClass(ITestContext context) {
		super.beforeClass(context);
		
		loadPageObjects(driver);
		driver.manage().timeouts().pageLoadTimeout(GcnDataConstants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(GcnDataConstants.IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//Set Environment
		String environment=this.testEnvironment;

		if(environment.equalsIgnoreCase("QAPC")) {
			context.setAttribute("BASE_ADMIN_SERVICE_URL", GcnDataConstants.GCN_BASE_URL_ADMIN_QAPC);
			context.setAttribute("BASE_GC_SERVICE_URL", GcnDataConstants.GCN_BASE_URL_GC_QAPC);
					
		}else if(environment.equalsIgnoreCase("PROD")) {
			context.setAttribute("BASE_ADMIN_SERVICE_URL", GcnDataConstants.GCN_BASE_URL_ADMIN_PROD);
			context.setAttribute("BASE_GC_SERVICE_URL", GcnDataConstants.GCN_BASE_URL_GC_PROD);
		}
		
		GcnDataConstants.GCN_ADMIN_AUT_URL= context.getAttribute("BASE_ADMIN_SERVICE_URL").toString();
		GcnDataConstants.GCN_GC_AUT_URL= context.getAttribute("BASE_GC_SERVICE_URL").toString();
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
