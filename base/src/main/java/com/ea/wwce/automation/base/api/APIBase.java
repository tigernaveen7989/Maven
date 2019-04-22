package com.ea.wwce.automation.base.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.ea.wwce.automation.base.config.BaseDataConstants;
import com.ea.wwce.automation.base.util.jsonhandlers.JsonFileProcessor;
import com.ea.wwce.automation.base.util.jsonhandlers.JsonMerger;
import com.ea.wwce.automation.base.util.jsonhandlers.JsonMerger.ConflictStrategy;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.qameta.allure.Step;

/**
 * 
 * @author rdronamraju
 * @description This class captures the API specific information from test,does the API call and validate the response
 *
 */

public class APIBase {
	
	String resultFilePath="";
	RESTParser restParser;
	private static final Logger logger = Logger.getLogger(APIBase.class);
	protected String apiURL;
	protected String requestType;
	protected String callType;
	protected String inputFileLocation;
	protected String assertionFileLocation;
	protected String responseFileLocation;
	protected String responseFileName;
	protected JsonObject uriParam;
	
	protected JsonObject headers;
	protected JsonObject inputParam;
	protected JsonObject postParam;	
	protected String authToken;
	protected String environment;
	protected String baseURL;
	
	int responseCode;
	String responseData;
	
	public APIBase(String url,String requestType,String callType,String inputFileLocation,String 
			responseFileLocation,String responseFileName,String environment,String authToken){
		this.callType = callType;
		this.inputFileLocation = inputFileLocation;
		this.responseFileLocation = responseFileLocation;
		this.assertionFileLocation = assertionFileLocation;
		this.requestType = requestType;
		this.callType = callType;
		this.environment = environment;
		this.baseURL=url;
		this.authToken = authToken;
		this.responseFileName = responseFileName;
		
		//09/13-Ravi : Payload and assertion loading will be based on Test case id rather than environment.
		
		//Replace URI values and append the parameters
		//String params=this.readParamFromJSON(inputFileLocation,environment);
				
		/*this.apiURL = this.replaceURIParam(url) + "?" + params;
		//this.authToken = this.getAuthorizationToken();
		restParser = new RESTParser(this.apiURL,requestType,callType,inputFileLocation,responseFileLocation,responseFileName,
				this.headers,this.postParam,this.authToken);*/
		
	}
		
	public APIBase(){
		
	}
	
	public JsonObject getUriParam() {
		return this.uriParam;
	}

	public void setUriParam(JsonObject uriParam) {
		this.uriParam = uriParam;
	}

	public JsonObject getHeaders() {
		return headers;
	}

	public void setHeaders(JsonObject headers) {
		this.headers = headers;
	}

	public JsonObject getInputParam() {
		return inputParam;
	}

