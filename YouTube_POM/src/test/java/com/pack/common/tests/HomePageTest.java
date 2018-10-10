package com.pack.common.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pack.base.TestBaseSetup;
import com.pack.common.pageobjects.HomePage;
import com.pack.functionlibrary.FunctionLibrary;
import com.pack.utils.*;
import com.pack.utils.Listeners.TestListener;

import io.appium.java_client.android.AndroidDriver;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Features("HomePage")
public class HomePageTest extends TestBaseSetup{
	private WebDriver driver;

	private HomePage homePage;
		
		@BeforeMethod
		public void setUp() {
			driver=getDriver();
		}
			
		@Severity(SeverityLevel.NORMAL)
		@Description("Verify Search functionality")
		@Stories("Verify Search functionality")
		@Parameters({ "deviceName", "platformVersion", "URL" })
		@Test(description="Verify Search functionality")
		public void verifySearchFunction() throws Exception {
			String TCName = Thread.currentThread().getStackTrace()[1].getMethodName();
			homePage = new HomePage(driver);
			homePage.verifySearchIcon();
			homePage.clickSearchIcon();
			homePage.enterValue(TCName);
			homePage.clickSearch();
		}
}
