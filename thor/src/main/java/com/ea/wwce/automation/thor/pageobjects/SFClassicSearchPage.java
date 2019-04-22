/**
 * 
 * @author Mohan Kamsu
 * @Description Page object created for SF admin page for handling Throttle Configuration
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
 * @description Classic Sales Force Search Page object
 */

public class SFClassicSearchPage extends ThorBasePageObject{
	
	public SFClassicSearchPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver,this);
	}
	
    private static final Logger logger = Logger.getLogger(SFClassicSearchPage.class);
    String lnkSearchtVal_XPATH="//a[contains(text(),'#')]";
    @FindBy(xpath ="//div/table[contains(@class,'list')]/tbody")
    WebElement searchTbl;
   
        
    //Click on Search object if it exists
    public boolean clickOnSearchdSFObject(String objectNameVal) {
    	boolean flag=false;
    	try{
			logger.info("Click on Searched object value "+objectNameVal);
			if (this.isElementVisible(searchTbl, 10))
			{
		    	this.clickOnDynamicElement(lnkSearchtVal_XPATH, objectNameVal);
		    	Thread.sleep(5000);
				flag=true;
			}
			else
			{logger.info("Failed to find searched object value table "+objectNameVal);
			flag=false;
			}
		}catch(Exception e){
			logger.info("Failed to click on searched value "+objectNameVal);
    	}
    	return flag;
    }
    
}