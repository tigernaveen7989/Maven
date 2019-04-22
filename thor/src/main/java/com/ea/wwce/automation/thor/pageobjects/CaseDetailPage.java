package com.ea.wwce.automation.thor.pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.thor.config.ThorDataConstants;

import io.qameta.allure.Step;


/**
 * 
 * @author mohan
 * @description Thor Advisor Case Detail page object
 */

public class CaseDetailPage extends ThorBasePageObject{
	
	public CaseDetailPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver,this);
	}
	
    private static final Logger logger = Logger.getLogger(CaseDetailPage.class);
   	@FindBy(xpath="(//span[@class='slds-text-heading_medium'])[3]")
	WebElement lblCaseDetailHeading;
   	@FindBy(xpath="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//div[contains(text(),'Case Number')]/following::div")
   	WebElement lblCaseID;
	@FindBy(xpath="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//div[contains(text(),'Product')]/following::input[1]")
	WebElement txtProduct;
	@FindBy(xpath="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//div[contains(text(),'Platform')]/following::input[1]")
	WebElement txtPlatform;
	@FindBy(xpath="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//div[contains(text(),'Category')]/following::input[1]")
	WebElement txtCategory;
	@FindBy(xpath="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//div[contains(text(),'Content Type')]/following::input[1]")
	WebElement txtContentType;
	@FindBy(xpath="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//div[contains(text(),'Petition UUID')]/following::div[1]")
	WebElement lblPetitionUUID;
	@FindBy(xpath="//button[contains(@title,'Close Case')]")
	WebElement btnCloseCase;
		
	
	//Get Case Detail Page heading
	@Step("Method to get Case Detail page heading")
	public String getCaseDetailPageHeading() {
		String strText="";
		try {
			Thread.sleep(5000);
			boolean flag=this.isElementVisible(lblCaseDetailHeading, 10);
			if (flag)
				strText=this.getText(lblCaseDetailHeading);
		}
		catch (Exception e) {
			logger.info("Failed to get Case Detail Page Heading  " + e.getMessage());
		}
		return strText;
	}
	
	//Get Case ID from Case Detail Page 
	@Step("Method to get Case ID fromDetail page")
	public String getCaseID() {
		String strText="";
		try {
			boolean flag=this.isElementVisible(lblCaseID, 10);
			if (flag)
				strText=this.getText(lblCaseID);
		}
		catch (Exception e) {
			logger.info("Failed to get Case ID  " + e.getMessage());
		}
		return strText;
	}
	
	//Get the Product name value from Case Detail page
	@Step("Method to get the product name ")
	public String getProductName() {
		String strText="";
		try {
			strText=this.getAttributeValue(txtProduct, "value");
		}
		catch (Exception e) {
			logger.info("Failed to get Product name  " + e.getMessage());
		}
		return strText;
	}
	
	//Get the Platform name value from Case Detail page
	@Step("Method to get the Platform name ")
	public String getPlatformName() {
		String strText="";
		try {
			strText=this.getAttributeValue(txtPlatform, "value");
		}
		catch (Exception e) {
			logger.info("Failed to get Platform name " + e.getMessage());
		}
		return strText;
	}
	
	//Get the Category name value from Case Detail page
	@Step("Method to get the Category name ")
	public String getCategoryName() {
		String strText="";
		try {
			strText=this.getAttributeValue(txtCategory, "value");
		}
		catch (Exception e) {
			logger.info("Failed to get Category name " + e.getMessage());
		}
		return strText;
	}
	
	//Get the ContentType name value from Case Detail page
	@Step("Method to get the Content Type  ")
	public String getContentType() {
		String strText="";
		try {
			strText=this.getAttributeValue(txtContentType, "value");
		}
		catch (Exception e) {
			logger.info("Failed to get Content Type " + e.getMessage());
		}
		return strText;
	}
	
	//Get the Case UUID from Case Detail page
	@Step("Method to get the Case UUID  ")
	public String getCaseUUID() {
		String strText="";
		try {
			strText=this.getText(lblPetitionUUID);
		}
		catch (Exception e) {
			logger.info("Failed to get Case UUID " + e.getMessage());
		}
		return strText;
	}
	
		
			
	//Method for closing Case Detail Tab
	@Step("Method for close Case Detail page")
	public void closeCaseDetail() {
		try {
			this.click(btnCloseCase);
			Thread.sleep(3000);
		}
		catch (Exception e) {
			logger.info("Failed to Close Case Detail Page" + e.getMessage());
		}
		
	}
	
		
	
	
	
	
}
