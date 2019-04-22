package com.ea.wwce.automation.thor.config;

import com.ea.wwce.automation.base.config.BaseDataConstants;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

/**
 * 
 * @author mohan
 * @description Class to capture the Thor project specific data constants
 *
 */

public class ThorDataConstants extends BaseDataConstants{
	//Configure Timeouts
		//public static String THOR_URL = "https://wwce-eait--thordp.cs68.my.salesforce.com/";
		public static final int PAGE_LOAD_TIMEOUT = 50;
		public static final int IMPLICIT_TIMEOUT = 5;
		
		//GSpec Configuration
		public static final String THOR_GSPEC_ROOT_PATH = "thor\\src\\main\\resources\\gspecs\\";
		
		//Test Data Configuration Path
		public static final String THOR_TEST_DATA_ROOT_PATH = "thor\\src\\test\\resources\\testdata\\";
		public static final String THOR_TEST_DATA_FILE_NAME = "testdata.json";
		
		public static final String THOR_BASE_URL_QAPC = "https://wwce-eait--qa1pc.cs25.my.salesforce.com/";
		
		public static final String THOR_BASE_URL_RUNFC = "https://wwce-eait--runfc.cs7.my.salesforce.com/";
		
		public static final String THOR_BASE_URL_UATPC = "https://wwce-eait--uatpc.cs77.my.salesforce.com/";
			
		public static final String THOR_BASE_URL_PROD = "";
		
		public static String THOR_AUT_URL = "";
		
		public static final int OMNI_CONFIGURED_QUEUE_CNT=10;
		
		public static final String PLAYER_EMAIL_URL = "https://www.gmail.com";
		
		//Pick the payload file from below location
		public static final String THOR_INPUT_FILE_ROOT = "\\src\\test\\resources\\apiPayLoads\\REST\\request\\";
		
		//Place the response file to below location
		public static final String THOR_OUTPUT_FILE_ROOT = "\\src\\test\\resources\\apiPayLoads\\REST\\response\\";
		//Place the assertion file to below location
		public static final String THOR_ASSERTION_FILE_ROOT = "\\src\\test\\resources\\apiPayLoads\\REST\\assertions\\";
		//Place the token file payload to below location
		public static final String THOR_TOKEN_FILE_PATH= "\\src\\test\\resources\\apiPayLoads\\REST\\token\\AUTH_TOKEN.json";
		public static final String THOR_TOKEN_URL = "";
		
		//Pick the master configuration of THOR APIs from below location
		public static final String API_MASTER_CONFIG_FILE_PATH = "\\src\\test\\resources\\config\\api_config.json";
		
		//Configure base service URI
		public static final String THOR_SERVICE_BASE_URL_QAPC = "https://qa-toscases.ceit.ea.com";
		
		public static final String THOR_SERVICE_BASE_URL_RUNFC =  "https://r7xnaqqo95.execute-api.us-east-2.amazonaws.com";
		
		public static final String THOR_SERVICE_BASE_URL_UATPC = "https://r7xnaqqo95.execute-api.us-east-2.amazonaws.com";
			
		public static final String THOR_SERVICE_BASE_URL_PROD = "";
		
		public static String THOR_SERVICE_BASE_URL = "";
		
		public static String AUTH_TOKEN="";
		
		public static int API_TOKEN_EXPIRY_WINDOW = 0;
		
		public static int API_TOKEN_EXPIRY_END_TIME = 0;
		
		//THOR SF API related 
		//Pick the master configuration of THOR SF APIs from below location
		//public static final String THOR_SF_API_MASTER_CONFIG_FILE_PATH = "\\src\\test\\resources\\config\\sf_api_config.json";
				
		//Place the token file payload to below location
		public static final String THOR_SF_TOKEN_FILE_PATH= "\\src\\test\\resources\\apiPayLoads\\REST\\token\\SF_AUTH_TOKEN.json";
		
		//THOR SF token end point
		public static final String THOR_SF_TOKEN_URL = "/services/oauth2/token";
				
		//Configure base service URI
		
		public static final String THOR_SF_SERVICE_BASE_URL_QAPC = "https://wwce-eait--QA1PC.cs96.my.salesforce.com";
		
		public static final String THOR_SF_SERVICE_BASE_URL_RUNFC =  "https://wwce-eait--runfc.cs95.my.salesforce.com";
		
		public static final String THOR_SF_SERVICE_BASE_URL_UATPC = "https://wwce-eait--uatpc.cs77.my.salesforce.com";
			
		public static final String THOR_SF_SERVICE_BASE_URL_PROD = "";
		
		public static String THOR_SF_SERVICE_BASE_URL = "";
		
		
		//THOR Content Action API related 
		//Pick the master configuration of THOR CA APIs from below location
						
		//Place the token file payload to below location
		public static final String THOR_CA_TOKEN_FILE_PATH= "\\src\\test\\resources\\apiPayLoads\\REST\\token\\SOV_AUTH_TOKEN.json";
		
		//THOR SF token end point
		public static final String THOR_CA_TOKEN_URL = "/oauth/token";
				
		//Configure base service URI
		
		public static final String THOR_CA_SERVICE_BASE_URL_QAPC = "https://wwcesfdc.stage.sov.ea.com";
		
		public static final String THOR_CA_SERVICE_BASE_URL_RUNFC =  "http://sovapp01.uat.rspc-ord.ea.com:8090";
		
		public static final String THOR_CA_SERVICE_BASE_URL_UATPC = "https://wwcesfdc.qa02.sov.ea.com";
			
		public static final String THOR_CA_SERVICE_BASE_URL_PROD = "";
		
		public static String THOR_CA_SERVICE_BASE_URL = "";
		
		//TestRail Configuration
		public static final String THOR_TESTRAIL_CONNECTION_URL = "https://e2qa.testrail.io";
		public static final String THOR_TESTRAIL_USERNAME = "mkamsu@contractor.ea.com";
		public static final String THOR_TESTRAIL_PASSWORD = "mkamsu12!@";

		
		

	
	
}
