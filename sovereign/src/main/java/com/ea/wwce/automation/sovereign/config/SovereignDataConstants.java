package com.ea.wwce.automation.sovereign.config;

import com.ea.wwce.automation.base.config.BaseDataConstants;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

/**
 * 
 * @author rdronamraju
 * @description Class to capture the Sovereign project specific data constants
 *
 */

public class SovereignDataConstants extends BaseDataConstants{


	protected static final int PAGE_LOAD_TIMEOUT = 90;
	protected static final int IMPLICIT_TIMEOUT = 30;

	
	//Pick the payload file from below location
	public static final String SOVEREIGN_INPUT_FILE_ROOT = "\\src\\test\\resources\\apiPayLoads\\REST\\request\\";
	
	//Place the response file to below location
	public static final String SOVEREIGN_OUTPUT_FILE_ROOT = "\\src\\test\\resources\\apiPayLoads\\REST\\response\\";
	
	//Place the response file to below location
		public static final String SOVEREIGN_ASSERTION_FILE_ROOT = "\\src\\test\\resources\\apiPayLoads\\REST\\assertions\\";
		
		
	//Place the token file payload to below location
	public static final String SOVEREIGN_TOKEN_FILE_PATH= "\\src\\test\\resources\\apiPayLoads\\REST\\token\\AUTH_TOKEN.json";
	
	//Place the token file payload to below location
	public static final String SOVEREIGN_TOKEN_URL = "/oauth/token";
	
	//Pick the master configuration of Sovereign APIs from below location
	public static final String API_MASTER_CONFIG_FILE_PATH = "\\src\\test\\resources\\config\\api_config.json";
	
	//Configure base service URI
	
	public static final String SOVEREIGN_SERVICE_BASE_URL_QAPC = "http://sovapp01.qa.rspc-ord.ea.com:8080";
	
	public static final String SOVEREIGN_SERVICE_BASE_URL_RUNFC =  "";
	
	public static final String SOVEREIGN_SERVICE_BASE_URL_UATPC = "http://sovapp01.uat.rspc-ord.ea.com:8100";
		
	public static final String SOVEREIGN_SERVICE_BASE_URL_PROD = "";
	
	public static String SOVEREIGN_SERVICE_BASE_URL = "";
	
	public static String AUTH_TOKEN="";
	
	public static int API_TOKEN_EXPIRY_WINDOW = 0;
	
	public static int API_TOKEN_EXPIRY_END_TIME = 0;
	
	public static final String SOVEREIGN_TESTRAIL_CONNECTION_URL = "https://e2qa.testrail.io";
	public static final String SOVEREIGN_TESTRAIL_USERNAME = "sadabala@contractor.ea.com";
	public static final String SOVEREIGN_TESTRAIL_PASSWORD = "Testrail12!@";
	
	
}
