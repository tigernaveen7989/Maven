package com.shell.markethub.uam.runner;
import java.io.File;

import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;

import com.cucumber.listener.Reporter;
import com.shell.markethub.base.pageobjects.BasePageObject;
import com.shell.markethub.base.util.config.BaseDataConstants;

import cucumber.api.testng.*;

import cucumber.api.CucumberOptions;

@CucumberOptions(
		features = "src/test/java/com/shell/markethub/uam/features",
		glue = {"com/shell/markethub/uam/stepdefinition","com/shell/markethub/integration/stepdefinition"},
		monochrome = true,
		plugin = {"pretty","json:target/testreport/cucumber.json","com.cucumber.listener.ExtentCucumberFormatter:target/testreport/extent-report/report.html"}
		)
public class Runner extends AbstractTestNGCucumberTests{

	static BasePageObject basePageObject = new BasePageObject();
	@AfterClass
	public static void writeExtentReport() {
		//Reporter.loadXMLConfig(new File(BaseDataConstants.PROJECT_ROOT_LOCATION+"/base/src/main/java/com/shell/markethub/base/util/config/ReportsConfig.xml"));
		try {
			//basePageObject.copyFileFromOneLocToAnotherLoc(BaseDataConstants.PROJECT_ROOT_LOCATION+"/uam/TestReport/cucumber.json", BaseDataConstants.PROJECT_ROOT_LOCATION+"/integration/TestReport/uamcucumber.json");
		}catch(Exception e) {}
	}
}
