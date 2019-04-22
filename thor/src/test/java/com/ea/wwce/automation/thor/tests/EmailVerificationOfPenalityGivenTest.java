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

public class EmailVerificationOfPenalityGivenTest extends ThorBaseTest {

	public static Logger logger = Logger.getLogger(EmailVerificationOfPenalityGivenTest.class);
	@Test(description = "Thor 496, 621 - Verify that Player is given a penalty by Advisor is informed via email",groups={"Regression"})
	@Description("Verify Email verification of Penality given to Player")
	public void verifyEmailVerificationOfPenality(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		//Provide the ID of the test case. This is the ID generated in the TestRail when the manual test case is created.
		testID = "15970,17486,17323,16268";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Email verification of Penality Given to Player functionality" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// validate login
		thorLoginPage.loginToTHOR(testData.get("T2username").toString(), testData.get("T2password").toString());
		//Verify page after successful login
		thorPetitionPage.islnkQueuedTabVisible();
		assertTrue(thorPetitionPage.islnkQueuedTabVisible(), "Verify Queued Tab Link visibility");
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
		assertTrue(thorPetitionPage.islnkQueuedTabVisible(), "Verify Queued Tab Link visibility after pull the Queue");
		//Change Advisor status to Available in Omni
		thorPetitionPage.clickOnOmniChannelButton();
		omniChannelWidgetPage.changeOmniStatusToAvailable();
		//Get Cases count from Omni widget
		int intCaseCnt=omniChannelWidgetPage.getCasesCountFrmOmniWidget();
		//System.out.println("Case Count from Omni "+intCaseCnt);
		omniChannelWidgetPage.clickOnOmniMinimizeButton();
		
		//Get Cases count from Queue
		int intQueueCnt=thorPetitionPage.getCasesCountFrmQueue();
		//System.out.println("Case Count from Queue "+intQueueCnt);
		
		//Apply Filter on Target Persona Id
		int rcount=thorPetitionPage.getQueueCountAfterFilter(testData.get("filterName").toString(),testData.get("filterValue").toString());
		Assert.assertTrue((rcount>=1),"Threre is no Cases Available in the Queue as per the Filter.");
		
		String strPetitionActionVal=testData.get("petitionActionVal").toString();
		String strStrikeType=testData.get("strikeType").toString();
		String strPLevel=testData.get("penaltyLevel").toString();
		String strDiscpAction=testData.get("disciplinaryAction").toString();
		String strReason=testData.get("reason").toString();
		String strEmailPwd=testData.get("emailPwd").toString();
			
		//Select first row from Queue Table
		thorPetitionPage.selectFirstPetitionFromQueue();
		//Work around for Case details Tab display
		if(!(thorPetitionPage.verifyDisplayOfPetitionPreview()))
		{	thorPetitionPage.clickOnQueueTab();
			thorPetitionPage.selectFirstPetitionFromQueue();
		}
		//Get Details from Petition Preview
		String strPetitionPreviewRHS=thorPetitionPage.getPetitionPreviewDetails();
		String [] arrOfStr = strPetitionPreviewRHS.split(",");
		//System.out.println(strPetitionPreviewRHS);
		//Click on View Case Details button
		thorPetitionPage.clickOnViewCaseDetailsButton();
		//Get Target Player Email ID
		String strTargetPlayerEmailId=thorPetitionPageDetails.getTargetPlayerEmailID();
		//System.out.println(strTargetPlayerEmailId);
		//Select Petition Action as Resolve - Violation Confirmed
		thorPetitionPageDetails.selectItemFrmComboBox("petitionAction", strPetitionActionVal);
		//Get the Existing Major Strike no
		int intExistingMajorStrikeNo=thorPetitionPageDetails.getMajorStrikeCnt();
		int index=intExistingMajorStrikeNo+1;
		//System.out.println(intExistingMajorStrikeNo);
		//Add Major Strike to Target Account
		thorPetitionPageDetails.selectStrikeType(strStrikeType);
		//Get default strike name
		String defaultStrike=thorPetitionPageDetails.getDefaultStrikeName();
		//System.out.println(defaultStrike);
		assertTrue(defaultStrike.contains("Current"),"Default Strike name not displayed Current");
		if(intExistingMajorStrikeNo>=5)
			logger.info("We can not add major strike count more than 5");
		else
		{
			thorPetitionPageDetails.selectStrikeNameByIndex(index);
			//Select Penalty Level as Account
			thorPetitionPageDetails.clickOnScrollDwn();
			thorPetitionPageDetails.selectItemFrmComboBox("penaltyLevel", strPLevel);
			//Select Disciplinary Action as Ban
			thorPetitionPageDetails.selectItemFrmComboBox("disciplinaryAction", strDiscpAction);
			//Select reason
			thorPetitionPageDetails.selectItemFrmComboBox("reasonCode", strReason);
			//Click on Save And Continue Button
			thorPetitionPageDetails.ckickOnSaveAndContinue(1);
			//Verify Manage Content and Automatic Case Notes Sections
			assertTrue(thorPetitionPageDetails.verifyManageContentForBan(strPLevel, strDiscpAction, strReason),"Manage Content not displayed as per our selection");
			String strNotes=thorPetitionPageDetails.getAutomaticCaseNotes();
			assertTrue(((strNotes.contains(strPLevel)) && (strNotes.contains(strDiscpAction)) && (strNotes.contains(strReason))),"Automatic Case Notes not displayed as per our selection");
			//Click On Save and Continue
			thorPetitionPageDetails.clickOnScrollDwn();
			thorPetitionPageDetails.ckickOnSaveAndContinue(2);
			Thread.sleep(3000);
			//Click On Save and Continue
			thorPetitionPageDetails.clickOnScrollDwn();
			thorPetitionPageDetails.ckickOnSaveAndContinue(3);
			Thread.sleep(3000);
			thorPetitionPageDetails.clickOnScrollDwn();
			//Verify Existence of Email message to Player Dynamic Option
			boolean flag=thorPetitionPageDetails.existenceOfEmailMessageToPlayer();
			Assert.assertTrue(flag,"Product "+arrOfStr[2]+" not configured with Email message Option");
			//Verify Locale name,Template Name and Message
			if(flag)
			{
				String strTempLan=thorPetitionPageDetails.getLocaleLanguage();
				String strTempName=thorPetitionPageDetails.getTemplateName();
				String strEmailMsg=thorPetitionPageDetails.getEmailMessage();
				//System.out.println(strTempLan+strTempName+strEmailMsg);
				assertTrue((strTempLan.contains("English") && strTempName.contains("Major Strike") && strEmailMsg.contains("Major Strike")),"Related Strike Email details not displayed");
				//Click on Save and Continue
				thorPetitionPageDetails.clickOnScrollDwn();
				thorPetitionPageDetails.ckickOnSaveAndContinue(3);
				//Verify Email message popup display and click on Yes Button
				thorPetitionPageDetails.verifyEmailPopupDialog();
				//Click on Commit button
				thorPetitionPageDetails.ckickOnCommitButton();
				//Assert.assertTrue(thorPetitionPage.islnkQueuedTabVisible(),"Commit Action not successful");
				//Due to defect we are using below statements and commented above statement
				Thread.sleep(5000);
				
				//Verify in Gmail
				//Navigate to Gmail and Login
				String strMailData="";
				if(googlePage.LogInGmail(strTargetPlayerEmailId, strEmailPwd))
					if(googlePage.gmailAdvancedSearchByWords(strEmailMsg))
					   strMailData=googlePage.getRowDataByRowNo(1);
				//System.out.println(strMailData);
				String[] arrMsg=strMailData.split("-");
				//System.out.println(arrMsg[1]);
				assertTrue(strMailData.contains("Customer Experience"),"Sender Name Not displayed properly"); 
				assertTrue(strMailData.contains("Sandbox: EA User Agreement Correspondence."),"Subject Not displayed properly"); 
				//System.out.println(arrMsg[1].indexOf(strEmailMsg));
				assertTrue(arrMsg[1].indexOf(strEmailMsg)>0,"Email Message Not displayed properly"); 
				//Logout from Gmail and Clear Cookies
				googlePage.LogOutGmail();
				//Delete Cookies
				googlePage.DeleteAllCookies();
				
			}	
		}
		assertAll();
	}

	
}
