package com.shell.markethub.usfuels.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.shell.markethub.integration.pageobjects.HomePage;

public class VolumesEntryPage extends USFuelsBasePageObject{

	public VolumesEntryPage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(VolumesEntryPage.class);
	@FindBy(xpath = "//h1[contains(text(),'Volumes Entry')]")
	WebElement volumesEntryTitle;
	@FindBy(xpath = "//div[@class='inputBox']//select")
	WebElement soldToDropdown;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock month']//select")
	WebElement monthDropdown;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock year']//select")
	WebElement yearDropdown;
	@FindBy(xpath = "//button[@id='volumeEntrySearch']")
	WebElement searchButton;
	@FindBy(xpath = "//button[@class='primary submitButton']")
	WebElement submitButton;
	@FindBy(xpath = "//button[@class='secondary volumeCancel']")
	WebElement cancelButton;
	@FindBy(xpath = "//button[@class='secondary excelUploadButton']")
	WebElement excelUploadButton;
	@FindBy(xpath = "//button[@class='secondary downloadButton']")
	WebElement downloadButton;
	@FindBy(xpath = "//a[@class='volumeSampleExcelLink']")
	WebElement sampleExcelLink;
	@FindBy(xpath = "//table[@id='testTable']")
	WebElement volumesEntryTable;
	
	public String verifyVolumesEntryTitle() throws Exception {
		return getText(volumesEntryTitle);
	}
	
	public Boolean verifySoldToDropdown() throws Exception {
		return isDisplayed(soldToDropdown);
	}
	
	public Boolean verifyMonthDropdown() throws Exception {
		return isDisplayed(monthDropdown);
	}
	
	public Boolean verifyYearDropdown() throws Exception {
		return isDisplayed(yearDropdown);
	}
	
	public Boolean verifySearchButton() throws Exception {
		return isDisplayed(searchButton);
	}
	
	public Boolean verifySubmitButton() throws Exception {
		return isDisplayed(submitButton);
	}
	
	public Boolean verifyCancelButton() throws Exception {
		return isDisplayed(cancelButton);
	}
	
	public Boolean verifyExcelUploadButton() throws Exception {
		return isDisplayed(excelUploadButton);
	}
	
	public Boolean verifyDownloadButton() throws Exception {
		return isDisplayed(downloadButton);
	}
	
	public Boolean verifySampleExcelLink() throws Exception {
		return isDisplayed(sampleExcelLink);
	}
	
	public Boolean verifyVolumesEntryTable() throws Exception {
		return isDisplayed(volumesEntryTable);
	}
}
