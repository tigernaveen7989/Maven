package com.shell.markethub.ecommerce.pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderManagementWorkspacePage extends eCommerceBasePageObject{

	public OrderManagementWorkspacePage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(OrderManagementWorkspacePage.class);
	@FindBy(xpath = "//h1[contains(text(),'Order Management Workspace')]")
	WebElement orderManagementWorkspaceTitle;
	@FindBy(xpath = "//button[@class='primary buttonBlockDesktop buttonBlockMobile buttonMargin createOrder']")
	WebElement createNewOrderButton;
	@FindBy(xpath = "//form[@id='viewAll']//a[@class='tertiaryLink'][contains(text(),'View all orders')]")
	WebElement viewAllOrdersLink;
	@FindBy(xpath = "//div[@class='flex-viewport']//span[@class='orderTrackerOrderNumber']")
	List<WebElement> orderNumber;
	@FindBy(xpath = "//select[@id='specificOrderDropDown']")
	WebElement searchByDropdown;
	@FindBy(xpath = "//input[@id='specificOrderCheck']")
	WebElement searchByEditbox;
	@FindBy(xpath = "//button[@class='icon-alone specificOrderSearch']//span[contains(@class,'icon-search')]")
	WebElement searchBySearchIcon;
	
	public String getPageTitle() {
		return getText(orderManagementWorkspaceTitle);
	}
	
	public void clickOnCreateNewOrderButton() throws Exception{
		click(createNewOrderButton);
	}
	
	public void clickOnViewOrderDetailsLink() throws Exception{
		click(viewAllOrdersLink);
	}
	
	public void selectOrderNumberDropdown() throws Exception{
		selectVisibleText(searchByDropdown, "Order Number");
	}
	
	public void enterOrderNumber(String orderNumber) throws Exception{
		sendKeys(searchByEditbox, orderNumber);
	}
	
	public void clickOnSearchIcon() throws Exception{
		click(searchBySearchIcon);
	}
}
