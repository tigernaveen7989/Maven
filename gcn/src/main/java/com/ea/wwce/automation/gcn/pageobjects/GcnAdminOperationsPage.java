package com.ea.wwce.automation.gcn.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.gcn.config.GcnDataConstants;

import io.qameta.allure.Step;

public class GcnAdminOperationsPage extends GcnBasePageObjects{
	
	private static final Logger logger=Logger.getLogger(GcnAdminOperationsPage.class);
	
	public GcnAdminOperationsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@Step("Verify navigation into Operations Page")
	public boolean verifyNavigationToOperationsPage() {
		//mehtod to check landing into Operations Page.
		logger.info("Check landing into operations Page.");
		boolean a=false;
		String s=this.getCurrentPageURL();
		if(s.equals(GcnDataConstants.QA_ADMIN_OPER_URL)) {
			logger.info("Landed into Operations Page");
			a=true;
		}else {
			logger.info("Error in Operations Page");
		}
		return a;
	}

}
