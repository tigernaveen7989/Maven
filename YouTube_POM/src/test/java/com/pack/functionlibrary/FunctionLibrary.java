package com.pack.functionlibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pack.utils.Listeners.TestListener;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;

public class FunctionLibrary {

	private WebDriver driver;
	Properties p = new Properties();
	Properties cfg = new Properties();
	ThreadLocal<ExtentTest> test = TestListener.getTest();
	// Map for getting column name in test data sheet
	public static Map<String, Integer> getcolumnnumberData = new HashMap<String, Integer>();
	// Map for getting column name in Device data sheet
	public static Map<String, Integer> getcolumnnumberDevice = new HashMap<String, Integer>();
	// Map for getting column OS in Device data sheet
	public static Map<String, String> getDeviceOS = new HashMap<String, String>();
	// Map for getting column OS in Device data sheet
	public static Map<String, String> getDeviceModel = new HashMap<String, String>();
	// Map for getting column name in Device data sheet
	public static Map<String, Integer> getcolumnnumberModule = new HashMap<String, Integer>();

	// Method to read OR
	public Properties getObjectRepository() throws IOException {
		// Read object repository file
		InputStream stream = new FileInputStream(new File(
				System.getProperty("user.dir") + "/src/test/java/com/pack/objectrespository/object.properties"));
		// load all objects
		p.load(stream);
		return p;
	}

	// Method to read OR
	public Properties getObjectRepository(String path) throws IOException {
		// Read object repository file
		InputStream stream = new FileInputStream(new File(path));
		// load all objects
		p.load(stream);
		return p;
	}

