package com.pack.common.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pack.functionlibrary.FunctionLibrary;
import com.pack.functionlibrary.FunctionLibrary.locatorType;
import com.pack.utils.Listeners.TestListener;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import ru.yandex.qatools.allure.annotations.Step;

public class StreamVideoPage {
	private WebDriver driver;
	FunctionLibrary globalfunctions=new FunctionLibrary();
	//Extent Report Declarations
	ThreadLocal<ExtentTest> test = TestListener.getTest();
	
	public StreamVideoPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void clickPause(String TCName) throws Exception{
		globalfunctions.sleep(30000);
		globalfunctions.singleTap(driver, 701, 489);
		globalfunctions.isDisplayed(driver, "YT_StreamVideoPage_Play_Pause_Button", locatorType.ID, "Pause & Play Button");
		globalfunctions.clickElement(driver, "YT_StreamVideoPage_Play_Pause_Button", locatorType.ID);
		test.get().log(Status.PASS, "Clicked on Pause Button");
	}
	
	public void explicitWait() throws Exception{
		globalfunctions.waitForElement(driver, locatorType.ID, "YT_StreamVideoPage_Video_Title", 60);
	}
	
	public void videoTitle(String videoTitle) throws Exception{
		String actualVideoTitle = globalfunctions.getText(driver, "YT_StreamVideoPage_Video_Title", locatorType.ID);
		if(videoTitle.contains(actualVideoTitle)){
			test.get().log(Status.PASS, "Video Title is matching with search results page");
		}else{
			test.get().log(Status.FAIL, "Video Title is not matching with search results page");
		}
	}
	
	public void verifyMinimizeVideo() throws Exception{
		try{
			explicitWait();
			driver.findElement(By.id(globalfunctions.getObjectRepository().getProperty("YT_StreamVideoPage_Video_Info_View"))).isDisplayed();
			test.get().log(Status.PASS, "Video minimized successfully");
			globalfunctions.screenShot(driver, "minimizevideo");
			test.get().addScreenCaptureFromPath(System.getProperty("user.dir") + "/src/test/java/com/screenshots/minimizevideo.png");
		}catch(Exception e){
			test.get().log(Status.FAIL, "Video is not minimized successfully");
		}
	}
	
	public void verifyMaximizeVideo() throws Exception{
		try{
			explicitWait();
			driver.findElement(By.id(globalfunctions.getObjectRepository().getProperty("YT_StreamVideoPage_Up_Next_Text"))).isDisplayed();
			test.get().log(Status.PASS, "Video maximized successfully");
			globalfunctions.screenShot(driver, "maximizevideo");
			test.get().addScreenCaptureFromPath(System.getProperty("user.dir") + "/src/test/java/com/screenshots/maximizevideo.png");
		}catch(Exception e){
			test.get().log(Status.FAIL, "Video is not maximized successfully");
		}
	}
	
	public void swipeDown() throws Exception{
		TouchAction action = new TouchAction((MobileDriver) driver);
		action.longPress(361, 334)
		  .moveTo(334,2035)
		  .release()
		  .perform();
	}
	
	public void swipeUp() throws Exception{
		TouchAction action = new TouchAction((MobileDriver) driver);
		action.longPress(957,2157)
		  .moveTo(957,434)
		  .release()
		  .perform();
	}
}
