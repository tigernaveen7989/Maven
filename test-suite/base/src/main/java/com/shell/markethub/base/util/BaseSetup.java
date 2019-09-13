package com.shell.markethub.base.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.TestNG;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.shell.markethub.base.pageobjects.BasePageObject;
import com.shell.markethub.base.util.config.BaseDataConstants;
import com.shell.markethub.base.util.extentreports.ExtentManager;

/**
 * @author N.Kumar8@shell.com
 *
 */
public class BaseSetup extends BasePageObject {
	
	int tstCnt;
	int testmaxnum;
	int deviceRow;
	int varTestControl;
	int loopControlK = 0;
	int testRownum = 0;
	String moduleName;
	String varPack;
	String varModule;
	String varTCName;
	String deviceName;
	String varVerifyModule;
	String runtimeXML;
	String varSuiteName;
	String strFrameWorkRunMode;
	String varPackage;
	String strTestClass = "";
	String node;
	String appType;
	String platformVerison;
	double deviceCnt, testCnt;
	Properties properties = new Properties();
	Properties configuration = new Properties();
	
	public BaseSetup() {
		super();
	}

	/**
	 * @param context
	 * @throws IOException 
	 * @throws Exception
	 * @description testdata and devicedata will be pulled from excelsheets and
	 *              dynamic testng.xml will be generated and executed
	 */
	@BeforeSuite
	public void beforeSuite(ITestContext context) throws IOException {
		// Setting value for configuration file
		moduleName = context.getSuite().getName();
		System.out.println(moduleName);
		System.setProperty("PROJECT_ROOT_HOME", BaseDataConstants.PROJECT_ROOT_LOCATION);
		PropertyConfigurator.configure(BaseDataConstants.PROJECT_ROOT_LOCATION + BaseDataConstants.LOG_PROPERTIES_FILE);
		System.setProperty("pathConfig", System.getProperty("user.dir") + BaseDataConstants.MAIN_RESOURCES_ROOT_PATH);
		FileInputStream fileInputStream = new FileInputStream(System.getProperty("pathConfig") + "\\"+moduleName.toLowerCase()+"config.properties");
		properties.load(fileInputStream);
		fileInputStream.close();

		FileOutputStream fileOutputStream = new FileOutputStream(System.getProperty("pathConfig") + "\\"+moduleName.toLowerCase()+"config.properties");
		properties.setProperty("userdir", System.getProperty("user.dir"));
		properties.store(fileOutputStream, null);
		fileOutputStream.close();

		// Load Config parameter
		configuration = getObjectRepository(System.getProperty("pathConfig") + "\\"+moduleName.toLowerCase()+"config.properties");
		System.setProperty("excel", System.getProperty("user.dir") + "\\"+BaseDataConstants.TEST_DATA_ROOT_PATH);
		System.setProperty("resources", System.getProperty("user.dir") + "\\"+BaseDataConstants.TEST_RESOURCES_ROOT_PATH);
		Sheet excelSheetTest =  readExcel(System.getProperty("excel"), "testdata.xlsx", moduleName.toLowerCase());
		loadColumnName(excelSheetTest, "Test");
	
		varSuiteName = moduleName;
		strFrameWorkRunMode = configuration.getProperty("FrameWorkRunMode");
		varPackage = configuration.getProperty("PackageName");
		runtimeXML = "<!DOCTYPE suite SYSTEM \"http://testng.org/testng-1.0.dtd\">\n";
		
		List<Object> rownumbers = new ArrayList<>();
		rownumbers.clear();
		List<Object> testNumbers = new ArrayList<>();
		testNumbers.clear();
		List<Object> moduleCnt = new ArrayList<>();
		moduleCnt.clear();

		if (strFrameWorkRunMode.equalsIgnoreCase("all") || strFrameWorkRunMode.equalsIgnoreCase("quick")) {
			runtimeXML = runtimeXML + "<suite name=\"" + varSuiteName + "\" parallel=\"" + "tests"
					+ "\" thread-count=\"" + "10" + "\">\n";
		} else {
			runtimeXML = runtimeXML + "<suite name=\"" + varSuiteName + "\">\n";
		}
		// runtimeXML = runtimeXML + "<suite name=\""+varSuiteName+"\">\n";
		runtimeXML = runtimeXML + "<listeners> <listener class-name=\""
				+ "com.shell.markethub.base.util.listeners.BaseListener" + "\"/> </listeners>\n";

		try {
			// variable for Suite and Module name in reporting
			Sheet excelSheetDevice =  readExcel(System.getProperty("excel"), "devicedata.xlsx",
					"devicedata");
			 loadColumnName(excelSheetDevice, "Device");

			int rowCountTest = excelSheetTest.getLastRowNum() - excelSheetTest.getFirstRowNum();
			int rowCountDevice = excelSheetDevice.getLastRowNum() - excelSheetDevice.getFirstRowNum();

			// Loop to find out number of device to be executed
			for (int i = 1; i <= rowCountDevice; i++) {
				String varRunmode = getCellValue(excelSheetDevice, "Device", "Run_Flag", i).toString();
				if (varRunmode.equalsIgnoreCase("yes")) {
					rownumbers.add(i);
				}
			}
			// Loop to find out number of Test case to be executed to be executed
			for (int i = 1; i <= rowCountTest; i++) {
				String varRunmode = getCellValue(excelSheetTest, "Test", "Run_Flag", i).toString();
				if (varRunmode.equalsIgnoreCase("yes")) {
					testNumbers.add(i);
				}
			}
			// Get device count
			deviceCnt = rownumbers.size();
			// Get tests count
			testCnt = testNumbers.size();
			testmaxnum = (int) Math.ceil(testCnt / deviceCnt);
			strFrameWorkRunMode = strFrameWorkRunMode.toLowerCase();
			strTestClass = getTestCaseList(excelSheetTest, testNumbers, varPackage);

			if (strFrameWorkRunMode.equalsIgnoreCase("quick")) {
				// Dividing the test case based on number of device
				for (int i = 0; i < deviceCnt; i++) {
					// setting test name
					deviceRow = (int) rownumbers.get(i);
					appType = getCellValue(excelSheetDevice, "Device", "App_Type", deviceRow)
							.toString();
					deviceName = getCellValue(excelSheetDevice, "Device", "Device_Name", deviceRow)
							.toString();
					platformVerison = getCellValue(excelSheetDevice, "Device", "Platform_Version", deviceRow)
							.toString();
					node = getCellValue(excelSheetDevice, "Device", "Node", deviceRow)
							.toString();
					runtimeXML = runtimeXML + "\n<test name=\"" + appType + "\">\n";
					// setting parameter name
					runtimeXML = runtimeXML + "<parameter name=\"appType\" value=\"" + appType + "\"/>\n";
					runtimeXML = runtimeXML + "<parameter name=\"node\" value=\"" + node + "\"/>\n";
					runtimeXML = runtimeXML + "<parameter name=\"deviceName\" value=\"" + deviceName + "\"/>\n";
					runtimeXML = runtimeXML + "<parameter name=\"platformVersion\" value=\"" + platformVerison + "\"/>\n";
					runtimeXML = runtimeXML + "<parameter name=\"moduleName\" value=\"" + moduleName.toLowerCase() + "\"/>\n<classes>\n";
					for (int j = 0; j < testmaxnum; j++) {
						testRownum = (i * testmaxnum) + j;
						if (testRownum < testNumbers.size()) {
							varTestControl = (int) testNumbers.get(testRownum);
							varPack = getCellValue(excelSheetTest, "Test", "Package", varTestControl)
									.toString();
							varModule = getCellValue(excelSheetTest, "Test", "Module", varTestControl)
									.toString();
							varTCName = getCellValue(excelSheetTest, "Test", "TC_Name", varTestControl)
									.toString();
							if (varPack.isEmpty()) {
								strTestClass = "<class name=\"" + varPackage + "." + varModule + "\">\n<methods>\n";
								strTestClass = strTestClass + "<include name=\"" + varTCName + "\"/>\n";
								strTestClass = strTestClass + "</methods>\n</class> <!-- " + varPackage + "."
										+ varModule + " -->\n";
								runtimeXML = runtimeXML + strTestClass;
							} else {
								strTestClass = "<class name=\"" + varPackage + "." + varPack + "." + varModule
										+ "\">\n<methods>\n";
								strTestClass = strTestClass + "<include name=\"" + varTCName + "\"/>\n";
								strTestClass = strTestClass + "</methods>\n</class> <!-- " + varPackage + "." + varPack
										+ "." + varModule + " -->\n";
								runtimeXML = runtimeXML + strTestClass;
							}
						}
					}
					runtimeXML = runtimeXML + "</classes>\n</test> <!-- " + deviceName + "-->\n";
					if (testRownum == testNumbers.size() - 1) {
						break;
					}
				}
			} else if (strFrameWorkRunMode.equalsIgnoreCase("all")) {
				for (int i = 0; i < deviceCnt; i++) {
					// setting test name
					deviceRow = (int) rownumbers.get(i);
					appType = getCellValue(excelSheetDevice, "Device", "App_Type", deviceRow)
							.toString();
					deviceName = getCellValue(excelSheetDevice, "Device", "Device_Name", deviceRow)
							.toString();
					platformVerison = getCellValue(excelSheetDevice, "Device", "Platform_Version", deviceRow)
							.toString();
					node = getCellValue(excelSheetDevice, "Device", "Node", deviceRow)
							.toString();
					runtimeXML = runtimeXML + "\n<test name=\"" + appType + "\">\n";
					// setting parameter name
					runtimeXML = runtimeXML + "<parameter name=\"appType\" value=\"" + appType + "\"/>\n";
					runtimeXML = runtimeXML + "<parameter name=\"node\" value=\"" + node + "\"/>\n";
					runtimeXML = runtimeXML + "<parameter name=\"deviceName\" value=\"" + deviceName + "\"/>\n";
					runtimeXML = runtimeXML + "<parameter name=\"platformVersion\" value=\"" + platformVerison + "\"/>\n";
					runtimeXML = runtimeXML + "<parameter name=\"moduleName\" value=\"" + moduleName.toLowerCase() + "\"/>\n<classes>\n";
					runtimeXML = runtimeXML + strTestClass;
					runtimeXML = runtimeXML + "</classes>\n</test> <!-- " + deviceName + "-->\n";
				}
			} else if (strFrameWorkRunMode.equalsIgnoreCase("module")) {
				for (int i = 0; i < deviceCnt; i++) {
					// setting test name
					deviceRow = (int) rownumbers.get(i);
					appType = getCellValue(excelSheetDevice, "Device", "App_Type", deviceRow)
							.toString();
					deviceName = getCellValue(excelSheetDevice, "Device", "Device_Name", deviceRow)
							.toString();
					platformVerison = getCellValue(excelSheetDevice, "Device", "Platform_Version", deviceRow)
							.toString();
					node = getCellValue(excelSheetDevice, "Device", "Node", deviceRow)
							.toString();
					runtimeXML = runtimeXML + "\n<test name=\"" + appType + "\">\n";
					// setting parameter name
					runtimeXML = runtimeXML + "<parameter name=\"appType\" value=\"" + appType + "\"/>\n";
					runtimeXML = runtimeXML + "<parameter name=\"node\" value=\"" + node + "\"/>\n";
					runtimeXML = runtimeXML + "<parameter name=\"deviceName\" value=\"" + deviceName + "\"/>\n";
					runtimeXML = runtimeXML + "<parameter name=\"platformVersion\" value=\"" + platformVerison + "\"/>\n";
					runtimeXML = runtimeXML + "<parameter name=\"moduleName\" value=\"" + moduleName.toLowerCase() + "\"/>\n<classes>\n";
					runtimeXML = runtimeXML + strTestClass;
					runtimeXML = runtimeXML + "</classes>\n</test> <!-- " + deviceName + "-->\n";
				}
			}

			runtimeXML = runtimeXML + "</suite> <!-- " + varSuiteName + " -->";
			// creating new xml files
			File f = null;
			f = new File(System.getProperty("resources") + "\\runtimetestng.xml");
			if (f.exists()) {
				f.delete();
			}
			f.createNewFile();

			// writing values in to file
			FileWriter fw = new FileWriter(f.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(runtimeXML);
			bw.close();
			
			//delete allure-results files
			FileUtils.cleanDirectory(new File(BaseDataConstants.PROJECT_ROOT_LOCATION+"\\"+moduleName.toLowerCase()+"\\allure-results"));
			
			// running the testng suite files
			List<String> testFilesList = new ArrayList<String>();
			testFilesList.add(System.getProperty("resources") + "\\runtimetestng.xml");
			TestNG tng = new TestNG();
			tng.setTestSuites(testFilesList);
			tng.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void tearDown(ITestContext context) throws Exception {
		
	}

	// Get list of all the test cases to be executed
	public String getTestCaseList(Sheet excelSheetTest, List<Object> testNumbers, String varPackage) {
		tstCnt = (int) testNumbers.size();

		for (int i = 0; i < tstCnt; i++) {
			varTestControl = (int) testNumbers.get(i);
			varPack = getCellValue(excelSheetTest, "Test", "Package", varTestControl).toString();
			varModule = getCellValue(excelSheetTest, "Test", "Module", varTestControl).toString();
			
			if (varPack.isEmpty()) {
				strTestClass = strTestClass + "<class name=\"" + varPackage + "." + varModule + "\">\n<methods>\n";
			} else {
				strTestClass = strTestClass + "<class name=\"" + varPackage + "." + varPack + "." + varModule
						+ "\">\n<methods>\n";
			}

			for (int j = i; j < tstCnt; j++) {
				varTestControl = (int) testNumbers.get(j);
				varVerifyModule = getCellValue(excelSheetTest, "Test", "Module", varTestControl)
						.toString();
				varTCName = getCellValue(excelSheetTest, "Test", "TC_Name", varTestControl).toString();
				if (!(varVerifyModule.equalsIgnoreCase(varModule))) {
					loopControlK = j - 1;
					break;
				} else {
					loopControlK = j;
				}
				strTestClass = strTestClass + "<include name=\"" + varTCName + "\"/>\n";
			}
			i = loopControlK;
			if (varPack.isEmpty()) {
				strTestClass = strTestClass + "</methods>\n</class> <!-- " + varPackage + "." + varModule + " -->\n";
			} else {
				strTestClass = strTestClass + "</methods>\n</class> <!-- " + varPackage + "." + varPack + "."
						+ varModule + " -->\n";
			}

		}
		return strTestClass;
	}
}
