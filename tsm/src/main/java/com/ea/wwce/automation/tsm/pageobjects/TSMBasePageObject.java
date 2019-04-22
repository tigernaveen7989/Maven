package com.ea.wwce.automation.tsm.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;

import com.ea.wwce.automation.base.pageobjects.BasePageObject;

/**
 * @author rgandham 
 * @description Base Page object for TSM application
 */

public class TSMBasePageObject extends BasePageObject {

	public final static String RESPONSE_PROPERTY = "response";
	private static final Logger logger = Logger.getLogger(TSMBasePageObject.class);

	public TSMBasePageObject(WebDriver driver) {
		super(driver);
	}

	// This method returns month number to String
	public static String getMonthInString(String strMonthNumber) {
		String strMonth = null;
		switch (strMonthNumber) {
		case "01":
			strMonth = "JAN";
			break;
		case "02":
			strMonth = "FEB";
			break;
		case "03":
			strMonth = "MAR";
			break;
		case "04":
			strMonth = "APR";
			break;
		case "05":
			strMonth = "MAY";
			break;
		case "06":
			strMonth = "JUN";
			break;
		case "07":
			strMonth = "JUL";
			break;
		case "08":
			strMonth = "AUG";
			break;
		case "09":
			strMonth = "SEP";
			break;
		case "10":
			strMonth = "OCT";
			break;
		case "11":
			strMonth = "NOV";
			break;
		case "12":
			strMonth = "DEC";
			break;
		default:
			logger.error("Input month number is out of range or Invalid");
			throw new InvalidArgumentException("Input month number is out of range or Invalid");
		}
		return strMonth;
	}
}
