package com.ea.wwce.automation.gcn.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.gcn.config.GcnDataConstants;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import io.qameta.allure.Step;

public class AdminOppBudgetPage extends GcnBasePageObjects{
	
	/*
	 * This Page Object Class defines all the elements in the budget Estimation Screen
	 * and defines methods to do budget estimation to game changers by flagging them for 
	 * Stipend or Content and selecting respective payment methods as PayPal or iProc
	 */
	
	private static final Logger logger=Logger.getLogger(AdminOppBudgetPage.class);
	
	public AdminOppBudgetPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	AdminViewUserProfile adminViewUserProfile = new AdminViewUserProfile(driver);
	
	AdminOppDetailsPage adminOppDetailsPage=new AdminOppDetailsPage(driver);
		
	@FindBy(xpath="//div[@id='title']")
	WebElement oppTitle;
	
	@FindBy(xpath="//h1[@class='page-title' and contains(text(),'Budget Estimation')]")
	WebElement budgetPageLabel;
	
	@FindBy(xpath="//img[@class='game-image']")
	WebElement oppImg;
	
	@FindBy(xpath="//div[@class='col-sm-6']/div[2]/span[2]/b")
	WebElement oppDate;
		
	@FindBy(xpath="//button[contains(text(),'Modify')]")
	WebElement modifyBtn;
	
	@FindBy(xpath="//a[contains(text(),'Flag for Stipend Payment (PayPal)')]")
	WebElement stipendBtn;
	
	@FindBy(xpath="//a[contains(text(),'Flag for Stipend Payment (iProc)')]")
	WebElement stipendIprocBtn;
	
	@FindBy(xpath="//a[contains(text(),'Flag for Content Payment (iProc)')]")
	WebElement contentBtn;
	
	@FindBy(xpath="//button[contains(text(),'Save')]")
	WebElement saveBtn;
	
	@FindBy(xpath="//thead/tr/th/span[contains(text(),'Status')]")
	WebElement statusLabel;
	
	@FindBy(xpath="//thead/tr/th/span[contains(text(),'Status')]/span")
	WebElement statusSortBtn;
	
	@FindBy(xpath="//thead/tr/th/span[contains(text(),'User')]")
	WebElement userLabel;
	
	@FindBy(xpath="//thead/tr/th/span[contains(text(),'User')]/span")
	WebElement userSortBtn;
	
	@FindBy(xpath="//thead/tr/th/span[contains(text(),'Full Name')]")
	WebElement fNameLabel;
	
	@FindBy(xpath="//thead/tr/th/span[contains(text(),'User')]/span")
	WebElement fNameSortBtn;
	
	@FindBy(xpath="//thead/tr/th/span[contains(text(),'Payment')]")
	WebElement paymentLabel;
	
	@FindBy(xpath="//thead/tr/th/span[contains(text(),'Payment')]/span")
	WebElement paymentSortBtn;
	
	@FindBy(xpath="//thead/tr/th/span[contains(text(),'Method')]")
	WebElement methodLabel;
	
	@FindBy(xpath="//thead/tr/th/span[contains(text(),'Payment')]/span")
	WebElement methodSortBtn;
	
	@FindBy(xpath="//table[@class='table']//tbody")
	WebElement userFrame;
	
	@FindBy(xpath="//app-display-message//div[@class='col-sm-7 message']/span")
	WebElement successMsg;
	
	@FindBy(xpath="//button[contains(text(),'Add')]")
	WebElement addBtn;
	
	@FindBy(xpath="//div[@class='jw-modal-body']")
	WebElement paymentPopup;
	
	@FindBy(xpath="//h3[@id='myModalLabel']")
	WebElement paymentPopupHeaderText;
	
	@FindBy(xpath="//input[@name='popuppaymentamount']")
	WebElement amountTextField;
	
	@FindBy(xpath="//input[@name='amountofstipendorcontent']")
	WebElement amountField;
		
	@FindBy(xpath="//div[@class='jw-header']//button[@type='button']/span[contains(text(),'Cancel')]")
	WebElement cancelLink;
	
	@FindBy(xpath="//div[@class='jw-header']//button[@type='button']/span[2]")
	WebElement calcelIcon;
	
	
	String userRow="//table[@class='table']//tbody/tr";
	
	String userCheckBox="//tr/td[10]//span";
	String userCheckBoxD="//tr[#]/td[10]//span";
	String userCheckBoxInputXpath="//tr/td[10]//span/preceding-sibling::input";
	String userCheckBoxInputXpathD="//tr[#]/td[10]//span/preceding-sibling::input";
	
