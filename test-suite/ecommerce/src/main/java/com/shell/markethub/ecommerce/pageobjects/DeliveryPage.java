package com.shell.markethub.ecommerce.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeliveryPage extends eCommerceBasePageObject{
	public DeliveryPage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	private static final Logger logger = Logger.getLogger(DeliveryPage.class);
	@FindBy(xpath = "//h2[text()='Delivery']")
	WebElement deliveryTitle;
	@FindBy(xpath = "//div[contains(@class,'stick')]//button[contains(@class,'shell-font-Futura')][contains(text(),'Continue')]")
	WebElement continueButton;
	By title = By.xpath("//h2[@class='shell-title has-font-size-20pix-mobile']");
	
	public String getPageTitle() {
		return getText(deliveryTitle);
	}
	
	public Boolean verifyPageTitle() {
		return isElementPresent(title, 10);
	}
	
	public void clickOnContinueButton() throws Exception {
		click(continueButton);
		Thread.sleep(2000);
	}
}
