package com.shell.markethub.base.util.jsonhandlers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;

/**
 * 
 * @author n.kumar8@shell.com
 * @description Common JSON utilities needed to process JSON file
 *
 */
public class JsonFileProcessor {

	String fileOutput = "";
	String fileLocation = "";
	private static final Logger logger = Logger.getLogger(JsonFileProcessor.class);

	public JsonFileProcessor() {

	}

	public JsonFileProcessor(String filePath) {
		this.fileLocation = filePath;
		this.readJSONFileAsString(this.fileLocation);
	}

	public String getFileOutput() {
		return fileOutput;
	}

	public void setFileOutput(String fileOutput) {
		this.fileOutput = fileOutput;
	}

	/**
	 * Reads the JSON file content and returns as String
	 * @Param : filepath
	 */
	public void readJSONFileAsString(String filePath) {
		StringBuilder builder = new StringBuilder();
		try {
			String temp = "";
			// BufferedReader reader = new BufferedReader(new FileReader(new
			// File(filePath)));
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF8"));
			String line = reader.readLine();
			while (line != null) {
				builder.append(line);
				line = reader.readLine();
			}
			reader.close();
			setFileOutput(builder.toString());

		} catch (IOException e) {
			logger.error("Error processing JSON file located at :" + filePath);
		}
	}

