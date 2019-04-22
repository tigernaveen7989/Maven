package com.ea.wwce.automation.gcn.intigration.tests;



import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.gcn.tests.GcnBaseTest;

public class LoginToAdminAndGcSite extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(LoginToAdminAndGcSite.class);
	Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
	
	@Test
	public void verifyLoginToAdminAndGcSite(ITestContext context) {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID = "16174,16171";

		context.setAttribute("testcase_id", testID);
		
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();

		gcnAdminLoginPage.verifyLogin(testData.get("username").toString(),
				testData.get("password").toString());
		
		//Load Admin Driver instance.
		mDriverInstance.put("ADMIN", this.driver);
		
		//Create new Instance for GameChanger Site.
		this.driver=this.loadNewInstance(context);
		
		//Load Gc Web Site.
		gcnGcHomePage.launchGcAndSignIn();
		
		gcnGcLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
		
		//Load GC Driver Instance
		mDriverInstance.put("GC", this.driver);
		
		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);
		
		assertAll();
		
		
	}

}
