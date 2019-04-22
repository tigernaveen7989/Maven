package com.ea.wwwce.automation.gcn.api.tests;

import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.ea.wwce.automation.base.api.APIBase;
import com.ea.wwce.automation.base.tests.APIBaseTest;
import com.ea.wwce.automation.base.util.testmanagement.TestRailClient;
import com.ea.wwce.automation.gcn.config.GcnDataConstants;

public class GcnAPIBaseTest extends APIBaseTest{
	
	public static Logger logger = Logger.getLogger(GcnAPIBaseTest.class);
	
	//Load the context with the master API config file location for Thor application
	@BeforeSuite
	public void beforeSuite(ITestContext context) {
		context.setAttribute("API_MASTER_CONFIG_FILE_PATH", System.getProperty("user.dir")+
				GcnDataConstants.API_MASTER_CONFIG_FILE_PATH);
		context.setAttribute("API_INPUT_PAYLOAD_PATH", System.getProperty("user.dir")+
				GcnDataConstants.GCN_INPUT_FILE_ROOT);
		context.setAttribute("API_OUTPUT_RESPONSE_PATH", System.getProperty("user.dir")+
				GcnDataConstants.GCN_OUTPUT_FILE_ROOT);
		context.setAttribute("API_ASSERTION_PATH", System.getProperty("user.dir")+
				GcnDataConstants.GCN_API_ASSERTION_ROOT);	
		
		context.setAttribute("GCN_API_TOKEN_FILE_PATH", System.getProperty("user.dir")+
				GcnDataConstants.GCN_TOKEN_FILE_PATH);		
		context.setAttribute("GCN_API_TOKEN_URL", GcnDataConstants.GCN_TOKEN_URL);
		context.setAttribute("SET_EXECUTION_START_TIME", (int)(new Date().getTime()/1000));
		logger.info("Suite execution start time : " + context.getAttribute("SET_EXECUTION_START_TIME"));
		
		//keeping this line here as we need to override global test rail configuration
		super.beforeSuite(context);		
		
		// Create Test Rail Client connection object and pass the plan id
		TestRailClient testRailClient = new TestRailClient(GcnDataConstants.GCN_TESTRAIL_CONNECTION_URL,
				GcnDataConstants.GCN_TESTRAIL_USERNAME, GcnDataConstants.GCN_TESTRAIL_PASSWORD);
		context.setAttribute("testRailClientObject", testRailClient);
	}
	
	@BeforeMethod
	public void beforeMethod(ITestContext context){

		//Load the base service URL based on the environment and update the test context
		//Load the service base URL based on the environment chosen
		String environment = context.getAttribute("TEST_ENVIRONMENT").toString();
		
		if(environment.equalsIgnoreCase("QAPC")){
			context.setAttribute("BASE_SERVICE_URL", GcnDataConstants.GCN_SERVICE_BASE_URL_QAPC);
		}else if(environment.equalsIgnoreCase("RUNFC")) {
				context.setAttribute("BASE_SERVICE_URL", GcnDataConstants.GCN_SERVICE_BASE_URL_RUNFC);
		}else if(environment.equalsIgnoreCase("UATPC")) {
			context.setAttribute("BASE_SERVICE_URL", GcnDataConstants.GCN_SERVICE_BASE_URL_UATPC);
		}else if(environment.equalsIgnoreCase("PROD")) {
			context.setAttribute("BASE_SERVICE_URL", GcnDataConstants.GCN_SERVICE_BASE_URL_PROD);
		}else{
			context.setAttribute("BASE_SERVICE_URL", GcnDataConstants.GCN_SERVICE_BASE_URL_QAPC);
		}
		
		GcnDataConstants.GCN_SERVICE_BASE_URL = context.getAttribute("BASE_SERVICE_URL").toString();
		//Generate auth token before each method
		String tokenURL = GcnDataConstants.GCN_SERVICE_BASE_URL + GcnDataConstants.GCN_TOKEN_URL;
		
		int currentTime = (int) (new Date().getTime()/1000);
		logger.info("Current time : " + currentTime);
		
		
		
		if(GcnDataConstants.API_TOKEN_EXPIRY_WINDOW==0 || currentTime>GcnDataConstants.API_TOKEN_EXPIRY_END_TIME) {
			String authTokenDetails=APIBase.getAuthorizationToken(tokenURL, context.getAttribute("GCN_API_TOKEN_FILE_PATH").toString(), environment);
			
			//Capture the auth token from auth token response
			context.setAttribute("authToken",authTokenDetails.split(",")[0]);
			GcnDataConstants.AUTH_TOKEN  = context.getAttribute("authToken").toString();
			
			//Capture the expiry period of the Token
			context.setAttribute("expiryWindow",Integer.parseInt(authTokenDetails.split(",")[1]));
			
			logger.info("Token is generated at timestamp " + context.getAttribute("SET_EXECUTION_START_TIME").toString() );
			
			//Calculate the end time when the token expires and there will be a need to generate new token.
			GcnDataConstants.API_TOKEN_EXPIRY_WINDOW = Integer.parseInt(context.getAttribute("expiryWindow").toString());
			logger.info("Expiry window of the token : " + GcnDataConstants.API_TOKEN_EXPIRY_WINDOW );
			
			GcnDataConstants.API_TOKEN_EXPIRY_END_TIME = Integer.parseInt(context.getAttribute("SET_EXECUTION_START_TIME").toString()) + 
					GcnDataConstants.API_TOKEN_EXPIRY_WINDOW;						
			context.setAttribute("expiryEndTime",GcnDataConstants.API_TOKEN_EXPIRY_END_TIME);
			logger.info("Estimated time when new token need to be generated : " + GcnDataConstants.API_TOKEN_EXPIRY_END_TIME );
			
			
		}else {
			
			logger.info("Current time " + currentTime + " is less than expiry period " + GcnDataConstants.API_TOKEN_EXPIRY_END_TIME + 
					".Token is still active. Using existing token ... ");
			context.setAttribute("authToken",GcnDataConstants.AUTH_TOKEN);
			context.setAttribute("expiryWindow",GcnDataConstants.API_TOKEN_EXPIRY_WINDOW);
			
		}
		super.beforeMethod(context);
	}

}