	public boolean isArray(String jsonContent) {
		JsonParser parser = new JsonParser();
		if (parser.parse(jsonContent) instanceof JsonArray) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param keyParam
	 * @return Method returns file content as JSON object.
	 */
	public JsonObject getContentAsJsonObject(String jsonContent) {
		JsonObject result = new JsonObject();
		JsonParser parser = new JsonParser();
		if (!isArray(jsonContent))
			result = parser.parse(jsonContent).getAsJsonObject();
		return result;
	}

	/**
	 * 
	 * @param keyParam
	 * @return Method returns file content as JSON array if it is an array
	 */
	public JsonArray getContentAsJsonArray(String jsonContent) {
		JsonArray result = new JsonArray();
		JsonParser parser = new JsonParser();
		try {
			if (isArray(jsonContent))
				result = parser.parse(jsonContent).getAsJsonArray();
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * Method converts the JSON object to Map and returns it.
	 * 
	 * @param json object
	 * @return
	 */
	public Map<String, Object> getContentAsMap(String jsonContent) {
		JsonObject jsonObj = new JsonObject();
		JsonParser parser = new JsonParser();
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (!isArray(jsonContent)) {
				jsonObj = parser.parse(jsonContent).getAsJsonObject();
				for (Map.Entry<String, JsonElement> keyPair : jsonObj.entrySet()) {
					if (!result.containsKey(keyPair.getKey())) {
						JsonElement defaultValue = keyPair.getValue();
						Object temp;
						if (!defaultValue.isJsonNull()) {
							try {
								temp = Long.parseLong(keyPair.getValue().toString().replace("\"", "").trim());
							} catch (NumberFormatException e) {
								try {
									temp = Double.parseDouble(keyPair.getValue().toString().replace("\"", "").trim());
								} catch (NumberFormatException e1) {
									// If data type is neither long nor double,
									// process it as string
									temp = keyPair.getValue().toString().replace("\"", "").trim();
								}
							}
						} else {
							temp = null;
						}
						result.put(keyPair.getKey(), temp);
					}
				}
			}
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 
	 * @param keyParam
	 * @return Get the value based on the passed in Key
	 */
	public String getValueByKey(String keyParam) {
		JsonParser parser = new JsonParser();
		int resultSetIndex = 0;
		JsonObject resultObj = new JsonObject();
		String value = "";
		try {
			// If the Object is JSONArray get the value based on key from first
			// object in the array
			if (parser.parse(getFileOutput()) instanceof JsonArray) {
				JsonArray resultSet = parser.parse(getFileOutput()).getAsJsonArray();
				for (resultSetIndex = 0; resultSetIndex < resultSet.size(); resultSetIndex++) {
					resultObj = (JsonObject) resultSet.get(resultSetIndex);
					Set<Map.Entry<String, JsonElement>> keyValues = resultObj.entrySet();
					for (Map.Entry<String, JsonElement> key : keyValues) {
						if (key.getKey().equalsIgnoreCase(keyParam)) {
							value = key.getValue().toString().replace("\"", "").trim();
							break;
						}
					}
				}
			} else {
				// If the Object is JSONObject get the value based on key from
				// the object
				JsonObject resultSet = parser.parse(getFileOutput()).getAsJsonObject();
				Set<Map.Entry<String, JsonElement>> keyValues = resultSet.entrySet();
				for (Map.Entry<String, JsonElement> key : keyValues) {
					if (key.getKey().equalsIgnoreCase(keyParam)) {
						value = key.getValue().toString().replace("\"", "").trim();
						break;
					}
				}
			}

		} catch (Exception e) {

		}
		return value;
	}

	/**
	 * 
	 * Function returns the count of objects in the value associated with given key.
	 * If the value is not an array, default value returned will be zero.
	 */
	public int getCountOfChildObjects(String keyParam) {
		int count = 0;
		JsonParser parser = new JsonParser();
		JsonObject result = new JsonObject();
		// If JSON structure is array , it is ambiguous case and hence return 0
		try {
			if (!this.isArray(this.getFileOutput())) {
				result = (JsonObject) parser.parse(getFileOutput());
				Set<Map.Entry<String, JsonElement>> keyValues = result.entrySet();
				for (Map.Entry<String, JsonElement> key : keyValues) {
					if (key.getKey().equalsIgnoreCase(keyParam)) {
						// If the value associated with the passed in key is an
						// array, return the count
						// if (parser.parse(key.getValue().getAsString())
						// instanceof JsonArray) {
						if (parser.parse(key.getValue().toString()) instanceof JsonArray) {
							JsonArray resultArray = (JsonArray) parser.parse(key.getValue().toString());
							count = resultArray.size();
						} else
							count = 0;
					}
				}
			} else {
				count = 0;
			}
		} catch (Exception e) {

		}
		return count;
	}

	/**
	 * 
	 * @param parentKey
	 * @param childKey  This method can be used to find a value based on key and
	 *                  scan the value further and retrieve the child object
	 *                  matching with childKey value
	 */
	public JsonObject findObjectBykey(String parentKey, String childKey, String childValue) {
		int count = 0;
		JsonParser parser = new JsonParser();
		JsonObject input = new JsonObject();
		JsonObject result = new JsonObject();
		// If JSON structure is array , it is ambiguous case and hence return 0
		try {
			if (!this.isArray(this.getFileOutput())) {
				input = (JsonObject) parser.parse(getFileOutput());
				Set<Map.Entry<String, JsonElement>> keyValues = input.entrySet();
				for (Map.Entry<String, JsonElement> key : keyValues) {
					if (key.getKey().equalsIgnoreCase(parentKey)) {
						// If the key exists in the parent object, retrieve the
						// value to scan further
						if (parser.parse(key.getValue().toString()) instanceof JsonArray) {
							JsonArray resultArray = (JsonArray) parser.parse(key.getValue().toString());
							int index = 0;
							// Scan through the array and compare if any of the
							// object has matching key and value

							for (index = 0; index < resultArray.size(); index++) {
								JsonObject obj = resultArray.get(index).getAsJsonObject();
								Set<Map.Entry<String, JsonElement>> childKeyPairs = obj.entrySet();
								for (Map.Entry<String, JsonElement> childPair : childKeyPairs) {
									if (childPair.getKey().equalsIgnoreCase(childKey)
											&& childPair.getValue().toString().contentEquals(childValue)) {
										result = obj;
										break;
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {

		}
		return result;
	}

	public List<String> convertJsonArraytoList(JsonArray jsonArray) {
		int index = 0;
		List<String> result = new ArrayList<String>();
		try {
			for (index = 0; index < jsonArray.size(); index++) {
				result.add(jsonArray.get(index).toString());
			}
		} catch (Exception e) {

		}
		return result;
	}

	public HashMap<String, Integer> convertJsonArraytoMap(JsonArray jsonArray) {
		int index = 0;
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		try {
			for (index = 0; index < jsonArray.size(); index++) {
				String key = jsonArray.get(index).toString().replace("\"", "").trim();
				if (!result.containsKey(key)) {
					result.put(key, 1);
				}
			}
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * Merge two HashMaps and return the consolidated HashMap
	 */
	public HashMap<String, Object> mergeMaps(Map<String, Object> parent, Map<String, Object> subset) {

		HashMap<String, Object> master = new HashMap<String, Object>();

		ArrayList<String> keys = new ArrayList<String>(parent.keySet());

		for (int i = 0; i < keys.size(); i++) {
			String keyName = keys.get(i).toString();
			if (!master.containsKey(keyName))
				master.put(keyName, parent.get(keyName));
		}
		keys = new ArrayList<String>(subset.keySet());
		for (int i = 0; i < keys.size(); i++) {
			String keyName = keys.get(i).toString();
			if (!master.containsKey(keyName))
				master.put(keyName, subset.get(keyName));
		}
		return master;
	}

	/**
	 * Convert HashMap to JSONObject
	 * 
	 */
	public JsonObject convertMapToJsonObject(Map<String, Object> map) {
		JsonObject target = new JsonObject();
		ArrayList<String> keys = new ArrayList<String>(map.keySet());
		for (int i = 0; i < keys.size(); i++) {
			String keyName = keys.get(i).toString();
			// target.add(keyName, parser.parse(map.get(keyName).toString()));
			target.addProperty(keyName, map.get(keyName).toString().trim());
		}
		return target;
	}

	/**
	 * Returns the value from json which satisfies the path provided
	 * @param jsonPath: The path from which the value has to be extracted
	 * @param jsonContent: The json content as string from which the value has to be extracted
	 * @return the target value as object
	 */
	public Object getTargetObjectFromJson(String jsonPath, String jsonContent) {
		Object targetObjectFromJson = null;
		try {
			DocumentContext jsonContext = JsonPath.parse(jsonContent);
			targetObjectFromJson = jsonContext.read(jsonPath);
		}catch(Exception e) {
			logger.error("Failed to parse/read json passed!"+e.getMessage());
		}
		return targetObjectFromJson;
	}

	/**
	 * Returns the value from json which satisfies the path provided
	 * @param jsonPath: The path from which the value has to be extracted
	 * @param jsonContent: The json content as JsonObject from which the value has to be extracted
	 * @return the target value as object
	 */
	public Object getTargetObjectFromJson(String jsonPath, JsonObject jsonContent) {
		Object targetObjectFromJson = null;
		try {
			DocumentContext jsonContext = JsonPath.parse(jsonContent);
			targetObjectFromJson = jsonContext.read(jsonPath);
		}catch(Exception e) {
			logger.error("Failed to parse/read json passed!"+e.getMessage());
		}
		return targetObjectFromJson;
	}
	/**
	 * This method extracts a field from source json and places that value in the target json which satisfies the xpath provided
	 * @param sourceJsonPath : The path from which the value has to be extracted
	 * @param sourceJsonContent : The json content from which the value has to be extracted
	 * @param targetJsonPath : The path to which the value has to be placed
	 * @param targetJsonContent : The json content to which the value has to be placed
	 * @return the resulting json after replacing the content 
	 */
	public String substituteFieldFromSourceJsonToTargetJson(String sourceJsonPath, String sourceJsonContent,
			String targetJsonPath, String targetJsonContent) {
		Configuration jsonConfig = Configuration.builder()
				.options(Option.DEFAULT_PATH_LEAF_TO_NULL, Option.SUPPRESS_EXCEPTIONS)
				.build();
		DocumentContext sourceJsonContext = JsonPath.parse(sourceJsonContent,jsonConfig);
		Object sourceObjectFromJson = sourceJsonContext.read(sourceJsonPath);

		DocumentContext targetJsonContext = JsonPath.parse(targetJsonContent,jsonConfig);
		targetJsonContent= targetJsonContext.set(targetJsonPath, sourceObjectFromJson).jsonString();
		return targetJsonContent;
	}


	/**
	 * This method extracts fields from source json and places that values in the target json which satisfies the xpaths provided
	 * @param sourceAndTargetJsonPaths: Key will be the sourcepath and value will be the targetpath
	 * @param sourceJsonContent : The json content from which the value has to be extracted
	 * @param targetJsonContent : The json content to which the value has to be placed
	 * @return the resulting json after replacing the content 
	 */
	public String substituteFieldsFromSourceJsonToTargetJson(HashMap<String,String> sourceAndTargetJsonPaths,String sourceJsonContent,
			String targetJsonContent) {
		Configuration jsonConfig = Configuration.builder()
				.options(Option.DEFAULT_PATH_LEAF_TO_NULL, Option.SUPPRESS_EXCEPTIONS)
				.build();
		DocumentContext sourceJsonContext = JsonPath.parse(sourceJsonContent,jsonConfig);
		for (Map.Entry<String,String> entry : sourceAndTargetJsonPaths.entrySet())  {
			String sourceJsonPath = entry.getKey();
			String targetJsonPath = entry.getValue();
			Object sourceObjectFromJson = sourceJsonContext.read(sourceJsonPath);
			DocumentContext targetJsonContext = JsonPath.parse(targetJsonContent,jsonConfig);
			targetJsonContent= targetJsonContext.set(targetJsonPath, sourceObjectFromJson).toString();
		}
		return targetJsonContent;
	}

	/**
	public static void main(String[] args) {
		JsonFileProcessor jfpSource=new JsonFileProcessor("C:\\Users\\svenkatram\\git\\automationframework\\sovereign\\src\\test\\resources\\apiPayLoads\\REST\\assertions\\QAPC\\ACCOUNT_DETAILS_ASSERTIONS.json");		
		JsonFileProcessor jfpTarget=new JsonFileProcessor("C:\\Users\\svenkatram\\git\\automationframework\\sovereign\\src\\test\\resources\\apiPayLoads\\REST\\assertions\\QAPC\\ACCOUNT_DETAILS_ASSERTIONS.json");

		String source=jfpSource.getFileOutput();
		String target = jfpTarget.getFileOutput();

		String substituteFieldFromSourceJsonToTargetJson = jfpTarget.substituteFieldFromSourceJsonToTargetJson("$.43486[0].userId",source,"$.43487[0].status",target);
		//Long author=(Long) jfpSource.getTargetObjectFromJson( "$.43486[0].userId",jfpSource.getFileOutput());
		System.out.println(substituteFieldFromSourceJsonToTargetJson);
	}*/

}
