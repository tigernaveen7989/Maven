package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnAdminLoginTest extends GcnBaseTest {

	public static Logger logger = Logger.getLogger(GcnAdminLoginTest.class);
	
	/*This class is to check Login functionality for GCN Admin WebSite.
	 * 
	 * TC: 16174 -Verify Admin is able to login into GCN Admin Application 
	 * 
	 * 
	 * */	

	@Test
	public void gcnAdminSignInTest(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID = "16174";

		context.setAttribute("testcase_id", testID);
		
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();

		gcnAdminLoginPage.verifyLogin(testData.get("username").toString(),
				testData.get("password").toString());

		// check if admin lands to home page after login.
		
		assertTrue(gcnAdminPage.verifyNavigationToAdminPage(), "GCN Admin Login Failed ");
		
		assertTrue(gcnAdminPage.verifyAdminPageTitle(), "Admin Page Title Mismatch");
		

		assertAll();

	}

	
}
