package com.ea.wwce.automation.base.api;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

/**
 * 
 * @author rdronamraju
 * @description This class contains reusable utilties needed for REST based GET, POST , PUT and DELETE calls processing
 */

public class RESTParser {
	
	 private static final Logger logger = Logger.getLogger(RESTParser.class);
	 
	//Capture the header configuration
	static String CONNECTION_STATUS = "keep-alive";
	
	static String GET_REQUEST_TYPE="GET";
	static String GET_MEDIA_TYPE = "application/json";
	static String GET_CONTENT_TYPE= "application/x-www-form-urlencoded,application/json";
	static String GET_ACCEPT_LANGUAGE = "en-US,en;q=0.5";
	
	static String POST_REQUEST_TYPE="POST";
	static String POST_MEDIA_TYPE = "application/json";
	static String POST_CONTENT_TYPE= "application/json";
	static String POST_ACCEPT_LANGUAGE = "en-US,en;q=0.5";
	
	String endPointURL;
	String requestType;
	String callType;
	String fileLocation;
	String responseFileLocation;
	String responseFileName;
	JsonObject headers;
	HttpGet httpGetObj;
	HttpPost httpPostObj;
	HttpPut httpPutObj;
	HttpDelete httpDeleteObj;
	HttpResponse response;
	String responseBody="";
	String getResponseBody ="";
	String responseFilePath;
	JsonObject postParamObj;
	String authToken;
	URL url= null;
	int responseCode;
	
	public RESTParser(){
		
	}
	
	public RESTParser(String endPointURL,String requestType,String callType,String fileLocation,String 
			responseFileLocation,String responseFileName,JsonObject headers,JsonObject postParam,String authToken){
		
		this.endPointURL = endPointURL;
		this.requestType = requestType;
		this.callType= callType;
		this.fileLocation = fileLocation;
		this.responseFileLocation = responseFileLocation;
		this.responseFileName = responseFileName;
		this.headers = headers;	
		this.postParamObj = postParam;
		this.authToken = authToken;
		
		//this.httpConn = getHttpConnObj(endPointURL,headers);	
		if(callType.equalsIgnoreCase("GET")){
			this.httpGetObj = getHttpGetConnObj(endPointURL,headers);
		}else if(callType.equalsIgnoreCase("POST")){
			this.httpPostObj = getHttpPostConnObj(endPointURL,headers);
		}else if(callType.equalsIgnoreCase("PUT")){
			this.httpPutObj = getHttpPutConnObj(endPointURL,headers);
		}else if(callType.equalsIgnoreCase("DELETE")){
			this.httpDeleteObj = getHttpDeleteConnObj(endPointURL,headers);
		}
		
	}
		
	public JsonObject getPostParamObj() {
		return postParamObj;
	}

	public void setPostParamObj(JsonObject postParamObj) {
		this.postParamObj = postParamObj;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}


	public String getResponseBody() {
		return responseBody;
	}

	public void setReponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	public String getGetResponseBody() {
		return getResponseBody;
	}

