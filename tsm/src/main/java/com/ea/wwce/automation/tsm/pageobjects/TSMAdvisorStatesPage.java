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
 * @description This class consists of Page objects and Functions of Advisor
 *              States in TSM application.
 * 
 */

public class TSMAdvisorStatesPage extends TSMBasePageObject {

	public TSMAdvisorStatesPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(TSMAdvisorStatesPage.class);

	@FindBy(xpath = "//span[text()='Omni-Channel']")
	WebElement clkOmnichannel;
	@FindBy(xpath = "//div [@class='slds-dropdown-trigger slds-dropdown-trigger_click']//button[contains(@class,'x-small')]")
	WebElement btnOmniStatusDrDwn;
	@FindBy(className = "slds-truncate")
	WebElement sltAvailable;
	@FindBy(xpath = "//span[text()='Minimize']")
	WebElement minimizeOmnichannel;
	@FindBy(css = "li[title='Available - Email']")
	WebElement listItemAvailableEmail;
	@FindBy(css = "li[title='Available - Chat']")
	WebElement listItemAvailableChat;
	@FindBy(css = "li[title='Break']")
	WebElement listItemBreak;
	@FindBy(css = "li[title='Meeting']")
	WebElement listItemMeeting;
	@FindBy(css = "li[title='Training']")
	WebElement listItemTraining;
	@FindBy(css = "li[title='Offline']")
	WebElement listItemOffline;
	@FindBy(className = "onlineStatus truncatedText uiOutputText")
	WebElement onlineStatus;
	@FindBy(css = "span[class*='offlineStatus truncatedText uiOutputText']")
	WebElement lblOmniStatus;
	@FindBy(css = "[data-aura-class='uiOutputText']")
	WebElement selecStatus;
	@FindBy(xpath = "//span[@class='onlineStatus truncatedText uiOutputText']")
	WebElement selectonlineStatus;
	@FindBy(xpath = "//span[@class='awayStatus truncatedText uiOutputText']")
	WebElement selectawayStatus;
	@FindBy(xpath = "//span[@class='awayStatus truncatedText uiOutputText']")
	WebElement selectofflineStatus;

	@Step("Check omnichannel option is visible")
	public boolean isOmnichannelWidgetAvailable() {
		boolean isVisible = false;
		try {
			logger.info("Verifying the OmnichannelWidget");
			this.waitForElementToBeVisible(clkOmnichannel, 20);
			isVisible = true;
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info("Failed to verify OmnichannelWidget");
			isVisible = false;
		}
		return isVisible;
	}

	@Step("Verify collapse icon available")

	public boolean isCollapseiconAvailable() {
		boolean isVisible = false;
		try {
			logger.info("Verifying the colapse icon visible");
			this.waitForClickableElement(20, minimizeOmnichannel);
			isVisible = true;
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info("Failed to verify colapse icon");
			isVisible = false;
		}
		return isVisible;
	}

	@Step("Open omnichannel")
	public void clickOmniChannel() {
		try {
			logger.info("Click OmniChannel");
			this.waitForClickableElement(30, clkOmnichannel);
			this.click(clkOmnichannel);

		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info("failed to Clicked on Omnichannel");
		}
	}

	@Step("verify dropdown is available")
	public boolean isDropdownAvailable() {
		boolean isVisible = false;
		try {
			logger.info("Verifying the status dropdown visible");
			this.waitForClickableElement(10, btnOmniStatusDrDwn);
			// this.waitForElementToBeVisible(btnOmniStatusDrDwn, 10);
			isVisible = true;
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info("Failed to verify status dropdown");
			isVisible = false;
		}
		return isVisible;
	}

	@Step("Get OmniStatus from Widget available ")
	public String getOmniStatusFrmWidget() {
		String strText = "";
		try {
			logger.info("Get Status Text from Omni Widget");
			strText = this.getText(selectonlineStatus);
			System.out.println(strText);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info("Failed to Get status text from Omni widget");
		}
		return strText;
	}

