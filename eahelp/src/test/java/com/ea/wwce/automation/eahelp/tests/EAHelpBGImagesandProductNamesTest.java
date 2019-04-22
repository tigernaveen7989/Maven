package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class EAHelpBGImagesandProductNamesTest extends EAHelpBaseTest {

	public static Logger logger = Logger.getLogger(EAHelpBGImagesandProductNamesTest.class);

	/**
	 * Verify that the game box images are displayed properly. Verify that the
	 * BG images and product names are displayed properly. Verify the BG image
	 * displayed for few of the other games.
	 */

	@Test
	public void verifyBGImagesAndProductNamesDisplayedProperly(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// String productName[] = { "Mass Effect Andromeda", "Battlefield 1" };

		String imgProperties[] = { "border-bottom-left-radius", "border-bottom-right-radius", "border-image-width",
				"border-top-left-radius", "border-top-right-radius", "font-family", "font-size", "line-height" };
		String imgPropertyValues[] = { "2px", "2px", "1", "2px", "2px",
				"\"Helvetica Neue\", Helvetica, Arial, sans-serif", "14px", "20px" };

		String backgroundImage[] = { "background-image",
				"url(\"http://eaassets-a.akamaihd.net/qa-eahelp/assets/72.06-RELEASE72/bundles/helpcenter/images/default.png\")" };

		String imageSourceAttribute[] = { "src", "style",
				"http://eaassets-a.akamaihd.net/qa-eahelp/images/box-art/x2/mass-effect-andromeda.jpg",
				"https://eaassets-a.akamaihd.net/qa-eahelp/images/box-art/x2/fifa-18.jpg",
				"background-image: url(\"//eaassets-a.akamaihd.net/wwce-hc-aem-dispatcher/eahelp/tcd-vop/fifa/tcd-fifa-18-fut.jpg\");" };

		// Provide the ID of the test case. This is the ID generated in the 
		// TestRail when the manual test case is created.
		testID = "40369,40370,40371,40389,34113,34121,40925";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());
		
		eaHelpHomePage.clickOnGamesLibrary();

		// Search for product
		eaHelpGameLibraryPage.selectProduct(testData.get("product-3").toString());

		// verify image source url
		String attrVal = eaHelpProductPage.getContactDriverImage(imageSourceAttribute[1]);
		
		//boolean isTrue=attrVal.contains("fifa-18-fut.jpg");

		// verify image url is configured for this product
		assertTrue(attrVal.contains("fifa-18.jpg"),
				"verify image url is configured for this product " + testData.get("product-3").toString());

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());
		
		eaHelpHomePage.clickOnGamesLibrary();

		// Search for product
		eaHelpGameLibraryPage.searchForProduct(testData.get("product-1").toString());

		int i = 0;
		// Verify default product image css values
		for (String property : imgProperties) {

			// verify css values
			String expected = eaHelpGameLibraryPage.verifyProductImageCSSValues(property);

			assertEquals(expected, imgPropertyValues[i], "Verify product image css value are matching as expected ");

			logger.info("Expected " + expected + "  Actaul " + imgPropertyValues[i]);
			i++;
		}

		// Verify back ground image is present
		String val = eaHelpGameLibraryPage.verifyProductSourceCSSValues(backgroundImage[0]);
		assertEquals(val, backgroundImage[1],
				"verify background image is configured for this product " + testData.get("product-1").toString());

		// verify image source url
		attrVal = eaHelpGameLibraryPage.getProductAttributeValues(imageSourceAttribute[0]);
		assertTrue(attrVal.contains("mass-effect-andromeda.jpg"),
				"verify image url is configured for this product " + testData.get("product-1").toString());

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());
		
		eaHelpHomePage.clickOnGamesLibrary();

		// Search for product
		eaHelpGameLibraryPage.searchForProduct(testData.get("product-2").toString());

		// Reset i to 0
		i = 0;
		// Verify default product image css values
		for (String property : imgProperties) {

			// verify css values
			String expected = eaHelpGameLibraryPage.verifyProductImageCSSValues(property);

			assertEquals(expected, imgPropertyValues[i], "Verify product image css value are matching as expected ");

			logger.info("Expected  " + expected + "  Actaul " + imgPropertyValues[i]);
			i++;
		}

		// Verify back ground image is present
		val = eaHelpGameLibraryPage.verifyProductSourceCSSValues(backgroundImage[0]);
		assertEquals(val, backgroundImage[1],
				"verify background image is configured for this product " + testData.get("product-2").toString());

		// verify image source url
		attrVal = eaHelpGameLibraryPage.getProductAttributeValues(imageSourceAttribute[0]);
		assertTrue(attrVal.contains("battlefield-1.jpg"),
				"verify image url is configured for this product " + testData.get("product-2").toString());

		assertAll();
	}

}