	public void setGetResponseBody(String getResponseBody) {
		this.getResponseBody = getResponseBody;
	}
	
	
	/**
	 * @description Set up the HttpGet object and configure the headers if available in the payload file else configure
	 * the default headers
	 * @param urlValue
	 * @param headers
	 * @return
	 */
	public HttpGet getHttpGetConnObj(String urlValue,JsonObject headers){
		HttpGet httpConnObj =null;
		String keyName="";
		String keyValue="";
		try{		
			logger.info("Getting HTTP Get Connection Object");
			url = new URL(urlValue);
			httpConnObj = new HttpGet(this.endPointURL);
			
			logger.info("Request type is " + this.requestType +",Setting"
					+ " GET connection properites");
			
			//If the API contains headers , load them else load default properties.
				
			if(!headers.isJsonNull()){
				for( Object key : headers.keySet()){
					keyName = key.toString().replace("\"", "").trim();					
					if(!keyName.equalsIgnoreCase("Authorization")){
						keyValue = headers.get(keyName).toString().replace("\"", "").trim();
					}else{
						//if the header name is authorization,read the token value captured from token generator earlier
						keyValue = this.authToken;
					}
					httpConnObj.setHeader(keyName, keyValue);
				}
			}else{				
				httpConnObj.setHeader("Accept", GET_MEDIA_TYPE);
				httpConnObj.setHeader("Content-Type", GET_CONTENT_TYPE);
			}		
		}catch(MalformedURLException e){
			logger.warn("Failed to create HTTP connection object due to" + e.getMessage());
		}catch(IOException e){
			logger.warn("Failed to create HTTP connection object due to" + e.getMessage());
		}
		return httpConnObj;		
	}
	
	
	/**
	 * @description Set up the HttpPost object and configure the headers if available in the payload file else configure
	 * the default headers
	 * @param urlValue
	 * @param headers
	 * @return
	 */
	public HttpPost getHttpPostConnObj(String urlValue,JsonObject headers){
		HttpPost httpConnObj =null;
		String keyName="";
		String keyValue="";
		try{		
			logger.info("Getting HTTP POST Connection Object");
			url = new URL(urlValue);
			httpConnObj = new HttpPost(this.endPointURL);
			
			logger.info("Request type is " + this.requestType +",Setting"
					+ " POST connection properites");
			
			//If the API contains headers , load them else load default properties.
				
			if(!headers.isJsonNull()){
				for( Object key : headers.keySet()){
					keyName = key.toString();
					if(!keyName.equalsIgnoreCase("Authorization")){
						keyValue = headers.get(keyName).toString().replace("\"", "").trim();
					}else{
						keyValue = this.authToken;
					}
					httpConnObj.setHeader(keyName, keyValue);
				}
			}else{				
				httpConnObj.setHeader("Accept", POST_MEDIA_TYPE);
				httpConnObj.setHeader("Content-Type", POST_CONTENT_TYPE);
			}		
		}catch(MalformedURLException e){
			logger.warn("Failed to create HTTP POST connection object due to" + e.getMessage());
		}catch(IOException e){
			logger.warn("Failed to create HTTP POST connection object due to" + e.getMessage());
		}
		return httpConnObj;		
	}
	
	/**
	 * @description Set up the HttpPut object and configure the headers if available in the payload 
	 * file else configure the default headers
	 * @param urlValue
	 * @param headers
	 * @return
	 */
	public HttpPut getHttpPutConnObj(String urlValue,JsonObject headers){
		HttpPut httpConnObj =null;
		String keyName="";
		String keyValue="";
		try{		
			logger.info("Getting HTTP PUT Connection Object");
			url = new URL(urlValue);
			httpConnObj = new HttpPut(this.endPointURL);
			
			logger.info("Request type is " + this.requestType +",Setting"
					+ " PUT connection properites");
			
			//If the API contains headers , load them else load default properties.
				
			if(!headers.isJsonNull()){
				for( Object key : headers.keySet()){
					keyName = key.toString();	
					if(!keyName.equalsIgnoreCase("Authorization")){
						keyValue = headers.get(keyName).toString().replace("\"", "").trim();
					}else{
						keyValue = this.authToken;
					}
					httpConnObj.setHeader(keyName, keyValue);
				}
			}else{				
				httpConnObj.setHeader("Accept", POST_MEDIA_TYPE);
				httpConnObj.setHeader("Content-Type", POST_CONTENT_TYPE);
			}		
		}catch(MalformedURLException e){
			logger.warn("Failed to create HTTP PUT connection object due to" + e.getMessage());
		}catch(IOException e){
			logger.warn("Failed to create HTTP PUT connection object due to" + e.getMessage());
		}
		return httpConnObj;		
	}
	
	
	/**
	 * @description Set up the HttpDelete object and configure the headers if available in the payload 
	 * file else configure the default headers
	 * @param urlValue
	 * @param headers
	 * @return
	 */
	public HttpDelete getHttpDeleteConnObj(String urlValue,JsonObject headers){
		HttpDelete httpConnObj =null;
		String keyName="";
		String keyValue="";
		try{		
			logger.info("Getting HTTP DELETE Connection Object");
			url = new URL(urlValue);
			httpConnObj = new HttpDelete(this.endPointURL);
			
			logger.info("Request type is " + this.requestType +",Setting"
					+ " DELETE connection properites");
			
			//If the API contains headers , load them else load default properties.
				
			if(!headers.isJsonNull()){
				for( Object key : headers.keySet()){
					keyName = key.toString();	
					if(!keyName.equalsIgnoreCase("Authorization")){
						keyValue = headers.get(keyName).toString().replace("\"", "").trim();
					}else{
						keyValue = this.authToken;
					}
					httpConnObj.setHeader(keyName, keyValue);
				}
			}else{				
				httpConnObj.setHeader("Accept", POST_MEDIA_TYPE);
				httpConnObj.setHeader("Content-Type", POST_CONTENT_TYPE);
			}		
		}catch(MalformedURLException e){
			logger.warn("Failed to create HTTP DELETE connection object due to" + e.getMessage());
		}catch(IOException e){
			logger.warn("Failed to create HTTP DELETE connection object due to" + e.getMessage());
		}
		return httpConnObj;		
	}
	
