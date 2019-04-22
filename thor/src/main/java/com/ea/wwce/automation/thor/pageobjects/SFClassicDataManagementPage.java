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
 * @description Classic Sales Force Home Page object
 */

public class SFClassicDataManagementPage extends ThorBasePageObject{
	
	public SFClassicDataManagementPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver,this);
	}
	
    private static final Logger logger = Logger.getLogger(SFClassicDataManagementPage.class);
   
   @FindBy(xpath ="//a[contains(text(),'Data Management')]")
   WebElement txtDataManagement;
   
   @FindBy(xpath ="//input[@value='Tos Penalty Disciplinary Configuration']")
   WebElement btnPenaltyDisciplinary;
   
 
    
        
  
	// Method to Click on SF object in DataManagement page
	@Step("Click on Data Management tab")
	public void clickOnDataManagementTab() {
		try {
			logger.info("Click on Data Management tab");
			this.waitForElementToBeVisible(txtDataManagement, 10);
			this.click(txtDataManagement);
			Thread.sleep(2000);
		}catch(NoSuchElementException e){
			logger.info("Failed to find the Data Management tab");
		}	
		catch (Exception e) {
			logger.info("Failed to click on Data Management tab  " + e.getMessage());
			}
	}
 	
	// Method to Click on SF object in DataManagement page
	@Step("Click on Tos PenaltyDisciplinary Configuration")
	public void clickOnPenaltyDisciplinaryConfiguration() {
		try {
			logger.info("Click on PenaltyDisciplinary Configuration button");
			this.waitForElementToBeVisible(btnPenaltyDisciplinary, 10);
			this.click(btnPenaltyDisciplinary);
			Thread.sleep(2000);
		}catch(NoSuchElementException e){
			logger.info("Failed to find the PenaltyDisciplinary Configuration button");
		}	
		catch (Exception e) {
			logger.info("Failed to click on PenaltyDisciplinary Configuration button  " + e.getMessage());
	 		}
	}
 	
}