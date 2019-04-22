package com.ea.wwce.automation.ahq.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import com.ea.wwce.automation.ahq.config.AHQDataConstants;
import com.ea.wwce.automation.base.util.Driver;

/**
 * 
 * @author rdronamraju
 *
 */

public class AHQSignInPage extends AHQBasePageObject{
	
	public AHQSignInPage(WebDriver driver){
		super(driver);
	}
	
    private static final Logger logger = Logger.getLogger(AHQSignInPage.class);	

}
