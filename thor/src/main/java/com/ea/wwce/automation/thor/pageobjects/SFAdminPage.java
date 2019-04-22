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
 * @description Sales Force Admin Page object
 */

public class SFAdminPage extends ThorBasePageObject{
	
	public SFAdminPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver,this);
	}
	
    private static final Logger logger = Logger.getLogger(SFAdminPage.class);
    @FindBy(xpath ="//h3[contains(text(),'Thor Admin Console Links')]")
    WebElement lnkThorAdmin;
    @FindBy(xpath ="//div[@class='dashboard']/h1")
    WebElement lblSFAdminPageTitle;
    @FindBy(id ="throttleconfiguration")
    WebElement lnkThrottleConfig;
    @FindBy(id ="dashboard-console-iframe")
    WebElement frameDashboard;
    @FindBy(xpath ="//div[@class='rightpaneWrapper']/section/h3")
    WebElement lblThrottleConfiguration;
    @FindBy(xpath ="//span[contains(@class,'sfStyle')]/select")
    WebElement lstProduct;
    @FindBy(xpath ="//span[contains(@id,'Platforms')]/span/button")
    WebElement btnPlatform;
    @FindAll(@FindBy(xpath ="//input[contains(@name,'PlatformsSelect')]"))
    List<WebElement> platformOptions;
    @FindBy(xpath ="//span[contains(@id,'Categories')]/span/button")
    WebElement btnCategory; 
    @FindAll(@FindBy(xpath ="//input[contains(@name,'CategoriesSelect')]"))
    List<WebElement> categoryOptions;
    @FindBy(xpath ="//label[text()='Min Petition Req Limit']/following::input[1]")
    WebElement minPetitionReqLmt;
    @FindBy(xpath ="//label[text()='Max Petition Req Limit']/following::input[1]")
    WebElement maxPetitionReqLmt;
    @FindBy(xpath ="//label[text()='Time']/following::input[1]")
    WebElement txtTime;
    @FindBy(xpath ="//input[contains(@value,'Save')]")
    WebElement btnSave;
    @FindBy(xpath ="//input[contains(@value,'Clear')]")
    WebElement btnClear;
    @FindBy(xpath ="//div[@class='messageText']//h4[contains(text(),'Success:')]")
    WebElement msgText;
    
    @FindBy(xpath ="(//h3[text()='Advisor Profile'])[1]")
    WebElement lnkMainAdvisorProfile;
    @FindBy(xpath ="//a[@id='advisorProfile']//h3[text()='Advisor Profile']")
    WebElement lnkSubAdvisorProfile;
    @FindBy(xpath="//h3[text()='Job Role']")
    WebElement lnkJobRole; 
    @FindBy(xpath="//h3[text()='Queues']")
    WebElement lnkQueues; 
        
    
    //Load SF admin Page and Verify
    public void loadSFAdminPage(String url) {
    	this.loadPage(url);
    	this.verifyPageIsLoaded();
    }
    
  //Click on Thor Admin Console Link
    public void clickOnThorAdminLnk() {
    	try{
			logger.info("Click on Thor Admin Console Link");
			this.click(lnkThorAdmin);
			this.waitForElementToBeVisible(lnkThrottleConfig, 3);
    	}
    	catch(Exception e){
			logger.info("Failed to click on Thor Admin Console Link");
    	}
    }
    
  //Click on Main Advisor Profile Link
    public void clickOnMainAdvisorProfileLnk() {
    	try{
			logger.info("Click on Main Advisor Profile Link");
			this.click(lnkMainAdvisorProfile);
			this.waitForElementToBeVisible(lnkSubAdvisorProfile, 3);
    	}
    	catch(Exception e){
			logger.info("Failed to click on Main Advisor Profile Link");
    	}
    }
    
  //Click on Sub Advisor Profile Link
    public void clickOnSubAdvisorProfileLnk() {
    	try{
			logger.info("Click on Sub Advisor Profile Link");
			this.click(lnkSubAdvisorProfile);
			Thread.sleep(3000);
    	}
    	catch(Exception e){
			logger.info("Failed to click on Main Advisor Profile Link");
    	}
    }
    
    //Click on Throttle Configuration Link
    public void clickOnThrottleConfig() {
    	try{
			logger.info("Click on Throttle Configuration Link");
			this.click(lnkThrottleConfig);
    	}
    	catch(Exception e){
			logger.info("Failed to click on Throttle Congiguration Link");
    	}
    }
    //Switch to Dashboard frame
    public void switchToDashboard() {
    	try{
			logger.info("Switch to Dashboard frame");
			this.waitAndSwitchToIframe(10, frameDashboard);
			
    	}
    	catch(Exception e){
			logger.info("Failed to switch to Dashboard frame");
    	}
    }
	
	//Verify SF Admin Page  Display 
	public boolean isSFAdminPageDisplayed(){
		boolean isVisible = false;
		try{
			logger.info("Verifying the SF Admin Page display");
			boolean flag=this.isElementVisible(lblSFAdminPageTitle, 20);
			if(flag)
			{	String strText=this.getText(lblSFAdminPageTitle);
				isVisible = true;
				logger.info("SF Admin Page Title was "+strText );
			}
			else
			{	isVisible = false;			}
			
		}catch(Exception e){
			logger.info("Failed to verify SF Admin Page display");
			isVisible=false;
		}
		return isVisible;		
	}
	
	//Verify Product List Display
	public boolean isProductDisplayed(){
		boolean isVisible = false;
		try{
			logger.info("Verifying the Product field display");
			//boolean flag=this.isElementPresent(lstProduct, 5);
			boolean flag=this.isElementVisible(lstProduct, 7);
			if(flag)
			{	isVisible = true;
				logger.info("Product field Displayed ");
			}
			else
			{	isVisible = false;			}
			
		}catch(Exception e){
			logger.info("Failed to verify Product Field display");
			isVisible=false;
		}
		return isVisible;		
	}
	
	//Verify display of Platform field
	public boolean isPlatformDisplayed(){
		boolean isVisible = false;
		try{
			logger.info("Verifying the Platform field display");
			boolean flag=this.isElementVisible(btnPlatform, 1);
			if(flag)
			{	isVisible = true;
				logger.info("Platform field Displayed ");
			}
			else
			{	isVisible = false;			}
			
		}catch(Exception e){
			logger.info("Failed to verify Platform Field display");
			isVisible=false;
		}
		return isVisible;		
	}
	
	//Verify display of Category 
	public boolean isCategoryDisplayed(){
		boolean isVisible = false;
		try{
			logger.info("Verifying the Category Field display");
			boolean flag=this.isElementVisible(btnCategory, 1);
			if(flag)
			{	isVisible = true;
				logger.info("Category Field Displayed");
			}
			else
			{	isVisible = false;			}
			
		}catch(Exception e){
			logger.info("Failed to verify Category Field display");
			isVisible=false;
		}
		return isVisible;		
	}
	
	//Verify display of minimum petition req Limit field
	public boolean isMinPetReqFieldDisplayed(){
		boolean isVisible = false;
		try{
			logger.info("Verifying the minimum petition request Limit field");
			boolean flag=this.isElementVisible(minPetitionReqLmt, 1);
			if(flag)
			{	isVisible = true;
				logger.info("Minimum petition request Limit field displayed ");
			}
			else
			{	isVisible = false;			}
			
		}catch(Exception e){
			logger.info("Failed to verify minimum petition request Limit field display");
			isVisible=false;
		}
		return isVisible;		
	}	
	
	//Verify display of maximum petition req Limit field
	public boolean isMaxPetReqFieldDisplayed(){
		boolean isVisible = false;
		try{
			logger.info("Verifying the maximum petition request Limit field");
			boolean flag=this.isElementVisible(maxPetitionReqLmt, 1);
			if(flag)
			{	isVisible = true;
				logger.info("Maximum petition request Limit field displayed ");
			}
			else
			{	isVisible = false;			}
			
		}catch(Exception e){
			logger.info("Failed to verify maximum petition request Limit field display");
			isVisible=false;
		}
		return isVisible;		
	}	
		
	//Verify display of time field
	public boolean istimeFieldDisplayed(){
		boolean isVisible = false;
		try{
			logger.info("Verifying the time field");
			boolean flag=this.isElementVisible(txtTime, 1);
			if(flag)
			{	isVisible = true;
				logger.info("Time field displayed ");
			}
			else
			{	isVisible = false;			}
			
		}catch(Exception e){
			logger.info("Failed to verify Time field display");
			isVisible=false;
		}
		return isVisible;		
	}	
	
	//Select Platform From List
	public void selectPlatfromFrmList(String platformOption) {
		try {
			logger.info("Select Item from Platform");
			this.click(btnPlatform);
			int size=platformOptions.size();
			for (int x = 0; x < size; x++) 
			{
				String strText=platformOptions.get(x).getAttribute("title");
				if (strText.equals(platformOption))
				{	this.click(platformOptions.get(x));
					//platformOptions.get(x).sendKeys(Keys.ENTER);
					this.click(lblThrottleConfiguration);
					break;
				}
				
			}
			
		}
		catch(Exception e){
			logger.info("Failed to select option from Platform List");
		}
			
	}
		
	//Select Category From List
	public void selectCategoryFrmList(String CategoryOption) {
		try {
			logger.info("Select Item from Category");
			this.click(btnCategory);
			int size=this.categoryOptions.size();
			for (int y = 0; y < size; y++) 
			{
				String strText=this.categoryOptions.get(y).getAttribute("title");
				if (strText.equals(CategoryOption))
				{	this.click(categoryOptions.get(y));
					//categoryOptions.get(y).sendKeys(Keys.ENTER);
					this.click(lblThrottleConfiguration);;
					break;
				}
				
			}
			
		}
		catch(Exception e){
			logger.info("Failed to select option from Category List");
		}
		
	}
	
	
	//Verify display of time field
	public boolean saveThrottleConfig(String strProduct,String strPlatform,String strCategory){
		boolean isSaved = false;
		int i=0;
		int j=0;
		try{
			logger.info("Verifying Save Throttle Configuration");
			//Select Product from List
			this.selectVisibleText(lstProduct, strProduct);
			//Select Platform from List
			while (platformOptions.size() <= 0 && i<30) {
				Thread.sleep(1000);
				i++;
			}
			this.selectPlatfromFrmList(strPlatform);
			//Select Category from List
			while (categoryOptions.size() <= 0 && j<30) {
				Thread.sleep(1000);
				j++;
			}
			this.selectCategoryFrmList(strCategory);
			//Enter max, min and time fields
			this.sendKeys(minPetitionReqLmt,"3");
			Thread.sleep(1000);
			this.sendKeys(maxPetitionReqLmt,"5");
			Thread.sleep(1000);
			this.sendKeys(txtTime,"1");
			//this.click(btnSave);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnSave);
			if (this.isElementVisible(msgText, 25))
				{isSaved = true;
				logger.info("Save Throttle Configuration Verified");
				}
			else
				isSaved = false;
						
		}catch(Exception e){
			logger.info("Failed to Save Throttle Configuration");
			isSaved=false;
		}
		return isSaved;
	}
	
	//Click on Job Role  Link
    public void clickOnJobRoleLnk() {
    	try{
			logger.info("Click on Job Role Link");
			this.click(lnkJobRole);
			this.waitForElementToBeVisible(lnkQueues, 3);
    	}
    	catch(Exception e){
			logger.info("Failed to click on Job Role Link");
    	}
    } 
    
    //Click on Queues  Link
    public void clickOnQueuesLnk() {
    	try{
			logger.info("Click on Queues Link");
			this.click(lnkQueues);
			
    	}
    	catch(Exception e){
			logger.info("Failed to click on Queues Link");
    	}
    } 
}
