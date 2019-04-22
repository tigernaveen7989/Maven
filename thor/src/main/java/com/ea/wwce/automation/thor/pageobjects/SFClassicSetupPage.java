/**
 * 
 * @author Mohan Kamsu
 * @Description Page object created for SF Setup page for handling queue and Advisor search
 */

package com.ea.wwce.automation.thor.pageobjects;

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
 * @description Classic Sales Force Setup Page object
 */

public class SFClassicSetupPage extends ThorBasePageObject{
	
	public SFClassicSetupPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver,this);
	}
	
    private static final Logger logger = Logger.getLogger(SFClassicSetupPage.class);
     @FindBy(xpath ="//input[@id='setupSearch']")
    WebElement txtSearch;
    String QueueLink_XPATH="//a[text()='#']";
    @FindBy(xpath ="//a[contains(@href,'showMoreList')]")
    WebElement lnkShowMore; 
    String AdvisorLink_XPATH="//a[text()='#']";
    String QueuePageTitle_XPATH="//h2[contains(text(),'#')]";
    @FindBy(xpath ="//span[@id='tailBreadcrumbNode']")
    WebElement advisorPageTitle;
    String CaseLink_XPATH="//a[text()='#']";
	
	
	
    
    //Search Queue object in Setup page
    public void searchObjectInSetup(String objectNameVal) {
    	try{
			logger.info("Searching for object "+objectNameVal);
			this.verifyPageIsLoaded();
	    	this.waitForElementToBeVisible(txtSearch, 10);
	    	this.click(txtSearch);
	    	this.sendKeys(txtSearch, objectNameVal);
	    	Thread.sleep(1000);
	    	txtSearch.sendKeys(Keys.RETURN);
    	}catch(Exception e){
			logger.info("Failed to Search the object value "+objectNameVal);
    	}
    }
    
    //Verify Existance of Queue Link in Setup page
    @Step("Verify existance of searched Queue Link in setup page ")
    public boolean existanceOfQueueLink(String QueueName)
    {	boolean flag=false;
    	try {
    		logger.info("Existance of Queue Link in setup page after search");
    		if(this.isDynamicElementPresent(QueueLink_XPATH, QueueName, 8))
    			flag=true;
    		else
    			flag=false;
    		
    		
    	}catch (Exception e) {
    		logger.info("Failed to verify queue name on setup page after search  " + e.getMessage());
      	}
      		return flag;
    }
    
    //Click on Searched Queue Link
    @Step("click on searched Queue Link")
    public void clickOnQueue(String QueueName)
    {	try {
    		logger.info("Click on Searched Queue Link");
    		if(this.existanceOfQueueLink(QueueName))
    			this.clickOnDynamicElement(QueueLink_XPATH, QueueName);
    		
    	}catch (Exception e) {
    		logger.info("Failed to click on Searched Queue in Setup page  " + e.getMessage());
      	}
      		
    }
    
    //Click on Advisor  Link
    @Step("click on Advisor Link")
    public void clickOnAdvisor(String advisorName, String QueueName)
    {	try {
    		logger.info("Click on Advisor  Link");
    		if(this.isDynamicElementPresent(QueuePageTitle_XPATH, QueueName, 8))
    			this.showAllEntriesInAdvisorTable();
    		if(this.isDynamicElementPresent(AdvisorLink_XPATH, advisorName, 8))
    			this.clickOnDynamicElement(AdvisorLink_XPATH, advisorName);
    		
    	}catch (Exception e) {
    		logger.info("Failed to click on Advisor in Setup page  " + e.getMessage());
      	}
      		
    }
 
    
    @Step("Expand Advisor Table")
    public void showAllEntriesInAdvisorTable() {
    	try {    		
    		logger.info("Get All the entries in Advisor Table ");
    		while (this.isElementVisible(lnkShowMore, 2))
    		{	this.click(lnkShowMore);
    			
    		}		
    						
    	}catch (Exception e) {
    		logger.info("Failed to Click on ShowMore link  " + e.getMessage());
    	}
    		
   }
      	
    //Verify Existance of case Link in Advisor page
    @Step("Verify existance of Case Link in advisor page ")
    public boolean existanceOfCaseNumLink(String caseNum)
    {	boolean flag=false;
    	try {
    		logger.info("Existance of case Num Link in advisor page");
    		this.isElementVisible(advisorPageTitle, 5);
    		if(this.isDynamicElementPresent(CaseLink_XPATH, caseNum, 8))
    			flag=true;
    		else
    			flag=false;
    		
    		
    	}catch (Exception e) {
    		logger.info("Failed to verify Case Num on advisor page" + e.getMessage());
      	}
      		return flag;
    } 

    
}