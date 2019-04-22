package com.ea.wwce.automation.eahelp.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class EAHelpContactUsPage extends EAHelpBasePageObject {

	public EAHelpContactUsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private static final Logger logger = Logger.getLogger(EAHelpHomePage.class);
	
	
	
	

}
