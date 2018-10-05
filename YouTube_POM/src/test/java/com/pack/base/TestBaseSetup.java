package com.pack.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.appium.java_client.android.AndroidDriver;

public class TestBaseSetup {

	private static XSSFSheet ExcelWSheet;
	 
	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;
	public WebDriver driver;
	static String driverPath = System.getProperty("user.dir")+"/src/test/java/com/drivers/";
	public static Map<String, String> getDeviceOS = new HashMap<String, String>();	

	public WebDriver getDriver() {
		return driver;
	}

	private void setDriver(String deviceName, String platformVersion) throws Exception {
		getDeviceOS.put(deviceName, "Android");
		String deviceOS = getDeviceOS.get(deviceName).toString();
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("platformName", deviceOS);
        capabilities.setCapability("appPackage", "com.google.android.youtube");
        capabilities.setCapability("appActivity","com.google.android.apps.youtube.app.WatchWhileActivity");
        capabilities.setCapability("noReset","false");
        
        driver =  new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}
	
	@Parameters({ "deviceName", "platformVersion" })
	@BeforeMethod
	public void initializeTestBaseSetup(String deviceName, String platformVersion) {
		try {
			setDriver(deviceName, platformVersion);

		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}	
}