package com.shell.markethub.integration.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends IntegrationBasePageObject{

	public HomePage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(HomePage.class);
	@FindBy(xpath = "//span[@class='icon-home']")
	WebElement homeButton;
	@FindBy(xpath = "//span[@class='icon-user-empty']")
	WebElement myProfileIcon;
	@FindBy(xpath="//a[contains(@href,'myprofile')]")
	WebElement myProfileLink;
	@FindBy(className = "manageUsericon")
	WebElement manageUserIcon;
	@FindBy(xpath="//li[text()='You are impersonating: ']")
	WebElement youAreImpersonatingText;
	@FindBy(xpath="//li[@class='menu original']//b[contains(text(),'All')]")
	WebElement allTab;
	@FindBy(xpath="(//a[contains(@href,'rackprice')])[2]")
	WebElement rackPricesLink;
	@FindBy(xpath="(//a[contains(@href,'bol')])[2]")
	WebElement bolsLink;
	@FindBy(xpath="//div[@class='menuDropDown']//a[contains(text(),'Order Management')]")
	WebElement orderManagementLink;
	@FindBy(xpath = "//div[@class='menuDropDown']//a[contains(text(),'Carrier Driver Last Lift')]")
	WebElement carrierDriverLastLiftLink;
	@FindBy(xpath = "//div[@class='menuDropDown']//a[contains(text(),'Contract Prices')]")
	WebElement contractPricesLink;
	@FindBy(xpath = "//div[@class='menuDropDown']//a[contains(text(),'Terminal Wait Time')]")
	WebElement terminalWaitTimeLink;
	@FindBy(xpath = "//div[@class='menuDropDown']//a[contains(text(),'Allocations')]")
	WebElement allocationsLink;
	@FindBy(xpath = "//div[@class='menuDropDown']//a[contains(text(),'Invoiced Volumes')]")
	WebElement invoicedVolumsLink;
	@FindBy(xpath = "//div[@class='menuDropDown']//a[contains(text(),'*Supply Workspace')]")
	WebElement supplyWorkspaceLink;
	@FindBy(xpath = "//div[@class='menuDropDown']//a[contains(text(),'Where And What Can I Lift')]")
	WebElement whereAndWhatCanILiftLink;
	@FindBy(xpath = "//div[@class='menuDropDown']//a[contains(text(),'Site List')]")
	WebElement siteListLink;
	@FindBy(xpath = "//div[@class='menuDropDown']//a[contains(text(),'Volumes Entry')]")
	WebElement volumesEntryLink;
	@FindBy(xpath = "//div[@class='menuDropDown']//a[contains(text(),'Carrier Maintenance')]")
	WebElement carrierMaintenanceLink;
	@FindBy(xpath = "//div[@class='menuDropDown']//a[contains(text(),'Contract Recapture')]")
	WebElement contractRecaptureLink;
	
	public void clickOnHomeButton() throws Exception {
		click(homeButton);
	}
	
	public void clickOnMyProfileIcon() throws Exception {
		click(myProfileIcon);
	}
	
	public void clickOnMyProfileLink() throws Exception {
		click(myProfileLink);
	}
	
	public void clickOnManageProfileIcon() throws Exception {
		click(manageUserIcon);
	}
	
	public String verifyYouAreImpersonatingText() throws Exception {
		return getText(youAreImpersonatingText).trim();
	}

	public void clickOnAllTab() throws Exception {
		click(allTab);
	}
	
	public void clickOnRackPricesLink() throws Exception {
		click(rackPricesLink);
	}
	
	public void clickOnBOLsLink() throws Exception {
		click(bolsLink);
	}
	
	public void clickOnOrderManagementLink() throws Exception {
		click(orderManagementLink);
	}
	
	public void clickOnCarrierDriverLastLiftLink() throws Exception {
		click(carrierDriverLastLiftLink);
	}
	
	public void clickOnContractPricesLink() throws Exception {
		click(contractPricesLink);
	}
	
	public void clickOnTerminalWaitTimeLink() throws Exception {
		click(terminalWaitTimeLink);
	}
	
	public void clickOnAllocationsLink() throws Exception {
		click(allocationsLink);
	}
	
	public void clickOnInvoicedVolumsLink() throws Exception {
		click(invoicedVolumsLink);
	}
	
	public void clickOnSupplyWorkspaceLink() throws Exception {
		click(supplyWorkspaceLink);
	}
	
	public void clickOnWhereAndWhatCanILiftLink() throws Exception {
		click(whereAndWhatCanILiftLink);
	}
	
	public void clickOnSiteListLink() throws Exception {
		click(siteListLink);
	}
	
	public void clickOnVolumesEntryLink() throws Exception {
		click(volumesEntryLink);
	}
	
	public void clickOnCarrierMaintenanceLink() throws Exception {
		click(carrierMaintenanceLink);
	}
	
	public void clickOnContractRecaptureLink() throws Exception {
		click(contractRecaptureLink);
	}
}
