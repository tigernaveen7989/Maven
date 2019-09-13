package com.shell.markethub.usfuels.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.shell.markethub.integration.pageobjects.HomePage;

public class WhereAndWhatCanILiftPage extends USFuelsBasePageObject{

	public WhereAndWhatCanILiftPage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(WhereAndWhatCanILiftPage.class);
	@FindBy(xpath = "//div[@class='main col-full-width whereandwhat']//h1[contains(text(),'Where And What Can I Lift')]")
	WebElement whereAndWhatCanILiftTitle;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock soldToComp']//button[@class='ms-choice']")
	WebElement soldToDropdown;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock dhTerminal']//button[@class='ms-choice']")
	WebElement terminalDropdown;
	@FindBy(xpath = "//button[@class='primary searchButton onLoadResultButton']")
	WebElement searchButton;
	@FindBy(xpath = "//button[@class='secondary downloadButton']")
	WebElement downloadButton;
	@FindBy(xpath = "//div[@id='productsTab']")
	WebElement productsTab;
	@FindBy(xpath = "//div[@id='carriersTab']")
	WebElement carriersTab;
	@FindBy(xpath = "//div[@id='pinsTab']")
	WebElement PINsTab;
	@FindBy(xpath = "//div[@id='productTable']//table[@id='testTable']")
	WebElement whereAndWhatCanILiftTable;
	
	public String verifyWhereAndWhatCanILiftTitle() throws Exception {
		return getText(whereAndWhatCanILiftTitle);
	}
	
	public Boolean verifySoldToDropdown() throws Exception {
		return isDisplayed(soldToDropdown);
	}
	
	public Boolean verifyTerminalDropdown() throws Exception {
		return isDisplayed(terminalDropdown);
	}
	
	public Boolean verifySearchButton() throws Exception {
		return isDisplayed(searchButton);
	}
	
	public Boolean verifyDownloadButton() throws Exception {
		return isDisplayed(downloadButton);
	}
	
	public Boolean verifyProductsTab() throws Exception {
		return isDisplayed(productsTab);
	}
	
	public Boolean verifyCarriersTab() throws Exception {
		return isDisplayed(carriersTab);
	}
	
	public Boolean verifyPINsTab() throws Exception {
		return isDisplayed(PINsTab);
	}
	
	public Boolean verifyWhereAndWhatCanILiftTable() throws Exception {
		return isDisplayed(whereAndWhatCanILiftTable);
	}
}
