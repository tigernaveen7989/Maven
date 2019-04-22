package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnGcProfilePageTest extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(GcnGcProfilePageTest.class);
	
	
	/*Class to verify profile page UI and Navigation.  
	 */
	
	@Test
	public void verifyProfilePageTest(ITestContext context) throws InterruptedException {
		Map<String,Object> testData=new HashMap<String,Object>();
		String testID="18534,18539,20707,20728,20753,42438,42442,42443";
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider=(DataProviders)context.getAttribute("dataProvider");
		testData=dataProvider.getTestData(testID);
		
		gcnGcHomePage.launchGcAndSignIn();
		
		gcnGcPage=gcnGcLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
		
		// check navigating to profile page.
		gcnGcPage.clickOnProfileLink();
		assertTrue(gcnGcProfilePage.verifyNavigationToProfilePage(), "Error Navigating to Profile Page");
		
		assertTrue(gcnGcProfilePage.verifyLoggedInUserName(testData.get("uid").toString()), "UserName mismatch");
		
		assertTrue(gcnGcProfilePage.verifyProfilePictureSet(), "Profile picture is Not Set");
		
		assertTrue(gcnGcProfilePage.verifyProfilePageUi(), "Profile Page UI Not Propper");
		
		
		// check availability of Menu links on Profile Page;
		
		assertTrue(gcnGcPage.verifyAvailabilityOfMenuLinks(),"GameChanger Menu Links not Displayed");
		
		// Check Navigation to Home Page.
		gcnGcPage.clickOnHomeLink();
		assertTrue(gcnGcPage.verifyNavigationToGcPage(), "Error Navigating to Home Page");
		gcnGcPage.clickOnProfileLink();
		
		// Check Navigation to Program Page.
		gcnGcPage.clickOnProgramLink();
		assertTrue(gcProgramPage.verifyNavigationToProgramPage(), "Error Navigating to Profile Page");
		gcnGcPage.clickOnProfileLink();
		
		// Check Navigation to Opportunity Page.
		gcnGcPage.goToOpportunitiesPage();
		assertTrue(gcOpportunityPage.verifyNavigationToOpportunityPage(), "Error Navigating to Opportunity Page");
		gcnGcPage.clickOnProfileLink();
		
		// Check for logout
		gcnGcPage.clickOnLogout();
		assertTrue(gcnGcHomePage.verifyNavigationToGcHomePage(), "Not Landed to Home Page of GC Site");
			
			
		assertAll();
	}
	

}