    /**
     * @description : Based on the call type, get the HttpGet or HttpPost object, do the call and capture the response code and body
     * @param endPointURL
     * @param requestType
     * @param fileLocation
     * @return
     */
    public int getResponseCodeofAPI(){
    	if(responseCode>0) {
    		return responseCode;
    	}
    	try{
    		logger.info("Getting response code");
    		HttpClient client = HttpClientBuilder.create().build();

    	   if(callType.equalsIgnoreCase("GET")){    
    		   	HttpGet get =this.httpGetObj;
    	    	response = client.execute(get);
    	    	getResponseBody = new BasicResponseHandler().handleResponse(response);
    	    	setGetResponseBody(getResponseBody);
    	    	
    	   }else if(callType.equalsIgnoreCase("POST")){    		   
    		    HttpPost post =this.httpPostObj;
    		    String jsonString = this.getPostParamObj().toString(); 
    		    logger.info("Request body to service:"+jsonString);
    		    StringEntity entity = new StringEntity(jsonString);
    		    post.setEntity(entity);
    	    	response = client.execute(post);
    	    	setReponseBody(EntityUtils.toString(response.getEntity()));
    	
    	   } else if(callType.equalsIgnoreCase("PUT")){    		   
	   		    HttpPut put = this.httpPutObj; 
	   		    //Convert payload param to String and pass it to PUT object
	   		    String jsonString = this.getPostParamObj().toString();    		    
	   		    StringEntity entity = new StringEntity(jsonString);
	   		    put.setEntity(entity);
	   	    	response = client.execute(put);
	   	    	setReponseBody(EntityUtils.toString(response.getEntity()));
    	 
    	   }  else if(callType.equalsIgnoreCase("DELETE")){    		   
	   		    HttpDelete delete = this.httpDeleteObj; 	   		    
	   		    //Convert payload param to String and pass it to DELETE object
	   		    String jsonString = this.getPostParamObj().toString();    		    
	   		    StringEntity entity = new StringEntity(jsonString);
	   		    response = client.execute(delete);
	   	    	setReponseBody(EntityUtils.toString(response.getEntity()));
	   	    	
    	   } 
    	   	int responseCode = response.getStatusLine().getStatusCode();
        	setResponseCode(responseCode);
        	logger.info("Response code : " + responseCode);
        	
    	}catch(Exception e){
    		logger.warn("Exception while getting response code " + e.getMessage());
    		setResponseCode(response.getStatusLine().getStatusCode());
    	}
    	return responseCode;
  	} 


	/**
	 * @description : Method to check if  call is successful
	 * @param endPointURL
	 * @param requestType
	 * @return
	 */
    public boolean isResponseSuccessful(){
    	if(response==null) {
    		if(getResponseCodeofAPI()== 200){
      			return true;
      		}else{
      			return false;
      		}	
    	}
    	return response.getStatusLine().getStatusCode()==200?true:false;
	}           
  
    
   /**
    * @description : Method to write the response to an output file
    */
    public  void writeResponseToFile(){
        String folderPath = null;
        String filePath = null;
        FileWriter fileWriter = null;
        BufferedWriter bufferWriter =  null;
        String timeStamp = null;
        try{       	

    		logger.info("Writing output response to the file "  + responseFileName);
    		
    		//Create a new folder for the response files based on the timestamp    		
    		File directory = new File(responseFileLocation);
    		  if (!directory.exists()) {
    			  directory.mkdir();
              }
    		   
    		//Create the response file and write the response to it. 
    		File file = new File(responseFileName);             
             if (!file.exists()) {
                 file.createNewFile();
             }
             
             fileWriter = new FileWriter(file);
             bufferWriter = new BufferedWriter(fileWriter);
             // Write in file
             if(callType.equalsIgnoreCase("GET")){
            	 bufferWriter.write(this.getGetResponseBody().toString()); 
             }else if(callType.equalsIgnoreCase("POST") || callType.equalsIgnoreCase("PUT") ||
            		 callType.equalsIgnoreCase("DELETE")){
            	 bufferWriter.write(this.getResponseBody().toString()); 
             }
            
             // Close connection
             bufferWriter.close();
             fileWriter.close();
             logger.info("---------------------------------------------------");
         }
         catch(Exception e){
        	 logger.warn("Failed to write output response to the file" + e.getMessage());
         }
	
     }
    


}