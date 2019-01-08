package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SelectFlightPage extends BasePage{

	public static @FindBy(xpath="//*[@class='fare-container']") WebElement fareCalender;
		
	public SelectFlightPage() {
		super();
	}
	
	public static SelectFlightPage verifyTitle() throws Exception{
		isDisplayed(fareCalender);
		return new SelectFlightPage();
	}
}
