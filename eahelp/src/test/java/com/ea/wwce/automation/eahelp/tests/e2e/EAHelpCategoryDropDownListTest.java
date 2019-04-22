package com.ea.wwce.automation.eahelp.tests.e2e;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.tests.EAHelpBaseTest;

public class EAHelpCategoryDropDownListTest extends EAHelpBaseTest {

	
	@Test
	public void verifyCategoryListForALocale(ITestContext context) throws Exception {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40467";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// select product
		eaHelpGameLibraryPage.selectProduct(testData.get("product").toString());

		// Verify categories
		int size = eaHelpProductPage.isCategoryContainsEntries();

		// Get country locales
		String[] countries = testData.get("locale").toString().split(",");

		for (String country : countries) {
			
			eaHelpHomePage.clickOnCountrySelector();
			eaHelpHomePage.selectCountry(country);
			Thread.sleep(3000);

			// Get list of categories
			String[] categories = testData.get("" + country + "_categories").toString().split(",");

			if (size != 0) {
				for (String category : categories) {
					// Verify categories are present in category down
					assertTrue(eaHelpProductPage.isCategoryContainsEntry(category),
							"Verify categories are present in category down");
				}
			}

		}

		// assert all
		assertAll();
	}
}
