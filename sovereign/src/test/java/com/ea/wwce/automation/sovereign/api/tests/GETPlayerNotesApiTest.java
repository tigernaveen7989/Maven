package com.ea.wwce.automation.sovereign.api.tests;

import java.util.HashMap;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import io.qameta.allure.Description;

public class GETPlayerNotesApiTest extends SovereignAPIBaseTest {

	public static Logger logger = Logger.getLogger(GETAddressByAddressIdApiTest.class);
	DataProviders assertionProvider;

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		context.setAttribute("API_KEY", "GET_PLAYER_NOTES_API");
		super.beforeMethod(context);
		assertionProvider = new DataProviders(context.getAttribute("assertion_path").toString());
	}

	@Test(description = "verify Player Notes API", groups = { "Sanity" })
	@Description("Verification of player notes API")
	public void verifyPlayerNotes(ITestContext context) {

		// Map the test ID to the automation result for automated updated in TestRail
		String testCaseID = "49281";
		context.setAttribute("testcase_id", testCaseID);
		base.loadAPIInfomation(testCaseID);
		logger.info("validating Two factor Authentication Test" + testCaseID);

		base.validateResponse();
		this.assertEquals(200, base.getResponseCode(), "Matching response code as 200");

		// Load the assertions needed for the test
		HashMap<String, Object> assertions = (HashMap<String, Object>) assertionProvider.getTestData(testCaseID,
				"ASSERTIONS");
		
		// Verify assertions
		assertTrue(base.getResponseData().contains(assertions.get("advisorEmail_1").toString()),"Verify advisorEmail_1 is as expected");
		assertTrue(base.getResponseData().contains(assertions.get("noteDetail_1").toString()),"Verify noteDetail_1 is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("noteDate_1").toString()),"Verify noteDate_1 is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("source_1").toString()),"Verify source_1 is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("accountType_1").toString()),"Verify accountType_1 is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("playerNoteId_1").toString()),"Verify playerNoteId_1 is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("advisorEmail_2").toString()),"Verify advisorEmail_2 is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("noteDetail_2").toString()),"Verify noteDetail_2 is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("noteDate_2").toString()),"Verify noteDate_2 is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("source_2").toString()),"Verify source_2 is as exepected");
		assertTrue(base.getResponseData().contains(assertions.get("playerNoteId").toString()),"Verify playerNoteId is as exepected");
		assertAll();

	}

}
