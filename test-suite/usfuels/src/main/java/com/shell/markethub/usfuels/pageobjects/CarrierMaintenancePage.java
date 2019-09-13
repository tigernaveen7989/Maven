package com.shell.markethub.usfuels.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.shell.markethub.integration.pageobjects.HomePage;

public class CarrierMaintenancePage extends USFuelsBasePageObject{

	public CarrierMaintenancePage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(CarrierMaintenancePage.class);
	@FindBy(xpath = "//h1[contains(text(),'Carrier Maintenance')]")
	WebElement carrierMaintenanceTitle;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock soldToComp1']//select")
	WebElement terminalDropdown;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock newCarrier']//button[@class='ms-choice']")
	WebElement carrierDropdown;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock dhTerminal1']//button[@class='ms-choice']")
	WebElement soldToDropdown;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock dhCarrier1']//button[@class='ms-choice']")
	WebElement shipToDropdown;
	@FindBy(xpath = "//button[@class='primary terminalButton']")
	WebElement searchButton;
	@FindBy(id = "addRadio")
	WebElement addRadioButton;
	@FindBy(id = "removeRadio")
	WebElement removeRadioButton;
	@FindBy(xpath = "//table[@id='testTable']")
	WebElement carrierMaintenanceTable;
	String carrierDropdownValue = "//input[@value ='#']";
	String soldToDropdownValue = "//input[contains(@data-soldto-count,'#')][contains(@value ,'#')]";
	
	public String verifyCarrierMaintenanceTitle() throws Exception {
		return getText(carrierMaintenanceTitle);
	}
	
	public Boolean verifyTerminalDropdown() throws Exception {
		return isDisplayed(terminalDropdown);
	}
	
	public Boolean verifyCarrierDropdown() throws Exception {
		return isDisplayed(carrierDropdown);
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
	
	public Boolean verifyAddRadioButton() throws Exception {
		return isDisplayed(addRadioButton);
	}
	
	public Boolean verifyRemoveRadioButton() throws Exception {
		return isDisplayed(removeRadioButton);
	}
	
	public Boolean verifyCarrierMaintenanceTable() throws Exception {
		return isDisplayed(carrierMaintenanceTable);
	}
	
	public void selectTerminalDropdown(String terminal) throws Exception {
		selectVisibleText(terminalDropdown, terminal);
	}
	
	public void selectCarrierDropdown(String carrier) throws Exception {
		click(carrierDropdown);
		WebElement carrierDropdownValue1 = findDynamicElement(carrierDropdownValue, carrier);
		click(carrierDropdownValue1);
	}
	
	public void selectSoldToDropdown(String soldTo, String terminal) throws Exception {
		click(soldToDropdown);
		WebElement soldToDropdownValue1 = findDynamicElements(soldToDropdownValue, terminal, soldTo);
		click(soldToDropdownValue1);
	}
	
	public void clickOnSearchButton() throws Exception {
		click(searchButton);
	}
}
