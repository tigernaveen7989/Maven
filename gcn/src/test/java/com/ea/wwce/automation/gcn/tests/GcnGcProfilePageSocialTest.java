package com.ea.wwce.automation.gcn.tests;

import java.awt.AWTException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnGcProfilePageSocialTest extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(GcnGcProfilePageSocialTest.class);
	
	
	/*
	 * Class to Test verification of Game Preferences in Game Changer Profile page.
	 */
	
	@Test
	public void verifyProfilePageSocialTest(ITestContext context) throws AWTException, InterruptedException {
		Map<String,Object> testData=new HashMap<String,Object>();
		String testID="27350";
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider=(DataProviders)context.getAttribute("dataProvider");
		testData=dataProvider.getTestData(testID);
		
		gcnGcHomePage.launchGcAndSignIn();
		
		gcnGcPage=gcnGcLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
		
		// check navigating to profile page.
		gcnGcPage.clickOnProfileLink();
		assertTrue(gcnGcProfilePage.verifyNavigationToProfilePage(), "Error Navigating to Profile Page");
		
		assertTrue(gcnGcProfilePage.verifySocialChannelIcons(), "Social Channels Missing");
		
		assertAll();
	}
	

}
