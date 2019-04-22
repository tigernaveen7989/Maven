package com.ea.wwce.automation.eahelp.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;

/**
 * 
 * @author sadabala
 *
 */
public class EAHelpCSATSurveyPage extends EAHelpBasePageObject {

	public EAHelpCSATSurveyPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(EAHelpHomePage.class);

	@FindBy(css = "input#isServiceAvalaible+div div.radio-group input:first-child")
	WebElement qToDecideWhatGameToBuy;
	@FindBy(css = "div.row.productSelectQuestion select+div")
	WebElement productlist;
	@FindBy(css = "div.row.productSelectQuestion select+div input")
	WebElement txtProduct;
	@FindBy(css = "div.questions.voc-fields.row div.row:nth-child(6) input:first-child")
	WebElement qAccountSecurity;
	@FindBy(css = "div.questions.voc-fields.row div.row:nth-child(7) input:first-child")
	WebElement optionYes;
	@FindBy(css = "div.questions.voc-fields.row div.row:nth-child(8) input:first-child")
	WebElement qSolveIssue;
	@FindBy(css = "div.questions.voc-fields.row div.row:nth-child(9) input:first-child")
	WebElement qHowEasy;
	@FindBy(css = "div.questions.voc-fields.row div.row:nth-child(10) input:first-child")
	WebElement qSatisFied;
	@FindBy(css = "div.questions.voc-fields.row div.row:nth-child(11) input:first-child")
	WebElement qyourExperience;
	@FindBy(css = "textarea.field")
	WebElement txtFeedback;
	@FindBy(css = "#submit-survey")
	WebElement submitbutton;
	@FindBy(css = "div.thank-you")
	WebElement confirmation;

	
	@Step("Submit CSAT survey form")
	public boolean submitCSATSurveyForm(String productName, String feedback) {
		boolean isTrue = false;

		try {

			logger.info("Select survey options and submit the form");
			this.click(qToDecideWhatGameToBuy);
			this.click(productlist);
			this.sendKeys(txtProduct, productName);
			this.pressEnterKey(txtProduct);
			this.click(qAccountSecurity);
			this.click(optionYes);
			this.click(qSolveIssue);
			this.click(qHowEasy);
			this.click(qSatisFied);
			this.click(qyourExperience);
			this.sendKeys(txtFeedback, feedback);			
			this.click(submitbutton);
			isTrue = this.isElementVisible(confirmation, 60);

		} catch (WebDriverException e) {
			logger.warn("Fail to submit CSAT survey form");
		} catch (Exception e) {
			logger.warn("Error submitting survey form");
		}
		return isTrue;
	}
	
	
	@Step("Get width of feedback text box")
	public String verifyFeedbackTextBoxWidth(){
		logger.info("Get width of feedback text box");
		return this.getCSSValue(txtFeedback,"width");		
	}

}
