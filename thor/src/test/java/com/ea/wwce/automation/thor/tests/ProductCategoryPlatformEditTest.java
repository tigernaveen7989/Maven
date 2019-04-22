package com.ea.wwce.automation.thor.tests;

import java.util.ArrayList;
import java.util.Arrays;
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

public class ProductCategoryPlatformEditTest extends ThorBaseTest {

	public static Logger logger = Logger.getLogger(ProductCategoryPlatformEditTest.class);
	@Test(description = "Thor 466 - The Product, Platform and Category of a petition need to be editable ",groups={"Regression"})
	@Description("Verify Product, Platform and Category are editable or not")
	public void ProductCategoryPlatformEdit(ITestContext context) throws InterruptedException {
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;
	
		//Provide the ID of the test case. This is the ID generated in the TestRail when the manual test case is created.
		testID = "4438,3602,10111,10112,4815,4817,3164,3166,3159";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Product and Category are editable or not in Petition Preview" + testID);
	
		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
	
		// validate login
		thorLoginPage.loginToTHOR(testData.get("username").toString(), testData.get("password").toString());
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
		
		//Verify different Advisor Status
		String[] StringArray = new String[]{"Available","Break","Busy","Feedback","Game Time","GPS Work","Lunch","Meeting","Mentoring","Non Queue Task","Not Ready/Idle","Project","System Issues","Team Meeting","Training","Offline"};
		//Verify Queued tab visibility
		assertTrue(thorPetitionPage.islnkQueuedTabVisible(), "Verify Queued Tab Link visibility after pull the Queue");
		//Change Advisor status to Available in Omni
		thorPetitionPage.clickOnOmniChannelButton();
		String[] advisorStatus=omniChannelWidgetPage.getAdvisorStatusInArray();
		//System.out.println(Arrays.equals(advisorStatus, StringArray));
		assertTrue(Arrays.equals(advisorStatus, StringArray), "Mismatch the advisory statuses");
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
		
		//Select First Petition from Queue
		thorPetitionPage.selectFirstPetitionFromQueue();
		//Work around for Case details Tab display
		if(!(thorPetitionPage.verifyDisplayOfPetitionPreview()))
		{	thorPetitionPage.clickOnQueueTab();
			thorPetitionPage.selectFirstPetitionFromQueue();
		}
		//Verify UGC content of Petition
		//If it is Iamgae click on Image and verify
		String strTemp=thorPetitionPage.verifyDisplayOfUGC();
		if (strTemp.equals("Image"))
		{
			assertTrue(strTemp.equals("Image"), "UGC content is not Image");
			//Click on Image
			thorPetitionPage.clickOnContentImage();
			assertTrue(thorReportedPage.isReportImageWindowDisplayed(),"UGC Image not openened in modal window");
			thorReportedPage.closeModalWindow();
		}
		if (strTemp.equals("URL"))
			assertTrue(strTemp.equals("URL"), "UGC content is not URL");
		if (strTemp.equals("No Image OR No URL"))
			assertTrue(strTemp.equals("No Image OR No URL"), "UGC content is other than URL or Image");
		
		//Verify Product is editable or not in Petition Preview Section
		assertTrue(thorPetitionPage.selectItemFrmProduct(testData.get("productItem").toString()), "Product field is not editable");
		//Verify Category is editable or not in Petition Preview Section
		assertTrue(thorPetitionPage.selectItemFrmCategory(testData.get("categoryItem").toString()), "Category field is not editable");
		
		//Click on View Case Details button
		thorPetitionPage.clickOnViewCaseDetailsButton();
		//Verify Product is editable or not in Petition detail page
		boolean flag=thorPetitionPageDetails.selectItemFrmProduct(testData.get("productItem").toString());
		//System.out.println(flag);
		assertTrue(flag, "Product field is not editable in Petition detail page");
		//Verify Platform is editable or not in Petition detail page
		assertTrue(thorPetitionPageDetails.selectItemFrmPlatform(testData.get("platformItem").toString()), "Platform field is not editable in Petition detail page");
		//Verify Category is editable or not in Petition detail page
		assertTrue(thorPetitionPageDetails.selectItemFrmCategory(testData.get("categoryItem").toString()), "Category field is not editable in Petition detail page");
				
		assertAll();
	}

}
