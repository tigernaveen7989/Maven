package com.shell.markethub.base.util.extentreports;

import java.io.File;
import java.util.logging.Level;

import org.openqa.selenium.Platform;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;
    private static Platform platform;
    private static String macPath = System.getProperty("user.dir")+ "\\target\\testreport";
    private static String windowsPath = System.getProperty("user.dir")+ "\\target\\testreport";
    private static String macReportFileLoc = macPath + "\\";
    private static String winReportFileLoc = windowsPath + "\\";
 
	public static ExtentReports getInstance(String moduleName) {
		if (extent == null)
			createInstance(moduleName);
		return extent;
	}
	   
  //Create an extent report instance
    public static ExtentHtmlReporter createHTMLReporterInstance(String moduleName) {
    	
        platform = getCurrentPlatform();
        String fileName = getReportFileLocation(platform, moduleName);
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        //htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        //htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Functional Suite");
        htmlReporter.config().enableTimeline(true);
        //htmlReporter.setAppendExisting(false);       
        return htmlReporter;
    }
 
    //Create an extent report instance
    public static ExtentReports createInstance(String moduleName) {
		platform = getCurrentPlatform();
		String fileName = getReportFileLocation(platform, moduleName);
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		//htmlReporter.setAppendExisting(false);		 
        extent = new ExtentReports();
        //extent.attachReporter(htmlReporter);
        return extent;
    }
 
    //Select the extent report file location based on platform
    private static String getReportFileLocation (Platform platform, String moduleName) {
        String reportFileLocation = null;
        switch (platform) {
            case MAC:
                reportFileLocation = macReportFileLoc+moduleName+"_Automation_Report.html";
                createReportPath(macPath);               
                break;
            case WINDOWS:
                reportFileLocation = winReportFileLoc+moduleName+"_Automation_Report.html";
                createReportPath(windowsPath);                
                break;
            default:            
                break;
        }
        return reportFileLocation;
    }
 
    //Create the report path if it does not exist
    private static void createReportPath (String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {             
            } else {     
            }
        } else {          
        }
    }
 
    //Get current platform
    private static Platform getCurrentPlatform () {
        if (platform == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win")) {
                platform = Platform.WINDOWS;
            } else if (operSys.contains("nix") || operSys.contains("nux")
                    || operSys.contains("aix")) {
                platform = Platform.LINUX;
            } else if (operSys.contains("mac")) {
                platform = Platform.MAC;
            }
        }
        return platform;
    }

	public static ExtentReports addReporterName(String moduleName) {
		platform = getCurrentPlatform();
		String fileName = getReportFileLocation(platform, moduleName);
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		//htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		//htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setLevel(Status.INFO);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().enableTimeline(false);
		htmlReporter.config().setDocumentTitle("Functional Suite");
		htmlReporter.config().setReportName(moduleName);
		extent.attachReporter(htmlReporter);
        return extent;
	}
}