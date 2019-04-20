package stepDefinition;

import java.util.concurrent.TimeUnit;
import cucumber.api.testng.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.junit.Assertions;
import pageObjects.LoginPage;
import pageObjects.SearchFlightPage;
import pageObjects.SelectFlightPage;
import utils.DriverFactory;

public class searchFlightSteps extends DriverFactory{
	
	@Given("^user navigates to airasia website$")
	public void user_navigates_to_airasia_website() throws Throwable {
		SearchFlightPage.getSearchFlightPage();
	}
	
	@And("^verify its title$")
	public void verify_its_title() throws Throwable {
		SearchFlightPage.verifyTitle();
	}
		
	@And("^enter origin as \"([^\"]*)\"$")
	public void enter_origin_as_origincity(String city) throws Throwable {
		SearchFlightPage.enterOriginCity(city);	  
	}
	
	@And("^enter destination as \"([^\"]*)\"$")
	public void enter_destination_as_destinationcity(String city) throws Throwable {
		SearchFlightPage.enterDestinationCity(city);  
	}
	
	@And("^select depart date as \"([^\"]*)\"$")
	public void select_depart_date_as_departdate(String date) throws Throwable {
		SearchFlightPage.clickOnDepartureDate();
	}
	
	@And("^select return date as \"([^\"]*)\"$")
	public void select_return_date_as_returndate(String date) throws Throwable {
		SearchFlightPage.clickOnOneWayRadioButton();	  
	}
	
	@And("^scroll down page to confirm button$")
	public void scroll_down_to_confirm_button() throws Throwable{
		SearchFlightPage.scrollDownToConfirmButton();
	}
	
	@And("^click on confirm button$")
	public void click_on_confirm_button() throws Throwable{
		SearchFlightPage.clickOnConfirmButton();
	}
	
	@When("^click on search button$")
	public void click_on_search_button() throws Throwable {
		SearchFlightPage.clickOnSearchButton();
	}
	
	@And("^click on login button$")
	public void click_on_login_button() throws Throwable {
		SearchFlightPage.clickOnLoginButton();
	}
}
