package com.shell.markethub.ecommerce.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PreviewPriceAndDeliveryDatesPage extends eCommerceBasePageObject{

	public PreviewPriceAndDeliveryDatesPage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	private static final Logger logger = Logger.getLogger(PreviewPriceAndDeliveryDatesPage.class);
	@FindBy(xpath = "//h3[@class='heading']")
	WebElement oldPreviewPriceAndDeliveryDatesTitle;
	@FindBy(xpath = "//input[@id='submitForPlaceOrder1']")
	WebElement oldPlaceOrderNowButton;
	
	public String getOldPageTitle() {
		return getText(oldPreviewPriceAndDeliveryDatesTitle);
	}
	
	public void oldClickOnPlaceOrderNowButton() throws Exception{
		click(oldPlaceOrderNowButton);
	}
}
