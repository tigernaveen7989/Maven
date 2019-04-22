package com.ea.wwce.automation.sovereign.api.tests;

import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.api.APIBase;
import com.ea.wwce.automation.base.tests.APIBaseTest;
import com.ea.wwce.automation.base.util.testmanagement.TestRailClient;
import com.ea.wwce.automation.sovereign.config.SovereignDataConstants;

/**
 * 
 * @author rdronamraju
 * @description Base Test for all the Sovereign API test. It will load the app specific settings before testing calls.
 */

public class SovereignAPIBaseTest extends APIBaseTest{
	
	public static Logger logger = Logger.getLogger(SovereignAPIBaseTest.class);
	
	//Load the context with the master API config file location for Sovereign application
	@BeforeSuite
	public void beforeSuite(ITestContext context){
		context.setAttribute("API_MASTER_CONFIG_FILE_PATH", System.getProperty("user.dir")+
				SovereignDataConstants.API_MASTER_CONFIG_FILE_PATH);
		context.setAttribute("API_INPUT_PAYLOAD_PATH", System.getProperty("user.dir")+
				SovereignDataConstants.SOVEREIGN_INPUT_FILE_ROOT);
		context.setAttribute("API_OUTPUT_RESPONSE_PATH", System.getProperty("user.dir")+
				SovereignDataConstants.SOVEREIGN_OUTPUT_FILE_ROOT);	
		context.setAttribute("API_ASSERTION_PATH", System.getProperty("user.dir")+
				SovereignDataConstants.SOVEREIGN_ASSERTION_FILE_ROOT);
		context.setAttribute("SOVEREIGN_API_TOKEN_FILE_PATH", System.getProperty("user.dir")+
				SovereignDataConstants.SOVEREIGN_TOKEN_FILE_PATH);		
		context.setAttribute("SOVEREIGN_API_TOKEN_URL", SovereignDataConstants.SOVEREIGN_TOKEN_URL);
		context.setAttribute("SET_EXECUTION_START_TIME", (int)(new Date().getTime()/1000));
		logger.info("Suite execution start time : " + context.getAttribute("SET_EXECUTION_START_TIME"));
		super.beforeSuite(context);
		
		// Create Test Rail Client connection object and pass the plan id
		TestRailClient testRailClient = new TestRailClient(SovereignDataConstants.SOVEREIGN_TESTRAIL_CONNECTION_URL,
				SovereignDataConstants.SOVEREIGN_TESTRAIL_USERNAME,
				SovereignDataConstants.SOVEREIGN_TESTRAIL_PASSWORD);

		context.setAttribute("testRailClientObject", testRailClient);
	}
	

	@BeforeMethod
	public void beforeMethod(ITestContext context){

		//Load the base service URL based on the environment and update the test context
		//Load the service base URL based on the environment chosen
		String environment = context.getAttribute("TEST_ENVIRONMENT").toString();
		
		if(environment.equalsIgnoreCase("QAPC")){
			context.setAttribute("BASE_SERVICE_URL", SovereignDataConstants.SOVEREIGN_SERVICE_BASE_URL_QAPC);
		}else if(environment.equalsIgnoreCase("RUNFC")) {
				context.setAttribute("BASE_SERVICE_URL", SovereignDataConstants.SOVEREIGN_SERVICE_BASE_URL_RUNFC);
		}else if(environment.equalsIgnoreCase("UATPC")) {
			context.setAttribute("BASE_SERVICE_URL", SovereignDataConstants.SOVEREIGN_SERVICE_BASE_URL_UATPC);
		}else if(environment.equalsIgnoreCase("PROD")) {
			context.setAttribute("BASE_SERVICE_URL", SovereignDataConstants.SOVEREIGN_SERVICE_BASE_URL_PROD);
		}else{
			context.setAttribute("BASE_SERVICE_URL", SovereignDataConstants.SOVEREIGN_SERVICE_BASE_URL_QAPC);
		}
		
		SovereignDataConstants.SOVEREIGN_SERVICE_BASE_URL = context.getAttribute("BASE_SERVICE_URL").toString();
		
		//Generate auth token before each method
		String tokenURL = SovereignDataConstants.SOVEREIGN_SERVICE_BASE_URL + SovereignDataConstants.SOVEREIGN_TOKEN_URL;
		
		//If auth token is not generated, generate else check the current timestamp falls within expiry time.If yes,do not generate
		//fresh one.
		
		int currentTime = (int) (new Date().getTime()/1000);
		logger.info("Current time : " + currentTime);
		
		if(SovereignDataConstants.API_TOKEN_EXPIRY_WINDOW == 0 || currentTime > SovereignDataConstants.API_TOKEN_EXPIRY_END_TIME){
			String authTokenDetails = APIBase.getAuthorizationToken(tokenURL, context.getAttribute("SOVEREIGN_API_TOKEN_FILE_PATH").toString(), environment);
			
			//Capture the auth token from auth token response
			context.setAttribute("authToken",authTokenDetails.split(",")[0]);
			SovereignDataConstants.AUTH_TOKEN  = context.getAttribute("authToken").toString();
			
			//Capture the expiry period of the Token
			context.setAttribute("expiryWindow",Integer.parseInt(authTokenDetails.split(",")[1]));
			
			logger.info("Token is generated at timestamp " + context.getAttribute("SET_EXECUTION_START_TIME").toString() );
			
			//Calculate the end time when the token expires and there will be a need to generate new token.
			SovereignDataConstants.API_TOKEN_EXPIRY_WINDOW = Integer.parseInt(context.getAttribute("expiryWindow").toString());
			logger.info("Expiry window of the token : " + SovereignDataConstants.API_TOKEN_EXPIRY_WINDOW );
			
			SovereignDataConstants.API_TOKEN_EXPIRY_END_TIME = Integer.parseInt(context.getAttribute("SET_EXECUTION_START_TIME").toString()) + 
					SovereignDataConstants.API_TOKEN_EXPIRY_WINDOW;						
			context.setAttribute("expiryEndTime",SovereignDataConstants.API_TOKEN_EXPIRY_END_TIME);
			logger.info("Estimated time when new token need to be generated : " + SovereignDataConstants.API_TOKEN_EXPIRY_END_TIME );
			
		}else {	
			logger.info("Current time " + currentTime + " is less than expiry period " + SovereignDataConstants.API_TOKEN_EXPIRY_END_TIME + 
					".Token is still active. Using existing token ... ");
			context.setAttribute("authToken",SovereignDataConstants.AUTH_TOKEN);
			context.setAttribute("expiryWindow",SovereignDataConstants.API_TOKEN_EXPIRY_WINDOW);
		}
		super.beforeMethod(context);
	}
 
}
