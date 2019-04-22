package com.ea.wwce.automation.omega.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.base.pageobjects.BasePageObject;

import io.qameta.allure.Step;

public class OmegaCasesPage extends BasePageObject {

	private static final Logger logger = Logger.getLogger(OmegaAgentHomePage.class);

	public OmegaCasesPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".attachment-icon")
	WebElement attachmentIcon;
	@FindBy(css = ".filename.viewable")
	WebElement fileName;
	@FindBy(css = "#view-cases div.header > a > span")
	WebElement casesCogWheel;
	@FindBy(css = "div[id='view-cases'][style*='visible;'] .refresh-views")
	WebElement casesRefresh;

	// Dynamic locators
	String resumeCaseStatus = "//div[@case-number='#']/span[@class='status open']";
	String caseStatus = "//div[@case-number='#']/span[contains(@class,'status')]";

	@Step("click on cases refresh button")
	public void refreshCases() {
		logger.info("click on cases refresh button");
		this.waitForClickableElement(60, casesRefresh);
		this.click(casesRefresh);
	}

	public boolean verifyAttachementIsPresent() {
		boolean isTrue = false;
		logger.info("Verify case is attached with a file");
		try {
			this.waitForElementToBeVisible(attachmentIcon, 60);
			this.click(attachmentIcon);
			isTrue = this.isElementVisible(fileName, 10);
		} catch (WebDriverException e) {
			logger.warn("Unable to find attachment");
		} catch (Exception e) {
			logger.warn("Unable to find attachment");
		}
		return isTrue;
	}

	public boolean verifyCaseStatus(String caseNumber, String expected) {
		try {
			logger.info("Verify case status in omega");
			this.waitForElementToBeVisible(casesCogWheel, 60);
			String txt = this.getTextFromDynamicElement(resumeCaseStatus, caseNumber);
			return txt.trim().equalsIgnoreCase(expected);
		} catch (Exception e) {
			logger.info("Not able to find case status in omega");
			throw e;
		}
	}

	public boolean verifyCaseStatusUpdatedByAdvsior(String caseNumber, String expected) {
		try {
			logger.info("Verify case status in omega");
			this.waitForElementToBeVisible(casesCogWheel, 60);
			String txt = this.getTextFromDynamicElement(caseStatus, caseNumber);
			return txt.trim().equalsIgnoreCase(expected);
		} catch (Exception e) {
			logger.info("Not able to find case status in omega");
			throw e;
		}
	}

	public boolean isCaseLoaded() {
		logger.info("Verify case is loaded in omega");
		return this.isElementVisible(casesCogWheel, 60);

	}

}
