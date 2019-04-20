package com.pack.common.pageobjects;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.pack.functionlibrary.FunctionLibrary;
import com.pack.functionlibrary.FunctionLibrary.locatorType;

public class HomePage {
	FunctionLibrary globalfunctions=new FunctionLibrary();
	private WebDriver driver;
	List<WebElement> emailList;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
	}
	
	public String getPageTitle() {
		String title = driver.getTitle();
		return title;
	}
	
	public boolean verifyPageTitle() throws Exception{
		String pageTitle = "Verizon Mail";
		System.out.println(getPageTitle());
		globalfunctions.waitForElement(driver, locatorType.ID, "Gmail_HomePage_Compose_Button", 25);
		return getPageTitle().contains(pageTitle);
	}
	
	public void composeMail(String ToEmail, String Subject, String Message) throws Exception{
		clickCompose();
		enterEmail(ToEmail);
		enterSubject(Subject);
		enterMessage(Message);
		clickSend();
	}
	
	public void enterEmail(String email) throws Exception {
		WebElement emailEditbox = driver.findElement(By.className(globalfunctions.getObjectRepository().getProperty("Gmail_HomePage_EmailEditBox")));
		if(emailEditbox.isDisplayed()){
			emailEditbox.sendKeys(email);
			globalfunctions.screenShot(driver, "Entered Email Successfully");
		}			
	}
	
	public void enterSubject(String subject) throws Exception {
		WebElement subjectEditbox = driver.findElement(By.className(globalfunctions.getObjectRepository().getProperty("Gmail_HomePage_Subject_Edit_Box")));
		if(subjectEditbox.isDisplayed())
			subjectEditbox.sendKeys(subject);
		globalfunctions.screenShot(driver, "Entered Subject Successfully");
	}
	
	public void enterMessage(String message) throws Exception{
		WebElement messageEditbox = driver.findElement(By.xpath(globalfunctions.getObjectRepository().getProperty("Gmail_HomePage_Message_Edit_Box")));
		if(messageEditbox.isDisplayed()){
			messageEditbox.click();
			messageEditbox.clear();
			messageEditbox.sendKeys(message);
			globalfunctions.screenShot(driver, "Entered Message Successfully");
			System.out.println("Send email is successful");
		}
	}
	
	public void clickSend() throws Exception{
		WebElement sendBtn = driver.findElement(By.xpath(globalfunctions.getObjectRepository().getProperty("Gmail_HomePage_Send_Button")));
		if(sendBtn.isDisplayed()){
			sendBtn.click();
			Thread.sleep(5000);
		}			
	}
	
	public void clickCompose() throws Exception{
		WebElement composeBtn = driver.findElement(By.id(globalfunctions.getObjectRepository().getProperty("Gmail_HomePage_Compose_Button")));
		if(composeBtn.isDisplayed())
			composeBtn.click();
	}
	
	public void clickUnReadEmail(String Subject) throws Exception {
		emailList = driver.findElements(By.cssSelector(globalfunctions.getObjectRepository().getProperty("Gmail_HomePage_Email_List")));
		for(WebElement emailsub : emailList){
		    if(emailsub.getText().equals(Subject) == true){
		    	emailsub.click();
		    	Thread.sleep(3000);;
				globalfunctions.screenShot(driver, "Read Email Successfully");
		    	System.out.println("Read email is successful");
		    	Thread.sleep(5000);
		    	break;
		    }
		}
	}
	
	public HomePage clickSignOut() throws Exception{
		WebElement signOutBtn = driver.findElement(By.xpath(globalfunctions.getObjectRepository().getProperty("Gmail_HomePage_SignOut_Button")));
		if(signOutBtn.isDisplayed()||signOutBtn.isEnabled()){
			signOutBtn.click();
			globalfunctions.implicitWait(driver, 30);
			globalfunctions.screenShot(driver, "Signed Out Successfully");
		}		
		else System.out.println("Element not found");	
		return new HomePage(driver);
	}
	
	public void clickAccount() throws Exception{
		globalfunctions.waitForElementWithPollingInterval(driver, locatorType.XPATH, "Gmail_HomePage_Account_Link", 1, 20);
		WebElement accountLink = driver.findElement(By.xpath(globalfunctions.getObjectRepository().getProperty("Gmail_HomePage_Account_Link")));
		if(accountLink.isDisplayed())
			accountLink.click();
	}
}
