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
 * @description Thor Player Search page object
 */

public class PlayerSearchPage extends ThorBasePageObject{
	
	public PlayerSearchPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver,this);
	}
	
    private static final Logger logger = Logger.getLogger(PlayerSearchPage.class);
   	@FindBy(xpath="(//h1[contains(text(),'Target Player Details')]/following::div[contains(text(),'Nucleus Persona')])[3]/following-sibling::div")
	WebElement txtNucleusPersona;
	@FindBy(xpath="(//h1[contains(text(),'Target Player Details')]/following::div[contains(text(),'Nucleus Persona ID')])[2]/following-sibling::div")
	WebElement txtNucleusPersonaId;
	@FindBy(xpath="(//h1[contains(text(),'Target Player Details')]/following::div[contains(text(),'Nucleus Account ID')])[2]/following-sibling::div")
	WebElement txtNucleusAcctID;
	@FindBy(xpath="(//h1[contains(text(),'Target Player Details')]/following::div[contains(text(),'Email ID')])[2]/following-sibling::div")
	WebElement txtEmailID;
	@FindBy(xpath="(//h1[contains(text(),'Target Player Details')]/following::div[contains(text(),'Nucleus Account Status')])[2]/following-sibling::div")
	WebElement txtAccountStatus;
	@FindBy(xpath="(//h1[contains(text(),'Target Player Details')]/following::div[contains(text(),'Security State')])[2]/following-sibling::div")
	WebElement txtSecurityState;
	@FindBy(xpath="(//h1[contains(text(),'Target Player Details')]/following::div[contains(text(),'Customer Age')])[2]/following-sibling::div")
	WebElement txtCustomerAge;
	@FindBy(xpath="(//h1[contains(text(),'Target Player Details')]/following::div[contains(text(),'Customer Language')])[2]/following-sibling::div")
	WebElement txtCustomerLan;
	@FindBy(xpath="(//h1[contains(text(),'Target Player Details')]/following::div[contains(text(),'Account Age')])[2]/following-sibling::div")
	WebElement txtAccountAge;
	@FindBy(xpath="//button[contains(@title,'Close Account')]")
	WebElement btnSearchPageClose;
	
	@FindBy(xpath="//div[text()='Create a New Case']/following::input[@name='petitionAction']")
	WebElement txtPetitionAction;
	@FindBy(xpath="//lightning-base-combobox-item[@data-value='create-petition']")
	WebElement liPA_CreatePetition;
	@FindBy(xpath="//lightning-base-combobox-item[@data-value='create-dispute']")
	WebElement liPA_CreateDispute;
	@FindBy(xpath="//div[text()='Add Details']/following::input[@placeholder='Select a Product']")
	WebElement txtProduct;
	@FindBy(xpath="//div[text()='Add Details']/following::input[@placeholder='Select a Platform']")
	WebElement txtPlatform;
	@FindBy(xpath="//div[text()='Add Details']/following::input[@placeholder='Choose a Category']")
	WebElement txtCategory;
	@FindBy(xpath="//div[text()='Add Details']/following::input[@placeholder='Choose a User Content Type(Optional)']")
	WebElement txtContentType;
	String ComboBox_Val_Xpath="//div[text()='Add Details']/following::span[text()='#']";
	@FindBy(xpath="//div[text()='Add Details']/following::input[@name='subject']")
	WebElement txtSubject;
	@FindBy(xpath="//div[text()='Add Details']/following::textarea")
	WebElement txtCaseNotes;
	@FindBy(xpath="//button[@title='Create & Open Case']")
	WebElement btnCreateOpenCase;
	@FindBy(xpath="(//lightning-spinner[@class='slds-spinner_container slds-hide'])[1]")
	WebElement spinner;
	
		
	
	//Get the Email value from Player Search page
	@Step("Method to get Target Player EmailID Details")
	public String getPlayerEmailID() {
		String strText="";
		try {
			boolean flag=this.isElementVisible(btnSearchPageClose, 8);
			if (flag)
				strText=this.getText(txtEmailID);
		}
		catch (Exception e) {
			logger.info("Failed to get Player Email Details  " + e.getMessage());
		}
		return strText;
	}
	
	//Get the Persona name value from Player Search page
	@Step("Method to get the Target Player persona name Details")
	public String getPlayerPersonaName() {
		String strText="";
		try {
			boolean flag=this.isElementVisible(btnSearchPageClose, 8);
			if (flag)
				strText=this.getText(txtNucleusPersona);
		}
		catch (Exception e) {
			logger.info("Failed to get Player Persona name Details  " + e.getMessage());
		}
		return strText;
	}
		
	//Get the Persona ID value from Player Search page
	@Step("Method to get the Target Player persona ID Details")
	public String getPlayerPersonaID() {
		String strText="";
		try {
			boolean flag=this.isElementVisible(btnSearchPageClose, 8);
			if (flag)
				strText=this.getText(txtNucleusPersonaId);
		}
		catch (Exception e) {
			logger.info("Failed to get Player Persona ID Details  " + e.getMessage());
		}
		return strText;
	}
	
	//Get the Account ID value from Player Search page
	@Step("Method to get the Target PlayerAccount ID Details")
	public String getPlayerAccountID() {
		String strText="";
		try {
			boolean flag=this.isElementVisible(btnSearchPageClose, 8);
			if (flag)
				strText=this.getText(txtNucleusAcctID);
		}
		catch (Exception e) {
			logger.info("Failed to get Player Account ID Details  " + e.getMessage());
		}
		return strText;
	}
		
	//Get the Account Status value from Player Search page
	@Step("Method to get the  Player Account status Details")
	public String getPlayerAccountStatus() {
		String strText="";
		try {
			boolean flag=this.isElementVisible(btnSearchPageClose, 6);
			if (flag)
				strText=this.getText(txtAccountStatus);
		}
		catch (Exception e) {
			logger.info("Failed to get Player Account Status Details  " + e.getMessage());
		}
		return strText;
	}
	
	//Get the Security State value from Player Search page
	@Step("Method to get the  Security State Details")
	public String getPlayerSecurityState() {
		String strText="";
		try {
			boolean flag=this.isElementVisible(btnSearchPageClose, 8);
			if (flag)
				strText=this.getText(txtSecurityState);
		}
		catch (Exception e) {
			logger.info("Failed to get Player Security State Details  " + e.getMessage());
		}
		return strText;
	}
	
	//Get the Customer Language value from Player Search page
	@Step("Method to get the  Customer Language Details")
	public String getCustomerLanguage() {
		String strText="";
		try {
			boolean flag=this.isElementVisible(btnSearchPageClose, 8);
			if (flag)
				strText=this.getText(txtCustomerLan);
		}
		catch (Exception e) {
			logger.info("Failed to get Customer Language Details  " + e.getMessage());
		}
		return strText;
	}
	
	//Get the Customer Age value from Player Search page
	@Step("Method to get the  Customer Age Details")
	public String getCustomerAge() {
		String strText="";
		try {
			boolean flag=this.isElementVisible(btnSearchPageClose, 8);
			if (flag)
				strText=this.getText(txtCustomerAge);
		}
		catch (Exception e) {
			logger.info("Failed to get Customer Age Details  " + e.getMessage());
		}
		return strText;
	}
	
	//Get the Account Age value from Player Search page
	@Step("Method to get the  Account Age Details")
	public String getAccountAge() {
		String strText="";
		try {
			boolean flag=this.isElementVisible(btnSearchPageClose, 6);
			if (flag)
				strText=this.getText(txtAccountAge);
		}
		catch (Exception e) {
			logger.info("Failed to get Account Age Details  " + e.getMessage());
		}
		return strText;
	}
	
	//Method for closing Player Search Page
	@Step("Method for close Player search page")
	public void closePlayerSearch() {
		try {
			this.click(btnSearchPageClose);
			Thread.sleep(5000);
		}
		catch (Exception e) {
			logger.info("Failed to Close Player Search Page" + e.getMessage());
		}
		
	}
	
		
	//Create Case
	@Step("Create Case")
	public String createCase(String CaseType,String Product, String Platform, String Category, String ContentType, String Subject,String CaseNotes) {
		String strText="";
		try {
			//Select Create Case option from dropdown
			this.click(txtPetitionAction);
			Thread.sleep(2000);
			if (CaseType.equals("petition"))
				this.click(liPA_CreatePetition);
			else
				this.click(liPA_CreateDispute);
			Thread.sleep(2000);
			this.click(txtProduct);
			Thread.sleep(3000);
			this.clickOnDynamicElement(ComboBox_Val_Xpath, Product);
			Thread.sleep(3000);
			String text1=this.getAttributeValue(txtProduct, "value");
			this.click(txtPlatform);
			Thread.sleep(3000);
			this.clickOnDynamicElement(ComboBox_Val_Xpath, Platform);
			Thread.sleep(3000);
			String text2=this.getAttributeValue(txtPlatform, "value");
			this.click(txtCategory);
			Thread.sleep(3000);
			this.clickOnDynamicElement(ComboBox_Val_Xpath, Category);
			Thread.sleep(3000);
			String text3=this.getAttributeValue(txtCategory, "value");
			if (CaseType.equals("petition"))
			{
				this.click(txtContentType);
				Thread.sleep(3000);
				this.clickOnDynamicElement(ComboBox_Val_Xpath, ContentType);
				Thread.sleep(3000);
				String text4=this.getAttributeValue(txtContentType, "value");
			}
			this.click(txtSubject);
			this.sendKeys(txtSubject, Subject);
			Thread.sleep(3000);
			this.click(txtCaseNotes);
			this.sendKeys(txtCaseNotes, CaseNotes);
			Thread.sleep(3000);
			//Click on Create&Open button
			this.windowScrollDwn();
			this.click(btnCreateOpenCase);
			strText=text1+","+text2+","+text3;
		}
		catch (Exception e) {
			logger.info("Failed to Create Case " + e.getMessage());
		}
		
		return strText;
	}
		
	
	
	
}
