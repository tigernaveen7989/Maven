package com.ea.wwce.automation.gcn.pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.gcn.config.GcnDataConstants;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import io.qameta.allure.Step;


public class GcOpportunityPage  extends GcnBasePageObjects{
	
	/*
	 * This class defines the UI elements of the Opportunity list page for game changers.
	 * game change can navigate to specific opportunity or search for opportunities.
	 */
	
	private static final Logger logger=Logger.getLogger(GcOpportunityPage.class);

	public GcOpportunityPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	GcOppDetailsPage gcOppDetailsPage = new GcOppDetailsPage(driver);
	
	@FindBy(xpath="//*[@id='site']/ea-section/ea-section-column/div[1]/div[1]/ea-select/span[2]")
	WebElement myGamesFilter;
	
	@FindBy(xpath="//*[@id='site']/ea-section/ea-section-column/div[1]/div[2]/ea-select/span[2]")
	WebElement allTypesFilter;
	
	@FindBy(xpath="//*[@id='site']/ea-section/ea-section-column/div[1]/div[3]/ea-select/span[2]")
	WebElement currentFilter;
	
	@FindBy(xpath="//*[@id='placeholder_opportunities']/ea-containers/ea-container/a/div/div[2]/div[1]/h3[contains(text(),'Sim City Launch')]")
	WebElement opportunityNHL7Opp9;
	
	@FindBy(xpath="//div[@id='site']/ea-header/div/div[2]/h1")
	WebElement oppLabel;
		
	String joinedOppXpath="//img[@src='images/ok.png']/ancestor::div[3]/div[2]/div/h3";
	
	String allOppXpath="//a[@class='opportunities-list-link']/div/div[2]/div/h3";
	
	String joinBtnXpath="//span[@id='Label' and contains(text(),'Join')]";
	
	List <WebElement> joinedOppList;
	List <WebElement> allOppList;
	List <WebElement> notJoinedOppList;
		
	// UI Actions
		
	@Step("verify Navigation to Opportunity list Page")
	public boolean verifyNavigationToOpportunityPage() {
		// method to click on opportunity link and check user lands into Opportunity Page.
		logger.info("Navigating to Opportunity Page");
		boolean a=false;
		this.verifyPageIsLoaded();
		if(this.isElementVisible(oppLabel, GcnDataConstants.IMPLICIT_TIMEOUT) && this.isElementVisible(myGamesFilter, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			logger.info("Landed to Opportunity Page");
			a=true;
		}else {
			logger.info("Error landing to Opportunity Page");
		}
		return a;
	}
	
	@Step("Verify Navigation to Joined Opportunity")
	public boolean verifyNavigationToJoinedOpportunity() throws InterruptedException {
		boolean a=false;
		try {
			Thread.sleep(3000);
			joinedOppList=this.driver.findElements(By.xpath(joinedOppXpath));
			String title=joinedOppList.get(1).getText();
			this.click(joinedOppList.get(1));
			this.verifyPageIsLoaded();
			this.waitForElementToBeVisible(gcOppDetailsPage.oppTitle, GcnDataConstants.IMPLICIT_TIMEOUT);
			if(title.equalsIgnoreCase(gcOppDetailsPage.oppTitle.getText()) &&
					this.isElementInvisibile(By.xpath(joinBtnXpath), GcnDataConstants.IMPLICIT_TIMEOUT)) {
				a=true;
			}
			
		}catch(ElementNotFoundException e) {
			logger.info("Element not found"+e.getMessage());
		}
		return a;
		
	}
	
	@Step("Verify Navigation to Not Joined Opportunity")
	public boolean verifyNavigationToNotJoinedOpp() throws InterruptedException {
		boolean a=false;
		Thread.sleep(3000);
		joinedOppList=this.driver.findElements(By.xpath(joinedOppXpath));
		notJoinedOppList=allOppList=this.driver.findElements(By.xpath(allOppXpath));

		notJoinedOppList.removeAll(joinedOppList);
		String title=notJoinedOppList.get(0).getText();
		this.click(notJoinedOppList.get(0));
		this.verifyPageIsLoaded();
		this.waitForElementToBeVisible(gcOppDetailsPage.oppTitle, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(gcOppDetailsPage.joinBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		
		if(title.equalsIgnoreCase(gcOppDetailsPage.oppTitle.getText()) &&
				this.isElementVisible(gcOppDetailsPage.joinBtn, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			a=true;			
		}
		
		return a;
	}
	
	
}
