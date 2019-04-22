/**
 * 
 * @author Praveen
 * @Description Verification of Create Queue by Thor Admin
 */

package com.ea.wwce.automation.thor.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import io.qameta.allure.Description;

public class CreateQueueByThorAdminTest extends ThorBaseTest {

	public static Logger logger = Logger.getLogger(CreateQueueByThorAdminTest.class);
	@Test(description = "Thor 702 - Verify that Thor Admin is able to create Queues",groups={"regression"})
	@Description("Verify Create Queue by Thor Admin")
	public void verifyCreateQueueByAdmin(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		//Provide the ID of the test case. This is the ID generated in the TestRail when the manual test case is created.
		testID = "19450,19451";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Create Queue By Thor Admin Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		// validate login
		thorLoginPage.loginToTHOR(testData.get("udadmin_username").toString(), testData.get("udadmin_password").toString());
		//Navigate to SF admin page
		sfAdminPage.loadSFAdminPage(testData.get("url").toString());
		//Click on Job Role Link
		sfAdminPage.clickOnJobRoleLnk();
		//Click on Queues link
		sfAdminPage.clickOnQueuesLnk();
		//swith to Dashboard page frame
		sfQueueMgtPage.switchToDashboard();
		//click on Add Action symbol link
		sfQueueMgtPage.clickOnAddActionSymbolLnk();
		sfQueueMgtPage.isSFAddQueuePageDisplayed();		
		String addedTxt=sfQueueMgtPage.saveAddQueue();
		//System.out.println(addedTxt);
		//Verify the email Queue
		assertTrue(sfQueueMgtPage.getSearchedText(addedTxt),"Email Queue not verified successfully");
		//Verify Available Role saved successfully
		assertTrue(sfQueueMgtPage.addAvailableRole(testData.get("roleName").toString()),"Available Role not saved successfully");
		
		thorLoginPage.LoadTHORURL();
		if(classicCRMEventsPage.islnkSwitchToLightningExperienceVisible())
			classicCRMEventsPage.clickOnSFLightningLnk();
		else {
		
		  // validate login
			  thorLoginPage.loginToTHOR(testData.get("T2username").toString(), testData.get("T2password").toString());
			 }  			 			
		//Click on Select Role button
		thorPetitionPage.clickOnSelectRoleButton();
		//Select Role from Select your Role modal
		boolean strStatus1=chooseUrRolePage.verifyExistanceOfChooseUrRoleDialog();
		//String rolname=testData.get("roleName").toString();
		if (strStatus1) 
			chooseUrRolePage.selectRoleRadioButton(testData.get("roleName").toString());
		else
			assertFalse(strStatus1," Select Your Role modal not displayed");
		//Verify Queue Name is added in Select your Queue box.
		assertTrue(fillUrQueuePage.isPresentQueueName(addedTxt),"Added Queue Name not displayed in Select Queue");
		
		assertAll();

	}
}
