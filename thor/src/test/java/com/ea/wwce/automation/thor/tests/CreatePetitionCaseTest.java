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

public class CreatePetitionCaseTest extends ThorBaseTest {

	public static Logger logger = Logger.getLogger(CreatePetitionCaseTest.class);
	@Test(description = "Thor 79,98 - Create Petition Case",groups={"regression"})
	@Description("Create Petition Case")
	public void verifyCreationOfPetitionCase(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		//Provide the ID of the test case. This is the ID generated in the TestRail when the manual test case is created.
		testID = "3638,3135,3136,3138,3139,18608,3141,3639";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Create Petition Case Test" + testID);

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
		{	thorPetitionPage.clickOnQueueTab();
			thorPetitionPage.selectFirstPetitionFromQueue();
		}
		//Click on view Case details button
		thorPetitionPage.clickOnViewCaseDetailsButton();
		//Click on Player Account button
		thorPetitionPageDetails.clickOnPlayerAccountLnk();
		
		//Get Target Player Details from Account Details page
		String targetPlyerDet=playerAccountPage.verifyTargetPlayerDetails();
		//System.out.println(targetPlyerDet);
		String emailID="";
		if (!(targetPlyerDet.isEmpty()))
			{
			String [] arrOfStr = targetPlyerDet.split(",");
			emailID=arrOfStr[8];
		}
		//System.out.println(emailID);
		//Search Player by Email ID
		thorPetitionPage.searchPlayer("Email", emailID);
		//After Search Verify Search is success or not
		String strPlayerEmailID="";
		strPlayerEmailID=playerSearchPage.getPlayerEmailID();
		assertTrue(strPlayerEmailID.equals(emailID),"Player Search By EmailID is not successful");
		//Petition Case Creation by Advisor
		String strText="";
		String strCaseType=testData.get("caseType").toString();
		String strProduct=testData.get("product").toString();
		String strPlatform=testData.get("platform").toString();
		String strCategory=testData.get("category").toString();
		String strContentType=testData.get("contentType").toString();
		String strSubject=testData.get("subject").toString();
		String strCaseNotes=testData.get("caseNotes").toString();
		//Enter Petition Case Details and create case
		strText=playerSearchPage.createCase(strCaseType,strProduct,strPlatform,strCategory,strContentType,strSubject,strCaseNotes);
		//System.out.println(strText);
		//Verify Petition case details entering
		assertTrue((!(strText.isEmpty())),"Case Details not entered properly");
		//Verify Petition Case Created or not
		String strProductFrmCaseDetail=caseDetailPage.getProductName();
		assertTrue(strProductFrmCaseDetail.equals(strProduct),"Case not created with Product "+strProduct );
		String strPlatformFrmCaseDetail=caseDetailPage.getPlatformName();
		assertTrue(strPlatformFrmCaseDetail.equals(strPlatform),"Case not created with Platform "+strPlatform );
		String strCategoryFrmCaseDetail=caseDetailPage.getCategoryName();
		assertTrue(strCategoryFrmCaseDetail.equals(strCategory),"Case not created with Category "+strCategory );
		String strContentTypeFrmCaseDetail=caseDetailPage.getContentType();
		assertTrue(strContentTypeFrmCaseDetail.equals(strContentType),"Case not created with Content Type "+strContentType );
		String strCaseID=caseDetailPage.getCaseID();
		//System.out.println(strCaseID);
		//Close Search Tab
		thorPetitionPageDetails.closeSearchCaseTab(strCaseID);
		thorPetitionPageDetails.closeSearchAccountTab();
		
		/*      Verify Case Events in SF                      */
		//Navigate to SF classic				
		thorPetitionPage.clickOnUserProfile();
		thorPetitionPage.switchToSFClassic();
		//Search for case in SF and Click on that case
		classicHomePage.searchSFObject(strCaseID);
		classicSearchPage.clickOnSearchdSFObject(strCaseID);
		//Verify case details from Cases object
		String strCaseNumFrmSF=classicCasesPage.getCaseDetailAnyFieldVale("Case Number","innerText");
		String strProductFrmSF=classicCasesPage.getCaseDetailAnyFieldVale("Product","innerText");
		String strPlatformFrmSF=classicCasesPage.getCaseDetailAnyFieldVale("Platform","innerText");
		String strCategoryFrmSF=classicCasesPage.getCaseDetailAnyFieldVale("Category","innerText");
		String strSubjectFrmSF=classicCasesPage.getCaseDetailAnyFieldVale("Subject","innerText");
		//System.out.println(strProductFrmSF+strCategoryFrmSF+strPlatformFrmSF+strSubjectFrmSF);
		assertTrue((strCaseNumFrmSF.equals(strCaseID)), "Case created in Thor application "+strCaseID+" not matched with SF Cases object case no");
		assertTrue((strProduct.equals(strProductFrmSF)), "Product used in case creation "+strProduct+" not matched with SF Cases object product");
		assertTrue((strPlatform.equals(strPlatformFrmSF)), "Platform used in case creation "+strPlatform+" not matched with SF Cases object Platform");
		assertTrue((strCategory.equals(strCategoryFrmSF)), "Category used in case creation "+strCategory+" not matched with SF Cases object Category");
		assertTrue((strSubject.equals(strSubjectFrmSF)), "Subject used in case creation "+strSubject+" not matched with SF Cases object Subject"); 
		//Switch to SF Lightning Link
		classicCRMEventsPage.clickOnSFLightningLnk();
		assertAll();

	}
}


