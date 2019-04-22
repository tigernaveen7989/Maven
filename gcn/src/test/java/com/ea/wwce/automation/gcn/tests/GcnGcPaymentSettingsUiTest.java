package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnGcPaymentSettingsUiTest extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(GcnGcPaymentSettingsUiTest.class);
	
	/*This class is to check Payments Settings scenarios for Game Changer.
	 * 
	 * TC: 18532 - Verify Navigation menu is displayed in the Game Changer profile screen
	 * TC: 18533 - Verify the links in Navigation menu of Game changer profile screen
	 * TC: 18535 - Verify the Payment settings button in all Joined and Invited Opportunites screen
	 * TC: 18536 - Verify the functionality of Payment settings button
	 * TC: 18538 - Verify the My Profile text and Game changer name
	 * TC: 18542 - Verify Game changer is able to navigate to payment details screen
	 * 
	 * */

	
	@Test(priority=1)
	public void gcnGcPaymentSettingsUiTest(ITestContext context) throws InterruptedException {
		Map<String,Object> testData=new HashMap<String,Object>();
		String testID="18532,18533,18535,18536,18538,18542";
		
		// 18532,18533,18534,18535,18536,18538,18542
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		dataProvider=(DataProviders)context.getAttribute("dataProvider");
		testData=dataProvider.getTestData(testID);
		
		gcnGcHomePage.launchGcAndSignIn();
		
		gcnGcPage=gcnGcLoginPage.verifyLogin(testData.get("userSanjeetgc3").toString(), testData.get("passSanjeetgc3").toString());
		
		gcnGcPage.clickOnProfileLink();
		
		assertTrue(gcnGcPage.verifyAvailabilityOfMenuLinks(),"GameChanger Menu Links not Displayed");
		
		gcnGcProfilePage.clickOnPaymentSettingsLink();
				
		assertTrue(gcnGcPaymentSettingsPage.verifyNavigationToPaymentSettingsPage(), "Error Navigating to Payment Settings Page");
		
		Thread.sleep(4000);
		// verify the details of user in Payment Settings Page
		// check for User Full Name
		assertEquals(gcnGcProfilePage.getFullName(), testData.get("fullname_sanjeet").toString(), "User Name Mismatch");
				
		// check for User Paypal email
		assertEquals(gcnGcPaymentSettingsPage.getPaypalEmail(), testData.get("paypal_sanjeet").toString(), "Paypal email id Mismatch");
		
		// check for User Country
		//assertEquals(gcnGcPaymentSettingsPage.getCountry(), testData.get("country_sanjeet").toString(), "Country id Mismatch");
		
		// check for User Entity Type
		//assertEquals(gcnGcPaymentSettingsPage.getEntityType(), testData.get("entityType_sanjeet").toString(), "Country id Mismatch");
		
		assertAll();	
		
	}
	

}
