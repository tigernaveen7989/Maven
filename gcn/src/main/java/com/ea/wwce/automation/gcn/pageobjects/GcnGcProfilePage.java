package com.ea.wwce.automation.gcn.pageobjects;

import java.awt.AWTException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.ea.wwce.automation.gcn.config.GcnDataConstants;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import io.qameta.allure.Step;

public class GcnGcProfilePage extends GcnBasePageObjects{
	
	/*
	 * This class defines the UI elements and methods for Game Changer Profile page.
	 * Here the game changer can update/change the profile information, social channels etc.
	 */
	
	private static final Logger logger=Logger.getLogger(GcnGcPage.class);
	
	public GcnGcProfilePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//h1[contains(text(),'Profile')]")
	WebElement profileLabel;
	
	@FindBy(xpath="//img[@id='profile-picture']")
	WebElement profilePicture;
	
	@FindBy(xpath="//img[@id='profile-picture' and starts-with(@src,'https://yt3')]")
	WebElement defaultProfilePic;
	
	@FindBy(xpath="//img[@id='profile-picture' and starts-with(@src,'https://s3.amazon')]")
	WebElement userProfilePic;
	
	@FindBy(xpath="//form[@id='profile-picture-form']/span[contains(text(),'Change Profile Picture')]")
	WebElement changeProfilePictureLink;
	
	@FindBy(xpath="//form[@id='profile-picture-form']/button[@id='update-picture']")
	WebElement saveProfilePicture;
	
	@FindBy(xpath="//form[@id='profile-picture-form']/button[@id='cancel-upload-picture']")
	WebElement cancelProfilePicture;
		
	@FindBy(xpath="//form[@id='personalInformation']/div/div/h2[contains(text(),'My Details')]")
	WebElement myDetailsLabel;
	
	@FindBy(xpath="//form[@id='personalInformation']/div/div[2]/a[contains(text(),'Edit')]")
	WebElement myDetailsEditBtn;
	
	@FindBy(xpath="//form[@id='personalInformation']/div/div[2]/a[contains(text(),'Save')]")
	WebElement myDetailsSaveBtn;
	
	@FindBy(xpath="//form[@id='personalInformation']/div/div[2]/a[contains(text(),'Cancel')]")
	WebElement myDetailsCancelBtn;	
	
	@FindBy(xpath="//div[@class='message alert alert-success']")
	WebElement myDetailSavedMsg;	
	
	@FindBy(xpath="//label[@for='firstName']")
	WebElement firstNameLabel;
	
	@FindBy(xpath="//form[@id='personalInformation']/div[2]/div[2]/div/p")
	WebElement firstName;
	
	@FindBy(id="firstName")
	WebElement firstNameEdit;
		
	@FindBy(xpath="//label[@for='lastName']")
	WebElement lastNameLabel;
	
	@FindBy(xpath="//form[@id='personalInformation']/div[2]/div[3]/div/p")
	WebElement lastName;
	
	@FindBy(id="lastName")
	WebElement lastNameEdit;
	
	@FindBy(xpath="//label[@for='username']")
	WebElement userNameLabel;
	
	@FindBy(xpath="//form[@id='personalInformation']/div[3]/div[1]//p")
	WebElement userName;
		
	@FindBy(xpath="//label[@for='dateOfBirth']")
	WebElement dobLabel;
	
	@FindBy(xpath="//form[@id='personalInformation']/div[3]/div[2]//p")
	WebElement dob;
	
	@FindBy(id="dateOfBirth")
	WebElement dobEdit;
		
	@FindBy(xpath="//label[@for='email']")
	WebElement contactEmailLabel;
	
	@FindBy(xpath="//form[@id='personalInformation']/div[4]//p")
	WebElement contactEmail;
	
	@FindBy(id="email")
	WebElement contactEmailEdit;
	
	@FindBy(xpath="//form[@id='gamePreferences']//h2[contains(text(),'Game Preferences')]")
	WebElement gamePreferenceLabel;
	
	@FindBy(xpath="//form[@id='gamePreferences']/div/div[2]/a[contains(text(),'Edit')]")
	WebElement gamePreferenceEditBtn;
	
	@FindBy(xpath="//form[@id='gamePreferences']/div/div[2]/a[contains(text(),'Save')]")
	WebElement gamePreferenceSaveBtn;
	
