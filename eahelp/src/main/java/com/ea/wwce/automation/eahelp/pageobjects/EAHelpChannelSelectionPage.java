package com.ea.wwce.automation.eahelp.pageobjects;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.ea.wwce.automation.eahelp.config.EAHELPDataConstants;
import com.ea.wwce.automation.eahelp.enums.CaseType;
import io.qameta.allure.Step;
import net.bytebuddy.implementation.bytecode.Throw;

/**
 * 
 * @author sadabala
 *
 */
public class EAHelpChannelSelectionPage extends EAHelpBasePageObject {

	public EAHelpChannelSelectionPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(EAHelpChannelSelectionPage.class);

	@FindBy(xpath = "//div[@class='email-channel channel-form']//input[@name='subject']")
	WebElement emailSubject;
	@FindBy(xpath = "//div[@class='call-channel channel-form']//input[@name='subject']")
	WebElement phoneSubject;
	@FindBy(xpath = "//div[@class='chat-channel channel-form']//input[@name='subject']")
	WebElement chatSubject;
	@FindBy(css = "input[name='subject']")
	WebElement subject;
	@FindBy(xpath = "//textarea[@name='description']")
	WebElement description;
	@FindBy(id = "emailSubmit")
	WebElement emailSubmit;
	@FindBy(name = "callbackNumber")
	WebElement txtPhoneNumber;
	@FindBy(css = "a[id='phoneSubmit'][class*=' active']")
	WebElement callMe;
	@FindBy(id = "chatSubmit")
	WebElement chatNow;
	@FindBy(css = "a[id*='Attach']")
	WebElement attachmentButton;
	@FindBy(xpath = "//div[@class='page-loader' and @style='display: block;']")
	WebElement pageLoader;
	@FindAll(@FindBy(xpath = "//span[@class='name B2']"))
	List<WebElement> fileNames;
	@FindBy(className = "file error")
	WebElement filerror;
	@FindBy(css = ".channel-box-body .B3 a")
	WebElement privacyLink;
	@FindBy(css = "div.action-panel a.eapl-cta-first.B2.x-scope.ea-cta-0")
	WebElement ruMyGameLink;
	@FindBy(css = ".B3.case-info-platform")
	WebElement selectedPlatform;
	@FindBy(css = ".B3.case-info-product")
	WebElement selectedProduct;
	@FindBy(css = ".B3.case-info-cat")
	WebElement selectedCategory;
	@FindBy(css = ".B3.case-info-issue")
	WebElement selectedSubCategory;
	@FindBy(css = ".channel-box.phone  img.expand-icon")
	WebElement phoneExpandIcon;
	@FindBy(css = ".channel-box.phone  img.collapse-icon")
	WebElement phoneCollapseIcon;
	@FindBy(css = ".channel-box.chat  img.expand-icon")
	WebElement chatExpandIcon;
	@FindBy(css = ".channel-box.chat  img.collapse-icon")
	WebElement chatCollapseIcon;
	@FindBy(css = ".channel-box.email  img.expand-icon")
	WebElement emailExpandIcon;
	@FindBy(css = ".channel-box.email  img.collapse-icon")
	WebElement emailCollapseIcon;
	@FindBy(css = "a[class*='deactive']")
	WebElement isChannelButtonDisabled;
	@FindBy(css = "a[class*='active']")
	WebElement isChannelButtonActive;
	@FindBy(css = ".channel-box.deflection.active")
	WebElement deflectionMessage;
	@FindBy(css = "div.container.content-col h3+bdi p")
	WebElement czicrmessage;
	@FindBy(css = "h1[class*=eapl-header__title]")
	WebElement lblcaseinformation;
	@FindBy(css = ".case-info-box p")
	WebElement lblyourcaseinfo;
	@FindBy(css = "h3[class*='ea-subheading']")
	WebElement lblrecommendedsupportoption;
	@FindBy(css = "div.community.static-channel-bar h4")
	WebElement lblcalleahelp;
	@FindBy(css = "p.static-content a[href$='/new']")
	WebElement simcityValidation2;
	@FindBy(css = "p.static-content a[href$='/SimCity_BuildIt']")
	WebElement simcityValidation3;
	@FindBy(css = ".msg.B3")
	WebElement emailSLA;
	@FindBy(css = "#phoneAreaCodeId :nth-child(3) label.B2 p")
	WebElement countryLabel;
	@FindBy(css = "[name='callbackCountry'] span[class*='selected-text']")
	WebElement selectedCountry;
	@FindBy(css = ".B3.ph_no_error.single.show")
	WebElement phoneNumberError;
	@FindBy(xpath = "//div[@class='chat-channel channel-form']")
	WebElement chatDeflection;
	@FindBy(css = "a[id='chatSubmit'][class*=' active']")
	WebElement chatnowActive;


