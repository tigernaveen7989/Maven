package com.shell.markethub.ecommerce.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReviewAndPlaceOrderPage extends eCommerceBasePageObject{

	public ReviewAndPlaceOrderPage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	private static final Logger logger = Logger.getLogger(ReviewAndPlaceOrderPage.class);
	@FindBy(xpath = "//h2[text()='Review & Place Order']")
	WebElement reviewAndPlaceOrderTitle;
	@FindBy(xpath = "//div[contains(@class,'stick')]//button[contains(@class,'shell-font-Futura')][contains(text(),'Place Order')]")
	WebElement placeOrderButton;
	@FindBy(xpath = "//span[contains(@text,'Save Order Modal')]")
	WebElement saveButton;
	@FindBy(xpath = "//b[contains(text(),'Cancel Order')]")
	WebElement cancelOrderButton;
	@FindBy(xpath = "//a[contains(text(),'Add more products')]")
	WebElement addMoreProductsLink;
	
	public String getPageTitle() {
		return getText(reviewAndPlaceOrderTitle);
	}
	
	public void clickOnPlaceOrderButton() throws Exception {
		click(placeOrderButton);
	}
	
	public void clickOnCancelButton() throws Exception {
		click(cancelOrderButton);
	}
	
	public void clickOnSaveButton() throws Exception {
		click(saveButton);
	}
	
	public void clickOnAddMoreProductsLink() throws Exception {
		click(addMoreProductsLink);
	}
}
