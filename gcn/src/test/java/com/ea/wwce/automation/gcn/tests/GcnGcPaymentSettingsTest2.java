package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnGcPaymentSettingsTest2 extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(GcnGcPaymentSettingsTest2.class);
	
	/*This class is to check the Payment Settings for Game Changer.
	 * 
	 * 
	 * */
	
	
	@Test(priority=1)
	public void gcnGcPaymentSettingsTest2(ITestContext context) throws InterruptedException {
		Map<String,Object> testData=new HashMap<String,Object>();
		String testID="global";
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider=(DataProviders)context.getAttribute("dataProvider");
		testData=dataProvider.getTestData(testID);
		
		gcnGcHomePage.launchGcAndSignIn();
		
		gcnGcPage=gcnGcLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
		
		gcnGcPage.clickOnProfileLink();
		assertTrue(gcnGcProfilePage.verifyNavigationToProfilePage()	, "Error landing into Profile Page");	
			
		
		
		assertAll();
	}
	

}