	// By locators
	By progressLoader = By.className("progressImg");

	// Dymaic locators
	String simcityValidation1 = "//p[@class='static-content']/a[not(contains(@href,'#'))][1]";

	// Create objects for required pages
	EAHelpLoginPage eaHelpLoginPage = new EAHelpLoginPage(driver);
	EAHelpHomePage eaHelpHomePage = new EAHelpHomePage(driver);
	EAHelpCaseInformationPage eaHelpCaseInformationPage = new EAHelpCaseInformationPage(driver);
	EAHelpProductPage eaHelpProductPage = new EAHelpProductPage(driver);
	EAHelpCaseConfirmationPage eaHelpCaseConfirmationPage = new EAHelpCaseConfirmationPage(driver);
	EAHelpContactInformationPage contactInformationPage = new EAHelpContactInformationPage(driver);
	EAHelpGameLibraryPage eaHelpGameLibraryPage = new EAHelpGameLibraryPage(driver);

	// ================================================================================

	// Verify EMail SLA text
	public String verifyEmailSLA() {
		return this.getText(emailSLA);
	}

	// Verify simcity validations
	public boolean validateSimCityICRPage(String locale, String val1, String val2, String val3) {

		boolean isTrue = false;
		this.verifyPageIsLoaded();
		this.waitForElementToBeVisible(simcityValidation2, 30);
		String msg = this.getTextFromDynamicElement(simcityValidation1, locale).trim();
		if (msg.equalsIgnoreCase(val1)) {
			msg = this.getText(simcityValidation2).trim();
			if (msg.equalsIgnoreCase(val2)) {
				msg = this.getText(simcityValidation3).trim();
				isTrue = true;
			}
		}
		return isTrue;
	}

	// Verify CZ ICR page validations
	public boolean isCZICRPageInEnglish(String a, String b, String c, String d) {
		boolean isTrue = false;

		String msg = this.getText(lblcaseinformation).trim();
		if (msg.equalsIgnoreCase(a)) {
			msg = this.getText(lblyourcaseinfo).trim();
			if (msg.equalsIgnoreCase(b)) {
				msg = this.getText(lblrecommendedsupportoption).trim();
				if (msg.equalsIgnoreCase(c)) {
					msg = this.getText(lblcalleahelp).trim();
					if (msg.equalsIgnoreCase(d)) {
						isTrue = true;
					}
				}
			}
		}

		return isTrue;
	}

	@Step("Verify Deflection message")
	public String getMessageOnCZICRPage() {
		String msg = null;
		try {
			msg = this.getText(czicrmessage);
		} catch (Exception e) {
			throw e;
		}
		return msg;
	}

	@Step("Verify Deflection message")
	public String verifyDeflectionMessage() {
		String deflectionMsg = null;
		try {
			deflectionMsg = this.getText(deflectionMessage);
		} catch (Exception e) {
			throw e;
		}
		return deflectionMsg;
	}

	@Step("Get paltform value from caseinformation")
	public String getSelectedPlatform() {
		logger.info("Select platform from case information page");
		return this.getText(selectedPlatform);
	}

	@Step("Get Product value from caseinformation")
	public String getSelectedProduct() {
		logger.info("Get value for selected product from case information page");
		return this.getText(selectedProduct);
	}

	@Step("Get category value from caseinformation")
	public String getSelectedCategory() {
		logger.info("Get value for selected category from case information page");
		return this.getText(selectedCategory);
	}

	@Step("Get sub category value from caseinformation")
	public String getSelectedSubCategory() {
		logger.info("Get value for selected subcategory from case information page");
		return this.getText(selectedSubCategory);
	}

	@Step("Verify AHQ banner is visible")
	public boolean isAHQBannerPresent() {
		logger.info("Verify AHQ banner is visible");
		return this.isElementVisible(lblcalleahelp, 6);
	}

	@Step("Verify phone expand button is visible")
	public boolean isPhoneExpandButtonPresent() {
		logger.info("Verify phone expand button is visible");
		return this.isElementVisible(phoneExpandIcon, 6);
	}

