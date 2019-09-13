package com.shell.markethub.ecommerce.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * 
 * @author N.Kumar8
 *
 */
public class AddToYourOrderPage extends eCommerceBasePageObject{

	public AddToYourOrderPage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	private static final Logger logger = Logger.getLogger(AddToYourOrderPage.class);
	@FindBy(xpath = "//span[@class='heading marginT']")
	WebElement oldAddToYourOrderTitle;
	@FindBy(xpath = "//input[@class='productCatalogueButton accordianClick']")
	WebElement oldProductCatalogueButton;
	@FindBy(xpath = "//input[@id='searchBox']")
	WebElement oldProductsEditbox;
	@FindBy(xpath = "(//input[@class='qtyManageListTable'])[1]")
	WebElement oldQuantityEditbox;
	@FindBy(xpath = "//input[@class='addToOrderButton accordianClick']")
	WebElement oldContinueButton;
	@FindBy(xpath = "//div[@class='clearfix']//input[@id='normalContinue']")
	WebElement oldContinueButton1;
	@FindBy(xpath = "//span[@class='icon-search']")
	WebElement oldSearchIcon;
	
	public String getOldPageTitle() {
		return getText(oldAddToYourOrderTitle);
	}
	
	public void oldEnterProduct(String productName) throws Exception{
		sendKeys(oldProductsEditbox, productName);
	}
	
	public void oldClickOnSearchIcon() throws Exception{
		click(oldSearchIcon);
	}
	
	public void oldEnterQuantity(String quantity) throws Exception{
		sendKeys(oldQuantityEditbox, quantity);
	}
	
	public void oldClickOnContinueButton() throws Exception{
		click(oldContinueButton);
	}
	
	public void oldClickOnContinueButton1() throws Exception{
		click(oldContinueButton1);
	}
}
