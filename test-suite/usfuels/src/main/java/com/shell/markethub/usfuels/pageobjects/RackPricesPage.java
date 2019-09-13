package com.shell.markethub.usfuels.pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

/**
 * 
 * @author N.Kumar8@shell.com
 * @description Page object for RackPricesPage
 *
 */
public class RackPricesPage extends USFuelsBasePageObject{

	public RackPricesPage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(RackPricesPage.class);
	@FindBy(xpath = "(//h1[text()='Rack Prices'])[2]")
	WebElement title;
	@FindBy(xpath="(//button[@class='ms-choice'])[2]")
	WebElement terminalDropDown;
	@FindBy(xpath="(//input[@data-name='selectItemterminals'][@data-soldto-count='0'])[1]")
	WebElement terminalDropDownCheckBox1;
	@FindBy(xpath="//button[@class='primary submitButton onLoadResultButton']")
	WebElement searchButton;
	@FindBy(id="rackpricerowCount")
	WebElement rackPricesRowCount;
	@FindBy(xpath="//*[@id='testTable']/tbody/tr")
	List<WebElement> rackPricesTableRow;
	@FindBy(xpath="//*[@id='testTable']/thead/tr/th")
	List<WebElement> tableHead;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock soldToComp']//button[@class='ms-choice']")
	WebElement soldToDropdown;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock soldToComp']//li[2]//label[1]//input[1]")
	WebElement soldToDropDownValue1;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock dhTerminal']//button[@class='ms-choice']")
	WebElement terminalDropdown;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock dhTerminal']//li[2]//label[1]//input[1]")
	WebElement terminalDropdownValue1;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock dhCarrier']//button[@class='ms-choice']")
	WebElement productDropdown;
	@FindBy(xpath = "//input[@data-name='selectItemproducts'][@value='400007405'][@data-parent='{0,0}']")
	WebElement productDropdownValueSHMV2DS15PPMValue;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock fromDate']//span[@class='icon-calendar']")
	WebElement fromCalenderIcon;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock toDate']//span[@class='icon-calendar']")
	WebElement toCalenderIcon;
	@FindBy(xpath = "//button[@class='secondary downloadButton']")
	WebElement downloadButton;
	@FindBy(xpath = "//span[contains(text(),'Add / Remove columns')]")
	WebElement addRemoveColumnsButton;
	@FindBy(xpath = "//table[@id='testTable']")
	WebElement rackPricesTable;
	
	public void clickOnTerminalDropDown() throws Exception{
		click(terminalDropDown);
	}
	
	public String verifyTitle() throws Exception{
		return getText(title);
	}
	
	public String clickOnTermialDropDownCheckBox1() throws Exception{
		click(terminalDropDownCheckBox1);
		String checkBoxValue = getAttributeValue(terminalDropDownCheckBox1, "value");
		return checkBoxValue;
	}
	
	public void clickOnSearchButton() throws Exception{
		click(searchButton);
	}
	
	public void verifyRackPricesRowCount() throws Exception{
		String rackPrices = getText(rackPricesRowCount);
		if(rackPrices.contains("rows returned with user preferences")) {
			Assert.assertTrue(true);
			test.get().log(Status.PASS, "Rack Prices Row Count Assertion Passed");
		}else {
			Assert.assertTrue(false);
			test.get().log(Status.FAIL, "Rack Prices Row Count Assertion Failed");
		}
	}
	
	public void verifyTerminalColumnValues(String checkBoxValue) throws Exception{
		int tableColumnCount = tableHead.size();
		for(int i=0; i<tableColumnCount; i++) {
			if(tableHead.get(i).getText().contains("Terminal")) {
				for(int j=0; j<rackPricesTableRow.size()-1; j++) {
					if (!rackPricesTableRow.get(j).findElement(By.xpath("td[" + (i + 1) + "]")).getText()
							.contains(checkBoxValue)) {
						Assert.assertFalse(true);
					}	 
				}
				break;
			}
		}
	}
	
	public Boolean verifySoldToDropdown() throws Exception {
		return isDisplayed(soldToDropdown);
	}
	
	public Boolean verifyTerminalDropdown() throws Exception {
		return isDisplayed(terminalDropdown);
	}
	
	public Boolean verifyProductDropdown() throws Exception {
		return isDisplayed(productDropdown);
	}
	
	public Boolean verifyFromCalenderIcon() throws Exception {
		return isDisplayed(fromCalenderIcon);
	}
	
	public Boolean verifyToCalenderIcon() throws Exception {
		return isDisplayed(toCalenderIcon);
	}
	
	public Boolean verifyDownloadButton() throws Exception {
		return isDisplayed(downloadButton);
	}
	
	public Boolean verifyAddRemoveColumnsButton() throws Exception {
		return isDisplayed(addRemoveColumnsButton);
	}
		
	public Boolean verifySearchButton() throws Exception {
		return isDisplayed(searchButton);
	}
	
	public Boolean verifyRackPricesTable() throws Exception {
		return isDisplayed(rackPricesTable);
	}
}
