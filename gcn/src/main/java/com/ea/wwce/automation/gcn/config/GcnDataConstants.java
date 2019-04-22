package com.ea.wwce.automation.gcn.config;

import com.ea.wwce.automation.base.config.BaseDataConstants;

public class GcnDataConstants extends BaseDataConstants{
	
	// Configure Timeouts
	public static final int PAGE_LOAD_TIMEOUT=30;
	public static final int IMPLICIT_TIMEOUT=10;
	
	// Test Data Configuration Path
	public static final String GCN_TEST_DATA_ROOT_PATH="gcn\\src\\test\\resources\\testdata\\";
	public static final String GCN_TEST_DATA_FILE_NAME="testdata.json";
	
	// Opportunity Images Folder path
	public static final String GCN_TEST_OPP_IMAGES="gcn\\src\\test\\resources\\images\\opp\\";
	public static final String GCN_TEST_PPICT_IMAGES="gcn\\src\\test\\resources\\images\\ppict\\";
	public static final String GCN_TEST_TAX_FILES="gcn\\src\\test\\resources\\images\\taxdoc\\";
	public static final String GCN_TEST_RECEIPT_IMAGES="gcn\\src\\test\\resources\\images\\receipts\\";
	
	// Constant URL Strings for QA Admin Site
	public static final String QA_ADMIN_HOME_BASE_URL="https://qa-admin.gamechangers.ea.com";
	public static final String QA_ADMIN_HOME_URL=QA_ADMIN_HOME_BASE_URL+"/?r";
	public static final String QA_ADMIN_OPPO_URL=QA_ADMIN_HOME_BASE_URL+"/?r=site/opportunities";
	public static final String QA_ADMIN_USER_URL=QA_ADMIN_HOME_BASE_URL+"/?r=site/users";
	public static final String QA_ADMIN_CONT_URL=QA_ADMIN_HOME_BASE_URL+"/?r=site/content";
	public static final String QA_ADMIN_REPO_URL=QA_ADMIN_HOME_BASE_URL+"/?r=site/reports";
	public static final String QA_ADMIN_OPER_URL=QA_ADMIN_HOME_BASE_URL+"/?r=site/operations";
	
	
	// Constat URL Strings for GameChanger site - footer links
	
	public static final String EA_FOOTER_BASE_URL="https://www.ea.com";
	public static final String EA_LEGAL=EA_FOOTER_BASE_URL+"/legal-notices";
	public static final String EA_EULA=EA_FOOTER_BASE_URL+"/legal";
	public static final String EA_SERVICE_UPDATES=EA_FOOTER_BASE_URL+"/service-updates";
	public static final String EA_TERMS="http://tos.ea.com/legalapp/WEBTERMS/US/en/PC/";
	public static final String EA_PRIVACY=EA_FOOTER_BASE_URL+"/legal/privacy-policy";
	
	// Constant Strings
	public static final String eaAdminFooterText="© 2019 Electronic Arts Inc. All Rights Reserved";
	public static final String eaFooterText="Powered By";
	public static final String paySetMsg1="Click “Payment Settings” to let us know how you’d like your stipend to be paid for this Opportunity.";
	public static final String paySetMsg2="Update your Profile -> Payment Settings page anytime your name, address, or any other payment information changes.";
	
	// Constant Title Strings
	// Page title for Login window.
	public static final String actAdminLoginTitle="Log In";
	// Page title for Admin logged in page
	public static final String actAdminTitle="Game Changers Network - Site";
	// Page title for Admin Payments page;
	public static final String actAdminPaymentPageTitle="Game Changers Network - Site";
	
	// Page title for GC site logged in page
	public static final String actGcTitle="Game Changers Network - Site";
	
	//Page title for GC Profile Page
	public static final String actGcProfilePageTitle="Game Changers Network - Profile Site";
	//Page title for GC Profile Payment Settings Page
	public static final String actGcPaymentSettingsPageTitle="Game Changers Network -  Site";
	
	//Page title for Admin-Opportunity-BudgetEstimation Page
	public static final String actBudgetPageTitle="Game Changers Network - Site";
	
	// Payment Success message
	public static final String paySuccessM="Thank you. The payment request has been submitted and you can track the progress of it below.";
	// Admin Budget Estimation Sussess Message
	public static final String budgetEstSuccessMsg="Thank you. The opportunity budget estimation has been updated.";
	
	// List of Banned Countries for GCN
	public static final String[] bannedCountries= {"Cuba", "Iran", "North Korea", "Myanmar", "Sudan", "South Sudan", "Syrian Arab Republic"};
	
	// Environment URL's
	public static final String GCN_BASE_URL_ADMIN_QAPC="https://qa-admin.gamechangers.ea.com/";
	
	public static final String GCN_BASE_URL_GC_QAPC="https://qa.gamechangers.ea.com/";
	
	public static final String GCN_BASE_URL_RUNFC="";
	
	public static final String GCN_BASE_URL_UATPC="";
	
	public static final String GCN_BASE_URL_ADMIN_PROD="";
	
	public static final String GCN_BASE_URL_GC_PROD="";
	
	public static String GCN_ADMIN_AUT_URL="";
	public static String GCN_GC_AUT_URL="";
	
	// Configuration for API Tests
	
	//Pick the payload file from below location
	public static final String GCN_INPUT_FILE_ROOT = "\\src\\test\\resources\\apiPayLoads\\REST\\request\\";

	//Place the response file to below location
	public static final String GCN_OUTPUT_FILE_ROOT = "\\src\\test\\resources\\apiPayLoads\\REST\\response\\";
	//Api Assertion file in below location
	public static final String GCN_API_ASSERTION_ROOT = "\\src\\test\\resources\\apiPayLoads\\REST\\assertions\\";
	//Place the token file payload to below location
	public static final String GCN_TOKEN_FILE_PATH= "\\src\\test\\resources\\apiPayLoads\\REST\\token\\AUTH_TOKEN.json";
	public static final String GCN_TOKEN_URL = "/oauth/token";

	//Pick the master configuration of GCN APIs from below location
	public static final String API_MASTER_CONFIG_FILE_PATH = "\\src\\test\\resources\\config\\api_config.json";

	//Configure base service URI
	public static final String GCN_SERVICE_BASE_URL_QAPC = "https://gcn-javaservices-preprod-1858686905.us-east-1.elb.amazonaws.com/gcn-commerce";

	public static final String GCN_SERVICE_BASE_URL_RUNFC =  "";

	public static final String GCN_SERVICE_BASE_URL_UATPC = "";

	public static final String GCN_SERVICE_BASE_URL_PROD = "";

	public static String GCN_SERVICE_BASE_URL = "";

	public static String AUTH_TOKEN="";

	public static int API_TOKEN_EXPIRY_WINDOW = 0;

	public static int API_TOKEN_EXPIRY_END_TIME = 0;
	
	//TestRail Configuration

	public static final String GCN_TESTRAIL_CONNECTION_URL = "https://e2qa.testrail.io";
	public static final String GCN_TESTRAIL_USERNAME = "bbehera@contractor.ea.com";
	public static final String GCN_TESTRAIL_PASSWORD = "Balaram12!@";
	
	

}
