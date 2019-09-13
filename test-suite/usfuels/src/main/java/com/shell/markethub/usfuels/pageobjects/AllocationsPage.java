package com.shell.markethub.usfuels.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AllocationsPage extends USFuelsBasePageObject{

	public AllocationsPage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(AllocationsPage.class);
	@FindBy(xpath = "//h1[contains(text(),'Allocations')]")
	WebElement allocationsTitle;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock dhTerminal']//button[@class='ms-choice']")
	WebElement terminalDropdown;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock soldToComp']//button[@class='ms-choice']")
	WebElement soldToDropdown;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock dhCarrier']//button[@class='ms-choice']")
	WebElement productDropdown;
	@FindBy(xpath = "//button[@class='primary terminalButton onLoadResultButton']")
	WebElement searchButton;
	@FindBy(xpath = "//button[@class='primary']")
	WebElement requestLoadButton;
	@FindBy(xpath = "//button[@class='secondary downloadButton']")
	WebElement downloadButton;
	@FindBy(xpath = "//span[contains(text(),'Add / Remove columns')]")
	WebElement addRemoveColumnsButton;
	@FindBy(xpath = "//span[contains(text(),'Show Filters')]")
	WebElement showFiltersLink;
	@FindBy(xpath = "//table[@id='testTable']")
	WebElement allocationsTable;
	@FindBy(xpath = "//div[@class='disclaimerNote']")
	WebElement disclaimerNote;
	
	public String verifyTitle() throws Exception{
		return getText(allocationsTitle);
	}
	
	public Boolean verifySoldToDropdown() throws Exception {
		return isDisplayed(soldToDropdown);
	}
	
	public Boolean verifyTerminalDropdown() throws Exception {
		return isDisplayed(terminalDropdown);
	}
	
	public Boolean verifyProductDropdown() throws Exception {
		return isDisplayed(productDropdown);
	}
	
	public Boolean verifyRequestLoadButton() throws Exception {
		Thread.sleep(5000);
		return isDisplayed(requestLoadButton);
	}
	
	public Boolean verifyAddRemoveColumnsButton() throws Exception {
		return isDisplayed(addRemoveColumnsButton);
	}
	
	public Boolean verifyDownloadButton() throws Exception {
		return isDisplayed(downloadButton);
	}
	
	public Boolean verifyDisclaimerNote() throws Exception {
		return isDisplayed(disclaimerNote);
	}
	
	public Boolean verifyAllocationsTable() throws Exception {
		return isDisplayed(allocationsTable);
	}
	
	public Boolean verifyShowFiltersLink() throws Exception {
		return isDisplayed(showFiltersLink);
	}
	
	public Boolean verifySearchButton() throws Exception {
		return isDisplayed(searchButton);
	}
}
