package com.ea.wwce.automation.thor.pageobjects;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.thor.config.ThorDataConstants;

import io.qameta.allure.Step;


import com.ea.wwce.automation.base.util.Driver;

/**
 * 
 * @author mohan
 * @description THOR Petition page Details object
 */

public class ThorPetitionDetailsPage extends ThorBasePageObject{
	
	public ThorPetitionDetailsPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver,this);
	}
	
    private static final Logger logger = Logger.getLogger(ThorPetitionDetailsPage.class);
	    
    @FindBy(xpath ="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//div[contains(text(),'Case Number')]")       
    WebElement lblCaseID;
	@FindBy(xpath ="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//div[contains(text(),'Case Number')]/following::div[1]")
	WebElement txtCaseID;
	@FindBy(xpath="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//div[contains(text(),'Petition UUID')]/following::div[1]")
	WebElement txtPetitionUUID;
	@FindBy(xpath ="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//div[contains(text(),'Content Type')]")       
    WebElement lblUserContentType;
	@FindBy(xpath ="//h1[contains(text(),'Petition details')]/following::div[@id='contentType']")
	WebElement txtUserContentType;
	@FindBy(xpath ="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//div[contains(text(),'Category')]")
	WebElement lblCategory;
	@FindBy(xpath ="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//div[contains(text(),'Category')]/following::input[1]")
	WebElement txtCategory;
	@FindBy(xpath="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//div[contains(text(),'Product')]")
	WebElement lblProduct;
	@FindBy(xpath="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//div[contains(text(),'Product')]/following::input[1]")
	WebElement txtProduct;
	@FindBy(xpath="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//div[contains(text(),'Platform')]" )
	WebElement lblPlatform;
	@FindBy(xpath ="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//div[contains(text(),'Platform')]/following::input[1]")
	WebElement txtPlatform;
	@FindBy(xpath ="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//h1[contains(text(),'Past Activity')]")       
    WebElement lblPastActivity;
	@FindBy(xpath ="(//h1[contains(text(),'Petition details')]/following::*[@data-key='check'])[1]")
	 WebElement chkBoxPastActivity;
	@FindBy(xpath ="//div[contains(@class,'maximized')]//a[contains(text(),'Account Detail')]")
	WebElement lnkPlayerAccount;
	@FindBy(xpath="//div[@class='slds-spinner_container']")
	WebElement spinner;
	@FindAll(@FindBy(xpath="//div[contains(@class,'scrollable scrollerSize uiScrollerWrapper')]/table/tbody/child::tr"))
	List<WebElement> supportingTblRows;
	@FindBy(xpath="//div[contains(@class,'maximized')]//span[@class='status']")
	WebElement lblCaseStatus;
	@FindBy(xpath="//lightning-icon[contains(@class,'back-icon')]")
	WebElement lnkBack;
	@FindBy(xpath="//div[contains(@class,'maximized')]//a[@id='accNoteClk']")
	WebElement lnkAddAcctNotes;
	@FindBy(xpath="//textarea[@name='notes']")
	WebElement txtAcctNotes;
	@FindBy(xpath="//input[contains(@class,'accNoteSticky ')]")
	WebElement chkBoxPin;
	@FindBy(xpath="//button[@title='Save']")
	WebElement btnAcctNotesSave;
	@FindBy(xpath="//div[contains(@class,'target-account-notes')]/div[1]/div[2]/div/div[1]/p")
	WebElement lblLatestNotesAdded;
	@FindBy(xpath="//div[contains(@class,'target-account-notes')]/div[1]/div[2]/div/div[2]/p")
	WebElement lblLatestNotesTimePeriod;
	@FindBy(xpath="//div[contains(@class,'target-account-notes')]/div[1]/div[2]/p[contains(text(),'UTC')]")
	WebElement lblLatestNotesTime;
	//@FindBy(xpath="//div[contains(@class,'target-account-notes')]/div[1]/div[2]/p/span[3]")
	@FindBy(xpath="(//span[@class='advisor-name'])[1]")
	WebElement lblLatestNotesAddedBy;
	@FindBy(xpath="//div[contains(@class,'target-account-notes')]/div[1]/div[1]/span/lightning-icon/lightning-primitive-icon")
	WebElement lblLatestNotesPinIcon;
	@FindBy(xpath="//lightning-spinner[@class='slds-spinner_container slds-hide']//div[contains(@class,'slds-spinner_small')]")
	WebElement notesAddSpinner;
	String selectPetiAction_XPATH="(//input[contains(@name,'#')])[2]";
	String selectComboBox_XPATH="//input[@name='#']";
	String selectComboBoxValue_XPATH="//lightning-base-combobox-formatted-text[@title='#']";
	@FindBy(xpath="(//input[@placeholder='Select Entitlements'])[1]")
	WebElement txtEntitlement;
	@FindBy(xpath="//span[contains(text(),'There are no ACTIVE entitlements present for this Account.')]")
	WebElement entitlementErrMsg;
	@FindBy(xpath="//span[contains(text(),'Error: Penalty Action \"SUSPEND\" was Unsuccessful.')]")
	WebElement suspendErrMsg;
	String btnSaveAndContinue_XPATH="(//button[contains(text(),'Save and Continue')])[#]";
	
	@FindBy(xpath="//button[contains(text(),'Save and Continue')]")
	WebElement btnSaveCont;
	@FindBy(xpath="//div[contains(@class,'maximized')]//button[@title='Commit']")
	WebElement btnCommit;
	@FindBy(xpath ="//span[contains(text(),'Selected Actions have been take and Events logged SUCCESS')]")
	WebElement lblEventSuccessMsg;
	@FindBy(xpath ="(//div[contains(@class,'slds-p-top_small slds-p-left_large slds-p-right_large')])[1]")
	WebElement lblManageContent;
	@FindBy(xpath ="(//textarea[contains(@name,'makeNotes')])[1]")
	WebElement txtCaseNotes;
	@FindBy(xpath ="//span[contains(text(),'Mark this note as \"Pinned\"')]")
	WebElement chkBoxSticky;
	@FindBy(xpath ="//span[contains(text(),'Updated case details with status Success.')]")
	WebElement lblCaseSuccessMsg;
	String productItem_XPATH="(//div[@id='product']/following::span[text()='#'])[1]";
	String categoryItem_XPATH="(//div[@id='category']/following::span[text()='#'])[1]";
	String platformItem_XPATH="(//div[@id='platform']/following::span[text()='#'])[1]";
	@FindBy(xpath ="(//div[@id='contentType']/following::div[@class='close-icon'])[1]")
	WebElement btnProdClose;
	@FindBy(xpath ="(//div[@id='product']/following::span[text()='close'])[1]")
	WebElement btnPlatformClose;
	@FindBy(xpath ="(//div[@id='platform']/following::span[text()='close'])[1]")
	WebElement btnCategoryClose;
	//T2 Email message WebElements
	@FindBy(xpath ="(//input[contains(@name,'msgLanguage')])[1]")
	WebElement txtEmailLanguage;
	String EmailLanguageOption_Xpath="//div[contains(@class,'maximized')]//div[text()='Send Message']/following::lightning-base-combobox-formatted-text[@title='#']";
	//String EmailLanguageOption_Xpath="//div[contains(@class,'maximized')]//div[text()='Send Message']/following::lightning-base-combobox-item[#]";
	@FindBy(xpath ="((//input[contains(@name,'emailMsgTemplateCMB')])[1]/following::div[contains(@class,'lightningInputRichText')])[1]")
	WebElement txtEmailMsgToPlayer;
	@FindBy(xpath ="((//input[contains(@name,'emailMsgTemplateCMB')])[1]/following::div[contains(@class,'ql-editor slds-rich-text-area__content slds-text-color_weak slds-grow')])[1]")
	WebElement txtEmailBodyArea;
	@FindBy(xpath ="((//input[contains(@name,'inGameMsgTemplate')])[1]/following::div[contains(@class,'lightningInputRichText')])[1]")
	WebElement txtInGameMsgToPlayer;
	@FindBy(xpath ="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//div[contains(text(),'Major Strikes')]/following-sibling::div")
	WebElement lblMajorStrikeCnt;
	@FindBy(xpath ="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//div[contains(text(),'Minor Strikes')]/following-sibling::div")
	WebElement lblMinorStrikeCnt;
	@FindBy(xpath ="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//div[contains(text(),'Petitions')]/following-sibling::div")
	WebElement lblPetitionsCnt;
	@FindBy(xpath ="(//select[contains(@name,'selectStrike')])[1]")
	WebElement selectStrike;
	@FindBy(xpath ="(//select[contains(@name,'strikesName')])[1]")
	WebElement selectStrikeName;
	@FindBy(xpath="//span[contains(text(),'IS SUCCESS')]")
	WebElement petitionActionSucceesMsg;
	@FindBy(xpath="//button[contains(@class,'slds-button toastClose slds-notify__close slds-button--icon-inverse slds-button_icon-bare')]")
	WebElement btnNotificationClose;
	
	@FindBy(xpath="((//div[contains(text(),'Product')])[1]/following::div[@class='close-icon'])[1]")
	WebElement closeicon;
	@FindBy(xpath="(//h1[contains(text(),'Petition details')])[1]")
	WebElement txtPetitionDetails;
	@FindBy(xpath ="//a[contains(text(),'Completed')]")
	WebElement lnkCompletedTab;	
	String Actiondropdown_XPATH="(//input[contains(@name,'#')])[2]";
	String selectResolveActionVal_XPATH= "//lightning-base-combobox-item[contains(@data-value,'#')]";
	String selectEscalateActionVal_XPATH= "//lightning-base-combobox-item[contains(@data-value,'#')]";
	String SelectQueue_XPATH="(//input[contains(@placeholder,'#')])[2]";
	String selectViewTier2Que_XPATH="//lightning-base-combobox-formatted-text[contains(@title,'#')]";
	@FindBy(xpath="(//input[@name='petitionAction'])[2]/following::input[1]")
	WebElement txtSelectQueue;
	@FindBy(xpath="//input[@name='progress']")
	WebElement txtSelect;
	@FindBy(xpath="//h1[contains(text(),'Account Status Details')]/following::div[@class='close-icon']")
	WebElement btnSearchClose;
	
	@FindBy(xpath ="(//span[contains(text(),'Copy to Account Notes')])[1]")
	WebElement chkCopytoAccountNotes;	
	@FindBy(xpath ="(//textarea[contains(@name,'accntNotes')])[1]")
	WebElement txtAccountNotes;
	@FindBy(xpath ="(//span[contains(text(),'Mark this note as \"Pinned\"')])[1]")
	WebElement chkPinned;
	//Target Player Details Xpaths
	@FindBy(xpath="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//div[contains(text(),'Target Login ID')]/following-sibling::div")
	WebElement targetPlayerEmailId;
	@FindBy(xpath="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//div[contains(text(),'Target ID')]/following-sibling::div")
	WebElement targetID;
	@FindBy(xpath="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//div[contains(text(),'Target Persona')]/following-sibling::div")
	WebElement targetPersonaID;
	//Petitioner Details Xpaths
	@FindBy(xpath="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//div[contains(text(),'Petitioner Persona')]/following::div[1]")
	WebElement txtPetitionerPersonaID;
	@FindBy(xpath="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//div[contains(text(),'Petitioner ID')]/following::div[1]")
	WebElement txtPetitionerID;
	@FindBy(xpath="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//div[contains(text(),'Petitioner Login ID')]/following::div[1]")
	WebElement txtPetitionerLoginId;
	
	@FindBy(xpath ="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//h1[contains(text(),'Past Activity')]/following::p[2]")
	WebElement txtPastActivityNotes; 
	String Search_CaseTab_Close_XPATH="//button[contains(@title,'#')]";
	String Search_CaseTab_XPATH="//span[contains(text(),'#')]";
	@FindBy(xpath ="//button[contains(@title,'Close Account')]")
	WebElement btnAccountTabClose;
	String ContentType_XPATH="(//h1[contains(text(),'Petition details')]/following::div[@id='contentType'])[1]//following::li[@data-id='#']";
	@FindBy(xpath ="(//h1[contains(text(),'Petition details')]/following::div[@class='close-icon'])[1]")
	WebElement btnContentTypeClose; 
	@FindBy(xpath ="//div[contains(@class,'maximized')]//div[contains(@id,'contentType')]//input")
	WebElement txtcontentype; 
	
	@FindBy(xpath="(//input[contains(@name,'emailMsgTemplateCMB')])[1]")
	WebElement txtEmailMsgTemplate;
	String EmailTemplateOption_Xpath="//div[contains(@class,'maximized')]//div[text()='Send Message']//following::input[contains(@name,'emailMsgTemplateCMB')][1]//following::lightning-base-combobox-formatted-text[@title='#']";
	@FindBy(xpath="//div[contains(@class,'maximized')]//a[contains(text(),'Case History')]")
	WebElement txtCaseHistorylnk; 
	@FindBy(xpath ="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//h1[contains(text(),'Past Activity')]/following::a[1]")
	WebElement txtPastActivityCaseStatus; 
	@FindBy(xpath ="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//h1[contains(text(),'Past Activity')]/following::a[2]")
	WebElement txtPastActivityAdvisorName; 
	@FindBy(xpath ="//div[contains(@class,'maximized')]//div[contains(@class,'slds-tabs_default__content slds-show')]//h1[contains(text(),'Past Activity')]/following::p[1]")
	WebElement txtPastActivityDate;
	@FindBy(xpath="(//div[@class='slds-p-top_large slds-p-left_xx-large slds-p-bottom_large cThorBase cPetitionDetail']//lightning-spinner[contains(@class,'slds-spinner_container slds-hide')]//div[contains(@class,'slds-spinner slds-spinner_brand slds-spinner_large')])[1])")
	WebElement bigSpinner; 
	
	//email message Confirmation popup
	@FindBy(xpath="//h2[contains(text(),'Send Message')]")
	WebElement lblDialogText;
	@FindBy(xpath="//div[contains(@class,'slds-modal__content slds-p-around--medium')]")
	WebElement lblEmailMsg;
	@FindBy(xpath="//button[contains(text(),'Yes')]")
	WebElement btnYes;
	@FindBy(xpath="//button[contains(text(),'No')]")
	WebElement btnNo;
	@FindBy(xpath="//button[contains(text(),'X')]")
	WebElement btnClose;
	
	
	//Select Combo box option
	@Step("Select Item from Combobox")
	public void selectItemFrmComboBox(String ComboBoxName, String ComboBoxOption ) {
		try {
			if (ComboBoxName.equals("petitionAction"))
				this.clickOnDynamicElement(selectPetiAction_XPATH, ComboBoxName);
			else
				this.clickOnDynamicElement(selectComboBox_XPATH, ComboBoxName);
			Thread.sleep(1000);
			this.clickOnDynamicElement(selectComboBoxValue_XPATH, ComboBoxOption);
			Thread.sleep(1000);
		}
		catch (Exception e) {
			logger.info("Failed to Click on Combobox " +ComboBoxName+" selection  " + e.getMessage());
		}
	}
	
	//Verify CaseID field Display 
	public boolean isCaseIDDisplayed(){
		boolean isVisible = false;
		try{
			logger.info("Verifying the Case ID label and text");
			boolean flag=this.isElementVisible(lblCaseID, 20);
			if(flag)
			{	this.waitForElementToBeVisible(txtCaseID, 15);
				String strText=this.getText(txtCaseID);
				isVisible = true;
				logger.info("CaseID Displayed was "+strText );
			}
			else
			{	isVisible = false;			}
			
		}catch(Exception e){
			logger.info("Failed to verify Cased ID display");
			isVisible=false;
		}
		return isVisible;		
	}
	
	//Verify UUID field Display 
	public String isCaseUUIDDisplayed(){
		String strText = "";
		try{
			logger.info("Verifying the Case UUID Display");
			boolean flag=this.isElementVisible(txtPetitionUUID, 5);
			if(flag)
			{	 strText=this.getText(txtPetitionUUID);
				logger.info("Case UUID Displayed was "+strText );
			}
						
		}catch(Exception e){
			logger.info("Failed to verify Cased UUID display");
		}
		return strText;
	}
	
	//Verify User Content type field Display
	public boolean isUserContentTypeDisplayed(){
		boolean isVisible = false;
		try{
			logger.info("Verifying the User Content type label and text");
			boolean flag=this.isElementVisible(lblUserContentType, 20);
			if(flag)
			{	String strText=this.getText(txtUserContentType);
				isVisible = true;
				logger.info("User Content Type Displayed was "+strText );
			}
			else
			{	isVisible = false;			}
			
		}catch(Exception e){
			logger.info("Failed to verify User Content Type display");
			isVisible=false;
		}
		return isVisible;		
	}
	
	//Verify display of Category 
	public boolean isCategoryDisplayed(){
		boolean isVisible = false;
		try{
			logger.info("Verifying the Category label and text");
			boolean flag=this.isElementVisible(lblCategory, 4);
			if(flag)
			{	String strText=this.getAttributeValue(txtCategory, "value");
				isVisible = true;
				logger.info("Category Displayed was "+strText );
			}
			else
			{	isVisible = false;			}
			
		}catch(Exception e){
			logger.info("Failed to verify Category display");
			isVisible=false;
		}
		return isVisible;		
	}
	
	//Verify display of Product field
	public boolean isProductDisplayed(){
		boolean isVisible = false;
		try{
			logger.info("Verifying the Product label and text");
			boolean flag=this.isElementVisible(lblProduct, 4);
			if(flag)
			{	String strText=this.getAttributeValue(txtProduct,"value");
				isVisible = true;
				logger.info("Product Displayed was "+strText );
			}
			else
			{	isVisible = false;			}
			
		}catch(Exception e){
			logger.info("Failed to verify Product display");
			isVisible=false;
		}
		return isVisible;		
	}
	
	//Verify display of platform field
	public boolean isPlatformDisplayed(){
		boolean isVisible = false;
		try{
			logger.info("Verifying the Platform label and text");
			boolean flag=this.isElementVisible(lblPlatform, 4);
			if(flag)
			{	String strText=this.getAttributeValue(txtPlatform,"value");
				isVisible = true;
				logger.info("Platform Displayed was "+strText );
			}
			else
			{	isVisible = false;			}
			
		}catch(Exception e){
			logger.info("Failed to verify Platform display");
			isVisible=false;
		}
		return isVisible;		
	}
	
	//Verify display of past Activity field
	public boolean isPastActivityDisplayed(){
		boolean isVisible = false;
		try{
			logger.info("Verifying the Past Activity label and text");
			boolean flag=this.isElementVisible(lblPastActivity, 4);
			if(flag)
			{	String strText=this.getText(chkBoxPastActivity);
				isVisible = true;
				logger.info("Past Activity Check box Displayed");
			}
			else
			{	isVisible = false;			}
			
		}catch(Exception e){
			logger.info("Failed to verify Past Activity display");
			isVisible=false;
		}
		return isVisible;		
	}	
	
	//Get Petition Details
	public String getPetitionDetailsFrmDetailPage() {
		String strText=" ";
		try {
			logger.info("Get Petition Details from Petition Detail page");
			this.isElementVisible(txtCaseID, 4);
			String strCaseID=this.getText(txtCaseID);
			String strCategory=this.getAttributeValue(txtCategory,"value");
			String strProduct=this.getAttributeValue(txtProduct,"value");
			strText=strCaseID+","+strCategory+","+strProduct;
		}catch(Exception e){
			logger.info("Failed to get details from Petition details page"+ e.getMessage());
			
		}
		return strText;
	}
	
	// Method to Click on Player Account link
	@Step("Click on Player Account Link")
	public void clickOnPlayerAccountLnk() {
		try {
			//Click on Player Account Link
			this.waitForElementToBeVisible(lnkPlayerAccount, 10);
			this.click(lnkPlayerAccount);
		}
		catch (Exception e) {
			logger.info("Failed to Click on Player Account Link  " + e.getMessage());
		}
	}
		
	// Verify supporting petitions count in a case
	@Step("Verify Supporting Petitions Count")
	public int getSupportingPetitionsCount() {
		logger.info("Get the Supporting Petitions count " );	
		int tblRowCnt=0;
		try {
			this.windowScrollDwn();
			tblRowCnt=this.getSize(supportingTblRows);
		}
		catch (Exception e) {
			logger.info("Failed to get the Supporting Petitions count " + e.getMessage());
			tblRowCnt=-1;
		}
		
		return tblRowCnt;
	}	
		
	//Get Case Status
	public String getCaseStatus() {
		String strText=" ";
		try {
			logger.info("Get Case Status from Petition Detail page");
			if(this.isElementVisible(lblCaseStatus, 3))
			   strText=this.getText(lblCaseStatus);
			
		}catch(Exception e){
			logger.info("Failed to get Case Status from Petition details page"+ e.getMessage());
			
		}
		return strText;
	}
	
	//Click on Back Link
	public void ckickOnBack() {
		try {
			logger.info("click on Back link from Petition Detail page");
			 this.click(lnkBack);
			
		}catch(Exception e){
			logger.info("Failed to click on Back button from Petition details page"+ e.getMessage());
		}
	}
	
	//Add Notes to the Account
	public boolean addNotesToTheAccount(String sticky, String Notes) {
		String strText=" ";
		boolean flag=false;
		try {
			logger.info("Adding notes to the Account using Add Accounts link in Petition Detail page");
			 this.click(lnkAddAcctNotes);
			 this.sendKeys(txtAcctNotes, Notes);
			 boolean blnSticky=Boolean.valueOf(sticky);
			 if(blnSticky) 
				 this.click(chkBoxPin);
			 this.windowScrollDwn();
			 this.click(btnAcctNotesSave);
			 this.waitForElementToBeInVisible(notesAddSpinner, 10);
			 this.waitForElementToBeVisible(lblLatestNotesAdded, 6);
			 strText=this.getText(lblLatestNotesAdded);
			 if(strText.equals(Notes))
				 flag=true;
			 else
				 flag=false;
			
		}catch(Exception e){
			logger.info("Failed to Add notes to the Account"+ e.getMessage());
		}
		return flag;
	}
	
	//Get latest added Notes time from Petition details page
	public String getLatestNotesDateTime() {
		String strText=" ";
		try {
			logger.info("Get latesy added Notes time from Petition Detail page");
			 strText=this.getText(lblLatestNotesTime);
			
		}catch(Exception e){
			logger.info("Failed to get latest added Notes time from Petition details page"+ e.getMessage());
			
		}
		return strText;
	}
	
	//Get latest Notes added by user
	public String getLatestNotesAddedByUser() {
		String strText=" ";
		try {
			logger.info("Get latest Notes Aded By User from Petition Detail page");
			 strText=this.getText(lblLatestNotesAddedBy);
			
		}catch(Exception e){
			logger.info("Failed to get latest Notes Aded By User from Petition details page"+ e.getMessage());
			
		}
		return strText;
	}
	
	// Method to Verify Pin display in Account Notes section
	@Step("verify display of Account Notes pin Icon")
	public boolean verifyAcctNotesPinDisplay() {
		try {
			return(this.isElementVisible(lblLatestNotesPinIcon, 3));
		}
		catch (Exception e) {
			logger.info("Failed to Click on Player Account Link  " + e.getMessage());
			return false;
		}
	}
	
	//Click on Save and Continue button
	public void ckickOnSaveAndContinue(int index) {
	  try {
		logger.info("click on Save and Continue button on Petition Detail page");
		String strIndex=Integer.toString(index);
		this.clickOnDynamicElement(btnSaveAndContinue_XPATH, strIndex);
		//if(index==1) {
		//	this.waitForElementToBeVisible(btnNotificationClose, 10);
		//	this.click(btnNotificationClose);
		//}
		Thread.sleep(4000);
					
	  }catch(Exception e){
			logger.info("Failed to click on Save and Continue button on Petition details page"+ e.getMessage());
	   }
	}
	
	// Verify Manage Content section for Ban
	@Step("verify display of Manage Content Section")
	public boolean verifyManageContentForBan(String strPLevel, String strDiscpAction, String strReason) {
		String strTemp=null;
		try {
			if(this.isElementVisible(lblManageContent, 3))
				strTemp=this.getText(lblManageContent);
			else
				logger.info("Manage Content section not displayed after Save Continue ");
			if ((strTemp.contains(strPLevel)) && (strTemp.contains(strDiscpAction)) && (strTemp.contains(strReason)))
				return true;
			else
				return false;
		}
		catch (Exception e) {
			logger.info("Failed to get content from Manage Content section  " + e.getMessage());
			return false;
		}
	}
	
	// Verify Manage Content section for Suspenson
	@Step("verify display of Manage Content Section")
	public boolean verifyManageContent(String strPLevel, String strDiscpAction, String strDuration, String strReason) {
		String strTemp=null;
		try {
			if(this.isElementVisible(lblManageContent, 3))
				strTemp=this.getText(lblManageContent);
			else
				logger.info("Manage Content section not displayed after Save Continue ");
			if ((strTemp.contains(strPLevel)) && (strTemp.contains(strDiscpAction)) && (strTemp.contains(strDuration)) && (strTemp.contains(strReason)))
				return true;
			else
				return false;
		}
		catch (Exception e) {
			logger.info("Failed to get content from Manage Content section  " + e.getMessage());
			return false;
		}
	}
	
	// Get Automatic Case Notes section
	@Step("Get automatic case notes Section")
	public String getAutomaticCaseNotes() {
		String strTemp=null;
		try {
			if(this.isElementVisible(txtCaseNotes, 3))
				strTemp=this.getAttributeValue(txtCaseNotes, "value");
			else
			{	strTemp=null;
				logger.info("Automatic Case Notes not displayed after Save Continue ");
			}
		}
		catch (Exception e) {
			logger.info("Failed to get Automatic notes from Case Notes section  " + e.getMessage());
		}
		return strTemp;
	}
	
	//Click on Sticky check box
	public void ckickOnStickyCheckBox() {
	  try {
		logger.info("click on Sticky CheckBox on Petition Detail page");
		 this.click(chkBoxSticky);
	  }catch(Exception e){
			logger.info("Failed to click on Sticky Check Box on Petition details page"+ e.getMessage());
	   }
	}
	
	//Click on Commit button
	public void ckickOnCommitButton() {
	  try {
		logger.info("click on Commit Button on Petition Detail page");
		 this.click(btnCommit);
		 Thread.sleep(4000);
		// this.waitForElementToBeVisible(lblCaseSuccessMsg, 6);
	  }catch(Exception e){
			logger.info("Failed to click on Commit Button on Petition details page"+ e.getMessage());
	   }
	}
	
	//Click on page down button
	public void clickOnScrollDwn() {
	  try {
		logger.info("click on page down Button on Petition Detail page");
		 this.windowScrollDwn();
	  }catch(Exception e){
			logger.info("Failed to click on page down Button on Petition details page"+ e.getMessage());
	   }
	}
	
	//Click on page down button
	public void clickOnScrollUp() {
	  try {
		logger.info("click on page UP Button on Petition Detail page");
		 this.windowScrollUp();
	  }catch(Exception e){
			logger.info("Failed to click on page Up Button on Petition details page"+ e.getMessage());
	   }
	}

	//Verify Existance of item in Product and then select item
 	@Step("Select Product Item from Detail page")
 	public boolean selectItemFrmProduct(String productVal) {
 		boolean flag=false;
 		try {
 			//click on Product List box
 			this.click(btnProdClose);
 			this.click(txtProduct);
 			Thread.sleep(1000);
 			this.sendKeys(txtProduct, productVal);
 			this.clickOnDynamicElement(productItem_XPATH, productVal);
 			Thread.sleep(1000);
 			String strText=this.getAttributeValue(txtProduct, "value");
 			//System.out.println(strText);
 			if (strText.equals(productVal))
 				flag=true;
 		}catch(Exception e){
			logger.info("Failed to select item from Product in Case Detail page");
    	}
 		return flag;
 	}
	 	
 	//Verify Existance of item in Category and then select item
 	@Step("Select Category Item from detail page")
 	public boolean selectItemFrmCategory(String categoryVal) {
 		boolean flag=false;
 		try {
 			this.windowScrollDwn();
 			//click on Category List box
 			//this.click(btnCategoryClose);
 			this.click(txtCategory);
 			Thread.sleep(1000);
 			this.sendKeys(txtCategory, categoryVal);
 			this.clickOnDynamicElement(categoryItem_XPATH, categoryVal);
 			Thread.sleep(1000); 
 			String strText=this.getAttributeValue(txtCategory, "value");
 			//System.out.println(strText);
 			if (strText.equals(categoryVal))
 				flag=true;
 		}catch(Exception e){
			logger.info("Failed to select item from Category in detail page");
    	}
 		return flag;
 	}
 	
 	//Verify Existance of item in Platform and then select item
 	@Step("Select Platform Item from detail page")
 	public boolean selectItemFrmPlatform(String platformVal) {
 		boolean flag=false;
 		try {
 			this.windowScrollDwn();
 			//click on Platform List box
 			//this.click(btnPlatformClose);
 			this.click(txtPlatform);
 			Thread.sleep(1000);
 			this.sendKeys(txtPlatform, platformVal);
 			this.clickOnDynamicElement(platformItem_XPATH, platformVal);
 			Thread.sleep(1000);
 			String strText=this.getAttributeValue(txtPlatform, "value");
 			//System.out.println(strText);
 			if (strText.equals(platformVal))
 				flag=true;
 		}catch(Exception e){
			logger.info("Failed to select item from Platform in detail page");
    	}
 		return flag;
 	}
 	
 	//Verify Existence of Email message to Player Option
 	@Step("Verify Existance of Email message to Player")
 	public boolean existenceOfEmailMessageToPlayer() {
 		boolean flag=false;
 		try {
 			this.waitForElementToBeVisible(txtEmailMsgToPlayer, 3);
 			if (this.isElementVisible(txtEmailMsgToPlayer, 1))
 				flag=true;
 		}catch(Exception e){
			logger.info("Failed to verify existence of Email message to Player Option");
    	}
 		return flag;
 	}
 	
 	//Enter Language and Email message
 	@Step("Enter Email message to Player")
 	public void enterEmailMessageToPlayer(String strLanguageIndx, String strMsg) {
 		try {
 			if (this.isElementVisible(txtEmailMsgToPlayer, 1)) {
 				//Select Language
 				this.click(txtEmailLanguage);
 				Thread.sleep(1000);
 				this.clickOnDynamicElement(EmailLanguageOption_Xpath, strLanguageIndx);
 				Thread.sleep(2000);
 				//Enter Text
 				this.click(txtEmailBodyArea);
 				this.sendKeys(txtEmailBodyArea, strMsg);
 			}
 				
 		}catch(Exception e){
			logger.info("Failed to Select Language and Enter Message");
    	}
 		
 	}
 	 	
 	//Verify Existence of Email message to Player Option
 	@Step("Verify Existance of In-Game message to Player")
 	public boolean existenceOfInGameMessageToPlayer() {
 		boolean flag=false;
 		try {
 			this.waitForElementToBeVisible(txtInGameMsgToPlayer, 3);
 			if (this.isElementVisible(txtInGameMsgToPlayer, 1))
 				flag=true;
 		}catch(Exception e){
			logger.info("Failed to verify existence of In-Game message to Player Option");
    	}
 		return flag;
 	}
 	
 	//Get locale Language
 	@Step("Get locale language")
 	public String getLocaleLanguage() {
 		String strLan="";
 		try {
 			if (this.isElementVisible(txtEmailMsgToPlayer, 5)) {
 				//get Language
 				Thread.sleep(1000);
 				strLan=this.getAttributeValue(txtEmailLanguage, "value");                
 			}	 				
 		}catch(Exception e){
			logger.info("Failed to get Localae Language");
    	} 
 		return strLan;
 	}
 	
 	//Get Template Name
 	@Step("Get Email Template Name")
 	public String getTemplateName() {
 		String strTemplateNamen="";
 		try {
 			if (this.isElementVisible(txtEmailMsgTemplate, 2)) {
 				//get Template Name
 				
 				strTemplateNamen=this.getAttributeValue(txtEmailMsgTemplate, "value");                 
 			}	 				
 		}catch(Exception e){
			logger.info("Failed to Get Email Template Name");
    	}
 		return strTemplateNamen;
 	}
 	
 	//Get Email Message
 	@Step("Get Email Message")
 	public String getEmailMessage() {
 		String strEmailMsg="";
 		try {
 			if (this.isElementVisible(txtEmailBodyArea, 2)) {
 				//get Template Message
 				
 				strEmailMsg=this.getAttributeValue(txtEmailBodyArea, "innerText");                 
 			}	 				
 		}catch(Exception e){
			logger.info("Failed to Get Message of Email");
    	}
 		return strEmailMsg;
 	}
 	 //Get Existing Major Strike Cnt
 	@Step("Get Existing Major Strike Count")
 	public int getMajorStrikeCnt() {
 		int Cnt=0;
 		try {
 			//Get text from Major strike Count
 			if(this.isElementVisible(lblMajorStrikeCnt, 6))
 				Cnt=Integer.parseInt(this.getText(lblMajorStrikeCnt));
 			
 		}catch(Exception e){
			logger.info("Failed to Get Major Strikes Count");
    	}
 		return Cnt;
 	}
 	
 	//Get Existing Minor Strike Cnt
 	@Step("Get Existing Minor Strike Count")
 	public int getMinorStrikeCnt() {
 		int Cnt=0;
 		try {
 			//Get text from Minor strike Count
 			if(this.isElementVisible(lblMinorStrikeCnt, 6))
 				Cnt=Integer.parseInt(this.getText(lblMinorStrikeCnt));
 			
 		}catch(Exception e){
			logger.info("Failed to Get Minor Strikes Count");
    	}
 		return Cnt;
 	}
 	
 	//Get Existing Petitions Count
 	@Step("Get Existing Petitions Count")
 	public int getPetitionsCnt() {
 		int Cnt=0;
 		try {
 			//Get text from Petitions Count
 			if(this.isElementVisible(lblPetitionsCnt, 4))
 				Cnt=Integer.parseInt(this.getText(lblPetitionsCnt));
 			
 		}catch(Exception e){
			logger.info("Failed to Get Petitions Count");
    	}
 		return Cnt;
 	}
 	
 	//Select Strike Type
 	@Step("Select Strike Type")
 	public void selectStrikeType(String strStrikeType) {
 		try {
 			//Select Strike Type like Major or Minor
 			//this.selectVisibleText(selectStrike, strStrikeType);
 			this.click(selectStrike);
 			if (strStrikeType.equals("Major Strikes"))
 				selectStrike.sendKeys(Keys.DOWN);
 			else {
 				selectStrike.sendKeys(Keys.DOWN);
 				Thread.sleep(1000);
 				selectStrike.sendKeys(Keys.DOWN);}
 			Thread.sleep(1000);
 			selectStrike.sendKeys(Keys.RETURN);
 		 			
 		}catch(Exception e){
			logger.info("Failed to Select Strike Type");
    	}
 		
 	}
 	
 	//Select Strike Name by Index
 	@Step("Select Strike Name By Index")
 	public void selectStrikeNameByIndex(int index) {
 		try {
 			//Select Strike Name by Index
 			Thread.sleep(5000);
 			this.selectByIndex(selectStrikeName,index);
 			
 		}catch(Exception e){
			logger.info("Failed to Select Strike Name by Index");
    	}
 		
 	}
 	
 	//get default strike name after selecting Strike Type
 	@Step("Get default Strike Name ")
 	public String getDefaultStrikeName() {
 		String strTemp="";
 		try {
 			
 			Thread.sleep(4000);
 			strTemp=this.getAttributeValue(selectStrikeName, "value");
 			
 			
 		}catch(Exception e){
			logger.info("Failed to get default Strike Name");
    	}
 		return strTemp;
 	}
 	
 	//Select Entitlement
 	@Step("Select First Entitlement")
 	public String SelectEntitlement()
 	 {String strTemp="";
		try {
			//Click on Entitlement
			if(this.isElementVisible(entitlementErrMsg, 6))
				strTemp="";
			else
			{	this.click(txtEntitlement);
				Thread.sleep(1000);
				txtEntitlement.sendKeys(Keys.DOWN);
				Thread.sleep(1000);
				txtEntitlement.sendKeys(Keys.RETURN);
				Thread.sleep(1000);
				strTemp=this.getText(txtEntitlement);
			}
			
		}catch(Exception e){
			logger.info("Failed to Select Entitlement");
	   	}
	 		return strTemp;
 		 
 	}
 	
 	//Verify the existence of Successful Action message
 	public boolean verifyExistenceOfSuccessfulActionMsg() {
 		boolean flag=false;
 		try {
 			//Select Strike Type like Major or Minor
 			//this.waitForElementToBeVisible(petitionAccessSucceesMsg, 8);
 			 flag=this.isElementVisible(petitionActionSucceesMsg, 8);
 			/*if(flag) 
 			{	strText=this.getText(petitionActionSucceesMsg);
 				this.click(btnNotificationClose);
 			} */
 			
 		}catch(Exception e){
			logger.info("Failed to verify visibility of Succeess Message");
    	}
 		return flag;
 	}
 	
 	// Verify display of Petition Details label
	@Step("Verify display of Petition Details label")
	public boolean verifyDisplayOfPetitionDetails() {
		boolean flag=false;
		try {
			
			flag=this.isElementVisible(txtPetitionDetails, 3);
			
		}
		catch (Exception e) {
			logger.info("Failed to verify Petition Details label  " + e.getMessage());
		}
		return flag;
	}	
  			
  		
	@Step("Edit Case and take Action in detail page as T1")
	public void T1CaseEditAndAction(String petitionActionVal,String massNotes,String SelectQueuVal,String productVal, String platformVal,String categoryVal) {
		try {
			 				
			this.selectItemFrmProduct(productVal);
			this.selectItemFrmPlatform(platformVal);
			this.selectItemFrmCategory(categoryVal);
			this.windowScrollUp();
			this.selectItemFrmComboBox("petitionAction", petitionActionVal);
            this.waitForElementToBeVisible(txtSelectQueue, 3);          		
       		if (petitionActionVal.contains("Escalate") || petitionActionVal.contains("Transfer"))
     		{	this.click(txtSelectQueue);
     			Thread.sleep(2000);
     			txtSelectQueue.sendKeys(Keys.ARROW_DOWN);
     			Thread.sleep(2000);
     			txtSelectQueue.sendKeys(Keys.ENTER);        			
     		} 				
			String strText=this.getAttributeValue(txtCaseNotes,"value").trim();
			strText=strText+" "+massNotes;
			//this.enterValueUsingJavaScriptExecutor(txtNotes, strText, 5);
			this.click(txtCaseNotes);
			txtCaseNotes.clear();
			Thread.sleep(2000);
			this.sendKeys(txtCaseNotes, strText);
			Thread.sleep(1000);
			this.waitForElementToBeVisible(btnCommit, 5);
			this.click(btnCommit);
			Thread.sleep(5000);		
		}
		catch (Exception e) {
			logger.info("Failed to Commit a  Petition Action from detail page  " + e.getMessage());
		}
	}
  		
	@Step("Select SelectQueue Action")
	public void SelectQueueAction(String SelectQueue, String SelectQueuVal ) {
		try {
			this.windowScrollDwn();
			this.clickOnDynamicElement(SelectQueue_XPATH, SelectQueue);
			Thread.sleep(1000);
			this.clickOnDynamicElement(selectViewTier2Que_XPATH, SelectQueuVal);
		}
		catch (Exception e) {
			logger.info("Failed to Click on Select Queue Action " +SelectQueue+" selection  " + e.getMessage());
		}
	}
	
	@Step("Edit case details and take action in T2 advisor deatail page")
    public void T2CaseEditAndAction(String petitionActionVal,String massNotes,String SelectQueuVal,String productVal, String platformVal,String categoryVal) {
        try {
              this.selectItemFrmProduct(productVal);
              this.selectItemFrmPlatform(platformVal);
              this.selectItemFrmCategory(categoryVal);
              this.windowScrollUp();
              this.selectItemFrmComboBox("petitionAction", petitionActionVal);
              this.waitForElementToBeVisible(txtSelectQueue, 3);                       
              if (petitionActionVal.contains("Escalate") || petitionActionVal.contains("Transfer"))
              {    this.click(txtSelectQueue);
                   Thread.sleep(2000);
                   txtSelectQueue.sendKeys(Keys.ARROW_DOWN);
                   Thread.sleep(2000);
                   txtSelectQueue.sendKeys(Keys.ENTER);                       
              }      
              String strText=this.getAttributeValue(txtCaseNotes,"value").trim();
	          strText=strText+" "+massNotes;
	          this.click(txtCaseNotes);
	          Thread.sleep(2000);
	          txtCaseNotes.clear();
	          Thread.sleep(2000);
	          this.sendKeys(txtCaseNotes, strText);
	          Thread.sleep(1000);
	          this.windowScrollDwn();
	          this.click(chkCopytoAccountNotes);
	          Thread.sleep(2000);
	          this.windowScrollDwn();
	          this.click(chkPinned);
	          this.ckickOnSaveAndContinue(2);
	          Thread.sleep(2000);
	          this.windowScrollDwn();
	          if(this.isElementVisible(txtEmailMsgToPlayer, 3)||this.isElementVisible(txtInGameMsgToPlayer, 3))
	          { this.ckickOnSaveAndContinue(3);   
		          //Verify Email message popup display and click on Yes Button
				  this.verifyEmailPopupDialog();
		          Thread.sleep(5);
	          }
	          this.waitForElementToBeVisible(btnCommit, 5);
	          this.ckickOnCommitButton();                                       
                                                    
            }
           catch (Exception e) {
                  logger.info("Failed to Commit a Escalate Petition Action  " + e.getMessage());
           }
    }

	// Get Target Player Email ID
	@Step("Get Target Player Email ID")
	public String getTargetPlayerEmailID() {
		String strTemp=null;
		try {
			if(this.isElementVisible(targetPlayerEmailId,3))
				strTemp=this.getAttributeValue(targetPlayerEmailId, "innerText");
			else
			{	strTemp=null;
				logger.info("Target Payer Email ID not displayed ");
			}
		}
		catch (Exception e) {
			logger.info("Failed to get Target Payer Email ID  " + e.getMessage());
		}
		return strTemp;
	}
		
	// Get Target Player Persona ID
	@Step("Get Target Player Persona ID")
	public String getTargetPlayerPersonaID() {
		String strTemp=null;
		try {
			if(this.isElementVisible(targetPersonaID,3))
				strTemp=this.getAttributeValue(targetPersonaID, "innerText");
			else
			{	strTemp=null;
				logger.info("Target Payer Persona ID not displayed ");
			}
		}
		catch (Exception e) {
			logger.info("Failed to get Target Payer Persona ID  " + e.getMessage());
		}
		return strTemp;
	}
	// Get Target Player ID
	@Step("Get Target Player ID")
	public String getTargetPlayerID() {
		String strTemp=null;
		try {
			if(this.isElementVisible(targetID,3))
				strTemp=this.getAttributeValue(targetID, "innerText");
			else
			{	strTemp=null;
				logger.info("Target Payer ID not displayed ");
			}
		}
		catch (Exception e) {
			logger.info("Failed to get Target Payer ID  " + e.getMessage());
		}
		return strTemp;
	}	
	
	// Get Petitioner LogIn ID
	@Step("Get Petitioner Login ID")
	public String getPetitionerLogInID() {
		String strTemp=null;
		try {
			if(this.isElementVisible(txtPetitionerLoginId,3))
				strTemp=this.getAttributeValue(txtPetitionerLoginId, "innerText");
			else
			{	strTemp=null;
				logger.info("Petitioner LogIn ID not displayed ");
			}
		}
		catch (Exception e) {
			logger.info("Failed to get Petitioner LogIn ID  " + e.getMessage());
		}
		return strTemp;
	}
			
	// Get Petitioner Persona ID
	@Step("Get Petitioner Persona ID")
	public String getPetitionerPersonaID() {
		String strTemp=null;
		try {
			if(this.isElementVisible(txtPetitionerPersonaID,3))
				strTemp=this.getAttributeValue(txtPetitionerPersonaID, "innerText");
			else
			{	strTemp=null;
				logger.info("Petitioner Persona ID not displayed ");
			}
		}
		catch (Exception e) {
			logger.info("Failed to get Petitioner Persona ID  " + e.getMessage());
		}
		return strTemp;
	}
	// Get Petitioner ID
	@Step("Get Petitioner ID")
	public String getPetitionerID() {
		String strTemp=null;
		try {
			if(this.isElementVisible(txtPetitionerID,3))
				strTemp=this.getAttributeValue(txtPetitionerID, "innerText");
			else
			{	strTemp=null;
				logger.info("Petitioner ID not displayed ");
			}
		}
		catch (Exception e) {
			logger.info("Failed to get Petitioner ID  " + e.getMessage());
		}
		return strTemp;
	}	
  	
	public void navigateBacktoPreviewPane() {
       try {
             logger.info("click on Navigate back link on Petition Detail page");
             this.click(lnkBack);
             Thread.sleep(1000);
       }
       catch (Exception e) {
           logger.info("Failed to Click on Navigate back link " + e.getMessage());
       }
	}
	
	//Get case notes from Past Activity Details
  	public String getCaseNotesFrmPastActivity() {
  		String strCaseNotes=" ";
  		try {
  			logger.info("Get Past activity CaseNotes from Case Detail page");
  			boolean flag=this.isElementVisible(txtPastActivityNotes, 12);
  			if (flag)
  			strCaseNotes=this.getText(txtPastActivityNotes);			
  			
  		}catch(Exception e){
  			logger.info("Failed to get Past activity details from Petition details page"+ e.getMessage());
  			
  		}
  		return strCaseNotes;
  	} 
  	//Close Search Case Tab
  	public void closeSearchCaseTab(String CaseNo) {
  		String Text="Close Case ";
  		try {
  			logger.info("Close Search Case Tab based on Case No");
  			Text=Text+CaseNo;
  			this.clickOnDynamicElement(Search_CaseTab_Close_XPATH, Text);
  			logger.info("Successfully closed the Search Case No");  			
  		}catch(Exception e){
  			logger.info("Failed to Close Serach Case Tab"+ e.getMessage());
  			
  		}
  		
  	} 
  	
  //Close Account Tab
  	public void closeSearchAccountTab() {
  		try {
  			logger.info("Close Search Account Tab ");
  			this.click(btnAccountTabClose);
  			logger.info("Successfully closed the Search Account Tab");  			
  		}catch(Exception e){
  			logger.info("Failed to Close Serach Account Tab"+ e.getMessage());
 		}
  	} 
  	
  //Click on Search Case Tab
  	public void clickOnSearchCaseTab(String CaseNo) {
  		String Text="Case ";
  		try {
  			logger.info("Click on Search Case Tab based on Case No");
  			Text=Text+CaseNo;
  			this.clickOnDynamicElement(Search_CaseTab_XPATH, Text);
  			//Thread.sleep(6000);
  			this.waitForElementToBeVisible(bigSpinner, 10);
  			this.waitForElementToBeInVisible(bigSpinner, 2);
  			logger.info("Successfully Click on the Search Case Tab");  			
  		}catch(Exception e){
  			logger.info("Failed to Click on Serach Case Tab"+ e.getMessage());
  			
  		}
  		
  	} 
  	
    // Method for  Automatic case notes from Petition Action
    @Step("Select Petition Action and Get Automatic case notes")
    public boolean verifyAutomaticCaseNotesFromDetail(String petitionActionVal,String defaultNotes) {
      boolean flag=false;
      try {
              this.windowScrollUp();
             //Select one of the petition action
             selectItemFrmComboBox("petitionAction", petitionActionVal);
             this.windowScrollDwn();
             this.waitForElementToBeVisible(txtSelectQueue, 3);
             if (petitionActionVal.contains("Escalate") || petitionActionVal.contains("Transfer"))
             {
                    this.click(txtSelectQueue);
                    Thread.sleep(2000);
                    txtSelectQueue.sendKeys(Keys.ARROW_DOWN);
                    Thread.sleep(2000);
                    txtSelectQueue.sendKeys(Keys.ENTER);
                    
              }
             //Get Petition Action Notes
             Thread.sleep(3000);
             String strText=this.getAttributeValue(txtCaseNotes, "value").trim();
             if (strText.contains(defaultNotes))
                    flag=true;
             this.windowScrollUp();
             Thread.sleep(4000);
      }catch(Exception e){
             logger.info("Failed to verify Automatic Case Notes");
       }
             return flag;
    }
    
   
    //get list element
 	public String gettextfromcontenttype(String dropval) {		
 		String temp ="";
 		try {	
 			this.click(btnContentTypeClose);
 			//this.clickOnDynamicElement(txtcontentype, "Petition Action");
 			this.click(txtcontentype);
 			temp=this.getTextFromDynamicElement(ContentType_XPATH, dropval);			 			  
				
 		}catch (Exception e)  {
			logger.warn("Failed to get list values from Content type " + e.getMessage());
			throw e;
    	}
 		return temp;
 		
 	} 
 	//Verify Existance of Suspend Error message
 	public boolean verifyExistanceOfSuspendErrMsg() {
 		boolean flag=false;
 		try {
 			if(this.isElementVisible(suspendErrMsg, 6))
 				flag=true;
 			else
 				flag=false;
 		}catch (Exception e)  {
			logger.warn("Failed to display the Suspend error message " + e.getMessage());
			throw e;
    	}
 		return flag;
 	} 
 		
 	//select Email template
    @Step("select Email template to Player")
    public String selectEmailTemplate(String strTemplate) {
        String strText="";
        try{
             if (this.isElementVisible(txtEmailMsgTemplate, 1)) {
                    //Select Language
                    this.click(txtEmailMsgTemplate);
                    Thread.sleep(1000);
                    this.clickOnDynamicElement(EmailTemplateOption_Xpath, strTemplate);
                    Thread.sleep(2000);
                    //Get Petition Action Notes
		            Thread.sleep(1000);
		            strText=this.getAttributeValue(txtEmailBodyArea, "textContent").trim();
             }
                        
       }catch(Exception e){
           logger.info("Failed to Select Template Message");
       }
       return strText;
    }  
    
    //select Language 
    @Step("Enter Email Langauage to Player")
    public void selectEmail(String strLanguage) {
        try {
             if (this.isElementVisible(txtEmailMsgToPlayer, 1)) {
                    //Select Language
                    this.click(txtEmailLanguage);
                    Thread.sleep(1000);
                    this.clickOnDynamicElement(EmailLanguageOption_Xpath, strLanguage);
                    Thread.sleep(2000);                            
              }
                        
        }catch(Exception e){
           logger.info("Failed to Select Language ");
        }
          
    }
	
    
	//Click on Case History link
	public void clickOnCaseHistory() {
		try {
			logger.info("click on Case History link from Petition Detail page");
			 this.click(txtCaseHistorylnk);
			
		}catch(Exception e){
			logger.info("Failed to click on Case History link from Petition details page"+ e.getMessage());
		}
	} 

	 //Get case Advisor name from Past Activity Details
  	public String getAdvisorNameFrmPastActivity() {
  		String strCaseAdvisor=" ";
  		try {
  			logger.info("Get Past activity CaseStatus from Case Detail page");
  			boolean flag=this.isElementVisible(txtPastActivityAdvisorName, 4);
  			if (flag)
  				strCaseAdvisor=this.getText(txtPastActivityAdvisorName);			
  			
  		}catch(Exception e){
  			logger.info("Failed to get Case Advisor Name from Petition details page"+ e.getMessage());
  			
  		}
  		return strCaseAdvisor;
  	} 	
	  	
	 //Get case Date from Past Activity Details
  	public String getDateFrmPastActivity() {
  		String strCaseDate=" ";
  		try {
  			logger.info("Get Past activity Date from Case Detail page");
  			boolean flag=this.isElementVisible(txtPastActivityDate, 4);
  			if (flag)
  				strCaseDate=this.getText(txtPastActivityDate);			
  			
  		}catch(Exception e){
  			logger.info("Failed to get Case Date from Petition details page"+ e.getMessage());
  			
  		}
  		return strCaseDate;
  	} 	
  	
  //Get case Status from Past Activity Details
  	public String getCaseStatusFrmPastActivity() {
  		String strCaseStatus=" ";
  		try {
  			logger.info("Get Past activity CaseStatus from Case Detail page");
  			boolean flag=this.isElementVisible(txtPastActivityCaseStatus, 10);
  			if (flag)
  			strCaseStatus=this.getText(txtPastActivityCaseStatus);			
  			
  		}catch(Exception e){
  			logger.info("Failed to get Case Status details from Petition details page"+ e.getMessage());
  			
  		}
  		return strCaseStatus;
  	} 
  	
  	//Verify Email popup message display and Select Yes or No button
  	public String verifyEmailPopupDialog()
  	{
  		String strText="";
  		try {
  			logger.info("Verify email popup display and accept Yes or No ");
  			if(this.isElementVisible(lblDialogText, 5))
				{
  					//Get text of Email popup
  					strText=this.getAttributeValue(lblEmailMsg, "textContent");
  					this.click(btnYes);
				}
  			else
  			{
  				strText="";
  				logger.info("Failed to display Email popup confirmation Dialog");
  			}
  		}catch(Exception e){
  			logger.info("Failed to verify the Email pop-up dialog text "+ e.getMessage());
  			
  		}
  		
  		return strText;
  	}

}
