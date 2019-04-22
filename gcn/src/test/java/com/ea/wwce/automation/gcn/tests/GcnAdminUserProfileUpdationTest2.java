package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class GcnAdminUserProfileUpdationTest2 extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(GcnAdminUserProfileUpdationTest2.class);
	
	/*
	 * Test Class to verify the Updation of Game Changer Details by Admin.
	 * First Name, Last Name, Payment Method,
	 */

	@Test
	@Description("Verify the Updation of Game Changer Details by Admin")
	public void verifyGcProfileUpdation(ITestContext context) throws InterruptedException {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="21263,21275,21265";
		
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
		assertTrue(adminViewUserProfile.verifyFullnamePaymentTypeUpdation(testData.get("fname2").toString(),
				testData.get("lname2").toString(), testData.get("paytype2").toString()), "Error updating Full Name and Payment Type");
		
		// Restore with correct data.
		assertTrue(adminViewUserProfile.verifyFullnamePaymentTypeUpdation(testData.get("fname_nihal").toString(),
				testData.get("lname_nihal").toString(), testData.get("paytype_nihal").toString()), "Error updating Full Name and Payment Type");
		
		assertFalse(adminViewUserProfile.verifyUserNameEditable(), "UserName is Editable");
		
		assertFalse(adminViewUserProfile.verifyLangShirtSizeUpdation(), "UserName is Editable");
		
		assertAll();
	}
}