	/*
	 * @Step("Verify the omnichannel") public void
	 * selectOmniStatusAvailableEmail1() { try {
	 * logger.info("Changing Omni Status to Available Email");
	 * this.waitForClickableElement(10, btnOmniStatusDrDwn);
	 * //this.waitForElementToBeVisible(btnOmniStatusDrDwn,20);
	 * this.click(btnOmniStatusDrDwn); this.waitForClickableElement(10,
	 * listItemAvailableEmail); this.click(listItemAvailableEmail);
	 * logger.info("Omni Status is changed to Available Email"); } catch
	 * (Exception e) { logger.info("Failed to change Omni Status to Available");
	 * } }
	 */

	/*
	 * @Step("Changing omni channel status to Available Email") public String
	 * selectOmniStatusAvailableEmail1() { String strStatus = ""; try {
	 * logger.info("Changing Omni Status to Available Email");
	 * 
	 * this.waitForClickableElement(10, btnOmniStatusDrDwn); //
	 * this.waitForElementToBeVisible(btnOmniStatusDrDwn,20);
	 * //Thread.sleep(10000); this.click(btnOmniStatusDrDwn); //
	 * this.waitForElementToBeVisible(listItemAvailableEmail, //
	 * TSMDataConstants.IMPLICIT_TIMEOUT); this.waitForClickableElement(10,
	 * listItemAvailableEmail);
	 * 
	 * this.click(listItemAvailableEmail); //Thread.sleep(7000); //strStatus =
	 * this.getText(selectonlineStatus);
	 * logger.info("Omni Status is changed to Available Email"); } catch
	 * (Exception e) { logger.info("Failed to change Omni Status to Available");
	 * } return strStatus; }
	 */

	@Step("Changing Omni status to Available Email")
	public boolean selectOmniStatusAvailableEmail() {
		boolean isVisible = false;
		try {
			logger.info("Changing Omni Status to Available Email");
			Thread.sleep(2000);
			this.waitForClickableElement(10, btnOmniStatusDrDwn);
			this.click(btnOmniStatusDrDwn);
			this.waitForElementToBeVisible(listItemAvailableEmail, 20);
			this.click(listItemAvailableEmail);
			isVisible = this.isElementVisible(selectonlineStatus, 20);
			logger.info("Omni Status is changed to Available Email");
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info("Failed to change Omni Status to Available Chat");
		}
		return isVisible;
	}

	@Step("Changing Omni status to Available Chat")
	public boolean selectOmniStatusAvailableChat() {
		boolean isVisible = false;
		try {
			logger.info("Changing Omni Status to Available Chat");
			// this.waitForElementToBeVisible(btnOmniStatusDrDwn, 20);
			Thread.sleep(2000);
			this.waitForClickableElement(20, btnOmniStatusDrDwn);
			this.click(btnOmniStatusDrDwn);
			this.waitForClickableElement(20, listItemAvailableChat);
			// this.waitForElementToBeVisible(listItemAvailableChat, 20);
			this.click(listItemAvailableChat);
			isVisible = this.isElementVisible(selectonlineStatus, 20);
			logger.info("Omni Status is changed to Available Chat");
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info("Failed to change Omni Status to Available Chat");
		}
		return isVisible;
	}

	/*
	 * @Step("Changing Omni status to Available Chat") public String
	 * selectOmniStatusAvailableChat1() { String strStatus = ""; try {
	 * logger.info("Changing Omni Status to Available Chat");
	 * //this.waitForElementToBeVisible(btnOmniStatusDrDwn, 20);
	 * this.waitForClickableElement(10, btnOmniStatusDrDwn);
	 * this.click(btnOmniStatusDrDwn);
	 * this.waitForElementToBeVisible(listItemAvailableChat, 20);
	 * //this.waitForClickableElement(10, listItemAvailableChat);
	 * this.click(listItemAvailableChat); //Thread.sleep(7000);
	 * this.waitForElementToBeVisible(selectonlineStatus, 10); strStatus =
	 * this.getText(selectonlineStatus);
	 * logger.info("Omni Status is changed to Available Chat"); } catch
	 * (Exception e) {
	 * logger.info("Failed to change Omni Status to Available Chat"); } return
	 * strStatus; }
	 */

