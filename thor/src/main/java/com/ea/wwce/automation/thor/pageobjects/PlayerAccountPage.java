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
 * @author mohan
 * @description Thor Player Account page object
 */

public class PlayerAccountPage extends ThorBasePageObject{
	
	public PlayerAccountPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver,this);
	}
	
    private static final Logger logger = Logger.getLogger(PlayerAccountPage.class);
    @FindBy(xpath="(//div[@ id='productAccStatus']/input[contains(@class,'slds-lookup__search-input')])[2]")
	WebElement txtSearch;
    String itemName="(//span[@title='#'])[1]";
    @FindBy(xpath="(//span[@title='FIFA'])[1]")
   	WebElement searchItemFifa;
    @FindBy(xpath="(//input[(@name='progress')])[2]")
	WebElement txtStatusType;
    @FindBy(xpath="(//lightning-base-combobox-formatted-text[@title='Franchise'])[1]")
    WebElement franchiseStatusType;
    @FindBy(xpath="(//lightning-base-combobox-formatted-text[@title='Product'])[1]")
    WebElement productStatusType;
    @FindBy(xpath="(//div[@class='accstatus slds-scrollable_y']/table)[2]/tr")
    WebElement acctStatusDetailTblRow;
    //@FindAll(@FindBy(css=".slds-grid.slds-wrap.cAccountDetail .accstatus.slds-scrollable_y table tr"))
    @FindAll(@FindBy(xpath="(//div[@class='accstatus slds-scrollable_y']/table)[2]/tr"))
	List<WebElement> acctStatusDetailTblRows;
    String statusDetailCellxpath="(//div[@class='accstatus slds-scrollable_y']/table)[2]/tr[%d]/td[%d]";
    @FindBy(xpath="(//div[@title='ID'])[2]")
    WebElement tblHeader;
    
    @FindBy(xpath="(//h1[contains(text(),'Target Player Details')]/following::div[contains(text(),'Nucleus Persona')])[1]")
	WebElement lblNucleusPersona;
	@FindBy(xpath="(//h1[contains(text(),'Target Player Details')]/following::div[contains(text(),'Nucleus Persona ID')])[1]")
	WebElement lblNucleusPersonaId;
	@FindBy(xpath="(//h1[contains(text(),'Target Player Details')]/following::div[contains(text(),'Customer Language')])[1]")
	WebElement lblCustomerLan;
	@FindBy(xpath="(//h1[contains(text(),'Target Player Details')]/following::div[contains(text(),'Customer Age')])[1]")
	WebElement lblCustomerAge;
	@FindBy(xpath="(//h1[contains(text(),'Target Player Details')]/following::div[contains(text(),'Nucleus Account ID')])[1]")
	WebElement lblNucleusAcctID;
	@FindBy(xpath="(//h1[contains(text(),'Target Player Details')]/following::div[contains(text(),'Account Age')])[1]")
	WebElement lblAccountAge;
	@FindBy(xpath="(//h1[contains(text(),'Target Player Details')]/following::div[contains(text(),'Nucleus Account Status')])[1]")
	WebElement lblNucleusAcctSts;
	@FindBy(xpath="(//h1[contains(text(),'Target Player Details')]/following::div[contains(text(),'Security State')])[1]")
	WebElement lblSecurityState;
	@FindBy(xpath="(//h1[contains(text(),'Target Player Details')]/following::div[contains(text(),'Email ID')])[1]")
	WebElement lblEmailID;
	
	@FindBy(xpath="(//h1[contains(text(),'Target Player Details')]/following::div[contains(text(),'Nucleus Persona')])[1]/following-sibling::div")
	WebElement txtNucleusPersona;
	@FindBy(xpath="(//h1[contains(text(),'Target Player Details')]/following::div[contains(text(),'Nucleus Persona ID')])[1]/following-sibling::div")
	WebElement txtNucleusPersonaId;
	@FindBy(xpath="(//h1[contains(text(),'Target Player Details')]/following::div[contains(text(),'Customer Language')])[1]/following-sibling::div")
	WebElement txtCustomerLan;
	@FindBy(xpath="(//h1[contains(text(),'Target Player Details')]/following::div[contains(text(),'Customer Age')])[1]/following-sibling::div")
	WebElement txtCustomerAge;
	@FindBy(xpath="(//h1[contains(text(),'Target Player Details')]/following::div[contains(text(),'Nucleus Account ID')])[1]/following-sibling::div")
	WebElement txtNucleusAcctID;
	@FindBy(xpath="(//h1[contains(text(),'Target Player Details')]/following::div[contains(text(),'Account Age')])[1]/following-sibling::div")
	WebElement txtAccountAge;
	@FindBy(xpath="(//h1[contains(text(),'Target Player Details')]/following::div[contains(text(),'Nucleus Account Status')])[1]/following-sibling::div")
	WebElement txtNucleusAcctSts;
	@FindBy(xpath="(//h1[contains(text(),'Target Player Details')]/following::div[contains(text(),'Security State')])[1]/following-sibling::div")
	WebElement txtSecurityState;
	@FindBy(xpath="(//h1[contains(text(),'Target Player Details')]/following::div[contains(text(),'Email ID')])[1]/following-sibling::div")
	WebElement txtEmailID;
	
	@FindAll(@FindBy(xpath="(//div[contains(@class,'cPersonaGridTableExpand ')])"))
	List<WebElement> playerPersonaTblRows;
   // String playerPersonasCellxpath="(//table[contains(@class,'slds-table  slds-table_cell-buffer')])[1]/tr[%d]/td[%d]";
	@FindBy(xpath="(//div[contains(@class,'target-account-notes')]/div[1]/div[2]/div/div[1]/p)[2]")
	WebElement latestAcctNotes;
	
	// Method to Verify Target Player Account Details 
	@Step("Method to verify Target Player Account Details")
	public String verifyTargetPlayerDetails() {
		String strText="";
		String str1,str2,str3,str4,str5,str6,str7,str8,str9;
		str1="";str2="";str3="";str4="";str5="";str6="";str7="";str8="";str9="";
		try {
				
			this.waitForElementToBeVisible(latestAcctNotes, 10);
			boolean f1=this.isElementVisible(lblNucleusPersona, 2);
			boolean f2=this.isElementVisible(txtNucleusPersona, 2);
			if (f1 && f2)
				str1=this.getText(txtNucleusPersona);
			
			boolean f3=this.isElementVisible(lblNucleusPersonaId, 2);
			boolean f4=this.isElementVisible(txtNucleusPersonaId, 2);
			if (f3 && f4)
				str2=this.getText(txtNucleusPersonaId);
			
			boolean f5=this.isElementVisible(lblCustomerLan, 2);
			boolean f6=this.isElementVisible(txtCustomerLan, 2);
			if (f5 && f6)
				 str3=this.getText(txtCustomerLan);
			
			boolean f7=this.isElementVisible(lblCustomerAge, 2);
			boolean f8=this.isElementVisible(txtCustomerAge, 2);
			if (f7 && f8)
				 str4=this.getText(txtCustomerAge);
			
			boolean f9=this.isElementVisible(lblNucleusAcctID, 2);
			boolean f10=this.isElementVisible(txtNucleusAcctID, 2);
			if (f9 && f10)
				str5=this.getText(txtNucleusAcctID);
			
			boolean f11=this.isElementVisible(lblAccountAge, 2);
			boolean f12=this.isElementVisible(txtAccountAge, 2);
			if (f11 && f12)
				 str6=this.getText(txtAccountAge);
			
			boolean f13=this.isElementVisible(lblNucleusAcctSts, 2);
			boolean f14=this.isElementVisible(txtNucleusAcctSts, 2);
			if (f13 && f14)
				 str7=this.getText(txtNucleusAcctSts);
			
			boolean f15=this.isElementVisible(lblSecurityState, 2);
			boolean f16=this.isElementVisible(txtSecurityState, 2);
			if (f15 && f16)
				 str8=this.getText(txtSecurityState);
			
			boolean f17=this.isElementVisible(lblEmailID, 2);
			boolean f18=this.isElementVisible(txtEmailID, 2);
			if (f17 && f18)
				 str9=this.getText(txtEmailID);
			
			if(f2&&f4&&f6&&f8&&f10&&f12&&f14&&f16&&f18)
			strText=str1+","+str2+","+str3+","+str4+","+str5+","+str6+","+str7+","+str8+","+str9;
		}
		catch (Exception e) {
			logger.info("Failed to Verify Target Player Details  " + e.getMessage());
		}
		return strText;
	}
	
    
	//Verify Account status Type values
    @Step("Verify Stastus Type Values")
    public boolean verifyStatusTypeValues(){
    	boolean flag1=false;
    	boolean flag2=false;
    	try{
			logger.info("Verify Product details for Franchise");
			//Click on Status Type 
			this.waitForElementToBeVisible(txtStatusType, 10);
			this.click(txtStatusType);
			Thread.sleep(2000);
			//Verify the existance of Product and Franchise
			flag1=this.isElementVisible(productStatusType, 3);
			flag2=this.isElementVisible(franchiseStatusType, 3);
			this.click(txtStatusType);
    	}catch(NoSuchElementException e){
			logger.warn("Failed to Verify Status Type drop down" + e.getMessage());
		}catch(Exception e){
			logger.warn("Failed to Verify Status Type drop down" + e.getMessage());			
		}
    	if ((flag1) && (flag2))
    		return true;
    	else
    		return false;
			
    }
    
   
    //Select Franchise Name and verify respective product details displayed or not		
	@Step("Select Franchise Name and Verify Product details")	
	public boolean selectFranchiseAndVerifyProducts(String franchisetName){
		boolean flag=true;
		try{
			logger.info("Select Franchise Name and Verify Product details");
			//Click on Status Type and select Franchise Option
			this.waitForElementToBeVisible(txtStatusType, 10);
			this.click(txtStatusType);
			Thread.sleep(2000);
			this.waitForElementToBeVisible(franchiseStatusType, 3);
			this.click(franchiseStatusType);
			//Type Franchise Value in lookup
			this.click(txtSearch);
			Thread.sleep(1000);
			//this.waitForElementToBeVisible(searchItemFifa, 3);
			//this.click(searchItemFifa);
			this.clickOnDynamicElement(itemName, franchisetName);
			//Verify Related products of Fifa
			//this.waitForElementToBeVisible(tblHeader,8);
			Thread.sleep(1000);
			int intRC=this.getSize(acctStatusDetailTblRows);
			
			for (int x = 1; x <= intRC-1; x++)
			{    
					String strName=this.getTableCellVal(statusDetailCellxpath,x,1);
					String strCurrentStatus=this.getTableCellVal(statusDetailCellxpath,x,3);
				    if (strName.isEmpty() || strCurrentStatus.isEmpty() )
				    { 	flag=false;
				    	break;
				    }
				    else
				    {	logger.info("Each "+strName+" display the Current status as "+strCurrentStatus);
				    	flag=true;
				    }
			}
			
		}catch(Exception e){
			logger.warn("Failed to get data from Account status detail table" + e.getMessage());			
		}
		if (flag)
			return	true;
		else
			return false;
	}
	
	public boolean verifyPlayerPersonaTable() {
		boolean flag=true;
		try
		{
			int intRC=this.getSize(playerPersonaTblRows);
			if (intRC>1)
				flag=true;
			else
			{	logger.info("Failed to display Player Persona Table, Table row cout showing  " + intRC+ " rows");
				flag=false;}
		}catch(Exception e){
			logger.warn("Failed to display data from player persona table" + e.getMessage());			
		}
		return flag;
	}
}
