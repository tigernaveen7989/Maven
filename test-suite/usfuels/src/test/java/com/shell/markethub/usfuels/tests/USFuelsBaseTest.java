package com.shell.markethub.usfuels.tests;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shell.markethub.base.util.BaseSetup;
import com.shell.markethub.base.util.BaseTest;
import com.shell.markethub.integration.pageobjects.HomePage;
import com.shell.markethub.integration.pageobjects.LoginPage;
import com.shell.markethub.usfuels.pageobjects.AllocationsPage;
import com.shell.markethub.usfuels.pageobjects.BOLsPage;
import com.shell.markethub.usfuels.pageobjects.CarrierDriverLastLiftPage;
import com.shell.markethub.usfuels.pageobjects.CarrierMaintenancePage;
import com.shell.markethub.usfuels.pageobjects.ContractPricesPage;
import com.shell.markethub.usfuels.pageobjects.ContractRecapturePage;
import com.shell.markethub.usfuels.pageobjects.InvoicedVolumesPage;
import com.shell.markethub.usfuels.pageobjects.RackPricesPage;
import com.shell.markethub.usfuels.pageobjects.SiteListPage;
import com.shell.markethub.usfuels.pageobjects.SupplyWorkspacePage;
import com.shell.markethub.usfuels.pageobjects.TerminalWaitTimePage;
import com.shell.markethub.usfuels.pageobjects.VolumesEntryPage;
import com.shell.markethub.usfuels.pageobjects.WhereAndWhatCanILiftPage;

/**
 * 
 * @author N.Kumar8@shell.com
 * 
 */
public class USFuelsBaseTest extends BaseTest{

	protected LoginPage loginPage;
	protected HomePage homePage;
	protected RackPricesPage rackPricesPage;
	protected BOLsPage bolsPage;
	protected CarrierDriverLastLiftPage carrierDriverLastLiftPage;
	protected ContractPricesPage contractPricesPage;
	protected TerminalWaitTimePage terminalWaitTimePage;
	protected AllocationsPage allocationsPage;
	protected InvoicedVolumesPage invoicedVolumesPage;
	protected SupplyWorkspacePage supplyWorkspacePage;
	protected WhereAndWhatCanILiftPage whereAndWhatCanILiftPage;
	protected SiteListPage siteListPage;
	protected VolumesEntryPage volumesEntryPage;
	protected CarrierMaintenancePage carrierMaintenancePage;
	protected ContractRecapturePage contractRecapturePage;
	protected BaseSetup baseSetup;
	
	@BeforeMethod
	public void setUp() {
		loadPageObjects();
	}
	
	/**
	 * @param context
	 * @throws IOException 
	 * @description This class will be called immediately after running testng.xml and 
	 * baseSetup beforesuite method will be overridden
	 */
	@Test()
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
		rackPricesPage = new RackPricesPage();
		bolsPage = new BOLsPage();
		carrierDriverLastLiftPage = new CarrierDriverLastLiftPage();
		contractPricesPage = new ContractPricesPage();
		terminalWaitTimePage = new TerminalWaitTimePage();
		allocationsPage = new AllocationsPage();
		invoicedVolumesPage = new InvoicedVolumesPage();
		supplyWorkspacePage = new SupplyWorkspacePage();
		whereAndWhatCanILiftPage = new WhereAndWhatCanILiftPage();
		siteListPage = new SiteListPage();
		volumesEntryPage = new VolumesEntryPage();
		carrierMaintenancePage = new CarrierMaintenancePage();
		contractRecapturePage = new ContractRecapturePage();
	}
}
