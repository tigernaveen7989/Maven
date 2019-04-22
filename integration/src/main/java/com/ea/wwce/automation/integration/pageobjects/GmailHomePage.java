package com.ea.wwce.automation.integration.pageobjects;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;

/**
 * @author Naveen Kumar
 * 
 * Page Object for GmailHomePage
 **/
public class GmailHomePage extends IntegrationPageObject{

	public GmailHomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	int count=0;
	String mailBodyContent=null;
	String[] str;
	String[] str1; 
	private static final Logger logger = Logger.getLogger(GmailLoginPage.class);
	
	@FindBy(xpath = "//div[@role='tabpanel']//table")
	WebElement emailList;
	@FindBy(xpath = "//table[@role='presentation']//table[2]//a")
	WebElement surveyLink;
	@FindBy(xpath = "//table[@border='0']//a[contains(@href,'urvey')]")
	WebElement inHouseSurveyLink;
	@FindBy(xpath = "//div[@aria-label='Select']/div")
	WebElement selectAllMailDropdown;
	@FindBy(xpath = "//div[@selector='all']")
	WebElement allMail;
	@FindBy(xpath = "//div[@title='Delete']")
	WebElement deleteIcon;
	@FindBy(xpath = "//table[@border='0']/tbody[1]//p[1]/span/span")
	WebElement mailBody;
	@FindBy(xpath = "//div[@title='Back to Inbox']/div/div")
	WebElement backButton;
	@FindBy(xpath = "//table[@border='0']/tbody[1]/tr[12]/td/table/tbody")
	WebElement mailBody1;
	
	String unReadEmail = "//span[@class='bog']/span[@class='bqe']";
	
	@Step("Load Gmail List")
	public void loadGmailList() {
		logger.info("Load Gmail List");
		try {
			this.waitForElementToBeVisible(emailList, 60);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//@Step("Get Un Read Email List")
	public void getUnReadMailList() {
		logger.info("Get Un Read Email List");
		List<String> listOfUnReadMails = new ArrayList<>();
		try {
			List<WebElement> emailList = this.getWebElementsFrmList(unReadEmail);
			for(WebElement list : emailList){
				listOfUnReadMails.add(list.getText());
			}
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Step("Click on Un Read Mail")
	public void clickOnUnReadMail(String unReadMail) {
		logger.info("Click on Un Read Mail");
		try {
			List<WebElement> emailList = this.getWebElementsFrmList(unReadEmail);
			for(WebElement list : emailList){
				if(list.getText().equals(unReadMail)){
					list.click();
					break;
				}
			}
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Step("Count the survey mails")
	public int countSurveyMails(String unReadMail) {
		logger.info("Count the survey mails");
		try {
			List<WebElement> emailList = this.getWebElementsFrmList(unReadEmail);
			for(WebElement list : emailList){
				if(list.getText().equals(unReadMail)){
					count = count +1;
				}
			}
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	//@Step("Click on Start the survey Link")
	public void clickOnSurveyLink() throws InterruptedException{
		logger.info("Click on Start the survey Link");
		try {
			this.waitForElementToBeVisible(surveyLink, 10);
			this.click(surveyLink);
			Thread.sleep(5000);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	
	//@Step("Click on In House survey Link")
	public void clickOnInHouseSurveyLink() throws InterruptedException{
		logger.info("Click on In House survey Link");
		try {
			this.waitForElementToBeVisible(inHouseSurveyLink, 10);
			this.click(inHouseSurveyLink);
			Thread.sleep(5000);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	
	//@Step("Delete All Emails in inbox")
	public void deleteAllMails() throws InterruptedException{
		logger.info("Delete All Emails in inbox");
		try {
			this.waitForElementToBeVisible(selectAllMailDropdown, 10);
			this.click(selectAllMailDropdown);
			Thread.sleep(2000);
			this.click(allMail);
			Thread.sleep(2000);
			this.click(deleteIcon);
			Thread.sleep(3000);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	
	//@Step("Read access key from mail")
	public String getAccessKey() {
		logger.info("Read access key from mail");
		try {
			mailBodyContent = this.getText(mailBody);
			str = mailBodyContent.split("question:");
			str1 = str[1].split(". A Game Advisor");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return str1[0].trim();
	}
	
	//@Step("Click on Back Button")
	public void clickOnBackButton() throws InterruptedException{
		logger.info("Click on Back Button");
		try {
			this.click(backButton);
			Thread.sleep(5000);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	
	//@Step("Read Mail Body")
	public void readMail(String str) {
		logger.info("Read Mail");
		try {
			mailBodyContent = this.getText(mailBody1);
			if(mailBodyContent.contains(str)){
				assertTrue(true, str+" Text is present in the mail");
			}else{
				assertTrue(false, str+" Text is not present in the mail");
			}
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
