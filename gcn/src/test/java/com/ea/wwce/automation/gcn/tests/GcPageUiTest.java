package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcPageUiTest extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(GcPageUiTest.class);
	
	/*This class is to check the GameChanger landing page, after Login.
	 * 
	 * TC: 18531 - Verify the functionality of Profile link in Game changer Home screen
	 * */
	
	
	@Test
	public void verifyGcPageUiTest(ITestContext context) {
		
		// Login to Game Changer application
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="18531";
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);		

		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnGcHomePage.launchGcAndSignIn();
		
		gcnGcPage=gcnGcLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
		
		assertTrue(gcnGcPage.verifyGcPageTitle(), "GC Page Title Mismatch");

		// UI Assertions
		
		assertTrue(gcnGcPage.verifyAvailabilityOfMenuLinks(),"GameChanger Menu Links not Displayed");
		
		gcnGcPage.clickOnProfileLink();
		assertTrue(gcnGcProfilePage.verifyProfilePageUi(), "Profile Page UI Not Propper");
		
		assertAll();
		
		
	}

}