	@Step("Verify chat expand button is visible")
	public boolean isChatExpandButtonPresent() {
		logger.info("Verify chat expand button is visible");
		return this.isElementVisible(chatExpandIcon, 6);
	}

	@Step("Verify email expand button is visible")
	public boolean isEmailExpandButtonPresent() {
		logger.info("Verify email expand button is visible");
		return this.isElementVisible(emailExpandIcon, 6);
	}

	@Step("Verify phone Collapse button is visible")
	public boolean isPhoneCollapseButtonPresent() {
		logger.info("Verify phone Collapse button is visible");
		return this.isElementVisible(phoneCollapseIcon, 6);
	}

	@Step("Verify chat Collapse button is visible")
	public boolean isChatCollapseButtonPresent() {
		logger.info("Verify chat Collapse button is visible");
		return this.isElementVisible(chatCollapseIcon, 6);
	}

	@Step("Verify email collapse button is visible")
	public boolean isEmailCollapseButtonPresent() {
		logger.info("Verify email collapse button is visible");
		return this.isElementVisible(emailCollapseIcon, 6);
	}

	@Step("Verify ICR page is loaded")
	public boolean isICRPageLoaded() {
		logger.info("Verify ICR page is loaded");
		this.waitForClickableElement(60, selectedPlatform);
		return this.isElementVisible(selectedPlatform, 60);
	}

	@Step("Close chat widnow")
	public void closeChatWidnow(String pageTitle) {
		this.switchWindowByTitle(pageTitle);
		this.closeCurrentWindow();
		// this.switchToDefaultContent();
	}

	@Step("Switch to chat window")
	public void switchToEAHelp(String pageTitle) {
		this.switchWindowByTitle(pageTitle);
	}

	public void clickChatNow() {
		try {
			this.click(chatNow);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			logger.info("Fail to click on chat now button");
		}
	}

