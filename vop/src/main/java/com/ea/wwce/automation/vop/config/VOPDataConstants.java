package com.ea.wwce.automation.vop.config;

import com.ea.wwce.automation.base.config.BaseDataConstants;

/**
 * 
 * @author praveen
 *
 */

public class VOPDataConstants extends BaseDataConstants {
	
	protected static final int PAGE_LOAD_TIMEOUT = 90;
	protected static final int IMPLICIT_TIMEOUT = 30;

	
	//Pick the payload file from below location
	public static final String VOP_INPUT_FILE_ROOT = "\\src\\test\\resources\\apiPayLoads\\REST\\request\\";
	
	//Place the response file to below location
	public static final String VOP_OUTPUT_FILE_ROOT = "\\src\\test\\resources\\apiPayLoads\\REST\\response\\";
	
	//Place the assertion file to below location
		public static final String VOP_ASSERTION_FILE_ROOT = "\\src\\test\\resources\\apiPayLoads\\REST\\assertions\\";
		
		
	//Place the token file payload to below location
	public static final String VOP_TOKEN_FILE_PATH= "\\src\\test\\resources\\apiPayLoads\\REST\\token\\AUTH_TOKEN.json";
	
	//Place the token file payload to below location
	public static final String VOP_TOKEN_URL = "/oauth/token";
	
	//Pick the master configuration of VOP APIs from below location
	public static final String API_MASTER_CONFIG_FILE_PATH = "\\src\\test\\resources\\config\\api_config.json";
	
	//Configure base service URI
	
	public static final String VOP_SERVICE_BASE_URL_QAPC = "https://wwcesfdc.stage.sov.ea.com";
	
	public static final String VOP_SERVICE_BASE_URL_RUNFC =  "";
	
	public static final String VOP_SERVICE_BASE_URL_UATPC = "";
		
	public static final String VOP_SERVICE_BASE_URL_PROD = "";
	
	public static String VOP_SERVICE_BASE_URL = "";
	
	public static String AUTH_TOKEN="";
	
	public static int API_TOKEN_EXPIRY_WINDOW = 0;
	
	public static int API_TOKEN_EXPIRY_END_TIME = 0;

	// Configure Timeouts
	public static final String VOP_URL = "";
		
	// GSpec Configuration
	public static final String VOP_GSPEC_ROOT_PATH = "";

	// Test Data Configuration Path
	public static final String VOP_TEST_DATA_ROOT_PATH = "tsm\\src\\test\\resources\\testdata\\";
	public static final String VOP_TEST_DATA_FILE_NAME = "testdata.json";

	// Environment URLs
	public static final String VOP_BASE_URL_QAPC = "https://wwce-eait--qa1pc.cs25.my.salesforce.com/";
	public static final String VOP_BASE_URL_RUNFC = "https://wwce-eait--qa3pc.lightning.force.com";
	public static final String VOP_BASE_URL_UATPC = "https://wwce-eait--qa3pc.lightning.force.com";
	public static final String VOP_BASE_URL_PROD = "https://wwce-eait--qa3pc.lightning.force.com";
	public static String VOP_AUT_URL = "";

	// TestRail Configuration
	public static final String VOP_TESTRAIL_CONNECTION_URL = "https://e2qa.testrail.io";
	public static final String VOP_TESTRAIL_USERNAME = "plingampally@contractor.ea.com";
	public static final String VOP_TESTRAIL_PASSWORD = "Praveen12!@";

}
