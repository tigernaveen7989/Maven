/**
 * 
 * @author Mohan Kamsu
 * @Description Page object created for gmail login
 */

package com.ea.wwce.automation.thor.pageobjects;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.thor.config.ThorDataConstants;


/**
 * 
 * @author mohan
 * @description Gmail Login object
 */

public class GooglePage extends ThorBasePageObject{
	
	public GooglePage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver,this);
	}
	
    private static final Logger logger = Logger.getLogger(GooglePage.class);
    @FindBy(xpath ="//input[@type='email']")
    WebElement txtGmailID;
    @FindBy(xpath ="//div[@id='identifierNext']")
    WebElement btnIdNext;
    @FindBy(xpath ="//input[@type='password']")
    WebElement txtGmailPwd;
    @FindBy(xpath="//div[@id='passwordNext']")
    WebElement btnPwdNext; 
    @FindBy(xpath="//button[@aria-label='Advanced search options']")
    WebElement btnAdvancedSearch;
    @FindBy(xpath="//label[text()='Has the words']/following::input[1]")
    WebElement txtWords;
    @FindBy(xpath="//div[@aria-label='Search Mail']")
    WebElement btnMaiSearch;
    @FindBy(xpath="//div[@class='ae4 UI']//table[@class='F cf zt']/tbody/tr[1]")
    WebElement searchedMailsTableFirstRow;
    String tblRow_XPATH="//div[@class='ae4 UI']//table[@class='F cf zt']/tbody/tr[%d]";
    @FindBy(xpath="//a[@href='https://accounts.google.com/SignOutOptions?hl=en&continue=https://mail.google.com/mail&service=mail']")
    WebElement lnkGoogleAcct;
    @FindBy(xpath="//a[text()='Sign out']")
    WebElement btnSignOut;
    @FindBy(xpath="//button[contains(text(),'Remove an account')]")
    WebElement lnkRemoveAccount;
    
    
    
    //Loginto gmail function
    public boolean LogInGmail(String MailId, String Password) {
    	boolean flag=false;
    	try{
			logger.info("Verify Gmail Login functionality");
			this.loadPage(ThorDataConstants.PLAYER_EMAIL_URL);
			this.waitForElementToBeVisible(txtGmailID, 5000);
			if (this.isElementVisible(txtGmailID, 3)) {
				this.click(txtGmailID);
				this.sendKeys(txtGmailID, MailId);
				this.click(btnIdNext);
			}
			if(this.isElementVisible(txtGmailPwd, 4)) {
				this.click(txtGmailPwd);
				this.sendKeys(txtGmailPwd, Password);
				this.click(btnPwdNext);
			}
			flag=this.isElementVisible(btnAdvancedSearch, 10);
			
    	}
    	catch(Exception e){
			logger.info("Failed to Login to Gmail");
    	}
    	return flag;
    }  
    
       
    //Advanced gmail Search by words
    public boolean gmailAdvancedSearchByWords(String strText) {
    	boolean blnFlag =false;
    	try{
			logger.info("Search Gmail by words");
			this.click(btnAdvancedSearch);
			Thread.sleep(1000);
			this.sendKeys(txtWords, strText);
			Thread.sleep(1000);
			//Click on search button
			this.click(btnMaiSearch);
			Thread.sleep(2000);
			blnFlag=this.isElementVisible(searchedMailsTableFirstRow, 3);
		}
    	catch(Exception e){
			logger.info("Failed to search gmail");
    	}
    	return blnFlag;
    }
    
    //Get row data from search table
    public String getRowDataByRowNo(int rowNo) {
    	String fullXpath="";
    	String strText="";
    	try{
			logger.info("Get the row data By table Row No");
			
			fullXpath=String.format(tblRow_XPATH,rowNo);
			strText=this.getText(driver.findElement(By.xpath(fullXpath)));
			//System.out.println(strText);
			
		}
    	catch(Exception e){
			logger.info("Failed to get row data from row "+rowNo);
    	}
    	//Click on Select button
    	
    	return strText;
    }
    
    //Logout from gmail function
    public boolean LogOutGmail() {
    	boolean flag=false;
    	try{
			logger.info("Verify Gmail Logout functionality");
			if (this.isElementVisible(lnkGoogleAcct, 3)) {
				this.click(lnkGoogleAcct);
				Thread.sleep(2000);
				this.click(btnSignOut);
			}
			flag=this.isElementVisible(lnkRemoveAccount, 10);
		}
    	catch(Exception e){
			logger.info("Failed to Logout from gmail");
    	}
    	return flag;
    }  
    
}
