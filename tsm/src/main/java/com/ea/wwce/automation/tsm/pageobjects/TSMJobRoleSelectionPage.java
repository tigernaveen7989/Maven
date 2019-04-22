package com.ea.wwce.automation.tsm.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.tsm.config.TSMDataConstants;

import io.qameta.allure.Step;

/**
 * 
 * @author rgandham 
 * @description This class consists of Page objects and Functions of Job role selection in TSM application.
 * 
 */

public class TSMJobRoleSelectionPage extends TSMBasePageObject {

	public TSMJobRoleSelectionPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(TSMJobRoleSelectionPage.class);

	@FindBy(xpath = "//span[text()='Job Role']")
	WebElement ClkJobRole;
	@FindBy(xpath = "//span[text()='Admin Email']")
	WebElement listItemAdminEmail;
	@FindBy(xpath = "//div[contains(@class,'slds-is-open')]//button[@title='Minimize']")
	WebElement minimize;
	
	
	
	String jobRole = "//span[text()='#']";

	@Step("Click the Job role")
	public void ClickOnJobRole() {
		try {
			logger.info("Click on Job Role");
			this.waitForElementToBeVisible(ClkJobRole, TSMDataConstants.IMPLICIT_TIMEOUT);
			this.click(ClkJobRole);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the elemnt within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info("failed to Clicked on Omnichannel");
		}
	}

	@Step("Verify Selected Jobrole")
	public String getJobRoleStatus() {
		String strText = "";
		try {
			logger.info("Get Status Text from Jobrole");
			strText = this.getText(listItemAdminEmail);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (NullPointerException e) {
			logger.warn("Element is null" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the elemnt within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info("Failed to Get status text from Jobrole");
		}
		return strText;
	}
	
	@Step("Select job role")
	public void selectJobRole(String txtJobRole) {
		try {
			logger.info("Select job role");
			Thread.sleep(2000);
			this.waitForClickableElement(30, ClkJobRole);
			this.click(ClkJobRole);
			Thread.sleep(3000);
			this.waitForDynamicElementToBeVisible(jobRole, txtJobRole, 5);
			this.clickOnDynamicElement(jobRole, txtJobRole);
			minimizeJobrolewindow();
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the elemnt within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info("failed to select Jobrole");
		}
	}
	
	@Step("Select job role")
	public void minimizeJobrolewindow() {
		try {
			logger.info("Select job role");
			this.waitForElementToBeVisible(minimize, 10);
			this.click(minimize);
		} catch (NoSuchElementException e) {
			logger.warn("failed to find the element" + e);
		} catch (TimeoutException ex) {
			logger.info("failed to find the elemnt within time " + ex.getMessage());
		} catch (Exception e) {
			logger.info("failed to minimize jobrole window");
		}
	}
	
	
	
	

}
