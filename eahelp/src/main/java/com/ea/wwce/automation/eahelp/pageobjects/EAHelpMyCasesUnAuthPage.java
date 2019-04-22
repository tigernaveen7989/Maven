package com.ea.wwce.automation.eahelp.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * 
 * @author sadabala
 *
 */
public class EAHelpMyCasesUnAuthPage extends EAHelpBasePageObject {

	public EAHelpMyCasesUnAuthPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	
	public static Logger logger = Logger.getLogger(EAHelpMyCasesPage.class);
	
	
	@FindBy(css = "input[name='caseid']")
	WebElement txtCaseID;
	@FindBy(css = "input[name='secret']")
	WebElement txtSecrectKey;
	@FindBy(css = ".submit")
	WebElement btnSubmit;

	// ---------------------------------------------------------------------------------------

	// Method to access case details from my cases unauth page
	public void accessCaseDetailsFromUnAuthMyCasesPage(String caseID, String secrtKey) {
		logger.info("Method to access case details from my cases unauth page");
		this.sendKeys(txtCaseID, caseID);
		this.sendKeys(txtSecrectKey, secrtKey);
		this.click(btnSubmit);
	}

	
	
}
