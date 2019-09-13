package com.shell.markethub.uam.tests;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.shell.markethub.base.pageobjects.BasePageObject;
import com.shell.markethub.base.util.Assertions;
import com.shell.markethub.base.util.BaseSetup;
import com.shell.markethub.base.util.BaseTest;
import com.shell.markethub.base.util.config.BaseDataConstants;
import com.shell.markethub.base.util.listeners.BaseListener;
import com.shell.markethub.integration.pageobjects.HomePage;
import com.shell.markethub.integration.pageobjects.LoginPage;
import com.shell.markethub.uam.pageobjects.SubmitAndConfirmPage;
import com.shell.markethub.uam.pageobjects.TransactRolesPage;
import com.shell.markethub.uam.pageobjects.RegisterCustomerUserPage;
import com.shell.markethub.uam.pageobjects.SearchCustomerUserPage;
import com.shell.markethub.uam.pageobjects.SetAccessAndTargetingAttributesPage;
import com.shell.markethub.uam.pageobjects.UserDetailsPage;

/**
 * 
 * @author N.Kumar8@shell.com
 * 
 */
public class UAMBaseTest extends BaseTest{

	protected LoginPage loginPage;
	protected HomePage homePage;
	protected BaseSetup baseSetup;
	protected SearchCustomerUserPage searchCustomerUserPage;
	protected RegisterCustomerUserPage registerCustomerUserPage;
	protected UserDetailsPage userDetailsPage;
	protected SetAccessAndTargetingAttributesPage setAccessAndTargetingAttributesPage;
	protected SubmitAndConfirmPage submitAndConfirmPage;
	protected TransactRolesPage transactRolesPage;
	protected BasePageObject basePageObject;
	
	@BeforeMethod
	public void setUp() {
		loadPageObjects();
	}
	
	/**
	 * @param context
	 * @throws IOException 
	 * @description This class will be called immediately after running testng.xml and 
	 * baseSetup beforesuite method will be overridden
	 */
	@Test
	public void beforeSuite(ITestContext context) throws IOException {
		baseSetup = new BaseSetup();
		baseSetup.beforeSuite(context);
	}
	
	/** 
	 * @param driver
	 * @description This method will load all page objects
	 */
	public void loadPageObjects() {
		loginPage = new LoginPage();
		homePage = new HomePage();
		searchCustomerUserPage = new SearchCustomerUserPage();
		registerCustomerUserPage = new RegisterCustomerUserPage();
		setAccessAndTargetingAttributesPage = new SetAccessAndTargetingAttributesPage();
		userDetailsPage = new UserDetailsPage();
		submitAndConfirmPage = new SubmitAndConfirmPage();
		transactRolesPage = new TransactRolesPage();
		basePageObject = new BasePageObject();
	}
}
