/**
 * 
 * @author Praveen
 * @Description Page object created for SF admin page for handling Throttle Configuration
 */

package com.ea.wwce.automation.thor.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
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
 * @author praveen
 * @description Sales Force Queue Management Page object
 */

public class SFQueueManagementPage extends ThorBasePageObject{
	
	public SFQueueManagementPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver,this);
	}
	
    private static final Logger logger = Logger.getLogger(SFQueueManagementPage.class);
       
    @FindBy(id ="dashboard-console-iframe")
    WebElement framedashboard;    
    @FindBy(xpath ="//input[contains(@name,'emailQueue')]")
    WebElement txtEmailQueue;
    @FindBy(xpath="(//span[@id='addAction'])[1]")
    WebElement addActionsymbol;    
    @FindBy(xpath="//div[contains(@class,'success inputDialog')]")
    WebElement inputAddDialogbox;
    @FindBy(xpath="//div[text()='Add Queue']")
    WebElement txtAddQueue;
    @FindBy(xpath="//input[contains(@class,'inputText')]")
    WebElement inputName;
    @FindBy(xpath="//input[contains(@name,'active')]")
    WebElement chkActive;
    @FindBy(xpath="//a[contains(@name,'Add')]")
    WebElement btnAdd;
    @FindBy(xpath="//div[@class='success inputDialog']")
    WebElement successmsg;
    @FindBy(xpath="//div[@id='emailQueue']//div[contains(@class,'plainGrid')]/table/tbody/tr/td[1]")  
    WebElement txtSearched;
    @FindBy(xpath ="//input[contains(@name,'jobRolesAvail')]")
    WebElement txtAvailableRoles;
    
    @FindBy(xpath="//div[@id='jobRolesAvail']//div[contains(@class,'plainGrid')]/table/tbody/tr/td[1]")  
    WebElement txtAvailableRole;
    @FindBy(xpath="//a[@class='btn moveRight']")
    WebElement btnRight;
    @FindBy(xpath="//a[contains(text(),'Save Data')]")
    WebElement btnSaveData;
    @FindBy(xpath="//div[contains(text(),'Mapping Saved')]")
    WebElement alertmsgSaved;
    @FindBy(xpath="//div[contains(text(),'Queue Added Successfully')]")
    WebElement alertmsgQueueAdded;
    @FindBy(xpath="//div[contains(text(),'No JobRole Mapping exist for selected queue')]")
    WebElement alertmsgSelectQueue;
    
    String strRole="//div[@id='jobRolesAvail']//div[contains(@class,'plainGrid')]/table/tbody/tr/td[text()='#']";
    String strQueue="//div[@id='emailQueue']//div[contains(@class,'plainGrid')]/table/tbody/tr/td[text()='#']";
  
  
  
    
  
    
    //Click on Action Symbol Link
    public void clickOnAddActionSymbolLnk() {
    	try{
			logger.info("Click on Add Action Symbol Link");
			this.click(addActionsymbol);
			this.waitForElementToBeVisible(inputAddDialogbox, 3);
    	}
    	catch(Exception e){
			logger.info("Failed to click on Add Action Symbol Link");
    	}
    }
    
    //Switch to Dashboard console frame
    public void switchToDashboard() {
    	try{
			logger.info("Switch to Dashboard frame");
			this.waitAndSwitchToIframe(10, framedashboard);
			
    	}
    	catch(Exception e){
			logger.info("Failed to switch to Dashboard frame");
    	}
    }
    
    //Verify SF Add Queue Page  Display 
  	public boolean isSFAddQueuePageDisplayed(){
  		boolean isVisible = false;
  		try{
  			logger.info("Verifying the SF Add Queue Page display");
  			boolean flag=this.isElementVisible(inputAddDialogbox, 20);
  			if(flag)
  			{	String strText=this.getText(txtAddQueue);
  				isVisible = true;
  				logger.info("SF Add Queue Page Title was "+strText );
  			}
  			else
  			{	isVisible = false;			}
  			
  		}catch(Exception e){
  			logger.info("Failed to verify SF Add Queue Page display");
  			isVisible=false;
  		}
  		return isVisible;		
  	}
    
  	
    //Verify Add new Queue in to Queue
  	public String saveAddQueue(){
  		String gettext = "";
  		try{
			logger.info("Verifying Save QueueName");
			//Select name from Add Queue
			 this.isElementVisible(inputName, 2);
			 String QueueName=this.generateRandomString();   			
			this.sendKeys(inputName, QueueName);
			Thread.sleep(1000);
			gettext=this.getAttributeValue(inputName, "value");
			//System.out.println(gettext);
			this.click(chkActive);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnAdd);
			this.isElementVisible(alertmsgQueueAdded, 10);
			//this.waitForElementToBeVisible(alertmsgQueueAdded, 10);
			this.waitForElementToBeInVisible(alertmsgQueueAdded, 30);
			  						
  		   }catch(Exception e){
  			logger.info("Failed to Save Queue Name");
  			
  		   }
  		return gettext;
  	}
  
  
    //get searched String 
    public boolean getSearchedText(String addedTxt ) {    	
	 boolean flag=false;
	    try{   		   
		   logger.info("searching Queue String");
		   Thread.sleep(20);
		   if(this.isDynamicElementPresent(strQueue, addedTxt, 10)) {		   
			   this.clickOnDynamicElement(strQueue, addedTxt);
			   Thread.sleep(10);
			   this.waitForElementToBeInVisible(alertmsgSelectQueue, 20);
			   //this.clickUsingJavaScriptExecutor(txtSearched, 5);
		   	   //this.waitForElementToBeVisible(alertmsgSelectQueue, 10);
			   //this.isElementVisible(alertmsgSelectQueue, 10);
			   Thread.sleep(30);		   
			   flag=true;
		   }
		   
		}
	    catch(Exception e) {
	    	logger.info("Failed to get searched string  " + e.getMessage());
	    }
	    return flag;
    }
  
    //verify Available queue added.
    public boolean addAvailableRole(String role) { 
	   boolean isVisible = false;
	     try{   
	    	
	    	
		   logger.info("select the  Available Role");
		   this.sendKeys(txtAvailableRoles, role);
		   Thread.sleep(10); 
		    
		    if(this.isDynamicElementPresent(strRole, role, 20)) {
		    this.clickOnDynamicElement(strRole, role);
	    	Thread.sleep(5);
	    	this.waitForElementToBeInVisible(alertmsgSelectQueue, 20);
	    	this.clickUsingJavaScriptExecutor(txtAvailableRole, 10);
	    	Thread.sleep(10);
	    	this.waitForElementToBeInVisible(alertmsgSelectQueue, 20);
	    	this.click(btnRight);
	    	Thread.sleep(5);
	    	this.click(btnSaveData);
	    	this.isElementVisible(alertmsgSaved, 20);
	    	isVisible=true;
	    
		    }
		    else 
		       isVisible=false;
		 }
	     catch(Exception e) {
	    	logger.info("Failed to save Selected Roles  " + e.getMessage());
	    	
	     }
	     return isVisible;
    }

}
