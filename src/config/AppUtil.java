package config;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.*;

public class AppUtil {

	public static WebDriver driver;
	public static Properties conpro;
	
	@BeforeTest
	public static void setUp() throws Throwable
	{
		conpro = new Properties();
		conpro.load(new FileInputStream("./Property File/Environment.properties"));
		String browser = conpro.getProperty("browser");
		
		if(browser.equalsIgnoreCase("Chrome"))
		{
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if (browser.equalsIgnoreCase("Firefox"))
			driver = new FirefoxDriver();
		else 
			Reporter.log("Browser value is not matching",true);
	}
	
	@AfterTest
	public static void tearDown()
	{
		driver.quit();
	}
}
