package com.ea.wwce.automation.tsm.tests;


import com.ea.wwce.automation.base.tests.BaseTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import java.util.concurrent.TimeUnit;
import com.ea.wwce.automation.base.util.jsonhandlers.JsonFileProcessor;
import com.ea.wwce.automation.base.util.testmanagement.TestRailClient;
import com.ea.wwce.automation.tsm.config.TSMDataConstants;
import com.ea.wwce.automation.tsm.pageobjects.TSMAccountBasicInfoPage;
import com.ea.wwce.automation.tsm.pageobjects.TSMAddAccountNotesPage;
import com.ea.wwce.automation.tsm.pageobjects.TSMAdvisorStatesPage;
import com.ea.wwce.automation.tsm.pageobjects.TSMCreateCasePage;
import com.ea.wwce.automation.tsm.pageobjects.TSMJobRoleSelectionPage;
import com.ea.wwce.automation.tsm.pageobjects.TSMLoginPage;


public  class TSMBaseTest extends BaseTest {
	
	public static Logger logger = Logger.getLogger(TSMBaseTest.class);
	TSMLoginPage tsmLoginPage;
	TSMAdvisorStatesPage tsmAdvisorStatesPage;
	TSMJobRoleSelectionPage tsmJobRoleSelectionPage;
	TSMCreateCasePage tsmCreateCasePage;
	TSMAccountBasicInfoPage tsmAccountBasicInfoPage;
	TSMAddAccountNotesPage tsmAddAccountNotesPage;
	
	JsonFileProcessor fileProcessor;
	
	//Define the page objects relevant the application Under Test
	public void loadPageObjects(WebDriver driver){
		tsmLoginPage = new TSMLoginPage(driver);
		tsmAdvisorStatesPage = new TSMAdvisorStatesPage(driver);
		tsmJobRoleSelectionPage = new TSMJobRoleSelectionPage(driver);
		tsmCreateCasePage = new TSMCreateCasePage(driver);
		tsmAccountBasicInfoPage = new TSMAccountBasicInfoPage(driver);
		tsmAddAccountNotesPage = new TSMAddAccountNotesPage(driver);
		
	}	
	
	@BeforeSuite
	public void beforeSuite(ITestContext context){
		
		// Load the test data needed for the suite
				String testDataFilePath = TSMDataConstants.PROJECT_ROOT_LOCATION
						+ TSMDataConstants.TSM_TEST_DATA_ROOT_PATH + TSMDataConstants.TSM_TEST_DATA_FILE_NAME;

				context.setAttribute("testDataFilePath", testDataFilePath);
				/* context.setAttribute("testDataFilePath", attachmentFilePath); */
				
				//Call base before suite using super as testrail connection needs to be override
				super.beforeSuite(context);

				// Create Test Rail Client connection object and pass the plan id
				TestRailClient testRailClient = new TestRailClient(TSMDataConstants.TSM_TESTRAIL_CONNECTION_URL,
						TSMDataConstants.TSM_TESTRAIL_USERNAME, TSMDataConstants.TSM_TESTRAIL_PASSWORD);

				context.setAttribute("testRailClientObject", testRailClient);
		
		
	}
	
	//Create a driver instance and load the page objects needed for the test
	@BeforeClass
	public void beforeClass(ITestContext context){
		super.beforeClass(context); 
		loadPageObjects(driver);	
		driver.manage().timeouts().pageLoadTimeout(TSMDataConstants.PAGE_LOAD_TIMEOUT,
				TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TSMDataConstants.IMPLICIT_TIMEOUT,
				TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		String environment = this.testEnvironment;	
		
		if(environment.equalsIgnoreCase("QA3PC")){
			context.setAttribute("BASE_SERVICE_URL", TSMDataConstants.TSM_BASE_URL_QA3PC);
		}else if(environment.equalsIgnoreCase("RUNFC")) {
				context.setAttribute("BASE_SERVICE_URL", TSMDataConstants.TSM_BASE_URL_RUNFC);
		}else if(environment.equalsIgnoreCase("UATPC")) {
			context.setAttribute("BASE_SERVICE_URL", TSMDataConstants.TSM_BASE_URL_UATPC);
		}else if(environment.equalsIgnoreCase("PROD")) {
			context.setAttribute("BASE_SERVICE_URL", TSMDataConstants.TSM_BASE_URL_PROD);
		}else{
			context.setAttribute("BASE_SERVICE_URL", TSMDataConstants.TSM_BASE_URL_QA3PC);
		}
		
		TSMDataConstants.TSM_AUT_URL = context.getAttribute("BASE_SERVICE_URL").toString();
	}
	
	@AfterClass
	public void afterClass(){
		driver.quit();
	}

	
}
