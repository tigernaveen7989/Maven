package com.ea.wwce.automation.base.util.testmanagement;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.ITestResult;

public class TestRailClient {
	/**
	 * This class will update the case status in testrail
	 * 
	 * @author sadabala
	 * @description Updates to TestRailClient
	 * @lastupdated rdronamraju - Search entry by test type passed from
	 *              Jenkins/TestNG and update the results.
	 * @param result
	 *            : org.testng.ITestResult
	 * @param url
	 *            : testrail url
	 * @param strUserName
	 *            : testrail username
	 * @param strpwd
	 *            : test rail password
	 * 
	 */

	private APIClient client;
	String planId = "";

	public static Logger logger = Logger.getLogger(TestRailClient.class);

	public TestRailClient(String url, String username, String password) {
		client = new APIClient(url, username, password);
	}

	/**
	 * Method to update test results in test rail
	 * 
	 * @param planId
	 * @param caseId
	 * @param browser
	 * @description Use this method to update the test result in TestRail using
	 *              listeners.
	 */
	public void addTestResult(ITestResult result) {
		String browser = null;
		String testCaseId = null;
		String runId = null;
		String config = null;
		JSONObject planDetails = null;
		JSONObject entryDetails = null;
		JSONObject runDetails = null;
		JSONArray entriesDetails = null;
		JSONArray runsDetails = null;

		LinkedHashMap<String, String> testDetails = new LinkedHashMap<>();

		try {
			// Load plan details
			String planId = result.getTestContext().getAttribute("test_plan_id").toString();
			planDetails = (JSONObject) client.sendGet("get_plan/" + planId + "");

			// Load the associated entry details like Sanity, Regression etc..
			entriesDetails = (JSONArray) planDetails.get("entries");

			// Scan through entries and pick entry over which run results need
			// to be updated.
			String suiteTypeTestNG = result.getTestContext().getCurrentXmlTest().getParameter("testType");

			for (int i = 0; i < entriesDetails.size(); i++) {

				entryDetails = (JSONObject) entriesDetails.get(i);

				String suiteTypeTestRail = entryDetails.get("name").toString();

				// if entry name matches with the suite type provided in TestNG,
				// proceed else search next entry
				if (suiteTypeTestRail.equalsIgnoreCase(suiteTypeTestNG)) {

					testDetails.put("TestRunName", entryDetails.get("name").toString());

					runsDetails = (JSONArray) entryDetails.get("runs");

					for (int j = 0; j < runsDetails.size(); j++) {
						runDetails = (JSONObject) runsDetails.get(j);

						runId = runDetails.get("id").toString();

						// Check if the testCaseId contains multiple values, if
						// yes update testresult across all of them.
						testCaseId = result.getTestContext().getAttribute("testcase_id").toString();
						List<String> testIds = new ArrayList<String>();

						if (testCaseId.contains(",")) {
							testIds = Arrays.asList(testCaseId.split(","));
						} else {
							testIds.add(testCaseId);
						}

						// Iterate over each test id and update the test result
						for (int testIter = 0; testIter < testIds.size(); testIter++) {
							String testID = testIds.get(testIter).toString().trim();
							try {
								config = runDetails.get("config").toString();

								// Get browser name and case id from ITestResult
								browser = result.getTestContext().getCurrentXmlTest().getParameter("browserName");

								if (config.equalsIgnoreCase(browser)) {
									client.sendPost("add_result_for_case/" + runId + "/" + testID + "",
											addResult(result));
									logger.warn("test case is updated with status..." + testID);

								}
							} catch (NullPointerException e) {
								// In case of API tests, there will not be any
								// browser
								client.sendPost("add_result_for_case/" + runId + "/" + testID + "", addResult(result));
								logger.warn("test case is updated with status..." + testID);

							} catch (Exception e) {
								logger.warn("Not able to find expected test case id ..." + testID);
								throw e;
							}
						}
					}

					// Exit as the matching entry was found
					break;
				}

			}
		} catch (IOException | APIException cause) {
			cause.printStackTrace();
		}

	}

	/**
	 * Method to add different test status
	 * 
	 * @param res
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws APIException
	 *             status id {1 : Passed;2 : Blocked;3 : Untested;4 : Retest; 5
	 *             :Failed
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Comparable> addResult(ITestResult result) {

		Map<String, Comparable> status = new HashMap<>();
		String statusId = "status_id";
		String comment = "comment";

		if (result.getStatus() == ITestResult.SUCCESS) {
			status.put(statusId, 1);
			status.put(comment, "The test execution has passed");

		} else if (result.getStatus() == ITestResult.FAILURE) {
			status.put(statusId, 5);
			status.put(comment, "The test execution has Failed");

		} else if (result.getStatus() == ITestResult.SKIP) {
			status.put(statusId, 3);
			status.put(comment, "This test is skipped");

		} else {
			status.put(statusId, 2);
			status.put(comment, "This test is Blocked");
		}
		return status;

	}

}
