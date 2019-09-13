package com.shell.markethub.uam.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SetAccessAndTargetingAttributesPage extends UAMBasePageObject{

	public SetAccessAndTargetingAttributesPage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(SetAccessAndTargetingAttributesPage.class);
	@FindBy(xpath = "//a[@class='repeatableControl addLink addAccess']")
	WebElement addSiteAccessSetAccessLink;
	@FindBy(xpath = "//select[contains(@id,'userSite')]")
	WebElement siteSetAccessDropdown;
	@FindBy(xpath = "//select[contains(@id,'Language')]")
	WebElement languageSetAccessDropdown;
	@FindBy(xpath = "//select[@id='userExperience']")
	WebElement experienceSetAccessDropdown;
	@FindBy(xpath = "//select[@id='usermarket']")
	WebElement marketingSetAccessDropdown;
	@FindBy(xpath = "//span[@id='ddcl-marketTarget']//span[@class='ui-dropdownchecklist-text']")
	WebElement marketingPreferenceSetAccessDropdown;
	@FindBy(xpath = "//input[@id='ddcl-marketTarget-i0']")
	WebElement marketingPreferenceSetAccessDropdownValue1;
	@FindBy(xpath = "//input[@id='ddcl-marketTarget-i1']")
	WebElement marketingPreferenceSetAccessDropdownValue2;
	@FindBy(xpath = "//input[@id='ddcl-marketTarget-i2']")
	WebElement marketingPreferenceSetAccessDropdownValue3;
	@FindBy(xpath = "//span[@id='ddcl-brandTarget']//span[@class='ui-dropdownchecklist-text']")
	WebElement brandPreferenceSetAccessDropdown;
	@FindBy(xpath = "//input[@id='ddcl-brandTarget-i0']")
	WebElement brandPreferenceSetAccessDropdownValue1;
	@FindBy(xpath = "//span[@id='ddcl-cobTarget']//span[@class='ui-dropdownchecklist-selector']")
	WebElement classOfBusinessSetAccessDropdown;
	@FindBy(xpath = "//input[@id='ddcl-cobTarget-i0']")
	WebElement classOfBusinessSetAccessDropdownValue1;
	@FindBy(xpath = "//button[@class='primary saveButton']")
	WebElement saveSetAccessButton;
	@FindBy(xpath = "//input[@id='makedefault']")
	WebElement defaultSetAccessRadioButton;
	@FindBy(xpath = "//input[@optionname='Normal User']")
	WebElement normalUserSetAccessRadioButton;
	@FindBy(xpath = "//input[@optionname='Super User']")
	WebElement superlUserSetAccessRadioButton;
	@FindBy(xpath = "//input[@optionname='Customer Admin']")
	WebElement customerAdminSetAccessRadioButton;
	@FindBy(xpath = "//button[contains(@id,'UserRolesNext')]")
	WebElement nextSetAccessButton;
	
	public void clickOnAddSiteAccessSetAccessLink() throws Exception {
		click(addSiteAccessSetAccessLink);
	}
	
	public void selectSiteSetAccessDropdown(String site) throws Exception {
		selectVisibleText(siteSetAccessDropdown, site);
		Thread.sleep(2000);
	}
	
	public void selectLanguageSetAccessDropdown(String language) throws Exception {
		selectVisibleText(languageSetAccessDropdown, language);
		Thread.sleep(2000);
	}
	
	public void selectExperienceSetAccessDropdown(String experience) throws Exception {
		selectVisibleText(experienceSetAccessDropdown, experience);
		Thread.sleep(2000);
	}
	
	public void selectMarketingSetAccessDropdown(String marketing) throws Exception {
		selectVisibleText(marketingSetAccessDropdown, marketing);
		Thread.sleep(2000);
	}
	
	public void selectMarketingPreferenceSetAccessDropdown() throws Exception {
		click(marketingPreferenceSetAccessDropdown);
		click(marketingPreferenceSetAccessDropdownValue1);
	}
	
	public void selectBrandPreferenceSetAccessDropdown() throws Exception {
		click(brandPreferenceSetAccessDropdown);
		click(brandPreferenceSetAccessDropdownValue1);
	}
	
	public void selectClassOfBusinessSetAccessDropdown() throws Exception {
		click(classOfBusinessSetAccessDropdown);
		click(classOfBusinessSetAccessDropdownValue1);
	}
	
	public void clickOnSaveSetAccessButton() throws Exception {
		click(saveSetAccessButton);
	}
	
	public void clickOnNormalUserSetAccessRadioButton() throws Exception {
		click(normalUserSetAccessRadioButton);
	}
	
	public void clickOnSuperUserSetAccessRadioButton() throws Exception {
		Thread.sleep(5000);
		click(superlUserSetAccessRadioButton);
	}
	
	public void clickOnCustomerAdminSetAccessRadioButton() throws Exception {
		click(customerAdminSetAccessRadioButton);
	}
	
	public void clickOnNextSetAccessButton() throws Exception {
		click(nextSetAccessButton);
		Thread.sleep(5000);
	}
	
	public void clickOnDefaultSetAccessRadioButton() throws Exception {
		click(defaultSetAccessRadioButton);
		Thread.sleep(2000);
	}
}
