package com.ea.wwce.automation.base.pageobjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ea.wwce.automation.base.config.BaseDataConstants;
import com.ea.wwce.automation.base.util.Assertions;

import io.qameta.allure.Step;

/**
 * 
 * @author rdronamraju
 * @description This class is the parent page object which contains the resuable
 *              methods/actions used by all page objects
 */
public class BasePageObject {

	private static final Logger logger = Logger.getLogger(BasePageObject.class);
	protected Assertions assertion;
	protected WebDriver driver;

	public BasePageObject(WebDriver driver) {
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
		PropertyConfigurator.configure(BaseDataConstants.PROJECT_ROOT_LOCATION + BaseDataConstants.LOG_PROPERTIES_FILE);
		assertion = new Assertions();
	}

	@Step("Click on element using java script executor")
	protected void clickUsingJavaScriptExecutor(WebElement element, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		JavascriptExecutor executor = (JavascriptExecutor) driver;

		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			executor.executeScript("arguments[0].click();", element);
		} catch (WebDriverException e) {
			logger.warn("Failed to click on element " + e.getMessage());
			throw e;
		}
	}

	@Step("Enter value using java script executor")
	protected void enterValueUsingJavaScriptExecutor(WebElement element, String val, int waitTime) {

		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			executor.executeScript("arguments[0].value = '" + " " + "'", element);
			executor.executeScript("arguments[0].value = '" + val + "'", element);

		} catch (WebDriverException e) {
			logger.warn("Failed to enter valuet " + e.getMessage());
			throw e;
		}
	}

	@Step("Load the page {url}")
	protected void loadPage(String url) {
		try {
			driver.navigate().to(url);
			logger.info("Navigating to URL " + url);
		} catch (WebDriverException e) {
			logger.warn("Failed to load URL " + e.getMessage());
			throw e;
		}
	}

	protected WebElement verifyFocus() {
		WebElement el = null;
		logger.info("Verifying current focus element");
		try {
			el = driver.switchTo().activeElement();
			el.sendKeys(Keys.TAB);
			el = driver.switchTo().activeElement();
		} catch (WebDriverException e) {
			logger.warn("Failed to focus on current element " + e.getMessage());
			throw e;
		}
		return el;
	}

	@Step("upload attachement")
	public boolean uploadAttachment(String filePath) throws AWTException, InterruptedException {
		boolean isAttachmentUploaded = false;
		try {
			logger.info("Attaching file....");
			StringSelection stringSelection = new StringSelection(filePath);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, null);
			Thread.sleep(2000);

			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(2000);
			r.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(2000);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(8000);
			logger.info("Attached file successfully");

		} catch (AWTException e) {
			logger.info("Failed to attached file " + e.getMessage());
			throw e;
		} catch (InterruptedException e) {
			logger.info("Failed to attached file " + e.getMessage());
			throw e;
		}
		return isAttachmentUploaded;

	}

	@Step("Verify web page is loaded  ")
	public void verifyPageIsLoaded() {
		int maxTime = 180;
		JavascriptExecutor js = null;
		String state;

		try {

			if (driver instanceof JavascriptExecutor) {
				js = (JavascriptExecutor) driver;
			}

			for (int i = 0; i < maxTime; i++) {

				state = js.executeScript("return document.readyState").toString();

				if (!(state.equals("complete") || state.equals("loaded"))) {
					logger.warn("Page is taking time to load");
					continue;
				} else {
					logger.info("Page is loaded successfully ");
					break;
				}
			}

		} catch (WebDriverException e) {
			logger.warn("Failed to load the page ");
			throw e;
		}
	}

	// @Step("Wait for Clickable element {element}")
	protected void waitForClickableElement(int seconds, WebElement element) {
		logger.info("Waiting for clickable element " + element);
		try {
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (NoSuchElementException e) {
			logger.warn("Could not find the element " + e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.warn("Error finding the clickable element " + e.getMessage());
			throw e;
		}
	}

	// Verify element is present
	public boolean isElementPresent(By locator, int timeOutSeconds) {
		boolean isTrue = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutSeconds);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			isTrue = true;
		} catch (NoSuchElementException e) {
			logger.warn("Could not find element " + e.getMessage());
			isTrue = false;
		} catch (Exception e) {
			logger.warn("Error finding element " + e.getMessage());
			isTrue = false;
		}
		return isTrue;

	}

	// @Step("Wait and switch to frame {frameName}")
	@Step("Wait and switch to Iframe")
	protected void waitAndSwitchToIframe(int timeOutSeconds, String frameName) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutSeconds);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
		} catch (NoSuchElementException e) {
			logger.warn("Could not find element " + e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.warn("Error finding the frame and switching to it " + e.getMessage());
			throw e;
		}
	}

	// @Step("Wait and switch to Iframe {element}")
	@Step("Wait and switch to Iframe")
	protected void waitAndSwitchToIframe(int timeOutSeconds, WebElement element) {
		try {
			logger.info("Switching to Iframe " + element);
			WebDriverWait wait = new WebDriverWait(driver, timeOutSeconds);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		} catch (NoSuchElementException e) {
			logger.warn("Could not find element " + e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.warn("Error finding the frame and switching to it " + e.getMessage());
			throw e;
		}
	}

	protected void waitForValues(Select element) {
		while (element.getOptions().size() <= 1) {
			// Wait action code here
		}
	}

	// @Step("Wait and switch to Iframe {text}")
	protected void sendKeys(WebElement element, String text) {
		try {
			logger.info("Setting text " + text + " in element " + element);
			element.clear();
			element.sendKeys(text);
		} catch (NoSuchElementException e) {
			logger.warn("Failed to send keys " + e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.warn("Failed to send keys " + e.getMessage());
			throw e;
		}
	}

	// @Step("Wait and switch to Iframe {text}")
	protected void clearTextbox(WebElement element) {
		try {
			logger.info("Clear Text" + " in element " + element);
			element.clear();

		} catch (NoSuchElementException e) {
			logger.warn("Failed to clear text " + e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.warn("Failed to clear Tex " + e.getMessage());
			throw e;
		}
	}

	// @Step("Click on element {element}")
	protected void click(WebElement element) {
		logger.info("Clicking on element " + element);
		try {
			if (this.isElementEnabled(element, 2)) {
				element.click();
				logger.warn("Element is clicked successfully ");
			} else
				logger.warn("Element is not visible ");
		} catch (NoSuchElementException e) {
			logger.warn("Failed to click on the element " + e.getMessage());
			throw e;
		} catch (WebDriverException e) {
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().build().perform();
			logger.warn("Element is clicked successfully ");
		} catch (Exception e) {
			logger.warn("Failed to click on the element " + e.getMessage());
			throw e;
		}
	}

	// @Step("Wait For Element visibility {element}")
	protected boolean waitForElementToBeVisible(WebElement element, long timeInSeconds) {
		boolean isTrue = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
			wait.until(ExpectedConditions.visibilityOf(element));
			isTrue = true;
		} catch (NoSuchElementException e) {
			logger.warn("Failed to find element " + e.getMessage());
			// throw e;
		} catch (Exception e) {
			logger.warn("Error finding the element " + e.getMessage());
			// throw e;
		}
		return isTrue;
	}

	@Step("verify element is invisible")
	protected boolean isElementInvisibile(By locator, int timeInSeconds) {
		boolean isTrue = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
			isTrue = true;
			logger.info("Element is invisible ");
		} catch (NoSuchElementException e) {
			logger.warn("Element is visible " + e.getMessage());
			isTrue = false;
		} catch (Exception e) {
			logger.warn("Error finding the element " + e.getMessage());
			isTrue = false;
		}
		return isTrue;
	}

	@Step("Wait For Element visibility ") // Not throwing exception as it is to
											// verify True/False condition
	protected boolean isElementVisible(WebElement element, int timeInSeconds) {
		boolean isTrue = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
			wait.until(ExpectedConditions.visibilityOf(element));
			isTrue = true;
			logger.info("Element is visible ");
		} catch (NoSuchElementException e) {
			logger.warn("Failed to find element " + e.getMessage());
			isTrue = false;
		} catch (Exception e) {
			logger.warn("Error finding the element " + e.getMessage());
			isTrue = false;

		}
		return isTrue;
	}

	@Step("Wait For Element to be enabled ")
	protected boolean isElementEnabled(WebElement element, int timeInSeconds) {
		boolean isTrue = false;
		try {
			isTrue = element.isEnabled();
			logger.info("Element is enabled");
		} catch (NoSuchElementException e) {
			logger.warn("Failed to find element " + e.getMessage());
		} catch (Exception e) {
			logger.warn("Error finding the element " + e.getMessage());
		}
		return isTrue;
	}

	// @Step("Wait For Element visibility {locator}")
	protected void waitForElementToBeVisible(By locator, long timeInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (NoSuchElementException e) {
			logger.warn("Failed to find element " + e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.warn("Error finding the element " + e.getMessage());
			throw e;
		}
	}

	@Step("Switch window by title {pageTitle}")
	public void switchWindowByTitle(String pageTitle) {

		try {
			logger.info("Switching to window with title " + pageTitle);
			Set<String> allTabs = driver.getWindowHandles();
			for (String tab : allTabs) {
				driver.switchTo().window(tab);
				if (driver.getTitle().equalsIgnoreCase(pageTitle)) {
					logger.info("Successfully Switched to window with title " + pageTitle);
					break;
				}
			}
		} catch (NoSuchWindowException e) {
			logger.warn("Failed to identify the window with title" + e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.warn("Failed to identify the window with title" + e.getMessage());
			throw e;
		}

	}

	@Step("Switch window by title {pageTitle}")
	public void switchWindowByIndex(int index) {

		try {
			logger.info("Switching to window with index " + index);
			ArrayList<String> allTabs = new ArrayList<String>(driver.getWindowHandles());
			if (!allTabs.isEmpty() && allTabs.size() >= 1) {
				driver.switchTo().window(allTabs.get(index));
				logger.info("Successfully Switched to window with title " + driver.getTitle());
			}
		} catch (NoSuchWindowException e) {
			logger.warn("Failed to identify the window with idex" + e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.warn("Failed to identify the window with index" + e.getMessage());
			throw e;
		}

	}

	/**
	 * This method is to close the current window handler
	 * 
	 * @param testDescription
	 * @return
	 */
	public void closeCurrentWindow() {
		driver.switchTo().window(driver.getWindowHandle()).close();
	}

	protected void waitForElementToBeInVisible(WebElement element, long timeInSeconds) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
			wait.until(ExpectedConditions.invisibilityOf(element));
		} catch (NoSuchElementException e) {
			logger.warn("Invisibility check failed." + e.getMessage());
			// throw e;
		} catch (Exception e) {
			logger.warn("Invisibility check failed." + e.getMessage());
			// throw e;
		}
	}

	protected void waitForElementToBeInVisible(By locale, long timeInSeconds) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locale));
			logger.warn("Expected element is Invisibile");
		} catch (NoSuchElementException e) {
			logger.warn("Invisibility check failed." + e.getMessage());
			// throw e;
		} catch (Exception e) {
			logger.warn("Invisibility check failed." + e.getMessage());
			// throw e;
		}
	}

	// move to particular element in page
	protected void moveToElement(WebElement element) {
		try {
			logger.info("Moving to element" + element);
			Actions actions = new Actions(driver);
			actions.moveToElement(element).build().perform();
		} catch (NoSuchElementException e) {
			logger.warn("Failed to move to element " + e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.warn("Error finding the element " + e.getMessage());
			throw e;
		}
	}

	// move to page up using scroll bar
	protected void windowScrollUp() {
		try {
			logger.info("Scroll up the window using  scroll bar");
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -250)", "");
		} catch (Exception e) {
			logger.warn("Error finding the element " + e.getMessage());
			throw e;
		}
	}

	// move to page Down using scroll bar
	protected void windowScrollDwn() {
		try {
			logger.info("Scroll Down the window using  scroll bar");
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 250)", "");
		} catch (Exception e) {
			logger.warn("Error finding the element " + e.getMessage());
			throw e;
		}
	}

	// @Step("Find dynamic element {txtReplace}")
	public WebElement findDynamicElement(String element, String txtReplace) {
		WebElement el = null;
		String finallocator = null;

		try {
			finallocator = element.replaceAll("#", txtReplace.trim());
			el = driver.findElement(By.xpath(finallocator));
			logger.info("Dynamic element has been found " + finallocator);
		} catch (Exception e) {
			logger.warn("Error finding dynamic element " + e.getMessage());
			throw e;
		}
		return el;

	}

	// Get Attribute value
	public String getAttributeValue(WebElement element, String attributeName) {

		String val = null;

		logger.info("Get attribute value from element " + element);
		try {
			val = element.getAttribute(attributeName);
		} catch (NoSuchElementException e) {
			logger.warn("Failed to find the element " + e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.warn("Failed to get attribute value from the element " + e.getMessage());
			throw e;
		}
		return val;

	}

	// Get css value
	public String getCSSValue(WebElement element, String propertyName) {

		String val = null;

		logger.info("Get CSS value from element " + element);
		try {
			val = element.getCssValue(propertyName);
		} catch (NoSuchElementException e) {
			logger.warn("Failed to find the element " + e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.warn("Failed to get CSS value from the element " + e.getMessage());
			throw e;
		}
		return val;

	}

	// Get css value from dynamic element
	public String getCSSValueFromDynamicElement(String element, String txtToReplace, long waitTime,
			String propertyName) {
		String finalLocator;
		String val = null;

		logger.info("Get CSS value from element " + element);
		try {
			finalLocator = element.replaceAll("#", txtToReplace.trim());
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.elementToBeClickable((driver.findElement(By.xpath(finalLocator)))));
			val = driver.findElement(By.xpath(finalLocator)).getCssValue(propertyName);
		} catch (NoSuchElementException e) {
			logger.warn("Failed to find the element " + e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.warn("Failed to get property vallue from the element " + e.getMessage());
			throw e;
		}
		return val;

	}

	// method press hard enter key
	public void pressEnterKey(WebElement element) {
		try {
			element.sendKeys(Keys.ENTER);
		} catch (NoSuchElementException e) {
			logger.warn("Failed to find the element " + e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.warn("Failed to press enter key for this element " + e.getMessage());
			throw e;
		}
	}

	// @Step("Get text from the element {element}")
	public String getText(WebElement element) {
		String val = null;

		logger.info("Get text from element " + element);
		try {
			val = element.getText();
		} catch (NoSuchElementException e) {
			logger.warn("Failed to find the element " + e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.warn("Failed to get text from the element " + e.getMessage());
			throw e;
		}
		return val;
	}

	// @Step("Get text from the element {element}")
	public String getTextFromDynamicElement(String element, String textToReplace) {
		String val = null;

		logger.info("Get text from dynamic element " + element);
		try {
			WebElement el = findDynamicElement(element, textToReplace);
			val = el.getText();
		} catch (NoSuchElementException e) {
			logger.warn("Failed to find the element " + e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.warn("Failed to get text from the element " + e.getMessage());
			throw e;
		}
		return val;
	}

	// @Step("Click on dynamic element {element}")
	public void clickOnDynamicElement(String element, String txtReplace) {
		WebElement el = findDynamicElement(element, txtReplace);
		logger.info("Clicking on element " + element);
		try {
			el.click();
		} catch (NoSuchElementException e) {
			logger.warn("Failed to click on the element " + e.getMessage());
			throw e;
		} catch (WebDriverException e) {
			Actions actions = new Actions(driver);
			actions.moveToElement(el).click().build().perform();
			logger.warn("Element is clicked successfully ");
		} catch (Exception e) {
			logger.warn("Failed to click on the element " + e.getMessage());
			throw e;
		}
	}

	// @Step("Wait For dynamic Element visibility {element}")
	protected boolean waitForDynamicElementToBeVisible(String element, String txtReplace, long timeInSeconds) {
		String finallocator = null;
		boolean isTrue;

		try {
			finallocator = element.replaceAll("#", txtReplace.trim());
			WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(finallocator))));
			logger.info("Element found successfully ");
			isTrue = true;
		} catch (NoSuchElementException e) {
			logger.warn("Failed to find element " + e.getMessage());
			isTrue = false;
		} catch (Exception e) {
			logger.warn("Error finding the element " + e.getMessage());
			isTrue = false;
		}
		return isTrue;
	}

	// @Step("Wait For dynamic Element visibility {element}")
	protected boolean isDynamicElementPresent(String element, String txtReplace, long timeInSeconds) {
		String finallocator = null;
		boolean isTrue;

		try {
			finallocator = element.replaceAll("#", txtReplace.trim());
			WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(finallocator)));
			logger.info("Element found successfully ");
			isTrue = true;
		} catch (NoSuchElementException e) {
			logger.warn("Failed to find element " + e.getMessage());
			isTrue = false;
		} catch (Exception e) {
			logger.warn("Error finding the element " + e.getMessage());
			isTrue = false;
		}
		return isTrue;
	}

	// Get elements size
	public int getSize(List<WebElement> element) {
		int size = 0;
		logger.info("Find size of List elements");
		try {
			size = element.size();
		} catch (NoSuchElementException e) {
			logger.warn("Failed to find element " + e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.warn("Error finding the element " + e.getMessage());
			throw e;
		}
		return size;

	}

	// @Step("Select all check boxes")
	protected void selectAllCheckBoxes(List<WebElement> elements) {
		try {
			logger.info("Check all check boxes");
			int intCnt = elements.size();
			for (int i = 1; i <= intCnt; i++) {
				this.click(elements.get(i));
			}
		} catch (Exception e) {
			logger.info("Failed to Click Check boxes" + e.getMessage());
			throw e;
		}

	}

	// Click on index element
	public void clickOnListElement(List<WebElement> element, int index) {

		logger.info("Click on row element from List");
		try {
			element.get(index).click();
		} catch (NoSuchElementException e) {
			logger.warn("Failed to find element " + e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.warn("Error finding the element " + e.getMessage());
			throw e;
		}

	}

	// Select visibile text
	public void selectVisibleText(WebElement element, String value) {
		logger.info("select list element by visible text");
		try {
			Select select = new Select(element);
			select.selectByVisibleText(value);
		} catch (NoSuchElementException e) {
			logger.warn("Failed to select element " + e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.warn("Error selecting the element " + e.getMessage());
			throw e;
		}
	}

	// Select index item from list
	public void selectByIndex(WebElement element, int value) {
		logger.info("select element by index value ");
		try {
			Select select = new Select(element);
			select.selectByIndex(value);
		} catch (NoSuchElementException e) {
			logger.warn("Failed to select element " + e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.warn("Error selecting the element " + e.getMessage());
			throw e;
		}
	}

	// Select list item by value
	public void selectListItem(WebElement element, String value, int seconds) {
		logger.info("select list element by value");
		try {
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.elementToBeSelected(element));
			List<WebElement> options = element.findElements(By.tagName("option"));
			for (WebElement option : options) {
				if (value.trim().equalsIgnoreCase(option.getText())) {
					option.click();
					break;
				}
			}
		} catch (NoSuchElementException e) {
			logger.warn("Failed to select element " + e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.warn("Error selecting the element " + e.getMessage());
			throw e;
		}
	}

	// Get Index of current Item in List
	public int getIndexOfCurrentListItem(WebElement element, String strCurrentItem, int seconds) {
		int index = 0;
		try {
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.elementToBeSelected(element));
			List<WebElement> options = element.findElements(By.tagName("option"));
			for (WebElement option : options) {
				if (!(strCurrentItem.trim().equalsIgnoreCase(option.getText())))
					index = index + 1;
				else
					break;
			}
			index = index - 1;

		} catch (NoSuchElementException e) {
			logger.warn("Failed to select element " + e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.warn("Error selecting the element " + e.getMessage());
			throw e;
		}
		return index;

	}

	// Get All items from List
	public List<String> getAllItemsFrmList(WebElement element) {
		List<String> temp = new ArrayList<String>();
		int index = 0;
		try {
			List<WebElement> options = element.findElements(By.tagName("option"));
			for (WebElement option : options)
				temp.add(index, option.getText());

		} catch (Exception e) {
			logger.warn("Failed to get list values from List " + e.getMessage());
			throw e;
		}
		return temp;

	}

	// Get Current URL
	public String getCurrentPageURL() {
		String pageTitle = null;
		logger.info("Get Current page URL");
		try {
			this.verifyPageIsLoaded();
			pageTitle = driver.getCurrentUrl();
		} catch (WebDriverException e) {
			logger.warn("Error getting current page URL " + e.getMessage());
			throw e;
		}
		return pageTitle;

	}

	// Get Current page title
	public String getPageTitle() {
		String pageTitle = null;
		logger.info("Get Current page Title");
		try {
			pageTitle = driver.getTitle();
		} catch (WebDriverException e) {
			logger.warn("Error getting current page title " + e.getMessage());
			throw e;
		}
		return pageTitle;
	}

	@Step("Refresh the page ")
	public void refreshPage() {
		try {
			driver.navigate().refresh();
			logger.info("Page is refreshed successfully ");
		} catch (WebDriverException e) {
			logger.warn("Failed to refresh the page " + e.getMessage());
			throw e;
		}
	}

	@Step("Navigate back to previous page ")
	public void navigateBackTo() {
		try {
			((JavascriptExecutor) driver).executeScript("javascript: setTimeout(\"history.go(-1)\", 2000)");
			logger.info("Successfully navigated to previous page ");
		} catch (WebDriverException e) {
			driver.navigate().back();
			// logger.warn("Not able to navigate to previous page ");
		} catch (Exception e) {
			logger.warn("Fail to navigate to previous page " + e.getMessage());
			throw e;
		}
	}

	@Step("Get current window handle ")
	public String getCurrentWindowHandle() {
		return driver.getWindowHandle();
	}

	@Step("Switch to current window handle")
	public void switchToWindowHandler(String windowHandler) {
		try {
			driver.switchTo().window(windowHandler);
			Thread.sleep(1000);
		} catch (WebDriverException e) {
			logger.warn("Not able to switch to expected window " + e.getMessage());
			throw e;
		} catch (InterruptedException e) {
			logger.warn("Interrupted exception occurred. " + e.getMessage());
		} catch (Exception e) {
			logger.warn("Not able to switch to expected window " + e.getMessage());
			throw e;
		}
	}

	@Step("Switch to default content ")
	public void switchToDefaultContent() {
		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			logger.warn("Not able to switch to expected window " + e.getMessage());
			throw e;
		}
	}

	/**
	 * Method to switch to alert
	 * 
	 * @author srinivas
	 */
	public String acceptAlert() {
		String alertText = null;
		try {
			logger.warn("switch to alert window ");
			alertText = driver.switchTo().alert().getText();
			driver.switchTo().alert().accept();

		} catch (NoAlertPresentException Ex) {
			logger.warn("Not able to switch to expected window " + Ex.getMessage());
		}
		return alertText;
	}

	public String getTableCellVal(String strXpath, int r, int c) {
		String strText = null;
		try {
			String fullXpath = String.format(strXpath, r, c);
			strText = this.getText(driver.findElement(By.xpath(fullXpath)));
		} catch (Exception e) {
			logger.info("Failed to Click on Table Cell  " + e.getMessage());
		}
		return strText;
	}

	// Validating ascending order
	public boolean isListSorted(List<String> list) {
		boolean sorted = false;
		try {
			List<String> temp = new ArrayList<String>(list);
			Collections.sort(temp);
			sorted = temp.equals(list);
		} catch (Exception e) {
			logger.info("Failed to Sort the list  " + e.getMessage());
		}
		return sorted;
	}

	// Method to convert current date to UTC format

	public String getUTCDateTimeAsString(String dateFormat) {
		String utcTime = null;
		try {
			logger.info("convert Current date to UTC date");
			final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

			utcTime = sdf.format(new Date());
		} catch (Exception e) {
			logger.info("Failed to convert Date to UTC date");

		}
		return utcTime;
	}

	public String getUTCDateTimeAsString(String dateFormat, String strTimeZone) {
		String utcTime = null;
		try {
			logger.info("convert Current date to UTC date");
			final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			sdf.setTimeZone(TimeZone.getTimeZone(strTimeZone));

			utcTime = sdf.format(new Date());
		} catch (Exception e) {
			logger.info("Failed to convert Date to " + strTimeZone + "  date");
		}
		return utcTime;
	}

	@Step("delete all cookies")
	public void deleteAllCookies() {
		try {
			logger.info("delete all cookies");
			driver.manage().deleteAllCookies();

		} catch (Exception e) {
			logger.info("Failed to delete all cookies");
		}
	}

	// @Step("Get attribute value of Dynamic element")
	protected String getAttributeValueOfDynamicElement(String element, String textToReplace, String attributeName) {
		String strText = null;
		try {
			logger.info("Get Attribute value from Dynamic Element ");
			WebElement el = findDynamicElement(element, textToReplace);
			strText = el.getAttribute(attributeName);
			strText = strText.trim();
		} catch (NoSuchElementException e) {
			logger.warn("Failed to find element " + e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.warn("Error finding the element " + e.getMessage());
			throw e;
		}
		return strText;
	}

	// Random alphabetic string
	public String generateRandomString() {
		String strrandom = null;
		try {

			logger.info("generate random String");
			strrandom = RandomStringUtils.randomAlphabetic(10);

		} catch (Exception e) {
			logger.info("Failed to generate random string  " + e.getMessage());
		}
		return strrandom;
	}

	// Method to delete all cookies
	public void DeleteAllCookies() {
		driver.manage().deleteAllCookies();
	}

	// Method to delete cache
	public void DeleteCahe() {
		try {
			Runtime.getRuntime().exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Method returns a random number within range of numbers
	public static int generateRandomNumber(int min, int max) {
		return (int) (Math.random() * ((max - min) + 1)) + min;
	}

	// This method appends to the number if number length is equals to 1
	public static String getRandomNumberInString(int min, int max) {
		String strNumber = String.valueOf(generateRandomNumber(min, max));
		if (strNumber.length() == 1) {
			strNumber = "0" + strNumber;
		}
		return strNumber;
	}

	// Find dynamic elements(Two elements)
	public WebElement findDynamicElements(String element, String txtReplace, String txtReplace1) {
		WebElement el = null;
		String finallocator = null;
		String finallocator2 = null;

		try {
			finallocator = element.replaceFirst("#", txtReplace.trim());
			finallocator2 = finallocator.replaceFirst("#", txtReplace1.trim());
			el = driver.findElement(By.xpath(finallocator2));
			logger.info("Dynamic element has been found " + finallocator2);
		} catch (Exception e) {
			logger.warn("Error finding dynamic element " + e.getMessage());
			throw e;
		}
		return el;
	}

	// Click on Dynamic elements(Substuites two dynamic elements)
	public void clickOnDynamicElements(String element, String txtReplace, String txtReplace1) {
		WebElement el = findDynamicElements(element, txtReplace, txtReplace1);
		logger.info("Clicking on element " + element);
		try {
			el.click();
		} catch (NoSuchElementException e) {
			logger.warn("Failed to click on the element " + e.getMessage());
			throw e;
		} catch (WebDriverException e) {
			Actions actions = new Actions(driver);
			actions.moveToElement(el).click().build().perform();
			logger.warn("Element is clicked successfully ");
		} catch (Exception e) {
			logger.warn("Failed to click on the element " + e.getMessage());
			throw e;
		}
	}
	
	// Get All WebElements from List
	public List<WebElement> getWebElementsFrmList(String element) {
		List<WebElement> temp;
		try {
			temp = driver.findElements(By.xpath(element));
		} catch (Exception e) {
			logger.warn("Failed to get list values from List " + e.getMessage());
			throw e;
		}
		return temp;

	}

	public String getDynamicElements(String element, String txtReplace) {
		String finallocator = null;

		try {
			finallocator = element.replaceFirst("#", txtReplace.trim());
			logger.info("Dynamic element has been found " + finallocator);
		} catch (Exception e) {
			logger.warn("Error finding dynamic element " + e.getMessage());
			throw e;
		}
		return finallocator;
	}
	
	// @Step("Find dynamic elements {txtReplace, txtReplace}")
	public String getDynamicElements(String element, String txtReplace, String txtReplace1) {
		String finallocator = null;
		String finallocator2 = null;

		try {
			finallocator = element.replaceFirst("#", txtReplace.trim());
			finallocator2 = finallocator.replaceFirst("#", txtReplace1.trim());
			logger.info("Dynamic element has been found " + finallocator2);
		} catch (Exception e) {
			logger.warn("Error finding dynamic element " + e.getMessage());
			throw e;
		}
		return finallocator2;
	}
	
	// move to page up using scroll bar
	protected void windowScrollRight(WebElement element) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		} catch (Exception e) {
			logger.warn("Error finding the element " + e.getMessage());
			throw e;
		}
	}
	
	// method to perform double click
	public void doubleClick(WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick(element).build().perform();
	}
}
