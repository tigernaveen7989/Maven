package com.ea.wwce.automation.omega.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.base.pageobjects.BasePageObject;

import io.qameta.allure.Step;

public class OmegaSaveCaseConfirmationPage extends BasePageObject {

	public OmegaSaveCaseConfirmationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(OmegaLoginPage.class);

	@FindBy(css = ".send-next-case.primary")
	WebElement btnSendAndNextCase;
	@FindBy(css = ".send-additional-case.primary")
	WebElement btnSendAndAdditionalCase;
	@FindBy(css = "[name=additional_notes]")
	WebElement additionalNotesTxt;
	@FindBy(css = "[placeholder='Type your message here...']")
	WebElement sendMessage;
	@FindBy(css = "[id='send-chat']")
	WebElement sendChat;
	@FindBy(xpath = "(//textarea[@name='description'])[1]")
	WebElement internalNotes;
	@FindBy(xpath = "//label[contains(@class,'notes-label responses')]/span[@class='icon']")
	WebElement txtInternalMessageIcon;
	@FindBy(xpath = "//li[@class='check-perm perm-transfer show']")
	WebElement btnTransfer;
	@FindBy(xpath = "//span[text()='Choose a Queue']")
	WebElement clkQueueDropdown;
	@FindBy(xpath = "(//label[@class='queues-label']//div)[2]")
	WebElement clkQueueDropdown1;
	@FindBy(xpath = "//span[contains(text(),'Choose a Queue')]")
	WebElement clkQueuedrop;
	@FindBy(css = "[id='files']")
	WebElement file;
	@FindBy(css = "strong[title='Did you pass AoV for this contact?'] +div a")
	WebElement vogAov;
	@FindBy(css = "strong[title='Did you pass AoV for this contact?'] +div input")
	WebElement vogAovInput;
	@FindBy(css = "strong[title='Did you have to verify account ownership during this contact?'] +div a")
	WebElement vogDidVerifyAov;
	@FindBy(css = "strong[title='Did you have to verify account ownership during this contact?'] +div input")
	WebElement vogDidVerifyAovInput;
	@FindBy(css = "strong[title='How did you pass AoV?'] +div a")
	WebElement vogHowDidAov;
	@FindBy(css = "strong[title='How did you pass AoV?'] +div input")
	WebElement vogHowDidAovInput;
	@FindBy(css = "strong[title='What HipChat consultation assistance did you provide?'] +div a")
	WebElement vogHttpChat;
	@FindBy(css = "strong[title='What HipChat consultation assistance did you provide?'] +div input")
	WebElement vogHttpChatInput;
	@FindBy(css = "strong[title='What part of AOV did you use to successfully verify the customer?'] +div a")
	WebElement vogWhatPart;
	@FindBy(css = "strong[title='What part of AOV did you use to successfully verify the customer?'] +div input")
	WebElement vogWhatPartInput;
	@FindBy(css = "strong[title='Is it necessary to transfer this contact to the TOS team?'] +div a")
	WebElement vogTos;
	@FindBy(css = "strong[title='Is it necessary to transfer this contact to the TOS team?'] +div input")
	WebElement vogTosInput;
	@FindBy(css = "strong[title='Is the customer ending their membership?'] +div a")
	WebElement vogCusEndMembership;
	@FindBy(css = "strong[title='Is the customer ending their membership?'] +div input")
	WebElement vogCusEndMembershipInput;
	@FindBy(xpath = "//select[@name='queue']")
	WebElement Queuename1;
	@FindBy(css = "[id='save-case']:not(.disabled)")
	WebElement btnSave;
	@FindBy(xpath = "//span[text()='Yes']")
	WebElement btnYes;
	@FindBy(xpath = "//*[text()='Go Idle']")
	WebElement goIdle;
	@FindBy(xpath = "//li[@class='check-perm perm-escalate show']")
	WebElement btnEscalate;
	@FindBy(xpath = "//a[@class='yes primary']")
	WebElement btnChatCloseYes;
	@FindBy(xpath = "//span/textarea[@name='transferText']")
	WebElement txtTransfer;
	@FindBy(xpath = "//span[text()='Choose a Queue']")
	WebElement DropDownStatus1;
	@FindBy(css = "select[name='queue'] +div input")
	WebElement inputqueue;
	@FindBy(css = "div.row.error .textarea.required.outline")
	WebElement txtInternalMessage;
	@FindBy(css = "div.row.error .textarea.outline")
	WebElement txtOutBoundMessage;
	@FindBy(css = "strong[title='Status'] +div a")
	WebElement DropDownStatus;
	@FindBy(css = "strong[title='Status'] +div input")
	WebElement inputStatus;
	@FindBy(css = "strong[title='Reason for escalation'] +div a")
	WebElement reasonDropDown;
	@FindBy(css = "strong[title='Reason for escalation'] +div input")
	WebElement inputEscalateReason;
	@FindBy(css = "strong[title='Reason for transfer'] +div a")
	WebElement reasonTransferDropDown;
	@FindBy(css = "strong[title='Reason for transfer'] +div input")
	WebElement inputTransferReason;
	@FindBy(xpath = "//em[@class='close']")
	WebElement endthisChat;
	@FindBy(xpath = "//div[@class='customer tab selected']//following-sibling::em")
	WebElement endthisChat1;
	@FindBy(xpath = "//span[contains(text(),'Yes')]")
	WebElement btnYes1;
	@FindBy(css = ".next-case.primary")
	WebElement btnNextCase;
	@FindBy(css = ".go-idle.primary")
	WebElement btnGoIdle;
	@FindBy(css = "send-idle primary")
	WebElement btnSendIdle;
	@FindBy(xpath = "//div[@class='footer']/ul[@class='buttons']/li[2]/a[@class='yes primary']")
	WebElement yesButtonActSessionPopup;
	@FindBy(xpath = "//div[@class='footer']/ul[@class='buttons']/li[1]/a[contains(@class,'no')]")
	WebElement noButtonActSessionPopup;
	@FindBy(xpath = "//div[@class='header']/span[text()='Active Chat Session']")
	WebElement activeChatSessionPopUpTitle;

