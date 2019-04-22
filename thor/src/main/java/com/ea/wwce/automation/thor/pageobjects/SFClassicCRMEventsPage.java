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
import org.openqa.selenium.interactions.Actions;
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
 * @description Classic Sales Force CRM Events object Page 
 */

public class SFClassicCRMEventsPage extends ThorBasePageObject{
	
	public SFClassicCRMEventsPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver,this);
	}
	
    private static final Logger logger = Logger.getLogger(SFClassicCRMEventsPage.class);
    String strCRMEventDetailFieldVal_XPATH="//table[@class='detailList']//td[text()='#']/following-sibling::td[1]";
    String strCaseEventDetails_XPATH="//h3[text()='CaseEventDetails']//following::table[1]/tbody/tr[#]";
    String strAccountEventDtails_XPATH="//h3[text()='AccountEventDetails']//following::table[1]/tbody/tr[#]";
    String strCaseInteractionEventDtls_XPATH="//h3[text()='Case Interactions']//following::table[1]/tbody/tr[#]";
    String strAccountEventDtails_Cell_XPATH="//h3[text()='AccountEventDetails']//following::table[1]/tbody[1]/tr[%d]/td[%d]"; 
        
    @FindBy(xpath ="//a[contains(@class,'switch-to-lightning')]")
    WebElement lnkSwitchToLightning;
    @FindAll(@FindBy(xpath="//h3[text()='AccountEventDetails']//following::table[1]/tbody/tr"))
	List<WebElement> AccountEventDetailTableRows;
   
    //Get CRM Event Detail Table any field value
    @Step("Get CRM Event details table field value")
    public String getCRMEventDetailAnyFieldVale(String strFieldName,String strAttributeName)
  	{	String strFieldVal="";
  		try {
  			logger.info("Get SF CRM Event field value from  Details table");
  			this.verifyPageIsLoaded();
  			strFieldVal=this.getAttributeValueOfDynamicElement(strCRMEventDetailFieldVal_XPATH,strFieldName,strAttributeName);
  		  			
  		}catch (Exception e) {
  			logger.info("Failed to get SF CRM Event field value from CRM detail table  " + e.getMessage());
  		}
  		return strFieldVal;
  	}
    
  
    //Get Case Event details from Case Event Details table
  	@Step("Get Case Event Details from Case Event Detail Table ")
  	public boolean getCaseEventDetailsFrmTbl()
  	{	boolean flag=false;
  		String strText=null;
  		try {
  			logger.info("Get Case Event Detail From Table");
  			//Get Case Event Details
  			String strCaseEventDetais=this.getAttributeValueOfDynamicElement(strCaseEventDetails_XPATH,"2","innerText");
  			//System.out.println(strCaseEventDetais);
  			String strArr[]=strCaseEventDetais.split("\t");
  			int intSize=strArr.length;
  			for(int i=1;i<intSize;i++)
  			{	strText=strArr[i].trim();
  				if(strText.isEmpty())
  				{flag=false;
  				 break;
  				}
  				else
  				flag=true;
  			}
  			
  		}catch (Exception e) {
  			logger.info("Failed to get Case Event Detail From Table  " + e.getMessage());
  		}
  		return flag;
  	}
  	
  	
	//This function verifies the Visibility of Switch to Lightning experience link
  	public boolean islnkSwitchToLightningExperienceVisible(){
  		boolean isVisible = false;
  		try{
  			logger.info("Verifying the SwitchToLightningExperience Link on successful login");
  			this.waitForElementToBeVisible(lnkSwitchToLightning, 5);
  			isVisible = true;
  		}catch(Exception e){
  			logger.info("Failed to verify lnkSwitchtoLightning Link");
  			isVisible=false;
  		}
  		return isVisible;		
  	} 

  	
  	// Method to Click on Switch to Lightning link
 	@Step("Click on switch to SF lightning link")
 	public void clickOnSFLightningLnk() {
 		try {
 			//Click on Lightning link
 			if (this.isElementVisible(lnkSwitchToLightning, 3))
 			{
 				this.click(lnkSwitchToLightning);
 				this.verifyPageIsLoaded();
 			}
 			else
 				logger.info("Switching to SF lightning Link not visible");
 			
 		}catch (Exception e) {
 			logger.info("Failed to Click on Switch to lightning link  " + e.getMessage());
 		}
 	}
 	
 	//Get CRM Account Event details from CRM Event Details table
  	@Step("Get CRM Account Event Details from CRM Event Detail Table ")
  	public boolean getAccountEventDetailsFrmTbl()
  	{	boolean flag=false;
		String strText=null;
		try {
			logger.info("Get Account Event Detail From Table");
			//Get Account Event Detail Name
			String strAccountEventDetais=this.getAttributeValueOfDynamicElement(strAccountEventDtails_XPATH,"2","innerText");
			//System.out.println(strAccountEventDetais);
			String strArr[];
			strArr=strAccountEventDetais.split("\t");
			int intSize=strArr.length;
			for(int i=1;i<intSize;i++)
			{	strText=strArr[i].trim();
				if(strText.isEmpty())
				{flag=false;
				 break;
				}
				else
				flag=true;
			}
  			
  		}catch (Exception e) {
  			logger.info("Failed to get Hide Event Detail From Table  " + e.getMessage());
  		}
  		return flag;
  	}
  	
  	//Get Account event Details table count from CRM events Detail table
    public int getAccountEventDetailRowsCount() {
    	int intEventsCnt=0;
    	try{
			logger.info("Get The Table Count ");
			intEventsCnt=this.getSize(AccountEventDetailTableRows);
	    	
    	}catch(Exception e){
			logger.info("Failed to get the Account Event Details table count ");
    	}
    	return intEventsCnt;
    }
  	
    //Get CRM Ban Entitlements details from CRM Event Details table
  	@Step("Get CRM Ban Entitlement Event Details from CRM Event Detail Table ")
  	public boolean getCRMBanEntitlementEventDetailsFrmTbl(String strEntitlementName)
  	{	boolean flag=false;
  		int intEventsCnt;
  		try {
  			logger.info("Get CRM Event Ban Entitlement Detail From Table");
  			//Get Table Count
  			intEventsCnt=this.getAccountEventDetailRowsCount();
  			//Get Ban Entitlement Name
  			String strName=getTableCellVal(strAccountEventDtails_Cell_XPATH, intEventsCnt, 2);
  			//Get Ban Account ID
  			int j=intEventsCnt-1;
  			String strBanEventAcctId=getTableCellVal(strAccountEventDtails_Cell_XPATH, j, 2);
  			//Get Ban Event New Value
  			int k=intEventsCnt-2;
  			String strBanEventNewVal=getTableCellVal(strAccountEventDtails_Cell_XPATH, k, 2);
  			//Get Ban Event Old Value
  			String strBanEventOldVal=getTableCellVal(strAccountEventDtails_Cell_XPATH, k, 3);
  			
  			if (strBanEventNewVal.contains("BANNED") &&(!(strBanEventOldVal.isEmpty())) && strName.contains(strEntitlementName) &&(!(strBanEventAcctId.isEmpty())))
  				flag=true;
	  		else
	  			flag=false;
  			
  			
  		}catch (Exception e) {
  			logger.info("Failed to get Ban Entitlement Event Detail From Table  " + e.getMessage());
  		}
  		return flag;
  	}
  	
  	  	
    //Get CRM Suspend Franchise Event details from CRM Event Details table
  	@Step("Get CRM Suspend Franchise Event Details from CRM Event Detail Table ")
  	public boolean getCRMSuspendFranchiseEventDetailsFrmTbl()
  	{	boolean flag=false;
  		int intEventsCnt=0;
  		try {
  			logger.info("Get CRM Event Suspend Franchise Detail From Table");
  			//Get Suspend Event New Value
  			intEventsCnt=this.getAccountEventDetailRowsCount();
  			String strSuspendEventNewVal=getTableCellVal(strAccountEventDtails_Cell_XPATH, intEventsCnt, 2);
  			//Get Suspend Event Old Value
  			String strSuspendEventOldVal=getTableCellVal(strAccountEventDtails_Cell_XPATH, intEventsCnt, 3);
  			if (strSuspendEventNewVal.contains("SUSPEND") &&(!(strSuspendEventOldVal.isEmpty())))
  				flag=true;
	  		else
	  			flag=false;
  			
  			
  		}catch (Exception e) {
  			logger.info("Failed to get Suspend Franchise Event Detail From Table  " + e.getMessage());
  		}
  		return flag;
  	}
   

 	//Get Case Interaction Event details 
  	@Step("Get Case Interaction Event Details ")
  	public boolean getCaseInterEventDetailsFrmTbl()
  	{	boolean flag=false;
  		try {
  			logger.info("Get Case Interaction Event Detail From Table");
  			//Get Case Event Details
  			String strCaseInterEventDetais=this.getAttributeValueOfDynamicElement(strCaseInteractionEventDtls_XPATH,"2","innerText");
  			//System.out.println(strCaseInterEventDetais);
  			if(strCaseInterEventDetais.contains("Inbound") && strCaseInterEventDetais.contains("CaseCreation") && strCaseInterEventDetais.contains("New") )
  				flag=true;
  			
  		}catch (Exception e) {
  			logger.info("Failed to get Case Event Detail From Table  " + e.getMessage());
  		}
  		return flag;
  	}
  	
  	
    
}