package com.shell.markethub.ecommerce.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPlacedPage extends eCommerceBasePageObject{

	public OrderPlacedPage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	private static final Logger logger = Logger.getLogger(OrderPlacedPage.class);
	@FindBy(xpath = "//h2[text()='Order placed']")
	WebElement orderPlacedTitle;
	@FindBy(xpath = "//p[@class='orderplaced-msg stick-article shell-font-Futura is-size-0-9 has-margin-btm-20']")
	WebElement orderHasBeenPlacedText;
	@FindBy(xpath = "//div[contains(@class,'columns has-no-margin-btm has-padding-top-12')]//div[1]")
	WebElement orderNumberText;
	@FindBy(xpath = "//div[contains(@class,'columns has-no-margin-btm has-padding-top-12')]//div[2]")
	WebElement orderStatusText;
	@FindBy(xpath = "//b[contains(text(),'Edit this Order')]")
	WebElement editThisOrderLink;
	By youCanEditThisOrderText = By.xpath("//p[contains(text(),'You can edit this order until')]");
	
	public String getPageTitle() {
		return getText(orderPlacedTitle);
	}
	
	public String getOrderNumberText() throws Exception{
		return getText(orderNumberText);
	}
	
	public String getOrderHasBeenPlacedText() throws Exception{
		return getText(orderHasBeenPlacedText);
	}
	
	public String getOrderStatusText() throws Exception{
		return getText(orderStatusText);
	}
	
	public boolean verifyYouCanEditThisOrderText() throws Exception {
		return isElementPresent(youCanEditThisOrderText, 30);
	}
	
	public void clickOnEditThisOrderLink() throws Exception {
		click(editThisOrderLink);
	}
}