	public void setInputParam(JsonObject inputParam) {
		this.inputParam = inputParam;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseData() {
		return responseData;
	}

	public void setResponseData(String responseData) {
		logger.info("Response from service:"+responseData);
		this.responseData = responseData;
	}
	
	public JsonObject getPostParam() {
		return postParam;
	}

	public void setPostParam(JsonObject postParam) {
		this.postParam = postParam;
	}
	
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getAuthToken() {
		return authToken;
	}
	public String readParamFromJSON(String fileName,String testId){
		return readParamFromJSON(fileName,testId,null);
	}
	/**
	 * Description - Reads the key value pairs in  JSON file and generates the parameter 
	 * string/URI needed
	 * for POST/GET calls
	 * @param fileName
	 * @return
	 */
	public String readParamFromJSON(String fileName,String testId,String apiId){
		String paramString="";
		JsonObject inputParam;
		try{
				logger.info("Reading header,URI and input parameters of API from the payload....");
				this.setPayLoad(fileName,testId,apiId);
				inputParam = this.getInputParam();
				
    			if(inputParam.keySet().size() > 0) {
    				for( Object key : inputParam.keySet()){
						String keyName = key.toString();
						String keyValue = inputParam.get(keyName).toString().replace("\"", "").trim();
						if(keyValue.length()>0){							
							paramString = paramString + keyName + "=" + keyValue + "&";	
						}
					}
    				paramString = paramString.substring(0, paramString.length()-1);		
    			}				    	
	    	}			
		catch(Exception e){
				paramString ="";
				logger.warn("Failed to process payload of API " + e.getMessage());
			}
		return paramString;		
	}
	
	/**
	 * @description Replace the URI param with the actual value
	 */
	public String replaceURIParam(String url){
		
		HashMap<String,String> uriMap = new HashMap<String,String>();
		JsonObject uriParamJSON = this.getUriParam();
		
		String delta = url;
		String result =url;
		
		//Look for the < & > tags in the URI and capture the URI keys. 
		do{					
			int start = delta.indexOf("<");
			int stop = delta.indexOf(">");
			
			if(start!=-1 || stop!=-1){
				String obj = delta.substring(start,stop+1);
				
				uriMap.put(obj, obj.replace("<", "").replace(">",""));
				delta = delta.substring(stop+1,delta.length());				
			}else{
				break;
			}
			
		}while(delta.length() >2);
		
		int index=0;
		ArrayList<String> keys = new ArrayList<String>();
		keys.addAll(0, uriMap.keySet());
		
		//Use these URI keys to read the URI param from payload file and return the processed string.
		while(index < keys.size()){
			String keyName = keys.get(index);
			String temp="";
			String keyValue = uriParamJSON.get(uriMap.get(keyName)).toString().replace("\"", "").trim();
			result=result.replace(keyName,keyValue);
			index++;
		}
		
		return result;
		
	}
	/**
	 * @description - This method hits the POST and GET end point and captures the response.
	 * 
	 */
	@Step("Validate the response of the end point")
	public boolean validateResponse() {		
		HashMap<String,HashMap<String,String>> masterData = new 
				HashMap<String,HashMap<String,String>>();
		HashMap<String,String> apiData = new HashMap<String,String>();
		Properties properties =	 new Properties();

		try{	
			logger.info("Validating the API ..." + "\n" + this.apiURL);
			//String propertyFilePath = System.getProperty("user.dir") + "\\resources\\Framework.properties";
			File file = new File(this.inputFileLocation);
			
			if(file.exists())
				properties.load(new FileInputStream(this.inputFileLocation));
		}catch(Exception e){
			
		}
	
		boolean output = false;
		if(callType.equalsIgnoreCase("GET")){									
			try{
				if(restParser.isResponseSuccessful() && this.apiURL.length() >0){	
					setResponseCode(restParser.getResponseCode());
					setResponseData(restParser.getGetResponseBody());
				    restParser.writeResponseToFile();	
				    output = true;
				}else{
					setResponseCode(restParser.getResponseCodeofAPI());
					setResponseData(restParser.getGetResponseBody());
					restParser.writeResponseToFile();		
					output = false;
				}
				
			}catch(Throwable e){	
				logger.warn("Failed to process GET call " + e.getMessage());
				output = false;	
			}

		}else if(callType.equalsIgnoreCase("POST") || callType.equalsIgnoreCase("PUT") || 
				callType.equalsIgnoreCase("DELETE")){

			try{						
				if(restParser.isResponseSuccessful() && this.apiURL.length() >0){
					setResponseCode(restParser.getResponseCodeofAPI());
					setResponseData(restParser.getResponseBody());				
					restParser.writeResponseToFile();
					output=true;
				}else{
					setResponseCode(restParser.getResponseCodeofAPI());
					setResponseData(restParser.getResponseBody());				
					restParser.writeResponseToFile();
					output=false;				
				}
			}catch(Exception e1){
				logger.warn("Failed to process " +  callType + " call " + e1.getMessage());
				output = false;								
			}
		}
		return output;

	}
	
	
	/**
	 * @description Capture the access token from Auth Token response
	 * 
	 */
	public static String getAuthorizationToken(String tokenBaseURL,String tokenFileLocation,String environment){
		
		String serviceURL;
		String keyName;
		String keyValue;
		JsonParser parser = new JsonParser();
		JsonObject masterTokenJSON = new JsonObject();
		JsonObject tokenJSON = new JsonObject();
		JsonObject headerJSON = new JsonObject();
		JsonObject inputParamJSON = new JsonObject();
		JsonObject postParamJSON = new JsonObject();
		JsonObject uriParamJSON = new JsonObject();
		JsonObject tokenResponseJSON = new JsonObject();
		String paramString= "";		
		String token="";
		String accessToken="";
		String expiryTime="";
		
		logger.info("Generating auth token ......");
			
		//String fileLocation = System.getProperty("user.dir") + filePath;	    	
    	File tokenFile = new File(tokenFileLocation);
		try{
			if(tokenFile.exists()){		
				masterTokenJSON = (JsonObject)parser.parse(new FileReader(tokenFile));
				tokenJSON=masterTokenJSON.get(environment).getAsJsonObject();
				
				//Load environment specific data
				
				
				//Read the URI param from the token file
				uriParamJSON = tokenJSON.get("uri_param").getAsJsonObject();
				
				//Read the headers from the token file
				headerJSON = tokenJSON.get("headers").getAsJsonObject();
				
				//Read the input param from the token file
				inputParamJSON=tokenJSON.get("input_param").getAsJsonObject();	 
				
				//Read the payload parameters applicable
				postParamJSON = tokenJSON.get("payload_param").getAsJsonObject();	
				
				
				//Append input param to base  service URL
				if(inputParamJSON.keySet().size() > 0) {
					for( Object key : inputParamJSON.keySet()){
						keyName = key.toString();
						keyValue = inputParamJSON.get(keyName).toString().replace("\"", "").trim();
						if(keyValue.length()>0){							
							paramString = paramString + keyName + "=" + keyValue + "&";	
						}
					}
					paramString = paramString.substring(0, paramString.length()-1);		
				}
				
				serviceURL = tokenBaseURL + "?" + paramString;
				
				//If the API contains headers , load them else load default properties.
					HttpPost tokenPost = new HttpPost(serviceURL);
					HttpClient client = HttpClientBuilder.create().build();
				
				//Load the headers of authorization token generator API
				if(!headerJSON.isJsonNull()){
					for( Object key : headerJSON.keySet()){
						keyName = key.toString();
						keyValue = headerJSON.get(keyName).toString().replace("\"", "").trim();			
						tokenPost.setHeader(keyName, keyValue);
					}
				}	
				String jsonString = postParamJSON.toString();    		    
			    StringEntity entity = new StringEntity(jsonString);
			    tokenPost.setEntity(entity);
		    	HttpResponse response = client.execute(tokenPost);	    
		    	token = EntityUtils.toString(response.getEntity());
		    	accessToken = parser.parse(token).getAsJsonObject().get("access_token").toString();
		    	expiryTime =  parser.parse(token).getAsJsonObject().get("expires_in").toString();
		    	logger.info(accessToken + " ---> expires in " + expiryTime);
			}
		}catch(Exception e){
			logger.warn("Failed to generate auth token " + e.getMessage());
			accessToken ="";
		}
		return accessToken + "," + expiryTime;		
	}
	
	/**
	 * Load the API headers, URI param based on testcase id.
	 */
	public void loadAPIInfomation(String testId){
		String params=this.readParamFromJSON(this.inputFileLocation,testId);
		this.apiURL = this.replaceURIParam(this.baseURL) + "?" + params;
		//this.authToken = this.getAuthorizationToken();
		this.restParser = new RESTParser(this.apiURL,this.requestType,this.callType,this.inputFileLocation,this.responseFileLocation,this.responseFileName,
				this.headers,this.postParam,this.authToken);
	}
	
	public void loadAPIInfomation(String testId,String apiID){
		String params=this.readParamFromJSON(this.inputFileLocation,testId,apiID);
		this.apiURL = this.replaceURIParam(this.baseURL) + "?" + params;
		//this.authToken = this.getAuthorizationToken();
		this.restParser = new RESTParser(this.apiURL,this.requestType,this.callType,this.inputFileLocation,this.responseFileLocation,this.responseFileName,
				this.headers,this.postParam,this.authToken);
	}
	

	 /**
	   * Method to process the payload
	 * @param apiId 
	   * 
	   * @return
	   */
	  public  void setPayLoad(String fileName,String testID, String apiId) {
	    JsonObject result = null;  
	   
	    try{
	    	    	
	    	logger.info("Fetching the Payload of the test " + testID);    	
	    	
	    	JsonFileProcessor fileLoader = new JsonFileProcessor(fileName);
	    	
	        String jsonContent = fileLoader.getFileOutput();
	              
	        if (!fileLoader.isArray(jsonContent)){
	          result = fileLoader.getContentAsJsonObject(jsonContent);
			          
				  if(result!=null&&!result.get( BaseDataConstants.GLOBAL_DATA_KEY_NAME).isJsonNull()){
					  if(!fileLoader.isArray(result.get( BaseDataConstants.GLOBAL_DATA_KEY_NAME).toString())){
						  
						  //Process global sections
						  setHeaders((JsonObject)result.get( BaseDataConstants.GLOBAL_DATA_KEY_NAME).getAsJsonObject().get(BaseDataConstants.HEADER_KEY_NAME));
						  
						  setUriParam((JsonObject)result.get( BaseDataConstants.GLOBAL_DATA_KEY_NAME).getAsJsonObject().get(BaseDataConstants.URI_PARAM_KEY_NAME));
						  
						  setInputParam((JsonObject)result.get( BaseDataConstants.GLOBAL_DATA_KEY_NAME).getAsJsonObject().get(BaseDataConstants.INPUT_PARAM_KEY_NAME));
						  
						//Read the payload parameters applicable for POST and PUT calls
						if(this.callType.equalsIgnoreCase("POST") || this.callType.equalsIgnoreCase("PUT")||this.callType.equalsIgnoreCase("DELETE")){
						  setPostParam((JsonObject)result.get( BaseDataConstants.GLOBAL_DATA_KEY_NAME).getAsJsonObject().get(BaseDataConstants.POST_PARAM_KEY_NAME));
						}	 						
				    	  
				      }
				  }    
			  
				  //If there are test specific entries, consolidate them with global entries
				  if(result!=null&&!result.get(testID).isJsonNull()){
					  if(!fileLoader.isArray(result.get(testID).toString())
							  &&result.get(testID).getAsJsonObject().get(BaseDataConstants.HEADER_KEY_NAME)!=null){	
						  JsonMerger.mergeJsonObjects(getHeaders(), ConflictStrategy.PREFER_SECOND_OBJ,
								  (JsonObject)result.get(testID).getAsJsonObject().get(BaseDataConstants.HEADER_KEY_NAME));
						  
						  //Merge URI Param
						  JsonMerger.mergeJsonObjects(getUriParam(), ConflictStrategy.PREFER_SECOND_OBJ, 
								  (JsonObject)result.get(testID).getAsJsonObject().get(BaseDataConstants.URI_PARAM_KEY_NAME));
						  
						  //Merge input Param
						  JsonMerger.mergeJsonObjects(getInputParam(), ConflictStrategy.PREFER_SECOND_OBJ,
								  (JsonObject)result.get(testID).getAsJsonObject().get(BaseDataConstants.INPUT_PARAM_KEY_NAME));
						  
						  //Merge Post Param
							//Read the payload parameters applicable for POST and PUT calls
			    			if(this.callType.equalsIgnoreCase("POST") || this.callType.equalsIgnoreCase("PUT")||this.callType.equalsIgnoreCase("DELETE")){
			    				  JsonMerger.mergeJsonObjects(getPostParam(), ConflictStrategy.PREFER_SECOND_OBJ,
			    						  (JsonObject)result.get(testID).getAsJsonObject().get(BaseDataConstants.POST_PARAM_KEY_NAME));
			    			}	
						
				      }else { //API Orchestration test data
							JsonObject apiData=(JsonObject)result.get(testID).getAsJsonObject().get(apiId);
							JsonMerger.mergeJsonObjects(getHeaders(), ConflictStrategy.PREFER_SECOND_OBJ,
									(JsonObject)apiData.get(BaseDataConstants.HEADER_KEY_NAME));
							
							
							//Merge URI Param
							JsonMerger.mergeJsonObjects(getUriParam(), ConflictStrategy.PREFER_SECOND_OBJ,
									(JsonObject)apiData.get(BaseDataConstants.URI_PARAM_KEY_NAME));

							//Merge input Param
							 JsonMerger.mergeJsonObjects(getInputParam(), ConflictStrategy.PREFER_SECOND_OBJ,
									 (JsonObject)apiData.get(BaseDataConstants.INPUT_PARAM_KEY_NAME));

							//Merge Post Param
							//Read the payload parameters applicable for POST and PUT calls
							if(this.callType.equalsIgnoreCase("POST") || this.callType.equalsIgnoreCase("PUT")||this.callType.equalsIgnoreCase("DELETE")){
								JsonMerger.mergeJsonObjects(getPostParam(), ConflictStrategy.PREFER_SECOND_OBJ,
										(JsonObject)apiData.get(BaseDataConstants.POST_PARAM_KEY_NAME));
							}	
						}
				  }  	         
		              
	        }
	    }catch(Exception e){
	    	logger.warn("Error getting payload" + e.getMessage());
	    }
	  }	
}
