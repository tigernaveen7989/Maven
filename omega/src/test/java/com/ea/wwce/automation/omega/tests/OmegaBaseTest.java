package com.ea.wwce.automation.omega.tests;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.ea.wwce.automation.base.tests.BaseTest;
import com.ea.wwce.automation.omega.config.OmegaDataConstants;
import com.ea.wwce.automation.omega.pageobjects.OmegaAgentHomePage;
import com.ea.wwce.automation.omega.pageobjects.OmegaLoginPage;
import com.ea.wwce.automation.omega.pageobjects.OmegaRolesContainerPage;

/**
 * 
 * @author sadabala
 *
 */
public class OmegaBaseTest extends BaseTest {

	public static Logger logger = Logger.getLogger(OmegaBaseTest.class);

	OmegaLoginPage omegaLoginPage;
	OmegaRolesContainerPage omegRolesContainerPage;
	OmegaAgentHomePage omegaAgentHomePage;

	// Define the page objects relevant the application Under Test
	public void loadPageObjects(WebDriver driver) {
		omegaLoginPage = new OmegaLoginPage(driver);
		omegRolesContainerPage = new OmegaRolesContainerPage(driver);
		omegaAgentHomePage = new OmegaAgentHomePage(driver);
	}

	@BeforeSuite
	public void beforeSuite(ITestContext context) {
		// Load the test data needed for the suite
		String testDataFilePath = OmegaDataConstants.PROJECT_ROOT_LOCATION
				+ OmegaDataConstants.OMEGA_TEST_DATA_ROOT_PATH + OmegaDataConstants.OMEGA_TEST_DATA_FILE_NAME;
		context.setAttribute("testDataFilePath", testDataFilePath);
		super.beforeSuite(context);
	}

	// Create a driver instance and load the page objects needed for the test
	@BeforeClass
	public void beforeClass(ITestContext context) {
		super.beforeClass(context);

		loadPageObjects(driver);
		driver.manage().timeouts().pageLoadTimeout(OmegaDataConstants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(OmegaDataConstants.IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

		String environment = this.testEnvironment;

		if (environment.equalsIgnoreCase("QAPC")) {
			context.setAttribute("BASE_SERVICE_URL", OmegaDataConstants.OMEGA_BASE_URL_QAPC);
		} else if (environment.equalsIgnoreCase("RUNFC")) {
			context.setAttribute("BASE_SERVICE_URL", OmegaDataConstants.OMEGA_BASE_URL_RUNFC);
		} else if (environment.equalsIgnoreCase("UATPC")) {
			context.setAttribute("BASE_SERVICE_URL", OmegaDataConstants.OMEGA_BASE_URL_UATPC);
		} else if (environment.equalsIgnoreCase("PROD")) {
			context.setAttribute("BASE_SERVICE_URL", OmegaDataConstants.OMEGA_BASE_URL_PROD);
		} else {
			context.setAttribute("BASE_SERVICE_URL", OmegaDataConstants.OMEGA_BASE_URL_QAPC);
		}

		OmegaDataConstants.OMEGA_AUT_URL = context.getAttribute("BASE_SERVICE_URL").toString();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
