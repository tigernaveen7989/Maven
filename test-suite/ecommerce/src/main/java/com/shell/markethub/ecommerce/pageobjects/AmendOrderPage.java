package com.shell.markethub.ecommerce.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmendOrderPage extends eCommerceBasePageObject{

	public AmendOrderPage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	private static final Logger logger = Logger.getLogger(AmendOrderPage.class);
	@FindBy(xpath = "//h3[@class='heading']")
	WebElement amendOrderTitle;
	@FindBy(xpath = "//input[@id='updateOrderButton']")
	WebElement submitChangesButton;
	@FindBy(xpath = "//textarea[@id='deliveryInstruction']")
	WebElement deliveryInstructionsEditbox;
	@FindBy(xpath = "//textarea[@id='driverNotes']")
	WebElement driverInstructionsEditbox;
	@FindBy(xpath = "//div[@class='addMoreProduct']//a[@class='placeOrderButtonBelow'][contains(text(),'Add new items')]")
	WebElement addNewItemsLink;
	
	public String getPageTitle() {
		return getText(amendOrderTitle);
	}
	
	public void enterDeliveryInstructions(String deliveryInstructions) throws Exception{
		sendKeys(deliveryInstructionsEditbox, deliveryInstructions);
	}
	
	public void enterDriverInstructions(String driverInstructions) throws Exception{
		sendKeys(driverInstructionsEditbox, driverInstructions);
	}
	
	public void clickOnSubmitChangesButton() throws Exception{
		click(submitChangesButton);
	}
	
	public void clickOnAddNewItemsLink() throws Exception {
		click(addNewItemsLink);
	}
}
