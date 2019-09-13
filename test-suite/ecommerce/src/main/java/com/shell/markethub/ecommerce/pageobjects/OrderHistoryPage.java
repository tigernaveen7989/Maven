package com.shell.markethub.ecommerce.pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderHistoryPage extends eCommerceBasePageObject{

	public OrderHistoryPage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	private static final Logger logger = Logger.getLogger(OrderPlacedPage.class);
	@FindBy(xpath = "//h3[@class='heading']")
	WebElement orderHistoryTitle;
	@FindBy(xpath = "//table[@id='myTable']")
	WebElement orderHistoryTable;
	@FindBy(xpath = "//a[@class='buildOrderScreenLink'][contains(@href,'orderDetails')]")
	List<WebElement> orderNumber;
	@FindBy(xpath = "//input[@id='keywordsearchbox']")
	WebElement searchBox;
	String dynamicOrderNumber = "//a[contains(text(),'#')]";
	
	
	public String getPageTitle() {
		return getText(orderHistoryTitle);
	}
	
	public String clickOnFirstOrderNumber() throws Exception{
		String orderNo = orderNumber.get(1).getText();
		orderNumber.get(1).click();
		return orderNo;
	}
	
	public void clickOnOrderNumber(String orderNumber) throws Exception{
		WebElement orderNumberElement = findDynamicElement(dynamicOrderNumber,orderNumber);
		click(orderNumberElement);
	}
}
