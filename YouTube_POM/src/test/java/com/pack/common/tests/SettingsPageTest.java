package com.pack.common.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pack.base.TestBaseSetup;
import com.pack.common.pageobjects.HomePage;
import com.pack.common.pageobjects.SearchResultsPage;
import com.pack.common.pageobjects.SettingsPage;
import com.pack.common.pageobjects.StreamVideoPage;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Features("Settings")
public class SettingsPageTest extends TestBaseSetup{
	private WebDriver driver;
	private HomePage homePage;
	private SettingsPage settingsPage;
		
		@BeforeMethod
		public void setUp() {
			driver=getDriver();
		}
			
		@Severity(SeverityLevel.NORMAL)
		@Description("Verify Turn On & Off Autoplay functionality")
		@Stories("Verify Turn On & Off Autoplay functionality")
		@Test(description="Verify Turn On & Off Autoplay functionality",groups="Native")
		public void verifyTurnOnAndOffAutoPlay() throws Exception {
			String TCName = Thread.currentThread().getStackTrace()[1].getMethodName();
			homePage = new HomePage(driver);
			homePage.clickMoreOptions();
			homePage.clickSettings();
			settingsPage = new SettingsPage(driver);
			settingsPage.clickGeneral();
			settingsPage.toggleOnAndOff();
		}
}
