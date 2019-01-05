package runner;

import org.junit.runner.RunWith;
import cucumber.api.testng.*;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@CucumberOptions(
		features = "src/test/java/resources/features",
		glue = "stepDefinition",
		monochrome = true,
		dryRun = false,
		plugin = {"pretty","html:target/cucumber","json:target/cucumber.json","com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"}
		)
public class MainRunner extends AbstractTestNGCucumberTests{

}
