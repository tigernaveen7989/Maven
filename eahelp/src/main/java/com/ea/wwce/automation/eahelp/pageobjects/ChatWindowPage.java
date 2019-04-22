package com.ea.wwce.automation.eahelp.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChatWindowPage extends EAHelpBasePageObject {

	public ChatWindowPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(EAHelpAccountManagementPage.class);

	@FindBy(css = "#buttonContainer #endchatbtn")
	WebElement endChatButton;
	@FindBy(css = "#buttonContainer #emailbtn")
	WebElement emailButton;
	@FindBy(css = "#buttonContainer #printbtn")
	WebElement printButton;
	@FindBy(xpath = "//span[text()='Sure']")
	WebElement sureButton;	
	@FindBy(css = ".ui-dialog-buttonset :nth-child(2)")
	WebElement noThanksButton;
	@FindBy(css = "#survey")
	WebElement surveyForm;
	@FindBy(xpath = "//textarea[@id='chatLine']")
	WebElement chatarea;

	// Switch to chat window
	public void switchToChatWidnow(String title) throws Exception {
		try {
			logger.info("Switch to chat window");
			Thread.sleep(60000);
			this.switchWindowByTitle(title);
		} catch (Exception e) {
			logger.info("Not able to Switch to chat window " + e);
			throw e;
		}
	}

	// Click on end chat button
	public void clickOnEndChatButton() throws Exception {
		try {
			logger.info("Click on end chat button");
			Thread.sleep(12000); // increasing time since it is taking long time
									// to connect
			this.click(endChatButton);
			Thread.sleep(10000);
		} catch (Exception e) {
			logger.info("unable to click on end chat button" + e);
			throw e;
		}

	}

	// verify chat survey window present
	public boolean verifyChatSurveyPresent() throws Exception {
		boolean isTrue = false;
		try {
			logger.info("verify chat survey window present");
			this.click(sureButton);
			Thread.sleep(5000);
			isTrue = this.waitForElementToBeVisible(surveyForm, 30);
		} catch (Exception e) {
			logger.info("unable to find chat survey window" + e);
			throw e;
		}
		return isTrue;
	}

	// chat function
	public void sendmsgfromPlayer(String text) throws Exception {
		try {
			logger.info("send message from player");
			Thread.sleep(12000); // increasing time since it is taking long time
									// to connect
			this.click(chatarea);
			Thread.sleep(10000);
			this.sendKeys(chatarea, text);
			this.pressEnterKey(chatarea);
			Thread.sleep(1000);
		} catch (Exception e) {
			logger.info("unable to send message" + e);
			throw e;
		}

	}

}