	By spinner = By.className("circle-spinner");
	By innerSpinner = By.className("circle-inner");

	String Queue = "//div[@class='chzn-drop']//ul/li[text()='#']";

	@Step("Click on Send and next case button")
	public void clickOnSendAndNextCase() {
		logger.info("Click on send and next case button");

		if (isSendAndNextCaseButtonPresent()) {
			this.click(btnSendAndNextCase);
		} else {
			clickOnSendAndAdditionalCase();
		}
	}

	@Step("Click on Go Idle button")
	public void clickOnGoIdleButton() {
		logger.info("Click on Go Idle button");
		this.click(goIdle);
	}

	@Step("Click on Send and next case button")
	public void clickOnSendAndAdditionalCase() {
		logger.info("Click on send and next case button");
		this.click(btnSendAndAdditionalCase);
	}

	@Step("Verify next button is not present")
	public boolean isSendAndNextCaseButtonPresent() {
		logger.info("Verify next button is not present");
		return this.waitForElementToBeVisible(btnSendAndNextCase, 5);
	}

	@Step("Enter additional notes")
	public void enterAdditonalNotes(String additionalNotes) {
		logger.info("Enter additional notes");
		this.waitForElementToBeVisible(additionalNotesTxt, 10);
		this.sendKeys(additionalNotesTxt, additionalNotes);
	}

	@Step("Click on Send and next case button")
	public void sendmessagefromAdvisor(String textmsg) {
		logger.info("Click on send text from advisor");
		this.click(sendMessage);
		this.sendKeys(sendMessage, textmsg);
		this.click(sendChat);
	}

	@Step("Click on Yes Button from Active Chat Session Pop up")
	public void clickOnYesFromActiveChatSessionPopup() {
		logger.info("Click on Yes from Active Chat Session Pop up");
		if (this.isElementVisible(activeChatSessionPopUpTitle, 10)) {
			this.click(yesButtonActSessionPopup);
		}
	}

	@Step("Click on No Button from Active Chat Session Pop up")
	public void clickOnNoButtonFromActiveChatSessionPopup() {
		logger.info("Click on No Button from Active Chat Session Pop up");
		if (this.isElementVisible(activeChatSessionPopUpTitle, 10)) {
			this.click(noButtonActSessionPopup);
		}
	}

