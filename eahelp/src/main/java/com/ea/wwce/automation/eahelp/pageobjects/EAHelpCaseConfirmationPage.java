package com.ea.wwce.automation.eahelp.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.ea.wwce.automation.eahelp.config.EAHELPDataConstants;

/**
 * 
 * @author sadabala
 *
 */

public class EAHelpCaseConfirmationPage extends EAHelpBasePageObject {

	public EAHelpCaseConfirmationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(EAHelpCaseConfirmationPage.class);

	@FindBy(xpath = "//h3[contains(@class,'case-link')]/a")
	WebElement caseNumberLink;

	// Get case number
	public String getCaseNumber() {
		String caseNumber = null;

		logger.info("Get case number from case number link");
		try {
			this.waitForClickableElement(60, caseNumberLink);
			caseNumber = this.getText(caseNumberLink);
			logger.info("Get case number from case number link" + caseNumber);
		} catch (NoSuchElementException e) {
			logger.warn("Not able to find case number " + e.getMessage());
		} catch (Exception e) {
			logger.warn("Not able to find case number " + e.getMessage());
		}
		return caseNumber;

	}

	// Click on case number link
	public void clickOnCaseLink() {
		try {
			this.click(caseNumberLink);
		} catch (NoSuchElementException e) {
			logger.warn("Not able to find case number " + e.getMessage());
		} catch (Exception e) {
			logger.warn("Not able to find case number " + e.getMessage());
		}
	}

}
