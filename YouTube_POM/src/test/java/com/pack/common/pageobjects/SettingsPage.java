package com.pack.common.pageobjects;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pack.functionlibrary.FunctionLibrary;
import com.pack.functionlibrary.FunctionLibrary.locatorType;
import com.pack.utils.Listeners.TestListener;

import ru.yandex.qatools.allure.annotations.Step;

public class SettingsPage {
	private WebDriver driver;
	FunctionLibrary globalfunctions=new FunctionLibrary();
	//Extent Report Declarations
	ThreadLocal<ExtentTest> test = TestListener.getTest();
	
	public SettingsPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void clickGeneral() throws Exception{
		globalfunctions.isDisplayed(driver, "YT_SettingsPage_General_Link", locatorType.XPATH, "General Link");
		globalfunctions.clickElement(driver, "YT_SettingsPage_General_Link", locatorType.XPATH);
		test.get().log(Status.INFO, "Clicked on General Link");
	}
	
	public String getAutoPlayToggleText() throws Exception{
		globalfunctions.isDisplayed(driver, "YT_SettingsPage_Autoplay_Toggle", locatorType.XPATH, "Autoplay Toggle");
		String toggleText = globalfunctions.getText(driver, "YT_SettingsPage_Autoplay_Toggle", locatorType.XPATH);
		return toggleText;
	}
	
	public void clickAutoPlayToggle() throws Exception{
		if(getAutoPlayToggleText().equals("ON")){
			globalfunctions.screenShot(driver, "Toggle is On");
			globalfunctions.clickElement(driver, "YT_SettingsPage_Autoplay_Toggle", locatorType.XPATH);
			test.get().log(Status.PASS, "Clicked on Toggle");
			test.get().addScreenCaptureFromPath(System.getProperty("user.dir") + "/src/test/java/com/screenshots/Toggle is On.png");
		}else{
			globalfunctions.screenShot(driver, "Toggle is Off");
			globalfunctions.clickElement(driver, "YT_SettingsPage_Autoplay_Toggle", locatorType.XPATH);
			test.get().log(Status.PASS, "Clicked on Toggle");
			test.get().addScreenCaptureFromPath(System.getProperty("user.dir") + "/src/test/java/com/screenshots/Toggle is Off.png");
		}
	}
	
	public void toggleOnAndOff() throws Exception{
		clickAutoPlayToggle();
		clickAutoPlayToggle();
	}
}
