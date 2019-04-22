package com.ea.wwce.automation.vop.api.tests;

import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.ea.wwce.automation.base.tests.APIBaseTest;
import com.ea.wwce.automation.base.util.testmanagement.TestRailClient;
import com.ea.wwce.automation.vop.config.VOPDataConstants;

/**
 * 
 * @author Praveen Lingampally
 * @description Base Test for all the VOP API test. It will load the app specific settings before testing calls.
 */

public class VOPAPIBaseTest extends APIBaseTest{
	
	public static Logger logger = Logger.getLogger(VOPAPIBaseTest.class);
	
	//Load the context with the master API config file location for VOP application
	@BeforeSuite
	public void beforeSuite(ITestContext context){
		context.setAttribute("API_MASTER_CONFIG_FILE_PATH", System.getProperty("user.dir")+
				VOPDataConstants.API_MASTER_CONFIG_FILE_PATH);
		context.setAttribute("API_INPUT_PAYLOAD_PATH", System.getProperty("user.dir")+
				VOPDataConstants. VOP_INPUT_FILE_ROOT);
		context.setAttribute("API_OUTPUT_RESPONSE_PATH", System.getProperty("user.dir")+
				VOPDataConstants.VOP_OUTPUT_FILE_ROOT);	
		context.setAttribute("API_ASSERTION_PATH",
				System.getProperty("user.dir")+	VOPDataConstants.VOP_ASSERTION_FILE_ROOT); 
		context.setAttribute("VOP_API_TOKEN_FILE_PATH", System.getProperty("user.dir")+
				VOPDataConstants.VOP_TOKEN_FILE_PATH);		
		context.setAttribute("VOP_API_TOKEN_URL", VOPDataConstants.VOP_TOKEN_URL);
		context.setAttribute("SET_EXECUTION_START_TIME", (int)(new Date().getTime()/1000));
		logger.info("Suite execution start time : " + context.getAttribute("SET_EXECUTION_START_TIME"));
		
		//keeping this line here as we need to override global test rail configuration
		super.beforeSuite(context);		
		
		// Create Test Rail Client connection object and pass the plan id
		TestRailClient testRailClient = new TestRailClient(VOPDataConstants.VOP_TESTRAIL_CONNECTION_URL,
		VOPDataConstants.VOP_TESTRAIL_USERNAME, VOPDataConstants.VOP_TESTRAIL_PASSWORD);
		context.setAttribute("testRailClientObject", testRailClient);
				
		//super.beforeSuite(context);
	}
	

	@BeforeMethod
	public void beforeMethod(ITestContext context){

		//Load the base service URL based on the environment and update the test context
		//Load the service base URL based on the environment chosen
		String environment = context.getAttribute("TEST_ENVIRONMENT").toString();
		
		if(environment.equalsIgnoreCase("QAPC")){
			context.setAttribute("BASE_SERVICE_URL", VOPDataConstants.VOP_SERVICE_BASE_URL_QAPC);
		}else if(environment.equalsIgnoreCase("RUNFC")) {
				context.setAttribute("BASE_SERVICE_URL", VOPDataConstants.VOP_SERVICE_BASE_URL_RUNFC);
		}else if(environment.equalsIgnoreCase("UATPC")) {
			context.setAttribute("BASE_SERVICE_URL", VOPDataConstants.VOP_SERVICE_BASE_URL_UATPC);
		}else if(environment.equalsIgnoreCase("PROD")) {
			context.setAttribute("BASE_SERVICE_URL", VOPDataConstants.VOP_SERVICE_BASE_URL_PROD);
		}else{
			context.setAttribute("BASE_SERVICE_URL", VOPDataConstants.VOP_SERVICE_BASE_URL_QAPC);
		}
		
		VOPDataConstants.VOP_SERVICE_BASE_URL = context.getAttribute("BASE_SERVICE_URL").toString();
		//Generate auth token before each method
		String tokenURL = VOPDataConstants.VOP_SERVICE_BASE_URL + VOPDataConstants.VOP_TOKEN_URL;
		super.beforeMethod(context);
	}
 
}
