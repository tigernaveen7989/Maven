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
 *              Home page in TSM application.
 *
 */

public class TSMAdvisorHomePage extends TSMBasePageObject {

	public TSMAdvisorHomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(TSMAdvisorHomePage.class);

	@FindBy(xpath = "//div [contains(@class, 'branding-user-profile bgimg')]")
	WebElement usericon;
	@FindBy(xpath = "//h1[@class='profile-card-name']")
	WebElement agentname;

	String agentName = "//*[@id='agent-name']/span[text()='#']";

	public String getagentname() {

		logger.info("Find agent name:" + agentname);
		try {
			this.click(usericon);
		} catch (NoSuchElementException e) {
			logger.warn("Failed to find element" + e);
		} catch (TimeoutException e) {
			logger.warn("Failed to find agent name " + e);
		}
		return this.getText(agentname);

	}

}
