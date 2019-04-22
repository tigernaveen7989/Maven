package com.ea.wwce.automation.base.util;


import org.openqa.selenium.Platform;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.service.DriverService;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariDriverService;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.MobileElement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;
import com.ea.wwce.automation.base.util.DriverCapabilities;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.base.config.BaseDataConstants;
import com.ea.wwce.automation.base.tests.BaseTest;

/**
 * 
 * @author rdronamraju
 * @description This class will be used to create Driver object from capabilities
 */

public class Driver {

    String browserName;
    static ThreadLocal<WebDriver> _driver=new ThreadLocal<WebDriver>();
    WebDriver driver;
    String executionMode;
    String gridUrl;
    String platform;
    String test;
    String chromeDriverPath = "\\src\\main\\resources\\chromeDriver.exe";
    String geckoDriverPath = "\\src\\main\\resources\\geckodriver.exe";
    String iExplorerDriverPath = "\\src\\main\\resources\\IEDriverServer.exe";
    String edgeDriverPath ="\\src\\main\\resources\\MicrosoftWebDriver.exe";
    String safariDriverPath ="\\src\\main\\resources\\SafariDriver.exe";
    String operatingSystem;
    String applicationId;
    String deviceName;
    String deviceHost;
    DriverCapabilities driverCap;
    private DriverService driverService;
    protected DriverServiceManager driverServiceManager;
    private URL driverServiceURL;
  
    
    private static final Logger logger = Logger.getLogger(Driver.class);
 
    public Driver() {
    	driverCap = new DriverCapabilities();
    	driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
	}
    
    public Driver(String executionMode,String platform,String operatingSystem,
    		String browserName,String browserVersion,String automationName,String testEnvironment,
    		String deviceName,String appPackage,String appActivity,String applicationId,
    		String bundleId,String appiumURL){
    	driverCap = new DriverCapabilities();
    	driverCap.setCapabilities(platform,operatingSystem, browserName,
    			browserVersion, automationName, deviceName, appPackage, appActivity,applicationId, bundleId,appiumURL);
    	
    	//Capture the custom properties at driver and standard desired capabilities will be part of Driver Capabilities object
    	this.executionMode = executionMode;
    	this.gridUrl = gridUrl;    
    	this.operatingSystem  = operatingSystem;
    	this.platform = platform;
    	this.browserName = browserName;
    	this.driverService = driverService;    	
  		driverServiceManager = new DriverServiceManager(operatingSystem,platform,browserName);
		
    }   
    
    public WebDriver getThreadSafeDriver(){
    	return _driver.get();
    }   
    
    
    // Use this method to create a new browser instance from the driver server
    public void setDriver(URL serviceURL) {	   
    	
    		//If the execution mode is remote
    	  if(executionMode.toUpperCase().equalsIgnoreCase("REMOTE") && gridUrl.contains("wd/hub")) {         		
	  			try {
	  				driver = new RemoteWebDriver(new URL(gridUrl), this.driverCap.getCapabilities());
	  			} catch (MalformedURLException e) {
	  				e.printStackTrace();
	  			}  		
	  		
	  		//if the execution mode is local
          } else if( operatingSystem.equalsIgnoreCase("WINDOWS") && platform.equalsIgnoreCase("WEB")) {                         
                  driver = new RemoteWebDriver(serviceURL,this.driverCap.getCapabilities());
         }/*else if(operatingSystem.toUpperCase().equalsIgnoreCase("ANDROID") && executionMode.toUpperCase().equalsIgnoreCase("MOBILE")) { 
        			String appiumURL = this.driverCap.appiumURL;
		        	driver = new AndroidDriver<MobileElement>(new URL(appiumURL),this.driverCap.getCapabilities());
         }else if(operatingSystem.toUpperCase().equalsIgnoreCase("IOS") && executionMode.toUpperCase().equalsIgnoreCase("MOBILE")  ) {        
		        	String appiumURL = this.driverCap.appiumURL;
		        	driver = new IOSDriver<MobileElement>(new URL(appiumURL),this.driverCap.getCapabilities());
         }  */     	     
      
   }

    public WebDriver getDriver() {
        return driver;
    }    

    //Use this method to return driver service manager object
    public DriverServiceManager getDriverServiceManager(){
    	return this.driverServiceManager;
    }

    
    //Set the Driver Service URL
    public void setDriverServiceURL(){
    	try{
    		logger.info("------------------------------------------------------------------------");
        	logger.info("Starting Driver Service .......");
        	logger.info("------------------------------------------------------------------------");
        	if(browserName.equalsIgnoreCase("CHROME")){
    			driverServiceURL =driverServiceManager.startChromeDriverService();
    		}else if(browserName.equalsIgnoreCase("FIREFOX")){
    			driverServiceURL =driverServiceManager.startGeckoDriverService();
    		}else if(browserName.equalsIgnoreCase("EDGE")){
    			driverServiceURL = driverServiceManager.startEdgeDriverService();
    		}else if(browserName.equalsIgnoreCase("IE")){
    			driverServiceURL = driverServiceManager.startIEDriverService();
    		}else if(browserName.equalsIgnoreCase("SAFARI")){
    			driverServiceURL = driverServiceManager.startSafariDriverService();
    		}  	
        	this.driverServiceURL = driverServiceURL;
    	}catch(Exception e){
    		logger.warn("Failed to start Driver service");
    	}
    	
    	
    }
    
    public URL getDriverServiceURL(){
    	return this.driverServiceURL;
    }
    
    
    //Use below method to create Driver object in case of UI tests
    public WebDriver createDriver(){
    	logger.info("------------------------------------------------------------------------");
    	logger.info("Creating Driver instance ... ");
    	logger.info("------------------------------------------------------------------------");    
    	this.setDriver(this.getDriverServiceURL());
		driver = this.getDriver();
		return driver;	
    }	
    

}
