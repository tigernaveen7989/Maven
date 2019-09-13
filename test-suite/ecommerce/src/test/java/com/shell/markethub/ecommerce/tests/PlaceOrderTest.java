package com.shell.markethub.ecommerce.tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shell.markethub.base.util.config.BaseDataConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;

@Feature("eCommerce")
public class PlaceOrderTest extends eCommerceBaseTest{

	private static Logger logger = Logger.getLogger(PlaceOrderTest.class);
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
	@Test(groups = {"sanity"})
	@Description("verify_aom_place_order")
	public void verify_aom_place_order(ITestContext context) throws Exception{	
		String userName = testData.get("userName").toString();
		String password = testData.get("password").toString();
		String deliveryLocation = testData.get("deliveryLocation").toString();
		String deliveryInstructions = testData.get("deliveryInstructions").toString();
		String driverInstructions = testData.get("driverInstructions").toString();
		String orderQuantity = testData.get("orderQuantity").toString();
		
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
		
		if(deliveryPage.verifyPageTitle() == true) {
			assertEquals(deliveryPage.getPageTitle(), "Delivery", "Delivery page title is displaying");
			deliveryPage.clickOnContinueButton();
		}

		assertEquals(reviewAndPlaceOrderPage.getPageTitle(), "Review & Place Order", "Review & Place Order page title is displaying");
		reviewAndPlaceOrderPage.clickOnPlaceOrderButton();
		
		assertEquals(orderPlacedPage.getPageTitle(), "Order placed", "Order placed page title is displaying");
		assertTrue(orderPlacedPage.getOrderHasBeenPlacedText().contains("Your order has been placed"), "Your order has been placed, you'll receive notifications about your order via your preferred communication method. Text is displaying");
		assertTrue(orderPlacedPage.getOrderNumberText().contains("Order number "), "Order number Text is displaying");
		assertTrue(orderPlacedPage.getOrderStatusText().contains("Order status"), "Order status Text is displaying");
		assertAll();
	}
		
	/**
	 * @param context
	 * @throws Exception
	 */
	@Test(groups = "sanity")
	@Description("verify_aom_frequently_ordered")
	public void verify_aom_frequently_ordered(ITestContext context) throws Exception{	
		String userName = testData.get("userName").toString();
		String password = testData.get("password").toString();
		String deliveryLocation = testData.get("deliveryLocation").toString();
		String deliveryInstructions = testData.get("deliveryInstructions").toString();
		String driverInstructions = testData.get("driverInstructions").toString();
		String orderQuantity = testData.get("orderQuantity").toString();
		
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
		selectProductsPage.clickOnFrequentlyOrderedLink();
		selectProductsPage.enterOrderQuantity(orderQuantity);
		selectProductsPage.clickOnAddToOrderButton();
		selectProductsPage.clickOnFrequentlyOrderedFrameCloseButton();
		selectProductsPage.clickOnContinueButton();
		
		if(deliveryPage.verifyPageTitle() == true) {
			assertEquals(deliveryPage.getPageTitle(), "Delivery", "Delivery page title is displaying");
			deliveryPage.clickOnContinueButton();
		}
		
		assertEquals(reviewAndPlaceOrderPage.getPageTitle(), "Review & Place Order", "Review & Place Order page title is displaying");
		reviewAndPlaceOrderPage.clickOnPlaceOrderButton();
		
		assertEquals(orderPlacedPage.getPageTitle(), "Order placed", "Order placed page title is displaying");
		assertTrue(orderPlacedPage.getOrderHasBeenPlacedText().contains("Your order has been placed"), "Your order has been placed, you'll receive notifications about your order via your preferred communication method. Text is displaying");
		assertTrue(orderPlacedPage.getOrderNumberText().contains("Order number "), "Order number Text is displaying");
		assertTrue(orderPlacedPage.getOrderStatusText().contains("Order status"), "Order status Text is displaying");
		assertAll();
	}
		
