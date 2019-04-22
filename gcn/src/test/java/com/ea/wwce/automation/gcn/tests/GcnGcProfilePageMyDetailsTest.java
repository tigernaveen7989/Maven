package com.ea.wwce.automation.gcn.tests;

import java.awt.AWTException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnGcProfilePageMyDetailsTest extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(GcnGcProfilePageMyDetailsTest.class);
	
	
	/*
	 * Class to verify updation of My Details in Profile Page
	 */
	
	@Test
	public void verifyProfilePageTest(ITestContext context) throws AWTException, InterruptedException {
		Map<String,Object> testData=new HashMap<String,Object>();
		String testID="27327,27333,27334,27335,27337,27340";
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider=(DataProviders)context.getAttribute("dataProvider");
		testData=dataProvider.getTestData(testID);
		
		gcnGcHomePage.launchGcAndSignIn();
		
		gcnGcPage=gcnGcLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
		
		// check navigating to profile page.
		gcnGcPage.clickOnProfileLink();
		assertTrue(gcnGcProfilePage.verifyNavigationToProfilePage(), "Error Navigating to Profile Page");
		
		//verify my details updation
		assertTrue(gcnGcProfilePage.verifyMyDetailsUpdation(testData.get("fname2").toString(), 
				testData.get("lname2").toString(), testData.get("dob").toString(), 
				testData.get("invalidusername1").toString()), "Error updating My Details Section.");
		
		// reset the fields
		assertTrue(gcnGcProfilePage.verifyMyDetailsUpdation(testData.get("fname1").toString(), 
				testData.get("lname1").toString(), testData.get("dob").toString(), 
				testData.get("username").toString()), "Error updating My Details Section.");
		
		//verify username is not editable
		assertFalse(gcnGcProfilePage.verifyUserNameEditable(), "Username is Editable");
		
		assertTrue(gcnGcProfilePage.uploadAndSaveProfilePic(), "Error in changing profile Pic");
		
		assertAll();
	}
	

}