	@Step("Submit email-form from Channel Selection Page")
	public void submitChannelForm(CaseType caseType, String txtSubject, String txtDescription, String phoneNumber) {

		logger.info("Submit email Channel Page");
		try {

			if (this.waitForElementToBeVisible(selectedPlatform, 80)) {

				switch (caseType) {

				case email:
					if (isEmailExpandButtonPresent()) {
						this.click(emailExpandIcon);
					}
					this.waitForClickableElement(30, emailSubject);
					this.waitForElementToBeVisible(emailSubject, EAHELPDataConstants.IMPLICIT_TIMEOUT);
					this.sendKeys(emailSubject, txtSubject);
					this.sendKeys(description, txtDescription);
					Thread.sleep(2000);
					this.click(emailSubmit);
					break;
				case chat:
					if (isChatExpandButtonPresent()) {
						this.click(chatExpandIcon);
					}
					this.waitForClickableElement(30, chatSubject);
					this.waitForElementToBeVisible(chatSubject, EAHELPDataConstants.IMPLICIT_TIMEOUT);
					this.sendKeys(chatSubject, txtSubject);
					Thread.sleep(8000);
					this.moveToElement(chatNow);
					this.click(chatNow);
					Thread.sleep(1000);
					break;
				case phone:
					if (isPhoneExpandButtonPresent()) {
						this.click(phoneExpandIcon);
					}
					this.waitForClickableElement(30, phoneSubject);
					this.waitForElementToBeVisible(phoneSubject, EAHELPDataConstants.IMPLICIT_TIMEOUT);
					phoneNumber = createDynamicPhoneNumber();
					this.sendKeys(txtPhoneNumber, phoneNumber);
					this.sendKeys(phoneSubject, txtSubject);
					// Thread.sleep(3000);
					this.isElementEnabled(callMe, 10);
					this.click(callMe);
					break;
				default:
					logger.warn("No Proper channel is assigned... Please make sure to proper channel input");
				}
			}

		} catch (NoSuchElementException e) {
			logger.warn("Failed to Submit Email form" + e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.warn("Failed to submit email form due to timeout" + e.getMessage());

		}
	}

	@Step("Fill case details and verify channel button is enabled")
	public boolean fillCaseDetailsAndVerifyChannelButtonIsEnabled(CaseType caseType, String txtSubject,
			String txtDescription, String phoneNumber) {

		boolean isTrue = false;

		logger.info("Submit Channel Page");
		try {

			this.waitForElementToBeVisible(selectedPlatform, 60);

			switch (caseType) {

			case email:
				this.waitForClickableElement(30, emailSubject);
				this.waitForElementToBeVisible(emailSubject, 30);
				this.isElementVisible(isChannelButtonDisabled, 10);
				this.sendKeys(emailSubject, txtSubject);
				this.sendKeys(description, txtDescription);
				Thread.sleep(2000);
				isTrue = this.isElementVisible(emailSubmit, 10);
				break;
			case chat:
				this.waitForClickableElement(30, chatSubject);
				this.waitForElementToBeVisible(chatSubject, 30);
				this.isElementVisible(isChannelButtonDisabled, 10);
				this.sendKeys(chatSubject, txtSubject);
				Thread.sleep(3000);
				isTrue = this.isElementVisible(chatNow, 10);
				break;
			case phone:
				if (isPhoneExpandButtonPresent()) {
					this.click(phoneExpandIcon);
				}
				this.waitForClickableElement(30, phoneSubject);
				this.waitForElementToBeVisible(phoneSubject, 30);
				// phoneNumber = createDynamicPhoneNumber();
				this.isElementVisible(isChannelButtonDisabled, 3);
				this.sendKeys(txtPhoneNumber, phoneNumber);
				this.sendKeys(phoneSubject, txtSubject);
				// Thread.sleep(1200000);
				this.waitForElementToBeVisible(isChannelButtonActive, 3);
				isTrue = this.isElementEnabled(callMe, 10);
				//System.out.println(isTrue);
				break;
			default:
				logger.warn("No Proper channel is assigned... Please make sure to proper channel input");
				isTrue = false;
			}

		} catch (NoSuchElementException e) {
			logger.warn("Failed to Submit Email form" + e.getMessage());

		} catch (Exception e) {
			logger.warn("Failed to submit email form due to timeout" + e.getMessage());

		}
		return isTrue;
	}

	/**
	 * @description Method to create an auth case for email/chat/phone channel
	 * @param URL
	 * @param testData
	 * @param caseType
	 * @param phoneNumber
	 * @return
	 * @throws InterruptedException
	 */
	public String createAuthCase(Map<String, Object> testData, CaseType caseType) throws InterruptedException {

		String caseNumber = null;

		logger.info("Create case based on user input value " + caseType);

		try {
			// validate login
			eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

			// verify page is loaded
			this.verifyPageIsLoaded();

			Thread.sleep(3000);

			// select product
			eaHelpGameLibraryPage.selectProduct(testData.get("product").toString());

			// verify page is loaded
			this.verifyPageIsLoaded();

			// Click on contact us button
			eaHelpProductPage.clickOnContactUsButton();

			// verify page is loaded
			this.verifyPageIsLoaded();

			// click on create new case button
			eaHelpCaseInformationPage.clickOnCreateNewCaseButton();

			// verify page is loaded
			this.verifyPageIsLoaded();

			// Select platform
			eaHelpCaseInformationPage.selectPlatform(testData.get("platform").toString());

			Thread.sleep(3000);

			// Select category
			eaHelpCaseInformationPage.selectCategory(testData.get("category").toString());

			Thread.sleep(5000);

			// Select sub category
			eaHelpCaseInformationPage.selectSubCategory(testData.get("subcategory").toString());

			Thread.sleep(5000);

			// Click on select contact option
			eaHelpCaseInformationPage.clickOnSelectContactOption();

			// Enter subject and description
			this.submitChannelForm(caseType, testData.get("subject").toString(), testData.get("description").toString(),
					testData.get("phonenumber").toString());

			// Get case number from case confirmation page
			caseNumber = eaHelpCaseConfirmationPage.getCaseNumber().replace("#", "");

		} catch (NoSuchElementException e) {
			logger.warn("Failed to find element" + e.getMessage());
		} catch (TimeoutException e) {
			logger.warn("Failed to find element due to time out" + e.getMessage());
		}
		return caseNumber;
	}

	/**
	 * @description Method to create an auth case for email/chat/phone channel
	 * @param URL
	 * @param testData
	 * @param caseType
	 * @param phoneNumber
	 * @return
	 * @throws InterruptedException
	 */
	public void selectCaseConfiguration(String product, String platform, String category, String subcategory)
			throws InterruptedException {

		logger.info("Selecting product, platform, category and subcategory");

		try {

			// verify page is loaded
			this.verifyPageIsLoaded();

			Thread.sleep(3000);

			// select product
			eaHelpGameLibraryPage.selectProduct(product);

			// Click on contact us button
			eaHelpProductPage.clickOnContactUsButton();

			if (eaHelpCaseInformationPage.iscreateNewCaseButtonVisible()) {
				// click on create new case button
				eaHelpCaseInformationPage.clickOnCreateNewCaseButton();
			}

			Thread.sleep(2000);

			// Select platform
			eaHelpCaseInformationPage.selectPlatform(platform);

			Thread.sleep(2000);

			// Select category
			eaHelpCaseInformationPage.selectCategory(category);

			Thread.sleep(2000);

			// Select sub category
			eaHelpCaseInformationPage.selectSubCategory(subcategory);

			Thread.sleep(2000);

			// Click on select contact option
			eaHelpCaseInformationPage.clickOnSelectContactOption();

			// verify page is loaded
			this.verifyPageIsLoaded();

		} catch (NoSuchElementException e) {
			logger.warn("Failed to find element" + e.getMessage());
		} catch (TimeoutException e) {
			logger.warn("Failed to find element due to time out" + e.getMessage());
		}

	}

	/**
	 * Method to verify email/chat/phone button is enabled
	 * 
	 * @param testData
	 * @param caseType
	 */

	public boolean verifyChannelButtonIsEnabled(Map<String, Object> testData, CaseType caseType) {

		boolean isTrue = false;

		logger.info("Create case based on user input value " + caseType);

		try {
			// validate login
			eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

			// verify page is loaded
			this.verifyPageIsLoaded();

			// select product
			eaHelpGameLibraryPage.selectProduct(testData.get("product").toString());

			// Click on contact us button
			eaHelpProductPage.clickOnContactUsButton();

			// click on create new case button
			eaHelpCaseInformationPage.clickOnCreateNewCaseButton();

			// Select platform
			eaHelpCaseInformationPage.selectPlatform(testData.get("platform").toString());

			// Select category
			eaHelpCaseInformationPage.selectCategory(testData.get("category").toString());

			// Select sub category
			eaHelpCaseInformationPage.selectSubCategory(testData.get("subcategory").toString());

			// Click on select contact option
			eaHelpCaseInformationPage.clickOnSelectContactOption();

			// Verify channel button is disabled
			isTrue = this.fillCaseDetailsAndVerifyChannelButtonIsEnabled(caseType, testData.get("subject").toString(),
					testData.get("description").toString(), testData.get("phonenumber").toString());

		} catch (NoSuchElementException e) {
			logger.warn("Failed to find element" + e.getMessage());
		} catch (TimeoutException e) {
			logger.warn("Failed to find element due to time out" + e.getMessage());
		}
		return isTrue;

	}

	// Get the invalid phone number message
	public String getInvalidPhoneNumberErrorMessage() {
		return this.getText(phoneNumberError);
	}

	/**
	 * Method to create an unauth case for email/chat/phone
	 * 
	 * @param testData
	 * @param caseType
	 * @throws InterruptedException
	 */

	public String createUnAuthCase(Map<String, Object> testData, CaseType caseType) throws InterruptedException {

		String caseNumber = null;

		logger.info("Create unauth case based on user input value " + caseType);
		try {

			// select product
			eaHelpGameLibraryPage.selectProduct(testData.get("product").toString());

			// Click on contact us button
			eaHelpProductPage.clickOnContactUsButton();

			// click on create new case button
			eaHelpCaseInformationPage.clickOnCreateNewCaseButton();

			// Select platform
			eaHelpCaseInformationPage.selectPlatform(testData.get("platform").toString());

			// Select category
			eaHelpCaseInformationPage.selectCategory(testData.get("category").toString());

			// Select sub category
			eaHelpCaseInformationPage.selectSubCategory(testData.get("subcategory").toString());

			// Click on select contact option
			eaHelpCaseInformationPage.clickOnSelectContactOption();

			// fill contact information details
			contactInformationPage.enterContactInformation(testData.get("subcategory").toString(),
					testData.get("subcategory").toString(), testData.get("subcategory").toString());

			// Enter subject and description
			this.submitChannelForm(caseType, testData.get("subject").toString(), testData.get("description").toString(),
					testData.get("phonenumber").toString());

			// Get case number from case confirmation page
			caseNumber = eaHelpCaseConfirmationPage.getCaseNumber();

		} catch (NoSuchElementException e) {
			logger.warn("Failed to find element" + e.getMessage());
		} catch (TimeoutException e) {
			logger.warn("Failed to find element due to time out" + e.getMessage());
		}
		return caseNumber;
	}

	// Verify country field is changed
	public String verifyCountryFieldLabel() {
		this.waitForClickableElement(30, phoneSubject);
		return this.getText(countryLabel);
	}

	// Verify phone number field is changed
	public String verifySelectedCountry() {
		this.waitForClickableElement(30, phoneSubject);
		return this.getText(selectedCountry);
	}

	// attach file
	public void attachfile(String fileName) throws InterruptedException {
		String filePath = EAHELPDataConstants.PROJECT_ROOT_LOCATION
				+ EAHELPDataConstants.EAHELP_TEST_DATA_ATTACHMENT_PATH;

		logger.info("Attach a file in channel selection page ");
		try {

			// add file path to file name
			fileName = filePath + fileName;

			// if
			// (this.getCurrentPageURL().contains("/ru/")||this.getCurrentPageURL().contains("/ar/"))
			// {
			// move to my game link
			this.moveToElement(attachmentButton);
			// } else {

			// move to attachment button
			// this.moveToElement(privacyLink);
			// }

			// Wait for 2 secs
			Thread.sleep(2000);

			// click on attachment button
			this.click(attachmentButton);

			// upload attachment
			this.uploadAttachment(fileName);

			// Wait for 3 secs
			Thread.sleep(3000);
		} catch (Exception e) {
			logger.warn("Fail to attach a file in channel selection page ");
		}

	}

	// Wait for element to be invisible
	public void verifyPageLoaderIsDisplayed(int time) {
		try {
			Thread.sleep(3000);
			logger.info("Wait for loader element to be invisible");
			this.waitForElementToBeInVisible(pageLoader, time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Wait for element to be invisible
	public void verifyProgressLoaderIsInVisible(int time) {
		logger.info("Wait for progress loader to be invisible");
		this.isElementInvisibile(progressLoader, time);

	}

	// Verify subject field is visible
	public void verifySubjectFieldIsVisible(int time) {
		// wait for subject element is visible
		logger.info("Wait for subject field to be visible");
		this.waitForElementToBeVisible(emailSubject, time);

	}

	// Verify file name
	public boolean verifyFileName(String fileName) {
		boolean isTrue = false;
		logger.info("Verifying file name....");

		for (WebElement filename : fileNames) {
			if (filename.getText().equals(fileName))
				logger.info("Verified file name successfully.... " + filename.getText());
			isTrue = true;
			break;
		}
		return isTrue;

	}

	// Find file error for unsupported files
	public void verifyFileError() {
		try {
			logger.info("Verifying file error....");
			this.waitForElementToBeVisible(filerror, 10);
			logger.info("File error for unsupported format files is shown.... " + filerror.getText());
		} catch (NoSuchElementException e) {
			logger.warn("Failed to find element" + e.getMessage());
		} catch (TimeoutException e) {
			logger.warn("Failed to find element due to time out" + e.getMessage());
		}
	}

	public String createDynamicPhoneNumber() {

		String phoneNumber = null;
		int max = 1000000000;
		int min = 1;

		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
		String num = Integer.toString(randomNum);

		if (this.getCurrentPageURL().contains("/en")) {

			if (num.length() > 8) {
				phoneNumber = num.substring(0, 7);
				phoneNumber = "512" + phoneNumber;
			}
		}
		return phoneNumber;

	}

	// Verify Chatdeflection message
	public String verifyChatdeflection() {

		this.waitForClickableElement(30, chatDeflection);
		return this.getText(chatDeflection);
	}

	@Step("Verify Chat channle is Present")
	public boolean isChatChannelPresent() {

		boolean isTrue = true;
		logger.info("Verify Chat channle is Present");
		try {
			isTrue = this.isElementVisible(chatNow, 10);
		} catch (Exception e) {
			logger.warn(" Chat channel not Present" + e);
		}
		logger.info("Chat channel is Present : " + isTrue);
		return isTrue;
	}
	
	// Click on EmailExpanicon
	public void clickEmailExpandicon() {
		try {
			this.click(emailExpandIcon);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			logger.info("Fail to click on email expand icon");
		}
	}


}
