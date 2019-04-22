package com.ea.wwce.automation.tsm.config;

import com.ea.wwce.automation.base.config.BaseDataConstants;

/**
 * 
 * @author rgandham
 *
 */

public class TSMDataConstants extends BaseDataConstants {

	// Configure Timeouts
	public static final String TSM_URL = "";
	
	//Configure Timeouts
		public static final int PAGE_LOAD_TIMEOUT = 40;
		public static final int IMPLICIT_TIMEOUT = 1;
		
	// GSpec Configuration
	public static final String TSM_GSPEC_ROOT_PATH = "";

	// Test Data Configuration Path
	public static final String TSM_TEST_DATA_ROOT_PATH = "tsm\\src\\test\\resources\\testdata\\";
	public static final String TSM_TEST_DATA_FILE_NAME = "testdata.json";

	// Environment URLs
	public static final String TSM_BASE_URL_QA3PC = "https://wwce-eait--qa3pc.cs96.my.salesforce.com/";
	public static final String TSM_BASE_URL_RUNFC = "https://wwce-eait--qa3pc.lightning.force.com";
	public static final String TSM_BASE_URL_UATPC = "https://wwce-eait--qa3pc.lightning.force.com";
	public static final String TSM_BASE_URL_PROD = "https://wwce-eait--qa3pc.lightning.force.com";
	public static String TSM_AUT_URL = "";

	// TestRail Configuration
	public static final String TSM_TESTRAIL_CONNECTION_URL = "https://e2qa.testrail.io";
	public static final String TSM_TESTRAIL_USERNAME = "rgandham@contractor.ea.com";
	public static final String TSM_TESTRAIL_PASSWORD = "Sahas*9966";

}
