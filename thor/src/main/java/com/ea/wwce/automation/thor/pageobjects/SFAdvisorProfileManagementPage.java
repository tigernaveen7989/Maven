/**
 * 
 * @author Mohan Kamsu
 * @Description Page object created for SF admin page for handling Throttle Configuration
 */

package com.ea.wwce.automation.thor.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.thor.config.ThorDataConstants;

import io.qameta.allure.Step;


import com.ea.wwce.automation.base.util.Driver;

/**
 * 
 * @author mohan
 * @description Sales Force Advisor Profile Management Page object
 */

public class SFAdvisorProfileManagementPage extends ThorBasePageObject{
	
	public SFAdvisorProfileManagementPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver,this);
	}
	
    private static final Logger logger = Logger.getLogger(SFAdvisorProfileManagementPage.class);
    @FindBy(xpath ="//span[text()='Active Advisor Profiles']/following::input[1]")
    WebElement txtSearchProfile;
    @FindBy(xpath ="//div[@id='activeUsers']//table//tr[1]")
    WebElement tblActiveAdvisorRows;
    @FindBy(xpath ="//div[@id='activeUsers']//span[@id='otherAction']")
    WebElement btnOtherActionMenu;
    @FindBy(xpath="//div[@id='activeUsers']//span[@id='otherAction']//li[@class='edit']")
    WebElement editMenuItem;
    @FindBy(xpath="//select[@id='selectedRoles']")
    WebElement selectSelectedRoles;
    @FindBy(xpath="//a[@class='btn reset']")
    WebElement btnCancel;
    @FindBy(xpath="//label[@id='advisorId']")
    WebElement advisorID;
   
    
    //Search Advisor Profile
    public String searchAdvisorProfile(String strAdvisorProfileName) {
    	String strText="";
    	try{
			logger.info("Search for Advisor Profile");
			this.isElementVisible(txtSearchProfile, 6);
	    	this.click(txtSearchProfile);
	    	this.sendKeys(txtSearchProfile, strAdvisorProfileName);
	    	Thread.sleep(5000);
	    	if (this.isElementVisible(tblActiveAdvisorRows, 7))
	    	{
	    		 strText=tblActiveAdvisorRows.getText();
	    		 this.click(tblActiveAdvisorRows);
	    	}
	    	else
	    		logger.info("Advisor Profile Not Found");
    	}catch(Exception e){
			logger.info("Failed to Search Advisor Profile");
    	}
    	return strText;    	
   }
    
    //Click on Edit menu item
    public void clickOnEditProfile() {
    	try{
			logger.info("Click on Edit Advisor Profile");
			this.waitForElementToBeVisible(advisorID, 6);
			//Click on EDit menu Item
			this.click(btnOtherActionMenu);
			this.click(editMenuItem);
    	}catch(Exception e){
			logger.info("Failed to click on Edit Advisor Profile");
    	}
    }
    
    //Get the Job roles from Selected Job roles List
	public List<String> getSelectedJobRolesFrmList() {
		List<String> list = new ArrayList<String>();
    	try{
			logger.info("Get all assigned Job roles from Advisor");
			this.waitForElementToBeVisible(selectSelectedRoles, 5);
			//get all the values from List
			list=this.getAllItemsFrmList(selectSelectedRoles);
			
    	}catch(Exception e){
			logger.info("Failed to get selected job roles of Advisor Profile");
    	}
    	return list;
	}
 
		
	//Click on Cancel button
	public void ClickOnCancelAdvisorEdit() {
		try {
			logger.info("Cancel Advisor Profile Edit");
			this.click(btnCancel);
			
		}
		catch(Exception e){
			logger.info("Failed to Cancel Advisor Profile Edit");
		}
		
	}
	
	
	
}
