package com.ea.wwce.automation.omega.config;

import com.ea.wwce.automation.base.config.BaseDataConstants;

/**
 * 
 * @author sadabala
 *
 */

public class OmegaDataConstants extends BaseDataConstants {

	// Configure Timeouts
	public static final String OMEGA_URL = "";
	
	//Configure Timeouts
		public static final int PAGE_LOAD_TIMEOUT = 30;
		public static final int IMPLICIT_TIMEOUT = 30;

	// GSpec Configuration
	public static final String OMEGA_GSPEC_ROOT_PATH = "omega\\src\\main\\resources\\gspecs\\";

	// Test Data Configuration Path
	public static final String OMEGA_TEST_DATA_ROOT_PATH = "omega\\src\\test\\resources\\testdata\\";
	public static final String OMEGA_TEST_DATA_FILE_NAME = "testdata.json";

	// Environment URLs
	public static final String OMEGA_BASE_URL_QAPC = "https://qapc-helporigin.cs77.force.com/omega";

	public static final String OMEGA_BASE_URL_RUNFC = "https://runfc-helporigin.cs95.force.com/omega";

	public static final String OMEGA_BASE_URL_UATPC = "https://uatfc-helporigin.cs97.force.com/omega";

	public static final String OMEGA_BASE_URL_PROD = "";

	public static String OMEGA_AUT_URL = "";

}
