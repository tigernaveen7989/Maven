package com.ea.wwce.automation.eahelp.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;

/**
 * 
 * @author sadabala
 *
 */

public class EAHelpArticlePage extends EAHelpBasePageObject {

	public EAHelpArticlePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(EAHelpAccountManagementPage.class);

	@FindBy(css = "#requireContact")
	WebElement taggedArticleContactUs;
	@FindBy(css = ".span12 h1")
	WebElement errorHeader;
	@FindBy(css = ".span12 .title")
	WebElement errorTitle;
	@FindBy(css = ".item.answer-hq")
	WebElement answerhqTile;
	@FindBy(css = ".item.origin-store")
	WebElement orignTile;
	@FindBy(css = ".item.manage-account")
	WebElement accountTile;
	@FindBy(css = ".item.ea-help")
	WebElement eaHelpTile;
		
	
	// By locators
	By deheaderText = (By.xpath("//h1[text()='Leider existiert diese Seite nicht!']"));
	By imageVideo =(By.cssSelector(".style-scope wwce-image"));

	@Step("Click on contact us button")
	public void clickOnContactUsButton() {
		logger.info("Click on contact us button");
		this.click(taggedArticleContactUs);
	}

	@Step("Verify Article Error page is shown correctly for DK locale ")
	public boolean isErrorPageShownCorrectly() {
		boolean isTrue = false;
		logger.info("Verify article error page is shown correctly");
		try {
			this.waitForElementToBeVisible(errorTitle, 5);
			this.waitForElementToBeVisible(answerhqTile, 5);
			this.waitForElementToBeVisible(orignTile, 5);
			this.waitForElementToBeVisible(accountTile, 5);
			this.waitForElementToBeVisible(eaHelpTile, 5);
			isTrue = true;
		} catch (Exception e) {
			isTrue = false;
			logger.info("Fail to verify article error page is shown correctly");
		}
		return isTrue;
	}

	@Step("Verify Article error page content in Dutch for de locale")
	public boolean verifyErrorContentInDeLocale() {
		logger.info("Verify Article error page content in Dutch for de locale");
		return this.isElementPresent(deheaderText, 5);
	}
	
	@Step("Verify Article page contains imega and video")
	public boolean verifyImageVideoPresent() {
		logger.info("Verify Article page contains imega and video");
		return this.isElementPresent(imageVideo, 30);

	}
}
