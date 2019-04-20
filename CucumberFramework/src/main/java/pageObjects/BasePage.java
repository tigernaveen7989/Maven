package pageObjects;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.listener.Reporter;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import utils.DriverFactory;

public class BasePage extends DriverFactory{
	
	protected WebDriverWait wait;
	protected static JavascriptExecutor js;

	public BasePage() {
		wait = new WebDriverWait(driver, 30);
		js = ((JavascriptExecutor) driver);
	}
	
	public static String getText(WebElement element) throws Exception{
		String text = element.getText();
		return text;
	}
	
	public static Boolean isDisplayed(WebElement element) throws Exception{
		Boolean flag=false;
		try {
			flag = element.isDisplayed();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	public static void getURL(String value) throws Exception{
		driver.get(value);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public static void enterValue(WebElement element, String value) throws Exception{
		element.sendKeys(value);
	}
	
	public static void clickElement(WebElement element) throws Exception{
		element.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public static void selectElement(WebElement element, String value) throws Exception{
		Select dropDown = new Select(element);
		dropDown.selectByVisibleText(value);
	}
	
	public static void selectElementByIndex(WebElement element, int value) throws Exception{
		Select dropDown = new Select(element);
		dropDown.selectByIndex(value);
	}
	
	public static void scrollDownPageToElement(WebElement element) throws Exception{
		js.executeScript("arguments[0].scrollIntoView()", element);
	}
	
	public static String returnDateStamp(String fileExtension) throws Exception{
		Date d= new Date();
		String date = d.toString()+fileExtension;
		return date;
	}
	
	public static void switchToFrame(int frame) throws Exception{
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		 int numberOfFrames = Integer.parseInt(exe.executeScript("return window.length").toString());
		 System.out.println("Number of iframes on the page are " + numberOfFrames);
		 driver.switchTo().frame(frame);
	}
	
	public static void captureScreenshot() throws Exception{
		File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotName = returnDateStamp(".png");
		FileUtils.copyFile(srcfile, new File(System.getProperty("user.dir")+"screenshot/"+screenshotName+""));
		Reporter.addStepLog("Taking screenshot");
	}
}
