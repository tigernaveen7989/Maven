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
 * @description Classic Sales Force Cases object Page 
 */

public class SFClassicCasesObjectPage extends ThorBasePageObject{
	
	public SFClassicCasesObjectPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver,this);
	}
	
    private static final Logger logger = Logger.getLogger(SFClassicCasesObjectPage.class);
    @FindBy(xpath ="//h3[text()='CRMEvents']")
    WebElement lblCRMEvents;
    @FindBy(xpath ="//h3[text()='CRMEvents']//following::table[1]")
    WebElement tblCRMEvents;
    @FindAll(@FindBy(xpath="//h3[text()='CRMEvents']//following::table[1]/tbody/tr"))
	List<WebElement> CRMEventsTableRows;
    @FindBy(xpath ="//h3[text()='CRMEvents']//following::a[contains(text(),'Go to list')][1]")
    WebElement lnkGoToList;
    @FindBy(xpath ="//h3[text()='CRMEvents']//following::a[contains(@href,'showXMore')][1]")
    WebElement lnkShowMore;
    String strCaseDetailFieldVal_XPATH="//table[@class='detailList']//td[text()='#']/following-sibling::td[1]";
    String strCRMEvent_XPATH="//h3[text()='CRMEvents']//following::table[1]/tbody/tr[#]/th/a";
    String strCRMEvenHideValue_XPATH="//h3[text()='CRMEvents']//following::table[1]/tbody/tr[%d]/td[%d]";
    String strCaseComment_XPATH="//h3[text()='Case Comments']//following::table[1]/tbody/tr[%d]/td[%d]";
    
    
    //Get Case Detail Table any field value
    @Step("Get Case details table field value")
    public String getCaseDetailAnyFieldVale(String strFieldName,String strAttributeName)
  	{	String strFieldVal="";
  		try {
  			logger.info("Get SF Case Detail field value from Case Details table");
  			this.verifyPageIsLoaded();
  			strFieldVal=this.getAttributeValueOfDynamicElement(strCaseDetailFieldVal_XPATH,strFieldName,strAttributeName);
  		  			
  		}catch (Exception e) {
  			logger.info("Failed to get SF field value from Case detail table  " + e.getMessage());
  		}
  		return strFieldVal;
  	}
    
  
    //Get CRM events count from CRM events table
    public int getCRMEventsCount() {
    	int intCRMEventsCnt=0;
    	try{
			logger.info("Get The Table Count of CRM events ");
			while (this.isElementVisible(lnkShowMore, 5))
			{	this.click(lnkShowMore);
				
			}
			Thread.sleep(2000);
			this.moveToElement(lblCRMEvents);
			Thread.sleep(2000);
			intCRMEventsCnt=this.getSize(CRMEventsTableRows);
		}catch(Exception e){
			logger.info("Failed to get the CRM events table count ");
    	}
    	return intCRMEventsCnt;
    }
    
    //Click on CRM Event
    public void clickOnCRMEvent(int intCRMEventTblIdx) {
    	try {
			logger.info("Click on CRM Event based on index");
			String strCRMEventTblIdx=Integer.toString(intCRMEventTblIdx);
			this.clickOnDynamicElement(strCRMEvent_XPATH, strCRMEventTblIdx);
		}catch(Exception e){
			logger.info("Failed to click on CRM event in Table ");
    	}
    }
    
    // Get CRM Case Comments
    public String getCaseComments()
  	{	String strCaseComments="";
  		try {
  			logger.info("Get Casecomments from Case comments table");
  			strCaseComments=this.getTableCellVal(strCaseComment_XPATH, 2, 2);
  			
  		}catch (Exception e) {
  			logger.info("Failed to get case comments from Case detail table  " + e.getMessage());
  		}
  		return strCaseComments;
  	}
    
    //Get Hide text from CRM Event Details table
    @Step("Get hide text from CRM Event Detail Table ")
    public String getHidetextFrmCRMEventDetailTbl(int rcount) {
       String strHidetext="";
       try {
                      logger.info("Get Hidetext from CRM Event Detail Table");
                      Thread.sleep(2000);
                      strHidetext=this.getTableCellVal(strCRMEvenHideValue_XPATH, rcount, 2);
                      
       }catch (Exception e) {
                      logger.info("Failed to get Hide text from CRM Event Detail Table  " + e.getMessage());
       }
       return strHidetext;
    }

 

}