package com.pack.functionlibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pack.utils.Listeners.TestListener;

public class FunctionLibrary {
	
	Properties p = new Properties();
	ThreadLocal<ExtentTest> test = TestListener.getTest();

	//Method to read OR 
		public Properties getObjectRepository() throws IOException{
			//Read object repository file
			InputStream stream = new FileInputStream(new File(System.getProperty("user.dir")+"/src/test/java/com/pack/objectrespository/object.properties"));
			//load all objects
			p.load(stream);
			return p;
		}
		
		//Method to read OR 
		public Properties getObjectRepository(String path) throws IOException{
			//Read object repository file
			InputStream stream = new FileInputStream(new File(path));
			//load all objects
			p.load(stream);
			return p;
		}
		
		//Method for implicit wait
		public void implicitWait(WebDriver driver, int timeOut) throws IOException{
			driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
		}
		
		//Method for wait for element
		public boolean waitForElement(WebDriver driver, locatorType type, String object, int timeOut) throws Exception{
			
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			switch (type) {
			case ID:
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(getObjectRepository().getProperty(object))));
				return true;
			case XPATH:
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(getObjectRepository().getProperty(object))));
				return true;
			case LINKTEXT:
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(getObjectRepository().getProperty(object))));
				return true;
			case CLASSNAME:
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(getObjectRepository().getProperty(object))));
				return true;
			case CSSSELECTER:
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(getObjectRepository().getProperty(object))));
				return true;
			case NAME:
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(getObjectRepository().getProperty(object))));
				return true;
			default:
				break;
			}
			Thread.sleep(3000);
			return false;
		}
		
		
		//Method for wait for element with polling interval
		public boolean waitForElementWithPollingInterval(WebDriver driver, locatorType type, String object, int pollingTimeOut, int timeOut) throws Exception{
					
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.pollingEvery(1, TimeUnit.SECONDS);
			switch (type) {
			case ID:
				wait.until(ExpectedConditions.elementToBeClickable(By.id(getObjectRepository().getProperty(object))));
				return true;
			case XPATH:
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(getObjectRepository().getProperty(object))));
				return true;
			case LINKTEXT:
				wait.until(ExpectedConditions.elementToBeClickable(By.linkText(getObjectRepository().getProperty(object))));
				return true;
			case CLASSNAME:
				wait.until(ExpectedConditions.elementToBeClickable(By.className(getObjectRepository().getProperty(object))));
				return true;
			case CSSSELECTER:
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(getObjectRepository().getProperty(object))));
				return true;
			case NAME:
				wait.until(ExpectedConditions.elementToBeClickable(By.name(getObjectRepository().getProperty(object))));
				return true;
			default:
				break;
			}
			Thread.sleep(3000);
			return false;
		}
		
		//Method to take screenshot
		public void screenShot(WebDriver driver, String screenShotName) throws IOException{
			
			File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
			 // now copy the  screenshot to desired location using copyFile //method
			FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"/src/test/java/com/pack/screenshots/"+screenShotName+".png"));
			test.get().addScreenCaptureFromPath(System.getProperty("user.dir")+"/src/test/java/com/pack/screenshots/"+screenShotName+".png");
			}catch (IOException e){
			  System.out.println(e.getMessage());			 
			 }
		}
		
		//Method to take screenshot
		public void isDisplayed(WebDriver driver, String object, String message) throws IOException{
			try{
				implicitWait(driver, 30);
				if(driver.findElement(By.xpath(getObjectRepository().getProperty(object))).isDisplayed()){
					test.get().log(Status.PASS, message+" is displaying");
				}				
			}catch(Exception e){
				test.get().log(Status.FAIL, message+" is not displaying");
			}
		}	
		
		//Method to click element
		public void clickElement(WebDriver driver, String object, locatorType type) throws Exception{
			switch (type) {
			case ID:
				driver.findElement(By.id(getObjectRepository().getProperty(object))).click();
			case XPATH:
				driver.findElement(By.xpath(getObjectRepository().getProperty(object))).click();
			default:
				break;
			}
			Thread.sleep(3000);
		}	
		
		//Method to Set value
		public void setValue(WebDriver driver, String object, locatorType type, String value) throws Exception{
			switch (type) {
			case ID:
				driver.findElement(By.id(getObjectRepository().getProperty(object))).sendKeys(value);
			case XPATH:
				driver.findElement(By.xpath(getObjectRepository().getProperty(object))).sendKeys(value);
			default:
				break;
			}
		}	
		
		//Method to select locators type
		public enum locatorType {
	        CLASSNAME, ID, LINKTEXT, XPATH, CSSSELECTER, NAME;
	   }
}
