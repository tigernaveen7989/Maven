package com.shell.markethub.ecommerce.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmationPage extends eCommerceBasePageObject{

	public OrderConfirmationPage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	private static final Logger logger = Logger.getLogger(OrderConfirmationPage.class);
	@FindBy(xpath = "//h3[@class='heading boldText']")
	WebElement oldOrderConfirmationTitle;
	@FindBy(xpath = "//div[@class='confirmMessageDiv largeFont']")
	WebElement oldYourOrderHasBeenSubmittedText;
	@FindBy(xpath = "//div[@class='orderDetailsDivInner1']")
	WebElement oldShellOrderNumberText;
	@FindBy(xpath = "//span[contains(@class,'orderStatusText-align lheight-50 orderSummaryGreyText')]")
	WebElement oldOrderStatusText;
	@FindBy(xpath = "//*[text()='Order Workspace']")
	WebElement oldOrderWorkspaceLink;
	@FindBy(xpath = "//span[@class='boldText']")
	WebElement oldOrderNumber;
	@FindBy(xpath = "//button[@aria-label='Exit']")
	WebElement closeSurveyPopupLink;
	By oldSummary = By.xpath("//div[@class='summaryMainDiv']");
	By surveyPopup = By.xpath("//*[text()='Please help us to improve the Shell MarketHub buying experience for you.']");
	
	public String getOldPageTitle() {
		return getText(oldOrderConfirmationTitle);
	}
	
	public String getOldShellOrderNumberText() throws Exception{
		return getText(oldShellOrderNumberText);
	}
	
	public String getOldYourOrderHasBeenSubmittedText() throws Exception{
		return getText(oldYourOrderHasBeenSubmittedText);
	}
	
	public String getOldOrderStatusText() throws Exception{
		return getText(oldOrderStatusText);
	}
	
	public Boolean getOldSummary() throws Exception {
		return isElementPresent(oldSummary, 10);
	}
	
	public void oldClickOnOrderWorkspaceLink() throws Exception{
		click(oldOrderWorkspaceLink);
	}
	
	public String getOldOrderNumber() throws Exception{
		return getText(oldOrderNumber);
	}
	
	public void closeSurveyPopup() throws Exception{
		click(closeSurveyPopupLink);
	}
	
	public Boolean verifySurveyPopup() throws Exception {
		return isElementPresent(surveyPopup, 10);
	}
}
