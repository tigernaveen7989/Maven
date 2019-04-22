package com.ea.wwce.automation.base.util;
import java.util.HashMap;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

/**
 * 
 * @author rdronamraju
 * @description This class will be used to extract the capabilities from Jenkins or TestNG which will
 * be needed for the Driver object
 */
public class DriverCapabilities extends DesiredCapabilities {
	
	String platform;
	String operatingSystem;
	String browserName;
	String browserVersion;
	String deviceName;
	String automationName;
	String testEnvironment;
	String appPackage;
	String appActivity;
	String applicationId;
	String bundleId;	
	String appiumURL;
	HashMap<String,String> capabilitiesMap = new HashMap<String,String>();
	
	public DriverCapabilities(){
		
	}
	
	 private static final Logger logger = Logger.getLogger(DataProviders.class);
	
	/**
	 * 
	 * @param platform
	 * @param operatingSystem
	 * @param browserName
	 * @param browserVersion
	 * @param automationName
	 * @param testEnvironment
	 * @param deviceName
	 * @param appPackage
	 * @param appActivity
	 * @param applicationId
	 * @param bundleId
	 */
	public DriverCapabilities(String platform,String operatingSystem,String browserName,
			String browserVersion,String automationName,String testEnvironment,String deviceName,
			String appPackage,String appActivity,String applicationId,String bundleId,
			String appiumURL){
		try{
			
			logger.info("Capturing desired capabilities");
			
			this.automationName=automationName;
			capabilitiesMap.put("automationName", automationName);
			this.platform = platform;	
			capabilitiesMap.put("platform", platform);
			this.operatingSystem=operatingSystem;
			capabilitiesMap.put("operatingSystem", operatingSystem);
			this.browserName = browserName;
			capabilitiesMap.put("browserName", browserName);
			this.browserVersion=browserVersion;
			capabilitiesMap.put("browserVersion", platform);
			this.testEnvironment=testEnvironment;
			capabilitiesMap.put("testEnvironment", testEnvironment);
			
			if(platform.toUpperCase().contains("MOBILE") && operatingSystem.toUpperCase().contains("ANDROID")){
				this.appPackage = appPackage;
				capabilitiesMap.put("appPackage", appPackage);
				this.appActivity = appActivity;
				capabilitiesMap.put("appActivity", appActivity);
				this.deviceName = deviceName;	
				capabilitiesMap.put("deviceName", deviceName);
				this.appiumURL =appiumURL;
				capabilitiesMap.put("appiumURL", appiumURL);
				
				
			}else if(platform.toUpperCase().contains("MOBILE") && operatingSystem.toUpperCase().contains("IOS")){
				this.deviceName = deviceName;
				capabilitiesMap.put("deviceName", deviceName);
				this.bundleId = bundleId;
				capabilitiesMap.put("bundleId", bundleId);
				this.appiumURL =appiumURL;
				capabilitiesMap.put("appiumURL", appiumURL);
			}	
		}catch(Exception e){
			logger.warn("Error capturing the desired capabilities" + e.getMessage());
		}
							
	}
	
	
	/**
	 * 
	 * @param platform
	 * @param platformVersion
	 * @param operatingSystem
	 * @param browserName
	 * @param browserVersion
	 * @param automationName
	 * @param deviceName
	 * @param appPackage
	 * @param appActivity
	 * @param applicationId
	 * @param bundleId
	 */
	public void setCapabilities(String platform,String operatingSystem,String browserName,String browserVersion,String automationName,
			String deviceName,String appPackage,String appActivity,String applicationId,String bundleId,String appiumURL){
		
		try{
			logger.info("Setting desired capabilities");
			
			this.automationName=automationName;
			capabilitiesMap.put("automationName", automationName);
			this.platform = platform;	
			capabilitiesMap.put("platform", platform);
			this.operatingSystem=operatingSystem;
			capabilitiesMap.put("operatingSystem", operatingSystem);
			this.browserName = browserName;
			capabilitiesMap.put("browserName", browserName);
			this.browserVersion=browserVersion;
			capabilitiesMap.put("browserVersion", platform);
			this.testEnvironment=testEnvironment;
			capabilitiesMap.put("testEnvironment", testEnvironment);
			
			
			if(platform.toUpperCase().contains("MOBILE") && operatingSystem.toUpperCase().contains("ANDROID")){
				this.appPackage = appPackage;
				capabilitiesMap.put("appPackage", appPackage);
				this.appActivity = appActivity;
				capabilitiesMap.put("appActivity", appActivity);
				this.deviceName = deviceName;	
				capabilitiesMap.put("deviceName", deviceName);
				this.appiumURL =appiumURL;
				capabilitiesMap.put("appiumURL", appiumURL);
				
				
			}else if(platform.toUpperCase().contains("MOBILE") && operatingSystem.toUpperCase().contains("IOS")){
				this.deviceName = deviceName;
				capabilitiesMap.put("deviceName", deviceName);
				this.bundleId = bundleId;
				capabilitiesMap.put("bundleId", bundleId);
				this.appiumURL =appiumURL;
				capabilitiesMap.put("appiumURL", appiumURL);
			}
		}catch(Exception e){
			logger.warn("Error setting the desired capabilities" + e.getMessage());
		}
		
	}
	
	/**
	 * 	 * @return
	 */
	public DriverCapabilities getCapabilities(){
		return this;
	}
	
	/**
	 * 
	 */
	public String getCapabilityValue(String capabilityName){
		String capabilityValue="";
		try{
			capabilityValue=this.capabilitiesMap.get(capabilityName);
		}catch(Exception e){
			capabilityValue="";			
		}
		return capabilityValue;		
	}
	
	/**
	 * 
	 */
	public boolean doesCapabilityMatch(String capabilityName,String value){
		boolean match=false;
		try{
			match=this.capabilitiesMap.get(capabilityName).toString().toUpperCase().equalsIgnoreCase(value.toUpperCase());
		}catch(Exception e){
			match=false;			
		}
		return match;		
	}

	

}
