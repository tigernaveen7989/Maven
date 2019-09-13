package com.shell.markethub.uam.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SubmitAndConfirmPage extends UAMBasePageObject{

	public SubmitAndConfirmPage() {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(SubmitAndConfirmPage.class);
	@FindBy(xpath = "//h4[contains(text(),'Registration Successful')]")
	WebElement registrationSuccessfulText;
	@FindBy(xpath = "//h4[contains(text(),'User data successfully updated')]")
	WebElement userDataSuccessfullyUpdatedText;
	
	public String getRegistrationSuccessfulText() throws Exception {
		return getText(registrationSuccessfulText);
	}
	
	public String getUserDataSuccessfullyUpdatedText() throws Exception {
		return getText(userDataSuccessfullyUpdatedText);
	}
}
