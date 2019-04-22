package com.ea.wwce.automation.salesforce.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;

public class AdvisorProfilePage extends SalesforceBasePageObject {

	private static final Logger logger = Logger.getLogger(AdvisorProfilePage.class);

	public AdvisorProfilePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// Lighting locators
	@FindBy(css = "a[title='New']")
	WebElement newAdvisor;
	@FindBy(css = "label[data-aura-class=\"uiLabel\"]+input[checked=\"checked\"]")
	WebElement active;
	// @FindBy(css = "label[data-aura-class='uiLabel'] +
	// input[type='checkbox']:not([checked='checked'])")
	// WebElement managerStatus;
	@FindBy(css = "div[role='list'] :nth-child(3) input")
	WebElement citadelPassword;
	// @FindBy(css = "div[role='list'] :nth-child(3) a")
	// WebElement managerType;
	@FindBy(css = "div[role='list'] :nth-child(4) input")
	WebElement manager;
	@FindBy(css = "div[role='list'] :nth-child(5) input:first-child")
	WebElement salesforceUser;
	@FindBy(css = "div[role='list'] :nth-child(5) input:last-child")
	WebElement aliasEmail;
	@FindBy(xpath = "//span[text()='Citadel User Name']/parent::label/following-sibling::input")
	WebElement citadelUserName;
	@FindBy(xpath = "//span[text()='Employee Number']/parent::label/following-sibling::input")
	WebElement employeeNumber;
	@FindBy(xpath = "//span[text()='Date Of Birth']/parent::label/following-sibling::div/input")
	WebElement dateOfBirth;
	@FindBy(xpath = "//span[text()='Employment Type']/parent::span/following-sibling::div//a")
	WebElement employmentType;
	// @FindBy(xpath = "//span[text()='Company
	// Name']/parent::span/following-sibling::div//a")
	// WebElement companyName;
	@FindBy(xpath = "//span[text()='Email Encoding']/parent::span/following-sibling::div//a")
	WebElement emailEncoding;
	// @FindBy(xpath = "//span[text()='Date Of
	// Birth']/parent::label/following-sibling::div/input")
	// WebElement floorDate;
	@FindBy(xpath = "//span[text()='GPS Password']/parent::label/following-sibling::input")
	WebElement gpsPassword;
	// @FindBy(xpath = "//span[text()='GPS User
	// Name']/parent::label/following-sibling::input")
	// WebElement gpsUserName;
	// @FindBy(xpath = "//span[text()='Hire
	// Date']/parent::label/following-sibling::div/input")
	// WebElement hireDate;
	@FindBy(xpath = "//span[text()='Lithium Password']/parent::label/following-sibling::input")
	WebElement lithiumPassowrd;
	@FindBy(xpath = "//span[text()='Lithium User Name']/parent::label/following-sibling::input")
	WebElement lithiumUserName;
	@FindBy(xpath = "//span[text()='LiveOps Agent Id']/parent::label/following-sibling::input")
	WebElement liveOpsAgentId;
	@FindBy(xpath = "//span[text()='LiveOps Password']/parent::label/following-sibling::input")
	WebElement liveOpsPassword;
	@FindBy(xpath = "//span[text()='LiveOps User Name']/parent::label/following-sibling::input")
	WebElement liveOpsUserName;
	@FindBy(xpath = "//span[text()='Live Person Agent Id']/parent::label/following-sibling::input")
	WebElement livePersonAgentId;
	@FindBy(xpath = "//span[text()='Chat Concurrency']/parent::label/following-sibling::input")
	WebElement chatConcurrency;
	@FindBy(xpath = "//span[text()='Live Person User Name']/parent::label/following-sibling::input")
	WebElement livePersonUserName;
	@FindBy(xpath = "//span[text()='Live Person Password']/parent::label/following-sibling::input")
	WebElement livePersonPassword;
	@FindBy(xpath = "//span[text()='Proficient Date']/parent::label/following-sibling::div/input")
	WebElement proficentDate;
	// @FindBy(xpath = "//span[text()='Release
	// Date']/parent::label/following-sibling::div/input")
	// WebElement releaseDate;
	@FindBy(xpath = "//span[text()='Zendesk Password']/parent::label/following-sibling::input")
	WebElement zendeskPassword;
	@FindBy(xpath = "//span[text()='Zendesk User Name']/parent::label/following-sibling::input")
	WebElement zendeskUserName;
	// @FindBy(css = "button[title='Save']")
	// WebElement save;
	@FindBy(css = "button[title='Save & New']")
	WebElement saveAndNew;
	@FindBy(css = "button[title='Cancel']")
	WebElement cancel;
	@FindBy(xpath = "//span[text()='Owner']/parent::div/following-sibling::div//span[@class='uiOutputText forceOutputLookup']")
	WebElement owner;

