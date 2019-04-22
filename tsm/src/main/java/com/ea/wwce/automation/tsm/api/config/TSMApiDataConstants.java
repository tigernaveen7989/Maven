package com.ea.wwce.automation.tsm.api.config;

/**
 * 
 * @author rgandham
 * @description Class to capture the TSM project specific data constants
 *
 */

public class TSMApiDataConstants {

	protected static final int PAGE_LOAD_TIMEOUT = 90;
	protected static final int IMPLICIT_TIMEOUT = 30;

	// Pick the payload file from below location
	public static final String TSMAPI_INPUT_FILE_ROOT = "\\src\\test\\resources\\apiPayLoads\\REST\\request\\";

	// Place the response file to below location
	public static final String TSMAPI_OUTPUT_FILE_ROOT = "\\src\\test\\resources\\apiPayLoads\\REST\\response\\";
	
	//Place the response file to below location
	public static final String TSMAPI_ASSERTION_FILE_ROOT = "\\src\\test\\resources\\apiPayLoads\\REST\\assertions\\";

	// Place the token file payload to below location
	public static final String TSMAPI_TOKEN_FILE_PATH = "\\src\\test\\resources\\apiPayLoads\\REST\\token\\AUTH_TOKEN.json";

	// Place the token file payload to below location
	public static final String TSMAPI_TOKEN_URL = "/oauth/token";

	// Pick the master configuration of TSM APIs from below location
	public static final String API_MASTER_CONFIG_FILE_PATH = "\\src\\test\\resources\\config\\api_config.json";

	// Configure base service URI
	public static final String TSMAPI_SERVICE_BASE_URL_QA3PC = "https://wwcesfdc.qa01.sov.ea.com";
	public static final String TSMAPI_SERVICE_BASE_URL_RUNFC = "";
	public static final String TSMAPI_SERVICE_BASE_URL_UATPC = "https://wwcesfdc.qa01.sov.ea.com";
	public static final String TSMAPI_SERVICE_BASE_URL_PROD = "";
	public static String TSMAPI_SERVICE_BASE_URL = "";
	public static String AUTH_TOKEN = "";
	public static int API_TOKEN_EXPIRY_WINDOW = 0;
	public static int API_TOKEN_EXPIRY_END_TIME = 0;

	// TestRail Configuration
	public static final String TSM_TESTRAIL_CONNECTION_URL = "https://e2qa.testrail.io";
	public static final String TSM_TESTRAIL_USERNAME = "rgandham@contractor.ea.com";
	public static final String TSM_TESTRAIL_PASSWORD = "Sahas*9966";

}
