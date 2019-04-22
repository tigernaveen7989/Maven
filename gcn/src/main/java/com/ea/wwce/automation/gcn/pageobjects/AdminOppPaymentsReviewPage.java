package com.ea.wwce.automation.gcn.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.gcn.config.GcnDataConstants;

import io.qameta.allure.Step;

public class AdminOppPaymentsReviewPage extends GcnBasePageObjects{ 
	
	/*
	 * This class defines the UI elements and business methods for the Payments Review Page.
	 * Here the Admin reviewes the payments for all selected game changers before
	 * submitting the request for payment.
	 * 
	 */

	private static final Logger logger=Logger.getLogger(AdminOppPaymentsReviewPage.class);
	
	public AdminOppPaymentsReviewPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//b[contains(text(),'Payment Review')]")
	WebElement pageLabel;
	
	@FindBy(xpath="//div[@class='div-opp-name']/span")
	WebElement oppName;
	
	@FindBy(xpath="//input[@placeholder='Add a payment description (Optional)']")
	WebElement payDesc;
	
	@FindBy(xpath="//button[contains(text(),'Back')]")
	WebElement backBtn;
	
	@FindBy(xpath="//button[contains(text(),'Submit Payment')]")
	WebElement submitBtn;
	
	@FindBy(xpath="//ul[@class='list-group']//li[3]//div[2]//span/strong")
	WebElement paySummary;
	
	@FindBy(xpath="//div[@class='row button-panel']//div[2]//div[2]/strong")
	WebElement totPaySummary;
	
	@FindBy(xpath="//ul[@class='list-group']//li[1]//div[2]/span/strong")
	WebElement paymentType;
	
	@FindBy(xpath="//ul[@class='list-group']//li[2]//div[2]/span/strong")
	WebElement totalUsers;
	
	@FindBy(xpath="//tbody/tr[2]/td[2]/span")
	WebElement paypalEmail;
	
	String paypalEmailXpath="//tbody/tr/td[2]/span";
	
	String defaultPayDescMsg="Add a payment description (Optional)";
	
	String attribute_placeholder="placeholder";
	
	// Below commented code will be removed before next PR after re-usability analysis.
	
	// UI Checks
	
	/*public boolean isPageLabelDisplayed() {
		logger.info("Check for Opportunity Payment Preview Page Label");
		return this.isElementVisible(pageLabel, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isOppNameDisplayed() {
		logger.info("Check for Opportunity Name.");
		return this.isElementVisible(oppName, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isPayDescriptionDisplayed() {
		logger.info("Check for Payment Description Text Box");
		return this.isElementVisible(payDesc, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isBackBtnDisplayed() {
		logger.info("Check for Back Button");
		return this.isElementVisible(backBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isSubmitBtnDisplayed() {
		logger.info("Check for Submit Button");
		return this.isElementVisible(submitBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
	}*/
	
	@Step("Verify total payment summary is displayed")
	public boolean isTotalPaymentSummaryDisplayed() {
		boolean a=false;
		logger.info("Check for Total Payment Summary");
		String val2=totPaySummary.getText();
		String val22=val2.substring(val2.indexOf('$')+1, val2.indexOf('.'));
		if(Integer.parseInt(val22)>0) {
			a=true;
		}else {
			a=false;
		}
		
		return a;
	}
	
	
	// UI Actions
	
	@Step("Verify Navigation to Payments review page")
	public boolean verifyNavigationToPaymentsReviewPage() {
		// method to check landing into Opportunity Payments Review Page
		logger.info("Check for landing into Opp Payments Review Page");
		boolean a=false;
		this.verifyPageIsLoaded();
		if(this.isElementVisible(payDesc, GcnDataConstants.IMPLICIT_TIMEOUT) && this.isElementVisible(submitBtn, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			logger.info("Landed into Payments Review Page");
			a=true;
		}else {
			logger.info("Error in Payments Review Page");
		}
		return a;
	}
	
	
	
	public AdminOppPaymentsPage clickOnBackBtn() {
		logger.info("Navigating to Payments Page.");
		this.windowScrollDwn();
		this.click(backBtn);
		return new AdminOppPaymentsPage(driver);
	}
	
	public void setPayDesc(String desc) {
		logger.info("Set the Payment Description.");
		this.sendKeys(payDesc, desc);
	}
	
	public AdminOppPaymentsPage clickOnSubmitBtn() {
		logger.info("Click on Submit Payment Button");
		this.waitForElementToBeVisible(submitBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.windowScrollDwn();
		this.click(submitBtn);
		return new 	AdminOppPaymentsPage(driver);	
	}
	
	public String getOppNameInPreviewPage() {
		logger.info("Get Opportunity Name from Payments Preview Page");
		return this.oppName.getText();
	}
	
	@Step("Get Paym summary Value")
	public int checkPaySummary() {
		this.windowScrollDwn();
		String val1=paySummary.getText();
		String val2=totPaySummary.getText();
		
		String val11=val1.substring(val1.indexOf('$')+1, val1.indexOf('.'));
		String val22=val2.substring(val2.indexOf('$')+1, val2.indexOf('.'));
		if(val11.equalsIgnoreCase(val22)) {
			logger.info("Summary pay and Total Pay is Matched");
		}else {
			logger.info("Summary Pay and Total Pay is Mis-Matched");
		}
		return Integer.parseInt(val22);
	}
		
	@Step("Verify Default Payment Description Msg")
	public boolean checkDefPayDescMsg() {
		boolean a=false;
		if(this.getAttributeValue(payDesc, attribute_placeholder).equalsIgnoreCase(defaultPayDescMsg)) {
			logger.info("Correct default Payment Description text is Displayed");
			a=true;
		}else {
			logger.info("Incorrect default Payment Description text is Displayed");
		}
		return a;
	}
	
	@Step("Verify Payment Currency type")
	public boolean checkPaymentCurrencyType() {
		// To check if payment is done in dollar i.e '$'
		boolean b=false;
		String val=totPaySummary.getText();
		if(val.contains("$")) {
			logger.info("Payment is Made in Dollar ($) ");
			b=true;
		}else {
			logger.info("Payment is Made in Other Currency");
		}
		return b;
	}
	
	@Step("Verify Payments Preivew page UI")
	public boolean verifyPaymentPreivewPageUi() {
		boolean a=false;
		this.waitForElementToBeVisible(pageLabel, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(oppName, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(payDesc, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(backBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(submitBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		
		if(this.isElementVisible(pageLabel, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(oppName, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(payDesc, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(backBtn, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(submitBtn, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			a=true;
		}
		return a;
	}
	
	public int getPaySummary() {
		// to get the payment summary value
		logger.info("Getting value from payment summary");
		String val1=paySummary.getText();
		String val11=val1.substring(val1.indexOf('$')+1, val1.indexOf('.'));
		return Integer.parseInt(val11);
	}
	
	public int getTotalUsers() {
		// to get the Total no of users from payment preview screen.
		logger.info("Getting value of Total No of Users");
		return Integer.parseInt(totalUsers.getText());
	}
	
	public String getPaymentType() {
		// get the payment type.
		logger.info("Getting the Payment Type");
		return paymentType.getText();
	}
}
	
	