package com.ea.wwce.automation.gcn.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.gcn.config.GcnDataConstants;

import io.qameta.allure.Step;

public class AdminOppPaymentsPage extends GcnBasePageObjects{
	
	/*
	 * This class defines the UI elements and business methods of the Payments page for opportunities.
	 * Here the admin can select users and make payments via paypal or iProc to the game changers.
	 * pre-condition: the user should be estimated in budget estimation link.
	 */
	
	private static final Logger logger=Logger.getLogger(AdminOppPaymentsPage.class);
	
	
	public AdminOppPaymentsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	AdminOpportunityListPage adminOpportunityListPage= new AdminOpportunityListPage(driver);
	AdminOppPaymentsReviewPage adminOppPaymentsReviewPage=new AdminOppPaymentsReviewPage(driver);
	

	@FindBy(xpath="//span[@id='title']")
	WebElement oppTitle;
	
	@FindBy(xpath="//img[@class='game-image']")
	WebElement oppImg;
	
	@FindBy(xpath="//div[@class='col-sm-6']//span[2]/b")
	WebElement oppDate;
	
	@FindBy(xpath="//input[@id='searchuser']")
	WebElement searchBox;
	
	@FindBy(xpath="//span[@class='select-all']")
	WebElement selectAllChk;
		
	//@FindBy(xpath="//button/a[contains(text(),'CANCEL')]")
	@FindBy(xpath="//a[contains(text(),'CANCEL')]")
	WebElement cancelBtn;
	
	@FindBy(xpath="//button[contains(text(),'NEXT')]")
	WebElement nextBtn;
	
	@FindBy(xpath="//div[@class='col-md-2 tax-amount']//div[2]//span[1]")
	WebElement totalPaymentEntered;
			
	@FindBy(xpath="//app-root//app-display-message/div/div/div[2]/span")
	//@FindBy(xpath="//app-root[@ng-version='6.1.0']//app-display-message[@class='col-sm-7 message']")
	WebElement paySuccessMsg;
	
	@FindBy(xpath="//span[contains(text(),'Amount')]")
	WebElement amountCol;
	
	@FindBy(xpath="//b[contains(text(),'Payment Total')]")
	WebElement paymentTotalLabel;
	
	@FindBy(xpath="//div[@class='grid-bc']")
	WebElement usersFrame;
	
	@FindBy(xpath="//div[@class='admin-footer-logo-container display-inline forn-size']")
	WebElement footerEAtext;
	
	@FindBy(xpath="//div[@class='admin-footer-logo-container display-inline forn-size']/img")
	WebElement footerEAwhiteLogo;
	
	@FindBy(xpath="//div[@class='admin-footer-text']")
	WebElement footerAdmintext;
	
	@FindBy(xpath="//span[contains(text(),'Tax From')]")
	WebElement taxFormLabel;
	
	
	String usersXpath="//tbody/tr/td[1]/div[2]/a/span/strong";
	String usersXpathD="//tbody/tr[#]/td[1]/div[2]/a/span/strong";
	
	String taxStatusXpath="//tbody/tr/td[2]/div/span[2]";
	String taxStatusXpathD="//tbody/tr[#]/td[2]/div/span[2]";
	
	String checkBoxXpath="//tbody/tr/td[8]//label/span";
	String checkBoxXpathD="//tbody/tr[#]/td[8]//label/span";
	
	String checkBoxEnabledXpath="//tbody/tr/td[8]//label/span[@class='checkmark ng-star-inserted']";
	String checkBoxEnabledXpathD="//tbody/tr[#]/td[8]//label/span[@class='checkmark ng-star-inserted']";
	
	String disabledCheckBoxD="//tbody/tr/td[8]//label/span[@class='checkmarkdisabled ng-star-inserted']";
	
	String taxStatusNAXpath="//tbody/tr/td[2]/div/span[contains(text(),'N/A')]";
	String taxStatusNAXpathD="//tbody/tr[#]/td[2]/div/span[contains(text(),'N/A')]";
	
	String taxStatusIconXpath="//tbody/tr/td[2]/div/span[1]";
	String taxStatusIconXpathD="//tbody/tr[#]/td[2]/div/span[1]";
	
	String payStatusXpath="//tbody/tr/td[7]//span";
	String payStatusXpathD="//tbody/tr[#]/td[7]//span";
	
	String amtXpathD="//tbody/tr[#]/td[3]/div/span";
	
