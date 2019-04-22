package com.ea.wwce.automation.thor.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.ea.wwce.automation.thor.config.ThorDataConstants;

import bsh.This;
import io.qameta.allure.Step;


import com.ea.wwce.automation.base.util.Driver;

/**
 * 
 * @author mohan
 * @description THOR Petition page object
 */

public class ThorPetitionPage extends ThorBasePageObject{
	
	public ThorPetitionPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver,this);
	}
	
    private static final Logger logger = Logger.getLogger(ThorPetitionPage.class);
    @FindBy(xpath ="//a[contains(text(),'Queue')]")		
    WebElement lnkQueuedTab;
    @FindBy(xpath ="(//li[contains(@class,'small')])[3]")
	WebElement listUserProfile;
    @FindBy(xpath ="(//a[@class='profile-link-label'])[1]")
    WebElement lnkUserName;
	//@FindBy(xpath ="//button[contains(@class,'oneUserProfileCardTrigger')]")
	@FindBy(xpath ="//img[@title='User']")
	WebElement btnUserProfile;
	@FindBy(xpath ="//a[contains(text(),'Switch to Salesforce Classic')]")
	WebElement lnkSFClassic;
	@FindBy(xpath ="//a[contains(text(),'Log Out')]")
	WebElement lnkLogout;
	@FindBy(xpath ="//button[contains(@title,'Pull Queue and Start')]")
	WebElement btnPullQueueAndStart;
	@FindBy(xpath="//div[@data-aura-class='cQueuedList']//tbody/tr[1]")
	WebElement qTablerow;
	String tblrowValXpath="//div[@data-aura-class='cQueuedList']//tbody/tr[#]";
	//@FindBy(name ="petitionAction")
	@FindBy(xpath="//input[contains(@name,'petitionAction')][position()=1]")
	WebElement selectPetiAction;
	@FindBy(xpath="//lightning-base-combobox-item[contains(@data-value,'Escalated')]" )
	//@FindBy(xpath="//span[contains(@data-value,'Escalated')]" )
	WebElement selectOnePetiAction;
	@FindBy(xpath ="(//button[contains(@title,'Commit')])[1]")
	WebElement btnCommit;
	@FindBy(xpath ="//button[contains(@title,'Commit & Get Next')]")
	WebElement btnCommitNext;
	@FindBy(xpath ="//a[contains(text(),'Completed')]")
    WebElement lnkCompletedTab;
	@FindAll(@FindBy(xpath="//div[@data-aura-class='cCompletedList']//tbody/tr"))
	List<WebElement> compltdTableRows;
	@FindAll(@FindBy(xpath="//div[@data-aura-class='cCompletedList']//tbody/tr/td"))
	List<WebElement> compltdTableCols;
	@FindAll(@FindBy(xpath="//div[@data-aura-class='cQueuedList']//tbody/tr"))
	List<WebElement> queuedTableRows;
	@FindAll(@FindBy(xpath="//div[@data-aura-class='cQueuedList']//tbody/tr/td"))
	List<WebElement> queuedTableCols;
	@FindBy(xpath ="//div[@id='preview-container']//span[contains(text(),'Subject')]/following::span[1]")
	WebElement lblSubjectFromRHS;
	@FindBy(xpath="(//input[@name='category'])[2]")
	WebElement txtCategoryFromRHS;
	@FindBy(xpath="(//input[@name='product'])[2]")
	WebElement txtProductFromRHS;
	@FindBy(xpath="(//span[text()='Case Number']/following::span[contains(@class,'uiOutputText')])[2]")
	WebElement lblCaseNoFromRHS;
	@FindBy(xpath ="//button[contains(text(),'View Case Details')]")
	WebElement btnViewCaseDetails;
	@FindBy(xpath="//input[@name='category'][position()=1]")
	WebElement fltrCategory;
	@FindBy(xpath="//span[text()='T1 - Petition Queue']")
	WebElement headingLabel;
	@FindBy(xpath="//a[contains(@title,'Account')]")
	WebElement lnkAccount;
	@FindBy(xpath="//div[@class='ugc-content']")
	WebElement expand;
	@FindBy(xpath="//div[@data-aura-class='cPetitionPreview']")
	WebElement petitionView;
	String tblCellValXpath="//div[@data-aura-class='cQueuedList']//tbody/tr[%d]/td[%d]/span";
	String compltdTblCellValXpath="//div[@data-aura-class='cCompletedList']//tbody/tr[%d]/td[%d]/span";
	String filter_XPATH="(//input[@name='#'])[1]";
	String filterVal_XPATH ="//lightning-base-combobox-item[contains(@data-value,'#')]";
	String resetFilter_XPATH="//lightning-base-combobox-formatted-text[@title='#']";
	String selectPetiAction_XPATH="(//input[contains(@name,'#')])[1]";
	//String selectPetiActionVal_XPATH ="//lightning-base-combobox-formatted-text[contains(@title,'#')]";
	String selectPetiActionVal_XPATH ="//lightning-base-combobox-item[contains(@data-value,'#')]";
	//String selectPetiActionVal_XPATH2="(//lightning-base-combobox-formatted-text[contains(@title,'#')])[1]";
	@FindBy(xpath="//button[contains(@title,'Select Role')]")
	WebElement btnSelectRole;
	@FindBy(xpath ="//div[@class='slds-modal__header']/h2")
	WebElement lblChooseUrRole;
	@FindBy(xpath="//span[text()='Omni-Channel']")
	WebElement btnOmni;
	@FindBy(xpath="//div[contains(@class,'petition-footer')]//textarea[contains(@name,'makeNotes')]")
	WebElement txtNotes;
	@FindBy(xpath="(//lightning-spinner[contains(@class,'slds-spinner_container')])[1]")
	WebElement spinner;
	String columnSortAscButton_XPATH="(//lightning-icon[contains(@class,'arrowdown')])[%d]";
	String columnSortDescButton_XPATH="(//lightning-icon[contains(@class,'arrowup')])[%d]";
	@FindBy(xpath="(//div[@class='slds-p-left_small label'])[1]")
	WebElement lblYourAHT;
	@FindBy(xpath="(//div[@class='slds-p-left_small label'])[2]")
	WebElement lblPerformanceToday;
	@FindBy(xpath="(//div[@class='slds-is-relative']/span)[1]")
	WebElement txtYourAHT;
	@FindBy(xpath="(//div[@class='slds-is-relative']/span)[2]")
	WebElement txtPerformanceToday;
	String productItem_XPATH="(//input[@name='product'])[2]/following::lightning-base-combobox-formatted-text[text()='#']";
	String categoryItem_XPATH="(//input[@name='category'])[2]/following::lightning-base-combobox-formatted-text[text()='#']";
	@FindBy(xpath="//div[contains(@class,'ugc-container-main')]/div/div/img")
	WebElement contentImage;
	@FindBy(xpath="(//div[contains(@class,'ugc-container-main')]/div/span/a)[1]")
	WebElement contentURL;
	@FindBy(xpath="(//input[@name='petitionAction'])[1]/following::input[1]")
	WebElement txtSelectQueue;
	
	@FindBy(xpath ="(//label[contains(@class,'slds-checkbox')])[1]/span[1]")       
    WebElement allCheckbox;	
	@FindBy(xpath ="//div[contains(@class,'cCaseMassAction')]/div/h2")
	WebElement txtTotalCasesSelected;	
	@FindBy(xpath="//button[@title='Commit']")
	WebElement massbtnCommit;
	@FindBy(xpath ="(//textarea[contains(@name,'makeNotes')])[1]")
	WebElement txtCaseNotes;
	@FindBy(xpath ="(//button[contains(@title,'Commit')])[2]")
	WebElement btnMassCommit;
	String massActiondropdown_XPATH="//h2[contains(text(),'#')]//following::input[1]";
	String selectMassResolveActionVal_XPATH= "//lightning-base-combobox-item[contains(@data-value,'#')]";
	String selectMassEscalateActionVal_XPATH= "//lightning-base-combobox-item[contains(@data-value,'#')]";
	String massSelectQueue_XPATH="//input[contains(@placeholder,'#')]";
	String MassActionQueueVal_XPATH="//input[@placeholder='Select Queue']/following::lightning-base-combobox-item[#]";
	@FindBy(xpath="(//p[text()='Mass action process is in progress. Please wait.']")
	WebElement massActionProgress;
	@FindBy(xpath="//span[contains(text(),'Cases Escalated')]")
	WebElement massEscalateSuccessMsg;
	@FindBy(xpath="//span[contains(text(),'Cases Resolved')]") 
	WebElement massResolveSuccessMsg;
	
	//@FindBy(xpath="(//input[contains(@name,'plfilterInput')])[4]")
	@FindBy(xpath="(//span[contains(text(),'Sandbox')]/following::input)[3]")
	WebElement selectSearchFilter;
	@FindBy(xpath="(//span[contains(text(),'Sandbox')]/following::input)[5]")
	WebElement txtSearch;
	@FindBy(xpath="//lightning-spinner[contains(@class,'player-search-spin slds-spinner_container cPlayerSearch slds-hide')]//div[contains(@class,'slds-spinner slds-spinner_brand slds-spinner_large')]")
	WebElement searchSpinner;
	@FindBy(xpath="//span[contains(text(),'No records found for your search criteria.')]") 
	WebElement lblSearchErrorMessage;
	
	@FindBy(xpath="//li[@is-visible='true']//a[@title='Queue']")
	WebElement QueueTab;
	@FindBy(xpath="//span[contains(text(),'Hide Image Content Permanently')]")
	WebElement txthideImge; 
	
	String selectCheckBox_XPATH="(//div[@data-aura-class='cQueuedList']//tbody/tr/td[1])[#]"; 
	@FindAll(@FindBy(xpath="//h2[contains(text(),'Petition Actions')]/following::lightning-base-combobox-item[contains(@class,'slds-listbox__option_plain')]"))
	List<WebElement> massPetitionActionvalues; 
	@FindBy(xpath="//button[@title='Manage Queue']")
	WebElement btnManageQueue; 
	@FindBy(xpath="//span[contains(text(),'Updated case details with status Success.')]")
	WebElement CommitSucessMsg;
	@FindAll(@FindBy(xpath="//div[@data-aura-class='cCompletedList']//tbody/tr"))
	List<WebElement> completedTableRows;
	@FindBy(xpath="(//div[@class='slds-p-top_large slds-p-left_xx-large slds-p-bottom_large cThorBase cPetitionDetail']//lightning-spinner[contains(@class,'slds-spinner_container slds-hide')]//div[contains(@class,'slds-spinner slds-spinner_brand slds-spinner_large')])[1])")
	WebElement bigSpinner; 
	
	 

	
	
	public void searchPlayer(String searchFilterVal,String SearchVal)
	{
	       try{
	             logger.info("Verifying Search functioanlity of Player");
	             selectSearchFilter.clear();
	             Thread.sleep(1000);
	             this.click(selectSearchFilter);
	             Thread.sleep(1000);
	             this.sendKeys(selectSearchFilter, searchFilterVal);
	             Thread.sleep(1000);
	             selectSearchFilter.sendKeys(Keys.ENTER);
	             Thread.sleep(2000);
	             //Type search value in the search text box
	             this.click(txtSearch);
	             this.sendKeys(txtSearch, SearchVal);
	             Thread.sleep(1000);
	             txtSearch.sendKeys(Keys.ENTER);
	             this.waitForElementToBeInVisible(searchSpinner, 20);
	             this.waitForElementToBeVisible(bigSpinner, 10);
	   			 this.waitForElementToBeInVisible(bigSpinner, 2);
	       }
	       catch (Exception e) {
	             logger.info("Failed to Search a Player by " +searchFilterVal+ " "+ e.getMessage());
	       }
	             
	}
	
   
	//Verify Search Error Message
	public boolean verifySearchErrorMessages(String searchFilterVal)
	{	boolean flag=false;
		try{
			logger.info("Verifying Search error messages");
			selectSearchFilter.clear();
			Thread.sleep(1000);
			this.click(selectSearchFilter);
			Thread.sleep(1000);
			this.sendKeys(selectSearchFilter, searchFilterVal);
			Thread.sleep(2000);
			selectSearchFilter.sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			//Type search value in the search text box
			this.click(txtSearch);
			Thread.sleep(2000);
			txtSearch.sendKeys(Keys.ENTER);
			flag=this.isElementVisible(lblSearchErrorMessage, 8);
			
		}
		catch (Exception e) {
			logger.info("Failed to display search error message "+ e.getMessage());
		}
		return flag;
	}
	
	
	//This function verifies the Visibility of Queued tab Link
	public boolean islnkQueuedTabVisible(){
		boolean isVisible = false;
		try{
			logger.info("Verifying the lnkQueuedTab Link on successful login");
			isVisible=this.isElementVisible(lnkQueuedTab, 10);
			
		}catch(Exception e){
			logger.info("Failed to verify lnkQueuedTab Link");
			isVisible=false;
		}
		return isVisible;		
	}
	
	//This function verifies the Visibility of any Link in Petition page
	public boolean islnkTabVisible(WebElement element){
		boolean isVisible = false;
		try{
			logger.info("Verifying the "+element+" Link on successful login");
			Thread.sleep(2000);
			isVisible=this.isElementVisible(element, ThorDataConstants.IMPLICIT_TIMEOUT);
			
		}catch(Exception e){
			logger.info("Failed to verify "+element+" Link");
			isVisible=false;
		}
		return isVisible;		
	}	
	
	//This function click on User Profile Image button
	public void clickOnUserProfile() {
		try{
			logger.info("Click on User Profile Image");
			this.isElementVisible(btnUserProfile, 10);
			this.click(btnUserProfile);
			Thread.sleep(2000);
		}catch(NoSuchElementException e){
			logger.info("Failed to find the user Profile button");
		}	
		catch (Exception e) {
			logger.info("Failed to Commit a Petition Action  " + e.getMessage());
		}
	}
	
	//This function get the Advisor name
	public String getAdvisorName(){
		String strText="";
		try{
			logger.info("Get Advisor name");
			this.waitForElementToBeVisible(lnkUserName, 5);
			strText=this.getText(lnkUserName);
		}catch(NoSuchElementException e){
			logger.info("Failed to get the Advisor Name");
		}	
		return strText;
	}
	
	
	//Logout of the Thor Application
	public void logout() {
		try {
			boolean isVisible=this.isElementVisible(lnkLogout, ThorDataConstants.IMPLICIT_TIMEOUT);
			
			if (isVisible) {
				this.click(lnkLogout);
				Thread.sleep(4000);
			}
			
			else
				logger.info("Failed to find the logout link");	
		}catch(NoSuchElementException e){
			logger.info("Failed to find the logout link");} 
		catch (Exception e) {
			logger.info("Failed to logout from the application "+ e.getMessage());
		}
	}
	
	//Switch to SF classic of the Thor Application
	public void switchToSFClassic() {
		try {
			boolean isVisible=this.isElementVisible(lnkSFClassic, ThorDataConstants.IMPLICIT_TIMEOUT);
			
			if (isVisible) {
				this.click(lnkSFClassic);}
			else
				logger.info("Failed to find the Switch to SF classic link");	
		}catch(NoSuchElementException e){
			logger.info("Failed to find the Switch to SF classic link");}
	}
		
	// Method for  Petition Action
	@Step("Select Petition Action and Commit")
	public boolean petitionAction(String petitionActionVal,String notes) {
		boolean hideFlag=false;
		try {
			//Select first row from Queue Table
			this.waitForElementToBeVisible(qTablerow, 4);
			this.selectFirstPetitionFromQueue();
			if(!(this.verifyDisplayOfPetitionPreview()))
			{	this.clickOnQueueTab();
				this.waitForElementToBeVisible(qTablerow, 4);
				this.selectFirstPetitionFromQueue();
			}
			//Select one of the petition action
			selectPetitionAction("petitionAction", petitionActionVal  );
			Thread.sleep(1000);
			this.windowScrollDwn();
			if (petitionActionVal.contains("Escalate") || petitionActionVal.contains("Transfer"))
 			{	this.waitForElementToBeVisible(txtSelectQueue, 3);
 				this.click(txtSelectQueue);
 				Thread.sleep(2000);
 				txtSelectQueue.sendKeys(Keys.ARROW_DOWN);
 				Thread.sleep(1000);
 				txtSelectQueue.sendKeys(Keys.ENTER);
 				Thread.sleep(1000);
 			}
			if(isElementVisible(txthideImge,3))
			{
				this.click(txthideImge);
				hideFlag=true;
			}
			Thread.sleep(2000);
			//Enter Petition Action Notes
			String strText=this.getAttributeValue(txtNotes, "value");
			strText=strText+" "+notes;
			//this.enterValueUsingJavaScriptExecutor(txtNotes, strText, 5);
			this.click(txtNotes);
			Thread.sleep(2000);
			txtNotes.clear();
			this.sendKeys(txtNotes, strText);
			this.windowScrollDwn();
			this.waitForElementToBeVisible(btnCommit, 5);
			this.click(btnCommit);
			this.isElementVisible(CommitSucessMsg, 5);	
			this.islnkTabVisible(lnkCompletedTab);
		}
		catch (Exception e) {
			logger.info("Failed to Commit a Petition Action  " + e.getMessage());
		}
		return hideFlag;
	}
	
	// Method for  Petition Action
	@Step("Select Petition Action and Commit")
	public boolean petitionAction(String petitionActionVal,String notes,String hide) {
		boolean hideFlag=false;
		boolean blnHide=Boolean.parseBoolean(hide);
		try {
			//Select first row from Queue Table
			this.waitForElementToBeVisible(qTablerow, 4);
			this.selectFirstPetitionFromQueue();
			if(!(this.verifyDisplayOfPetitionPreview()))
			{	this.clickOnQueueTab();
				this.waitForElementToBeVisible(qTablerow, 4);
				this.selectFirstPetitionFromQueue();
			}
			//Select one of the petition action
			selectPetitionAction("petitionAction", petitionActionVal  );
			Thread.sleep(1000);
			this.windowScrollDwn();
			if (petitionActionVal.contains("Escalate") || petitionActionVal.contains("Transfer"))
 			{	this.waitForElementToBeVisible(txtSelectQueue, 3);
 				this.click(txtSelectQueue);
 				Thread.sleep(2000);
 				txtSelectQueue.sendKeys(Keys.ARROW_DOWN);
 				Thread.sleep(1000);
 				txtSelectQueue.sendKeys(Keys.ENTER);
 				Thread.sleep(1000);
 			}
			if((blnHide) &&isElementVisible(txthideImge,3))
			{
				this.click(txthideImge);
				hideFlag=true;
			}
			Thread.sleep(2000);
			//Enter Petition Action Notes
			String strText=this.getAttributeValue(txtNotes, "value");
			strText=strText+" "+notes;
			//this.enterValueUsingJavaScriptExecutor(txtNotes, strText, 5);
			this.click(txtNotes);
			Thread.sleep(2000);
			txtNotes.clear();
			this.sendKeys(txtNotes, strText);
			this.windowScrollDwn();
			this.waitForElementToBeVisible(btnCommit, 5);
			this.click(btnCommit);
			this.isElementVisible(CommitSucessMsg, 5);
			this.islnkTabVisible(lnkCompletedTab);
		}
		catch (Exception e) {
			logger.info("Failed to Commit a Petition Action  " + e.getMessage());
		}
		return hideFlag;
	}
		
	
	@Step("Select Edit Petition Action and Commit")
	public boolean petitionEditAndAction(String productVal,String categoryVal,String petitionActionVal,String notes) {
		boolean hideFlag=false;
		try {
			//Select first row from Queue Table
			this.waitForElementToBeVisible(qTablerow, 20);
			this.selectFirstPetitionFromQueue();
			this.verifyDisplayOfPetitionPreview();
			this.selectItemFrmProduct(productVal);
			this.selectItemFrmCategory(categoryVal);
			//Select one of the petition action
			selectPetitionAction("petitionAction", petitionActionVal);
			this.waitForElementToBeVisible(txtSelectQueue, 3);
			this.windowScrollDwn();
			if (petitionActionVal.contains("Escalate") || petitionActionVal.contains("Transfer"))
			{
				this.click(txtSelectQueue);
				Thread.sleep(2000);
				txtSelectQueue.sendKeys(Keys.ARROW_DOWN);
				Thread.sleep(1000);
				txtSelectQueue.sendKeys(Keys.ENTER);
				Thread.sleep(1000);
				
			}
			if(isElementVisible(txthideImge,5))
			{this.click(txthideImge);
			 hideFlag=true;
			}
			Thread.sleep(2000);
			//Enter Petition Action Notes
			String strText=this.getAttributeValue(txtNotes, "value");
			strText=strText+" "+notes;
			//this.enterValueUsingJavaScriptExecutor(txtNotes, strText, 5);
			this.click(txtNotes);
			txtNotes.clear();
			Thread.sleep(2000);
			this.sendKeys(txtNotes, strText);
			//this.pressEnterKey(txtNotes);
			this.windowScrollDwn();	
			
			this.waitForElementToBeVisible(btnCommit, 5);
			this.click(btnCommit);
			this.isElementVisible(CommitSucessMsg, 5);			
			this.islnkTabVisible(lnkCompletedTab);
		}
		catch (Exception e) {
			logger.info("Failed to Commit a Petition Action  " + e.getMessage());
		}
		return hideFlag;
	} 
	
		
	// Method to Select First Row from Petition Queue
	@Step("Select first petition from Queue")
	public void selectFirstPetitionFromQueue() {
		try {
			//Select first row from Queue Table
			this.click(qTablerow);
			Thread.sleep(5000);
			this.waitForElementToBeVisible(selectPetiAction, 5);
		}
		catch (Exception e) {
			logger.info("Failed to Select First Petition  " + e.getMessage());
		}
	}
	// Method to Select Any Row from Petition Queue	
	public void selectPetitionFromQueue(int row){
		try {
                String strrow=Integer.toString(row);
                //Select first row from Queue Table
                this.clickOnDynamicElement(tblrowValXpath,strrow);
                Thread.sleep(6000);
                //this.waitForElementToBeVisible(selectPetiAction, 30);
		     }
             catch (Exception e) {
                    logger.info("Failed to Select "+row+" Petition  " + e.getMessage());
             }
	}

		
	// Method to verify Petition preview Display
	@Step("Verify Display of Petition preview")
	public boolean verifyDisplayOfPetitionPreview() {
		boolean flag=false;
		try {
			
			flag=this.isElementVisible(btnViewCaseDetails, 3);
			
		}
		catch (Exception e) {
			logger.info("Failed to verify Petition Preview section  " + e.getMessage());
		}
		return flag;
	}
	
	// Click on Queue Tab
	@Step("Click on Queue Tab")
	public void clickOnQueueTab() {
		try {
			//Click Queue Tab
			this.click(QueueTab);
			Thread.sleep(2000);
			this.isElementVisible(selectPetiAction, 10);
			
		}
		catch (Exception e) {
			logger.info("Failed to Click on Queue Tab" + e.getMessage());
		}
	}
		
		
	
	//Method to click on Completed Queue link
	public void clickOnCompletedQueue() {
		try {
			this.click(lnkCompletedTab);
		}
		catch (Exception e) {
			logger.info("Failed to click on Completed Tab   " + e.getMessage());
		}
	}
	
	// Method to  Petition Action with commit and next
	@Step("Select Petition Action and Commit and next")
	public boolean petitionActionWithCommitAndNext(String petitionActionVal, String PetitionNotes  ) {
		boolean flag=false;
		try {
			//Select Queued tab
			this.click(lnkQueuedTab);
			//Select one of the petition action
			this.petitionAction(petitionActionVal,PetitionNotes);
			//Get the values from Right hand side
			Thread.sleep(4000);
			this.waitForElementToBeVisible(lblSubjectFromRHS, 5);
			String strSubject=this.getText(lblSubjectFromRHS);
			String strCategory=this.getAttributeValue(txtCategoryFromRHS,"value");
			String strProduct=this.getAttributeValue(txtProductFromRHS,"value");
			//System.out.println(strSubject+strCategory+strProduct);
			//Now get the first row data from Queued tab
			String strProdFromQueue="";
			String strCatFrmQueue="";
			String strSubjectFrmQueue="";
			strSubjectFrmQueue=this.getTableCellVal(tblCellValXpath, 1, 2);
			strCatFrmQueue=this.getTableCellVal(tblCellValXpath, 1, 3);
			strProdFromQueue=this.getTableCellVal(tblCellValXpath, 1, 5);
			//System.out.println(strSubjectFrmQueue+strCatFrmQueue+strProdFromQueue);
			if ((strSubject.equals(strSubjectFrmQueue)) && (strCategory.equals(strCatFrmQueue)) && (strProduct.equals(strProdFromQueue))) 
				flag=true;
			else
				flag=false;
		}
		catch (Exception e) {
			logger.info("Failed to Commit and Get next Petition Action  " + e.getMessage());
		}
		return flag;
	}
	
	// Method to  get Petition Preview values from RHS
	@Step("Get Petition Preview Details from RHS")
	public String getPetitionPreviewDetails() {
		 String strText="";
		try {
			this.waitForElementToBeVisible(txtCategoryFromRHS, 8);
			String strCaseNo=this.getText(lblCaseNoFromRHS);
			String strProduct=this.getAttributeValue(txtProductFromRHS,"value");
			String strCategory=this.getAttributeValue(txtCategoryFromRHS,"value");
			strText=strCaseNo+","+strCategory+","+strProduct;
		}
		catch (Exception e) {
			logger.info("Failed to Get details from Petition Preview(RHS)  " + e.getMessage());
		}
		return strText;
	}
		
	// Method to  Click on Completed Queued Tab
	@Step("Click on Completed Queue Tab")
	public void ClickOnCompletedQueueTab() {
		try {
			this.windowScrollUp();
			this.click(lnkCompletedTab);
		}
		catch (Exception e) {
			logger.info("Failed to Click on Completed Queue Tab  " + e.getMessage());
		}
	}
				
	// Method to  Verify Completed Queued count
	@Step("Verify Completed Queue count")
	public boolean verifyCompletedQueueCount() {
		boolean flag = false;
		try {
			this.windowScrollUp();
			this.click(lnkCompletedTab);
			Thread.sleep(5000);
			String strText=this.getText(lnkCompletedTab);
			int rowCnt=this.getSize(compltdTableRows);
			String strTextCnt=strText.substring(12);
			int intRowCntFromCTab = Integer.parseInt(strTextCnt);	
			if (rowCnt==intRowCntFromCTab) {
				flag=true;
			}
			else
				flag=false;	
		}
		catch (Exception e) {
			logger.info("Failed to verify the petitions count against Completed Tab heading  " + e.getMessage());
		}
		return flag;
	}
				
	// Method to  Verify Queued count
	@Step("Verify Queued count")
	public boolean verifyQueuedCount() {
		boolean flag = false;
		try {
			this.click(lnkQueuedTab);
			Thread.sleep(2000);
			String strText=this.getText(lnkQueuedTab);
			int rowCnt=this.getSize(queuedTableRows);
			String strTextCnt=strText.substring(9);
			int intRowCntFromCTab = Integer.parseInt(strTextCnt);	
			if (rowCnt==intRowCntFromCTab) {
				flag=true;
			}
			else
				flag=false;	
			Thread.sleep(2000);
		}
		catch (Exception e) {
			logger.info("Failed to verify the petitions count against Queued Tab heading  " + e.getMessage());
		}
		return flag;
	}
	
		
	// Method to Click on Chat Log expand button
	@Step("Click on chat log expand")
	public void clickChatLogExpand() {
		try {
			//this.waitForClickableElement(8, expand);
			if(this.isElementVisible(expand, 8))
			{
				this.click(expand);
				
			}
			
			} catch (Exception e) {
			logger.info("Failed to Click Chat Log Expand  " + e.getMessage());
		}
	}
	
	// Method to Click on View Case Details button
	@Step("Click on View Case Details button")
	public void clickOnViewCaseDetailsButton() {
		try {
			//Click on View Case Details button
			this.click(btnViewCaseDetails);
			this.isElementVisible(spinner, 8);
						
		}
		catch (Exception e) {
			logger.info("Failed to Click on View Case Details button  " + e.getMessage());
		}
		
	}

	//Select one Filer and Option
	@Step("Select Filter and Option")
	public boolean selectFilterAndOption(String Filter, String Option ) {
		boolean flag=false;
		try {
			this.windowScrollUp();
			this.clickOnDynamicElement(filter_XPATH, Filter);
			Thread.sleep(1000);
			this.clickOnDynamicElement(filterVal_XPATH, Option);
			Thread.sleep(2000);
			flag=true;
		}
		catch (Exception e) {
			logger.info("Failed to Click on Filter " +Filter+" selection  " + e.getMessage());
			flag=false;
		}
		return flag;
	}
	
	//Reset Filer  Option
	@Step("Reset Filter Option")
	public void resetFilterOption(String Filter, String FilterIdx ) {
		try {
			this.windowScrollUp();
			this.clickOnDynamicElement(filter_XPATH, Filter);
			Thread.sleep(1000);
			this.clickOnDynamicElement(resetFilter_XPATH, FilterIdx);
			Thread.sleep(1000);
		}
		catch (Exception e) {
			logger.info("Failed to Click on Filter " +Filter+" selection  " + e.getMessage());
		}
	}
		
	//Select Petition Action
	@Step("Select Petition Action")
	public void selectPetitionAction(String PetitionAction, String PetitionActionOption ) {
		try {
			this.windowScrollDwn();
			Thread.sleep(1000);
			this.windowScrollDwn();
			this.clickOnDynamicElement(selectPetiAction_XPATH, PetitionAction);
			Thread.sleep(1000);
			this.clickOnDynamicElement(selectPetiActionVal_XPATH, PetitionActionOption);
		}
		catch (Exception e) {
			logger.info("Failed to Click on Petition Action " +PetitionAction+" selection  " + e.getMessage());
		}
	}
		
	//Get Completed table cell value
	@Step("Get Complated table Cell Value")
	public String getCompletedTableCellVal(int r,int c ) {
		String strText = null;
		try {			
			String fullXpath1=String.format(compltdTblCellValXpath,r,c);
			strText=this.getText(driver.findElement(By.xpath(fullXpath1)));
		}
		catch (Exception e) {
			logger.info("Failed to Click on Completed Table Cell  " + e.getMessage());
		}
		return strText;
	}
	
	//Get Queue table cell value
	@Step("Get Queued table Cell Value")
	public String getQueuedTableCellVal(int r,int c ) {
		String strText = null;
		try {			
			String fullXpath1=String.format(tblCellValXpath,r,c);
			strText=this.getText(driver.findElement(By.xpath(fullXpath1)));
		}
		catch (Exception e) {
			logger.info("Failed to Click Table Cell  " + e.getMessage());
		}
		return strText;
	}
	
	// Adding Table specific column values in to list
	public List<String>  getValuesFrmTblColumn(String strTblCellXpath,int r,int c){
	  List<String> temp=new ArrayList<String>();
	  String strText = null;
	   try{
	     for(int i=1;i<r;i++){   
		 	strText=getTableCellVal(strTblCellXpath,i,c);	
		 	temp.add(strText);
		 }
	   }catch (Exception e) {
			logger.info("Store Table cell values in Array List  " + e.getMessage());
		}
	   return temp;
	}
	
	//Get Queue Table column values in array list
	@Step("Get Queue Table column values in array list")
	public List<String> getQueueTableColumnValuesInList( int clnIdx) {
		List<String> tblColumnList=new ArrayList<String>();
		try {
			int intrc=this.getSize(queuedTableRows);
			if (intrc>0)
			{
				if (intrc>10) intrc=10;
				tblColumnList=this.getValuesFrmTblColumn(tblCellValXpath,intrc,clnIdx);
			}
			else
			{
				logger.info("No Rows displayed on Queued Table");
				
			}
		}
		catch (Exception e) {
			logger.info("Failed to Get Queued Table row count  " + e.getMessage());
		}
		return tblColumnList;
	}
	
	//Verify Column list sorted or not
	@Step("Verify Column List is sorted or Not ")
	public boolean verifyIsColumnListSorted(List<String> list)
	{boolean flag=false;
		try {
			flag=this.isListSorted(list);
		}
		catch (Exception e) {
			logger.info("Failed to sort Queued Table Column list  " + e.getMessage());
		}
	
		return flag;
	}
	
	//Click on Column Sort Ascending button
	@Step("click on Column Sort Ascending Button ")
	public void clickOnColumnSortAscButton(int clnIdx)
	{	try {
			String fullXpath1=String.format(columnSortAscButton_XPATH,clnIdx);
			Actions actions = new Actions(driver);
			WebElement element=driver.findElement(By.xpath(fullXpath1));
			actions.moveToElement(element).click().build().perform();
			Thread.sleep(3000);
		}catch (Exception e) {
			logger.info("Failed to click on Column Ascending Sort button  " + e.getMessage());
		}
	}
	
	//Click on Column Sort Descending button
	@Step("click on Column Sort Descending Button ")
	public void clickOnColumnSortDescButton(int clnIdx)
	{	try {
			String fullXpath1=String.format(columnSortDescButton_XPATH,clnIdx);
			Actions actions = new Actions(driver);
			WebElement element=driver.findElement(By.xpath(fullXpath1));
			actions.moveToElement(element).click().build().perform();
			Thread.sleep(3000);
		}catch (Exception e) {
			logger.info("Failed to click on Column Sort Descending button  " + e.getMessage());
		}
	}
		
	//Select  Category Filter and Verify in Queue table
	@Step("Select  Category Filter and verify Queue Table")
	public boolean selectCategoryFilterAndVerifyQueueTable(String categName) {
		String strText=null;	
		boolean flag=false;
		try {		
			this.selectFilterAndOption("category", categName);
			//Read the row count of Queue table
			int rCnt=this.getSize(queuedTableRows);
			if (rCnt>10) rCnt=10;
			//System.out.println("rCnt "+rCnt);
			for (int x = 1; x <= rCnt; x++)
			{    strText=getQueuedTableCellVal(x,3);
				//System.out.println(strText+" "+categName);
				  if (strText.equals(categName))
						  flag=true;
				  else
				  {
					  	 flag=false;
	         			 break;
				  }
				  
			}
			
		}catch (Exception e) {
			logger.info("Failed to Verify Queued table after select Category filter " + e.getMessage());
		}
		if (flag)
			return	true;
		else
			return false;
	}
		
	//Select  Product Filter and Verify in Queue table
	@Step("Select  Product Filter and verify Queue Table")
	public boolean selectProductFilterAndVerifyQueueTable(String prodName) {
		String strText=null;	
		boolean flag=false;
		try {		
			this.selectFilterAndOption("product", prodName);
			//Read the row count of Queue table
			int rCnt=this.getSize(queuedTableRows);
			if (rCnt>10) rCnt=10;
			//int colCnt=this.getTableColCnt(queuedTableCols);
			for (int x = 1; x <= rCnt; x++)
			{    strText=getQueuedTableCellVal(x,5);
				//System.out.println(strText+" "+prodName);
				  if (strText.equals(prodName))
						  flag=true;
				  else
				  { flag=false;
				  	 break;
				  }
				  
			}
		}catch (Exception e) {
			logger.info("Failed to Verify Queded table after select Product filter " + e.getMessage());
		}
		if (flag)
			return	true;
		else
			return false;
			
	}
					
	//Select  Persona Filter and Verify in Queue table
	@Step("Select  Persona Filter and verify Queue Table")
	public boolean selectPersonaFilterAndVerifyQueueTable(String petitionId) {
		String strText=null;	
		boolean flag=false;
		try {		
			this.selectFilterAndOption("petition-age", petitionId);
			//Read the row count of Queue table
			int rCnt=this.getSize(queuedTableRows);
			if (rCnt>10) rCnt=10;
			//int colCnt=this.getTableColCnt(queuedTableCols);
			for (int x = 1; x <= rCnt; x++)
			{    strText=getQueuedTableCellVal(x,4);
			//System.out.println(strText+" "+petitionId);
				  if (strText.equals(petitionId))
						  flag=true;
				  else
				  {
					  	 flag=false;
				  		 break;
				  }
				  
			}
		}catch (Exception e) {
			logger.info("Failed to Verify Queded table after select Persona filter " + e.getMessage());
		}
			if (flag)
				return	true;
			else
				return false;
	}
		
	//Select  Category Filter and Verify in Completed table
	@Step("Select  Category Filter and verify Completed Table")
	public boolean selectCategoryFilterAndVerifyCompletedTable(String categName) {
		String strText=null;	
		boolean flag=false;
		try {		
			this.selectFilterAndOption("category", categName);
			//Read the row count of Completed table
			int rCnt=this.getSize(compltdTableRows);
			if (rCnt>10) rCnt=10;
			//System.out.println("rCnt "+rCnt);
			for (int x = 1; x <= rCnt; x++)
			{    strText=getCompletedTableCellVal(x,2);
				  if (strText.equals(categName))
						  flag=true;
				  else
				  {
					  	 flag=false;
	         			 break;
				  }
				 
			}
			
		}catch (Exception e) {
			logger.info("Failed to Verify Completed table after select Category filter " + e.getMessage());
		}
		if (flag)
			return	true;
		else
			return false;
	}
			
	//Select  Product Filter and Verify in Completed table
	@Step("Select  Product Filter and verify Completed Table")
	public boolean selectProductFilterAndVerifyCompletedTable(String prodName) {
		String strText=null;	
		boolean flag=false;
		try {		
			this.selectFilterAndOption("product", prodName);
			//Read the row count of Completed table
			int rCnt=this.getSize(compltdTableRows);
			if (rCnt>10) rCnt=10;
			//System.out.println("rCnt "+rCnt);
			for (int x = 1; x <= rCnt; x++)
			{    
				strText=getCompletedTableCellVal(x,4);
				  if (strText.equals(prodName))
						  flag=true;
				  else
				  { flag=false;
				  	 break;
				  }
				  
			}
		}catch (Exception e) {
			logger.info("Failed to Verify Completed table after select Product filter " + e.getMessage());
		}
		if (flag)
			return	true;
		else
			return false;
			
	}
						
	//Select  Persona Filter and Verify in Completed table
	@Step("Select  Persona Filter and verify Completed Table")
	public boolean selectPersonaFilterAndVerifyCompletedTable(String petitionId) {
		String strText=null;	
		boolean flag=false;
		try {		
			this.selectFilterAndOption("petition-age", petitionId);
			//Read the row count of Completed table
			int rCnt=this.getSize(compltdTableRows);
			if (rCnt>10) rCnt=10;
			//System.out.println("rCnt "+rCnt);
			for (int x = 1; x <= rCnt; x++)
			{    strText=getCompletedTableCellVal(x,3);
				  if (strText.equals(petitionId))
						  flag=true;
				  else
				  {
					  	 flag=false;
				  		 break;
				  }
				  
			}
		}catch (Exception e) {
			logger.info("Failed to Verify Completed table after select Persona filter " + e.getMessage());
		}
			if (flag)
				return	true;
			else
				return false;
	}
	
	// Method to Click on Select Role button
	@Step("Click on Select Role button")
	public void clickOnSelectRoleButton() {
		try {
			//Click on Select Role button
			if (this.isElementVisible(lblChooseUrRole, 3))
			{logger.info("Select your Role modal already displayed. No need to Click on Select Role button");}
			else {
			this.click(btnSelectRole);	
			}
			
		}catch (Exception e) {
			logger.info("Failed to Click on Select Role button  " + e.getMessage());
		}
	}
	
	// Method to Click on Omni-Channel
	@Step("Click on Omni Channel button")
	public void clickOnOmniChannelButton() {
		try {
			//Click on Omni Channel button
			//this.click(btnOmni);
			this.waitForElementToBeVisible(btnOmni, 10);
			this.clickUsingJavaScriptExecutor(btnOmni, 3);
			Thread.sleep(2000);
		}
		catch (Exception e) {
			logger.info("Failed to Click on Omni Channel button  " + e.getMessage());
		}
	}
	
	// Method to Verify AHT 
	@Step("Method to verify AHT of Advisor")
	public String verifyAHT() {
		String strText="";
		try {
			boolean f1=this.isElementVisible(lblYourAHT, 3);
			boolean f2=this.isElementVisible(txtYourAHT, 3);
			if (f1 && f2)
				strText=this.getText(txtYourAHT);
		}
		catch (Exception e) {
			logger.info("Failed to Verify AHT Label and Text  " + e.getMessage());
		}
		return strText;
	}
	
	// Method to Verify Performance Today 
	@Step("Method to verify Performance of Advisor")
	public String verifyPerformance() {
		String strText="";
		try {
			boolean f1=this.isElementVisible(lblPerformanceToday, 3);
			boolean f2=this.isElementVisible(txtPerformanceToday, 3);
			if (f1 && f2)
				strText=this.getText(txtPerformanceToday);
		}
		catch (Exception e) {
			logger.info("Failed to Verify Performance Today Label and Text  " + e.getMessage());
		}
		return strText;
	}
	
	//Get cases count from Queue Link
	 public int getQueueCount() {
	    	int intTempCaseCnt=0;
	    	try{
				logger.info("Get Cases Count from Queue link");
				String strText=this.getText(lnkQueuedTab);
				String arrText[]=strText.split("-");
				String strTempText=arrText[1].trim();
				intTempCaseCnt=Integer.parseInt(strTempText);
	    	}
	    	catch(Exception e){
				logger.info("Failed to get count from Queue link");
	    	}
	    	return intTempCaseCnt;
	}
	//Verify Queue count and Configured Queue count
    public int getCasesCountFrmQueue() {
    	int intCaseCnt=0; int i=0;
    	try{
			logger.info("Verify Case count from Queue with Configured no of Queues");
			intCaseCnt=getQueueCount();
			while(intCaseCnt>=0 && intCaseCnt<=ThorDataConstants.OMNI_CONFIGURED_QUEUE_CNT) {
				if (ThorDataConstants.OMNI_CONFIGURED_QUEUE_CNT==intCaseCnt) {
					  break;
				}
				else
				{	Thread.sleep(2000);
					i=i+1;
					intCaseCnt=getQueueCount();	
					if((i>6) && (intCaseCnt==0))
					{	logger.info("No cases displayed in Queue");
						break;
					}	
					if((i>ThorDataConstants.OMNI_CONFIGURED_QUEUE_CNT)&&(intCaseCnt<=ThorDataConstants.OMNI_CONFIGURED_QUEUE_CNT))
						break;
				}
						
			}
		}
    	catch(Exception e){
			logger.info("Failed to verify Queue case count");
    	}
    	return intCaseCnt;
    }	
    
    // Method for  Automatic case notes from Petition preview
 	@Step("Select Petition Action and Get Automatic case notes")
 	public boolean verifyAutomaticCaseNotesFromPreview(String petitionActionVal,String defaultNotes) {
 		boolean flag=false;
 		try {
 			//Select first row from Queue Table
 			this.waitForElementToBeVisible(qTablerow, 2);
 			this.selectFirstPetitionFromQueue();
 			this.verifyDisplayOfPetitionPreview();
 			//Select one of the petition action
 			selectPetitionAction("petitionAction", petitionActionVal);
 			this.windowScrollDwn();
 			this.waitForElementToBeVisible(txtSelectQueue, 3);
 			if (petitionActionVal.contains("Escalate") || petitionActionVal.contains("Transfer"))
 			{
 				this.click(txtSelectQueue);
 				Thread.sleep(1000);
 				txtSelectQueue.sendKeys(Keys.ARROW_DOWN);
 				Thread.sleep(1000);
 				txtSelectQueue.sendKeys(Keys.ENTER);
 				
 			}
 			//Get Petition Action Notes
 			Thread.sleep(2000);
 			String strText=this.getAttributeValue(txtNotes, "value").trim();
 			if (strText.contains(defaultNotes))
 				flag=true;
 		}catch(Exception e){
			logger.info("Failed to verify Automatic Case Notes");
    	}
 		return flag;
 	}
 	
 	//Verify Existance of item in Product and then select item
 	@Step("Select Product Item")
 	public boolean selectItemFrmProduct(String productVal) {
 		boolean flag=false;
 		try {
 			this.windowScrollDwn();
 			//click on Product List box
 			this.click(txtProductFromRHS);
 			Thread.sleep(1000);
 			//Verify existance of Product Item
 			WebElement el=this.findDynamicElement(productItem_XPATH, productVal);
 			//Click on the dynamic element
 			this.click(el);
 			Thread.sleep(1000);
 			String strText=this.getAttributeValue(txtProductFromRHS, "value");
 			if (strText.equals(productVal))
 				flag=true;
 		}catch(Exception e){
			logger.info("Failed to select item from Product");
    	}
 		return flag;
 	}
 	
 	//Verify Existance of item in Category and then select item
 	@Step("Select Category Item")
 	public boolean selectItemFrmCategory(String categoryVal) {
 		boolean flag=false;
 		try {
 			//click on Category List box
 			this.click(txtCategoryFromRHS);
 			Thread.sleep(1000);
 			//Verify existance of Category Item
 			WebElement el=this.findDynamicElement(categoryItem_XPATH, categoryVal);
 			//Click on the dynamic element
 			this.click(el);
 			Thread.sleep(1000);
 			String strText=this.getAttributeValue(txtCategoryFromRHS, "value");
 			if (strText.equals(categoryVal))
 				flag=true;
 		}catch(Exception e){
			logger.info("Failed to select item from Category");
    	}
 		return flag;
 	}
 	
 	//Verify display of UGC content in Petition preview
 	@Step("Verify display of UGC content in Petition Preview")
 	public String verifyDisplayOfUGC() {
 		String temp="";
 		try {
 			//Verify Image/URL existence in Petition preview
 			if (this.isElementVisible(contentImage, 3))
 				temp="Image";
 			else if (this.isElementVisible(contentURL, 3))
 			temp="URL";
 			else
 				temp="No Image OR No URL";
 		}catch(Exception e){
			logger.info("Failed to verify UGC content");
    	}
 		return temp;
 	}
 	
 	//click on Content Image
 	@Step("click on content Image")
 	public void clickOnContentImage() {
 		
 		try {
 			//Verify Image existence in Petition preview
 			if (this.isElementVisible(contentImage, 3))
 				this.click(contentImage);
 		}catch(Exception e){
			logger.info("Failed to click on Content Image");
    	}
 		
 	}
 	
 	
 	//This function click on All check box for Mass Action
 	@Step("click on All check box")
	public void clickOnAllCheckBox() {
		try{
			logger.info("Click on All Check box");
			this.waitForElementToBeVisible(allCheckbox, 10);
			this.click(allCheckbox);
			Thread.sleep(2000);
		}catch(NoSuchElementException e){
			logger.info("Failed to find the All check box ");
		}	
		catch (Exception e) {
			logger.info("Failed to Find All check box  " + e.getMessage());
		}
	}
 	 			
 	 			
 	//This function get mass action count
 	@Step("Get Mass action selection count ")
	public String getMassActionCount() {
		String masscount = null;
		try {
			 logger.info("get Mass Action selection Count");
			 this.waitForElementToBeVisible(txtTotalCasesSelected, 10);
			 masscount=this.getText(txtTotalCasesSelected);
		}catch(NoSuchElementException e){
			logger.info("Failed to get Mass Action selection count");
		}	
		return masscount;
	}
 	 			
	// Method for  Mass Petition Resolve Action
	@Step("Select Mass Resolve Petition Action and Commit")
	public boolean masspResolvePetitionAction(String petitionActionVal,String massNotes) {
		boolean flag=false;
		try {
			logger.info("select Mass Resolve petition Action");
			//Select one of the petition action
			selectResolvePetitionAction("Petition Actions",petitionActionVal);
			//Enter Petition Action Notes
			this.click(txtCaseNotes);
			Thread.sleep(2000);
			this.sendKeys(txtCaseNotes, massNotes);
			this.waitForElementToBeVisible(btnMassCommit, 5);
			this.click(btnMassCommit);
			flag=this.isElementVisible(massResolveSuccessMsg, 30);				
		}
		catch (Exception e) {
			logger.info("Failed to Commit a Mass Resolve Petition Action  " + e.getMessage());
			flag=false;
		}
		return flag;
	}
 	 					
 	 					
 	 			
	@Step("Select Mass Resolve Petition Action")
		public void selectResolvePetitionAction(String text, String petitionActionVal ) {
		try {
			this.windowScrollDwn();
			this.clickOnDynamicElement(massActiondropdown_XPATH, text);
			Thread.sleep(1000);
			this.clickOnDynamicElement(selectMassResolveActionVal_XPATH, petitionActionVal);
			this.windowScrollDwn();
		}
		catch (Exception e) {
			logger.info("Failed to Click on Petition Action " +text+" selection  " + e.getMessage());
		}
	}

	@Step("Select Mass EscalatePetition Action")
		public void selectEscalatePetitionAction(String text, String petitionActionVal2) {
			try {
				this.windowScrollDwn();
				this.clickOnDynamicElement(massActiondropdown_XPATH, text);
				Thread.sleep(1000);
				this.clickOnDynamicElement(selectMassEscalateActionVal_XPATH, petitionActionVal2);
			}
			catch (Exception e) {
				logger.info("Failed to Click on Petition Action " +text+" selection  " + e.getMessage());
			}
	}

 	 		
	// Method for  Mass Petition Escalate Action
	@Step("Select Mass Escalate Petition Action and Commit")
	public boolean massEscalatePetitionAction(String petitionActionVal2,String massNotes,String SelectQueuVal) {
		boolean flag=false;
		try {
			
			//Select one of the petition action
			selectEscalatePetitionAction("Petition Actions",petitionActionVal2); 						 					
			Thread.sleep(2000);
			SelectMassQueueAction("Select Queue", SelectQueuVal);
			//Enter Petition Action Notes
			this.click(txtCaseNotes);
			Thread.sleep(2000);
			this.sendKeys(txtCaseNotes, massNotes);
			this.waitForElementToBeVisible(btnMassCommit, 5);
			this.click(btnMassCommit);
			flag=this.isElementVisible(massEscalateSuccessMsg, 30);
			//this.islnkTabVisible(lnkCompletedTab);
		}
		catch (Exception e) {
			logger.info("Failed to Commit a Mass Escalate Petition Action  " + e.getMessage());
			flag=false;
		}
		return flag;
	}
 	 		
 	 		
 	 		 		
	@Step("Select Mass SelectQueue Action")
	public void SelectMassQueueAction(String SelectQueue, String SelectQueuVal ) {
		try {
			this.windowScrollDwn();
			this.clickOnDynamicElement(massSelectQueue_XPATH, SelectQueue);
			Thread.sleep(1000);
			this.clickOnDynamicElement(MassActionQueueVal_XPATH, SelectQueuVal);
		}
		catch (Exception e) {
			logger.info("Failed to Click on Select Queue Action " +SelectQueue+" selection  " + e.getMessage());
		}
	}
 	 		
	@Step("Get Qued Table size")
	public boolean getQueeTablesize() {
	    int Queuesize=0;
	    boolean flag=false;
	    
	    try {
	    	Thread.sleep(3000);
	    	Queuesize=this.getSize(queuedTableRows);
	    
			if(Queuesize>0)
				flag=true;						
		 }
		catch (Exception e) {
			logger.info("Failed to get Qued table size "+ e.getMessage());
		}
	    
	    return flag;
 	}
	//Method to  Click on Queued Tab
	@Step("Click on Queued Tab")
	public void ClickOnQueuedTab() {
		try {
			//this.windowScrollUp();
			logger.info("Get Queued tab link");
			this.click(lnkQueuedTab);
			Thread.sleep(1000);
		}
		catch (Exception e) {
			logger.info("Failed to Click on Queued Tab  " + e.getMessage());
		}
	} 
	
	//Get cases count from Completed table
	public int getCompletedCount() {
		int rCnt=0;	
		try {
			 this.click(lnkCompletedTab);
			 Thread.sleep(3000);
			 rCnt=this.getSize(compltdTableRows);
			
		}catch (Exception e) {
			logger.info("Failed to get Queued table count" + e.getMessage());
		}
		return rCnt;
	} 
	//Get Selection of Cases
	@Step("Get selection of cases from table ")
	public boolean SelectMultipleCases(int intQueueCnt, int count)
	{	boolean flag=false;
		try {
			logger.info("select multiplecases from Case Details table");
			if(intQueueCnt>=count && count!=1)
			{ for(int i=1;i<=count;i++)
				{
				String value=Integer.toString(i);
				this.windowScrollDwn();
			   	this.clickOnCheckBoxes(value);	
				}
				//Get selection of count
				String massActionCount=this.getMassActionCount();
				String MassCaseCount[]=massActionCount.split(" ");
				//System.out.println(MassCaseCount[0]);
				int selectedcount=Integer.parseInt(MassCaseCount[0]);
				if(selectedcount==count)
					flag=true;
				else
					flag=false;
			}
			else
				logger.info("Mass Selection Can not perform on Single Case and Mass selection can not perform if count is more than Queue count ");
			
		}catch (Exception e) {
			logger.info("Failed to select multiple cases from Case detail table  " + e.getMessage());
		}
		
		return flag;
		
	} 
	
	public void clickOnCheckBoxes(String value) {
		try{
			logger.info("Click on  Check box");
			this.clickOnDynamicElement(selectCheckBox_XPATH, value);
			Thread.sleep(2000);
		}catch(NoSuchElementException e){
			logger.info("Failed to find the All check box ");
		}	
		catch (Exception e) {
			logger.info("Failed to Find All check box  " + e.getMessage());
		}
	} 
	
	//Click on Manage Queue Link in Tier2
	public void ManageQueue() {
		try {
			logger.info("click on Manage Queue link in Tier2 Petition page");
			this.click(btnManageQueue);
			Thread.sleep(1000);	
		}
		catch (Exception e) {
				logger.info("Failed to Click on Manage Queue link " + e.getMessage());
		}
	} 
			
	//get list element
 	public String gettextfromdropdown() {		
 		String a="";
 		try {	
 			this.clickOnDynamicElement(massActiondropdown_XPATH, "Petition Action");
		    a=this.getvaluesfromMassActionDropdown();	
		    this.clickOnDynamicElement(massActiondropdown_XPATH, "Petition Action");
				
 		}catch (Exception e)  {
			logger.warn("Failed to get list values from Petition Action List " + e.getMessage());
			throw e;
    	}
 		return a;
 		
 	} 
 	
	//Get All items from Petition Action dropdown
 	public String getvaluesfromMassActionDropdown() {	 		
 		String temp="";
 		
 		try {				
			for (WebElement element : massPetitionActionvalues) 
				
				temp=temp+this.getAttributeValue(element, "innerText")+",";	
				Thread.sleep(2000);
								
 		}catch (Exception e) {
			logger.warn("Failed to get list values from Petition Action List " + e.getMessage());
			
    	}
 		return temp;
 	} 
 	
 	//get list element of Categories
 	public String getCategorieslistfromdropdown(String categName) {		
 		String a="";
 		try { this.selectFilterAndOption("category", categName);
 			  a=this.getvaluesfromMassActionDropdown();				  
		}catch (Exception e)  {
			logger.warn("Failed to get list values from Categories dropdown List " + e.getMessage());
			throw e;
    	}
 		return a;
 	} 
 	//get list element of Products
 	public String getProductslistfromdropdown(String prodtName) {
 		   String b="";
 		   try {
 			this.selectFilterAndOption("product", prodtName);
			  b=this.getvaluesfromMassActionDropdown();				  
		}catch (Exception e)  {
			logger.warn("Failed to get list values from Product dropdown List " + e.getMessage());
			throw e;
    	}
 		return b;
 	} 
 	 //get list element of Personas
 	public String getTargetPersonaslistfromdropdown(String Perid) {
	   String c="";
	   try {
		this.selectFilterAndOption("petition-age", Perid);
		  c=this.getvaluesfromMassActionDropdown();				  
			
		}catch (Exception e)  {
			logger.warn("Failed to get list values from Personaid dropdown List " + e.getMessage());
			throw e;
		}
	   return c;
 	} 
 	
 	//Get Completed Table column values in array list
	@Step("Get Completed Table column values in array list")
	public List<String> getCompletedTableColumnValuesInList( int clnIdx) {
		List<String> tblColumnList=new ArrayList<String>();
		try {
			int intrc=this.getSize(completedTableRows);
			if (intrc>0)
			{
				if (intrc>10) intrc=10;
				tblColumnList=this.getValuesFrmTblColumn(compltdTblCellValXpath,intrc,clnIdx);
			}
			else
			{
				logger.info("No Rows displayed on Completed Table");
				
			}
		}
		catch (Exception e) {
			logger.info("Failed to Get Queued Table row count  " + e.getMessage());
		}
		return tblColumnList;
	} 
	
	//Select  the filter and get Queue table count
	@Step("Select  Persona Filter and get Queue Table count")
	public int getQueueCountAfterFilter(String name,String value){

		int rCnt=0;	
			try {		
				boolean flag=this.selectFilterAndOption(name, value);
				if(flag)
				  rCnt=this.getSize(queuedTableRows);
				
			}catch (Exception e) {
				logger.info("Failed to Verify Queded table after select Persona filter " + e.getMessage());
			}
			return rCnt;
	} 
	
	// get Queue table count
	@Step("get Queue Table count")
	public int getQueueTableCount(){

		int rCnt=0;	
			try { this.click(lnkQueuedTab);	
				  Thread.sleep(3000);
				 rCnt=this.getSize(queuedTableRows);
				
			}catch (Exception e) {
				logger.info("Failed to get Queued table count" + e.getMessage());
			}
			return rCnt;
	}

	 	
}
