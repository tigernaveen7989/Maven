package com.shell.markethub.usfuels.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TerminalWaitTimePage extends USFuelsBasePageObject{

	public TerminalWaitTimePage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(TerminalWaitTimePage.class);
	@FindBy(xpath = "//h1[contains(text(),'Terminal Wait Time')]")
	WebElement terminalWaitTimeTitle;
	@FindBy(xpath = "//select[@class='terminalname']")
	WebElement terminalDropdown;
	@FindBy(xpath = "//input[@class='datepicker picker__input']")
	WebElement dateIcon;
	@FindBy(xpath = "//div[@class='picker__month']")
	WebElement month;
	@FindBy(xpath = "//div[@class='picker__year']")
	WebElement year;
	@FindBy(xpath = "//input[@class='timeinput is-timeEntry']")
	WebElement timeEditbox;
	@FindBy(xpath = "//button[@class='primary searchButton']")
	WebElement searchButton;
	@FindBy(xpath = "//div[@class='terminalWaitingReportResultsDiv']")
	WebElement terminalWaitTimeReportGraph;
	@FindBy(xpath = "//div[@class='picker__nav--prev']")
	WebElement prevCalenderButton;
	String date = "//div[@class='picker__day picker__day--infocus'][contains(text(),'#')]";
	
	public String verifyTitle() throws Exception {
		return getText(terminalWaitTimeTitle);
	}
	
	public Boolean verifyTerminalDropdown() throws Exception {
		return isDisplayed(terminalDropdown);
	}
	
	public Boolean verifyDateIcon() throws Exception {
		return isDisplayed(dateIcon);
	}
	
	public Boolean verifyTimeEditbox() throws Exception {
		return isDisplayed(timeEditbox);
	}
	
	public Boolean verifySearchButton() throws Exception {
		return isDisplayed(searchButton);
	}
	
	public void selectTerminalDropdown(String terminal) {
		selectVisibleText(terminalDropdown, terminal);
	}
	
	public void clickOnDateIcon() throws Exception {
		click(dateIcon);
	}
	
	public void selectMonth(String monthValue) throws Exception {
		String monthCalender = getText(month);
		while(!monthCalender.equals(monthValue)) {
			click(prevCalenderButton);
			Thread.sleep(1000);
			monthCalender = getText(month);
		}
	}
	
	public void selectYear(String yearValue) throws Exception {
		String yearCalender = getText(year);
		while(!yearCalender.equals(yearValue)) {
			click(prevCalenderButton);
			Thread.sleep(1000);
			yearCalender = getText(year);
		}
	}
	
	public void selectDate(String dateValue) throws Exception {
		WebElement date1 = findDynamicElement(date, dateValue);
		click(date1);
	}
	
	public void enterTime(String time) throws Exception {
		sendKeys(timeEditbox, time);
	}
	
	public Boolean verifyTerminalWaitTimeGraph() throws Exception {
		return isDisplayed(terminalWaitTimeReportGraph);
	}
	
	public void clickOnSearchButton() throws Exception {
		click(searchButton);
	}
}
