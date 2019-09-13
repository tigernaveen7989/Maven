package com.shell.markethub.ecommerce.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * 
 * @author N.Kumar8@shell.com
 * @description page object for configure your order page
 *
 */
public class ConfigureYourOrderPage extends eCommerceBasePageObject{

	public ConfigureYourOrderPage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(ConfigureYourOrderPage.class);
	@FindBy(xpath = "//h2[@class='shell-title has-font-size-20pix-mobile']")
	WebElement configureYourOrderTitle;
	@FindBy(xpath = "//div[@class='columns']//div[@class='box is-gray-color shell-font-futura boxBorderGreen']")
	WebElement account;
	@FindBy(xpath = "//h3[contains(@class,'subtitle is-size-18pix has-text-dark-grey has-margin-btm-1rem is-size-5-mobile')]//b[contains(text(),'Account')]")
	WebElement accountText;
	@FindBy(xpath = "//h3[@class='subtitle is-size-18pix has-text-dark-grey has-margin-btm-1rem is-size-5-mobile']")
	WebElement businessCategoryText;
	@FindBy(xpath = "//div[@class='content singlecategory']//div[@class='box is-gray-color shell-font-futura boxBorderGreen']")
	WebElement businessCategory;
	@FindBy(xpath = "//h3[@class='subtitle is-size-18pix has-text-dark-grey is-size-5-mobile has-margin-btm-1rem']")
	WebElement deliveryLocationText;
	@FindBy(xpath = "//body[contains(@class,'page-orderconfigure pageType-ContentPage template-pages-layout-layoutFullWidth pageLabel-Order-Configure language-en_GB')]/div[@id='pageWrapper']/div[contains(@class,'layout1BodySlot')]/div[contains(@class,'bulma-page-container order-configure-screen')]/div[contains(@class,'container-react')]/div[contains(@class,'section section-main-content')]/div[contains(@class,'container')]/div[contains(@class,'columns screen-content')]/div[contains(@class,'column is-two-thirds-tablet')]/div[contains(@class,'orderCreationModule')]/section[@id='delivery-Location']/div[contains(@class,'is-primary')]/div[contains(@class,'box has-text-black has-blue-hover MultiDeliveryLocation')]/div[contains(@class,'columns')]/div[1]")
	WebElement deliveryLocation;
	@FindBy(xpath = "//input[contains(@id,'accountlocX')]")
	WebElement deliveryLocationCheckbox;
	@FindBy(xpath = "//input[contains(@id,'accountlocX')]/following::label")
	WebElement deliveryLocationValue;
	@FindBy(xpath = "//input[contains(@placeholder,'Search by location number, company name or address.')]")
	WebElement deliveryLocationSearchBar;
	@FindBy(xpath = "//textarea[@id='textDelivery']")
	WebElement deliveryInstructionsEditBox;
	@FindBy(xpath = "//textarea[@id='textDriver']")
	WebElement driverInstructionsEditBox;
	@FindBy(xpath = "//div[contains(@class,'stick')]//button[contains(@class,'shell-font-Futura')][contains(text(),'Continue')]")
	WebElement continueButton;
	
	
	public String getPageTitle() {
		return getText(configureYourOrderTitle);
	}
	
	public void enterDeliveryLocation(String deliveryLocation) throws Exception{
		click(deliveryLocationSearchBar);
		sendKeys(deliveryLocationSearchBar, deliveryLocation);
	}
	
	public void enterDriverInstructions(String driverInstructions) throws Exception{
		sendKeys(driverInstructionsEditBox, driverInstructions);
	}
	
	public void enterDeliveryInstructions(String deliveryInstructions) throws Exception{
		sendKeys(deliveryInstructionsEditBox, deliveryInstructions);
	}
	
	public void clickOnContinueButton() throws Exception{
		click(continueButton);
	}
	
	public void clickOnDeliveryLocationCheckbox() throws Exception{
		click(deliveryLocationCheckbox);
	}
}

