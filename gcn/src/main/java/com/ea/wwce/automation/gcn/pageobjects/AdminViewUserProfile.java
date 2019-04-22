package com.ea.wwce.automation.gcn.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.ea.wwce.automation.gcn.config.GcnDataConstants;

import io.qameta.allure.Step;

public class AdminViewUserProfile extends GcnBasePageObjects{
	
	/*
	 * This class defines the UI elements and business methods of the UserProfile page from admin view.
	 * Here the Admin can change profile / tax details and channel details of the game changers.
	 * 
	 */
	
	private static final Logger logger=Logger.getLogger(AdminViewUserProfile.class);
	
	public AdminViewUserProfile(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//img[@class='user-image-container']")
	WebElement userProfileImg;
	
	@FindBy(xpath="//div[@class='col-md-12']/h1")
	WebElement userNameLabel;
	
	@FindBy(xpath="//a[@id='side-navigation-user-profile']")
	WebElement profileLink;
	
	@FindBy(xpath="//a[@id='side-navigation-user-tax-details']")
	WebElement taxDetailsLink;
	
	@FindBy(xpath="//a[@id='side-navigation-user-channels']")
	WebElement channelsLink;
	
	@FindBy(xpath="//a[@id='side-navigation-user-opportunities']")
	WebElement opportunityLink;
	
	@FindBy(xpath="//span[@class='label label-success']")
	WebElement userStatus;
		
	@FindBy(xpath="//span[@class='label label-primary']")
	WebElement userTier;
	
	@FindBy(xpath="//form[@id='FormUser']/div[4]/div/div/div[3]/p")
	WebElement userName;
	
	@FindBy(xpath="//input[@id='email']")
	WebElement userEmail;
	
	@FindBy(xpath="//input[@id='paypalEmail']")
	WebElement paypalEmail;
	
	@FindBy(xpath="//input[@id='first_name']")
	WebElement firstName;
	
	@FindBy(xpath="//input[@id='last_name']")
	WebElement lastName;
	
	@FindBy(xpath="//label[contains(text(),'Date of Birth')]/following::input[@id='last_name']")
	WebElement dob;
	
	@FindBy(xpath="//input[@id='certificationNumber']")
	WebElement certNum;
	
	@FindBy(xpath="//select[@id='countryCode']")
	WebElement countrySel;
	
	@FindBy(xpath="//select[@id='contentLanguage']")
	WebElement contLangSel;
	
	@FindBy(xpath="//select[@id='language']")
	WebElement commLangSel;
	
	@FindBy(xpath="//div[@class='form-group col-md-12']/div/div/div[1]/p[1]")
	WebElement territory;
	
	@FindBy(xpath="//form[@class='form']/div[2]/div/div/div[2]/p[1]")
	WebElement uId;
	
	@FindBy(xpath="//form[@class='form']/div[2]/div/div/div[2]/p[2]")
	WebElement createdDate;
	
	@FindBy(xpath="//form[@class='form']/div[2]/div/div/div[2]/p[3]")
	WebElement loginDate;
	
	@FindBy(xpath="//select[@id='pointOfContact']")
	WebElement poc;
		
	@FindBy(xpath="//a[@id='button_save_user_profile']")
	WebElement saveBtn;
	
	@FindBy(xpath="//button[@type='button' and contains(text(),'ok')]")
	WebElement okBtn;
	
	@FindBy(xpath="//select[@id='vendor']")
	WebElement payMeth;
	
	@FindBy(xpath="//select[@id='tShirtSize']")
	WebElement tSizeList;
	
	
	
	String attribute_value="value";
	
	Select c1,l1,l2,pM,tsl;
	
	
	public String getFullName() {
		logger.info("Get the Full Name of User");
		logger.info("First Name :"+this.getAttributeValue(firstName, attribute_value));
		logger.info("Last Name :"+this.getAttributeValue(lastName, attribute_value));
		return (this.getAttributeValue(firstName, attribute_value)+" "+this.getAttributeValue(lastName, attribute_value));
	}
	
	@Step("verify Availability of Left Navigation Links")
	public boolean verifyLeftNavLinks() {
		boolean isDisplayed=false;
		this.waitForElementToBeVisible(saveBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		if(profileLink.isDisplayed() && taxDetailsLink.isDisplayed() &&
				channelsLink.isDisplayed() && opportunityLink.isDisplayed()) {
			isDisplayed=true;
		}
		
		return isDisplayed;
	}
	
	@Step("verify Availability of basic User Details")
	public boolean verifyUserProfileDetails() {
		boolean isDisplayed=false;
		if(this.isElementVisible(firstName, GcnDataConstants.IMPLICIT_TIMEOUT) && 
				this.isElementVisible(lastName, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(userProfileImg, GcnDataConstants.IMPLICIT_TIMEOUT)&&
				this.isElementVisible(userStatus, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(userTier, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			if(userStatus.getText().contains("Active") && userTier.getText().contains("tier")) {
				isDisplayed=true;
			}
			
		}
		
		return isDisplayed;
	}
	
	@Step("Verify Personal Details of Game Changer")
	public boolean verifyUserPersonalDetails() {
		boolean isTrue=false;
		c1=new Select(countrySel);
		l1=new Select(commLangSel);
		l2=new Select(contLangSel);
		if(!dob.getText().equals(" ") &&
				certNum.isDisplayed() &&
				!c1.getFirstSelectedOption().getText().equals(" ") &&
				!l1.getFirstSelectedOption().getText().equals(" ") &&
				!l2.getFirstSelectedOption().getText().equals(" ")) {
			isTrue=true;
		}
		
		return isTrue;
	}
	
	@Step("Verify ID,Date and Country Details")
	public boolean verifyTerritoryIdDatePocDetails() {
		boolean isTrue=false;
		Select p1=new Select(poc);
		
		if(!uId.getText().equals(" ") && 
				!createdDate.getText().equals(" ") &&
				!loginDate.getText().equals(" ") && 
				!p1.getFirstSelectedOption().getText().equals(" ") &&
				!territory.getText().equals(" ")) {
			isTrue=true;
		}		
		return isTrue;
	}
	
	@Step("Verify updation of Certificate Num, Dob, contact Email")
	public boolean verifyCertNumDobContEmailUpdation(String certN,String Dob,String cEmail,String pEmail) throws InterruptedException {
		boolean isUpdated=false;
		this.waitForElementToBeVisible(saveBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.sendKeys(dob, Dob);
		this.sendKeys(userEmail, cEmail);
		this.sendKeys(paypalEmail, pEmail);
		this.moveToElement(certNum);
		this.sendKeys(certNum, certN);
		this.click(saveBtn);
		this.waitForElementToBeVisible(okBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.click(okBtn);
		Thread.sleep(3000);
		if(dob.getAttribute(attribute_value).contains(Dob) &&
				userEmail.getAttribute(attribute_value).contains(cEmail) &&
				paypalEmail.getAttribute(attribute_value).contains(pEmail) &&
				certNum.getAttribute(attribute_value).contains(certN)) {
			isUpdated=true;
		}
		
		return isUpdated;
	}
	
	@Step("Verify Territory Updation")
	public boolean verifyTerritoryUpdation(String country,String terri) throws InterruptedException {
		boolean isTrue=false;
		this.waitForElementToBeVisible(saveBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		c1=new Select(countrySel);
		c1.selectByVisibleText(country);
		this.click(saveBtn);
		this.waitForElementToBeVisible(okBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.click(okBtn);
		Thread.sleep(3000);
		if(c1.getFirstSelectedOption().getText().contains(country) && territory.getText().contains(terri)) {
			isTrue=true;
		}
		return isTrue;
	}
	
	@Step("Verify Full Name and Payment Type Updation")
	public boolean verifyFullnamePaymentTypeUpdation(String fn,String ln,String pt) throws InterruptedException {
		boolean isTrue=false;
		this.waitForElementToBeVisible(saveBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		pM=new Select(payMeth);
		this.sendKeys(firstName, fn);
		this.sendKeys(lastName, ln);
		pM.selectByVisibleText(pt);
		this.click(saveBtn);
		this.waitForElementToBeVisible(okBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.click(okBtn);
		Thread.sleep(3000);
		if(firstName.getAttribute(attribute_value).contains(fn) &&
				lastName.getAttribute(attribute_value).contains(ln) &&
				pM.getFirstSelectedOption().getText().contains(pt)) {
			isTrue=true;
		}
		
		return isTrue;
	}
	
	@Step("Verify language and Tshirt size updation")
	public boolean verifyLangShirtSizeUpdation() {
		boolean isUpdated=false;
		tsl=new Select(tSizeList);
		List<WebElement> sl=tsl.getOptions();
		List<String> tsize=new ArrayList<String>();
		for(int i=0;i<sl.size();i++) {
			System.out.println("Tshirt size are : "+sl.get(i).getText());
			tsize.add(sl.get(i).getText());
		}
		System.out.println("Size list is : "+tsize);
		return isUpdated;
	}
	
	@Step("Verify UserName is Non Editable")
	public boolean verifyUserNameEditable() {
		boolean isEditable=true;
		this.waitForElementToBeVisible(saveBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		try {
			this.sendKeys(userName, "hello");
			isEditable=true;
		} catch(InvalidElementStateException e) {
			logger.info("First Name field is Not Editable");
			isEditable=false;
		}
		return isEditable;
	}
	
	
	

}
