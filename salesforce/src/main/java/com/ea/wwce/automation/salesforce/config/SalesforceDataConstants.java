package com.ea.wwce.automation.salesforce.config;

import com.ea.wwce.automation.base.config.BaseDataConstants;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

/**
 * 
 * @author sadabala
 *
 */

public class SalesforceDataConstants extends BaseDataConstants {

	// Configure Timeouts
	public static final String SF_URL = "";

	// Configure Timeouts
	public static final int PAGE_LOAD_TIMEOUT = 10;
	public static final int IMPLICIT_TIMEOUT = 30;

	// GSpec Configuration
	public static final String SF_GSPEC_ROOT_PATH = "salesforce\\src\\main\\resources\\gspecs\\";

	// Test Data Configuration Path
	public static final String SF_TEST_DATA_ROOT_PATH = "salesforce\\src\\test\\resources\\testdata\\";
	public static final String SF_TEST_DATA_FILE_NAME = "testdata.json";

	// Environment URLs
	public static final String SF_BASE_URL_QAPC = "https://test.salesforce.com/";

	public static final String SF_BASE_URL_RUNFC = "https://test.salesforce.com/";

	public static final String SF_BASE_URL_UATPC = "https://test.salesforce.com/";

	public static final String SF_BASE_URL_PROD = "";

	public static String SF_AUT_URL = "";
}
