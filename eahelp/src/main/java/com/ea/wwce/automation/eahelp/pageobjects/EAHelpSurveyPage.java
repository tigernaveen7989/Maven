package com.ea.wwce.automation.eahelp.pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.eahelp.config.EAHELPDataConstants;
//import com.ea.wwce.automation.omega.config.OmegaDataConstants;

import io.qameta.allure.Step;

/**
 * 
 * @author rdronamraju
 * @description EA HELP Login page object
 */

public class EAHelpSurveyPage extends EAHelpBasePageObject {

	public EAHelpSurveyPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(EAHelpHomePage.class);

	private static final boolean String = false;

	@FindBy(xpath = "//button[@id='nextPageLink']")
	WebElement btnSubmit;
	@FindBy(xpath = "(//span[contains(@class,'text')])[1]//following-sibling::td[2]")
	WebElement text;	
	@FindBy(xpath = "//div[contains(text(),'EA - Customer Satisfaction Survey')]")
	WebElement inHouseheader;	
	@FindBy(xpath = "//textarea[@class='field ']")
	WebElement txtArea1;
	@FindBy(xpath = "//textarea[@id='commentArea']")
	WebElement txtArea2;	
	@FindBy(xpath = "//button[@id='submit-survey']")
	WebElement btnSubmit1;
	@FindBy(xpath = "//a[text()='Complete']")
	WebElement btnComplete;
	@FindBy(css = "[class='title']")
	WebElement header;	
	@FindBy(xpath = "//iframe[@id='postChatFrame']")
	WebElement iframe;
	@FindBy(xpath = "//center[contains(text(),'Thank You!')]")
	WebElement txtfeedback;
	@FindBy(xpath = "//div[@class='logoContainer']")
	WebElement inMomentheader;	
			
	String inmomentqa="//td[contains(text(),'#')]/ancestor::div[contains(@id,'prompt_')]//div[@class='promptInput multipleChoiceInput']/div//label[text()='#']";
	String inmomentqa1="//td[contains(text(),'#')]/ancestor::div[contains(@id,'prompt_')]/div[@class='promptInput multipleChoiceInput']//div[contains(text(),'#')]";
	String inhouseqa="//div[contains(@class,'row')]/strong[contains(text(),'#')]/..//following-sibling::span[contains(text(),'#')]";
	String inmomentqa2="(//div[@id='promptArea']/div[contains(@class,'promptContainer')]//div[contains(@class,'multipleChoiceInput')])[#]//label";
	String questions;
	String inMomentQuestions="//td[contains(text(),'?')]";
	String inHouseQuestions = "(//div[contains(@class,'row')]/strong)[#]/..//following-sibling::span";
	String inHouseQuestions1 = "//div[contains(@class,'row')]/strong";
	
	//submit Inmoment survey 
	public void isInMomentSurveySubmitted(String text) throws Exception{
			logger.info("Select InMoment survey answer");
		try {
			Thread.sleep(3000);
			// String pageTitle = this.getPageTitle();
			this.waitAndSwitchToIframe(10, iframe);
			this.waitForClickableElement(20, inMomentheader);

			Thread.sleep(5000);
			List<WebElement> inMomentsQues = this.getWebElementsFrmList(inMomentQuestions);
			for (int i = 0; i < inMomentsQues.size(); i++) {
				String inMomentAns = this.getDynamicElements(inmomentqa2, i + 1 + "");
				List<WebElement> answers = this.getWebElementsFrmList(inMomentAns);
				for (int j = 0; j < answers.size(); j++) {
					try {
						if (i < 2 && answers.size() == 2) {
							this.clickOnDynamicElements(inmomentqa, inMomentsQues.get(i).getText(),
									answers.get(j).getText());
						} else if (answers.size() == 5) {
							this.clickOnDynamicElements(inmomentqa1, inMomentsQues.get(i).getText(),
									answers.get(j).getText());
							this.windowScrollDwn();
						} else if (i >= 2 && answers.size() == 2) {
							String e1 = this.getDynamicElements(inmomentqa, inMomentsQues.get(i).getText(),
									answers.get(j).getText());
							List<WebElement> list = this.getWebElementsFrmList(e1);
							list.get(1).click();
						}
					} catch (Exception e) {
					}
					break;
				}
			}
			try{
				this.click(txtArea2);
				this.sendKeys(txtArea2, text);
			}catch(NoSuchElementException e){
				logger.info("failed to find the element" + e.getMessage());
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

	//submit InHouse Survey
	public boolean isInHouseSurveySubmitted(String text) throws InterruptedException {
			logger.info("Select InHouse survey answer");
			boolean isElementPresent = false;
			try {
				Thread.sleep(2000);
				List<WebElement> inHouseQuesList = this.getWebElementsFrmList(inHouseQuestions1);
				for(int i = 0; i < inHouseQuesList.size(); i++) {
				String inHouseAns = this.getDynamicElements(inHouseQuestions, i + 1 + "");
				List<WebElement> answers = this.getWebElementsFrmList(inHouseAns);
				for (int j = 0; j < answers.size(); j++) {
					this.clickOnDynamicElements(inhouseqa, inHouseQuesList.get(i).getText().replace(" *", ""),answers.get(j+1).getText());
					Thread.sleep(2000);
					break;
				}
			}
			this.windowScrollDwn();
			try{
				this.click(txtArea1);
				this.sendKeys(txtArea1, text);
			}catch(NoSuchElementException ex){
				logger.info("failed to find the element" + ex.getMessage());
			}
			this.click(btnSubmit1);
			Thread.sleep(2000);
			isElementPresent = 	this.isElementVisible(btnComplete, 5);
			this.click(btnComplete);
			
			} catch (NoSuchElementException ex) {
				logger.info("failed to find the element" + ex.getMessage());
			} catch (TimeoutException ex) {
				logger.info("failed to find the element within time " + ex.getMessage());
			} catch (Exception e) {
				logger.warn("Search Question " + e);
			}			
			return isElementPresent;
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

