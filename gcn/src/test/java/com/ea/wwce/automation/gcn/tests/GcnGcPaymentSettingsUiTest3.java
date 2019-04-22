package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnGcPaymentSettingsUiTest3 extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(GcnGcPaymentSettingsUiTest3.class);
	
	/*This Test Class is to verify the Game Changer details in the mydetails tab/ profile page/ payments settings page.
	 * 
	 * TC: 18541 - Verify the details of Gamer changer under My Details tab
	 * 
	 * */

	
	@Test(priority=1)
	public void gcnGcPaymentSettingsUiTest3(ITestContext context) throws InterruptedException {
		Map<String,Object> testData=new HashMap<String,Object>();
		String testID="18541";
		
		// 
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		DataProviders dataProvider=(DataProviders)context.getAttribute("dataProvider");
		testData=dataProvider.getTestData(testID);
		
		gcnGcHomePage.launchGcAndSignIn();
		
		gcnGcPage=gcnGcLoginPage.verifyLogin(testData.get("usernihalgc2").toString(), testData.get("passgcnihal2").toString());
		
		gcnGcPage.clickOnProfileLink();
		assertTrue(gcnGcProfilePage.verifyNavigationToProfilePage()	, "Error landing into Profile Page");	
			
		gcnGcProfilePage.clickOnPaymentSettingsLink();
		assertTrue(gcnGcPaymentSettingsPage.verifyNavigationToPaymentSettingsPage(), "Error landing into Payment Settings Page");
				
				
		assertEquals(testData.get("testmail1").toString(), gcnGcPaymentSettingsPage.getPaypalEmail(), "Email Updation Mismatch");
		assertTrue(testData.get("testmail1").toString().equalsIgnoreCase(gcnGcPaymentSettingsPage.getPaypalEmail()), "Info Saved Successfully");
		
		assertAll();	
		
	}
	


}
