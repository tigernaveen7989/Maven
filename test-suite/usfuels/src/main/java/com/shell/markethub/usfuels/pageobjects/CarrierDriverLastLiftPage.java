package com.shell.markethub.usfuels.pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.shell.markethub.integration.pageobjects.HomePage;

public class CarrierDriverLastLiftPage extends USFuelsBasePageObject{

	public CarrierDriverLastLiftPage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(CarrierDriverLastLiftPage.class);
	@FindBy(xpath = "//div[@class='main col-full-width']//h1[contains(text(),'Carrier Driver Last Lift')]")
	WebElement carrierDriverLastLiftTitle;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock soldToComp']//button[@class='ms-choice']")
	WebElement soldToDropdown;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock soldToComp']//li[2]//label[1]//input[1]")
	WebElement soldToDropDownValue1;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock dhTerminal']//button[@class='ms-choice']")
	WebElement terminalDropdown;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock dhTerminal']//li[51]//label[1]//input[1]")
	WebElement terminalDropdownValue1;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock dhCarrier']//button[@class='ms-choice']")
	WebElement carrierDropdown;
	@FindBy(xpath = "//input[@data-name='selectItemcarriers'][@value='CMNO']")
	WebElement carrierDropdownValueCMNO;
	@FindBy(xpath = "//button[@class='primary saveButton searchButton onLoadResultButton']")
	WebElement searchButton;
	@FindBy(xpath = "//span[@class='icon-download']")
	WebElement downloadButton;
	@FindBy(id="driverhistoryrowCount")
	WebElement carrierDriverLastLiftRowCount;
	@FindBy(xpath="//*[@id='testTable']/tbody/tr")
	List<WebElement> carrierDriverLastLiftTableRow;
	@FindBy(xpath="//*[@id='testTable']/thead/tr/th")
	List<WebElement> tableHead;
	@FindBy(id="radioDoc3")
	WebElement csvRadioButton;
	@FindBy(xpath="//button[@class='primary popup-custom-close downloadButton']")
	WebElement downloadButton1;
	@FindBy(xpath="//button[@class='Secondary popup-custom-close']")
	WebElement cancelButton;
	@FindBy(xpath = "//span[contains(text(),'Add / Remove columns')]")
	WebElement addRemoveColumnsButton;
	@FindBy(xpath = "//table[@id='testTable']")
	WebElement carrierDriverLastLiftTable;
	By downloadHeader = By.xpath("//section[@class='lightboxHeader']");
	
	
	public String getTitle() throws Exception {
		return getText(carrierDriverLastLiftTitle);
	}
	
	public Boolean verifySoldToDropdown() throws Exception {
		return isDisplayed(soldToDropdown);
	}
	
	public Boolean verifyTerminalDropdown() throws Exception {
		return isDisplayed(terminalDropdown);
	}
	
	public Boolean verifyCarrierDropdown() throws Exception {
		return isDisplayed(carrierDropdown);
	}
	
	public Boolean verifyDownloadButton() throws Exception {
		return isDisplayed(downloadButton);
	}
	
	public Boolean verifySearchButton() throws Exception {
		return isDisplayed(searchButton);
	}
	
	public Boolean verifyAddRemoveColumnsButton() throws Exception {
		return isDisplayed(addRemoveColumnsButton);
	}
	
	public Boolean verifyCarrierDriverLastLiftTable() throws Exception {
		return isDisplayed(carrierDriverLastLiftTable);
	}
	
	public void clickOnTerminalDropDown() throws Exception{
		click(terminalDropdown);
	}
	
	public void selectSoldToDropdown() throws Exception {
		click(soldToDropdown);
		click(soldToDropDownValue1);
	}
	
	public String clickOnTerminalCheckbox1() throws Exception {
		click(terminalDropdownValue1);
		return getAttributeValue(terminalDropdownValue1, "value");
	}
	
	public void selectCarrierDropdown() throws Exception {
		click(carrierDropdown);
		click(carrierDropdownValueCMNO);
	}
	
	public void clickOnSearchButton() throws Exception {
		click(searchButton);
	}
	
	public String verifyCarrierDriverLastLiftRowCount() throws Exception{
		return getText(carrierDriverLastLiftRowCount);
	}
	
	public void verifyTerminalColumnValues(String checkBoxValue) throws Exception{
		int tableColumnCount = tableHead.size();
		for(int i=0; i<tableColumnCount; i++) {
			if(tableHead.get(i).getText().contains("Terminal")) {
				for(int j=0; j<carrierDriverLastLiftTableRow.size()-1; j++) {
					if (!carrierDriverLastLiftTableRow.get(j).findElement(By.xpath("td[" + (i + 1) + "]")).getText()
							.contains(checkBoxValue)) {
						Assert.assertFalse(true);
					}	 
				}
				break;
			}
		}
	}
	
	public Boolean verifyDownloadPopup() throws Exception {
		return isElementPresent(downloadHeader, 10);
	}
	
	public void clickOnDownloadButton() throws Exception{
		click(downloadButton);
	}
	
	public void clickOnDownloadButton1() throws Exception{
		click(downloadButton1);
	}
	
	public void clickOnCancelButton() throws Exception{
		click(cancelButton);
	}
}
