package com.shell.markethub.ecommerce.tests;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shell.markethub.base.util.config.BaseDataConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;

@Feature("eCommerce")
public class ViewOrderTest extends eCommerceBaseTest{

	private static Logger logger = Logger.getLogger(ViewOrderTest.class);
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
	@Test(groups = {"Sanity"})
	@Description("verify aom view order")
	public void verify_aom_view_order(ITestContext context) throws Exception{	
		String userName = testData.get("userName").toString();
		String password = testData.get("password").toString();
		
		loginPage.getMarketHubHomePage(BaseDataConstants.MARKETHUB_AUT_URL);
		loginPage.loginMarketHub(userName, password);
		
		homePage.clickOnAllTab();
		homePage.clickOnOrderManagementLink();

		assertEquals(orderManagementWorkspacePage.getPageTitle(), "Order Management Workspace",
				"Order Management Workspace page title is displaying");
		orderManagementWorkspacePage.clickOnViewOrderDetailsLink();

		assertEquals(orderHistoryPage.getPageTitle(), "Order History", "Order History page title is displaying");
		String orderNumber = orderHistoryPage.clickOnFirstOrderNumber();

		assertEquals(placedOrderDetailsPage.getPageTitle(), "Placed Order Details",
				"Placed Order Details page title is displaying");
		assertTrue(placedOrderDetailsPage.getOrderNumber().contains(orderNumber),
				orderNumber + " Order Number is displaying");
		assertTrue(placedOrderDetailsPage.getOrderStatus().contains("Order status"), "Order status text is displaying");
		assertTrue(placedOrderDetailsPage.getAccountDetails(), "Account details is displaying");
		assertTrue(placedOrderDetailsPage.getSummary(), "Summary is displaying");

		assertAll();
	}
	
	/**
	 * @param context
	 * @throws Exception
	 */
	@Test(groups = {"Sanity"})
	@Description("verify old om view order")
	public void verify_old_om_view_order(ITestContext context) throws Exception{	
		String userName = testData.get("userName").toString();
		String password = testData.get("password").toString();
		
		loginPage.getMarketHubHomePage(BaseDataConstants.MARKETHUB_AUT_URL);
		loginPage.loginMarketHub(userName, password);
		
		homePage.clickOnAllTab();
		homePage.clickOnOrderManagementLink();
		
		assertEquals(orderManagementWorkspacePage.getPageTitle(), "Order Management Workspace", "Order Management Workspace page title is displaying");
		orderManagementWorkspacePage.clickOnViewOrderDetailsLink();
		
		assertEquals(orderHistoryPage.getPageTitle(), "Order History", "Order History page title is displaying");
		String orderNumber = orderHistoryPage.clickOnFirstOrderNumber();
		
		assertEquals(placedOrderDetailsPage.getOldPageTitle(), "Placed Order Details", "Placed Order Details page title is displaying");
		assertTrue(placedOrderDetailsPage.getOldOrderNumber().contains(orderNumber), orderNumber+" Order Number is displaying");
		assertTrue(placedOrderDetailsPage.getOldOrderStatus().contains("Order Status"), "Order status text is displaying");
		assertTrue(placedOrderDetailsPage.getOldSummary(), "Summary is displaying");
		assertAll();
	}
}
