package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class OpportunityGeneralPageTest extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(OpportunityGeneralPageTest.class);
	/*
	 * This class is to verify the functionality of opportunity general tab
	 */
	
	
	@Test
	public void fillGeneralTab(ITestContext context) throws InterruptedException {
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="112";
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
				
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
		
		gcnAdminPage=gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
		
		// Navigate to Admin Opportunities Page
		gcnAdminPage.navToAdminOpportunityPage();
		
		adminOpportunityListPage.clickOnCreateNew();
		
		assertTrue(newOpportunityGeneralPage.isOppPreviewImgDisplayed(), "Opportunity Preview Image is Not Displayed");
			
		newOpportunityGeneralPage.clickOnImgNxtBtn();
		assertTrue(newOpportunityGeneralPage.isImgPrevBtnDisplayed(), "Prev button not Displayed");
		
		assertTrue(newOpportunityGeneralPage.isOppPreviewImgDisplayed(), "Opp Preview Image not Set");
		
		newOpportunityGeneralPage.fillOppGeneralTab(testData.get("Visibility_yes").toString(),
				testData.get("ApprovalReq_no").toString(),testData.get("OppoDesc1").toString());
		
		newOpportunityGeneralPage.clickOnOkBtn();
		
		assertAll();
		
	}
	
}
