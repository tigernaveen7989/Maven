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

public class SearchPlayerAccountTest extends ThorBaseTest {

	public static Logger logger = Logger.getLogger(SearchPlayerAccountTest.class);
	@Test(description = "Thor 280 - Search Player Account using different fields",groups={"regression"})
	@Description("Verify Search Player")
	public void verifySearchPlayer(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		//Provide the ID of the test case. This is the ID generated in the TestRail when the manual test case is created.
		testID = "3121,3122,3124,3131,3133,3134,3174,21021";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Search Player Test" + testID);

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
		//If petition preview not displayed click on Queue Tab
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
		String nucleusPersona="";
		String nucleusPersonaId="";
		String nucleusAcctID="";
		String emailID="";
		if (!(targetPlyerDet.isEmpty()))
			{
			String [] arrOfStr = targetPlyerDet.split(",");
			nucleusPersona=arrOfStr[0];
			nucleusPersonaId=arrOfStr[1];
			nucleusAcctID=arrOfStr[4];
			emailID=arrOfStr[8];
		}
		//System.out.println(nucleusPersona+nucleusPersonaId+nucleusAcctID+emailID);
		//Search Player by Email ID
		thorPetitionPage.searchPlayer("Email", emailID);
		//After Search Verify Search is success or not
		String strPlayerEmailID="";
		strPlayerEmailID=playerSearchPage.getPlayerEmailID();
		assertTrue(strPlayerEmailID.equals(emailID),"Player Search By EmailID is not successful");
		//Close Search page
		playerSearchPage.closePlayerSearch();
		
		//Search Player by Persona Name
		/*thorPetitionPage.searchPlayer("Persona Name", nucleusPersona);
		//After Search Verify Search is success or not
		String strPersonaName="";
		strPersonaName=playerSearchPage.getPlayerPersonaName();
		assertTrue(strPersonaName.equals(nucleusPersona),"Player Search By Persona Name is not successful");
		//Close Search page
		playerSearchPage.closePlayerSearch();		*/
		
		//Search Player by Persona ID
		thorPetitionPage.searchPlayer("Persona ID", nucleusPersonaId);
		//After Search Verify Search is success or not
		String strPersonaID="";
		strPersonaID=playerSearchPage.getPlayerPersonaID();
		assertTrue(strPersonaID.equals(nucleusPersonaId),"Player Search By Persona ID is not successful");
		//Close Search page
		playerSearchPage.closePlayerSearch();			
		  
		
		//Search Player by Account ID
		thorPetitionPage.searchPlayer("Nucleus ID", nucleusAcctID);
		//After Search Verify Search is success or not
		String strAccountID="";
		String strPName="";
		String strPID="";
		String strEmailID="";
		String strCustomerLan="";
		String strCustomerAge="";
		String strAccountAge="";
		String strSecurityState="";
		String strAccountStatus="";
		
		strAccountID=playerSearchPage.getPlayerAccountID();
		assertTrue(strAccountID.equals(nucleusAcctID),"Player Search By Account ID is not successful");
		strPName=playerSearchPage.getPlayerPersonaName();
		assertTrue((!(strPName.isEmpty())),"Persona Name not displayed after Serach");
		strPID=playerSearchPage.getPlayerPersonaID();
		assertTrue((!(strPID.isEmpty())),"Persona ID not displayed after Serach");
		strEmailID=playerSearchPage.getPlayerEmailID();
		assertTrue((!(strEmailID.isEmpty())),"Email ID not displayed after Serach");
		strCustomerLan=playerSearchPage.getCustomerLanguage();
		assertTrue((!(strCustomerLan.isEmpty())),"Customer Language not displayed after Serach");
		strCustomerAge=playerSearchPage.getCustomerAge();
		assertTrue((!(strCustomerAge.isEmpty())),"Customer Age not displayed after Serach");
		strAccountAge=playerSearchPage.getAccountAge();
		assertTrue((!(strAccountAge.isEmpty())),"Account Age not displayed after Serach");
		strSecurityState=playerSearchPage.getPlayerSecurityState();
		assertTrue((!(strSecurityState.isEmpty())),"Security State not displayed after Serach");
		strAccountStatus=playerSearchPage.getPlayerAccountStatus();
		assertTrue((!(strAccountStatus.isEmpty())),"Account Status not displayed after Serach");
		playerSearchPage.closePlayerSearch();
		
		
		//Search Player by Invalid  EmailID, Persona Name, Persona ID, Account ID
		//assertTrue(thorPetitionPage.verifySearchErrorMessages("Email"),"Search Invalid Error Message not displayed for Email");
		assertTrue(thorPetitionPage.verifySearchErrorMessages("Persona Name"),"Search Invalid Error Message not displayed for Persona Name");
		assertTrue(thorPetitionPage.verifySearchErrorMessages("Persona ID"),"Search Invalid Error Message not displayed for Persona ID");
		assertTrue(thorPetitionPage.verifySearchErrorMessages("Nucleus ID"),"Search Invalid Error Message not displayed for Nucleus ID");
		assertAll();

	}

}
