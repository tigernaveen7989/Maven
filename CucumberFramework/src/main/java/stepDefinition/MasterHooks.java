package stepDefinition;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import utils.DriverFactory;

public class MasterHooks extends DriverFactory{

	@Before()
	public void setup() throws Exception {
		driver = driver();
	}
	
	@After()
	public void tearDown() throws Exception{
		if(driver != null) {
			driver.manage().deleteAllCookies();
			driver.quit();
		}
	}
}
