package com.shell.markethub.usfuels.runner;
import java.io.File;

import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;

import com.cucumber.listener.Reporter;
import com.shell.markethub.base.util.config.BaseDataConstants;

import cucumber.api.testng.*;

import cucumber.api.CucumberOptions;

@CucumberOptions(
		features = "src/test/java/com/shell/markethub/usfuels/features",
		glue = {"com/shell/markethub/usfuels/stepdefinition","com/shell/markethub/integration/stepdefinition"},
		monochrome = true,
		plugin = {"pretty","json:target/testreport/cucumber.json","com.cucumber.listener.ExtentCucumberFormatter:target/testreport/extent-report/report.html"}
		)
public class Runner extends AbstractTestNGCucumberTests{

	@AfterClass
	public static void writeExtentReport() {
		//Reporter.loadXMLConfig(new File(BaseDataConstants.PROJECT_ROOT_LOCATION+"/base/src/main/java/com/shell/markethub/base/util/config/ReportsConfig.xml"));
	}
}