	// Dynamic elements
	String selectType = "//li[@role='presentation']/a[text()='#']";

	// Classic Locators

	@FindBy(xpath = "(//h3[text()='Advisor Profile'])[position()=1]")
	WebElement advisorProfileMenu;
	@FindBy(xpath = "//*[@id=\"advisorProfile\"]/h3[(text()='Advisor Profile')]")
	WebElement advisorProfile;
	@FindBy(css = "#activeUsers .pagination span.actionMenu span#addAction")
	WebElement newUserPlusBtn;
	@FindBy(css = "#firstName")
	WebElement fName;
	@FindBy(css = "#lastName")
	WebElement lName;
	@FindBy(css = "#email")
	WebElement email;
	@FindBy(css = "#userName")
	WebElement userName;
	@FindBy(css = "#title")
	WebElement title;
	@FindBy(css = "#birthDate")
	WebElement dob;
	@FindBy(css = "#EmployeeNumber")
	WebElement empNumber;
	@FindBy(css = "input#isActive")
	WebElement isActive;
	@FindBy(css = "input#isKnowledgeUser")
	WebElement isKnowledgeUser;
	@FindBy(css = "input#isServiceCloudUser")
	WebElement isServiceCloudUser;
	@FindBy(css = "#lpUserName")
	WebElement lpUserName;
	@FindBy(css = "#lpPassword")
	WebElement lpPassword;
	@FindBy(css = "#livePersonAgentID")
	WebElement lpAgentID;
	@FindBy(css = "#loUserName")
	WebElement loUserName;
	@FindBy(css = "#loPassword")
	WebElement loPassword;
	@FindBy(css = "#loAgentID")
	WebElement loAgentID;
	@FindBy(css = "#overrideValidation")
	WebElement overrideValidation;
	@FindBy(css = "#lhUserName")
	WebElement lhUserName;
	@FindBy(css = "#znUserName")
	WebElement znUserName;
	@FindBy(css = "#ctUserName")
	WebElement ctUserName;
	@FindBy(css = "#gpsUserName")
	WebElement gpsUserName;
	@FindBy(css = ".availableCompany.required")
	WebElement companyName;
	@FindBy(css = ".managerName.required")
	WebElement managerName;
	@FindBy(css = ".sfProfiles.required")
	WebElement profile;
	@FindBy(css = ".sfRoles.required")
	WebElement staff;
	@FindBy(css = ".sfEmpType.required")
	WebElement empType;
	@FindBy(css = ".sfManagerType.required")
	WebElement managerType;
	@FindBy(css = "input#managerStatus")
	WebElement managerStatus;
	@FindBy(css = "#timeZone")
	WebElement timeZone;
	@FindBy(css = "input#hireDate")
	WebElement hireDate;
	@FindBy(css = "input#floorDate")
	WebElement floorDate;
	@FindBy(css = "input#releaseDate")
	WebElement releaseDate;
	@FindBy(css = "input#proficientDate")
	WebElement ProficientDate;
	@FindBy(css = ".rightAlign a.btn.save")
	WebElement save;
	@FindBy(css = "a.btn.Add.moveRight")
	WebElement moveRight;
	@FindBy(css = "#dashboard-console-iframe")
	WebElement iframeAdvisorProfile;
	@FindBy(css = "a#validateLivePersonDetails")
	WebElement validateLivePersonDetails;
	@FindBy(css = "a#validateLiveOpsDetails")
	WebElement validateLiveOpsDetails;
	@FindBy(css = "div.popUpContent textarea")
	WebElement notes;
	@FindBy(css = "a.btn.savenotes")
	WebElement saveNotes;

	// Dynamic locators
	String jobRole = "//option[@title='#']";