	String amountFieldXpathD="//tr[#]/td[5]//input";
		
	String userNameXpath="//tr/td[2]/a[contains(@href,'site/user&id')]/span";
	
	String statusXpath="//tr/td[1]/span";
	
	String userFullNamesXpath="//tr/td[2]/a[contains(@href,'site/user&id')]/following-sibling::div";
	
	String paymentTypeXpath="//div[@id='main-grid-div']/div/div[6]/div/div/span";
	String paymentTypeXpathD="//div[@id='main-grid-div']/div[#]/div[6]/div/div/span";
	
	String attribute_model="ng-reflect-model";
	String attribute_disabled="ng-reflect-model";
		
	
	// UI Checks
	
	@Step("verify Opportunity Details")
	public boolean verifyOpportunityDetails() {
		boolean a=false;
		if(this.isElementVisible(oppTitle, GcnDataConstants.IMPLICIT_TIMEOUT) && 
				this.isElementVisible(oppImg, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(oppDate, GcnDataConstants.IMPLICIT_TIMEOUT) ) {
			a=true;
		}
		return a;
	}
		
	@Step("Verify Save button Status")
	public boolean isSaveBtnDisplayedAndEnabled() {
		boolean a;
		logger.info("Check for the Save Button");
		if(this.isElementVisible(saveBtn, GcnDataConstants.IMPLICIT_TIMEOUT) && saveBtn.isEnabled()) {
			a=true;
			logger.info("Save button is Displayed and Enabled");
		}else {
			a=false;
			logger.info("Save button is Not Displayed OR Not Enabled");
		}
		return a;
	}
	
	// UI Actions.
	
	@Step("verify the Navigation into Budget Estimation Page")
	public boolean verifyNavigationToBudgetEstPage() {
		// method to check landing into Budget Estimation Page.
		logger.info("Check landing into Budget Estimation Page");
		boolean a=false;
		this.verifyPageIsLoaded();
		if(this.isElementVisible(budgetPageLabel, GcnDataConstants.IMPLICIT_TIMEOUT) && 
				this.isElementVisible(oppTitle, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			logger.info("Landed into Opportunity Budget Estimation Page");
			a=true;
		}else {
			logger.info("Error in Budget Estimation Page");
		}
		return a;
	}
	
	@Step("Verify if Checkbox is Selectable")
	public boolean verifyCheckboxIsClickable(WebElement e) {
		boolean a;
		try {
			this.click(e);
			a=true;
		}catch(ElementNotInteractableException ex) {
			a=false;
		}
		return a;
	}
	
		
	public List<WebElement> usersList() {
		logger.info("Check the Users List in the Budget Estimation Page");
		List<WebElement> userRows=userFrame.findElements(By.xpath(userRow));
		return userRows;
	}
		
	public List<WebElement> noOfNotJoinedUsers() {
		logger.info("Check the No of Not Joined Users in the Budget Estimation Page");
		List<WebElement> notJoinedUserRows=new ArrayList<WebElement>();
		List<WebElement> joiningStatusList=userFrame.findElements(By.xpath(statusXpath));
		for(WebElement e:joiningStatusList) {
			if(e.getText().equals("Not Joined")) {
				notJoinedUserRows.add(e);
			}else {
				continue;
			}
		}
		logger.info("No of Not Joined Users are : "+ (this.getSize(notJoinedUserRows)+1));
		return notJoinedUserRows;
	}
	
	public List<WebElement> noOfJoinedUsers(){
		logger.info("Check for the No of Joined Users");
		List<WebElement> joinedUserRows=new ArrayList<WebElement>();
		List<WebElement> joiningStatusList=userFrame.findElements(By.xpath(statusXpath));
		for(WebElement e:joiningStatusList) {
			if(e.getText().equals("Joined")) {
				joinedUserRows.add(e);
			}else {
				continue;
			}
		}
		logger.info("No of Joined Users are : "+ this.getSize(joinedUserRows));
		return joinedUserRows;
	}
	
	public List<WebElement> noOfDeclinedUsers(){
		logger.info("Check for the No of Joined Users");
		List<WebElement> declinedUserRows=new ArrayList<WebElement>();
		List<WebElement> joiningStatusList=userFrame.findElements(By.xpath(statusXpath));
		for(WebElement e:joiningStatusList) {
			if(e.getText().equals("Declined")) {
				declinedUserRows.add(e);
			}else {
				continue;
			}
		}
		logger.info("No of Joined Users are : "+ this.getSize(declinedUserRows));
		return declinedUserRows;
	}
	
	
	public int noOfUserCheckBox() {
		logger.info("Check the No of User Check Boxes");
		List<WebElement> checkBox=userFrame.findElements(By.xpath(userCheckBox));
		logger.info("No of User Check Box is " + this.getSize(checkBox));
		return this.getSize(checkBox);
	}
	
	public boolean isUserNameSorted(){
		logger.info("Check the Sorting for User Names");
		this.click(userLabel);
		List<String> userNames=getUserNames();
		
		logger.info("List of Users are : "+ userNames);
		List<String> s1=this.firstCharToUpper(userNames);

		return this.isListSorted(s1);
	}
	
	public List<String> getUserNames(){
		logger.info("Get the List of User Names");
		List<String> userNames=new ArrayList<String>();
		List<WebElement> userNameList=userFrame.findElements(By.xpath(userNameXpath));
		for(WebElement e:userNameList) {
			userNames.add(e.getText());
		}
		logger.info("List of Users are : "+ userNames);
		return userNames;
	}
	
	public List<String> getStatusAsList(){
		List<WebElement> userStatusListEl=userFrame.findElements(By.xpath(statusXpath));
		List<String> userStatusList=new ArrayList<String>();
		
		for(int i=1;i<userStatusListEl.size();i++) {
			userStatusList.add(userStatusListEl.get(i).getText());
		}
		logger.info("Status List is : "+userStatusList);
		return userStatusList;
	}
	
	public boolean isStatusSorted() {
		logger.info("Check the Sorting for Joined Status");
		this.click(statusLabel);
		List<String> stList=getStatusAsList();
		return this.isListSorted(stList);
	}
	
	public List<String> getFullNameList(){
		List<WebElement> fullNameEl=userFrame.findElements(By.xpath(userFullNamesXpath));
		List<String> fullNameList=new ArrayList<String>();
		for(int i=1;i<fullNameEl.size();i++) {
			fullNameList.add(fullNameEl.get(i).getText());
		}
		logger.info("List of Full Names are : "+fullNameList);
		return fullNameList;
		
	}
	
	@Step("Verify if Budget Estimation list is empty")
	public boolean verifyEmptyUserList() {
		boolean a=false;
		if(this.isElementVisible(userFrame, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			a=true;
		}
		return a;
	}
	
	@Step("Verify if FullName are sorted")
	public boolean isFullNameSorted() {
		logger.info("Check for Full Names Sorting");
		//List<String> fNlist=getFullNameList();
		this.click(fNameLabel);
		List<String> fNlist=getFullNameList();
		return this.isListSorted(fNlist);
	}
	
	@Step("Verify if FullName are reverse sorted")
	public boolean isFullNameReverseSorted() {
		// dev need to correct the DOM for 1st row of the grid.
		logger.info("Check for Reverse Sorting of Full Name");
		List<String> fNlist1=getFullNameList();
		this.click(fNameLabel);
		List<String> fNlist2=getFullNameList();
		return this.isListReverseSorted(fNlist1,fNlist2);
	}
	
	@Step("Navigate to User Profile page from Estimation Page")
	public void goToUserProfileByUserName(String username) {
		logger.info("Navigate to User Profile");
		List<WebElement> userNameList=userFrame.findElements(By.xpath(userNameXpath));
		for(WebElement e:userNameList) {
			if(e.getText().equalsIgnoreCase(username)) {
				this.click(e);
				this.waitForElementToBeVisible(adminViewUserProfile.userName, GcnDataConstants.IMPLICIT_TIMEOUT);
				logger.info("Navigating to User Profile");
				break;
			}else {
				logger.info("User Name Not Found");
			}
		}
	
	}
		
	@Step("Check for Full Name")
	public boolean verifyFullNameByUserName(String username,String fullName) {
		logger.info("Check for full Name by UserName");
		boolean a=false;
		List<WebElement> list1=userFrame.findElements(By.xpath(userNameXpath));
		List<WebElement> list2=userFrame.findElements(By.xpath(userFullNamesXpath));
		for(int i=0;i<list1.size();i++) {
			if(list1.get(i).getText().equals(username) && list2.get(i).getText().equals(fullName)) {
				logger.info("User Name and Full Name Matched");
				a=true;
				break;
			}else {
				logger.info("User Name and Full Name do not Match");
				a=false;
			}
			
		}
		return a;
	}
	
	@Step("Save Budget Estimation")
	public boolean saveBudgetEstimationForAll(String budgetEstSuccessMsg,String amount,String paytype) {
		logger.info("Click on Save and Check for Success Msg");
		boolean a=false;
		verifyAllJoinedUserSelected();
		this.moveToElement(modifyBtn);
		this.windowScrollUp();
		this.click(modifyBtn);
		if(paytype.equals("stipend")) {
			this.click(stipendBtn);
			logger.info("Estimating as Stipend");
		}else if(paytype.equals("content")) {
			this.click(contentBtn);
			logger.info("Estimating as Content");
		}
		this.sendKeys(amountTextField, amount);
		this.click(addBtn);
		this.moveToElement(saveBtn);
		this.windowScrollDwn();
		this.click(saveBtn);
		this.waitForElementToBeVisible(successMsg, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.isElementVisible(successMsg, GcnDataConstants.IMPLICIT_TIMEOUT);
		if(successMsg.getText().equals(budgetEstSuccessMsg)) {
			logger.info("Budget Estimate Saved Message displayed Successfully");
			a=true;
		}else {
			logger.info("Budget Estimate Saved Message Not Displayed");
		}
		return a;
	}
		
	@Step("Verify if Save button is disabled")
	public boolean saveBtnDisabled() {
		boolean a=false;
	
		logger.info("Check for Save button Disabled");
		try {
			if(this.getAttributeValue(saveBtn, attribute_disabled)==null) {
				logger.info("Button is Disabled");
				a=true;
			}
		}catch(Exception e) {
			logger.info("Button is Not Disabled");
			a=false;
			
		}
		
		return a;
	}
	
	@Step("Verify Not Joined User should not be Selectable")
	public boolean isNotJoinedUserSelectable() {
		// returns true if checkbox for Not Joined is Not Checkable.
		boolean a=false;
		logger.info("Check for selecting Not Joined User");
		List<WebElement> rows=userFrame.findElements(By.xpath(userRow));
	
		for(int i=1;i<rows.size();i++) {
			if(rows.get(i).getText().contains("Not Joined")) {
				rows.get(i).findElement(By.xpath(userCheckBox)).click();

				if(this.getAttributeValue(rows.get(i).findElement(By.xpath(userCheckBoxInputXpath)), attribute_model).equals("false")) {
					logger.info("Not Joined User Check Box is Not Checkable");
					a=true;
				}
			}
		}
		return a;
	}
	
	@Step("Verify Declined User should not be Selectable")
	public boolean isDeclinedUserSelectable() {
		// returns true if checkbox for Declined is Not Checkable.
		boolean a=false;
		logger.info("Check for selecting Declined User");
		List<WebElement> rows=userFrame.findElements(By.xpath(userRow));
	
		for(int i=1;i<rows.size();i++) {
			if(rows.get(i).getText().contains("Declined")) {
				rows.get(i).findElement(By.xpath(userCheckBox)).click();

				if(this.getAttributeValue(rows.get(i).findElement(By.xpath(userCheckBoxInputXpath)), attribute_model).equals("false")) {
					logger.info("Not Joined User Check Box is Not Checkable");
					a=true;
				}
			}
		}
		return a;
	}
	
	@Step("Verify if All Joined users are selected")
	public boolean verifyAllJoinedUserSelected() {
		// return true if checkbox for Joined users is Checkable.
		boolean a=false;
		String s="";
		//this.click(statusLabel);
		logger.info("Check for selecting Joined User");
		List<WebElement> userStatusListEl=userFrame.findElements(By.xpath(statusXpath));
		for(int i=0;i<userStatusListEl.size();i++) {
			s=String.valueOf(i+1);
			if(userStatusListEl.get(i).getText().equals("Joined")) {
				try {
					this.click(findDynamicElement(userCheckBoxD, s));
					if(this.getAttributeValue(this.findDynamicElement(userCheckBoxInputXpathD, s), attribute_model).equals("true")) {
						logger.info("Joined User Check Box is Checkable");
						a=true;
						
					}
				}catch(WebDriverException e) {
					logger.info("Joined User Check Box is Not Checkable");					
				}
			}
		}
		return a;	
	}
	
	@Step("Verify Setting All User for Stipend")
	public boolean verifySetAmountForAll(String amount) {
		boolean a=false;
		String s="";
		List<WebElement> userStatusListEl=userFrame.findElements(By.xpath(statusXpath));
		for(int i=0;i<userStatusListEl.size();i++) {
			s=String.valueOf(i+1);
			if(userStatusListEl.get(i).getText().equals("Joined")) {
				try {
					if(this.findDynamicElement(amountFieldXpathD, s).getAttribute(attribute_model).equals(amount)) {
						logger.info("User Stipend Set to :"+amount);
						a=true;
						
					}
				}catch(WebDriverException e) {
					logger.info("Amount is not Set Properly");					
				}
			}
		}
		return a;
	}
	
	@Step("Verify if Single Joined User is Selected")
	public boolean verifySingleUserSelected() {
		boolean a=false;
		String s="";
		logger.info("Check for selecting Joined User");
		List<WebElement> joinedList=noOfJoinedUsers();
		s=String.valueOf(getRandNum(joinedList.size()));
		for(int i=0;i<joinedList.size();i++) {
			if(joinedList.get(i).getText().equals("Joined")) {
				try {
					
					this.moveToElement(findDynamicElement(userCheckBoxD, s));
					this.click(findDynamicElement(userCheckBoxD, s));
					if(this.getAttributeValue(this.findDynamicElement(userCheckBoxInputXpathD, s), attribute_model).equals("true")) {
						logger.info("Joined User Check Box is Checkable");
						a=true;
						
					}
				}catch(ElementNotSelectableException e) {
					logger.info("Joined User Check Box is Not Checkable" + e);	
				}
			}
		}
		return a;
	}
	
	@Step("Verify if Joined user is selectable")
	public boolean isJoinedUserSelectable2() {
		boolean a=false;
		this.click(statusLabel);
		logger.info("Check for selecting Joined User");
		List<WebElement> userStatusListEl=userFrame.findElements(By.xpath(statusXpath));
		for(int i=1;i<userStatusListEl.size();i++) {
			if(userStatusListEl.get(i).getText().equals("Joined")) {
				try {
					userStatusListEl.get(i).findElement(By.xpath(userCheckBox)).click();
					if(this.getAttributeValue(userStatusListEl.get(i).findElement(By.xpath(userCheckBoxInputXpath)), attribute_model).equals("true")) {
						logger.info("Joined User Check Box is Checkable");
						a=true;
					}
				
				}catch(WebDriverException e) {
					logger.info("Joined User Check Box is Not Checkable");
					
				}
				continue;
			}
		}
		return a;
	}
	
	@Step("Verify if all Joined Users are Marked for Payment Type as Stipend")
	public boolean isDefaultMarkAsStipendForJoined() {
		// Check for all Joined users Marked as Stipend.
		boolean a=false;
		List<WebElement> userStatusListEl=userFrame.findElements(By.xpath(statusXpath));
		for(WebElement e:userStatusListEl) {
			if(e.getText().startsWith("Joined") && e.findElement(By.xpath(paymentTypeXpath)).getText().equals("Stipend")) {
				logger.info("Joined user are marked for Stipend by default");
				a=true;
			}else {
				logger.info("Joined user Not Marked for Stipend by default");
			}
		
		}
		return a;
	}
	
	@Step("Verify all Not Joined user are marked for Payment type as Na")
	public boolean isDefaultMarkForNotJoined() throws InterruptedException {
		// check for Not Joined User as marked for Payment type as NA.
		boolean a=false;
		String s="";
		this.click(adminOppDetailsPage.budgetLink);
		//this.click(statusLabel);
		List<WebElement> userStatusListEl=userFrame.findElements(By.xpath(statusXpath));
		for(int i=1;i<userStatusListEl.size();i++) {
			s=String.valueOf(i+2);
			
			if(userStatusListEl.get(i).getText().startsWith("Not")) {
				if(this.findDynamicElement(paymentTypeXpathD, s).getText().equals("")) {
					//logger.info(userStatusListEl.get(i).findElement(By.xpath(paymentTypeXpath)).getText());
					logger.info("Not Joined User has Payment type as blank");
					a=true;
				}else {
					logger.info("Not Joined User has incorrect Payment Type");
				}
			}
			
			/*if(userStatusListEl.get(i).getText().startsWith("Not")) {
				if(userStatusListEl.get(i).findElement(By.xpath("//div[@id='main-grid-div']/div["+(i+2)+"]/div[6]/div/div/span")).getText().equals("")) {
					//logger.info(userStatusListEl.get(i).findElement(By.xpath(paymentTypeXpath)).getText());
					logger.info("Not Joined User has Payment type as blank");
					a=true;
				}else {
					logger.info("Not Joined User has incorrect Payment Type");
				}
			}*/
		}
		return a;
	}
	
	@Step("Verify Modify Btn and Options Displayed and Enabled")
	public boolean verifyModifyBtn_OptionsDisplayed() {
		boolean a=false;
		
		if(modifyBtn.isDisplayed()) {
			this.click(modifyBtn);
			if(stipendBtn.isDisplayed() && stipendIprocBtn.isDisplayed() && contentBtn.isDisplayed()) {
				a=true;
			}
		}
		return a;
	}
	
} 
