package com.shell.markethub.base.util;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import com.shell.markethub.base.pageobjects.BasePageObject;
import com.shell.markethub.base.util.config.BaseDataConstants;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * @author N.Kumar8
 * @description This class will initiate driver instance
 * @retun driver
 */
public class Driver {

	public WebDriver driver;
	private static Logger logger = Logger.getLogger(Driver.class);
	private DesiredCapabilities capabilities;
	protected BasePageObject basePageObject;	 
			
	@SuppressWarnings("deprecation")
	public WebDriver setDriver(String appType, String node, String deviceName, String platformVersion) throws Exception{
		try {			
			switch(appType) {
			case "firefox":
				System.setProperty("webdriver.gecko.driver", BaseDataConstants.PROJECT_ROOT_LOCATION +BaseDataConstants.GECKO_DRIVER_PATH);
				capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability("marionette", true);
				driver = new FirefoxDriver(capabilities);
				driver.manage().window().maximize();
				break;
				
			case "chrome":
				System.setProperty("webdriver.chrome.driver",BaseDataConstants.PROJECT_ROOT_LOCATION + BaseDataConstants.CHROME_DRIVER_PATH);
				driver = new ChromeDriver();
				//driver = new RemoteWebDriver(new URL("http://"+node+"/wd/hub"), capabilities);
				driver.manage().window().maximize();
				break;
				
			case "ie":
				capabilities = DesiredCapabilities.internetExplorer();
				System.setProperty("webdriver.ie.driver", BaseDataConstants.PROJECT_ROOT_LOCATION +BaseDataConstants.IEXPLORER_DRIVER_PATH);
				capabilities.setCapability("ignoreZoomSetting", true);
				driver = new InternetExplorerDriver(capabilities);
				driver.manage().window().maximize();
				break;
				
			case "edge":
				System.setProperty("webdriver.edge.driver", BaseDataConstants.PROJECT_ROOT_LOCATION +BaseDataConstants.EDGE_DRIVER_PATH);
				driver = new EdgeDriver();
				driver.manage().window().maximize();
				break;
				
			case "mobileChrome":
				DesiredCapabilities capabilities = new DesiredCapabilities();
		        capabilities.setCapability("deviceName", deviceName);
		        capabilities.setCapability("platformVersion", platformVersion);
		        capabilities.setCapability("platformName", "Android");
		        capabilities.setCapability("automationName", "UiAutomator2");
		        capabilities.setCapability("browserName", "chrome");
		        capabilities.setCapability("fullReset", "false");
		        capabilities.setCapability("noReset", "true");
		        System.setProperty("webdriver.chrome.driver", BaseDataConstants.PROJECT_ROOT_LOCATION +BaseDataConstants.CHROME_DRIVER_PATH);
		        driver =  new AndroidDriver<MobileElement>(new URL("http://"+node+"/wd/hub"), capabilities);
		        
			case "mobileSafari":
				DesiredCapabilities capabilities1 = new DesiredCapabilities();
				capabilities1.setCapability("deviceName", deviceName);
		        capabilities1.setCapability("platformName", "iOS");
		        capabilities1.setCapability("platformVersion", platformVersion);
		        capabilities1.setCapability("browserName", "Safari");
		        //capabilities1.setCapability("udid", deviceName);
		        capabilities1.setCapability("fullReset", "false");
				capabilities1.setCapability("noReset", "true");	 
		        driver = new IOSDriver(new URL("http://"+node+"/wd/hub"), capabilities1);
			default:
				logger.info("No driver has been setup");		
			}
			
		}catch(Exception e) {
			logger.info("Error launching Browser--"+e.getMessage());
		}finally {
			basePageObject = PageFactory.initElements(driver, BasePageObject.class);
		}
		return driver;
	}	
}
