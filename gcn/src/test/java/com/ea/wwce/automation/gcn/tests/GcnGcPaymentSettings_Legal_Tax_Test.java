package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnGcPaymentSettings_Legal_Tax_Test extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(GcnGcPaymentSettings_Legal_Tax_Test.class);
	
	/*This class is to verify updation of country,legal entity and tax id information. 
	 * 
	 * TC: 18548 - Verify Game changer is able to change the Country
	 * TC: 18549 - Verify the list of countries in the drop down
	 * TC: 18550 - Verify Game changer is able to edit the Tax Id Type and Tax ID under Non-US Tax ID
	 * TC: 18551 - Verify Game changer is able to change the Legal Entity Type
	 * TC: 18552 - Verify the functionality of Save button 
	 * 
	 * */
	
	
	@Test(priority=1)
	public void gcnGcPaymentSettingsTest(ITestContext context) throws InterruptedException {
		Map<String,Object> testData=new HashMap<String,Object>();
		String testID="18542,18548,18551,18552,20717,42448,42449,42450,42451,42531,42532";
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider=(DataProviders)context.getAttribute("dataProvider");
		testData=dataProvider.getTestData(testID);
		
		gcnGcHomePage.launchGcAndSignIn();
		
		gcnGcPage=gcnGcLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
			
		gcnGcPage.clickOnProfileLink();
		assertTrue(gcnGcProfilePage.verifyNavigationToProfilePage()	, "Error landing into Profile Page");	
			
		gcnGcProfilePage.clickOnPaymentSettingsLink();
		assertTrue(gcnGcPaymentSettingsPage.verifyNavigationToPaymentSettingsPage(), "Error landing into Payment Settings Page");
		
		// Verification for tax details updated for US citizen as Individual.
		assertTrue(gcnGcPaymentSettingsPage.verifyUpdationOfTaxDetails(testData.get("country_us").toString(), 
				testData.get("entityType2").toString(), testData.get("bizName1").toString()), "Error Saving Tax Details");
		
		// Verification for tax details updated for US citizen as Business.
		assertTrue(gcnGcPaymentSettingsPage.verifyUpdationOfTaxDetails(testData.get("country_us").toString(), 
				testData.get("entityType1").toString(), testData.get("bizName1").toString()), "Error Saving Tax Details");
		
		// Verification for tax details updated for Non-US citizen as Individual.
		assertTrue(gcnGcPaymentSettingsPage.verifyUpdationOfTaxDetails(testData.get("country_nonus").toString(), 
				testData.get("entityType2").toString(), testData.get("bizName1").toString()), "Error Saving Tax Details");
		
		// Verification for tax details updated for Non-US citizen as Business.
		assertTrue(gcnGcPaymentSettingsPage.verifyUpdationOfTaxDetails(testData.get("country_nonus").toString(), 
				testData.get("entityType1").toString(), testData.get("bizName1").toString()), "Error Saving Tax Details");
					
		//gcnGcPaymentSettingsPage.setPaymentSettings(testData.get("Add1_street").toString(), testData.get("Add1_city").toString(), testData.get("Add1_state").toString(), testData.get("Add1_zip").toString(), 1);

		assertAll();
	}
	
}
