package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class GcnAdminUserProfileUpdationTest extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(GcnAdminUserProfileUpdationTest.class);
	
	/*
	 * Test Class to verify the Updation of Game Changer Details by Admin.
	 * Country,Territory,Contact Email,Paypal Email,Certificate Number,DoB
	 */

	@Test
	@Description("Verify the Updation of Game Changer Details by Admin")
	public void verifyGcProfileUpdation(ITestContext context) throws InterruptedException {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="21254,21256,21266,21268,21276,21279";
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
		gcnAdminPage=gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
		
		gcnAdminPage.clickOnUsersLink();
		
		assertTrue(adminUsersPage.verifyNavigationToUsersPage(), "Error Navigating to Users Tab");
		
		assertTrue(adminUsersPage.verifyProfilePageByUserName(testData.get("nihaluid").toString()), "Error Navigating to User Page");
		
		// check for updation with test data
		assertTrue(adminViewUserProfile.verifyCertNumDobContEmailUpdation(testData.get("certNum_sunil").toString(),
				testData.get("dob").toString(), testData.get("testmail_cont").toString(),
				testData.get("testmail_pay").toString()),"Error in Updation");
		
		// restore with correct data
		assertTrue(adminViewUserProfile.verifyCertNumDobContEmailUpdation(testData.get("certNum_nihal").toString(),
				testData.get("dob_nihal").toString(), testData.get("usernihalgc2").toString(),
				testData.get("paypal_nihal").toString()),"Error in Updation");
		
		gcnAdminPage.clickOnUsersLink();
		assertTrue(adminUsersPage.verifyNavigationToUsersPage(), "Error Navigating to Users Tab");
		assertTrue(adminUsersPage.verifyProfilePageByUserName(testData.get("sanjeetuid").toString()), "Error Navigating to User Page");
		
		// Check for Territory Updation.
		assertTrue(adminViewUserProfile.verifyTerritoryUpdation(testData.get("country_sanjeet2").toString(),
				testData.get("territory_sanjeet2").toString()), "Error in Territory Updation");
		
		// Restore with correct Data.
		assertTrue(adminViewUserProfile.verifyTerritoryUpdation(testData.get("country_sanjeet1").toString(),
				testData.get("territory_sanjeet1").toString()), "Error in Territory Updation");
		
		assertAll();
	}
}
