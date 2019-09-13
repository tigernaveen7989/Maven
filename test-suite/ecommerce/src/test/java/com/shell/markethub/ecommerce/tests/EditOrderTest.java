package com.shell.markethub.ecommerce.tests;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shell.markethub.base.util.config.BaseDataConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;

@Feature("eCommerce")
public class EditOrderTest extends eCommerceBaseTest{
	private static Logger logger = Logger.getLogger(EditOrderTest.class);
	/**
	 * @description getDriver from BaseTest
	 */
	@BeforeMethod
	public void setUp(ITestContext context) {
		super.setUp(context);
	}
	
	/**
	 * @param context
	 * @throws Exception
	 */
	@Test(groups = "sanity")
	@Description("verify_aom_edit_order")
	public void verify_aom_edit_order(ITestContext context) throws Exception{	
		String userName = testData.get("userName").toString();
		String password = testData.get("password").toString();
		String deliveryLocation = testData.get("deliveryLocation").toString();
		String deliveryInstructions = testData.get("deliveryInstructions").toString();
		String driverInstructions = testData.get("driverInstructions").toString();
		String orderQuantity = testData.get("orderQuantity").toString();
		String productName = testData.get("productName").toString();
		
		loginPage.getMarketHubHomePage(BaseDataConstants.MARKETHUB_AUT_URL);
		loginPage.loginMarketHub(userName, password);
		
		homePage.clickOnAllTab();
		homePage.clickOnOrderManagementLink();
		
		assertEquals(orderManagementWorkspacePage.getPageTitle(), "Order Management Workspace", "Order Management Workspace page title is displaying");
		orderManagementWorkspacePage.clickOnCreateNewOrderButton();
		
		assertEquals(configureYourOrderPage.getPageTitle(), "Configure your order", "Configure your order page title is displaying");
		configureYourOrderPage.enterDeliveryLocation(deliveryLocation);
		configureYourOrderPage.clickOnDeliveryLocationCheckbox();
		configureYourOrderPage.enterDeliveryInstructions(deliveryInstructions);
		configureYourOrderPage.enterDriverInstructions(driverInstructions);
		configureYourOrderPage.clickOnContinueButton();
		
		assertEquals(selectProductsPage.getPageTitle(), "Select Products", "Select Products page title is displaying");
		selectProductsPage.clickOnBulkButton();
		selectProductsPage.clickOnViewCatalogButton();
		selectProductsPage.enterOrderQuantity(orderQuantity);
		selectProductsPage.clickOnAddToOrderButton();
		selectProductsPage.clickOnProductCatalogueFrameCloseButton();
		selectProductsPage.clickOnContinueButton();
		
		if (deliveryPage.verifyPageTitle() == true) {
			assertEquals(deliveryPage.getPageTitle(), "Delivery", "Delivery page title is displaying");
			deliveryPage.clickOnContinueButton();
		}

		assertEquals(reviewAndPlaceOrderPage.getPageTitle(), "Review & Place Order", "Review & Place Order page title is displaying");
		reviewAndPlaceOrderPage.clickOnPlaceOrderButton();
		
		if(orderConfirmationPage.verifySurveyPopup() == true) {
			orderConfirmationPage.closeSurveyPopup();
		}
		assertEquals(orderPlacedPage.getPageTitle(), "Order placed", "Order placed page title is displaying");
		assertTrue(orderPlacedPage.getOrderHasBeenPlacedText().contains("Your order has been placed"), "Your order has been placed, you'll receive notifications about your order via your preferred communication method. Text is displaying");
		assertTrue(orderPlacedPage.getOrderNumberText().contains("Order number "), "Order number Text is displaying");
		assertTrue(orderPlacedPage.getOrderStatusText().contains("Order status"), "Order status Text is displaying");
		orderPlacedPage.clickOnEditThisOrderLink();
		
		placedOrderDetailsPage.clickOnEditThisOrderButton();
		
		assertEquals(amendOrderPage.getPageTitle(), "Amend Order", "Verify Amend Order page title");
		amendOrderPage.clickOnAddNewItemsLink();
		
		assertEquals(addToYourOrderPage.getOldPageTitle(), "Add to your order", "Add to your order page title is displaying");
		addToYourOrderPage.oldEnterProduct(productName);
		addToYourOrderPage.oldClickOnSearchIcon();
		addToYourOrderPage.oldEnterQuantity(orderQuantity);
		addToYourOrderPage.oldClickOnContinueButton();
		addToYourOrderPage.oldClickOnContinueButton1();
		
		amendOrderPage.clickOnSubmitChangesButton();
		
		assertTrue(orderConfirmationPage.getOldYourOrderHasBeenSubmittedText().contains("Your order has been updated successfully"), "Verify Your order has been updated successfully Text");
		assertAll();
	}

