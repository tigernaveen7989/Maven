package com.ea.wwce.automation.ahq.config;

import com.ea.wwce.automation.base.config.BaseDataConstants;

/**
 * 
 * @author rdronamraju
 *
 */

public class AHQDataConstants extends BaseDataConstants{

	//Configure Timeouts
	public static String AHQ_URL = "https://communitystage.help.ea.com/t5/Answer-HQ-English/ct-p/"
			+ "AHQ-English";
	public static final int PAGE_LOAD_TIMEOUT = 30;
	public static final int IMPLICIT_TIMEOUT = 30;
	
	//GSpec Configuration
	public static final String AHQ_GSPEC_ROOT_PATH = "ahq\\src\\main\\resources\\gspecs\\";
	
	//Test Data Configuration Path
	public static final String AHQ_TEST_DATA_ROOT_PATH = "ahq\\src\\main\\resources\\testdata\\";
	public static final String AHQ_TEST_DATA_FILE_NAME = "testdata.json";
	
	//Logger Configuration
	public static final String LOG_PROPERTIES_FILE = "ahq\\src\\main\\resources\\logProperties\\log4j.properties";
}
