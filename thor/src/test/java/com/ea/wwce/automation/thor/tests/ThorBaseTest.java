package com.ea.wwce.automation.thor.tests;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.ea.wwce.automation.base.tests.BaseTest;
import com.ea.wwce.automation.base.util.jsonhandlers.JsonFileProcessor;
import com.ea.wwce.automation.base.util.testmanagement.TestRailClient;
import com.ea.wwce.automation.thor.config.ThorDataConstants;
import com.ea.wwce.automation.thor.pageobjects.CaseDetailPage;
import com.ea.wwce.automation.thor.pageobjects.CaseHistoryPage;
import com.ea.wwce.automation.thor.pageobjects.ChooseYourRolePage;
import com.ea.wwce.automation.thor.pageobjects.FillYourQueuePage;
import com.ea.wwce.automation.thor.pageobjects.GooglePage;
import com.ea.wwce.automation.thor.pageobjects.OmniChannelWidgetPage;
import com.ea.wwce.automation.thor.pageobjects.PlayerAccountPage;
import com.ea.wwce.automation.thor.pageobjects.PlayerSearchPage;
import com.ea.wwce.automation.thor.pageobjects.SFAdminPage;
import com.ea.wwce.automation.thor.pageobjects.SFAdvisorProfileManagementPage;
import com.ea.wwce.automation.thor.pageobjects.SFClassicCRMEventsPage;
import com.ea.wwce.automation.thor.pageobjects.SFClassicCasesObjectPage;
import com.ea.wwce.automation.thor.pageobjects.SFClassicDataManagementPage;
import com.ea.wwce.automation.thor.pageobjects.SFClassicHomePage;
import com.ea.wwce.automation.thor.pageobjects.SFClassicSearchPage;
import com.ea.wwce.automation.thor.pageobjects.SFClassicSetupPage;
import com.ea.wwce.automation.thor.pageobjects.SFQueueManagementPage;
import com.ea.wwce.automation.thor.pageobjects.ThorLoginPage;
import com.ea.wwce.automation.thor.pageobjects.ThorPetitionDetailsPage;
import com.ea.wwce.automation.thor.pageobjects.ThorPetitionPage;
import com.ea.wwce.automation.thor.pageobjects.ThorReporterContentPage;


/**
 * 
 * @author Mohan
 *
 */
public  class ThorBaseTest extends BaseTest{
	
	public static Logger logger = Logger.getLogger(ThorBaseTest.class);
	JsonFileProcessor fileProcessor;
	ThorLoginPage thorLoginPage;
	ThorPetitionPage thorPetitionPage;
	ThorPetitionDetailsPage thorPetitionPageDetails;
	ThorReporterContentPage thorReportedPage;
	SFAdminPage sfAdminPage;
	ChooseYourRolePage chooseUrRolePage;
	FillYourQueuePage fillUrQueuePage;
	OmniChannelWidgetPage omniChannelWidgetPage;
	PlayerAccountPage playerAccountPage;
	SFClassicHomePage classicHomePage;
	SFClassicSearchPage classicSearchPage;
	SFClassicCRMEventsPage classicCRMEventsPage;
	SFClassicCasesObjectPage classicCasesPage;
	PlayerSearchPage playerSearchPage;
	CaseDetailPage caseDetailPage;
	SFAdvisorProfileManagementPage sfAdvProfileMangePage;
	SFClassicDataManagementPage classicDataManagement;
	SFClassicSetupPage classicSetupPage;
	CaseHistoryPage caseHistoryPage;
	GooglePage googlePage;
	SFQueueManagementPage sfQueueMgtPage;
	
	
	//Define the page objects relevant the application Under Test
	public void loadPageObjects(WebDriver driver){
		thorLoginPage = new ThorLoginPage(driver);	
		thorPetitionPage = new ThorPetitionPage(driver);
		thorPetitionPageDetails = new ThorPetitionDetailsPage(driver);
		thorReportedPage= new ThorReporterContentPage(driver);
		sfAdminPage= new SFAdminPage(driver);
		chooseUrRolePage=new ChooseYourRolePage(driver);
		fillUrQueuePage=new FillYourQueuePage(driver);
		omniChannelWidgetPage=new OmniChannelWidgetPage(driver);
		playerAccountPage=new PlayerAccountPage(driver);
		classicHomePage=new SFClassicHomePage(driver);
		classicSearchPage=new SFClassicSearchPage(driver);
		classicCRMEventsPage=new SFClassicCRMEventsPage(driver);
		classicCasesPage=new SFClassicCasesObjectPage(driver);
		playerSearchPage=new PlayerSearchPage(driver);
		caseDetailPage=new CaseDetailPage(driver);
		classicDataManagement=new SFClassicDataManagementPage(driver);
		sfAdvProfileMangePage=new SFAdvisorProfileManagementPage(driver);
		classicSetupPage=new SFClassicSetupPage(driver);
		caseHistoryPage=new CaseHistoryPage(driver);
		googlePage=new GooglePage(driver);
		sfQueueMgtPage=new SFQueueManagementPage(driver);
	}	
	
	@BeforeSuite
	public void beforeSuite(ITestContext context){
		
		//Load the test data needed for the suite
		String testDataFilePath = ThorDataConstants.PROJECT_ROOT_LOCATION + 
				ThorDataConstants.THOR_TEST_DATA_ROOT_PATH +
				ThorDataConstants.THOR_TEST_DATA_FILE_NAME;	
		
		context.setAttribute("testDataFilePath", testDataFilePath);
		
		//keeping this line here as we need to override global test rail configuration
		super.beforeSuite(context);		
		
		// Create Test Rail Client connection object and pass the plan id
		TestRailClient testRailClient = new TestRailClient(ThorDataConstants.THOR_TESTRAIL_CONNECTION_URL,
		ThorDataConstants.THOR_TESTRAIL_USERNAME, ThorDataConstants.THOR_TESTRAIL_PASSWORD);
		context.setAttribute("testRailClientObject", testRailClient);
		
		//super.beforeSuite(context);	
	}
	
	//Create a driver instance and load the page objects needed for the test
	@BeforeClass
	public void beforeClass(ITestContext context){
		super.beforeClass(context); 
		loadPageObjects(driver);	
		driver.manage().timeouts().pageLoadTimeout(ThorDataConstants.PAGE_LOAD_TIMEOUT,
				TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(ThorDataConstants.IMPLICIT_TIMEOUT,
				TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		String environment = this.testEnvironment;	
		
		if(environment.equalsIgnoreCase("QAPC")){
			context.setAttribute("BASE_SERVICE_URL", ThorDataConstants.THOR_BASE_URL_QAPC);
		}else if(environment.equalsIgnoreCase("RUNFC")) {
				context.setAttribute("BASE_SERVICE_URL", ThorDataConstants.THOR_BASE_URL_RUNFC);
		}else if(environment.equalsIgnoreCase("UATPC")) {
			context.setAttribute("BASE_SERVICE_URL", ThorDataConstants.THOR_BASE_URL_UATPC);
		}else if(environment.equalsIgnoreCase("PROD")) {
			context.setAttribute("BASE_SERVICE_URL", ThorDataConstants.THOR_BASE_URL_PROD);
		}else{
			context.setAttribute("BASE_SERVICE_URL", ThorDataConstants.THOR_BASE_URL_QAPC);
		}
		
		ThorDataConstants.THOR_AUT_URL = context.getAttribute("BASE_SERVICE_URL").toString();
	}
	
	@AfterClass
	public void afterClass(){
		driver.quit();
	}

	
}
