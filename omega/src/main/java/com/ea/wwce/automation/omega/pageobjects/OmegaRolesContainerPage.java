package com.ea.wwce.automation.omega.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.omega.config.OmegaDataConstants;

import io.qameta.allure.Step;

public class OmegaRolesContainerPage extends OmegaBasePageObject {

	public OmegaRolesContainerPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(OmegaRolesContainerPage.class);

	OmegaLoginPage omegaLoginPage = new OmegaLoginPage(driver);
	OmegaAgentHomePage omegaAgentHomePage = new OmegaAgentHomePage(driver);

	@FindBy(className = "continue")
	WebElement btnContinue;
	@FindBy(id = "aux-status")
	WebElement auxstaus;
	@FindBy(xpath = "//li[contains(text(),'Available')]")
	WebElement available;

	// dynamic xpath to select specific role
	String btnRole = "//span[@original-title='#']";

	public void selectRole(String roleName) {
		try {
			Thread.sleep(2000);
			omegaLoginPage.isSpinnerInvisible(60);
			this.waitForDynamicElementToBeVisible(btnRole, roleName, 60);
			this.clickOnDynamicElement(btnRole, roleName);
			this.click(btnContinue);
			logger.info("Successfully selected required role");
			omegaLoginPage.isSpinnerInvisible(60);

		} catch (NoSuchElementException e) {
			logger.info("Failed to select role" + e);
		} catch (TimeoutException e) {
			logger.info("Failed to select role " + e);
		} catch (InterruptedException e) {
			logger.info("Failed to select role " + e);
		}
	}

	public void loginAsAdvsior(String URL, String username, String password, String roleName) {

		try {

			// Launch omega URL
			this.loadPage(URL);

			// Verify omega login
			omegaLoginPage.loginToOmega(username, password);

			// Select required role
			this.selectRole(roleName);

			omegaLoginPage.isSpinnerInvisible(20);

			// Verify agent name
			assertion.assertTrue(omegaAgentHomePage.verifyAgentName(roleName), "Verify agent is visible");

			// Wait for aux status
			this.waitForClickableElement(OmegaDataConstants.IMPLICIT_TIMEOUT, auxstaus);

			Thread.sleep(2000);

			// click on aux status
			this.click(auxstaus);

			Thread.sleep(2000);

			// wait for available status link
			this.waitForClickableElement(OmegaDataConstants.IMPLICIT_TIMEOUT, available);

			// click on available status link
			this.click(available);

			omegaLoginPage.isSpinnerInvisible(20);

			// Thread.sleep(1000);

		} catch (Exception e) {
			logger.warn("Failed to login " + e);
		}
	}

	@Step("Verify agent name is visible")
	public void loginAsEmailAdvsior(String URL, String username, String password, String roleName) {

		try {

			// Launch omega URL
			this.loadPage(URL);

			// Verify spinner element is visible
			omegaLoginPage.isSpinnerInvisible(30);

			// Verify omega login
			omegaLoginPage.loginToOmega(username, password);

			// Verify spinner element is visible
			omegaLoginPage.isSpinnerInvisible(30);

			Thread.sleep(2000);

			// Select required role
			this.selectRole(roleName);

			// Verify spinner element is visible
			omegaLoginPage.isSpinnerInvisible(30);

			Thread.sleep(2000);

			// Verify agent name
			omegaAgentHomePage.verifyAgentName(roleName);

		} catch (Exception e) {
			logger.warn("Failed to login " + e);
		}
	}

}
