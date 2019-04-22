/**
 * 
 * @author Mohan Kamsu
 * @Description Verification of Advisor Profile Job roles displayed in THOR Application or not
 */

package com.ea.wwce.automation.thor.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import io.qameta.allure.Description;

public class VerifyAdvisorProfileJobRolesTest extends ThorBaseTest {

	public static Logger logger = Logger.getLogger(VerifyAdvisorProfileJobRolesTest.class);
	@Test(description = "Thor 658 - Job Roles and Permissons",groups={"regression"})
	@Description("Verify Job Roles of Advisor")
	public void verifyAdvisorJobRoles(ITestContext context)  {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		//Provide the ID of the test case. This is the ID generated in the TestRail when the manual test case is created.
		testID = "18527,18528";
		context.setAttribute("testcase_id", testID);
		logger.info("Verifying Advisor Profile Job Roles" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		// validate Thor login
		thorLoginPage.loginToTHOR(testData.get("username").toString(), testData.get("password").toString());
		//Verify existance of Select Role modal
		boolean strStatus1=chooseUrRolePage.verifyExistanceOfChooseUrRoleDialog();
		List<String> jobRolesFromThorArr = new ArrayList<String>();
		if (strStatus1)
		{	jobRolesFromThorArr=chooseUrRolePage.getAlltheJobRoles();
			fillUrQueuePage.selectFirstQueueCheckBox();
		}
		//Logout from THOr as Game advisor
		thorPetitionPage.clickOnUserProfile();
		thorPetitionPage.logout();
		boolean logOutStatus=thorLoginPage.usernameDisplay();
		// Login to thor Again as UD admin
		if(logOutStatus)
			thorLoginPage.loginToTHOR(testData.get("udadmin_username").toString(), testData.get("udadmin_password").toString());
		else
			Assert.assertFalse(logOutStatus," Logout not done properly for Ud game advisor");
		//Select Role from Select your Role modal
		boolean strStatus3=chooseUrRolePage.verifyExistanceOfChooseUrRoleDialog();
		if (strStatus3) 
			chooseUrRolePage.selectRoleRadioButton(testData.get("roleName").toString());
		else
			assertFalse(strStatus3," Select Your Role modal not displayed");
		//Select queue from Fill your Queue modal
		boolean strStatus4=fillUrQueuePage.verifyExistanceOfFillUrQueueDialog();
		if (strStatus4)
			fillUrQueuePage.selectQueueCheckBox(testData.get("queueName").toString());
		else
			assertFalse(strStatus4," Select Your Queue modal not displayed");
		//Navigate to SF admin page
		sfAdminPage.loadSFAdminPage(testData.get("url").toString());
		//Click on Main Advisor Profile Link
		sfAdminPage.clickOnMainAdvisorProfileLnk();
		//Click on Sub Advisor Profile Link
		sfAdminPage.clickOnSubAdvisorProfileLnk();
		//Switch to Dash board frame
		sfAdminPage.switchToDashboard();
		//Search Advisor Profile
		String strAdvisor=sfAdvProfileMangePage.searchAdvisorProfile(testData.get("advisorName").toString());
		//Edit Advisor Profile
		//sfAdvProfileMangePage.clickOnEditProfile();
		//Get the Already assigned job roles from Advisor
		List<String> jobRolesArr = new ArrayList<String>();
		jobRolesArr=sfAdvProfileMangePage.getSelectedJobRolesFrmList();
		Collections.reverse(jobRolesArr);		
		//Verify Job roles of Advisor Profile from UD_Admin and Thor application Job roles
		//System.out.println(jobRolesFromThorArr.equals(jobRolesArr));
		assertTrue(jobRolesFromThorArr.equals(jobRolesArr),"Job roles not matched from Advisor Profile and Thor Application");
		assertAll();

	}
}
