package com.shell.markethub.usfuels.pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BOLsPage extends USFuelsBasePageObject{

	public BOLsPage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(BOLsPage.class);
	@FindBy(xpath = "(//h1[text()='BOLs'])[2]")
	WebElement title;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock soldToComp']//button[@class='ms-choice']")
	WebElement soldToDropdown;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock soldToComp']//li[2]//label[1]//input[1]")
	WebElement soldToDropDownValue1;
	@FindBy(xpath="(//button[@class='ms-choice'])[2]")
	WebElement shipToDropdown;
	@FindBy(xpath="//input[@data-name='selectItemproducts']")
	WebElement shipToDropdownAllCheckBox;
	@FindBy(xpath="//button[@class='primary searchButton searchbolButton onLoadResultButton']")
	WebElement searchButton;
	@FindBy(xpath="//input[@placeholder='Enter text here']")
	WebElement bolEditBox;
	@FindBy(xpath="//span[@class='icon-calendar']")
	WebElement fromCalenderIcon;
	@FindBy(xpath="//div[@title='Previous month']")
	WebElement fromCalenderPrevMonthButton;
	@FindBy(xpath="//div[@class='picker__day picker__day--infocus']")
	WebElement fromCalenderDayButton;
	@FindBy(xpath = "//div[@class='formRow formRowInlineBlock bolToDate']//span[@class='icon-calendar']")
	WebElement toCalenderIcon;
	@FindBy(xpath="//label[@for='select-all-checkboxes-0']")
	WebElement allCheckBox;
	@FindBy(xpath="//div[@class='actionButtons']//button")
	WebElement downloadButton;
	@FindBy(id="radioDoc3")
	WebElement csvRadioButton;
	@FindBy(id="radioDoc4")
	WebElement xmlRadioButton;
	@FindBy(xpath="//section[@class='lightboxHeader']")
	WebElement downloadHeader;
	@FindBy(xpath="//button[@class='primary popup-custom-close downloadButton']")
	WebElement downloadButton1;
	@FindBy(xpath="//button[@class='Secondary popup-custom-close']")
	WebElement cancelButton;
	@FindBy(xpath="//*[@id='testTable']/tbody/tr")
	List<WebElement> bolTableRow;
	@FindBy(xpath="//*[@id='testTable']/thead/tr/th")
	List<WebElement> tableHead;
	@FindBy(xpath = "//span[contains(text(),'Add / Remove columns')]")
	WebElement addRemoveColumnsButton;
	@FindBy(xpath = "//table[@id='testTable']")
	WebElement bolsTable;
		
	public String verifyTitle() throws Exception{
		return getText(title);
	}
	
	public void verifyBOLNoColumnValues(String checkBoxValue) throws Exception{
		int tableColumnCount = tableHead.size();
		for(int i=0; i<tableColumnCount; i++) {
			if(tableHead.get(i).getText().contains("BOL#")) {
				for(int j=0; j<bolTableRow.size()-1; j++) {
					if (!bolTableRow.get(j).findElement(By.xpath("td[" + (i + 1) + "]")).getText()
							.contains(checkBoxValue)) {
						Assert.assertFalse(true);
					}	 
				}
				break;
			}
		}
	}
	
	public void clickOnShipToDropDown() throws Exception{
		click(shipToDropdown);
	}
	
	public void clickOnShipToAllDropDownCheckBox() throws Exception{
		click(shipToDropdownAllCheckBox);
	}
	
	public void clickOnFromCalenderIcon() throws Exception{
		click(fromCalenderIcon);
	}
	
	public void clickOnFromCalenderPrevMonthButton() throws Exception{
		click(fromCalenderPrevMonthButton);
	}
	
	public void clickOnFromCalenderDayButton() throws Exception{
		click(fromCalenderDayButton);
	}
	
	public void enterBOLNumber(String bolNumber) throws Exception{
		sendKeys(bolEditBox, bolNumber);
	}
	
	public void clickOnSearchButton() throws Exception{
		click(searchButton);
	}
	
	public void selectAllCheckBox() throws Exception{
		click(allCheckBox);
	}
	
	public void verifyDownloadHeader() throws Exception{
		isDisplayed(downloadHeader);
	}
	
	public void selectXMLRadioButton() throws Exception{
		click(xmlRadioButton);
	}
	
	public void selectCSVRadioButton() throws Exception{
		click(csvRadioButton);
	}
	
	public void clickOnDownloadButton() throws Exception{
		click(downloadButton);
	}
	
	public void clickOnDownloadButton1() throws Exception{
		click(downloadButton1);
	}
	
	public Boolean verifySoldToDropdown() throws Exception {
		return isDisplayed(soldToDropdown);
	}
		
	public Boolean verifyShipToDropdown() throws Exception {
		return isDisplayed(shipToDropdown);
	}
	
	public Boolean verifyFromCalenderIcon() throws Exception {
		return isDisplayed(fromCalenderIcon);
	}
	
	public Boolean verifyToCalenderIcon() throws Exception {
		return isDisplayed(toCalenderIcon);
	}
	
	public Boolean verifyBOLBumberEditbox() throws Exception {
		return isDisplayed(bolEditBox);
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
	
	public Boolean verifyBOLsTable() throws Exception {
		return isDisplayed(bolsTable);
	}
}
