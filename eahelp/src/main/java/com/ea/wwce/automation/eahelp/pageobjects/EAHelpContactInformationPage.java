package com.ea.wwce.automation.eahelp.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.ea.wwce.automation.eahelp.config.EAHELPDataConstants;

import io.qameta.allure.Step;

/**
 * 
 * @author sadabala
 *
 */
public class EAHelpContactInformationPage extends EAHelpBasePageObject {

	public EAHelpContactInformationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(EAHelpContactInformationPage.class);

	@FindBy(id = "firstName")
	WebElement firstName;
	@FindBy(id = "lastName")
	WebElement lastName;
	@FindBy(id = "email")
	WebElement email;
	@FindBy(xpath = "//a[@title='Next']")
	WebElement nextButton;

	// Method to enter contact information
	@Step("Enter contact information")
	public void enterContactInformation(String fname, String lname, String emailId) {

		logger.info("Enter contact information ");
		try {
			this.waitForElementToBeVisible(firstName, EAHELPDataConstants.IMPLICIT_TIMEOUT);
			this.sendKeys(firstName, fname);
			this.sendKeys(lastName, lname);
			this.sendKeys(email, emailId);
			this.click(nextButton);
		} catch (NoSuchElementException e) {
			logger.warn("Failed to find the element " + e);

		} catch (TimeoutException e) {
			logger.warn("Failed due to timeout exception " + e);

		}

	}

}
