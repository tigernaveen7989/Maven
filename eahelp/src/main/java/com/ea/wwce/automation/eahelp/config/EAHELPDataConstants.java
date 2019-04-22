package com.ea.wwce.automation.eahelp.config;

import com.ea.wwce.automation.base.config.BaseDataConstants;

/**
 * 
 * @author rdronamraju
 *
 */

public class EAHELPDataConstants extends BaseDataConstants {

	// Configure Timeouts
	public static String EAHELP_URL = "";
	public static final int PAGE_LOAD_TIMEOUT = 120;
	public static final int IMPLICIT_TIMEOUT = 1;

	// GSpec Configuration
	public static final String EAHELP_GSPEC_ROOT_PATH = "eahelp\\src\\main\\resources\\gspecs\\";

	// Test Data Configuration Path
	public static final String EAHELP_TEST_DATA_ROOT_PATH = "eahelp\\src\\test\\resources\\testdata\\";

	// Test Data Configuration Path
	public static final String EAHELP_TEST_DATA_ATTACHMENT_PATH = "eahelp\\src\\test\\resources\\attachments\\";

	public static final String EAHELP_TEST_DATA_FILE_NAME = "testdata.json";

	public static final String EAHELP_BASE_URL_QAPC = "http://qa.help.ea.com/en/";

	public static final String EAHELP_BASE_URL_RUNFC = "http://qa1.help.ea.com/en/";

	public static final String EAHELP_BASE_URL_UATPC = "http://uat.help.ea.com/en/";

	public static final String EAHELP_BASE_URL_PROD = "http://help.ea.com/en/";

	public static String EAHELP_AUT_URL = "";
	
	//TestRail Configuration
	
	public static final String EAHelp_TESTRAIL_CONNECTION_URL = "https://e2qa.testrail.io";
	public static final String EAHelp_TESTRAIL_USERNAME = "sadabala@contractor.ea.com";
	public static final String EAHelp_TESTRAIL_PASSWORD = "Testrail12!@";
	

}
