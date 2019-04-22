package com.ea.wwce.automation.base.tests;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.IAssert;

import com.ea.wwce.automation.base.api.APIBase;
import com.ea.wwce.automation.base.config.BaseDataConstants;
import com.ea.wwce.automation.base.util.Assertions;
import com.ea.wwce.automation.base.util.jsonhandlers.JsonFileProcessor;
import com.ea.wwce.automation.base.util.testmanagement.TestRailClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.qameta.allure.Step;



/**
 * 
 * @author rdronamraju
 * @description This class the Base Test for all API based tests. It sets the macro environment needed for API processing.
 */
public class APIBaseTest {

	private static final Logger logger = Logger.getLogger(APIBaseTest.class);
	protected Assertions asserts;
	protected APIBase base;
	protected static Map<String,Object> apiConfigMap = new HashMap<String,Object>();
	JsonObject apiConfigJSON = new JsonObject();
	protected static String testEnvironment;
	protected static String planId;
	protected static String testType;
	protected TestRailClient testRailClient;
	protected HashMap<String, HashMap<String,Object>> apiOrchestrator;

	public HashMap<String, HashMap<String, Object>> getApiOrchestrator() {
		return apiOrchestrator;
	}

	public void setApiOrchestrator(HashMap<String, HashMap<String, Object>> apiOrchestrator) {
		this.apiOrchestrator = apiOrchestrator;
	}

	@BeforeSuite
	public void beforeSuite(ITestContext context){	    
		System.setProperty("PROJECT_ROOT_HOME", BaseDataConstants.PROJECT_ROOT_LOCATION);  
		PropertyConfigurator.configure(BaseDataConstants.PROJECT_ROOT_LOCATION + 
				BaseDataConstants.LOG_PROPERTIES_FILE);
		logger.info("Enabling logging...");
		logger.info("---------------------------------------------------");
		logger.info("Loading the before suite conditions......");



		//Load the API master config which contains the API specifics like URL, payload location etc
		JsonFileProcessor mainConfigProcessor = new JsonFileProcessor();
		mainConfigProcessor.readJSONFileAsString(context.getAttribute("API_MASTER_CONFIG_FILE_PATH").
				toString());	


		//Load the config as key value pair so that API config can read by its key

		//Load the API Config map.
		apiConfigMap = mainConfigProcessor.getContentAsMap(mainConfigProcessor.getFileOutput());
		context.setAttribute("MAIN_CONFIG_API_MAP", apiConfigMap);

		apiConfigJSON = mainConfigProcessor.getContentAsJsonObject(mainConfigProcessor.getFileOutput());
		context.setAttribute("MAIN_CONFIG_API_JSON", apiConfigJSON);

		/* 	System.setProperty("TESTNG_SUITE_NAME", context.getSuite().getName());	    	
	    	System.setProperty("TESTNG_NAME", context.getCurrentXmlTest().getName());*/

		//Create Test Rail Client connection object and pass the plan id
		testRailClient = new TestRailClient(BaseDataConstants.TESTRAIL_CONNECTION_URL,BaseDataConstants.
				TESTRAIL_USERNAME,BaseDataConstants.TESTRAIL_PASSWORD);

		context.setAttribute("testRailClientObject", testRailClient);

		logger.info("---------------------------------------------------");

	}       

	@BeforeTest
	@Parameters({"executionMode","platform","operatingSystem","browserName",
		"browserVersion","automationName","testEnvironment","deviceName","appPackage","appActivity",
		"applicationId","bundleId","appiumURL","planId","testType"})
	public void beforeTest(String executionMode,String platform,String operatingSystem,
			String browserName,String browserVersion,String automationName,String testEnvironment,
			String deviceName,String appPackage,String appActivity,String applicationId,String bundleId,String appiumURL,
			String planId,String testType,ITestContext context) {

		String configurationSource="";
		logger.info("---------------------------------------------------");
		logger.info("Loading the before test conditions......");


		if(System.getProperty("PICK_CONFIGURATION_FROM")==null){
			logger.info("No test configuration chosen,picking the test environment from TestNG.xml");
			configurationSource="TestNG";
		} else{    			
			configurationSource=System.getProperty("PICK_CONFIGURATION_FROM");
			logger.info("Test configuration will be picked from " + configurationSource);
		}

		//If the configuraton source is TestNG.xml
		if(configurationSource.contains("TestNG")){
			logger.info("Picked test environment " + testEnvironment + " from TestNG.xml");	    			
			APIBaseTest.testEnvironment = testEnvironment;

			APIBaseTest.planId = planId;   	
			context.setAttribute("test_plan_id", planId);

			APIBaseTest.testType = testType;
			context.setAttribute("testType", testType);


		}else if(configurationSource.contains("JENKINS")){

			//If the configuration source is Jenkins, then override TestNG.xml parameters
			APIBaseTest.testEnvironment = System.getProperty("TEST_ENVIRONMENT");	  
			logger.info("Test environment " + BaseTest.testEnvironment + " from JENKINS");	    

			APIBaseTest.planId = planId;
			context.setAttribute("test_plan_id", planId);

			APIBaseTest.testType = System.getProperty("TEST_TYPE");	
			context.setAttribute("testType", APIBaseTest.testType);	    			

		}   	  

		//Update the context with the environment chosen
		context.setAttribute("TEST_ENVIRONMENT",APIBaseTest.testEnvironment);


		logger.info("---------------------------------------------------");

	}

