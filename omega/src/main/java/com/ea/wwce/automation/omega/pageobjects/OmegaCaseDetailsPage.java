package com.ea.wwce.automation.omega.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.base.pageobjects.BasePageObject;

import io.qameta.allure.Step;

public class OmegaCaseDetailsPage extends BasePageObject {

	public OmegaCaseDetailsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(OmegaAgentHomePage.class);

	//@FindBy(css = "div.row.error .textarea.required.outline")
	// @FindBy(css="div[id='case-panel'][style$='visible;'] div.row.error
	// .textarea.required.outline")
	// @FindBy(css="div.section.show div.row.error .textarea.required.outline")
	@FindBy(xpath="//textarea[@name='description']")
	WebElement txtInternalMessage;
	@FindBy(xpath="//label[contains(@class,'notes-label responses')]/span[@class='icon']")
	WebElement txtInternalMessageIcon;
	@FindBy(css = "div.row.error .textarea.outline")
	// @FindBy(xpath="//textarea[@name='outbound_message']")
	WebElement txtOutBoundMessage;
	@FindBy(css = "strong[title='Status'] +div a")
	WebElement DropDownStatus;
	@FindBy(css = "strong[title='Status'] +div input")
	WebElement inputStatus;
	@FindBy(css = "strong[title='Issue Resolution Status'] +div a")
	WebElement DropDownResolutionStatus;
	@FindBy(css = "strong[title='Issue Resolution Status'] +div input")
	WebElement inputResolutionStatus;
	@FindBy(css = "strong[title='Reason'] +div a")
	WebElement dropDownReason;
	@FindBy(css = "strong[title='Reason'] +div input")
	WebElement inputReason;
	@FindBy(css = "[id='save-case']:not(.disabled)")
	WebElement btnSave;
	@FindBy(css = ".modal.case-confirmation")
	WebElement caseconfirmationWindow;
	@FindBy(css = "div[id=case-panel][style$='visible;'] #details")
	WebElement caseDetailsSection;
	@FindBy(css = "input[name='subject']")
	WebElement subject;
	@FindBy(css = "div[id=case-panel][style*='visible;'] #details input[name='subject']")
	WebElement chatSubject;
	@FindBy(css = "select[name='product'] +div span")
	WebElement product;
	@FindBy(css = "select[name='platform'] +div span")
	WebElement platform;
	@FindBy(css = "select[name='category'] +div span")
	WebElement category;
	@FindBy(css = "select[name='issue'] +div span")
	WebElement subcategory;
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
	
	
	@Step("Save case details in omega")
	public boolean saveCaseDetails(String intMsg, String outMsg, String status, String resolutionStatus,String reason) {
		boolean isTrue = false;
		logger.info("Save case details in omega");
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
			try{
				this.click(DropDownStatus);
				try{
					this.sendKeys(inputStatus, status);
					this.pressEnterKey(inputStatus);
				}catch(ElementNotInteractableException e){
					this.click(DropDownStatus);
					this.sendKeys(inputStatus, status);
					this.pressEnterKey(inputStatus);
				}
				Thread.sleep(2000);
			}catch(Exception e){}
			try{
				this.click(DropDownResolutionStatus);
				this.sendKeys(inputResolutionStatus, resolutionStatus);
				this.pressEnterKey(inputResolutionStatus);
				Thread.sleep(2000);
			}catch(Exception e){}
			try{
				this.click(dropDownReason);
				this.sendKeys(inputReason, reason);
				this.pressEnterKey(inputReason);
			}catch(Exception e){}
			this.click(btnSave);
			this.waitForElementToBeVisible(caseconfirmationWindow, 60);
			isTrue = true;
		} catch (Exception e) {
			logger.warn("Save case details in omega" + e);
		}
		return isTrue;
	}

	@Step("Save case details in omega")
	public boolean saveCaseDetailsWithVOG(String intMsg, String outMsg, String status, String resolutionStatus,
			String reason, String vogTxt1, String vogTxt2, String vogTxt3) {
		boolean isTrue = false;
		logger.info("Save case details in omega");
		try {

			/*if (this.isElementVisible(vogWhatPart, 3)) {
				this.click(vogWhatPart);
				this.sendKeys(vogWhatPartInput, "SQA");
				this.pressEnterKey(vogWhatPartInput);
			}
*/
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
				this.sendKeys(vogAovInput, vogTxt1);
				this.pressEnterKey(vogAovInput);
			}

			if (this.isElementVisible(vogHowDidAov, 3)) {
				this.click(vogHowDidAov);
				Thread.sleep(1000);
				this.sendKeys(vogHowDidAovInput, vogTxt2);
				this.pressEnterKey(vogHowDidAovInput);
				Thread.sleep(2000);
			}

			this.windowScrollDwn();
			
			if (this.isElementVisible(vogHttpChat, 3)) {
				Thread.sleep(2000);
				this.click(vogHttpChat);
				try{
					this.sendKeys(vogHttpChatInput, status);
					this.pressEnterKey(vogHttpChatInput);
				}catch(ElementNotInteractableException e){
					this.click(vogHttpChat);
					this.sendKeys(vogHttpChatInput, vogTxt3);
					this.pressEnterKey(vogHttpChatInput);
				}
				Thread.sleep(2000);
			}

			saveCaseDetails(intMsg, outMsg, status, resolutionStatus, reason);

		} catch (Exception e) {
			logger.warn("Save case details in omega" + e);
		}
		return isTrue;
	}

	public boolean verifyCaseDetailsInOmega(String txtSubject, String txtProduct, String txtPlatform,
			String txtCategory, String txtSubCategory) {

		boolean isTrue = false;
		logger.info("Verify case details in omega");
		try {
			/*
			 * if (this.isElementVisible(subject, 60)) { this.getAttributeValue(subject,
			 * "value").equalsIgnoreCase(txtSubject); } else if
			 * (this.isElementVisible(chatSubject, 60)) {
			 * this.getAttributeValue(chatSubject, "value").equalsIgnoreCase(txtSubject); }
			 */
			this.isElementVisible(product, 15);
			this.moveToElement(product);
			if (this.getText(product).equalsIgnoreCase(txtProduct)) {
				if (this.getText(platform).equalsIgnoreCase(txtPlatform)) {
					if (this.getText(category).equalsIgnoreCase(txtCategory)) {
						if (this.getText(subcategory).equalsIgnoreCase(txtSubCategory))
							isTrue = true;
					}
				}
			}

		} catch (Exception e) {
			logger.warn("Not able to find case details in omega" + e);
		}
		return isTrue;
	}

	/*
	 * public boolean verifyChatCaseDetailsInOmega(String txtSubject, String
	 * txtProduct, String txtPlatform, String txtCategory, String txtSubCategory) {
	 * 
	 * boolean isTrue = false; logger.info("Verify case details in omega"); try {
	 * this.waitForClickableElement(60, chatSubject);
	 * 
	 * if (this.getAttributeValue(chatSubject,
	 * "value").equalsIgnoreCase(txtSubject)) { if
	 * (this.getText(product).equalsIgnoreCase(txtProduct)) { if
	 * (this.getText(platform).equalsIgnoreCase(txtPlatform)) { if
	 * (this.getText(category).equalsIgnoreCase(txtCategory)) { if
	 * (this.getText(subcategory).equalsIgnoreCase(txtSubCategory)) isTrue = true; }
	 * } } } } catch (Exception e) { logger.warn(
	 * "Not able to find case details in omega" + e); } return isTrue; }
	 */
	
		
}
