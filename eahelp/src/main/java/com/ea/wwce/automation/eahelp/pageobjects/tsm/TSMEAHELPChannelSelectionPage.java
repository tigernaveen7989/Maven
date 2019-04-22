package com.ea.wwce.automation.eahelp.pageobjects.tsm;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.eahelp.pageobjects.EAHelpBasePageObject;

import io.qameta.allure.Step;

public class TSMEAHELPChannelSelectionPage extends EAHelpBasePageObject {

	public TSMEAHELPChannelSelectionPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(TSMEAHELPChannelSelectionPage.class);

	@FindBy(xpath = "//label[@class='msg B3']")
	WebElement estimatedwaittime;
	@FindBy(xpath = "//span[@id='headerTextLabel']")
	WebElement chatpopup;
	@FindBy(xpath = "//div[@class='chatContent']")
	WebElement chatWelcomeMessage;
	@FindBy(xpath = "//div[contains(@class,'uiInput--textarea')]//textarea[contains(@class,'textarea')]")
	WebElement chatTextbox;
	@FindBy(xpath = "(//li[contains(@class,'agent')]//div[@class='uiOutputRichText'])[2]")
	WebElement advChatMessage;
	@FindBy(xpath = "//button[@class='closeButton headerItem']")
	WebElement chatClosebutton;
	@FindBy(xpath = "//span[text()='Go Back']")
	WebElement chatBackbutton;
	@FindBy(xpath = "//span[text()='Confirm End Chat']")
	WebElement confirmEndChat;
	@FindBy(xpath = "//div[text()='Chat Ended']")
	WebElement EndChat;

	// Verify Estimated waittime
	public String verifyEstimatedwaitTime() {
		String strcaseID = null;
		try {
			String watitime = this.getText(estimatedwaittime);
			String[] cid = watitime.split("\\s");
			strcaseID = cid[3];
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return strcaseID;
	}

	// Verify Chat popup is displayed
	@Step("Verify the Chat popup")
	public boolean verifyChatPopup() {
		boolean isTrue = true;
		try {
			logger.info("Verify Chat Popup");
			this.waitForElementToBeVisible(chatpopup, 30);
			isTrue = this.isElementVisible(chatWelcomeMessage, 30);
		} catch (Exception e) {
			logger.info("Chat popup is Present : " + isTrue);
		}
		return isTrue;
	}

	// Send message to Advisor from EAHelp chat popup
	@Step("Verify Chat message sent by Player to Advisor")
	public String sendChatMessage(String chatMessage) {

		try {
			this.waitForClickableElement(20, chatTextbox);
			this.sendKeys(chatTextbox, chatMessage);
			this.pressEnterKey(chatTextbox);
		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Failed to send chat message" + e);
		}
		return chatMessage;
	}

	// Verify Chat message sent by Advisor
	@Step("Verify Chat message sent from Advisor")
	public String verifyMessagefromAdvisor() {
		String advchatMessage = null;
		try {
			logger.info("Verify Chat message sent by Advisor");
			this.waitForElementToBeVisible(advChatMessage, 30);
			advchatMessage = this.getText(advChatMessage);
		} catch (Exception e) {
			logger.info("Chat message is Present : ");
		}
		return advchatMessage;
	}

	// Verify close Chat Popup
	@Step("Verify close Chat Popup")
	public boolean closeChatPopup() {
		boolean isTrue = true;
		try {
			logger.info("Verify close Chat Popup");
			this.waitForElementToBeVisible(chatClosebutton, 10);
			this.click(chatClosebutton);
			this.click(confirmEndChat);
			isTrue = this.isElementVisible(EndChat, 10);
		} catch (Exception e) {
			logger.info("Chat popup is Present : " + isTrue);
		}
		return isTrue;
	}

	// Verify Back button
	@Step("Verify Back button")
	public boolean gobackbutton() {
		boolean isTrue = true;
		try {
			logger.info("Verify back button in Chat Popup");
			this.waitForElementToBeVisible(chatClosebutton, 10);
			this.click(chatClosebutton);
			this.click(chatBackbutton);
			isTrue = this.isElementVisible(chatTextbox, 10);
		} catch (Exception e) {
			logger.info("Chat popup is Present : " + isTrue);
		}
		return isTrue;
	}

}
