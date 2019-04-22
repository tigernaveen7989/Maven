package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnGcPaymentSettingsUiTest2 extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(GcnGcPaymentSettingsUiTest2.class);
	
	/*This class is to check for Payment Settings scenario for Game Changer.
	 * 
	 * 
	 * 
	 * */

	
	@Test(priority=1)
	public void gcnGcPaymentSettingsUiTest2(ITestContext context) throws InterruptedException {
		Map<String,Object> testData=new HashMap<String,Object>();
		String testID="18545,18546,18547,20713,20715,42444";
		
		// 18532,18535,18545,18544
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider=(DataProviders)context.getAttribute("dataProvider");
		testData=dataProvider.getTestData(testID);
		
		gcnGcHomePage.launchGcAndSignIn();
		
		gcnGcPage=gcnGcLoginPage.verifyLogin(testData.get("userSanjeetgc3").toString(), testData.get("passSanjeetgc3").toString());
		
		assertTrue(gcnGcPage.verifyNavigationToGcPage(), "Error landing into GameChanger landing page");
		
		gcnGcPage.clickOnProfileLink();
		gcnGcProfilePage.clickOnPaymentSettingsLink();
		
		assertTrue(gcnGcPaymentSettingsPage.verifyNavigationToPaymentSettingsPage(), "Error landing into Payment Settings Page");
						
		assertTrue(gcnGcPaymentSettingsPage.isPaypalLogoDisplayed(), "Paypal Logo is Not Displayed");
		
		//assertTrue(gcnGcPaymentSettingsPage.verifyPaypalEmailUpdation(testData.get("testmail1").toString()), "Error updating Paypal email id");
		
		assertTrue(gcnGcPaymentSettingsPage.verifyIProcUpdation(), "iProc is Not Selected");
		
		assertTrue(gcnGcPaymentSettingsPage.verifyPaypalRadioAndEmailUpdation(testData.get("testmail1").toString()), "Paypal is Not Selected");
		
		assertTrue(gcnGcPaymentSettingsPage.verifyAddressElementsDisplayed(), "All Address Fields are Not Displayed");
				
		gcnGcPaymentSettingsPage.setPaypalEmail(testData.get("paypal_sanjeet").toString());
		
		
		
		
		assertAll();	
		
	}
	
}