	@BeforeMethod
	public void beforeMethod(ITestContext context){
		//Based on the key passed from the test, capture the api information from payload file
		String authToken="";
		String apiKey = context.getAttribute("API_KEY").toString();
		try {
			authToken = context.getAttribute("authToken").toString();
		} catch(NullPointerException  e){
			logger.info("This API does not need auth token. Skipping the creation of auth token ... ");
		}
		if(!apiKey.contains(",")) {
			logger.info("loading the key of the API " + apiKey);
			SimpleDateFormat dateFormat  = new SimpleDateFormat("yyyyMMdd_hhmm");
			Date date = new Date();
			JsonObject apiJSON = new JsonObject();
			JsonObject apiMasterConfig = new JsonObject();

			apiMasterConfig = (JsonObject)context.getAttribute("MAIN_CONFIG_API_JSON");
			apiJSON = (JsonObject)apiMasterConfig.get(apiKey);	   	

			//Derive the param needed for processing API call,input file location , output file location
			//call type and url.

			//Derive the complete URL by appending Base service URI with the API URI
			String url = context.getAttribute("BASE_SERVICE_URL") + apiJSON.get("url").toString().replace("\"", "").trim();
			logger.info("URL of the API before appending parameters " + url);
			String requestType = apiJSON.get("service_type").toString().replace("\"", "").trim();
			String call_type = apiJSON.get("call_type").toString().replace("\"", "").trim();

			//Load the payload path
			String payload_path = context.getAttribute("API_INPUT_PAYLOAD_PATH").toString()+  APIBaseTest.testEnvironment+ "\\" +
					apiJSON.get("payload_name").toString().replace("\"", "").trim() ;
			context.setAttribute("payload_path", payload_path);
			logger.info("Getting payload from location " + payload_path);


			//Load the location of assertion file
			String assertion_path = context.getAttribute("API_ASSERTION_PATH").toString()+ APIBaseTest.testEnvironment+ "\\" +
					apiJSON.get("assertions").toString().replace("\"", "").trim();

			context.setAttribute("assertion_path", assertion_path);

			logger.info("Getting assertions from location " + assertion_path);

			String responseFileLocation = context.getAttribute("API_OUTPUT_RESPONSE_PATH").toString() + APIBaseTest.testEnvironment + "\\" +
					dateFormat.format(date);

			String responseFileName =responseFileLocation + "\\" + apiJSON.get("payload_name").toString().replace("\"", "").trim();

			logger.info("Output response of the file will be written to " + responseFileName);
			try {
				authToken = context.getAttribute("authToken").toString();
			} catch(NullPointerException  e){
				logger.info("This API does not need auth token. Skipping the creation of auth token ... ");
			}

			//Pass the current API info to REST Parser for further processing.
			//String testId = context.getAttribute("testcase_id").toString();
			base = new APIBase(url,requestType,call_type,payload_path,responseFileLocation,responseFileName,APIBaseTest.testEnvironment,authToken);
			asserts = new Assertions(); 	   	
		}else {//Orchestration of API calls
			logger.info("loading the keys for the APIs " + apiKey);
			String[] apiKeys = apiKey.split(",");
			apiOrchestrator=new HashMap<String, HashMap<String,Object>>();
			SimpleDateFormat dateFormat  = new SimpleDateFormat("yyyyMMdd_hhmm");
			Date date = new Date();
			JsonObject apiJSON = new JsonObject();
			JsonObject apiMasterConfig = new JsonObject();
			apiMasterConfig = (JsonObject)context.getAttribute("MAIN_CONFIG_API_JSON");
			for (int i = 0; i < apiKeys.length; i++) {
				String apiNameSeperator = null; //If same API has to be called in a test multiple times, pass api names with @1, @2 appended
				try {
					apiNameSeperator = (String) context.getAttribute(BaseDataConstants.API_NAME_SEPERATOR_KEY);
				}catch(Exception e) {
					logger.debug("No test level api name seperator found, Considering base seperator.");
				}
				if(apiNameSeperator==null)
					apiNameSeperator = BaseDataConstants.API_NAME_SEPERATOR;
				if(apiKeys[i].contains(apiNameSeperator)) {
					apiJSON = (JsonObject)apiMasterConfig.get(apiKeys[i].split(apiNameSeperator)[0]);
				}else {
					apiJSON = (JsonObject)apiMasterConfig.get(apiKeys[i]);
				}
				HashMap<String, Object> apiKeyMap = new HashMap<String,Object>();
				String url = context.getAttribute("BASE_SERVICE_URL") + apiJSON.get("url").toString().replace("\"", "").trim();
				logger.info("URL of the API before appending parameters " + url);
				String requestType = apiJSON.get("service_type").toString().replace("\"", "").trim();
				String call_type = apiJSON.get("call_type").toString().replace("\"", "").trim();
				//Load the payload path
				String payload_path = context.getAttribute("API_INPUT_PAYLOAD_PATH").toString()+  APIBaseTest.testEnvironment+ "\\" +
						apiJSON.get("payload_name").toString().replace("\"", "").trim() ;
				logger.info("Getting payload from location " + payload_path);
				apiKeyMap.put("PAYLOAD_PATH", payload_path);


				//Load the location of assertion file
				String assertion_path = context.getAttribute("API_ASSERTION_PATH").toString()+ APIBaseTest.testEnvironment+ "\\" +
						apiJSON.get("assertions").toString().replace("\"", "").trim();

				logger.info("Getting assertions from location " + assertion_path);
				apiKeyMap.put("ASSERTION_PATH", assertion_path);

				String responseFileLocation = context.getAttribute("API_OUTPUT_RESPONSE_PATH").toString() + APIBaseTest.testEnvironment + "\\" +
						dateFormat.format(date);

				String responseFileName =responseFileLocation + "\\" + apiJSON.get("payload_name").toString().replace("\"", "").trim();

				logger.info("Output response of the file will be written to " + responseFileName);
				apiKeyMap.put("RESPONSE_FILE_LOCATION", responseFileName);

				//Pass the current API info to REST Parser for further processing.
				//String testId = context.getAttribute("testcase_id").toString();

				APIBase apiBase = new APIBase(url,requestType,call_type,payload_path,responseFileLocation,responseFileName,APIBaseTest.testEnvironment,authToken);
				apiKeyMap.put("API_BASE", apiBase);
				apiOrchestrator.put(apiKeys[i], apiKeyMap);
				asserts = new Assertions(); 
			}
		}
	}