	/**
	 * @param context
	 * @throws Exception
	 */
	@Test
	public void verify_aom_clone_this_order(ITestContext context) throws Exception{	
		String userName = testData.get("userName").toString();
		String password = testData.get("password").toString();
		String orderQuantity = testData.get("orderQuantity").toString();
		
		loginPage.getMarketHubHomePage(BaseDataConstants.MARKETHUB_AUT_URL);
		loginPage.loginMarketHub(userName, password);
		
		homePage.clickOnAllTab();
		homePage.clickOnOrderManagementLink();
		
		assertEquals(orderManagementWorkspacePage.getPageTitle(), "Order Management Workspace", "Order Management Workspace page title is displaying");
		orderManagementWorkspacePage.clickOnViewOrderDetailsLink();
		
		assertEquals(orderHistoryPage.getPageTitle(), "Order History", "Order History page title is displaying");
		String orderNumber = orderHistoryPage.clickOnFirstOrderNumber();
		
		assertEquals(placedOrderDetailsPage.getPageTitle(), "Placed Order Details", "Placed Order Details page title is displaying");
		assertTrue(placedOrderDetailsPage.getOrderNumber().contains(orderNumber), orderNumber+" Order Number is displaying");
		assertTrue(placedOrderDetailsPage.getOrderStatus().contains("Order status"), "Order status text is displaying");
		assertTrue(placedOrderDetailsPage.getAccountDetails(), "Account details is displaying");
		assertTrue(placedOrderDetailsPage.getSummary(), "Summary is displaying");
		placedOrderDetailsPage.clickOnCloneThisOrderButton();
		
		if(deliveryPage.verifyPageTitle() == true) {
			assertEquals(deliveryPage.getPageTitle(), "Delivery", "Delivery page title is displaying");
			deliveryPage.clickOnContinueButton();
		}
		
		assertEquals(reviewAndPlaceOrderPage.getPageTitle(), "Review & Place Order", "Review & Place Order page title is displaying");
		reviewAndPlaceOrderPage.clickOnAddMoreProductsLink();
		
		assertEquals(selectProductsPage.getPageTitle(), "Select Products", "Select Products page title is displaying");
		selectProductsPage.clickOnViewCatalogButton();
		selectProductsPage.clickOnAutomotiveGasOilLink();
		selectProductsPage.enterOrderQuantity(orderQuantity);
		selectProductsPage.clickOnAddToOrderButton();
		selectProductsPage.clickOnProductCatalogueFrameCloseButton();
		selectProductsPage.clickOnContinueButton();
		
		if(deliveryPage.verifyPageTitle() == true) {
			assertEquals(deliveryPage.getPageTitle(), "Delivery", "Delivery page title is displaying");
			deliveryPage.clickOnContinueButton();
		}
		
		assertEquals(reviewAndPlaceOrderPage.getPageTitle(), "Review & Place Order", "Review & Place Order page title is displaying");
		reviewAndPlaceOrderPage.clickOnPlaceOrderButton();
		
		assertEquals(orderPlacedPage.getPageTitle(), "Order placed", "Order placed page title is displaying");
		assertTrue(orderPlacedPage.getOrderHasBeenPlacedText().contains("Your order has been placed"), "Your order has been placed, you'll receive notifications about your order via your preferred communication method. Text is displaying");
		assertTrue(orderPlacedPage.getOrderNumberText().contains("Order number "), "Order number Text is displaying");
		assertTrue(orderPlacedPage.getOrderStatusText().contains("Order status"), "Order status Text is displaying");
		assertTrue(orderPlacedPage.verifyYouCanEditThisOrderText(), "You can edit this order Text is displaying");
		assertAll();
	}
	
	/**
	 * @param context
	 * @throws Exception
	 */
	@Test(groups = {"sanity"})
	@Description("verify_old_om_place_order")
	public void verify_old_om_place_order(ITestContext context) throws Exception{	
		String userName = testData.get("userName").toString();
		String password = testData.get("password").toString();
		String deliveryLocation = testData.get("deliveryLocation").toString();
		String deliveryInstructions = testData.get("deliveryInstructions").toString();
		String driverInstructions = testData.get("driverInstructions").toString();
		String productName = testData.get("productName").toString();
		String orderQuantity = testData.get("orderQuantity").toString();
		
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
		
		if(deliveryPage.verifyPageTitle() == true) {
			assertEquals(deliveryPage.getPageTitle(), "Delivery", "Delivery page title is displaying");
			deliveryPage.clickOnContinueButton();
		}
		
		assertEquals(previewPriceAndDeliveryDatesPage.getOldPageTitle(), "Preview Price and Delivery Dates", "Preview Price and Delivery Dates page title is displaying");
		previewPriceAndDeliveryDatesPage.oldClickOnPlaceOrderNowButton();
		
		assertEquals(orderConfirmationPage.getOldPageTitle(), "Order confirmation", "Order confirmation page title is displaying");
		assertTrue(orderConfirmationPage.getOldYourOrderHasBeenSubmittedText().contains("Your order has been submitted successfully"), "Your order has been submitted successfully Text is displaying");
		assertTrue(orderConfirmationPage.getOldShellOrderNumberText().contains("Shell Order Number"), "Shell Order Number Text is displaying");
		assertTrue(orderConfirmationPage.getOldOrderStatusText().contains("Order status"), "Order status Text is displaying");
		assertTrue(orderConfirmationPage.getOldSummary(), "Verify summary table displayed");
		
		assertAll();
	}
}
