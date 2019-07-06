package cutebabycontest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class testng {
	
	private static WebDriver driver;
	
	@BeforeMethod
	public void setup() throws Exception{
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test
	public void testOne() throws Exception{
		driver.get("https://mycutebaby.in/contest/participant/?n=5d1cabec20e41");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		Boolean flag = driver.findElement(By.xpath("//img[@class='image-circle']")).isDisplayed();
		Thread.sleep(10000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)");
		
		if(driver.findElement(By.xpath("//input[@class='form-control name_box']")).isDisplayed()) {
			driver.findElement(By.xpath("//input[@class='form-control name_box']")).click();
		}
		
		if(driver.findElement(By.xpath("//div[@id='fb-close']")).isDisplayed()) {
			driver.findElement(By.xpath("//div[@id='fb-close']")).click();
		}
		
		if(driver.findElement(By.xpath("//input[@class='form-control name_box']")).isDisplayed()) {
			driver.findElement(By.xpath("//input[@class='form-control name_box']")).sendKeys("Naveen Kumar");
		}
		
		if(flag==true) {
			WebElement element = driver.findElement(By.xpath("//input[@class='form-control name_box']"));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
			
			driver.findElement(By.xpath("//a[@id='vote_btn']")).click();
			System.out.println("Clicked on Vote Button");
			Thread.sleep(10000);	
		}
	}
	
	@AfterMethod
	public void tearDown() throws Exception{
		driver.quit();
	}
}
