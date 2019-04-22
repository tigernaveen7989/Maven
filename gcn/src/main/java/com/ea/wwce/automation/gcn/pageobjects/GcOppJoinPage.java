package com.ea.wwce.automation.gcn.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.gcn.config.GcnDataConstants;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import io.qameta.allure.Step;


public class GcOppJoinPage  extends GcnBasePageObjects{
	
	/*
	 * This class defines the UI elements of the Opportunity joining page for game changers.
	 * 
	 */
	
	private static final Logger logger=Logger.getLogger(GcOppJoinPage.class);

	public GcOppJoinPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	//GcOppDetailsPage gcOppDetailsPage = new GcOppDetailsPage(driver);
	
	@FindBy(xpath="//div[@class='modal-header']//h1")
	WebElement joinOppHeader;
	
	@FindBy(xpath="//div[@class='modal-header']//h1/small")
	WebElement oppTitle;
	
	@FindBy(xpath="//form[@id='FormOpportunityRegister']/div[1]/div[1]/input")
	WebElement depAirport;
	
	@FindBy(xpath="//form[@id='FormOpportunityRegister']/div[1]/div[2]/div/input[1]")
	WebElement mob; // month of birth
	
	@FindBy(xpath="//form[@id='FormOpportunityRegister']/div[1]/div[2]/div/input[2]")
	WebElement dob;
	
	@FindBy(xpath="//form[@id='FormOpportunityRegister']/div[1]/div[2]/div/input[3]")
	WebElement yob;
	
	@FindBy(xpath="//form[@id='FormOpportunityRegister']/div[2]/div[1]/input")
	WebElement fName;
	
	@FindBy(xpath="//form[@id='FormOpportunityRegister']/div[2]/div[2]/input")
	WebElement lName;
	
	@FindBy(xpath="//form[@id='FormOpportunityRegister']/div[3]/div[1]/input")
	WebElement passportNum;
	
	@FindBy(xpath="//form[@id='FormOpportunityRegister']/div[3]/div[2]/div/input[1]")
	WebElement mExp;
	
	@FindBy(xpath="//form[@id='FormOpportunityRegister']/div[3]/div[2]/div/input[2]")
	WebElement dExp;
	
	@FindBy(xpath="//form[@id='FormOpportunityRegister']/div[3]/div[2]/div/input[3]")
	WebElement yExp;
	
	@FindBy(xpath="//form[@id='FormOpportunityRegister']/div[4]/div[1]/input")
	WebElement pIssCont;
	
	@FindBy(xpath="//form[@id='FormOpportunityRegister']/div[4]/div[2]/input")
	WebElement phNumber;
	
	@FindBy(xpath="//ea-checkbox[starts-with(@name,'FormOpportunityRegister[agree_terms]')]//iron-icon[1]")
	WebElement agreeCheckbox;
	
	@FindBy(xpath="//input[@value='Complete Registration']")
	WebElement regBtn;
	
	GcOppDetailsPage gcOppDetailsPage=new GcOppDetailsPage(driver);
			
	// UI Actions
		
	@Step("verify Navigation to Opportunity Joining Page")
	public boolean verifyNavigationToOppJoiningPage() {
		boolean a=false;
		this.verifyPageIsLoaded();
		if(this.isElementVisible(joinOppHeader, GcnDataConstants.IMPLICIT_TIMEOUT) && this.isElementVisible(regBtn, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			logger.info("Landed to Joining Page");
			a=true;
		}else {
			logger.info("Error landing to Joining Page");
		}
		return a;
	}
	
	@Step("Verify Joining an Opportunity")
	public boolean verifyJoiningToOpportunity(String depAir,String dob_m,String dob_d,String yob_y,
			String fname,String lname,String passNum,String passExpM,String passExpD,
			String passExpY,String passCont,String phNum) throws InterruptedException {
		boolean a=false;
		try {
			Thread.sleep(3000);
			this.verifyPageIsLoaded();
			this.sendKeys(depAirport, depAir);
			this.sendKeys(mob, dob_m);
			this.sendKeys(dob, dob_d);
			this.sendKeys(yob, yob_y);
			this.sendKeys(fName, fname);
			this.sendKeys(lName, lname);
			this.sendKeys(passportNum, passNum);
			
			
			this.sendKeys(mExp, passExpM);
			this.sendKeys(dExp, passExpD);
			this.sendKeys(yExp, passExpY);
			
			this.sendKeys(pIssCont, passCont);
			this.sendKeys(phNumber, phNum);
			this.click(agreeCheckbox);
						
			this.click(regBtn);
			
			gcOppDetailsPage.verifyNavigationToOppoDetailsPage();
			
			if(gcOppDetailsPage.joinedChkbox.isDisplayed()) {
				a=true;
			}
			
		}catch(ElementNotFoundException e) {
			logger.info("Element not found"+e.getMessage());
		}
		return a;
		
	}	
	
}