	String emailXpath="//tbody/tr/td[1]/div[2]//span[2]";
	String emailXpathD="//tbody/tr[#]/td[1]/div[2]//span[2]";
	
	String toolTipMsgXpath="//tbody/tr/td[8]/div[2]//span";
	
	String att_class="class";
	
	
	List<WebElement> users=new ArrayList<WebElement>();
	List<WebElement> users2=new ArrayList<WebElement>();
		
	public boolean isNextBtnDisplayed() {
		this.windowScrollDwn();
		logger.info("Check for Next Button");
		return this.isElementVisible(nextBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isPaySuccessMsgDisplayed() {
		logger.info("Check for Payment success message");
		this.windowScrollUp();
		this.waitForElementToBeVisible(paySuccessMsg, GcnDataConstants.IMPLICIT_TIMEOUT);
		return this.isElementVisible(paySuccessMsg, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	@Step("Verify EA Admin footer Text")
	public boolean isEaAdminFooterTextDisplayed() {
		boolean a=false;
		logger.info("Check for EA Admin Footer Text ");
		if(footerAdmintext.isDisplayed() && footerAdmintext.getText().equals(GcnDataConstants.eaAdminFooterText)) {
			a=true;
			logger.info("Footer Admin Text is Displayed");
		}else {
			logger.info("Footer Text is not Displayed");
			a=false;
		}
		return a;
	}
	
	@Step("Verify EA Footer Text")
	public boolean isEaFooterTextDisplayed() {
		boolean a=false;
		logger.info("Check for Powered by text ");
		if(footerEAtext.isDisplayed() && footerEAtext.getText().equals(GcnDataConstants.eaFooterText)) {
			a=true;
			logger.info("Footer Text is Displayed");
		}else {
			logger.info("Footer Text is Not Displayed");
			a=false;
		}
		return a;
	}
	
	public boolean isEaWhiteLogoDisplayed() {
		logger.info("Check for EA White Logo displayed in Footer");
		return this.isElementVisible(footerEAwhiteLogo, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	// UI Actions
	@Step("verify Navigation to Payments Page")
	public boolean verifyNavigationToPaymentsPage() {
		// method to check landing into Payments Page
		logger.info("Check Landing into Payments Page");
		this.verifyPageIsLoaded();
		boolean a=false;
		this.waitForElementToBeVisible(amountCol, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(searchBox, GcnDataConstants.IMPLICIT_TIMEOUT);
		//this.waitForElementToBeVisible(usersFrame, GcnDataConstants.IMPLICIT_TIMEOUT);
		
		if(this.isElementVisible(amountCol, GcnDataConstants.IMPLICIT_TIMEOUT) && 
				 this.isElementVisible(searchBox, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			logger.info("Landed into Payments Page");
			a=true;
		}else {
			logger.info("Error in Payments Page");
		}
		return a;
	}
	
	@Step("Verify Payments Page UI elements")
	public boolean verifyPaymentPageUi() {
		boolean a=false;
		this.verifyPageIsLoaded();
		this.waitForElementToBeVisible(oppTitle, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(oppImg, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(oppDate, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(selectAllChk, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(cancelBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(paymentTotalLabel, GcnDataConstants.IMPLICIT_TIMEOUT);
		
		if(this.isElementVisible(oppTitle, GcnDataConstants.IMPLICIT_TIMEOUT) && 
				this.isElementVisible(oppImg, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(oppDate, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(selectAllChk, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(cancelBtn, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(paymentTotalLabel, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			a=true;
		}
		
		return a;
	}
	
	public String getOppName() {
		logger.info("Get the Opportunity Name");
		this.waitForElementToBeVisible(oppTitle, GcnDataConstants.IMPLICIT_TIMEOUT);
		return this.oppTitle.getText();
	}
	
	public boolean checkOppNameFromOppListPage() {
		// method to check if opp name is same as in the Opportunity List Page
		logger.info("Check for Opportunity name in Payments Page matches the Name in Opportunity List Page");
		boolean a=false;
		this.verifyPageIsLoaded();
		return a;
	}
	
	public AdminOpportunityListPage clickOnCancel() {
		logger.info("Click on Cancel button");
		this.waitForElementToBeVisible(cancelBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.moveToElement(cancelBtn);
		this.windowScrollDwn();
		this.click(cancelBtn);
		return new AdminOpportunityListPage(driver);
	}
	
	public int noOfParticipants() {
		users=this.driver.findElements(By.xpath(usersXpath));
		return users.size();
	}
	
	public int noOfSuccessfullPayment() {
		users=this.driver.findElements(By.xpath(toolTipMsgXpath));
		return users.size();
	}
	
	@Step("Find no of Users to be Paid")
	public int noOfUsersToBePaid() {
		users=this.driver.findElements(By.xpath(usersXpath));
		logger.info("No of Users Row : "+ users.size());
		
		int noOfUsers=noOfParticipants();
		for(int i=0;i<noOfUsers;i++) {
			logger.info("User details are : "+users.get(i).getText());
			if(!users.get(i).getText().contains("Payment Pending") && !users.get(i).getText().contains("No PayPal Settings") && !users.get(i).getText().contains("Payment Successful") ) {
				
				//logger.info("Payment Not Initiated.");
				users2.add(users.get(i));
			}
			
		}
		logger.info("No of user which needs to be paid : "+users2.size());
		return users2.size();
		
	}
	
	@Step("Make Payments to All")
	public void payAll() {
		//noOfUsersToBePaid();
		this.waitForElementToBeVisible(nextBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.moveToElement(selectAllChk);
		this.click(selectAllChk);
		/*int sum=0;
		int noOfUsers=noOfParticipants();
		for(int i=0;i<noOfUsers;i++) {
				int x=getRandNum(4);
				if(!users.get(i).getText().contains("Payment Pending") && !users.get(i).getText().contains("No PayPal Settings") && !users.get(i).getText().contains("Payment Successful")) {
					users.get(i).findElement(By.xpath("//div[@class='grid-color']/div["+(i+1)+"]/div[3]/div/input")).clear();
					users.get(i).findElement(By.xpath("//div[@class='grid-color']/div["+(i+1)+"]/div[3]/div/input")).sendKeys(Integer.toString(x));
					sum=sum+x;
				}	
		}
		logger.info("Total Payment made now is : "+sum);*/
		this.moveToElement(nextBtn);
		this.click(nextBtn);
		this.verifyPageIsLoaded();
	}
	
	@Step("Make Single Payment")
	public boolean verifySinglePayment() {
		int noOfUsers=noOfParticipants();
		boolean isTrue=false;
		String s="";
		String amt="";
		String email="";
		List<WebElement> statusList=this.driver.findElements(By.xpath(payStatusXpath));
		List<WebElement> checkboxEnabledList=this.driver.findElements(By.xpath(checkBoxEnabledXpath));
		for(int i=0;i<noOfUsers;i++) {
			s=String.valueOf(i+1);
			if(!statusList.get(i+1).getText().contains("Payment Pending") && 
					!statusList.get(i+1).getText().contains("No PayPal Settings") && 
					!statusList.get(i+1).getText().contains("Payment Successful") && 
					statusList.get(i+1).getText().equals("") &&
					this.getAttributeValue(this.findDynamicElement(checkBoxXpathD, s), att_class).equals("checkmark ng-star-inserted")) {
				this.click(this.findDynamicElement(checkBoxEnabledXpathD, s));
				amt=this.findDynamicElement(amtXpathD, s).getText();
				amt=amt.substring(amt.indexOf('$')+1, amt.indexOf('.'));
				email=this.findDynamicElement(emailXpathD, s).getText();
				break;
			}
		}	
		this.click(nextBtn);
		this.waitForElementToBeVisible(adminOppPaymentsReviewPage.submitBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		String amt2=adminOppPaymentsReviewPage.totPaySummary.getText();
		amt2=amt2.substring(amt2.indexOf('$')+1, amt2.indexOf('.'));
		String email2=adminOppPaymentsReviewPage.paypalEmail.getText();
		
		if(amt.equals(amt2) && email.equals(email2)) {
			isTrue=true;
			logger.info("Amount and Email Matched");
		}
		return isTrue;
	}
	
	@Step("Make multiple payments")
	public void multiplePay() {
		int noOfUsers=noOfParticipants();
		for(int i=0;i<noOfUsers-1;i++) {
			logger.info("User details are : "+users.get(i).getText());
			if(!users.get(i).getText().contains("Payment Pending") && !users.get(i).getText().contains("No PayPal Settings") && !users.get(i).getText().contains("Payment Successful")) {
				users.get(i).findElement(By.xpath("//div[@class='grid-color']/div["+(i+1)+"]/div[3]/div/input")).clear();
				users.get(i).findElement(By.xpath("//div[@class='grid-color']/div["+(i+1)+"]/div[3]/div/input")).sendKeys("1");
				users.get(i).findElement(By.xpath("//div[@class='grid-color']/div["+(i+1)+"]/div[4]/div/div[2]/label/span")).click();
									
			}
		}
	}
	
	
	@Step("Select User by username")
	public void verifySelectingCheckBoxByUserName(String username) throws InterruptedException {
		String s="";
		int noOfUsers=noOfParticipants();
		List<WebElement> statusList=this.driver.findElements(By.xpath(payStatusXpath));
		List<WebElement> checkboxEnabledList=this.driver.findElements(By.xpath(checkBoxEnabledXpath));
		for(int i=0;i<noOfUsers;i++) {
			s=String.valueOf(i+1);
			if(!statusList.get(i+1).getText().contains("Payment Pending") && 
					!statusList.get(i+1).getText().contains("No PayPal Settings") && 
					!statusList.get(i+1).getText().contains("Payment Successful") && 
					statusList.get(i+1).getText().equals("") &&
					this.getAttributeValue(this.findDynamicElement(checkBoxXpathD, s), att_class).equals("checkmark ng-star-inserted") &&
					users.get(i).getText().contains(username)) {
				this.click(this.findDynamicElement(checkBoxEnabledXpathD, s));
									
				Thread.sleep(4000);
				break;
			}
		}
			
	}
	
	@Step("Verify the Payment Status from ToolTip")
	public void checkPaymentStatusToolTip() throws InterruptedException {
		String msg="";
		
		int noOfUsers=noOfParticipants();
		for(int i=0;i<noOfUsers;i++) {
			logger.info("User details are : "+users.get(i).getText());
			if(users.get(i).getText().contains("Payment Successful")) {
				String username=users.get(i).findElement(By.xpath("//div[@class='grid-color']/div["+(i+1)+"]/div[1]/div/div[2]/span/b")).getText();
				this.moveToElement(users.get(i).findElement(By.xpath("//div[@class='grid-color']/div["+(i+1)+"]/div[4]/div/div[2]/span[@class='fa fa-check-circle payicon']")));
				msg=users.get(i).findElement(By.xpath("//div[@class='grid-color']/div["+(i+1)+"]/div[4]/div/div[2]/bs-tooltip-container/div[2]")).getText();
				
				logger.info("Payment Info for User : ** "+username+" ** is as follows :"+msg);
				Thread.sleep(3000);
				//break;	
			}  
			
		}
		
	}
	
	@Step("Check Payment Status")
	public void checkPaymentStatus(String status) throws InterruptedException {
		
		int noOfUsers=noOfParticipants();
		for(int i=0;i<noOfUsers;i++) {
			logger.info("User details are : "+users.get(i).getText());
			
			if(users.get(i).getText().contains(status)) {
				String username=users.get(i).findElement(By.xpath("//div[@class='grid-color']/div["+(i+1)+"]/div[1]/div/div[2]/span/b")).getText();
					
				logger.info("Payment Status for User : ** "+username+" ** is :"+status);
			}else {
				logger.info("No User with Status : "+status);
			}
			
		}
	
	}
	
	@Step("Check payment status by username")
	public void checkPaymentStatusByUserName(String username) throws InterruptedException {
		String msg="";
		
		int noOfUsers=noOfParticipants();
		for(int i=0;i<noOfUsers;i++) {
			logger.info("User details are : "+users.get(i).getText());
			if(users.get(i).getText().contains("Payment Successful") && users.get(i).getText().contains(username)) {
				//String username=users.get(i).findElement(By.xpath("//div[@class='grid-color']/div["+(i+1)+"]/div[1]/div/div[2]/span/b")).getText();
				this.moveToElement(users.get(i).findElement(By.xpath("//div[@class='grid-color']/div["+(i+1)+"]/div[4]/div/div[2]/span[@class='fa fa-check-circle payicon']")));
				Thread.sleep(3000);
				msg=users.get(i).findElement(By.xpath("//div[@class='grid-color']/div["+(i+1)+"]/div[4]/div/div[2]/bs-tooltip-container/div[2]")).getText();
				
				logger.info("Payment Info for User : ** "+username+" ** is as follows :"+msg);
				//break;	
			}  
			
		}
		
	}
	
	@Step("Get Payment Value")
	public int getPaymentValue() {
		
		String payVal=totalPaymentEntered.getText();
		String payVal2=payVal.substring(payVal.indexOf('$')+1, payVal.indexOf('.'));
		logger.info("Payment summary as before submit : "+Integer.parseInt(payVal2));
		return Integer.parseInt(payVal2);
	}
	
	@Step("Verify Payment Currency type")
	public boolean checkPaymentCurrencyType() {
		// To check if payment is done in dollar i.e '$'
		boolean b=false;
		this.waitForElementToBeVisible(totalPaymentEntered, GcnDataConstants.IMPLICIT_TIMEOUT);
		String val=totalPaymentEntered.getText();
		if(val.contains("$")) {
			logger.info("Payment is Made in Dollar ($) ");
			b=true;
		}else {
			logger.info("Payment is Made in Other Currency");
		}
		return b;
	}
	
	
	public AdminOppPaymentsReviewPage goToPaymentPreview() {
		logger.info("Navigating to Payments Preview Screen");
		this.windowScrollDwn();
		this.windowScrollDwn();
		this.waitForElementToBeVisible(nextBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.click(nextBtn);
		this.verifyPageIsLoaded();
		return new AdminOppPaymentsReviewPage(driver);
	}
	
	@Step("Verify search user by username")
	public boolean searchUserDisplayed(String username) {
		logger.info("Search User name in Payment screen");
		this.sendKeys(searchBox, username);
		int noOfUsers=noOfParticipants();
		boolean a=false;
		if(noOfUsers>0) {
			for(int i=0;i<noOfUsers;i++) {
				if(users.get(i).getText().contains(username)) {
					logger.info(username+" : Searched User is Displayed.");
					a=true;
				}
			}
		}else {
			logger.info("No Search Result");
			a=false;
		}
		
		return a;
	}
	
	public String paySuccessMsgContent() {
		logger.info("Capture the Payment Success Message.");
		String m1=this.paySuccessMsg.getText();
		String m2=m1.substring(0, m1.lastIndexOf('.')+1);
		return m2;
	}
	
	public String searchBoxDefaultText() {
		logger.info("Check for Default Text value of search Box");
		return this.searchBox.getText();
	}
	
	@Step("Get Tax Document Status")
	public boolean getTaxDocStatus(String taxdocStatus) {
		boolean isAvailable=false;
		this.waitForElementToBeVisible(cancelBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		String uname="";
		String s="";
		logger.info("Check for Tax Document Status : "+taxdocStatus);
		int noOfUsers=noOfParticipants();		
		for(int i=0;i<noOfUsers;i++) {
			s=String.valueOf(i+1);
			if(this.findDynamicElement(taxStatusXpathD, s).getText().contains(taxdocStatus)) {
				uname=this.findDynamicElement(usersXpathD,s).getText();
				logger.info("Valid Tax Document for : "+uname);
				isAvailable=true;
				break;
			}else {
				logger.info("User Not found for Tax Doc Status :"+taxdocStatus);
			}
		}
		return isAvailable;
		//return uname;
	}
	
	@Step("Verify checkbox to be un-checkable for users with Non-Valid Tax Document")
	public boolean verifyCheckBoxForNonValidTaxDocUsers() {
		boolean isCheckable=true;
		boolean istrue=true;
		String s="";
		this.click(taxFormLabel);
		int noOfUsers=noOfParticipants();	
		for(int i=0;i<noOfUsers;i++) {
			s=String.valueOf(i+1);
			istrue=this.findDynamicElement(taxStatusIconXpathD, s).getText().contains("N/A")?false:true;
			
			if(istrue && (this.findDynamicElement(taxStatusXpathD, s).getText().contains("Incomplete") ||
					this.findDynamicElement(taxStatusXpathD, s).getText().contains("Missing"))) {
				if(this.getAttributeValue(this.findDynamicElement(disabledCheckBoxD, s), att_class).equals("checkmarkdisabled ng-star-inserted")) {
					isCheckable=false;
					//break;
				}
			}
		}
				
		return isCheckable;
	}
	
	public void clickSelectAllChkBox() {
		logger.info("Clicking on Select All check box");
		this.windowScrollUp();
		this.click(selectAllChk);
	}
	
			
}
