package com.pack.base;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class TestBaseSetupWeb {
	private WebDriver driver;
	static String driverPath = System.getProperty("user.dir")+"/src/test/java/com/drivers/";
	public static Map<String, String> getDeviceOS = new HashMap<String, String>();	

	public WebDriver getDriver() {
		return driver;
	}
  
	private void setDriver(String deviceName, String platformVersion, String URL) throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("platformName", "Android");
        //capabilities.setCapability("udid", deviceName);
       //capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("browserName", "chrome");
        System.setProperty("webdriver.chrome.driver", driverPath+ "chromedriver/chromedriver.exe");
        Thread.sleep(3000);
        driver =  new AndroidDriver<MobileElement>(new URL("http://"+URL+"/wd/hub"), capabilities);
	}
	
	@Parameters({ "deviceName", "platformVersion", "URL" })
	@BeforeMethod
	public void initializeTestBaseSetup(String deviceName, String platformVersion, String URL) {
		try {
			setDriver(deviceName, platformVersion, URL);

		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace().toString());
		}
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}	
}