package com.ea.wwce.automation.base.config;

/**
 * 
 * @author rdronamraju
 * @description This class contains the common data constants used by all applications
 */
public class BaseDataConstants{

	//public static final String PROJECT_ROOT_LOCATION="D:\\WWCE\\Automation\\Framework\\EA_Automation_Framework\\automation\\";

	private static String  projectRootPath = System.getProperty("user.dir");	
	public static final String PROJECT_ROOT_LOCATION =projectRootPath.substring(0, projectRootPath.lastIndexOf("\\")) + "\\";

	public static final String CHROME_DRIVER_PATH ="base\\src\\main\\resources\\drivers\\chromedriver.exe";
	public static final String GECKO_DRIVER_PATH ="base\\src\\main\\resources\\drivers\\geckodriver.exe";
	public static final String IEXPLORER_DRIVER_PATH ="base\\src\\main\\resources\\drivers\\IEDriverServer.exe";
	public static final String EDGE_DRIVER_PATH ="base\\src\\main\\resources\\drivers\\MicrosoftWebDriver.exe";
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
	
	//TestRail Configuration
	public static final String TESTRAIL_CONNECTION_URL = "https://e2qa.testrail.io";
	public static final String TESTRAIL_USERNAME = "";
	public static final String TESTRAIL_PASSWORD = "";
	public static final String API_NAME_SEPERATOR = "@";
	public static final String API_NAME_SEPERATOR_KEY= "API_NAME_SEPERATOR";

}