package com.shell.markethub.ecommerce.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * 
 * @author N.Kumar8@shell.com
 * @description page object for select products page
 *
 */
public class SelectProductsPage extends eCommerceBasePageObject{

	public SelectProductsPage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	private static final Logger logger = Logger.getLogger(SelectProductsPage.class);
	@FindBy(xpath = "//h2[@class='shell-title has-font-size-20pix-mobile']")
	WebElement selectProductsTitle;
	@FindBy(xpath = "//div[@class='box js_package-box border-2x has-padding-mobile-07rem is-full-width has-pad-1 make-green']")
	WebElement bulkButton;
	@FindBy(xpath = "//a[contains(@class,'is-full-width-mobile')]")
	WebElement viewCatalogButton;
	@FindBy(xpath = "//h2[contains(text(),'Product Catalogue')]")
	WebElement productCatalogTitle;
	@FindBy(xpath = "//div[contains(@class,'modal-product-title-container')]//div[1]//div[1]//div[2]//div[1]//div[1]//div[1]//input[1]")
	WebElement orderEditBox;
	@FindBy(xpath = "//div[contains(@class,'modal-product-title-container')]//div[1]//div[1]//div[2]//div[1]//div[1]//div[2]//button[1]")
	WebElement addToOrderButton;
	@FindBy(xpath = "//div[contains(@class,'stick')]//button[contains(@class,'')][contains(text(),'Continue')]")
	WebElement continueButton;
	@FindBy(xpath = "//span[contains(text(),'Frequently ordered')]")
	WebElement frequentlyOrderedLink;
	@FindBy(xpath = "//h2[contains(text(),'Frequently ordered')]")
	WebElement frequentlyOrderedTitle;
	@FindBy(xpath = "//div[contains(@class,'modal modal-simplifiedordering modal-Frequently-Ordered is-active')]//i[contains(@aria-label,'close')]")
	WebElement frequentlyOrderedCloseButton;
	@FindBy(xpath = "//div[contains(@class,'')]//i[contains(@class,'has-font-size-0-9-rem flex-pull-right shell-icon-remove')]")
	WebElement productCatalogueCloseButton;
	@FindBy(xpath = "//div[@id='categories-item-2']//a[@class='is-size-7 has-text-black has-text-weight-bold'][contains(text(),'Automotive Gas Oil')]")
	WebElement automativeGasOilLink;
	
	public String getPageTitle() {
		return getText(selectProductsTitle);
	}
	
	public Boolean verifyProductCatalogFrame() throws Exception {
		return isDisplayed(productCatalogTitle);
	}
	
	public void clickOnViewCatalogButton() throws Exception {
		click(viewCatalogButton);
	}
	
	public void enterOrderQuantity(String orderQuantity) throws Exception {
		sendKeys(orderEditBox, orderQuantity);
	}
	
	public void clickOnAddToOrderButton() throws Exception {
		click(addToOrderButton);
	}
	
	public void clickOnContinueButton() throws Exception {
		click(continueButton);
	}
	
	public void clickOnFrequentlyOrderedLink() throws Exception {
		click(frequentlyOrderedLink);
		Thread.sleep(3000);
	}
	
	public void clickOnFrequentlyOrderedFrameCloseButton() throws Exception {
		click(frequentlyOrderedCloseButton);
	}
	
	public void clickOnBulkButton() throws Exception {
		click(bulkButton);
	}
	
	public void clickOnAutomotiveGasOilLink() throws Exception{
		click(automativeGasOilLink);
	}
	
	public void clickOnProductCatalogueFrameCloseButton() throws Exception {
		click(productCatalogueCloseButton);
	}
}
