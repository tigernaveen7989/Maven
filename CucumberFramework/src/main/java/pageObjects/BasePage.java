package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.JavascriptExecutor;

import utils.DriverFactory;

public class BasePage extends DriverFactory{
	
	protected WebDriverWait wait;
	protected static JavascriptExecutor js;

	public BasePage() {
		wait = new WebDriverWait(driver, 30);
		js = ((JavascriptExecutor) driver);
	}
	
	public static void enterValue(WebElement element, String value) throws Exception{
		element.sendKeys(value);
	}
	
	public static void clickElement(WebElement element) throws Exception{
		element.click();
	}
	
	public static void scrollDownPageToElement(WebElement element) throws Exception{
		js.executeScript("arguments[0].scrollIntoView()", element);
	}
}
