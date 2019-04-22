package com.ea.wwce.automation.thor.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
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

public class ConfigurePenaltyLevelandDisciplinaryActionTest extends ThorBaseTest {

	public static Logger logger = Logger.getLogger(ConfigurePenaltyLevelandDisciplinaryActionTest.class);
	@Test(description = "Thor 742, Mapping feature in UD admin to determine Penalty Level & Disciplinary Action options to be displayed in UI ",groups={"regression"})
	@Description("Verify Penalty Level & Disciplinary Action")
	public void verifyPenaltyLevelandDisciplinaryAction(ITestContext context) throws Exception {
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;
		//Provide the ID of the test case. This is the ID generated in the TestRail when the manual test case is created.
		testID = "20531,20532,20529,20530";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Penalty Level & Disciplinary Test" + testID);
		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		// validate login
		thorLoginPage.loginToTHOR(testData.get("udadmin_username").toString(), testData.get("udadmin_password").toString());
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
		
		//Navigate to SF classic
		thorPetitionPage.clickOnUserProfile();
		thorPetitionPage.switchToSFClassic();
		classicDataManagement.clickOnDataManagementTab();
		classicDataManagement.clickOnPenaltyDisciplinaryConfiguration();
		classicHomePage.clickOnPenaltyDisciplinaryConfiguration();
		classicHomePage.NewTosPenaltyConfiguration(testData.get("productNameVal").toString(),testData.get("penaltylevel").toString(),testData.get("disciplinaryAction").toString());
		int totalrowcount=classicHomePage.getPenaltyConfigurationRowCount();	
		//System.out.println(totalrowcount);
		String strProductNameFrmCRM=classicHomePage.getProductNameFrmCRMPenaltyConfigurationTbl(totalrowcount);
		String strPenaltyLevelFrmCRM=classicHomePage.getPenaltyLevelFrmCRMPenaltyConfigurationTbl(totalrowcount);
		String strDisciplinaryActionFrmCRM=classicHomePage.getDisciplinaryActionFrmCRMPenaltyConfigurationTbl(totalrowcount);	
		String level=testData.get("penaltylevel").toString();
		String [] arrOfStr = level.split(";");
		String action=testData.get("disciplinaryAction").toString();
		String [] arrOfStr1= action.split(";");
		assertTrue((strProductNameFrmCRM.equals(testData.get("productNameVal"))), "Product name in Thor application"+strProductNameFrmCRM+" not matched with SF Product Name");
		assertTrue((strDisciplinaryActionFrmCRM.contains(arrOfStr1[0])), "disciplinaryAction name in Thor application"+strDisciplinaryActionFrmCRM+" not matched with SF DisciplinaryAction name1");
		assertTrue((strPenaltyLevelFrmCRM.contains(arrOfStr[0])), "penaltylevel name in Thor application"+strPenaltyLevelFrmCRM+" not matched with SF Penallty name1");
		classicHomePage.deletePenaltyConfiguration(totalrowcount);
		//Switch to SF Lightning Link
		classicCRMEventsPage.clickOnSFLightningLnk();
		assertAll();
	}
}
