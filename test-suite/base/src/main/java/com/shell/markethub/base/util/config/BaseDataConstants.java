package com.shell.markethub.base.util.config;

/**
 * 
 * @author n.kumar8@shell.com
 * @description This class contains the common data constants used by all applications
 */
public class BaseDataConstants{

	private static String  projectRootPath = System.getProperty("user.dir");	
	public static final String PROJECT_ROOT_LOCATION =projectRootPath.substring(0, projectRootPath.lastIndexOf("\\")) + "\\";

	public static final String CHROME_DRIVER_PATH = "base\\src\\main\\resources\\drivers\\chromedriver\\chromedriver.exe";
	public static final String GECKO_DRIVER_PATH = "base\\src\\main\\resources\\drivers\\gecko\\geckodriver.exe";
	public static final String IEXPLORER_DRIVER_PATH ="base\\main\\test\\resources\\drivers\\ie\\IEDriverServer_win64.exe";
	public static final String EDGE_DRIVER_PATH ="base\\src\\main\\resources\\drivers\\ie\\MicrosoftWebDriver.exe";
	public static final String SAFARI_DRIVER_PATH ="base\\src\\main\\resources\\drivers\\SafariDriver.exe";

	//Configure Timeouts
	public static final int PAGE_LOAD_TIMEOUT = 30;
	public static final int IMPLICIT_TIMEOUT = 20;

	//Logger Configuration
	public static final String LOG_PROPERTIES_FILE = "base\\src\\main\\resources\\logProperties\\log4j.properties";

	//Global Data key Name
	public static final String GLOBAL_DATA_KEY_NAME = "global";

	//Global Data key Name
	public static final String HEADER_KEY_NAME = "headers";
	public static final String URI_PARAM_KEY_NAME = "uri_param";
	public static final String INPUT_PARAM_KEY_NAME = "input_param";
	public static final String POST_PARAM_KEY_NAME = "payload_param";

	//GSPEC Configuration
	public static final String GSPEC_ROOT_PATH = "base\\src\\main\\resources\\gspecs\\";

	//API Configuration
	public static final String API_BASE_AUTH_TOKEN = "";

	//Use below file to capture the  type, payload location and URI of each API
	public static final String API_MASTER_CONFIG_FILE_PATH = "\\src\\main\\resources\\api_config.json";

	//Configure the locations of input and output files for REST based calls.
	public static final String REST_INPUT_FILE_PATH = "\\src\\main\\resources\\api\\REST\\request\\";
	public static final String REST_OUTPUT_FILE_PATH = "\\src\\main\\resources\\api\\REST\\response\\";

	public static final String TEST_DATA_FILE_NAME = "testdata.json";
	
	// Test Data Configuration Path
	public static final String TEST_DATA_ROOT_PATH = "\\src\\main\\resources\\testdata\\";
	
	//Test Resources Configuration Path
	public static final String TEST_RESOURCES_ROOT_PATH="src\\test\\resources\\";
	
	//Main Resources Configuration Path
	public static final String MAIN_RESOURCES_ROOT_PATH="\\src\\main\\resources\\";

	//Attachments file path
	public static final String TEST_DATA_ATTACHMENT_PATH = "src\\test\\resources\\attachments\\";
	
	public static final String API_NAME_SEPERATOR = "@";
	public static final String API_NAME_SEPERATOR_KEY= "API_NAME_SEPERATOR";
	
	//Environment URL
	public static final String PROD_MARKETHUB_URL="https://www.markethub.shell.com";
	public static final String PERF_MARKETHUB_URL="https://akamai.markethub.shell.com/";
	public static String MARKETHUB_AUT_URL="";

}