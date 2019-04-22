package com.ea.wwce.automation.thor.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import io.qameta.allure.Description;

public class PetitionAccountStatusTest extends ThorBaseTest {

	public static Logger logger = Logger.getLogger(PetitionAccountStatusTest.class);
	@Test(description = "Thor 96, Thor 118, Thor 282 - Verify that beside selected franchise/product respective status is displayed",groups={"regression"})
	@Description("Verify Account Status Details")
	public void verifyAccountStatusDetails(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		//Provide the ID of the test case. This is the ID generated in the TestRail when the manual test case is created.
		testID = "3069,3512,3173,3618,3619,3620,3066,3395,3528,3393,3065,3621";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Account Status Details Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		// validate login
		thorLoginPage.loginToTHOR(testData.get("username").toString(), testData.get("password").toString());
		//Verify page after successful login
		thorPetitionPage.islnkQueuedTabVisible();
		assertTrue(thorPetitionPage.islnkQueuedTabVisible(), "Verify Queued Tab Link visibility successfully");
		//Click on Select Role button
		thorPetitionPage.clickOnSelectRoleButton();
		//Select Role from Select your Role modal
		boolean strStatus1=chooseUrRolePage.verifyExistanceOfChooseUrRoleDialog();
		if (strStatus1) 
			chooseUrRolePage.selectRoleRadioButton(testData.get("roleName").toString());
		else
			assertFalse(strStatus1," Select Your Role modal not displayed");
		
		//Select queue from Fill your Queue modal
		boolean strStatus2=fillUrQueuePage.verifyExistanceOfFillUrQueueDialog();
		if (strStatus2)
			fillUrQueuePage.selectQueueCheckBox(testData.get("queueName").toString());
		else
			assertFalse(strStatus2," Select Your Queue modal not displayed");
			
		//Verify Queued tab visibility
		assertTrue(thorPetitionPage.islnkQueuedTabVisible(), "Verify Queued Tab Link visibility successfully after pull the Queue");
		//Change Advisor status to Available in Omni
		thorPetitionPage.clickOnOmniChannelButton();
		omniChannelWidgetPage.changeOmniStatusToAvailable();
		int intCaseCnt=omniChannelWidgetPage.getCasesCountFrmOmniWidget();
		//System.out.println("Case Count "+intCaseCnt);
		omniChannelWidgetPage.clickOnOmniMinimizeButton();
		
		//Get Cases count from Queue
		int intQueueCnt=thorPetitionPage.getCasesCountFrmQueue();
		//System.out.println("Case Count from Queue "+intQueueCnt);
		
		//Apply Filter on Target Persona Id
		int rcount=thorPetitionPage.getQueueCountAfterFilter(testData.get("filterName").toString(),testData.get("filterValue").toString());
		Assert.assertTrue((rcount>=1),"Threre is no Cases Available in the Queue as per the Filter.");
		
		//Select first row from Queue Table
		thorPetitionPage.selectFirstPetitionFromQueue();
		//Work around for Case details Tab display
		if(!(thorPetitionPage.verifyDisplayOfPetitionPreview()))
		{	thorPetitionPage.clickOnQueueTab();}
		//Click on view Case details button
		thorPetitionPage.clickOnViewCaseDetailsButton();
		//Click on Player Account button
		thorPetitionPageDetails.clickOnPlayerAccountLnk();
		
		//Verify Target Player Details
		String targetPlyerDet=playerAccountPage.verifyTargetPlayerDetails();
		//System.out.println(targetPlyerDet);		
		//assertTrue(!(targetPlyerDet.isEmpty()),"Target Player Details not displayed");
		String [] arrOfStr= {};
		if (!(targetPlyerDet.isEmpty()))
		{
			 arrOfStr = targetPlyerDet.split(",");
		}
		if ((!(arrOfStr[0].isEmpty())) && (!(arrOfStr[1].isEmpty())) && (!(arrOfStr[2].isEmpty())) && (!(arrOfStr[3].isEmpty())) && (!(arrOfStr[4].isEmpty())) && (!(arrOfStr[5].isEmpty())) && (!(arrOfStr[6].isEmpty())) && (!(arrOfStr[7].isEmpty())) && (!(arrOfStr[8].isEmpty())))
				assertTrue(true,"All the Target Player Details displayed");
		else
				assertFalse(false,"Some of the Target Player Details not displayed");
		
		
		//Verify Status Type Values
		assertTrue(playerAccountPage.verifyStatusTypeValues(),"Status Type values not displayed Product and Franchise");
			
		//Select Franchise as Status Type and Select Francise value and verify corresponding products
		
		if (playerAccountPage.selectFranchiseAndVerifyProducts(testData.get("franchiseName").toString()))
			assertTrue(true,"Franchise corresponding Product all details displayed");	
		else
			assertFalse(false,"Franchise corresponding all Product details not displayed\"");	
		
		//Verify Player Persona Table Details
		assertTrue(playerAccountPage.verifyPlayerPersonaTable(),"Player Persona Table details not displayed");
		
		//Verify Major Strikes, Minor Strikes and Petitions Count
		//Get the Existing Major Strike no
		int intExistingMajorStrikeNo=thorPetitionPageDetails.getMajorStrikeCnt();
		//Get the Existing Major Strike no
		int intExistingMinorStrikeNo=thorPetitionPageDetails.getMinorStrikeCnt();
		//Get the Existing Petitions Count
		int intExistingPetitionsCnt=thorPetitionPageDetails.getPetitionsCnt();
		
		assertTrue((intExistingMajorStrikeNo >= 0),"Major Strikes Count Not displayed");
		assertTrue((intExistingMinorStrikeNo >= 0),"Minor Strikes Count Not displayed");
		assertTrue((intExistingPetitionsCnt >= 0),"Petitions Count Not displayed");
			
		assertAll();

	}

}
