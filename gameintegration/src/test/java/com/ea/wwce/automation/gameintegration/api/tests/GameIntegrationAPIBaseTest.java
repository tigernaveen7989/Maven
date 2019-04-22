package com.ea.wwce.automation.gameintegration.api.tests;

import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.api.APIBase;
import com.ea.wwce.automation.base.tests.APIBaseTest;
import com.ea.wwce.automation.gameintegration.config.GameIntegrationDataConstants;


/**
 * 
 * @author kajoshi1
 * @description Base Test for all the GAMEINTEGRATION API test. It will load the app specific settings before testing calls.
 */

public class GameIntegrationAPIBaseTest extends APIBaseTest{
	
	public static Logger logger = Logger.getLogger(GameIntegrationAPIBaseTest.class);
	
	//Load the context with the master API config file location for Sovereign application
	@BeforeSuite
	public void beforeSuite(ITestContext context){
		context.setAttribute("API_MASTER_CONFIG_FILE_PATH", System.getProperty("user.dir")+
				GameIntegrationDataConstants.API_MASTER_CONFIG_FILE_PATH);
		context.setAttribute("API_INPUT_PAYLOAD_PATH", System.getProperty("user.dir")+
				GameIntegrationDataConstants.GAMEINTEGRATION_INPUT_FILE_ROOT);
		context.setAttribute("API_ASSERTION_PATH", System.getProperty("user.dir")+
				GameIntegrationDataConstants.GAMEINTEGRATION_ASSERTION_FILE_ROOT);
		context.setAttribute("API_OUTPUT_RESPONSE_PATH", System.getProperty("user.dir")+
				GameIntegrationDataConstants.GAMEINTEGRATION_OUTPUT_FILE_ROOT);		
		context.setAttribute("SOVEREIGN_API_TOKEN_FILE_PATH", System.getProperty("user.dir")+
				GameIntegrationDataConstants.GAMEINTEGRATION_TOKEN_FILE_PATH);		
		context.setAttribute("SOVEREIGN_API_TOKEN_URL", GameIntegrationDataConstants.GAMEINTEGRATION_TOKEN_URL);
		context.setAttribute("SET_EXECUTION_START_TIME", (int)(new Date().getTime()/1000));
		logger.info("Suite execution start time : " + context.getAttribute("SET_EXECUTION_START_TIME"));
		super.beforeSuite(context);
	}
	

	@BeforeMethod
	public void beforeMethod(ITestContext context){

		//Load the base service URL based on the environment and update the test context
		//Load the service base URL based on the environment chosen
		String environment = context.getAttribute("TEST_ENVIRONMENT").toString();
		
		if(environment.equalsIgnoreCase("QAPC")){
			context.setAttribute("BASE_SERVICE_URL", GameIntegrationDataConstants.GAMEINTEGRATION_SERVICE_BASE_URL_QAPC);
		}else if(environment.equalsIgnoreCase("RUNFC")) {
				context.setAttribute("BASE_SERVICE_URL", GameIntegrationDataConstants.GAMEINTEGRATION_SERVICE_BASE_URL_RUNFC);
		}else if(environment.equalsIgnoreCase("UATPC")) {
			context.setAttribute("BASE_SERVICE_URL", GameIntegrationDataConstants.GAMEINTEGRATION_SERVICE_BASE_URL_UATPC);
		}else if(environment.equalsIgnoreCase("PROD")) {
			context.setAttribute("BASE_SERVICE_URL", GameIntegrationDataConstants.GAMEINTEGRATION_SERVICE_BASE_URL_PROD);
		}else{
			context.setAttribute("BASE_SERVICE_URL", GameIntegrationDataConstants.GAMEINTEGRATION_SERVICE_BASE_URL_QAPC);
		}
		
		GameIntegrationDataConstants.GAMEINTEGRATION_SERVICE_BASE_URL = context.getAttribute("BASE_SERVICE_URL").toString();
		
		//Generate auth token before each method
		String tokenURL = GameIntegrationDataConstants.GAMEINTEGRATION_SERVICE_BASE_URL + GameIntegrationDataConstants.GAMEINTEGRATION_TOKEN_URL;
		
		super.beforeMethod(context);
	}
 
}