	@Step("Transfer the Case and Save")
	public void transferCase(String intMsg, String outMsg, String queue, String status) throws InterruptedException {
		logger.info("Transfer the case and save");

		if (this.isElementVisible(vogCusEndMembership, 3)) {
			this.click(vogCusEndMembership);
			this.sendKeys(vogCusEndMembershipInput, "No");
			this.pressEnterKey(vogCusEndMembershipInput);
			this.windowScrollDwn();
		}

		this.windowScrollDwn();

		if (this.isElementVisible(vogDidVerifyAov, 3)) {
			this.click(vogDidVerifyAov);
			this.sendKeys(vogDidVerifyAovInput, "No");
			this.pressEnterKey(vogDidVerifyAovInput);
		}

		if (this.isElementVisible(vogTos, 3)) {
			this.click(vogTos);
			this.sendKeys(vogTosInput, "I transferred this contact to TOS.");
			this.pressEnterKey(vogTosInput);
		}

		if (this.isElementVisible(vogAov, 3)) {
			this.click(vogAov);
			Thread.sleep(1000);
			this.sendKeys(vogAovInput, "I didn't need to pass AoV for this contact");
			this.pressEnterKey(vogAovInput);
		}

		if (this.isElementVisible(vogHowDidAov, 3)) {
			this.click(vogHowDidAov);
			Thread.sleep(1000);
			this.sendKeys(vogHowDidAovInput, "Primary email verification");
			this.pressEnterKey(vogHowDidAovInput);
			Thread.sleep(2000);
		}

		this.windowScrollDwn();

		if (this.isElementVisible(vogHttpChat, 3)) {
			Thread.sleep(2000);
			this.click(vogHttpChat);
			try {
				this.sendKeys(vogHttpChatInput, status);
				this.pressEnterKey(vogHttpChatInput);
			} catch (ElementNotInteractableException e) {
				this.click(vogHttpChat);
				this.sendKeys(vogHttpChatInput, "Provided Information");
				this.pressEnterKey(vogHttpChatInput);
			}
			Thread.sleep(2000);
		}

		try {
			if (this.isElementVisible(txtInternalMessageIcon, 3)) {
				this.click(txtInternalMessageIcon);
				Thread.sleep(2000);
				this.sendKeys(txtInternalMessage, intMsg);
			}
			if (this.isElementVisible(txtOutBoundMessage, 3)) {
				this.sendKeys(txtOutBoundMessage, outMsg);
			}
			this.windowScrollDwn();
			this.click(btnTransfer);
			this.moveToElement(file);
			this.click(DropDownStatus);
			Thread.sleep(5000);
			this.sendKeys(inputStatus, status);
			this.pressEnterKey(inputStatus);
			Thread.sleep(2000);

			this.click(btnTransfer);
			Thread.sleep(3000);
			this.isElementVisible(DropDownStatus1, 20);
			try {
				this.click(reasonTransferDropDown);
				this.sendKeys(inputTransferReason, outMsg);
				this.pressEnterKey(inputTransferReason);
				Thread.sleep(2000);
			} catch (Exception e) {
			}
			this.click(DropDownStatus1);
			Thread.sleep(2000);
			this.sendKeys(inputqueue, queue);
			this.pressEnterKey(inputqueue);
			Thread.sleep(3000);
			this.click(btnSave);
			Thread.sleep(1000);
			this.waitForElementToBeVisible(goIdle, 10);
			this.click(goIdle);

		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Save case details in omega" + e);
		}

	}

