package com.ea.wwce.automation.tsm.pageobjects;

import org.apache.log4j.Logger;
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
 * @description This class consists of Page objects and Functions of Account
 *              notes page in TSM applicatio.
 * 
 */

public class TSMAddAccountNotesPage extends TSMBasePageObject {

	public TSMAddAccountNotesPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(TSMAddAccountNotesPage.class);

	@FindBy(css = "li[title='Account Notes']")
	WebElement accountNotes;
	@FindBy(id = "accNoteClk")
	WebElement addAcctNotes;
	@FindBy(css = "textarea[name='notes']")
	WebElement notes;
	@FindBy(css = "button[title='Save']")
	WebElement btnSave;
	@FindBy(xpath = "(//*[name()='svg' and @data-key='pin'])[1]")
	WebElement pinNotes;
	@FindBy(xpath = "//span[contains(@class,'sticky')]")
	WebElement pinnednotes;
	@FindBy(css = "[data-key='pin']")
	WebElement unpinNotes;

	@FindBy(xpath = "(//div[contains(@class, 'slds-no-space note-text')]//p[@class='slds-m-horizontal_xx-small'])[1]")
	WebElement accountNotestext;

	@Step("Close the Account Notes tab")
	public void clkOnAccountNotes() {

		logger.info("Clik on Account notes");
		try {
			// this.waitForElementToBeVisible(accountNotes, 20);
			Thread.sleep(2000);
			this.waitForClickableElement(20, accountNotes);
			this.click(accountNotes);
			logger.info("Cliked on Account notes");
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("failed to click on Account Notes" + e);
		}
	}

	@Step("Adding Account notes to case")
	public void addAccountNotes(String txtNotes) {

		logger.info("Add Account Notes");
		try {
			// this.waitForElementToBeVisible(addAcctNotes, 20);
			this.waitForClickableElement(20, addAcctNotes);
			Thread.sleep(5000);
			this.click(addAcctNotes);
			// Thread.sleep(2000);
			this.waitForClickableElement(10, notes);
			this.sendKeys(notes, txtNotes);
			this.waitForClickableElement(10, btnSave);
			this.click(btnSave);
			Thread.sleep(2000);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Accounts notes not saved" + e);
		}
	}

	@Step("Get Account notes text")
	public String getAccNotes(String txtAccNotes) {

		String getaccountNotes = null;
		logger.info("get account notes");
		try {
			Thread.sleep(3000);
			this.waitForClickableElement(5, accountNotestext);
			getaccountNotes = this.getText(accountNotestext);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (NullPointerException e) {
			logger.warn("Element is null" + e);
		} catch (Exception e) {
			logger.warn("account notes text not fetched" + e);
		}
		return getaccountNotes;
	}

	@Step("Verify Pin Notes")
	public void pinNotes() {

		logger.info("pin account notes");
		try {
			this.waitForElementToBeVisible(pinNotes, 10);
			// Thread.sleep(5000);
			this.click(pinNotes);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Account notes not pinned" + e);
		}
	}

	@Step("Verify UnPin Notes")
	public void unPinNotes() {

		logger.info("Unpin account notes");
		try {
			// this.waitForElementToBeVisible(pinnednotes,10);
			Thread.sleep(3000);
			this.click(pinnednotes);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Account notes not Unpinned" + e);
		}
	}

	@Step("Verify Pinned Notes is present")
	public boolean ispinnednotespresent() {

		boolean isTrue = false;
		logger.info("Verify pinned notes are present");
		try {
			isTrue = this.isElementVisible(pinnednotes, 30);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Not able to find pinned notes" + e);
		}
		logger.info("pinned notes found : " + isTrue);
		return isTrue;
	}

	@Step("Verify UnPinned Notes is present")
	public boolean isUnpinnednotespresent() {

		boolean isTrue = false;
		logger.info("Verify Unpinned notes are present");
		try {
			Thread.sleep(2000);
			isTrue = this.isElementVisible(pinNotes, 30);
			// if(this.isElementVisible(pinNotes, 30));
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Not able to find Unpinned notes" + e);
			return false;
		}
		logger.info("Unpinned notes found : " + isTrue);
		return isTrue;
	}

}
