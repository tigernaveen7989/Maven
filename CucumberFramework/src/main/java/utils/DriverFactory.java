package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import pageObjects.SearchFlightPage;

public class DriverFactory {

	public static WebDriver driver;
	public static SearchFlightPage searchFlightPage;
	
	public WebDriver driver() throws Exception{
		try {
			ReadConfigFile file = new ReadConfigFile();
			String browserName = file.getBrowser();
			
			switch(browserName) {
			case "firefox":
				System.setProperty("webdriver.gecko.driver", Constant.GECKO_DRIVER_DIRECTORY);
				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability("marionette", true);
				driver = new FirefoxDriver(capabilities);
				driver.manage().window().maximize();
				break;
				
			case "chrome":
				System.setProperty("webdriver.chrome.driver", Constant.CHROME_DRIVER_DIRECTORY);
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				break;
				
			case "ie":
				DesiredCapabilities capabilities1 = DesiredCapabilities.internetExplorer();
				System.setProperty("webdriver.ie.driver", Constant.IE_DRIVER_DIRECTORY);
				capabilities1.setCapability("ignoreZoomSetting", true);
				driver = new InternetExplorerDriver(capabilities1);
				driver.manage().window().maximize();
				break;
			}
			
		}catch(Exception e) {
			System.out.println("Error launching Browser--"+e.getMessage());
		}finally {
			searchFlightPage = PageFactory.initElements(driver, SearchFlightPage.class);
		}
		return driver;
	}	
}