	/**
	 * @param context
	 * @throws Exception
	 */
	@Test(groups = "sanity")
	@Description("verify_old_om_edit_order")
	public void verify_old_om_edit_order(ITestContext context) throws Exception{	
		String userName = testData.get("userName").toString();
		String password = testData.get("password").toString();
		String deliveryLocation = testData.get("deliveryLocation").toString();
		String deliveryInstructions = testData.get("deliveryInstructions").toString();
		String driverInstructions = testData.get("driverInstructions").toString();
		String productName = testData.get("productName").toString();
		String productName1 = testData.get("productName1").toString();
		String orderQuantity = testData.get("orderQuantity").toString();
		String orderNumber;
		
		loginPage.getMarketHubHomePage(BaseDataConstants.MARKETHUB_AUT_URL);
		loginPage.loginMarketHub(userName, password);
		
		homePage.clickOnAllTab();
		homePage.clickOnOrderManagementLink();
		
		assertEquals(orderManagementWorkspacePage.getPageTitle(), "Order Management Workspace", "Order Management Workspace page title is displaying");
		orderManagementWorkspacePage.clickOnCreateNewOrderButton();
		 
		
		assertEquals(createAnOrderPage.getOldPageTitle(), "Create an Order", "Create an Order page title is displaying");
		createAnOrderPage.oldEnterDeliveryLocation(deliveryLocation);
		createAnOrderPage.oldClickOnSelectButton();
		createAnOrderPage.oldClickOnBulkRadioButton();
		createAnOrderPage.oldEnterDeliveryInstructions(deliveryInstructions);
		createAnOrderPage.oldEnterDriverInstructions(driverInstructions);
		createAnOrderPage.oldClickOnContinueButton();
		
		assertEquals(addToYourOrderPage.getOldPageTitle(), "Add to your order", "Add to your order page title is displaying");
		addToYourOrderPage.oldEnterProduct(productName);
		addToYourOrderPage.oldClickOnSearchIcon();
		addToYourOrderPage.oldEnterQuantity(orderQuantity);
		addToYourOrderPage.oldClickOnContinueButton();
		addToYourOrderPage.oldClickOnContinueButton1();
		
		assertEquals(previewPriceAndDeliveryDatesPage.getOldPageTitle(), "Preview Price and Delivery Dates", "Preview Price and Delivery Dates page title is displaying");
		previewPriceAndDeliveryDatesPage.oldClickOnPlaceOrderNowButton();
		
	
		if (orderConfirmationPage.verifySurveyPopup() == true) {
			orderConfirmationPage.closeSurveyPopup();
		}
		 
		assertEquals(orderConfirmationPage.getOldPageTitle(), "Order confirmation", "Order confirmation page title is displaying");
		assertTrue(orderConfirmationPage.getOldYourOrderHasBeenSubmittedText().contains("Your order has been submitted successfully"), "Your order has been submitted successfully Text is displaying");
		assertTrue(orderConfirmationPage.getOldShellOrderNumberText().contains("Shell Order Number"), "Shell Order Number Text is displaying");
		assertTrue(orderConfirmationPage.getOldOrderStatusText().contains("Order status"), "Order status Text is displaying");
		assertTrue(orderConfirmationPage.getOldSummary(), "Verify summary table displayed");
		orderNumber = orderConfirmationPage.getOldOrderNumber();
		orderConfirmationPage.oldClickOnOrderWorkspaceLink();
		
		orderManagementWorkspacePage.selectOrderNumberDropdown();
		orderManagementWorkspacePage.enterOrderNumber(orderNumber);
		orderManagementWorkspacePage.clickOnSearchIcon();
		
		orderHistoryPage.clickOnOrderNumber(orderNumber);
		
		placedOrderDetailsPage.oldClickOnEditOrderButton();
		
		assertEquals(amendOrderPage.getPageTitle(), "Amend Order", "Verify Amend Order page title");
		amendOrderPage.clickOnAddNewItemsLink();
		
		assertEquals(addToYourOrderPage.getOldPageTitle(), "Add to your order", "Add to your order page title is displaying");
		addToYourOrderPage.oldEnterProduct(productName1);
		addToYourOrderPage.oldClickOnSearchIcon();
		addToYourOrderPage.oldEnterQuantity(orderQuantity);
		addToYourOrderPage.oldClickOnContinueButton();
		addToYourOrderPage.oldClickOnContinueButton1();
		
		amendOrderPage.clickOnSubmitChangesButton();
		
		assertTrue(orderConfirmationPage.getOldYourOrderHasBeenSubmittedText().contains("Your order has been updated successfully"), "Your order has been updated successfully Text is displaying");
		assertAll();
	}
}
