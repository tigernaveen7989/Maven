package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class GcnAdminUserProfileBasicDetailsTest extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(GcnAdminUserProfileBasicDetailsTest.class);
	
	/*
	 * Test Class to verify the Basic Game Changer UI and Details from Admin view.
	 */

	@Test
	@Description("Verify the basic user details of Game Changer from Admin view")
	public void verifyBasicProfileDetails(ITestContext context) {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="21251,21252,21253";
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
		gcnAdminPage=gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
		
		gcnAdminPage.clickOnUsersLink();
		
		assertTrue(adminUsersPage.verifyNavigationToUsersPage(), "Error Navigating to Users Tab");
		
		assertTrue(adminUsersPage.verifyProfilePageByUserName(testData.get("nihaluid").toString()), "Error Navigating to User Page");
		
		assertTrue(adminViewUserProfile.verifyLeftNavLinks(), "Navigation Links Missing");
		
		assertTrue(adminViewUserProfile.verifyUserProfileDetails(),"Error in Profile Details");
		
		assertTrue(adminViewUserProfile.verifyUserPersonalDetails(),"Error in Personal Details");
		
		assertTrue(adminViewUserProfile.verifyTerritoryIdDatePocDetails(), "Error in Data");
		
		
		
		assertAll();
	}
}
