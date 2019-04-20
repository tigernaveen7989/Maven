package stepDefinition;

import cucumber.api.java.en.Then;
import pageObjects.SelectFlightPage;
import utils.DriverFactory;

public class selectFlightSteps extends DriverFactory{

	@Then("^verify that user navigated successfully to select flight page$")
	public void verify_that_user_navigated_successfully_to_select_flight_page() throws Throwable {
	  SelectFlightPage.verifyTitle();
	}
}