	// Method for implicit wait
	public void implicitWait(WebDriver driver, int timeOut) throws IOException {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	// Method for wait for element
	public boolean waitForElement(WebDriver driver, locatorType type, String object, int timeOut) throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		switch (type) {
		case ID:
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(getObjectRepository().getProperty(object))));
			return true;
		case XPATH:
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(getObjectRepository().getProperty(object))));
			return true;
		case LINKTEXT:
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.linkText(getObjectRepository().getProperty(object))));
			return true;
		case CLASSNAME:
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.className(getObjectRepository().getProperty(object))));
			return true;
		case CSSSELECTER:
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.cssSelector(getObjectRepository().getProperty(object))));
			return true;
		case NAME:
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.name(getObjectRepository().getProperty(object))));
			return true;
		default:
			break;
		}
		Thread.sleep(3000);
		return false;
	}

	// Method for wait for element with polling interval
	public boolean waitForElementWithPollingInterval(WebDriver driver, locatorType type, String object,
			int pollingTimeOut, int timeOut) throws Exception {

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
			wait.until(
					ExpectedConditions.elementToBeClickable(By.className(getObjectRepository().getProperty(object))));
			return true;
		case CSSSELECTER:
			wait.until(
					ExpectedConditions.elementToBeClickable(By.cssSelector(getObjectRepository().getProperty(object))));
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

	// Method to take screenshot
	public void screenShot(WebDriver driver, String screenShotName) throws IOException {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			// now copy the screenshot to desired location using copyFile
			// //method
			FileUtils.copyFile(src, new File(
					System.getProperty("user.dir") + "/src/test/java/com/pack/screenshots/" + screenShotName + ".png"));
			test.get().addScreenCaptureFromPath(
					System.getProperty("user.dir") + "/src/test/java/com/pack/screenshots/" + screenShotName + ".png");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Method to sleep
	public void sleep(int sleepTime) throws Exception{
		Thread.sleep(sleepTime);
	}

	// Method to take screenshot
	public void isDisplayed(WebDriver driver, String object, locatorType type, String message) throws IOException {
		switch (type) {
		case ID:
			try {
				implicitWait(driver, 30);
				if (driver.findElement(By.id(getObjectRepository().getProperty(object))).isDisplayed()) {
					test.get().log(Status.PASS, message + " is displaying");
				}
			} catch (Exception e) {
				test.get().log(Status.FAIL, message + " is not displaying");
			}
			break;
		case XPATH:
			try {
				implicitWait(driver, 30);
				if (driver.findElement(By.xpath(getObjectRepository().getProperty(object))).isDisplayed()) {
					test.get().log(Status.PASS, message + " is displaying");
				}
			} catch (Exception e) {
				test.get().log(Status.FAIL, message + " is not displaying");
			}
			break;
		}
	}

	// Method to click element
	public void clickElement(WebDriver driver, String object, locatorType type) throws Exception {
		switch (type) {
		case ID:
			driver.findElement(By.id(getObjectRepository().getProperty(object))).click();
			break;
		case XPATH:
			driver.findElement(By.xpath(getObjectRepository().getProperty(object))).click();
			break;
		default:
			break;
		}
	}
	
	// Method to single tap
	public void singleTap(WebDriver driver, int x, int y) throws Exception {
		TouchAction action = new TouchAction((MobileDriver) driver);
		action.tap(x, y).perform();
		Thread.sleep(3000);
	}
	
	// Method to Set value
	public void setValue(WebDriver driver, String object, locatorType type, String value) throws Exception {
		switch (type) {
		case ID:
			driver.findElement(By.id(getObjectRepository().getProperty(object))).sendKeys(value);
			break;
		case XPATH:
			driver.findElement(By.xpath(getObjectRepository().getProperty(object))).sendKeys(value);
			break;
		default:
			break;
		}
	}

	// Method to select locators type
	public enum locatorType {
		CLASSNAME, ID, LINKTEXT, XPATH, CSSSELECTER, NAME;
	}

	// Method to get text
	public String getText(WebDriver driver, String object, locatorType type) throws Exception {
		String text = null;
		switch (type) {
		case ID:
			text = driver.findElement(By.id(getObjectRepository().getProperty(object))).getText();
			return text;
		case XPATH:
			text = driver.findElement(By.xpath(getObjectRepository().getProperty(object))).getText();
			return text;
		default:
			break;
		}
		return text;
	}

	// Method to get text
	public List<WebElement> getList(WebDriver driver, String object, locatorType type) throws Exception {
		List<WebElement> list=null;
		switch (type) {
		case ID:
			list = driver.findElements(By.id(getObjectRepository().getProperty(object)));
			return list;
		case XPATH:
			list = driver.findElements(By.xpath(getObjectRepository().getProperty(object)));
			return list;
		default:
			break;
		}
		return list;
	}

	// Reading excel file
	public Sheet readExcel(String filePath, String fileName, String sheetName) throws IOException {
		// Create a object of File class to open xlsx file
		File file = new File(filePath + "/" + fileName);
		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = null;
		// Find the file extension by spliting file name in substing and getting
		// only extension name
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		// Check condition if the file is xlsx file
		if (fileExtensionName.equals(".xlsx")) {
			// If it is xlsx file then create object of XSSFWorkbook class
			workbook = new XSSFWorkbook(inputStream);
		}
		// Check condition if the file is xls file
		else if (fileExtensionName.equals(".xls")) {
			// If it is xls file then create object of XSSFWorkbook class
			workbook = new HSSFWorkbook(inputStream);
		}
		// Read sheet inside the workbook by its name
		Sheet worksheet = workbook.getSheet(sheetName);
		inputStream.close();
		return worksheet;
	}

	// loading column values to map variable
	public void loadColumnName(Sheet worksheet, String name) {
		// Find number of rows in excel file
		// int rowCount = worksheet.getLastRowNum()-worksheet.getFirstRowNum();
		int colCount = worksheet.getRow(0).getLastCellNum();
		// Loop over all the rows
		Row row = worksheet.getRow(0);
		// Create a loop to print cell values in a row
		for (int j = 0; j < colCount; j++) {
			// Print excel data in console
			if (name.equals("Test")) {
				getcolumnnumberData.put(row.getCell(j).toString(), j);
			} else if (name.equals("Device")) {
				getcolumnnumberDevice.put(row.getCell(j).toString(), j);
			}
		}
	}

	// getting cell value from excel sheets
	public Object getCellValue(Sheet worksheet, String workbookName, String colName, int rowNum) {
		int colnum;
		Object returnval = null;
		// System.out.println("inside get cell value");
		if (workbookName.equals("Test")) {
			// System.out.println("inside workbook" + workbookName);
			// System.out.println(getcolumnnumberData.get(colName));
			colnum = getcolumnnumberData.get(colName);
			// System.out.println(colnum);
			Row row = worksheet.getRow(rowNum);
			returnval = row.getCell(colnum);
			// System.out.println(returnval);
		} else if (workbookName.equals("Device")) {
			colnum = getcolumnnumberDevice.get(colName);
			Row row = worksheet.getRow(rowNum);
			returnval = row.getCell(colnum);
		} else if (workbookName.equals("Module")) {
			colnum = Integer.parseInt(colName);
			Row row = worksheet.getRow(rowNum);
			returnval = row.getCell(colnum);
		}
		return returnval;
	}

	// getting cell value from excel sheets
	public Object getCellValue(String colName, int rowNum) throws Exception {
		Object returnval = null;
		Sheet excelSheetTest;
		try {
			excelSheetTest = readExcel(System.getProperty("user.dir") + "/src/test/java/com/pack/Datasheet",
					"TestData.xlsx", "YouTube");
			loadColumnName(excelSheetTest, "Test");
			returnval = getCellValue(excelSheetTest, "Test", colName, rowNum);
			return returnval;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Getting Row number of excel sheet
	public int getRowNumber(String testname) throws Exception {
		Sheet excelSheetTest;
		int returnval, rowcount;
		try {
			excelSheetTest = readExcel(System.getProperty("user.dir") + "/src/test/java/com/pack/Datasheet",
					"TestData.xlsx", "YouTube");
			loadColumnName(excelSheetTest, "Test");
			rowcount = excelSheetTest.getLastRowNum();
			for (int i = 1; i < rowcount + 1; i++) {
				String temp = getCellValue(excelSheetTest, "Test", "TC_Name", i).toString();
				if (temp.equalsIgnoreCase(testname)) {
					returnval = i;
					return returnval;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