	// Create advisor profile
	@Step("Create advisor profile")
	public void createAdvisorProfile(String fname, String lname, String mail, String sfuser, String stitle,
			String txtdob, String empnumber, String txtjobRole, String lpuserName, String lppassword, String lpAgentId,
			String lopassword, String louserName, String loAgentId, String lhuserName, String zUserName,
			String ctusername, String gUsername, String cName, String txtManager, String txtProfile, String txtStaff,
			String eType, String managertype, String tZone, String hrDate, String frDate, String rDate, String pfDate,
			String note) throws InterruptedException {

		String[] jobRoles = txtjobRole.split(",");

		this.click(advisorProfileMenu);
		this.click(advisorProfile);
		this.waitAndSwitchToIframe(15, iframeAdvisorProfile);
		this.isElementVisible(newUserPlusBtn, 30);
		this.clickUsingJavaScriptExecutor(newUserPlusBtn, 10);
		this.waitForElementToBeVisible(fName, 30);
		this.sendKeys(fName, fname);
		this.sendKeys(lName, lname);
		this.sendKeys(email, mail);
		this.sendKeys(userName, sfuser);
		this.sendKeys(title, stitle);
		this.sendKeys(dob, txtdob);
		this.sendKeys(empNumber, empnumber);
		this.click(isActive);
		this.click(isKnowledgeUser);
		this.click(isServiceCloudUser);

		for (String role : jobRoles) {
			this.clickOnDynamicElement(jobRole, role);
			this.clickUsingJavaScriptExecutor(moveRight, 10);
		}

		this.sendKeys(lpUserName, lpuserName);
		this.sendKeys(lpPassword, lppassword);
		this.sendKeys(lpAgentID, lpAgentId);
		this.sendKeys(loUserName, louserName);
		this.sendKeys(loPassword, lopassword);
		this.sendKeys(loAgentID, loAgentId);
		this.sendKeys(lhUserName, lhuserName);
		this.sendKeys(znUserName, zUserName);
		this.sendKeys(ctUserName, ctusername);
		this.sendKeys(gpsUserName, gUsername);
		this.selectVisibleText(companyName, cName);
		this.selectVisibleText(managerName, txtManager);
		this.selectVisibleText(profile, txtProfile);
		this.selectVisibleText(staff, txtStaff);
		this.selectVisibleText(empType, eType);
		this.click(managerStatus);
		this.selectVisibleText(managerType, managertype);
		// this.selectVisibleText(timeZone, tZone);
		this.sendKeys(hireDate, hrDate);
		this.sendKeys(floorDate, frDate);
		this.sendKeys(releaseDate, rDate);
		this.sendKeys(ProficientDate, pfDate);

		this.click(validateLiveOpsDetails);
		Thread.sleep(8000);
		this.click(validateLivePersonDetails);
		Thread.sleep(8000);
		this.click(save);
		this.sendKeys(notes, note);
		this.click(saveNotes);
		Thread.sleep(8000);
	}

	// Create advisor profile
	@Step("Create advisor profile")
	public void createAdvisorProfileUsingLighting(String ctPassword, String mType, String mName, String sfuser,
			String aEmail, String ctUsername, String empNumber, String dob, String empType, String cName,
			String eEncoding, String fDate, String gPassword, String gUsername, String hDate, String lPassword,
			String lUserName, String loAgentId, String loPassword, String loUserName, String lpAgentId,
			String lpPassword, String lpUserName, String pDate, String rDate, String zPassword, String zUserName) {

		logger.info("Create advsior profile");
		this.waitForClickableElement(30, newAdvisor);
		this.click(newAdvisor);
		this.click(managerStatus);
		this.sendKeys(citadelPassword, ctPassword);
		this.click(managerType);
		this.clickOnDynamicElement(selectType, mType);
		this.sendKeys(manager, mName);
		this.sendKeys(salesforceUser, sfuser);
		this.sendKeys(aliasEmail, aEmail);
		this.sendKeys(citadelUserName, ctUsername);
		this.sendKeys(employeeNumber, empNumber);
		this.sendKeys(dateOfBirth, dob);
		this.pressEnterKey(owner);
		this.click(employmentType);
		this.clickOnDynamicElement(selectType, empType);
		this.click(companyName);
		this.clickOnDynamicElement(selectType, cName);
		this.click(emailEncoding);
		this.clickOnDynamicElement(selectType, eEncoding);
		this.sendKeys(floorDate, fDate);
		this.pressEnterKey(owner);
		this.sendKeys(gpsPassword, gPassword);
		this.sendKeys(gpsUserName, gUsername);
		this.sendKeys(hireDate, hDate);
		this.pressEnterKey(owner);
		this.sendKeys(lithiumPassowrd, lPassword);
		this.sendKeys(lithiumUserName, lUserName);
		this.sendKeys(liveOpsAgentId, loAgentId);
		this.sendKeys(liveOpsPassword, loPassword);
		this.sendKeys(liveOpsUserName, loUserName);
		this.sendKeys(livePersonAgentId, lpAgentId);
		this.sendKeys(livePersonUserName, lpUserName);
		this.sendKeys(livePersonPassword, lpPassword);
		this.sendKeys(proficentDate, pDate);
		this.sendKeys(releaseDate, rDate);
		this.sendKeys(zendeskPassword, zPassword);
		this.sendKeys(zendeskUserName, zUserName);
	}
}
