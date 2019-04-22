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

public class SFClassicHomePage extends ThorBasePageObject{
	
	public SFClassicHomePage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver,this);
	}
	
    private static final Logger logger = Logger.getLogger(SFClassicHomePage.class);
    @FindBy(xpath ="//input[@id='phSearchInput']")
    WebElement txtSearch;
    @FindBy(xpath ="//input[@id='phSearchButton']")
    WebElement btnSearch;
    
    String strPenaltyConfigurationValues_XPATH="//div/table[contains(@class,'list')]/tbody/tr[%d]/td[%d]";
    String strDeleteConfigurationValues_XPATH="//div/table[contains(@class,'list')]/tbody/tr[#]/td[1]/a[2]";
	@FindBy(xpath ="//a[contains(text(),'Create New View')]")
	WebElement lnkCreateNewView;
	@FindBy(xpath = "//input[@name='fname']")
	WebElement txtboxViewName;
	@FindBy(xpath = "//input[@name='devname']")
	WebElement txtboxViewUniqueName;
	@FindBy(xpath = "//label[contains(text(),'All Tos Penalty Configurations')]")
	WebElement radiobtnAllTos;
	@FindBy(xpath = "//input[contains(@title,'New Tos Penalty Configuration')]")
	WebElement btnNewTosPenaltyConfig;
	@FindBy(xpath = "//h2[contains(text(),'Tos Penalty and Disciplinary Configuration')]")
	WebElement txtTosPDC;
	@FindBy(xpath = "(//input[contains(@type,'text')])[2]")
	WebElement txtProduct;
	@FindBy(xpath = "//input[contains(@type,'checkbox')]")
	WebElement chkboxIsAllPlatforms;
	@FindBy(xpath = "//select[contains(@title,'Penalty Level - Available')]")
	WebElement selectPenaltyLevel;
	@FindBy(xpath = "(//img[contains(@title,'Add')])[1]")
	WebElement imgPenaltyLevelAdd;
	@FindBy(xpath = "//select[contains(@title,'Disciplinary Action - Available')]")
	WebElement SelectDisciplinaryAction;
	@FindBy(xpath = "(//img[contains(@title,'Add')])[2]")
	WebElement imgDisciplinaryActionAdd;
	@FindBy(xpath = "(//input[contains(@value,'Save')])[2]")
	WebElement btnSave;
	@FindAll(@FindBy(xpath="//div/table[contains(@class,'list')]/tbody/tr"))
	List<WebElement> getPenaltyConfigurationTableRows;
	@FindBy(xpath = "//a[contains(text(),'Del')]")
	WebElement btnDelete;
	@FindBy(xpath ="//div[@class='fewerMore']//a[contains(@href,'apex/tospenaltyconfigurations?j_id0%3Aj_id1%3Arowsperpage')][2]")
	WebElement lnkShowMore;
	@FindBy(xpath ="//div[@id='userNav-arrow']")
	WebElement lnkUserMenu;
	@FindBy(xpath ="//a[text()='Setup']")
	WebElement lnkSetup;
	
    
    //Search SF object in Home page
    public void searchSFObject(String objectNameVal) {
    	try{
			logger.info("Searching for object "+objectNameVal);
			this.verifyPageIsLoaded();
	    	this.waitForElementToBeVisible(txtSearch, 10);
	    	this.click(txtSearch);
	    	this.sendKeys(txtSearch, objectNameVal);
	    	this.click(btnSearch);
    	}catch(Exception e){
			logger.info("Failed to Search the object value "+objectNameVal);
    	}
    }
    
    //Get product name from CRM penalty configuration table
    @Step("Get Product name from CRM Penalty configuration Table ")
    public String getProductNameFrmCRMPenaltyConfigurationTbl(int totalrowcount)
    {	String strProductName="";
    	try {
    		logger.info("Get ProductName from CRM Penalty Configuration Table");
    		Thread.sleep(2000);
    		strProductName=this.getTableCellVal(strPenaltyConfigurationValues_XPATH, totalrowcount, 2);
    		
    	}catch (Exception e) {
    		logger.info("Failed to get Product Name from CRM Penalty Configuration Table  " + e.getMessage());
      	}
      		return strProductName;
    }
    
    //Get Platform from CRM penalty configuration table
    @Step("Get Platform from CRM Penalty configuration Table ")
    public String getPlatformFrmCRMPenaltyConfigurationTbl(int totalrowcount)
    {	String strPlatform="";
    	try {
    		logger.info("Get Platform from CRM Penalty Configuration Table");
    		Thread.sleep(2000);
    		strPlatform=this.getTableCellVal(strPenaltyConfigurationValues_XPATH, totalrowcount, 4);
    		
    	}catch (Exception e) {
    		logger.info("Failed to get Platform from CRM Penalty Configuration Table  " + e.getMessage());
      	}
      		return strPlatform;
    }
 
    //Get Penalty Level from CRM penalty configuration table
    @Step("Get Penalty Level from CRM Penalty configuration Table ")
    public String getPenaltyLevelFrmCRMPenaltyConfigurationTbl(int totalrowcount)
    {	String strPenaltyLevel="";
    	try {
    		logger.info("Get PenaltyLevel from CRM Penalty Configuration Table");
    		Thread.sleep(2000);
    		strPenaltyLevel=this.getTableCellVal(strPenaltyConfigurationValues_XPATH, totalrowcount, 5);
    		
    	}catch (Exception e) {
    		logger.info("Failed to get PenaltyLevel from CRM Penalty Configuration Table  " + e.getMessage());
      	}
      		return strPenaltyLevel;
    }
      	
    //Get Disciplinary Action from CRM penalty configuration table
    @Step("Get Disciplinary Action from CRM Penalty configuration Table ")
    public String getDisciplinaryActionFrmCRMPenaltyConfigurationTbl(int totalrowcount)
    {	String strDisciplinaryAction="";
    	try {
    		logger.info("Get DisciplinaryAction from CRM Penalty Configuration Table");
    		Thread.sleep(2000);
    		strDisciplinaryAction=this.getTableCellVal(strPenaltyConfigurationValues_XPATH, totalrowcount, 6);
    		
    	}catch (Exception e) {
    		logger.info("Failed to get DisciplinaryAction from CRM Penalty Configuration Table  " + e.getMessage());
      	}
      		return strDisciplinaryAction;
    }
       	
    // Method to Click on SF New Tos penalty configuration
    @Step("Click on Tos New Tos penalty configuration")
    public void clickOnPenaltyDisciplinaryConfiguration() {
    	try {
    		logger.info("Click on New Tos penalty configuration button");
    		this.waitForElementToBeVisible(btnNewTosPenaltyConfig, 5);
    		this.click(btnNewTosPenaltyConfig);
    		Thread.sleep(2000);
    	}catch(NoSuchElementException e){
    		logger.info("Failed to find the Tos New Tos penalty configuration");
    	}	
    	catch (Exception e) {
    		logger.info("Failed to click on Tos New Tos penalty configuration button  " + e.getMessage());
      	}
    }
       	
    //create Penalty configuration from New Tos penalty configuration
    public void NewTosPenaltyConfiguration(String productNameVal,String penaltylevel,String disciplinaryAction) throws Exception {
    	logger.info("create Penalty Configuration ");
    	try {
    		logger.info("create New Tos Penalty Confirguration");			
    		//this.verifyPageIsLoaded();
        	this.isElementVisible(txtProduct, 1);
        	this.click(txtProduct);
        	this.sendKeys(txtProduct, productNameVal);
        	this.click(chkboxIsAllPlatforms);
        	String array[]=penaltylevel.split(";");
        	
        	for(int i=0;i<array.length;i++) {
        		
        		    this.selectVisibleText(selectPenaltyLevel, array[i]);
        		    Thread.sleep(2000);
        			this.click(imgPenaltyLevelAdd);
        			Thread.sleep(2000);
        		}    	
        	  	    	
        	String array2[]=disciplinaryAction.split(";");
        	   	
        		for(int i=0;i<array2.length;i++) {
        			this.selectVisibleText(SelectDisciplinaryAction, array2[i]);
        			this.click(imgDisciplinaryActionAdd);
        			}
        	 	    	
        	this.click(btnSave);	    	
    		
    	} catch (NoSuchElementException e) {
    		logger.warn("Failed to create New Tos Penalty and Disciplinary configration " + e.getMessage());
    		throw e;
    	} catch (Exception e) {
    		logger.warn("Error selecting the element " + e.getMessage());
    		throw e;
    	}
    }

    @Step("get Row size of Penalty Configuration")
    public int getPenaltyConfigurationRowCount() {
    	int rowCnt=0;
    	try {
    		
    		logger.info("Get The Table Count of CRM Penalty Configuration ");
    		Thread.sleep(2000);
    		while (this.isElementVisible(lnkShowMore, 2))
    		{	this.click(lnkShowMore);
    			Thread.sleep(2000);
    		}		
    		rowCnt=this.getSize(getPenaltyConfigurationTableRows);					
    		}
    	
    	catch (Exception e) {
    		logger.info("Failed to get the count against Penalty configuration table  " + e.getMessage());
    	}
    		return rowCnt;
    }
      	
    @Step("Delete Penalty Configuration")
    public void deletePenaltyConfiguration(int r) {
    	
    	try {
    		String row=Integer.toString(r);
    		 logger.info("Delete from CRM Penalty Configuration Table");
    			Thread.sleep(2000);
    			this.clickOnDynamicElement(strDeleteConfigurationValues_XPATH,row);	
    			this.acceptAlert();
    		}
    	
    	catch (Exception e) {
    		logger.info("Failed to get the count against Penalty configuration table  " + e.getMessage());
    	}
    		
    }
    
    // Method to Click on Setup Menu
    @Step("Click on Setup menu item")
    public void clickOnSetup() {
    	try {
    		logger.info("Click on Setup menu Item");
    		this.waitForElementToBeVisible(lnkUserMenu, 5);
    		this.click(lnkUserMenu);
    		Thread.sleep(2000);
    		this.waitForElementToBeVisible(lnkSetup, 5);
    		this.click(lnkSetup);
    		Thread.sleep(2000);
    		
    	}catch(NoSuchElementException e){
    		logger.info("Failed to find the User drop down button");
    	}	
    	catch (Exception e) {
    		logger.info("Failed to click on Setup Link " + e.getMessage());
      	}
    }
    

    
}