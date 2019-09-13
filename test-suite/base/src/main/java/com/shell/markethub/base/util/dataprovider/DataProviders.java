package com.shell.markethub.base.util.dataprovider;

import com.shell.markethub.base.enums.TestDataType;
import com.shell.markethub.base.util.config.BaseDataConstants;
import com.shell.markethub.base.util.jsonhandlers.JsonFileProcessor;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import edu.emory.mathcs.backport.java.util.Arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 
 * @author n.kumar8@shell.com
 * @description Test management utility class which extract the test data from JSON file based on test ID
 * and will be passed to test.
 *
 */
public final class DataProviders {

	static JsonFileProcessor fileLoader;
	private static final Logger logger = Logger.getLogger(DataProviders.class);
	private String filePath = "";
	private String apiId;

	public DataProviders(String testDataFilePath) {
		this.filePath = testDataFilePath;
	}

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	/**
	 * Method to read attribute data from external JSON file and pass on to the
	 * Attribute Resource object.
	 * 
	 * @return Map
	 */
	public Map<String, Object> getTestData(String testID, String... dataType) {
		JsonObject result = new JsonObject();
		int objCount = 0;
		int globalObj = 0;
		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, Object> globalData = new HashMap<String, Object>();
		JsonArray testObjects = new JsonArray();
		JsonArray globalObjects = new JsonArray();
		int index = 0;
		String globalKeyName = BaseDataConstants.GLOBAL_DATA_KEY_NAME;
		String type = TestDataType.ASSERTIONS.toString();
		try {

			// If the assertions are needed,set the data type as assertions
			try {
				type = dataType[0];
			} catch (ArrayIndexOutOfBoundsException e) {
				type = TestDataType.TEST_DATA.toString();
			}

			if (!type.equalsIgnoreCase(TestDataType.ASSERTIONS.toString())) {
				logger.info("Fetching the test data for the TestID " + testID);
			} else {
				logger.info("Fetching the assertions of the TestID " + testID);
			}

			fileLoader = new JsonFileProcessor(this.filePath);

			String jsonContent = fileLoader.getFileOutput();

			if (!fileLoader.isArray(jsonContent)) {
				result = fileLoader.getContentAsJsonObject(jsonContent);

				globalObj = fileLoader.getCountOfChildObjects(globalKeyName);
				objCount = fileLoader.getCountOfChildObjects(testID);

				// Get the count of global or common object data
				if (!type.equalsIgnoreCase(TestDataType.ASSERTIONS.toString())) {
					logger.info("Loading the global test data");
				} else {
					logger.info("Loading global assertions");
				}
				if (!result.get(globalKeyName).isJsonNull()) {
					if (fileLoader.isArray(result.get(globalKeyName).toString()) && globalObj == 1) {
						globalObjects = (JsonArray) result.get(globalKeyName);
						globalData = fileLoader.getContentAsMap(globalObjects.get(0).toString());
					}
				}

				// If the value associated with attributes key contains an array of attribute
				// objects to be created
				if (!type.equalsIgnoreCase("ASSERTIONS")) {
					logger.info("Loading test data configured specific to test");
				} else {
					logger.info("Loading assertions configured specific to test");
				}
				if (fileLoader.isArray(result.get(testID).toString()) && objCount == 1) {
					testObjects = (JsonArray) result.get(testID);
					testData = fileLoader.getContentAsMap(testObjects.get(0).toString());
					// logger.info("Succesfully captured test data for the test " + testID);
				} else { // Orchestration of API calls
					JsonObject asserts = (JsonObject) result.get(testID).getAsJsonObject().get(apiId);
					testData = fileLoader.getContentAsMap(asserts.toString());
				}

				// Append global data to test specific data.If global
				if (globalData.keySet().size() > 0) {
					Iterator keyIter = globalData.keySet().iterator();
					while (keyIter.hasNext()) {
						Object globalKey = keyIter.next();

						// Append global data to test data only if specific key does not exist in
						// testdata. Test specific data always take
						// precedence
						if (!testData.containsKey(globalKey)) {
							testData.put(globalKey.toString(), globalData.get(globalKey));
						}
					}
				}
			}
		} catch (Exception e) {
			logger.warn("Error getting test data" + e.getMessage());
		}
		return testData;
	}
  
	// Use JSON utility and return the value based on the key passed
	public String getParameter(String parameter) {
		String param = "";
		logger.info("Getting the parameter value " + parameter + "test data");
		try {
			param = fileLoader.getValueByKey(parameter);
		} catch (Exception e) {
			logger.warn("Failed to fetch the parameter." + e.getMessage());
			throw e;
		}
		return param;

	}
}
