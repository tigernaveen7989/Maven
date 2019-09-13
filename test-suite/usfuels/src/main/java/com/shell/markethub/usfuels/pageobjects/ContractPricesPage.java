package com.shell.markethub.usfuels.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.shell.markethub.integration.pageobjects.HomePage;

public class ContractPricesPage extends USFuelsBasePageObject{

	public ContractPricesPage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(ContractPricesPage.class);
	@FindBy(xpath = "//div[@class='main col-full-width']//h1[contains(text(),'Contract Prices')]")
	WebElement contractPricesTitle;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock soldToComp']//button[@class='ms-choice']")
	WebElement soldToDropdown;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock soldToComp']//li[2]//label[1]//input[1]")
	WebElement soldToDropDownValue1;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock dhTerminal']//button[@class='ms-choice']")
	WebElement terminalDropdown;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock dhTerminal']//li[2]//label[1]//input[1]")
	WebElement terminalDropdownValue1;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock dhCarrier']//button[@class='ms-choice']")
	WebElement productDropdown;
	@FindBy(xpath = "//input[@data-name='selectItemproducts'][@value='400007405'][@data-parent='{0,0}']")
	WebElement productDropdownValueSHMV2DS15PPMValue;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock fromDate']//span[@class='icon-calendar']")
	WebElement fromCalenderIcon;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock toDate']//span[@class='icon-calendar']")
	WebElement toCalenderIcon;
	@FindBy(xpath = "//button[@class='primary submitButton onLoadResultButton']")
	WebElement searchButton;
	@FindBy(xpath = "//button[@class='secondary downloadButton']")
	WebElement downloadButton;
	@FindBy(xpath = "//span[contains(text(),'Add / Remove columns')]")
	WebElement addRemoveColumnsButton;
	@FindBy(xpath = "//table[@id='testTable']")
	WebElement contractPricesTable;
	
	public String getTitle() throws Exception {
		return getText(contractPricesTitle);
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
	
	public Boolean verifyFromCalenderIcon() throws Exception {
		return isDisplayed(fromCalenderIcon);
	}
	
	public Boolean verifyToCalenderIcon() throws Exception {
		return isDisplayed(toCalenderIcon);
	}
	
	public Boolean verifyDownloadButton() throws Exception {
		return isDisplayed(downloadButton);
	}
	
	public Boolean verifyAddRemoveColumnsButton() throws Exception {
		return isDisplayed(addRemoveColumnsButton);
	}
	
	public void clickOnSearchButton() throws Exception {
		click(searchButton);
	}
	
	public Boolean verifySearchButton() throws Exception {
		return isDisplayed(searchButton);
	}
	
	public Boolean verifyContractPricesTable() throws Exception {
		return isDisplayed(contractPricesTable);
	}
}