	@FindBy(xpath="//form[@id='gamePreferences']/div/div[2]/a[contains(text(),'Cancel')]")
	WebElement gamePreferenceCancelBtn;
	
	@FindBy(xpath="//div[@class='message alert alert-success']")
	WebElement gamePrefSaveMsg;
	
	
	
	@FindBy(xpath="//label[contains(text(),'Primary Genre')]")
	WebElement primaryGenreLabel;
	
	@FindBy(xpath="//form[@id='gamePreferences']/div[2]//p")
	WebElement primaryGenre;
	
	@FindBy(id="primaryFranchise")
	WebElement primaryGenreSelect;
	
	@FindBy(xpath="//label[@for='secondaryGenres']")
	WebElement secondaryGenreLabel;
	
	@FindBy(xpath="//form[@id='gamePreferences']/div[2]/div[3]//li")
	WebElement secondaryGenres;
	
	@FindBy(xpath="//label[@for='primaryConsole']")
	WebElement primaryConsoleLabel;
	
	@FindBy(xpath="//form[@id='gamePreferences']/div[3]//p")
	WebElement primaryConsole;
	
	@FindBy(id="primaryConsole")
	WebElement primaryConsoleSelect;
	
	@FindBy(xpath="//label[@for='secondaryConsoles']")
	WebElement secondaryConsoleLabel;
	
	@FindBy(xpath="//form[@id='gamePreferences']/div[3]/div[2]//input[@type='checkbox' and @checked='']")
	WebElement secondaryConsole;
	
	@FindBy(xpath="//h2[contains(text(),'Linked Channels')]")
	WebElement linkAccLabel;
	
	@FindBy(xpath="//img[@src='images/assignment_logo_youtube.png']")
	WebElement youtubeLogo;
	
	@FindBy(xpath="//a[@channel-list-type='youtube']")
	WebElement youtubeAddBtn;
	
	@FindBy(xpath="//img[@src='images/assignment_logo_twitch.png']")
	WebElement twitchLogo;
	
	@FindBy(xpath="//img[@src='images/assignment_logo_twitch.png']")
	WebElement twitchAddBtn;
	
	@FindBy(xpath="//img[@src='images/assignment_logo_mixer.png']")
	WebElement mixerLogo;
	
	@FindBy(xpath="//img[@src='images/assignment_logo_mixer.png']")
	WebElement mixerAddBtn;
		
	@FindBy(xpath="//a[@class='list-group-item' and contains(text(),'Payment settings')]")
	WebElement paymentSettingLink;
	
	@FindBy(xpath="//form[@id='miscellaneousInformation']/div/div[2]/a[contains(text(),'Edit')]")
	WebElement miscEditBtn;
	
	@FindBy(xpath="//form[@id='miscellaneousInformation']/div/div[2]/a[contains(text(),'Save')]")
	WebElement miscSaveBtn;
	
	@FindBy(xpath="//form[@id='miscellaneousInformation']/div/div[2]/a[contains(text(),'Cancel')]")
	WebElement miscCancelBtn;
	
	@FindBy(id="contentLanguage")
	WebElement contLangSel;
	
	@FindBy(id="language")
	WebElement commLangSel;
	
	@FindBy(id="tShirtSize")
	WebElement tSizeSel;
	
	@FindBy(id="certificationNumber")
	WebElement certNumEdit;
	
	@FindBy(xpath="//form[@id='miscellaneousInformation']//div/p[@data-property='contentLanguage']")
	WebElement contLang;
	
	@FindBy(xpath="//form[@id='miscellaneousInformation']//div/p[@data-property='language']")
	WebElement commLang;
	
	@FindBy(xpath="//form[@id='miscellaneousInformation']//div/p[@data-property='tShirtSize']")
	WebElement tShirtSize;
	
	@FindBy(xpath="//form[@id='miscellaneousInformation']//div/p[@data-property='certificationNumber']")
	WebElement certNum;
	
	@FindBy(xpath="//form[@id='miscellaneousInformation']//div[@class='message alert alert-success']")
	WebElement miscSavedMsg;
	
	//String secondaryConsoleXpath="//label[@for='secondaryConsoles']/following-sibling::div/label/input";
	String secondaryConsoleCheckedXpath="//label[@for='secondaryConsoles']/following-sibling::div/label/input[@checked='']";
	String secondaryConsoleXpathD="//label[@for='secondaryConsoles']/following-sibling::div/label/input[@value='#']";
	
