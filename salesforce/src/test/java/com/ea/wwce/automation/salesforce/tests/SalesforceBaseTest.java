package com.ea.wwce.automation.salesforce.tests;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.ea.wwce.automation.base.tests.BaseTest;
import com.ea.wwce.automation.salesforce.config.SalesforceDataConstants;
import com.ea.wwce.automation.salesforce.pageobjects.AdvisorProfilePage;
import com.ea.wwce.automation.salesforce.pageobjects.SalesforceHomePage;
import com.ea.wwce.automation.salesforce.pageobjects.SalesforceLoginPage;

/**
 * 
 * @author sadabala
 *
 */
public class SalesforceBaseTest extends BaseTest {

	public static Logger logger = Logger.getLogger(SalesforceBaseTest.class);

	SalesforceLoginPage salesforceLoginPage;
	SalesforceHomePage salesforceHomePage;
	AdvisorProfilePage advisorProfilePage;

	// Define the page objects relevant the application Under Test
	public void loadPageObjects(WebDriver driver) {
		salesforceLoginPage = new SalesforceLoginPage(driver);
		salesforceHomePage = new SalesforceHomePage(driver);
		advisorProfilePage = new AdvisorProfilePage(driver);
	}

	@BeforeSuite
	public void beforeSuite(ITestContext context) {
		// Load the test data needed for the suite
		String testDataFilePath = SalesforceDataConstants.PROJECT_ROOT_LOCATION
				+ SalesforceDataConstants.SF_TEST_DATA_ROOT_PATH + SalesforceDataConstants.SF_TEST_DATA_FILE_NAME;
		context.setAttribute("testDataFilePath", testDataFilePath);
		super.beforeSuite(context);
	}

	// Create a driver instance and load the page objects needed for the test
	@BeforeClass
	public void beforeClass(ITestContext context) {
		super.beforeClass(context);

		loadPageObjects(driver);
		driver.manage().timeouts().pageLoadTimeout(SalesforceDataConstants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(SalesforceDataConstants.IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		String environment = this.testEnvironment;

		if (environment.equalsIgnoreCase("QAPC")) {
			context.setAttribute("BASE_SERVICE_URL", SalesforceDataConstants.SF_BASE_URL_QAPC);
		} else if (environment.equalsIgnoreCase("RUNFC")) {
			context.setAttribute("BASE_SERVICE_URL", SalesforceDataConstants.SF_BASE_URL_RUNFC);
		} else if (environment.equalsIgnoreCase("UATPC")) {
			context.setAttribute("BASE_SERVICE_URL", SalesforceDataConstants.SF_BASE_URL_UATPC);
		} else if (environment.equalsIgnoreCase("PROD")) {
			context.setAttribute("BASE_SERVICE_URL", SalesforceDataConstants.SF_BASE_URL_PROD);
		} else {
			context.setAttribute("BASE_SERVICE_URL", SalesforceDataConstants.SF_BASE_URL_QAPC);
		}

		SalesforceDataConstants.SF_AUT_URL = context.getAttribute("BASE_SERVICE_URL").toString();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
