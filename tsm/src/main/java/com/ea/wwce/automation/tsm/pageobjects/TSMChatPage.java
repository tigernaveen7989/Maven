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
 * @description This class consists of Page objects and Functions of Chat page
 *              in TSM application.
 * 
 */

public class TSMChatPage extends TSMBasePageObject {

	public TSMChatPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(TSMChatPage.class);

	@FindBy(xpath = "//lightning-spinner[@class='slds-spinner_container slds-hide']/parent::div/parent::div/parent::div/parent::div[contains(@class,'cTSMChat')]")
	WebElement chatspinner;
	@FindBy(xpath = "(//div[contains(@class,'text_inbound')]//span)[1]")
	WebElement advisorChatmessage;
	@FindBy(xpath = "//textarea[contains(@class,'runtime_service_liveagentChatInput')]")
	WebElement SendMessagetxtbox;

	// Verify Chat spinner is displayed
	// @Step("Verify the Chat spinner")
	public void chatspinner() {

		try {
			logger.info("Verify Chat cases Spinner");
			this.waitForElementToBeVisible(chatspinner, 20);

		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info("Chat spinner is Present : ");
		}
	}

	// Verify Chat message sent by Player
	@Step("Verify Chat message sent by Player")
	public String verifyMessagefromPlayer() {
		String chatMessage = null;
		try {
			logger.info("Verify Chat message sent by Player");
			this.waitForElementToBeVisible(advisorChatmessage, 30);
			chatMessage = this.getText(advisorChatmessage);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (NullPointerException e) {
			logger.warn("Element is null" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info("Chat message is not Present : ");
		}
		return chatMessage;
	}

	// Send Chat message From Advisor
	@Step("Verify Chat message sent from Advisor")
	public String sendMessagefromAdvisor(String advsendMessage) {
		try {
			logger.info("Verify Chat message sent from Advisor");
			this.waitForElementToBeVisible(SendMessagetxtbox, 30);
			this.sendKeys(SendMessagetxtbox, advsendMessage);
			this.pressEnterKey(SendMessagetxtbox);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info("Chat message is not Present : ");
		}
		return advsendMessage;
	}
}
