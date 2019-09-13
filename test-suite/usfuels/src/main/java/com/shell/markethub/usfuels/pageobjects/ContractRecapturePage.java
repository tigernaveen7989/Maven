package com.shell.markethub.usfuels.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.shell.markethub.integration.pageobjects.HomePage;

public class ContractRecapturePage extends USFuelsBasePageObject{

	public ContractRecapturePage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(ContractRecapturePage.class);
	@FindBy(xpath = "//div[@class='main col-full-width ng-scope']//h1[contains(text(),'Contract Recapture')]")
	WebElement contractRecaptureTitle;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock soldToComp']//button[@class='ms-choice']")
	WebElement soldToDropdown;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock dhTerminal']//button[@class='ms-choice']")
	WebElement shipToDropdown;
	@FindBy(xpath = "//span[@class='icon-calendar']")
	WebElement terminalDateIcon;
	@FindBy(xpath = "//button[@class='primary addButton']")
	WebElement addButton;
	@FindBy(xpath = "//button[contains(text(),'Calculate')]")
	WebElement calculateButton;
	@FindBy(xpath = "//button[contains(text(),'Refresh')]")
	WebElement refreshButton;
	@FindBy(xpath = "//div[@id='pageWrapper']//table[1]")
	WebElement contractRecaptureTable;
	
	String soldToValue = "//input[contains(@value,'#')]";
	String shipToValue = "//input[contains(@value,'#')]";
	
	public String verifyContractRecaptureTitle() throws Exception {
		return getText(contractRecaptureTitle);
	}
	
	public Boolean verifySoldToDropdown() throws Exception {
		return isDisplayed(soldToDropdown);
	}
	
	public Boolean verifyShipToDropdown() throws Exception {
		return isDisplayed(shipToDropdown);
	}
	
	public Boolean verifyTerminalDateIcon() throws Exception {
		return isDisplayed(terminalDateIcon);
	}
	
	public Boolean verifyAddButton() throws Exception {
		return isDisplayed(addButton);
	}
	
	public Boolean verifyCalculateButton() throws Exception {
		return isDisplayed(calculateButton);
	}
	
	public Boolean verifyRefreshButton() throws Exception {
		return isDisplayed(refreshButton);
	}
	
	public Boolean verifyContractRecaptureTable() throws Exception {
		return isDisplayed(contractRecaptureTable);
	}
	
	public void clickOnAddButton() throws Exception {
		click(addButton);
	}
	
	public void selectSoldToDropdown(String soldTo) throws Exception {
		click(soldToDropdown);
		WebElement soldTo1 = findDynamicElement(soldToValue, soldTo);
		click(soldTo1);
	}
	
	public void selectShipToDropdown(String shipTo) throws Exception {
		click(shipToDropdown);
		WebElement shipTo1 = findDynamicElement(shipToValue, shipTo);
		click(shipTo1);
	}
}