	@AfterMethod
	public void afterMethod(){

	}

	//Build reusable assert methods


	@Step("Verify Condition - {message}")
	protected void assertEquals(String actual, String expected, String message){
		asserts.assertEquals(actual, expected, message);
	}


	@Step("Verify Condition - {message}")
	protected void assertEquals(boolean actual, boolean expected, String message){
		asserts.assertEquals(actual, expected, message);
	}


	@Step("Verify Condition - {message}")
	protected void assertEquals(Object actual, Object expected, String message) {
		asserts.assertEquals(actual, expected, message);
	}


	@Step("Verify Condition - {message}")
	protected void assertNotEquals(String actual, String expected, String message) {
		asserts.assertNotEquals(actual, expected, message);
	}


	@Step("Verify Condition - {message}")
	protected void assertFalse(Boolean condition, String message) {
		asserts.assertFalse(condition, message);
	}


	@Step("Verify Condition - {message}")
	protected void assertTrue(Boolean condition, String message) {
		asserts.assertTrue(condition, message);
	}

	@Step("Verify that value of {key} is matching")
	protected void assertKeyValue(String key, String response, String expectedValue) {
		JsonParser parser = new JsonParser();
		JsonObject responseObj = new JsonObject();
		responseObj = (JsonObject)parser.parse(response);	
		logger.info("Verifying key value  "+key );

		if(!responseObj.get(key).isJsonNull() && !responseObj.get(key).isJsonObject() && !responseObj.get(key).isJsonArray() ){
			String keyValue = responseObj.get(key).getAsString();	        
			asserts.assertEquals(keyValue, expectedValue);
			logger.info("Successfully validated key value  "+expectedValue );
		}    	        
	}

	protected void doAssert(IAssert assertion){
		asserts.doAssert(assertion);;
	}

	protected void assertAll(){
		asserts.assertAll();
	}

}