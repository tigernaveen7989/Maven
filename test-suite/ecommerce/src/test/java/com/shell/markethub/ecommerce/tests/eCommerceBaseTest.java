package com.shell.markethub.ecommerce.tests;

import java.io.IOException;

import org.apache.logging.log4j.core.config.Order;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shell.markethub.base.util.BaseSetup;
import com.shell.markethub.base.util.BaseTest;
import com.shell.markethub.base.util.config.BaseDataConstants;
import com.shell.markethub.ecommerce.pageobjects.AddToYourOrderPage;
import com.shell.markethub.ecommerce.pageobjects.AmendOrderPage;
import com.shell.markethub.ecommerce.pageobjects.ConfigureYourOrderPage;
import com.shell.markethub.ecommerce.pageobjects.CreateAnOrderPage;
import com.shell.markethub.ecommerce.pageobjects.DeliveryPage;
import com.shell.markethub.ecommerce.pageobjects.OrderConfirmationPage;
import com.shell.markethub.ecommerce.pageobjects.OrderHistoryPage;
import com.shell.markethub.ecommerce.pageobjects.OrderManagementWorkspacePage;
import com.shell.markethub.ecommerce.pageobjects.OrderPlacedPage;
import com.shell.markethub.ecommerce.pageobjects.PlacedOrderDetailsPage;
import com.shell.markethub.ecommerce.pageobjects.PreviewPriceAndDeliveryDatesPage;
import com.shell.markethub.ecommerce.pageobjects.ReviewAndPlaceOrderPage;
import com.shell.markethub.ecommerce.pageobjects.SelectProductsPage;
import com.shell.markethub.integration.pageobjects.HomePage;
import com.shell.markethub.integration.pageobjects.LoginPage;

/**
 * 
 * @author N.Kumar8@shell.com
 * 
 */
public class eCommerceBaseTest extends BaseTest{

	protected LoginPage loginPage;
	protected HomePage homePage;
	protected BaseSetup baseSetup;
	protected OrderManagementWorkspacePage orderManagementWorkspacePage;
	protected ConfigureYourOrderPage configureYourOrderPage;
	protected SelectProductsPage selectProductsPage;
	protected ReviewAndPlaceOrderPage reviewAndPlaceOrderPage;
	protected OrderPlacedPage orderPlacedPage;
	protected OrderHistoryPage orderHistoryPage;
	protected PlacedOrderDetailsPage placedOrderDetailsPage;
	protected CreateAnOrderPage createAnOrderPage;
	protected AddToYourOrderPage addToYourOrderPage;
	protected DeliveryPage deliveryPage;
	protected PreviewPriceAndDeliveryDatesPage previewPriceAndDeliveryDatesPage;
	protected OrderConfirmationPage orderConfirmationPage;
	protected AmendOrderPage amendOrderPage;
	
	@BeforeMethod
	public void setUp(ITestContext context) {
		loadPageObjects();
	}
	
	/**
	 * @param context
	 * @throws IOException 
	 * @description This class will be called immediately after running testng.xml and 
	 * baseSetup beforesuite method will be overridden
	 */
	@Test
	public void beforeSuite(ITestContext context) throws IOException {
		baseSetup = new BaseSetup();
		baseSetup.beforeSuite(context);
	}
	
	/** 
	 * @param driver
	 * @description This method will load all page objects
	 */
	public void loadPageObjects() {
		loginPage = new LoginPage();
		homePage = new HomePage();
		orderManagementWorkspacePage = new OrderManagementWorkspacePage();
		configureYourOrderPage = new ConfigureYourOrderPage();
		selectProductsPage = new SelectProductsPage();
		reviewAndPlaceOrderPage = new ReviewAndPlaceOrderPage();
		orderPlacedPage = new OrderPlacedPage();
		orderHistoryPage = new OrderHistoryPage();
		placedOrderDetailsPage = new PlacedOrderDetailsPage();
		createAnOrderPage = new CreateAnOrderPage();
		addToYourOrderPage = new AddToYourOrderPage();
		deliveryPage = new DeliveryPage();
		previewPriceAndDeliveryDatesPage = new PreviewPriceAndDeliveryDatesPage();
		orderConfirmationPage = new OrderConfirmationPage();
		amendOrderPage = new AmendOrderPage();
	}
}
