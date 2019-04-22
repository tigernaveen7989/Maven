package com.ea.wwce.automation.thor.pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
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
 * @description THOR Reported Chat Log page object
 */

public class ThorReporterContentPage extends ThorBasePageObject{
	
	public ThorReporterContentPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver,this);
	}
	
    private static final Logger logger = Logger.getLogger(ThorReporterContentPage.class);
    
    @FindBy(xpath ="//button[contains(@title,'Close this window')]")       
    WebElement btnClose;
    @FindBy(xpath="//div[@class='header cModalHeader']")
   	WebElement lblHeading;
    @FindBy(xpath ="//button[contains(@title,'No violation')]")       
    WebElement btnNoViolation;
    @FindBy(xpath ="//button[contains(@title,'Violation confirmed')]")       
    WebElement btnViolationConfirmed;
    @FindAll(@FindBy(xpath="//input[contains(@class,'violationchk uiInput uiInputCheckbox')]"))
	List<WebElement> violationChkBoxes;
    @FindBy(xpath="//div[@class='ugc-container slds-align_absolute-center cModalContent']")
   	WebElement myImage;
    @FindBy(xpath="//div[@class='header cModalHeader']")
   	WebElement lblImageStatus;
   
	//Verify display of Chat Log Window
	public boolean isChatLogWindowDisplayed(){
		boolean isVisible = false;
		boolean flag=false;
		try{
			logger.info("Verifying the Chat Log window display");
			//Verify the heading of the chat log window
			if (this.isElementVisible(lblHeading, 8))
				flag=true;
			else
				flag=false;
			if(flag)
			{	
				isVisible = true;
				logger.info("Chat log window displayed with heading "+lblHeading );
			}
			else
			{	isVisible = false;			}
			
		}catch(Exception e){
			logger.info("Failed to verify Chat Log Window display");
			isVisible=false;
		}
		return isVisible;		
	}
	
	//Verify display of Image Window
	public boolean isReportImageWindowDisplayed(){
		boolean isVisible = false;
		boolean flag=false;
		try{
			logger.info("Verifying the Report Content window display");
			//Verify the heading of the chat log window
			if (this.isElementVisible(myImage, 3))
				flag=true;
			else
				flag=false;
			if(flag)
			{	
				isVisible = true;
				logger.info("Report content window displayed with Image ");
			}
			else
			{	isVisible = false;			}
			
		}catch(Exception e){
			logger.info("Failed to verify Image Window display");
			isVisible=false;
		}
		return isVisible;		
	}
	
	
	
	//Verify display of No Violation button
	public boolean isNoViolationButtonDisplayed(){
		boolean isVisible = false;
		boolean flag=false;
		try{
			logger.info("Verifying No Violation button display in the Chat Log window");
			
			//Verify the No Violation  button display
			if (this.isElementVisible(btnNoViolation, 5))
				flag=true;
			else
				flag=false;
			if(flag)
			{	
				isVisible = true;
				logger.info("No Violation button display in Chat log window");
			}
			else
			{	isVisible = false;			}
			
		}catch(Exception e){
			logger.info("Failed to verify No Violation button display");
			isVisible=false;
		}
		return isVisible;		
	}
	
	//Verify display of Violation Confirmed button
	public boolean isViolationConfirmedButtonDisplayed(){
		boolean isVisible = false;
		boolean flag=false;
		try{
			logger.info("Verifying Violation Confirmed button display in the Chat Log window");
			
			//Verify the  Violation Confirmed button display
			if (this.isElementVisible(btnViolationConfirmed, 6))
				flag=true;
			else
				flag=false;
			if(flag)
			{	
				isVisible = true;
				logger.info("Violation Confirmed button display in Chat log window");
			}
			else
			{	isVisible = false;			}
			
		}catch(Exception e){
			logger.info("Failed to verify Violation Confirmed button display");
			isVisible=false;
		}
		return isVisible;		
	}	
	
	//Select all check boxes 
	public void selectAllCheckBoxesInChatLog()
	{
		try{
			logger.info("Check all check boxes in the Chat Log window");
			this.selectAllCheckBoxes(violationChkBoxes);
		}catch (Exception e) {
			logger.info("Failed to Click Check box in Chat log window");
		}
			
	}
	
	//Close Chat Log window	
	public void closeModalWindow()
	{
		try{
			logger.info("Closing modal window");
			this.click(btnClose);
			Thread.sleep(2000);
		}catch (Exception e) {
			logger.info("Failed to close content modal window");
		}
			
	}	
	
	
		
}
