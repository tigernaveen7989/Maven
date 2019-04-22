/**
 * 
 * @author Mohan Kamsu
 * @Description Page object created for Choose Your Role Modal
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


/**
 * 
 * @author mohan
 * @description Thor Choose Your Role Dialog object
 */

public class ChooseYourRolePage extends ThorBasePageObject{
	
	public ChooseYourRolePage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver,this);
	}
	
    private static final Logger logger = Logger.getLogger(ChooseYourRolePage.class);
    @FindBy(xpath ="//div[@class='slds-modal__header']/h2")
    WebElement lblChooseUrRole;
    @FindBy(xpath ="//button[@title='Cancel']")
    WebElement btnCancel;
    @FindBy(xpath ="//button[@title='Next']")
    WebElement btnNext;
    String Role_Radio_Btn_XPATH="//span[text()='#']";
    @FindBy(xpath="//button[text()='Select']")
    WebElement btnSelect; 
    @FindAll(@FindBy(xpath="//span[@class='slds-radio']"))
	List<WebElement> jobRoles;
    
    
  //Verify Choose Your Role dialog displayed
    public boolean verifyExistanceOfChooseUrRoleDialog() {
    	boolean flag=false;
    	try{
			logger.info("Verify Choose Your Role Dialog");
			flag=this.isElementVisible(lblChooseUrRole, 6);
    	}
    	catch(Exception e){
			logger.info("Failed to Verify Select Your Role Dialog");
    	}
    	return flag;
    }  
    
       
    //Select Role Radio Button
    public void selectRoleRadioButton(String RoleName) {
    	try{
			logger.info("Select Role Radio button");
			this.clickOnDynamicElement(Role_Radio_Btn_XPATH, RoleName);
			this.click(btnSelect);
			Thread.sleep(5000);
		}
    	catch(Exception e){
			logger.info("Failed to Select Role radio button");
    	}
    }
    
    //Get all the Radio button names from Modal
    public List<String> getAlltheJobRoles() {
    	List<String> temp = new ArrayList<String>();
    	int index=0;
    	try{
			logger.info("Get all the Job roles names from Select Your Role modal");
			int size=this.getSize(jobRoles);
			//System.out.println(size);
			for (index=0; index<size; index++) 
				temp.add(index, jobRoles.get(index).getText());
			Thread.sleep(1000);	
			this.click(btnSelect);
			Thread.sleep(3000);	
		}
    	catch(Exception e){
			logger.info("Failed to get job roles from Modal");
    	}
    	//Click on Select button
    	
    	return temp;
    }
    
}
