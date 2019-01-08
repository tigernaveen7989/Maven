package pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.cucumber.listener.Reporter;

import cucumber.api.Scenario;

public class SearchFlightPage extends BasePage{
	
	public static @FindBy(xpath="//*[@class='title']") WebElement Title;
	public static @FindBy(xpath="//*[@id='-origin-autocomplete-heatmap']") WebElement OriginCity_EditBox;
	public static @FindBy(xpath="//*[@id='-destination-autocomplete-heatmap']") WebElement DestinationCity_EditBox;
	public static @FindBy(xpath="//*[@class='input-daterange input-left from']") WebElement DepartureDate;
	public static @FindBy(xpath="//*[@for='trip-oneway']") WebElement Oneway_RadioButton;
	public static @FindBy(xpath="//*[@id='flight-search-airasia-button-inner-button-select-flight-heatmap']") WebElement Search_Button;
	public static @FindBy(xpath="//*[@class='calendar-button']") WebElement Confirm_Button;
	public static @FindBy(xpath="//*[@id=\"header\"]/div/div[8]/span") WebElement Login_Button;
	
	public SearchFlightPage() {
		super();
	}
	
	public static SearchFlightPage verifyTitle() throws Exception{
		isDisplayed(Title);
		String text = getText(Title);
		if(text.contains("1.9 Million Promo Seats!")) {
			System.out.println("Title is displaying");
		}else {
			System.out.println("Title is not displaying");
		}
		return new SearchFlightPage();
	}

	public static SearchFlightPage getSearchFlightPage() throws Exception{
		driver.get("https://www.airasia.com/en/home.page");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return new SearchFlightPage();
	}
	
	public static SearchFlightPage enterOriginCity(String city) throws Exception{
		enterValue(OriginCity_EditBox, city);
		return new SearchFlightPage();
		
	}
	
	public static SearchFlightPage enterDestinationCity(String city) throws Exception{
		enterValue(DestinationCity_EditBox, city);
		return new SearchFlightPage();
		
	}
	
	public static SearchFlightPage clickOnDepartureDate() throws Exception{
		clickElement(DepartureDate);
		return new SearchFlightPage();
	}
	
	public static SearchFlightPage clickOnOneWayRadioButton() throws Exception{
		clickElement(Oneway_RadioButton);
		return new SearchFlightPage();
	}
	
	public static SearchFlightPage clickOnSearchButton() throws Exception{
		clickElement(Search_Button);
		return new SearchFlightPage();
	}
	
	public static SearchFlightPage clickOnConfirmButton() throws Exception{
		clickElement(Confirm_Button);
		return new SearchFlightPage();		
	}

	public static SearchFlightPage scrollDownToConfirmButton() throws Exception {
		scrollDownPageToElement(Confirm_Button);
		return new SearchFlightPage();		
	}

	public static LoginPage clickOnLoginButton() throws Exception{
		clickElement(Login_Button);
		return new LoginPage();
	}
}
