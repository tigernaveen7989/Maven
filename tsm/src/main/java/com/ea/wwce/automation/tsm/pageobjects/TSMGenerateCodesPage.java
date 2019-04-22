package com.ea.wwce.automation.tsm.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;

/**
 * 
 * @author rgandham
 * @description This class consists of Page objects and Functions of Generate
 *              Codes page in TSM application.
 */

public class TSMGenerateCodesPage extends TSMBasePageObject {

	public TSMGenerateCodesPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(TSMGenerateCodesPage.class);

	@FindBy(xpath = "(//li[@title='ACCOUNT']//ancestor::div[@class='slds-tabs_scoped']//lightning-icon[contains(@class,'circle badge-red')])[2]")
	WebElement accountTFA;
	@FindBy(xpath = "(//li[@title='ACCOUNT']//ancestor::div[@class='slds-tabs_scoped']//span[@class='slds-checkbox_faux'])[2]")
	WebElement accountLoginVerificationSwitch;
	@FindBy(xpath = "(//div[text()='Backup Codes'])[2]")
	WebElement accountBackupCodes;
	@FindBy(xpath = "(//li[@title='ACCOUNT']//ancestor::div[@class='slds-tabs_scoped']//div[text()='Login Verification'])[2]")
	WebElement accountLoginVerificationPopup;
	@FindBy(xpath = "(//li[@title='OVERVIEW']//ancestor::div[@class='slds-tabs_scoped']//lightning-icon[contains(@class,'badge-red')])[2]")
	WebElement overviewTFA;
	@FindBy(xpath = "(//li[@title='OVERVIEW']//ancestor::div[@class='slds-tabs_scoped']//span[@class='slds-checkbox_faux'])[1]")
	WebElement overviewLoginVerificationSwitch;
	@FindBy(xpath = "(//div[text()='Backup Codes'])[1]")
	WebElement overviewBackupCodes;
	@FindBy(xpath = "(//div[text()='Login Verification'])[1]")
	WebElement overviewLoginVerificationPopup;
	@FindBy(xpath = "(//div[contains(@class,'slds-p-top_small')])[1]")
	WebElement backupcodes;
	
	//Verify Account tab Backup Codes
	@Step("Verify Backup Codes")
	public boolean verifyAccountBackupCodes() {
		boolean isTrue = false;
		logger.info("Verify Backup Codes");
		try {
			//if (this.isElementVisible(accountLoginVerificationPopup, 10))
				this.waitForElementToBeVisible(accountTFA, 10);
				this.click(accountTFA);
				this.click(accountLoginVerificationSwitch);
				isTrue = this.isElementVisible(accountBackupCodes, 10);
				this.click(accountLoginVerificationSwitch);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the " + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the elemnt within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Backup Codes are not available in Account tab" + e);
		}
		return isTrue;
	}
	

	//Verify Overview tab Backup Codes
	@Step("Verify Backup Codes")
	public boolean verifyOverviewBackupCodes() {
		boolean isTrue = false;
		logger.info("Verify Backup Codes");
		try {
			
				this.waitForElementToBeVisible(overviewTFA, 10);
				this.click(overviewTFA);
				if (this.isElementVisible(overviewLoginVerificationPopup, 10))
				this.click(overviewLoginVerificationSwitch);
				isTrue = this.isElementVisible(overviewBackupCodes, 10);
				this.click(overviewLoginVerificationSwitch);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the " + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the elemnt within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Backup Codes are not available in Overview tab" + e);
		}
		return isTrue;
	}
}