	String secondaryGenreXpathD="//label[@for='secondaryGenres']/following-sibling::div/label/input[@value='#']";
	
	String attrib_value="value";
		
	// UI Actions
	@Step("verify navigation into Game changer profile page")
	public boolean verifyNavigationToProfilePage() {
		//method to click on Profile link and check if user lands to profile page.
		logger.info("Navigating to Game Changer Profile Page");
		boolean a=false;
		this.verifyPageIsLoaded();
		if(this.getPageTitle().equals(GcnDataConstants.actGcProfilePageTitle) && 
				this.isElementVisible(linkAccLabel, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			logger.info("Landed to Profile Page");
			a=true;
		}else {
			logger.info("Error in Navigating to Profile Page.");
		}

		return a;
	}
	
	@Step("Verify Logged UserName")
	public boolean verifyLoggedInUserName(String uname) {
		boolean a=false;
		if(this.isElementVisible(profileLabel, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				uname.equals(userName.getText())){
			a=true;
		}
		return a;
	}
	
	public GcnGcPaymentSettingsPage clickOnPaymentSettingsLink() {
		logger.info("Click on Payment Settings Link");
		this.click(paymentSettingLink);
		return new GcnGcPaymentSettingsPage(driver);
	}
	
	@Step("Verify User uploaded Profile Picture OR default Profile Pic is Displayed")
	public boolean verifyProfilePictureSet(){
		boolean a=false;
		if(this.isElementVisible(defaultProfilePic, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			a=true;
			logger.info("Default Profile Picture is Set");
		}else if(this.isElementVisible(userProfilePic, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			a=true;
			logger.info("User uploaded Profile Picture is Set");
		}
		return a;
	}
	
	@Step("verify the updation of profile pic of gamechanger")
	public boolean uploadAndSaveProfilePic() throws AWTException, InterruptedException {
		String path=GcnDataConstants.PROJECT_ROOT_LOCATION+GcnDataConstants.GCN_TEST_PPICT_IMAGES;
		this.click(changeProfilePictureLink);
		String fName=uploadFile(path);
		this.waitForElementToBeVisible(saveProfilePicture, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.click(saveProfilePicture);
		this.waitForElementToBeVisible(changeProfilePictureLink, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.driver.navigate().refresh();
		/*String f=getSourceOfImage(profilePicture);
		return (f.contains(fName));*/
		return true;
	}

	@Step("Verify Profile MyDetails Updation")
	public boolean verifyMyDetailsUpdation(String fname,String lname,String dob,String email) {
		boolean a=false;
		this.waitForElementToBeInVisible(myDetailSavedMsg, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.click(myDetailsEditBtn);
		this.sendKeys(firstNameEdit, fname);
		this.sendKeys(lastNameEdit, lname);
		this.sendKeys(dobEdit, dob);
		this.sendKeys(contactEmailEdit, email);
		this.windowScrollUp();
		this.click(myDetailsSaveBtn);
		boolean isSaved=this.isElementVisible(myDetailSavedMsg, GcnDataConstants.IMPLICIT_TIMEOUT);
		
		if(isSaved && firstName.getText().equals(fname)) {
			a=true;
		}
		
		return a;
	}
	
	@Step("Verify UserName is Non Editable")
	public boolean verifyUserNameEditable() {
		boolean a=true;
		this.waitForElementToBeInVisible(myDetailSavedMsg, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.click(myDetailsEditBtn);
		try {
			this.sendKeys(userName, "hello");
			a=true;
		} catch(InvalidElementStateException e) {
			logger.info("First Name field is Not Editable");
			a=false;
		}
		return a;
	}
	
	@Step("verify the UI elements in Profile Page")
	public boolean verifyProfilePageUi() {
		boolean a=false;
		
		this.waitForElementToBeVisible(profileLabel, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(linkAccLabel, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(youtubeLogo, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(twitchLogo, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(profilePicture, GcnDataConstants.IMPLICIT_TIMEOUT);
		
		try {
			if(this.isElementVisible(profileLabel, GcnDataConstants.IMPLICIT_TIMEOUT) &&
					this.isElementVisible(linkAccLabel, GcnDataConstants.IMPLICIT_TIMEOUT) &&
					this.isElementVisible(youtubeLogo, GcnDataConstants.IMPLICIT_TIMEOUT) &&
					this.isElementVisible(twitchLogo, GcnDataConstants.IMPLICIT_TIMEOUT) &&
					this.isElementVisible(profilePicture, GcnDataConstants.IMPLICIT_TIMEOUT) ) {
				a=true;
			}
		}catch(ElementNotFoundException e) {
			logger.info("Error finding Element :"+e.getMessage());
		}
		return a;
	}
	
	@Step("Verify if First Name and Last Name are Set and Displayed")
	public boolean isNameDisplayed() {
		logger.info("Check for Name Displayed");
		 this.isElementVisible(firstName, GcnDataConstants.IMPLICIT_TIMEOUT);
		 this.isElementVisible(lastName, GcnDataConstants.IMPLICIT_TIMEOUT);
		 return (firstName.getText() != null && lastName.getText() !=null);
	}
	
	@Step("Verify Game Preferences Updation")
	public boolean verifyGamePrefUpdation(String pG,String sG,String pC,String sC) {
		/*List<WebElement> secGenre=this.driver.findElements(By.xpath(secondaryGenreXpath));
		List<WebElement> secConsole=this.driver.findElements(By.xpath(secondaryConsoleXpath));*/
		List<WebElement> secConsoleChecked=this.driver.findElements(By.xpath(secondaryConsoleCheckedXpath));
		boolean a=false;
		this.waitForElementToBeInVisible(gamePrefSaveMsg, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.click(gamePreferenceEditBtn);
		
		this.moveToElement(secondaryConsoleLabel);
		this.windowScrollDwn();
		
		//Clear Secondary Console selection
		for(int i=0;i<secConsoleChecked.size();i++) {
			this.click(secConsoleChecked.get(i));
		}
		
		this.findDynamicElement(secondaryConsoleXpathD, sC).click();
		
		Select sel=new Select(primaryGenreSelect);
		sel.selectByVisibleText(pG);
		
		sel=new Select(primaryConsoleSelect);
		sel.selectByVisibleText(pC);
		
		this.moveToElement(gamePreferenceSaveBtn);
		this.windowScrollUp();

		this.click(gamePreferenceSaveBtn);
		
		this.waitForElementToBeVisible(gamePrefSaveMsg, GcnDataConstants.IMPLICIT_TIMEOUT);
		boolean isSaved=this.isElementVisible(gamePrefSaveMsg, GcnDataConstants.IMPLICIT_TIMEOUT);
		if(isSaved && gamePreferenceLabel.isDisplayed()) {
			a=true;
		}
		return a;
	}
	
	@Step("Verify Misc Data Updation")
	public boolean verifyMiscDataUpdation(String contL,String commL,String tSize) throws InterruptedException {
		boolean a=false;
		int cNum=getRandNum(6);
		
		this.moveToElement(tShirtSize);
				
		this.waitForElementToBeInVisible(miscSavedMsg, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.click(miscEditBtn);
		Select sel=new Select(contLangSel);
		sel.selectByVisibleText(contL);

		sel=new Select(commLangSel);
		sel.selectByVisibleText(commL);
		
		sel=new Select(tSizeSel);
		sel.selectByVisibleText(tSize);
		
		this.sendKeys(certNumEdit, String.valueOf(cNum));
		
		this.click(miscSaveBtn);
				
		boolean isSaved=this.isElementVisible(miscSavedMsg, GcnDataConstants.IMPLICIT_TIMEOUT);
		Thread.sleep(6000);
		//this.waitForElementToBeInVisible(miscSavedMsg, GcnDataConstants.IMPLICIT_TIMEOUT);
		if(isSaved && certNum.getText().equals(String.valueOf(cNum))) {
			a=true;
		}
		
		return a;
	}
	
	@Step("Verify display of Social Channel icons")
	public boolean verifySocialChannelIcons() {
		boolean a=false;
		if(linkAccLabel.isDisplayed() && youtubeLogo.isDisplayed()
				&& twitchLogo.isDisplayed() && mixerLogo.isDisplayed()
				&& youtubeAddBtn.isDisplayed() && twitchAddBtn.isDisplayed()
				&& mixerAddBtn.isDisplayed()) {
			a=true;
		}
		
		return a;
	}
	
	
	public String getFullName() {
		String fullName="";
		if(isNameDisplayed()) {
			fullName = firstName.getText()+""+lastName.getText();
			logger.info("Get the set Name - First and Last Name : "+fullName);	
		}
		
		return fullName;
	}
	
	
}
