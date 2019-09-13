package com.shell.markethub.uam.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TransactRolesPage extends UAMBasePageObject{

	public TransactRolesPage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(TransactRolesPage.class);
	@FindBy(xpath = "//input[@id='productDocument1']")
	WebElement productDocumentsCheckbox;
	@FindBy(xpath = "//input[@id='ehaulReport1']")
	WebElement confirmedShipmentReportCheckbox;
	@FindBy(xpath = "//button[@id='ehaulierconfigureUserTransactRolesNext']")
	WebElement nextButton;
	
	public void clickOnProductDocumentsCheckbox() throws Exception {
		click(productDocumentsCheckbox);
	}
	
	public void clickOnConfirmedShipmentReportCheckbox() throws Exception {
		click(confirmedShipmentReportCheckbox);
	}
	
	public void clickOnNextButton() throws Exception {
		click(nextButton);
	}
}
