package com.shell.markethub.usfuels.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SupplyWorkspacePage extends USFuelsBasePageObject{

	public SupplyWorkspacePage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(SupplyWorkspacePage.class);
	@FindBy(xpath = "//h1[contains(text(),'Supply Workspace')]")
	WebElement supplyWorkspaceTitle;
	@FindBy(xpath = "//h2[contains(text(),'Allocations')]")
	WebElement allocationsWidgetTitle;
	@FindBy(xpath = "//section[@id='allocations']//div[@class='formRow formRowInlineBlock soldToComp']//button[@class='ms-choice']")
	WebElement allocationsSoldToDropdown;
	@FindBy(xpath = "//section[@id='allocations']//div[@class='formRow formRowInlineBlock dhTerminal']//button[@class='ms-choice']")
	WebElement allocationsTerminalDropdown;
	@FindBy(xpath = "//section[@id='allocations']//div[@class='formRow formRowInlineBlock dhCarrier']//button[@class='ms-choice']")
	WebElement allocationsProductDropdown;
	@FindBy(xpath = "//section[@id='allocations']//button[@class='primary saveButton searchButton onLoadResultButton'][contains(text(),'Search')]")
	WebElement allocationsSearchButton;
	@FindBy(xpath = "//section[@id='allocations']//span[contains(text(),'Show Filters')]")
	WebElement allocationsShowFiltersLink;
	@FindBy(xpath = "//section[@id='allocations']//a[@class='testing123 expandTableLink'][contains(text(),'Expand results')]")
	WebElement allocationsExpandResultsLink;
	@FindBy(xpath = "//button[@class='secondary downloadButton allocDownloadButton']")
	WebElement allocationsDownloadButton;
	@FindBy(xpath = "//section[@id='allocations']//a[@class='viewdetail icon-play icon-right-aligned'][contains(text(),'View detail')]")
	WebElement allocationsViewDetailLink;
	@FindBy(xpath = "//a[contains(text(),'Jump to TopTech website')]")
	WebElement allocationsJumpToTopTechLink;
	@FindBy(xpath = "//section[@id='allocations']//a[@class='refreshLInk icon-play2 icon-right-aligned'][contains(text(),'Refresh')]")
	WebElement allocationsRefreshLink;
	@FindBy(xpath = "//div[@class='resultsDiv']//table[@id='testTable']")
	WebElement allocationsWidgetTable;
	@FindBy(xpath = "//h2[contains(text(),'Rack prices')]")
	WebElement rackPricesWidgetTitle;
	@FindBy(xpath = "//div[@class='rackprice dex-basecomponent']//div[@class='tileHeader']//span[@class='icon-plus']")
	WebElement rackPricesExpandIcon;
	@FindBy(xpath = "//div[@class='rackprice dex-basecomponent']//div[@class='formRow formRowInlineBlock soldToComp']//button[@class='ms-choice']")
	WebElement rackPricesSoldToDropdown;
	@FindBy(xpath = "//div[@class='rackprice dex-basecomponent']//div[@class='formRow formRowInlineBlock dhTerminal']//button[@class='ms-choice']")
	WebElement rackPricesTerminalDropdown;
	@FindBy(xpath = "//button[@class='primary searchButton onLoadResultButton']")
	WebElement rackPricesSearchButton;
	@FindBy(xpath = "//div[@class='rackprice dex-basecomponent']//a[@class='testing123 expandTableLink'][contains(text(),'Expand results')]")
	WebElement rackPricesExpandResultsLink;
	@FindBy(xpath = "//div[@class='rackprice dex-basecomponent']//a[@class='icon-alone open-inline-popup-link downLink']")
	WebElement rackPricesDownloadButton;
	@FindBy(xpath = "//div[@class='rackprice dex-basecomponent']//span[contains(text(),'Show Filters')]")
	WebElement rackPricesShowFiltersLink;
	@FindBy(xpath = "//div[@class='rackprice dex-basecomponent']//a[@class='viewdetail icon-play icon-right-aligned'][contains(text(),'View detail')]")
	WebElement rackPricesViewDetailLink;
	@FindBy(xpath = "//div[@class='rackprice dex-basecomponent']//a[@class='refreshLInk icon-play2 icon-right-aligned'][contains(text(),'Refresh')]")
	WebElement rackPricesRefreshLink;
	@FindBy(xpath = "//div[@class='rackPriceWidgetResultsDiv']//table[@id='testTable']")
	WebElement rackPricesWidgetTable;
	@FindBy(xpath = "//h2[contains(text(),'Contract prices')]")
	WebElement contractPricesWidgetTitle;
	@FindBy(xpath = "//div[@class='contractPrice']//div[@class='tileHeader']//span[@class='icon-plus']")
	WebElement contractPricesExpandIcon;
	@FindBy(xpath = "//div[@class='contractPrice']//div[@class='formRow formRowInlineBlock soldToComp']//button[@class='ms-choice']")
	WebElement contractPricesSoldToDropdown;
	@FindBy(xpath = "//div[@class='contractPrice']//div[@class='formRow formRowInlineBlock dhTerminal']//button[@class='ms-choice']")
	WebElement contractPricesTerminalDropdown;
	@FindBy(xpath = "//section[@id='pricing']//button[@class='primary saveButton searchButton onLoadResultButton'][contains(text(),'Search')]")
	WebElement contractPricesSearchButton;
	@FindBy(xpath = "//div[@class='contractPrice']//a[@class='testing123 expandTableLink'][contains(text(),'Expand results')]")
	WebElement contractPricesExpandResultsLink;
	@FindBy(xpath = "//div[@class='contractPrice']//a[@class='icon-alone open-inline-popup-link downLink']")
	WebElement contractPricesDownloadButton;
	@FindBy(xpath = "//div[@class='contractPrice']//span[contains(text(),'Show Filters')]")
	WebElement contractPricesShowFiltersLink;
	@FindBy(xpath = "//div[@class='contractPrice']//a[@class='viewdetail icon-play icon-right-aligned'][contains(text(),'View detail')]")
	WebElement contractPricesViewDetailLink;
	@FindBy(xpath = "//div[@class='contractPrice']//a[@class='refreshLInk icon-play2 icon-right-aligned'][contains(text(),'Refresh')]")
	WebElement contractPricesRefreshLink;
	@FindBy(xpath = "//div[@class='contractPriceWidgetResultsDiv']//table[@id='testTable']")
	WebElement contractPricesWidgetTable;
	@FindBy(xpath = "//h2[contains(text(),'BOLs')]")
	WebElement bolsWidgetTitle;
	@FindBy(xpath = "//section[@id='bols']//div[@class='tileHeader']//span[@class='icon-plus']")
	WebElement bolsExpandIcon;
	@FindBy(xpath = "//section[@id='bols']//div[@class='formRow formRowInlineBlock soldToComp']//button[@class='ms-choice']")
	WebElement bolsSoldToDropdown;
	@FindBy(xpath = "//section[@id='bols']//div[@class='formRow formRowInlineBlock dhTerminal']//button[@class='ms-choice']")
	WebElement bolsShipToDropdown;
	@FindBy(xpath = "//section[@id='bols']//div[@class='formRow formRowInlineBlock dhCarrier']//button[@class='ms-choice']")
	WebElement bolsTerminalDropdown;
	@FindBy(xpath = "//section[@id='bols']//button[@class='primary saveButton searchButton onLoadResultButton'][contains(text(),'Search')]")
	WebElement bolsSearchButton;
	@FindBy(xpath = "//section[@id='bols']//span[contains(text(),'Show Filters')]")
	WebElement bolsShowFiltersLink;
	@FindBy(xpath = "//section[@id='bols']//a[@class='viewdetail icon-play icon-right-aligned'][contains(text(),'View detail')]")
	WebElement bolsViewDetailLink;
	@FindBy(xpath = "//section[@id='bols']//a[@class='refreshLInk icon-play2 icon-right-aligned'][contains(text(),'Refresh')]")
	WebElement bolsRefreshLink;
	
	public String verifySupplyWorkspaceTitle() throws Exception {
		return getText(supplyWorkspaceTitle);
	}
	
	public String verifyAllocationsWidgetTitle() throws Exception {
		return getText(allocationsWidgetTitle);
	}
	
	public Boolean verifyAllocationsSoldToDropdown() throws Exception {
		return isDisplayed(allocationsSoldToDropdown);
	}
	
	public Boolean verifyAllocationsTerminalDropdown() throws Exception {
		return isDisplayed(allocationsTerminalDropdown);
	}
	
	public Boolean verifyAllocationsProductDropdown() throws Exception {
		return isDisplayed(allocationsProductDropdown);
	}
	
	public Boolean verifyAllocationsSearchButton() throws Exception {
		return isDisplayed(allocationsSearchButton);
	}
	
	public Boolean verifyAllocationsShowFiltersLink() throws Exception {
		return isDisplayed(allocationsShowFiltersLink);
	}
	
	public Boolean verifyAllocationsExpandResultsLink() throws Exception {
		return isDisplayed(allocationsExpandResultsLink);
	}
	
	public Boolean verifyAllocationsDownloadButton() throws Exception {
		return isDisplayed(allocationsDownloadButton);
	}
	
	public Boolean verifyAllocationsViewDetailLink() throws Exception {
		return isDisplayed(allocationsViewDetailLink);
	}
	
	public Boolean verifyAllocationsJumpToTopTechWebsiteLink() throws Exception {
		Thread.sleep(2000);
		return isDisplayed(allocationsJumpToTopTechLink);
	}
	
	public Boolean verifyAllocationsRefreshLink() throws Exception {
		return isDisplayed(allocationsRefreshLink);
	}
	
	public Boolean verifyAllocationsWidgetTable() throws Exception {
		return isDisplayed(allocationsWidgetTable);
	}
	
	public Boolean verifyContractPricesExpandIcon() throws Exception {
		return isDisplayed(contractPricesExpandIcon);
	}
	
	public void clickOnContractPricesExpandIcon() throws Exception {
		click(contractPricesExpandIcon);
	}
	
	public String verifyContractPricesWidgetTitle() throws Exception {
		return getText(contractPricesWidgetTitle);
	}
	
	public Boolean verifyContractPricesSoldToDropdown() throws Exception {
		return isDisplayed(contractPricesSoldToDropdown);
	}
	
	public Boolean verifyContractPricesTerminalDropdown() throws Exception {
		return isDisplayed(contractPricesTerminalDropdown);
	}
	
	public Boolean verifyContractPricesSearchButton() throws Exception {
		return isDisplayed(contractPricesSearchButton);
	}
	
	public Boolean verifyContractPricesShowFiltersLink() throws Exception {
		return isDisplayed(contractPricesShowFiltersLink);
	}
	
	public Boolean verifyContractPricesExpandResultsLink() throws Exception {
		return isDisplayed(contractPricesExpandResultsLink);
	}
	
	public Boolean verifyContractPricesDownloadButton() throws Exception {
		return isDisplayed(contractPricesDownloadButton);
	}
	
	public Boolean verifyContractPricesViewDetailLink() throws Exception {
		return isDisplayed(contractPricesViewDetailLink);
	}
	
	public Boolean verifyContractPricesRefreshLink() throws Exception {
		return isDisplayed(contractPricesRefreshLink);
	}
	
	public Boolean verifyContractPricesWidgetTable() throws Exception {
		return isDisplayed(contractPricesWidgetTable);
	}
	
	public Boolean verifyRackPricesExpandIcon() throws Exception {
		return isDisplayed(rackPricesExpandIcon);
	}
	
	public void clickOnRackPricesExpandIcon() throws Exception {
		click(rackPricesExpandIcon);
	}
	
	public String verifyRackPricesWidgetTitle() throws Exception {
		return getText(rackPricesWidgetTitle);
	}
	
	public Boolean verifyRackPricesSoldToDropdown() throws Exception {
		return isDisplayed(rackPricesSoldToDropdown);
	}
	
	public Boolean verifyRackPricesTerminalDropdown() throws Exception {
		return isDisplayed(rackPricesTerminalDropdown);
	}
	
	public Boolean verifyRackPricesSearchButton() throws Exception {
		return isDisplayed(rackPricesSearchButton);
	}
	
	public Boolean verifyRackPricesShowFiltersLink() throws Exception {
		return isDisplayed(rackPricesShowFiltersLink);
	}
	
	public Boolean verifyRackPricesExpandResultsLink() throws Exception {
		return isDisplayed(rackPricesExpandResultsLink);
	}
	
	public Boolean verifyRackPricesDownloadButton() throws Exception {
		return isDisplayed(rackPricesDownloadButton);
	}
	
	public Boolean verifyRackPricesViewDetailLink() throws Exception {
		return isDisplayed(rackPricesViewDetailLink);
	}
	
	public Boolean verifyRackPricesRefreshLink() throws Exception {
		return isDisplayed(rackPricesRefreshLink);
	}
	
	public Boolean verifyRackPricesWidgetTable() throws Exception {
		return isDisplayed(rackPricesWidgetTable);
	}
	
	public Boolean verifyBOLsExpandIcon() throws Exception {
		return isDisplayed(bolsExpandIcon);
	}
	
	public void clickOnBOLsExpandIcon() throws Exception {
		click(bolsExpandIcon);
	}
	
	public String verifyBOLsWidgetTitle() throws Exception {
		return getText(bolsWidgetTitle);
	}
	
	public Boolean verifyBOLsSoldToDropdown() throws Exception {
		return isDisplayed(bolsSoldToDropdown);
	}
	
	public Boolean verifyBOLsTerminalDropdown() throws Exception {
		return isDisplayed(bolsTerminalDropdown);
	}
	
	public Boolean verifyBOLsSearchButton() throws Exception {
		return isDisplayed(bolsSearchButton);
	}
	
	public Boolean verifyBOLsShowFiltersLink() throws Exception {
		return isDisplayed(bolsShowFiltersLink);
	}
	
	public Boolean verifyBOLsShipToDropdown() throws Exception {
		return isDisplayed(bolsShipToDropdown);
	}
	
	public Boolean verifyBOLsViewDetailLink() throws Exception {
		return isDisplayed(bolsViewDetailLink);
	}
	
	public Boolean verifyBOLsRefreshLink() throws Exception {
		return isDisplayed(bolsRefreshLink);
	}
}