	@Step("Changing Omni status to Break")
	public boolean selectOmniStatusAvailableBreak() {
		boolean isVisible = false;
		try {
			logger.info("Changing Omni Status to Available Break");
			Thread.sleep(2000);
			this.waitForClickableElement(10, btnOmniStatusDrDwn);
			// this.waitForElementToBeVisible(btnOmniStatusDrDwn, 20);
			this.click(btnOmniStatusDrDwn);
			this.waitForClickableElement(10, listItemBreak);
			this.click(listItemBreak);
			isVisible = this.isElementVisible(selectawayStatus, 20);
			logger.info("Omni Status is changed to Break");
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info("Failed to change Omni Status to Break");
		}
		return isVisible;
	}

	@Step("Changing Omni status to Meeting")
	public boolean selectOmniStatusAvailableMeeting() {
		boolean isVisible = false;
		try {
			logger.info("Changing Omni Status to Meeting");
			Thread.sleep(2000);
			this.waitForClickableElement(10, btnOmniStatusDrDwn);
			// this.waitForElementToBeVisible(btnOmniStatusDrDwn, 20);
			this.click(btnOmniStatusDrDwn);
			this.waitForClickableElement(10, listItemMeeting);
			this.click(listItemMeeting);
			isVisible = this.isElementVisible(selectawayStatus, 20);
			logger.info("Omni Status is changed to Meeting");
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info("Failed to change Omni Status to Meeting");
		}
		return isVisible;
	}

	@Step("Changing Omni status to Training")
	public boolean selectOmniStatusAvailableTraining() {
		boolean isVisible = false;
		try {
			logger.info("Changing Omni Status to Training");
			Thread.sleep(2000);
			this.waitForClickableElement(10, btnOmniStatusDrDwn);
			// this.waitForElementToBeVisible(btnOmniStatusDrDwn, 20);
			this.click(btnOmniStatusDrDwn);
			this.waitForClickableElement(10, listItemTraining);
			this.click(listItemTraining);
			// Thread.sleep(7000);
			isVisible = this.isElementVisible(selectawayStatus, 20);
			logger.info("Omni Status is changed to Training");
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info("Failed to change Omni Status to Training");
		}
		return isVisible;
	}

	@Step("Changing Omni status to Offline")
	public boolean selectOmniStatusAvailableOffline() {
		boolean isVisible = false;
		try {
			logger.info("Changing Omni Status to Available Offline");
			Thread.sleep(2000);
			this.waitForClickableElement(10, btnOmniStatusDrDwn);
			// this.waitForElementToBeVisible(btnOmniStatusDrDwn, 20);
			this.click(btnOmniStatusDrDwn);
			this.waitForClickableElement(10, listItemOffline);
			this.click(listItemOffline);
			// Thread.sleep(7000);
			isVisible = this.isElementVisible(selectofflineStatus, 20);
			logger.info("Omni Status is changed to Offline");
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info("Failed to change Omni Status to Offline");
		}
		return isVisible;
	}

	@Step("Minimize omni channel")
	public void CloseOmnichannel() {
		try {
			logger.info("Click on Collapse icon");
			this.waitForClickableElement(5, minimizeOmnichannel);
			this.click(minimizeOmnichannel);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info("Failed to Minimise Omni channel");
		}
	}

	/*
	 * @Step("Verify the selected status") public String
	 * isStatusSelectedDisplayed() { String isVisible = null; try {
	 * logger.info("Verifying the Advisor status and text");
	 * //Thread.sleep(3000); isVisible = this.getText(selecStatus);
	 * this.waitForElementToBeVisible(selecStatus, 20);
	 * logger.info("Selected status " + isVisible); } catch (Exception e) {
	 * logger.info("Failed to verify selected Advisor State"); } return
	 * isVisible; }
	 */
}
