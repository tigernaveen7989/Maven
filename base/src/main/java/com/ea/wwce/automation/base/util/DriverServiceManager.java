package com.ea.wwce.automation.base.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.service.DriverService;
import org.openqa.selenium.safari.SafariDriverService;

import com.ea.wwce.automation.base.config.BaseDataConstants;

/**
 * 
 * @author rdronamraju
 * @description Class to handle the management of Driver Service
 */
public class DriverServiceManager {
	
	String operatingSystem;
	String platform;
	String browserName;
	protected GeckoDriverService geckoDriverService;
	protected ChromeDriverService chromeDriverService;
	protected EdgeDriverService edgeDriverService;
	protected InternetExplorerDriverService ieDriverService;
	protected SafariDriverService safariDriverService;
	protected DriverServiceManager driverService;
	
	public DriverServiceManager(String operatingSystem,String platform,String browser){
		this.operatingSystem = operatingSystem;
		this.platform = platform;
		this.browserName = browser;
	}
	
	public DriverServiceManager(){
		
	}
	
	  //Use this method to create a new driver service at the start of the test
    public URL startGeckoDriverService(){    
    		try{
        	     		
      			if( operatingSystem.equalsIgnoreCase("WINDOWS") && platform.equalsIgnoreCase("WEB") && 
      					browserName.equalsIgnoreCase("FIREFOX")) {                         
            	  		  File geckoDriverFile = new File(BaseDataConstants.PROJECT_ROOT_LOCATION + BaseDataConstants.GECKO_DRIVER_PATH);                    
                          geckoDriverService = new GeckoDriverService.Builder().usingDriverExecutable(geckoDriverFile).
                          		usingAnyFreePort().build();
                          geckoDriverService.start();                   
                  }
      			return geckoDriverService.getUrl();
      			
    		}catch(IOException E){
    			return geckoDriverService.getUrl();
          }  
    }
    
    public URL startChromeDriverService(){    
		try{
    	     		
			if( operatingSystem.equalsIgnoreCase("WINDOWS") && platform.equalsIgnoreCase("WEB") && 
					browserName.equalsIgnoreCase("CHROME")) {                
				  File chromeDriverFile = new File(BaseDataConstants.PROJECT_ROOT_LOCATION + BaseDataConstants.CHROME_DRIVER_PATH);
				    chromeDriverService = new ChromeDriverService.Builder().usingDriverExecutable(chromeDriverFile).
				    		usingAnyFreePort().build();
				    chromeDriverService.start();    				    
			}      
  			return chromeDriverService.getUrl();
  			
		}catch(IOException E){
			return chromeDriverService.getUrl();
		}  
    }	
    
    public URL startIEDriverService(){    
  		try{
      	     		
  			if( operatingSystem.equalsIgnoreCase("WINDOWS") && platform.equalsIgnoreCase("WEB") && 
  					browserName.equalsIgnoreCase("IE")) {       
        	  		File ieDriverFile = new File(BaseDataConstants.PROJECT_ROOT_LOCATION + BaseDataConstants.IEXPLORER_DRIVER_PATH);
		                ieDriverService = new InternetExplorerDriverService.Builder().usingDriverExecutable
		                		(ieDriverFile).usingAnyFreePort().build();
		                ieDriverService.start();		             
	         }      
    			return ieDriverService.getUrl();
    			
  		}catch(IOException E){
  			return ieDriverService.getUrl();
  		}  
     }
    
    public URL startEdgeDriverService(){    
  		try{      	     		
  			if( operatingSystem.equalsIgnoreCase("WINDOWS") && platform.equalsIgnoreCase("WEB") && 
  					browserName.equalsIgnoreCase("EDGE")) {       
        	  		File edgeDriverFile = new File(BaseDataConstants.PROJECT_ROOT_LOCATION + BaseDataConstants.EDGE_DRIVER_PATH);
		                edgeDriverService = new EdgeDriverService.Builder().usingDriverExecutable(edgeDriverFile).
		                		usingAnyFreePort().build();
  		                edgeDriverService.start();   		             
	         }     
    		return edgeDriverService.getUrl();
    			
  		}catch(IOException E){
  			return edgeDriverService.getUrl();
  		}  
     }
    
    public URL startSafariDriverService(){    
  		try{      	     		
  			if( operatingSystem.equalsIgnoreCase("WINDOWS") && platform.equalsIgnoreCase("WEB") && 
  					browserName.equalsIgnoreCase("SAFARI")) {        
        	  		File safariDriverFile = new File(BaseDataConstants.PROJECT_ROOT_LOCATION + BaseDataConstants.IEXPLORER_DRIVER_PATH);
		                System.setProperty("webdriver.safari.driver", safariDriverFile.getAbsolutePath());
		                safariDriverService = new SafariDriverService.Builder().usingDriverExecutable
		                		(safariDriverFile).usingAnyFreePort().build();
		                safariDriverService.start(); 	  	          
	         }     
    		return safariDriverService.getUrl();
    			
  		}catch(IOException E){
  			return safariDriverService.getUrl();
  		}  
     }
    
    public void stopChromeDriverService(){
    	this.chromeDriverService.stop();
    }
    
    public void stopGeckoDriverService(){
    	this.geckoDriverService.stop();
    }
    
    public void stopIEDriverService(){
    	this.ieDriverService.stop();
    }
    
    public void stopEdgeDriverService(){
    	this.edgeDriverService.stop();
    }
    
    public void stopSafariDriverService(){
    	this.safariDriverService.stop();
    }
}