	@Step("Escalate the Case and Save")
	public void escalateCase(String intMsg, String outMsg, String queue, String status) throws InterruptedException {
		logger.info("Escalate the case and save");
		if (this.isElementVisible(vogCusEndMembership, 3)) {
			this.click(vogCusEndMembership);
			this.sendKeys(vogCusEndMembershipInput, "No");
			this.pressEnterKey(vogCusEndMembershipInput);
			this.windowScrollDwn();
		}

		this.windowScrollDwn();

		if (this.isElementVisible(vogDidVerifyAov, 3)) {
			this.click(vogDidVerifyAov);
			this.sendKeys(vogDidVerifyAovInput, "No");
			this.pressEnterKey(vogDidVerifyAovInput);
		}

		if (this.isElementVisible(vogTos, 3)) {
			this.click(vogTos);
			this.sendKeys(vogTosInput, "I transferred this contact to TOS.");
			this.pressEnterKey(vogTosInput);
		}

		if (this.isElementVisible(vogAov, 3)) {
			this.click(vogAov);
			Thread.sleep(1000);
			this.sendKeys(vogAovInput, "I didn't need to pass AoV for this contact");
			this.pressEnterKey(vogAovInput);
		}

		if (this.isElementVisible(vogHowDidAov, 3)) {
			this.click(vogHowDidAov);
			Thread.sleep(1000);
			this.sendKeys(vogHowDidAovInput, "Primary email verification");
			this.pressEnterKey(vogHowDidAovInput);
			Thread.sleep(2000);
		}

		this.windowScrollDwn();

		if (this.isElementVisible(vogHttpChat, 3)) {
			Thread.sleep(2000);
			this.click(vogHttpChat);
			try {
				this.sendKeys(vogHttpChatInput, status);
				this.pressEnterKey(vogHttpChatInput);
			} catch (ElementNotInteractableException e) {
				this.click(vogHttpChat);
				this.sendKeys(vogHttpChatInput, "Provided Information");
				this.pressEnterKey(vogHttpChatInput);
			}
			Thread.sleep(2000);
		}

		try {
			if (this.isElementVisible(txtInternalMessageIcon, 3)) {
				this.click(txtInternalMessageIcon);
				Thread.sleep(2000);
				this.sendKeys(txtInternalMessage, intMsg);
			}
			if (this.isElementVisible(txtOutBoundMessage, 3)) {
				this.sendKeys(txtOutBoundMessage, outMsg);
			}
			this.windowScrollDwn();
			this.click(btnEscalate);
			this.moveToElement(file);
			this.click(DropDownStatus);
			this.sendKeys(inputStatus, status);
			this.pressEnterKey(inputStatus);
			Thread.sleep(2000);
			this.click(btnEscalate);
			Thread.sleep(3000);
			try {
				this.click(reasonDropDown);
				this.sendKeys(inputEscalateReason, outMsg);
				this.pressEnterKey(inputEscalateReason);
				Thread.sleep(2000);
			} catch (Exception e) {
			}
			this.click(DropDownStatus1);
			Thread.sleep(2000);
			this.sendKeys(inputqueue, queue);
			this.pressEnterKey(inputqueue);
			Thread.sleep(2000);
			this.click(btnSave);
			Thread.sleep(3000);
			this.waitForElementToBeVisible(goIdle, 10);
			this.click(goIdle);
		} catch (Exception e) {
			logger.warn("Save case details in omega" + e);
		}
	}

	// Click on end chat from Advisor
	public void clickOnEndChatFromAdvisor() throws Exception {
		try {
			logger.info("Click on end chat");
			Thread.sleep(12000); // increasing time since it is taking long time
									// to connect
			this.click(endthisChat);
			Thread.sleep(10000);
			this.click(btnYes);
			try {
				this.click(endthisChat);
				Thread.sleep(2000);
				this.click(btnYes);
			} catch (Exception e) {
			}
		} catch (Exception e) {
			logger.info("unable to click on end this chat button from Advisor" + e);
			throw e;
		}

	}

	@Step("Click on  next case button")
	public void clickOnNextCase() {
		logger.info("Click on next case button");

		if (isNextCaseButtonPresent()) {
			this.click(btnNextCase);
		} else {
			clickOnGoIdle();
		}

	}

	@Step("Verify nextCase button is not present")
	public boolean isNextCaseButtonPresent() {
		logger.info("Verify next Case button is not present");
		return this.waitForElementToBeVisible(btnNextCase, 5);
	}

	@Step("Click on GoIdle button")
	public void clickOnGoIdle() {
		logger.info("Click on GoIdle button");
		this.click(btnGoIdle);
	}

	@Step("Click on Send and Idel button")
	public void clickOnSendIdle() {
		logger.info("Click on SendIdle button");
		this.isElementVisible(btnSendIdle, 10);
		this.click(btnSendIdle);
	}
}
