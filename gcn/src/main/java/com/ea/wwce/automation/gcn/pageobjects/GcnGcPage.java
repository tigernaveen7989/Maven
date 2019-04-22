package com.ea.wwce.automation.gcn.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.gcn.config.GcnDataConstants;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import io.qameta.allure.Step;

public class GcnGcPage extends GcnBasePageObjects{
	
	/*
	 * This class defines the UI elements and methods to verify the navigation links and ui
	 * elements in the Game Changer Page after login.
	 */
	
	private static final Logger logger=Logger.getLogger(GcnGcPage.class);
	
	public GcnGcPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(xpath="//img[@class='eapl-local-nav__shelf-logo style-scope ea-local-nav']")
	WebElement gameChangerLogo;
	
	@FindBy(xpath="//span[@class='eapl-local-nav__open-link style-scope ea-local-nav']")
	WebElement menuLink;
	
	@FindBy(xpath="//a[@class='eapl-local-nav__shelf-link' and @title='Home']")
	WebElement homeLink;
	
	@FindBy(xpath="//a[@class='eapl-local-nav__shelf-link' and @title='Opportunities']")
	WebElement opportunitiesLink;
	
	@FindBy(xpath="//a[@class='eapl-local-nav__shelf-link' and @title='Program']")
	WebElement programLink;
	
	@FindBy(xpath="//a[@class='eapl-local-nav__shelf-link' and @title='Profile']")
	WebElement profileLink;
	
	@FindBy(xpath="//a[@class='eapl-local-nav__shelf-link' and @title='Logout']")
	WebElement logoutLink;
	
	@FindBy(xpath="//div[@id='site']/ea-header/div/div[2]/h1")
	WebElement welcomeMsg;
	

	// Footer LInks
	
		@FindBy(xpath="//a[@title='Browse Games']")
		WebElement footer_homelink;
		
		@FindBy(xpath="//a[@title='Legal Notices']")
		WebElement footer_legalnoticelink;
		
		@FindBy(xpath="//a[@title='Product EULAs and Disclaimers']")
		WebElement footer_eulalink;
		
		@FindBy(xpath="//a[@title='Online Service Updates']")
		WebElement footer_serviceupdateslink;
		
		@FindBy(xpath="//a[@title='Terms of Service']")
		WebElement footer_termslink;
		
		@FindBy(xpath="//a[@title='Privacy & Cookie Policy']")
		WebElement footer_privacylink;
	
	public String actGcTitle="Game Changers Network - Site";

	
	// UI Actions
	
	public GcOpportunityPage goToOpportunitiesPage() {
		logger.info("Navigating to Opportunities Page of Game Changers..");
		this.click(opportunitiesLink);
		this.verifyPageIsLoaded();
		return new GcOpportunityPage(driver);
	}
	
	public GcnGcHomePage clickOnLogout() {
		
		logger.info("Logout from Gamechanger website");
		this.click(logoutLink);
		this.verifyPageIsLoaded();
		return new GcnGcHomePage(driver);		
	}
		
	
	
	public GcnGcProfilePage clickOnProfileLink() {
		logger.info("Click on Profile Link");
		this.click(profileLink);
		return new GcnGcProfilePage(driver);
	}
	
	@Step("Verify the GameChanger Page title")
	public boolean verifyGcPageTitle() {
		boolean a=false;
		if(GcnDataConstants.actGcTitle.equals(this.getPageTitle())){
			a=true;
		}
		return a;
	}
	
	@Step("Verify Navigation to GameChangers Page")
	public boolean verifyNavigationToGcPage() throws InterruptedException {
		// method to click on Home link and check user lands into Home Page.
		logger.info("Navigating to HomePage or Landing Page");
		boolean a=false;
		this.verifyPageIsLoaded();
		if(this.isElementVisible(welcomeMsg, GcnDataConstants.IMPLICIT_TIMEOUT) && 
				this.isElementVisible(logoutLink, GcnDataConstants.IMPLICIT_TIMEOUT) ) {
			logger.info("Landed on Home Page");
			a=true;
		}else {
			logger.info("Error Landing on Home Page");
		}
		Thread.sleep(2000);
		return a;
		
	}
	
	@Step("Verify the Availability of Menu Links in Game Changer Page")
	public boolean verifyAvailabilityOfMenuLinks() {
		boolean a=false;
		/*
		 * this method will check the availability of menu items as below
		 * Menu || Home || Opportunities || Reimbursement || Program || Profile || Logout
		 */
		try {
			
			if(this.isElementVisible(menuLink, GcnDataConstants.IMPLICIT_TIMEOUT) &&
					this.isElementVisible(homeLink, GcnDataConstants.IMPLICIT_TIMEOUT) &&
					this.isElementVisible(homeLink, GcnDataConstants.IMPLICIT_TIMEOUT) &&
					this.isElementVisible(homeLink, GcnDataConstants.IMPLICIT_TIMEOUT) &&
					this.isElementVisible(homeLink, GcnDataConstants.IMPLICIT_TIMEOUT) &&
					this.isElementVisible(homeLink, GcnDataConstants.IMPLICIT_TIMEOUT) &&
					this.isElementVisible(homeLink, GcnDataConstants.IMPLICIT_TIMEOUT)) {
				a=true;
			}
		}catch(ElementNotFoundException e) {
			logger.info("Links Not Found "+e.getMessage());
		}
		
		return a;
	}
	
	public void clickOnHomeLink() {
		logger.info("Navigating to Home Page");
		this.click(homeLink);
	}
	public GcProgramPage clickOnProgramLink() {
		logger.info("Navigating to Program Page");
		this.click(programLink);
		return new GcProgramPage(driver);
	}
	
	// Footer link navigation.
	public void clickOnFooterHome() {
		this.waitForElementToBeVisible(footer_homelink, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.click(footer_homelink);
	}

	public GcLegalNoticesPage clickOnFooterLegalNotices() {
		this.waitForElementToBeVisible(footer_legalnoticelink, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.click(footer_legalnoticelink);
		return new GcLegalNoticesPage(driver);
	}

	public GcEulaPage clickOnFooterEulaLink() {
		this.waitForElementToBeVisible(footer_eulalink, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.click(footer_eulalink);
		return new GcEulaPage(driver);
	}

	public GcTermsPage clickOnFooterTermsLink() {
		this.waitForElementToBeVisible(footer_termslink, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.click(footer_termslink);
		return new GcTermsPage(driver);
	}

	public GcPrivacyPage clickOnFooterPrivacyLink() {
		this.waitForElementToBeVisible(footer_privacylink, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.click(footer_privacylink);
		return new GcPrivacyPage(driver);
	}

	public GcServiceUpdatesPage clickOnFooterServiceUpdatesLink() {
		this.waitForElementToBeVisible(footer_serviceupdateslink, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.click(footer_serviceupdateslink);
		return new GcServiceUpdatesPage(driver);
	}

	
	
}
