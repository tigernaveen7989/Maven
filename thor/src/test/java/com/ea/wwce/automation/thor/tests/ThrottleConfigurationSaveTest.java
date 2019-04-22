/**
 * 
 * @author Mohan Kamsu
 * @Description Verification of Saving Throttle Configuration in Admin page
 */

package com.ea.wwce.automation.thor.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import io.qameta.allure.Description;

public class ThrottleConfigurationSaveTest extends ThorBaseTest {

	public static Logger logger = Logger.getLogger(ThrottleConfigurationSaveTest.class);
	@Test(description = "Thor 268 - Verify that Throttle Configuration fields display and save",groups={"regression"})
	@Description("Verify Throttle Configuration Save")
	public void verifyThrottleConfigurationSave(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		//Provide the ID of the test case. This is the ID generated in the TestRail when the manual test case is created.
		testID = "3717,3718";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Throttle Configuration Save Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		// validate login
		thorLoginPage.loginToTHOR(testData.get("udadmin_username").toString(), testData.get("udadmin_password").toString());
		//Navigate to SF admin page
		sfAdminPage.loadSFAdminPage(testData.get("url").toString());
		//Click on Thor Admin Console Link
		sfAdminPage.clickOnThorAdminLnk();
		//Click on Throttle Configuration link and verify fields
		sfAdminPage.clickOnThrottleConfig();
		//Switch to Dashboard frame
		sfAdminPage.switchToDashboard();
		//Verify Fields display in Throttle Configuration Page
		assertTrue(sfAdminPage.isProductDisplayed(),"Verify Product Field Displayed successfully");
		assertTrue(sfAdminPage.isPlatformDisplayed(),"Verify Platform Field Displayed successfully");
		assertTrue(sfAdminPage.isCategoryDisplayed(),"Verify Category Field Displayed successfully");
		assertTrue(sfAdminPage.isMinPetReqFieldDisplayed(),"Verify minimum Petition Request Limit Field Displayed successfully");
		assertTrue(sfAdminPage.isMaxPetReqFieldDisplayed(),"Verify maximum Petition Request Limit Field Displayed successfully");
		assertTrue(sfAdminPage.istimeFieldDisplayed(),"Verify time Field Displayed successfully");
		//Enter values and click on Save configuration
		assertTrue(sfAdminPage.saveThrottleConfig(testData.get("product").toString(),testData.get("platform").toString(),testData.get("category").toString()),"Verify Save Throttle Configuration successfully");
		assertAll();

	}

	
}
