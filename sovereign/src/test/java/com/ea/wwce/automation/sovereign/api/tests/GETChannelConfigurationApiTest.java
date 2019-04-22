package com.ea.wwce.automation.sovereign.api.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.tests.APIBaseTest;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;



public class GETChannelConfigurationApiTest extends SovereignAPIBaseTest{
	
	public static Logger logger = Logger.getLogger(GETChannelConfigurationApiTest.class);
	DataProviders assertionProvider;
	
	@BeforeMethod
	public void beforeMethod(ITestContext context){
		context.setAttribute("API_KEY", "GET_CHANNEL_CONFIGURATION_API");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description ="verify Premium Customer value API",groups={"Sanity"})
	@Description("Verification of Channel Configuration API")
	public void verifyChannelConfigurationAPI(ITestContext context){			
				
		//Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "20584";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);	 	
		
		//Load the assertions needed for the test    	
    	HashMap<String,Object> assertions= (HashMap<String,Object>)assertionProvider.getTestData(testCaseID,"ASSERTIONS");
    	
		logger.info("validating Two factor Authentication Test" + testCaseID);			
    	
		base.validateResponse();
		//this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");	
		this.assertEquals(base.getResponseCode(),Integer.parseInt(assertions.get("response_code").toString()) , "Matching response code as 200");
		this.assertEquals("test1",assertions.get("test1").toString() , "test1");
		this.assertEquals("test2",assertions.get("test2").toString(), "test2");
		this.assertEquals("test3",assertions.get("test3").toString(), "test3");
		
		assertAll();
	}
 
}
