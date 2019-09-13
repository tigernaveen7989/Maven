package com.shell.markethub.ecommerce.pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PlacedOrderDetailsPage extends eCommerceBasePageObject{
	public PlacedOrderDetailsPage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	private static final Logger logger = Logger.getLogger(PlacedOrderDetailsPage.class);
	@FindBy(xpath = "//h2[@class='title is-4 has-margin-btm-1rem has-font-size-20pix-mobile shell-font-Futura']")
	WebElement placedOrderDetailsTitle;
	@FindBy(xpath = "//div[@class='columns has-no-margin-btm has-padding-top-12 is-hidden-mobile']//div[@class='column']")
	WebElement orderNumberText;
	@FindBy(xpath  = "//div[@class='numberAndStatusDiv']//div")
	WebElement oldOrderNumberText;
	@FindBy(xpath = "//span[text()='Order status']")
	WebElement orderStatusText;
	@FindBy(xpath = "//span[contains(@class,'orderStatusText-align lheight-50 orderSummaryGreyText')]")
	WebElement oldOrderStatusText;
	@FindBy(xpath = "//button[text()='Clone this Order']")
	WebElement cloneThisOrderButton;
	@FindBy(xpath = "//div[@class='placedOrderDetailsHead']")
	WebElement oldPlacedOrderDetailsTitle;
	@FindBy(xpath = "//div[@class='nextStepsDivPlacedOrder']//input[2]")
	WebElement oldEditOrderButton;
	@FindBy(xpath = "//button[contains(text(),'Edit this Order')]")
	WebElement editThisOrderButton;
	By accountDetails = By.xpath("//div[@id='accounts']//div[@class='columns']");
	By summary = By.xpath("//div[@class='card basket has-padding-15']//div[@class='card-content']");
	By oldSummary = By.xpath("//div[@class='orderDetailsSummaryDivPlacedOrder clearfix']");
	
	
	public String getPageTitle() {
		return getText(placedOrderDetailsTitle);
	}
	
	public String getOldPageTitle() {
		return getText(oldPlacedOrderDetailsTitle);
	}

	public String getOrderNumber() throws Exception{
		return getText(orderNumberText);
	}
	
	public String getOrderStatus() throws Exception{
		return getText(orderStatusText);
	}
	
	public String getOldOrderNumber() throws Exception{
		return getText(oldOrderNumberText);
	}
	
	public String getOldOrderStatus() throws Exception{
		return getText(oldOrderStatusText);
	}
	
	public boolean getAccountDetails() throws Exception{
		return isElementPresent(accountDetails, 30);
	}
	
	public boolean getSummary() throws Exception{
		return isElementPresent(summary, 30);
	}
	
	public boolean getOldSummary() throws Exception{
		return isElementPresent(oldSummary, 30);
	}
	
	public void clickOnCloneThisOrderButton() throws Exception{
		click(cloneThisOrderButton);
	}
	
	public void oldClickOnEditOrderButton() throws Exception{
		click(oldEditOrderButton);
	}
	
	public void clickOnEditThisOrderButton() throws Exception{
		click(editThisOrderButton);
	}
}
