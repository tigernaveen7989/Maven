package com.ea.wwce.automation.thor.pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.thor.config.ThorDataConstants;

import io.qameta.allure.Step;


/**
 * 
 * @author praveen
 * @description Thor Advisor Case History page object
 */

public class CaseHistoryPage extends ThorBasePageObject{
	
	public CaseHistoryPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver,this);
	}
	
    private static final Logger logger = Logger.getLogger(CaseHistoryPage.class);
   	@FindBy(xpath="//div[contains(@class,'cDataGridTableExpand cDataGrid')]//div[contains(@class,'slds-border_bottom slds-section caseHistoryData')]//div[contains(@class,'slds-truncate subject')]")
	WebElement subCaseHistory;
   	@FindAll(@FindBy(xpath="//div[contains(@class,'cDataGridTableExpand cDataGrid')]//div[contains(@class,'slds-border_bottom slds-section caseHistoryData')]//div[contains(@class,'slds-truncate subject')]"))
	List<WebElement> CaseHistoryTblRows;
   	
   	String gettblDetails_XPATH="(//div[contains(@class,'slds-border_bottom slds-section caseHistoryData')])[1]//div[contains(@class,'slds-col slds-size_1-of-8 ')][%d]";
   	
   	
  //Get the Subject name value from Case History page
  	@Step("Method to get the Case History data ")
  	public String getCaseHisotrytableData() {
  		String strText="";
  		try {  			
  			String strCaseNumberFrmHistorytbl="";
  			String strDateFrmHistorytbl="";
  			String strStatusFrmHistorytbl="";
  			String strTypeFrmHistorytbl="";
  			String strFulldetailsFrmHistorytbl="";
		    strCaseNumberFrmHistorytbl=this.getTableDataVal(gettblDetails_XPATH, 1);
		    strDateFrmHistorytbl=this.getTableDataVal(gettblDetails_XPATH, 2);
		    strStatusFrmHistorytbl=this.getTableDataVal(gettblDetails_XPATH, 3);
		    strTypeFrmHistorytbl=this.getTableDataVal(gettblDetails_XPATH, 4);
		    strFulldetailsFrmHistorytbl=this.getTableDataVal(gettblDetails_XPATH, 5);
		   // System.out.println(strCaseNumberFrmHistorytbl+strDateFrmHistorytbl+strStatusFrmHistorytbl+strTypeFrmHistorytbl+strFulldetailsFrmHistorytbl);
		    strText=strCaseNumberFrmHistorytbl+","+strDateFrmHistorytbl+","+strStatusFrmHistorytbl+","+strTypeFrmHistorytbl+","+strFulldetailsFrmHistorytbl;
			   			
  		}
  		catch (Exception e) {
  			logger.info("Failed to get the Case History Data " + e.getMessage());
  		}
  		return strText;
  	}
  	
  	@Step("Verify Case History table count")
	public int getCaseHistorytblCount() {
		  int rowCnt=0;
  		try {
			this.windowScrollUp();
			Thread.sleep(5000);
			rowCnt=this.getSize(CaseHistoryTblRows);
			}
		catch (Exception e) {
			logger.info("Failed to get the History count" + e.getMessage());
		}
		return rowCnt;
	}
   
  	
	public String getTableDataVal(String strXpath,int r ) {
		String strText = null;
		try {			
			String fullXpath=String.format(strXpath,r);
			strText=this.getAttributeValue(driver.findElement(By.xpath(fullXpath)),"innerText");
		}
		catch (Exception e) {
			logger.info("Failed to Click on Table Cell  " + e.getMessage());
		}
		return strText;
	} 

	
		
	
	
	
	
}
