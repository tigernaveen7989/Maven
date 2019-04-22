package com.ea.wwce.automation.gcn.intigration.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.gcn.tests.GcnBaseTest;

import io.qameta.allure.Description;

public class GcnGcOppJoiningTest extends GcnBaseTest{
	
	/*
	 * This Test Class is to verify the Joining an opportunity by Game Changers.
	 */

	public static Logger logger=Logger.getLogger(GcnGcOppJoiningTest.class);
	Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
	
	@Test
	@Description("Verify Joining and Opportunity by Game Changer")
	public void verifyGcJoiningOpportunity(ITestContext context) throws InterruptedException {
		
		Map<String,Object> testData=new HashMap<String,Object>();
		String testID="global";
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider=(DataProviders)context.getAttribute("dataProvider");
		testData=dataProvider.getTestData(testID);
		
		gcnGcHomePage.launchGcAndSignIn();
		gcnGcLoginPage.verifyLogin(testData.get("userGajagc1").toString(), testData.get("passGajagc1").toString());
		gcOpportunityPage=gcnGcPage.goToOpportunitiesPage();
		assertTrue(gcOpportunityPage.verifyNavigationToNotJoinedOpp(), "Error navigating into Not Joined Opportunity");
		
		gcOppDetailsPage.goToJoinPage();
		
		assertTrue(gcOppJoinPage.verifyNavigationToOppJoiningPage(), "Error navigating to Joining Page");
		
		assertTrue(gcOppJoinPage.verifyJoiningToOpportunity(testData.get("depAirp").toString(),
				testData.get("mob").toString(), testData.get("dob2").toString(), testData.get("yob").toString(),
				testData.get("fname2").toString(), testData.get("lname2").toString(), testData.get("passNum").toString(),
				testData.get("pExpM").toString(), testData.get("pExpD").toString(), testData.get("pExpY").toString(),
				testData.get("passIssCont").toString(), testData.get("phNum").toString()), "Error Joining Opportunity");
		
		// Save driver instance for First Game Changer
		mDriverInstance.put("GC1", this.driver);
		
		// Create driver instance for Second Game Changer
		this.driver=this.loadNewInstance(context);
		
		gcnGcHomePage.launchGcAndSignIn();
		gcnGcLoginPage.verifyLogin(testData.get("usernihalgc2").toString(), testData.get("passnihalgc2").toString());
		
		gcOpportunityPage=gcnGcPage.goToOpportunitiesPage();
		assertTrue(gcOpportunityPage.verifyNavigationToNotJoinedOpp(), "Error navigating into Not Joined Opportunity");
		
		gcOppDetailsPage.goToJoinPage();
		
		assertTrue(gcOppJoinPage.verifyNavigationToOppJoiningPage(), "Error navigating to Joining Page");
		
		assertTrue(gcOppJoinPage.verifyJoiningToOpportunity(testData.get("depAirp").toString(),
				testData.get("mob").toString(), testData.get("dob2").toString(), testData.get("yob").toString(),
				testData.get("fname2").toString(), testData.get("lname2").toString(), testData.get("passNum").toString(),
				testData.get("pExpM").toString(), testData.get("pExpD").toString(), testData.get("pExpY").toString(),
				testData.get("passIssCont").toString(), testData.get("phNum").toString()), "Error Joining Opportunity");
		
		// Save driver instance for Second Game Changer
		mDriverInstance.put("GC2", this.driver);
		
		// Create driver instance for Third Game Changer
		this.driver=this.loadNewInstance(context);

		gcnGcHomePage.launchGcAndSignIn();
		gcnGcLoginPage.verifyLogin(testData.get("userOp2").toString(), testData.get("PassOp2").toString());

		gcOpportunityPage=gcnGcPage.goToOpportunitiesPage();
		assertTrue(gcOpportunityPage.verifyNavigationToNotJoinedOpp(), "Error navigating into Not Joined Opportunity");

		gcOppDetailsPage.goToJoinPage();

		assertTrue(gcOppJoinPage.verifyNavigationToOppJoiningPage(), "Error navigating to Joining Page");

		assertTrue(gcOppJoinPage.verifyJoiningToOpportunity(testData.get("depAirp").toString(),
				testData.get("mob").toString(), testData.get("dob2").toString(), testData.get("yob").toString(),
				testData.get("fname2").toString(), testData.get("lname2").toString(), testData.get("passNum").toString(),
				testData.get("pExpM").toString(), testData.get("pExpD").toString(), testData.get("pExpY").toString(),
				testData.get("passIssCont").toString(), testData.get("phNum").toString()), "Error Joining Opportunity");

		// Save driver instance for Second Game Changer
		mDriverInstance.put("GC3", this.driver);
		
		
		// Create driver instance for Fourth Game Changer
		this.driver=this.loadNewInstance(context);

		gcnGcHomePage.launchGcAndSignIn();
		gcnGcLoginPage.verifyLogin(testData.get("userSham").toString(), testData.get("passSham").toString());

		gcOpportunityPage=gcnGcPage.goToOpportunitiesPage();
		assertTrue(gcOpportunityPage.verifyNavigationToNotJoinedOpp(), "Error navigating into Not Joined Opportunity");

		gcOppDetailsPage.goToJoinPage();

		assertTrue(gcOppJoinPage.verifyNavigationToOppJoiningPage(), "Error navigating to Joining Page");

		assertTrue(gcOppJoinPage.verifyJoiningToOpportunity(testData.get("depAirp").toString(),
				testData.get("mob").toString(), testData.get("dob2").toString(), testData.get("yob").toString(),
				testData.get("fname2").toString(), testData.get("lname2").toString(), testData.get("passNum").toString(),
				testData.get("pExpM").toString(), testData.get("pExpD").toString(), testData.get("pExpY").toString(),
				testData.get("passIssCont").toString(), testData.get("phNum").toString()), "Error Joining Opportunity");

		// Save driver instance for Second Game Changer
		mDriverInstance.put("GC4", this.driver);
		
		// Create driver instance for Fifth Game Changer
		this.driver=this.loadNewInstance(context);

		gcnGcHomePage.launchGcAndSignIn();
		gcnGcLoginPage.verifyLogin(testData.get("userNaresh").toString(), testData.get("passNaresh").toString());

		gcOpportunityPage=gcnGcPage.goToOpportunitiesPage();
		assertTrue(gcOpportunityPage.verifyNavigationToNotJoinedOpp(), "Error navigating into Not Joined Opportunity");

		gcOppDetailsPage.goToJoinPage();

		assertTrue(gcOppJoinPage.verifyNavigationToOppJoiningPage(), "Error navigating to Joining Page");

		assertTrue(gcOppJoinPage.verifyJoiningToOpportunity(testData.get("depAirp").toString(),
				testData.get("mob").toString(), testData.get("dob2").toString(), testData.get("yob").toString(),
				testData.get("fname2").toString(), testData.get("lname2").toString(), testData.get("passNum").toString(),
				testData.get("pExpM").toString(), testData.get("pExpD").toString(), testData.get("pExpY").toString(),
				testData.get("passIssCont").toString(), testData.get("phNum").toString()), "Error Joining Opportunity");

		// Save driver instance for Second Game Changer
		mDriverInstance.put("GC5", this.driver);
		
		// Create driver instance for Sixth Game Changer
		this.driver=this.loadNewInstance(context);

		gcnGcHomePage.launchGcAndSignIn();
		gcnGcLoginPage.verifyLogin(testData.get("userSanjeetgc3").toString(), testData.get("passSanjeetgc3").toString());

		gcOpportunityPage=gcnGcPage.goToOpportunitiesPage();
		assertTrue(gcOpportunityPage.verifyNavigationToNotJoinedOpp(), "Error navigating into Not Joined Opportunity");

		gcOppDetailsPage.goToJoinPage();

		assertTrue(gcOppJoinPage.verifyNavigationToOppJoiningPage(), "Error navigating to Joining Page");

		assertTrue(gcOppJoinPage.verifyJoiningToOpportunity(testData.get("depAirp").toString(),
				testData.get("mob").toString(), testData.get("dob2").toString(), testData.get("yob").toString(),
				testData.get("fname2").toString(), testData.get("lname2").toString(), testData.get("passNum").toString(),
				testData.get("pExpM").toString(), testData.get("pExpD").toString(), testData.get("pExpY").toString(),
				testData.get("passIssCont").toString(), testData.get("phNum").toString()), "Error Joining Opportunity");

		// Save driver instance for Second Game Changer
		mDriverInstance.put("GC6", this.driver);
		
		
		// Set driver instances to context
		//context.setAttribute("mDriverInstances", mDriverInstance);
		
		assertAll();
		
	}
}
