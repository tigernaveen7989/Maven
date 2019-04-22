/**
 * 
 * @author Mohan Kamsu
 * @Description Page object created for Fill Your Queue Modal
 */

package com.ea.wwce.automation.thor.pageobjects;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * 
 * @author mohan
 * @description Thor Fill Your Queue Dialog object
 */

public class FillYourQueuePage extends ThorBasePageObject{
	
	public FillYourQueuePage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver,this);
	}
	
    private static final Logger logger = Logger.getLogger(FillYourQueuePage.class);
    @FindBy(xpath ="//div[@class='slds-modal__header']/h2")
    WebElement lblFillUrQueue;
    @FindBy(xpath ="//button[@title='Cancel']")
    WebElement btnCancel;
    @FindBy(xpath ="//button[text()='Save']")
    WebElement btnSave;
    @FindBy(xpath ="//button[text()='Change Role']")
    WebElement btnChangeRole;
    String Queue_CheckBox_XPATH="//span[text()='#']";
    @FindBy(xpath="//lightning-spinner[contains(@class,'cThorBase cThor')]")
    WebElement spinner;
    @FindBy(xpath="//legend[text()='Select One or More Queues to Pull Cases']/following::label[1]")
    WebElement firstChkBox;
  
    
    
    
    //Verify Fill Your Queue dialog displayed
    public boolean verifyExistanceOfFillUrQueueDialog() {
    	boolean flag=false;
    	try{
			logger.info("Verify Select Your Queue Dialog");
			flag=this.isElementVisible(lblFillUrQueue, 6);
    	}
    	catch(Exception e){
			logger.info("Failed to Verify Select Your Queue Dialog");
    	}
    	return flag;
    }  
    
       
    public void selectQueueCheckBox(String CheckBoxName) {
    	try{
			logger.info("Select Queue Check Box");
			if(!(this.isElementEnabled(btnSave, 2)))
			     this.clickOnDynamicElement(Queue_CheckBox_XPATH, CheckBoxName);
			this.click(btnSave);
			//this.waitForElementToBeInVisible(spinner, 30);
			Thread.sleep(4000);
		}
    	catch(Exception e){
			logger.info("Failed to Select Queue Check Box");
    	}
    } 
     
    public void selectDifferentQueueCheckBox(String CheckBoxName1, String CheckBoxName2) {
     	try{
 			logger.info("Un Select already checked Queue Check Box");
 			this.clickOnDynamicElement(Queue_CheckBox_XPATH, CheckBoxName1);
 			logger.info("Select New Queue Check Box");
 			this.clickOnDynamicElement(Queue_CheckBox_XPATH, CheckBoxName2);
 			this.click(btnSave);
 			//this.waitForElementToBeInVisible(spinner, 30);
 			Thread.sleep(5000);
 		}
     	catch(Exception e){
 			logger.info("Failed to Select New Queue Check Box");
     	}
    } 
    
    //Select first check box
    public void selectFirstQueueCheckBox() {
    	try{
			logger.info("Select First Queue Check Box");
			if (this.isElementVisible(firstChkBox, 3))
			{
				this.click(firstChkBox);
				this.click(btnSave);
				Thread.sleep(5000);
    		}
			//this.waitForElementToBeInVisible(spinner, 30);
		}
    	catch(Exception e){
			logger.info("Failed to Select First Queue Check Box");
    	}
    }
    //Queue name displayed on Fill your Queue modal   
    public boolean isPresentQueueName(String text) {
  	   boolean flag=false;
	 	try{
				logger.info("verify existence of Queue name");
				this.isDynamicElementPresent(Queue_CheckBox_XPATH, text, 10);
				Thread.sleep(5000);
				flag=true;
			
		}
	  	catch(Exception e){
				logger.info("Failed to Select New Queue Check Box");
	  	}
	 	return flag;
    } 
    
}
