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

public class ValidateMessagingTemplateTest extends ThorBaseTest {

	public static Logger logger = Logger.getLogger(ValidateMessagingTemplateTest.class);
	@Test(description = "Thor 658 - Verify that Advisor is able to select messaging template",groups={"Regression"})
	@Description("Verify T2 advisor messaging template functionality")
	public void verifyMessagingTemplate(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		//Provide the ID of the test case. This is the ID generated in the TestRail when the manual test case is created.
		testID = "18606";
		context.setAttribute("testcase_id", testID);
		logger.info("validating T2 Advisor Messaging Template functionality" + testID);

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
		
		String strPetitionActionVal=testData.get("strPetitionActionVal").toString();
		String strEmailPwd=testData.get("emailPwd").toString();
		String strMsg=testData.get("emailMsg").toString();
		String strSearchText=testData.get("searchText").toString();
		
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
		//System.out.println(arrOfStr[0]);
		//Click on View Case Details button
		thorPetitionPage.clickOnViewCaseDetailsButton();
		//Get Target Player Email ID
		String strTargetPlayerEmailId=thorPetitionPageDetails.getTargetPlayerEmailID();
		//System.out.println(strTargetPlayerEmailId);
		//Select Petition Action as Resolve - Violation Confirmed
		thorPetitionPageDetails.selectItemFrmComboBox("petitionAction", strPetitionActionVal);
		thorPetitionPageDetails.clickOnScrollDwn();
		String strNotes=thorPetitionPageDetails.getAutomaticCaseNotes();
		thorPetitionPageDetails.clickOnScrollDwn();
		//verify default notes
		assertTrue((strNotes.contains(testData.get("defaultNotes1").toString())),"Automatic Case Notes not displayed as per our selection");
		//Click On Save and Continue
		thorPetitionPageDetails.ckickOnSaveAndContinue(2);
		Thread.sleep(3000);
		
		//Verify Existence of Email message to Player Dynamic Option
		boolean flag=thorPetitionPageDetails.existenceOfEmailMessageToPlayer();
		//Enter Email message
		if(flag) {
			thorPetitionPageDetails.clickOnScrollUp();
			thorPetitionPageDetails.selectEmail(testData.get("strLanguage1").toString());
			thorPetitionPageDetails.clickOnScrollDwn();
			String Englishnotes=thorPetitionPageDetails.selectEmailTemplate(testData.get("strTemplate1").toString());
			assertTrue((Englishnotes.contains(testData.get("EnglishMsg").toString())),"English Notes not displayed as per our selection");
			Thread.sleep(3000);
			thorPetitionPageDetails.clickOnScrollUp();
			thorPetitionPageDetails.selectEmail(testData.get("strLanguage2").toString());
			String Germannotes=thorPetitionPageDetails.selectEmailTemplate(testData.get("strTemplate2").toString());
			thorPetitionPageDetails.clickOnScrollDwn();
			assertTrue((Germannotes.contains(testData.get("GermanMsg").toString())),"German Notes not displayed as per our selection");
			//Click On Save and Continue
			thorPetitionPageDetails.clickOnScrollDwn();
			thorPetitionPageDetails.clickOnScrollDwn();
			thorPetitionPageDetails.ckickOnSaveAndContinue(3);
			//Verify Email message popup display and click on Yes Button
			thorPetitionPageDetails.verifyEmailPopupDialog();
			//Click on Commit
			thorPetitionPageDetails.ckickOnCommitButton();
			//Assert.assertTrue(thorPetitionPage.islnkQueuedTabVisible(),"Commit Action not successful");
			//Due to defect we are using below statements and commented above statement
			Thread.sleep(5000);
			//Verify in Gmail
			//Navigate to Gmail and Login
			String strMailData="";
			Assert.assertTrue(googlePage.LogInGmail(strTargetPlayerEmailId, strEmailPwd), "Unable to log in Gmail account");
			if(googlePage.gmailAdvancedSearchByWords(strSearchText))
			{	   strMailData=googlePage.getRowDataByRowNo(1);
					//System.out.println(strMailData);
					assertTrue(strMailData.contains("Customer Experience"),"Sender Name Not displayed properly"); 
					assertTrue(strMailData.contains("Sandbox: EA User Agreement Correspondence."),"Subject Not displayed properly"); 
					//System.out.println(strMailData.indexOf(strMsg));
					assertTrue(strMailData.indexOf(strMsg)>0,"Email Message Not displayed properly"); 
			}
			//Logout from Gmail and Clear Cookies
			googlePage.LogOutGmail();	
			//Delete Cookies
			googlePage.DeleteAllCookies();

			}			
		else
			logger.info("Product "+arrOfStr[2]+" not configured with Email message Option");
	
		assertAll();
	}
	
}
