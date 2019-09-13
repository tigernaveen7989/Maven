package com.shell.markethub.usfuels.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InvoicedVolumesPage extends USFuelsBasePageObject{

	public InvoicedVolumesPage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(InvoicedVolumesPage.class);
	@FindBy(xpath = "//body/div/div[@id='pageWrapper']/div[@class='grid']/div[@class='us-fuels main col-full-width multiDropFlow reportDropFlow']/h1[1]")
	WebElement invoicedVolumesTitle;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock soldToComp']//button[@class='ms-choice']")
	WebElement companyDropdown;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock dhTerminal']//button[@class='ms-choice']")
	WebElement soldToDropdown;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock dhCarrier']//button[@class='ms-choice']")
	WebElement shipToDropdown;
	@FindBy(xpath = "//button[@class='primary terminalButton searchButton onLoadResultButton']")
	WebElement searchButton;
	@FindBy(xpath = "//span[contains(text(),'Show filters')]")
	WebElement showFiltersLink;
	@FindBy(xpath = "//button[@class='secondary downloadInvoicedVolumeButton']")
	WebElement downloadButton;
	@FindBy(xpath = "//div[@class='volumeLiftReportResultsDiv']")
	WebElement invoicedVolumesGraph;
	
	public String verifyTitle() throws Exception {
		return getText(invoicedVolumesTitle);
	}
	
	public Boolean verifyCompanyDropdown() throws Exception {
		return isDisplayed(companyDropdown);
	}
	
	public Boolean verifyShipToDropdown() throws Exception {
		return isDisplayed(shipToDropdown);
	}
	
	public Boolean verifySoldToDropdown() throws Exception {
		return isDisplayed(soldToDropdown);
	}
	
	public Boolean verifySearchButton() throws Exception {
		return isDisplayed(searchButton);
	}
	
	public Boolean verifyShowFiltersLink() throws Exception {
		Thread.sleep(5000);
		return isDisplayed(showFiltersLink);
	}
	
	public Boolean verifyDownloadButton() throws Exception {
		Thread.sleep(2000);
		return isDisplayed(downloadButton);
	}
	
	public Boolean verifyInvoicedVolumesTable() throws Exception {
		return isDisplayed(invoicedVolumesGraph);
	}
}
