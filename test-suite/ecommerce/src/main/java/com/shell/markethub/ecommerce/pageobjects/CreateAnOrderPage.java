package com.shell.markethub.ecommerce.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateAnOrderPage extends eCommerceBasePageObject{
	
	public CreateAnOrderPage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	private static final Logger logger = Logger.getLogger(CreateAnOrderPage.class);
	@FindBy(xpath = "//h1[contains(text(),'Create an Order')]")
	WebElement oldCreateAnOrderTitle;
	@FindBy(xpath = "//input[@id='locationSearchTxt']")
	WebElement oldDeliveryLocationEditbox;
	@FindBy(xpath = "//td[@id='chooseButton1']//button[@class='primary pannelButton selectShipTo'][contains(text(),'Select')]")
	WebElement oldSelectButton;
	@FindBy(xpath = "//input[@id='packageTypeBulk']")
	WebElement oldBulkRadioButton;
	@FindBy(xpath = "//input[@id='poNumber']")
	WebElement oldPONumberEditbox;
	@FindBy(xpath  = "//textarea[@id='deliveryInstruction']")
	WebElement oldDeliveryInstructionsEditbox;
	@FindBy(xpath  = "//textarea[@id='driverInstruction']")
	WebElement oldDriverInstructionsEditbox;
	@FindBy(xpath = "//button[@class='primary mT16 processContract js_ordercontinueButton']")
	WebElement oldContinueButton;
	@FindBy(xpath = "//*[text()='Order Workspace']")
	WebElement oldOrderWorkspaceLink;
	
	
	public String getOldPageTitle() {
		return getText(oldCreateAnOrderTitle);
	}
	
	public void oldEnterDeliveryLocation(String deliveryLocation) throws Exception{
		sendKeys(oldDeliveryLocationEditbox, deliveryLocation);
		Thread.sleep(3000);
	}
	
	public void oldEnterDeliveryInstructions(String deliveryInstructions) throws Exception{
		sendKeys(oldDeliveryInstructionsEditbox, deliveryInstructions);
	}
	
	public void oldEnterDriverInstructions(String driverInstructions) throws Exception{
		sendKeys(oldDriverInstructionsEditbox, driverInstructions);
	}
	
	public void oldClickOnBulkRadioButton() throws Exception {
		click(oldBulkRadioButton);
	}
	
	public void oldClickOnContinueButton() throws Exception {
		click(oldContinueButton);
	}
	
	public void oldClickOnSelectButton() throws Exception {
		click(oldSelectButton);
	}
	
	public void oldClickOnOrderWorkspaceLink() throws Exception{
		click(oldOrderWorkspaceLink);
	}
}
