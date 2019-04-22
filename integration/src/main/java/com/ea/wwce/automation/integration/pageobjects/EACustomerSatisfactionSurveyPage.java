package com.ea.wwce.automation.integration.pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;

/**
 * @author Naveen Kumar
 * 
 * Page Object for EACustomerSatisfactionSurveyPage
 **/
public class EACustomerSatisfactionSurveyPage extends IntegrationPageObject{

	public EACustomerSatisfactionSurveyPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(GmailLoginPage.class);
	
	@FindBy(xpath = "//textarea[@id='commentArea']")
	WebElement txtArea2;	
	@FindBy(xpath = "//button[@id='nextPageLink']")
	WebElement btnSubmit;
	@FindBy(xpath = "//center[contains(text(),'Thank You!')]")
	WebElement txtfeedback;
	
	String inMomentQuestions="//td[contains(text(),'?')]";	
	String inmomentqa="//td[contains(text(),'#')]/ancestor::div[contains(@id,'prompt')]//div[@class='promptInput multipleChoiceInput']/div//label[text()='#']";
	String inmomentqa1="//td[contains(text(),'#')]/ancestor::div[contains(@id,'prompt')]/div[@class='promptInput multipleChoiceInput']//div[contains(text(),'#')]";
	String inhouseqa="//div[contains(@class,'row')]/strong[contains(text(),'#')]/..//following-sibling::span[contains(text(),'#')]";
	String inmomentqa2="(//div[@id='promptArea']/div[contains(@class,'promptContainer')]//div[contains(@class,'multipleChoiceInput')])[#]//label";
	
	// submit Inmoment survey
	@Step("Submit In Moment Survey")
	public void isInMomentSurveySubmitted(String text) {
		logger.info("Select InMoment survey answer");
		try {
			Thread.sleep(13000);
			List<WebElement> inMomentsQues = this.getWebElementsFrmList(inMomentQuestions);
			for(int i=0; i<inMomentsQues.size();i++){
				String inMomentAns=	this.getDynamicElements(inmomentqa2, i+1+"");
				List<WebElement> answers = this.getWebElementsFrmList(inMomentAns);
				for(int j=0;j<answers.size();j++){
					try{
						if(i<2 && answers.size()==2){
							this.clickOnDynamicElements(inmomentqa, inMomentsQues.get(i).getText(), answers.get(j).getText());
						}else if(answers.size()==5){
							this.clickOnDynamicElements(inmomentqa1, inMomentsQues.get(i).getText(), answers.get(j).getText());
							this.windowScrollDwn();
						}else if(i>=2 && answers.size()==2){
							String e1= this.getDynamicElements(inmomentqa,inMomentsQues.get(i).getText(), answers.get(j).getText());
							List<WebElement> list = this.getWebElementsFrmList(e1);
							list.get(1).click();
						}
					}catch(Exception e){}
					break;
				}
			}
			try {
				this.click(txtArea2);
				this.sendKeys(txtArea2, text);
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.click(btnSubmit);

		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (TimeoutException ex) {
			logger.info("failed to find the element within time " + ex.getMessage());
		} catch (Exception e) {
			logger.warn("Search Question " + e);
		}
	}
	
	//@Step("Get Feedback text")
	public String getFeedback() {

		String getfeedbacktext = null;
		logger.info("get feedback text");
		try {
			this.waitForClickableElement(10, txtfeedback);
			getfeedbacktext = this.getText(txtfeedback);
			this.closeCurrentWindow();

		} catch (NoSuchElementException ex) {
			logger.info("failed to find the element" + ex.getMessage());
		} catch (NullPointerException e) {
			logger.warn("Element is null" + e);
		} catch (Exception e) {
			logger.warn("feedback notes text not fetched" + e);
		}
		return getfeedbacktext;
	}
}
