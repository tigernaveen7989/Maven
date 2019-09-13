package com.shell.markethub.usfuels.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SiteListPage extends USFuelsBasePageObject{

	public SiteListPage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(SiteListPage.class);
	@FindBy(xpath = "//div[@class='siteList']//h1[contains(text(),'Site List')]")
	WebElement siteListLitle;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock soldToComp']//button[@class='ms-choice']")
	WebElement soldToDropdown;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock dhTerminal']//button[@class='ms-choice']")
	WebElement shipToDropdown;
	@FindBy(xpath = "//button[@class='primary searchButton onLoadResultButton']")
	WebElement searchButton;
	@FindBy(xpath = "//button[@class='secondary downloadButton']")
	WebElement downloadButton;
	@FindBy(xpath = "//table[@id='testTable']")
	WebElement siteListTable;
	@FindBy(xpath = "//span[contains(text(),'Add / Remove columns')]")
	WebElement addRemoveColumnsButton;
	
	public String verifySiteListTitle() throws Exception {
		return getText(siteListLitle);
	}
	
	public Boolean verifySoldToDropdown() throws Exception {
		return isDisplayed(soldToDropdown);
	}
	
	public Boolean verifyShipToDropdown() throws Exception {
		return isDisplayed(shipToDropdown);
	}
	
	public Boolean verifySearchButton() throws Exception {
		return isDisplayed(searchButton);
	}
	
	public Boolean verifyDownloadButton() throws Exception {
		return isDisplayed(downloadButton);
	}
	
	public Boolean verifyAddRemoveColumnsButton() throws Exception {
		return isDisplayed(addRemoveColumnsButton);
	}
	
	public Boolean verifySiteListTable() throws Exception {
		return isDisplayed(siteListTable);
	}
}
