/**
 * 
 * @author Mohan Kamsu
 * @Description Page object created for Omni Channel Widget
 */

package com.ea.wwce.automation.thor.pageobjects;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.ea.wwce.automation.thor.config.ThorDataConstants; 

/**
 * 
 * @author mohan
 * @description Thor Omni Channel Widget object
 */

public class OmniChannelWidgetPage extends ThorBasePageObject{
	
	public OmniChannelWidgetPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver,this);
	}
	
    private static final Logger logger = Logger.getLogger(OmniChannelWidgetPage.class);
    @FindBy(xpath ="//a[@class='headerLink']/following::button[1]") 
    WebElement btnOmniStatusDrDwn;
    @FindBy(xpath ="//span[contains(@title,'Minimize')]/button")
    WebElement btnMinimize;
    @FindBy(xpath ="//span[contains(text(),'New') and contains(@class,'title')]")
    WebElement lblNew;
    @FindBy(xpath ="//li[contains(@title,'Available')]/a")   
    WebElement listItemAvailable;
    @FindBy(xpath ="//li[contains(@class,'slds-dropdown__item offlineStatus')]/a/span")
    WebElement listItemOffLine;
    @FindBy(xpath ="//div[contains(@class,'slds-col slds-align-middle')]")
    WebElement lblStatus;
    @FindBy(xpath ="//span[contains(@class,'offlineStatus') or contains(@class,'onlineStatus') or contains(@class,'updatingStatus')]")
    WebElement lblOmniStatus;
    @FindAll(@FindBy(xpath="//li[contains(@class,'slds-dropdown__item')]"))
	List<WebElement> advisorStatus;
    @FindAll(@FindBy(xpath="//div[@class='secondaryFields']"))
    List<WebElement> caseNums;
    
    
  //Get Omni Status from Widget
    public String getOmniStatusFrmWidget() {
    	String strText="";
    	try{
			logger.info("Get Status Text from Omni Widget");
			strText=this.getText(lblOmniStatus);
    	}
    	catch(Exception e){
			logger.info("Failed to Get status text from Omni widget");
    	}
		return strText;
    }  
    
       
    //Change Omni Status
    public void changeOmniStatusToAvailable() {
    	try{
			logger.info("Changing Omni Status to Available");
			//Get the current status of Omni channel
			String strStatus=this.getOmniStatusFrmWidget();
			
			//Change Omni Status to Available
			if (strStatus.equals("Offline"))
			{	// Click on Omni Status drop down button"
				this.isElementVisible(btnOmniStatusDrDwn, 3);
				this.click(btnOmniStatusDrDwn);
				Thread.sleep(2000);
				this.isElementVisible(listItemAvailable, 3);
				//this.click(btnOmniStatusDrDwn);
				Thread.sleep(2000);
				this.click(listItemAvailable);
				String strStatus1=this.getOmniStatusFrmWidget();
				//System.out.println(strStatus1);
				if(strStatus1.equals("Offline"))
				{	this.click(btnOmniStatusDrDwn);
					Thread.sleep(2000);
					this.isElementVisible(listItemAvailable, 3);
					Thread.sleep(2000);
					this.click(listItemAvailable);
									}
				logger.info("Omni Status is changed to Available");
			}
			else
			logger.info("Omni Status is already Available");
		}
    	catch(Exception e){
			logger.info("Failed to change Omni Status to Available");
		}
    }
    
    //Click on Omni Minimize Button
    public void clickOnOmniMinimizeButton() {
    	try{
			logger.info("Click on Omni Minimize button");
			this.click(btnMinimize);
		}
    	catch(Exception e){
			logger.info("Failed to Click on Omni minimize button");
    	}
    }
    
    //Get Case Count from New Label
    public int getCasesCount() {
    	int intTempCaseCnt=0;
    	try{
			logger.info("Get Cases Count from Omni widget New link");
			String strText=this.getText(lblNew);
			String strSubText=strText.substring(5);
			String arrText[]=strSubText.split("\\)",0);
			intTempCaseCnt=Integer.parseInt(arrText[0]);
    	}
    	catch(Exception e){
			logger.info("Failed to get count from New Label");
    	}
    	return intTempCaseCnt;
    }
    
    //Verify Omni widget case count and Configured Queue count
    public int getCasesCountFrmOmniWidget() {
    	int intCaseCnt=0; int i=0;
    	try{
			logger.info("Verify Case count from Omni with Configured no of Queues");
			intCaseCnt=getCasesCount();
			while(intCaseCnt>=0 && intCaseCnt<=ThorDataConstants.OMNI_CONFIGURED_QUEUE_CNT) {
				if (ThorDataConstants.OMNI_CONFIGURED_QUEUE_CNT==intCaseCnt) {
					  break;
				}
				else
				{	Thread.sleep(1000);
					i=i+1;
					intCaseCnt=getCasesCount();	
					if((i>9) && (intCaseCnt==0) && (ThorDataConstants.OMNI_CONFIGURED_QUEUE_CNT==10))
					{	logger.info("No cases assigned to Advisor");
						break;
					}	
					if((i>20) && (intCaseCnt==0) && (ThorDataConstants.OMNI_CONFIGURED_QUEUE_CNT==100))
					{	logger.info("No cases assigned to Advisor");
						break;
					}	
					if((i>ThorDataConstants.OMNI_CONFIGURED_QUEUE_CNT)&&(intCaseCnt<=ThorDataConstants.OMNI_CONFIGURED_QUEUE_CNT))
						break;
				}
						
			}
		}
    	catch(Exception e){
			logger.info("Failed to verify Omniwidget count");
    	}
    	return intCaseCnt;
    }
    
    
    //Get Case Count from New Label
    public String[] getAdvisorStatusInArray() {
    	int intSize=0;
    	String strArray[] = null;
    	try{
			logger.info("Get All advisor Status in array");
			this.isElementVisible(btnOmniStatusDrDwn, 3);
			this.click(btnOmniStatusDrDwn);
			Thread.sleep(1000);
			intSize=this.getSize(advisorStatus);
			strArray = new String[intSize];
			for(int i=0;i<intSize;i++) 
				 strArray[i]=(advisorStatus.get(i).getAttribute("title")).trim();
			this.click(btnOmniStatusDrDwn);
    	}
    	catch(Exception e){
			logger.info("Failed to get Advisor Status in Array");
    	}
    	return strArray;
    }
    
        
    //Get Case Num in Array
    public String[] getCaseNumsInArray() {
    	String strArray[] = null;
    	String strText;
    	int intSize;
    	try{
			logger.info("Get All Case Nums in array");
			intSize=this.getSize(caseNums);
			strArray = new String[intSize];
			for(int i=0;i<intSize;i++) 
			{	strText=(caseNums.get(i).getAttribute("innerText")).trim();
			 	String numberOnly= strText.replaceAll("[^0-9]", "");
			 	strArray[i]=numberOnly;
			} 
    	}catch(Exception e){
				logger.info("Failed to get Case Nums in Array");
	    }
		return strArray;
    }
  
    
}
