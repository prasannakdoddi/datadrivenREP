package commonFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil{

	public static String adminLogin(String uname, String pwd) throws Throwable
	{		
		String message;
		
		driver.get(conpro.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.xpath(conpro.getProperty("ObjReset"))).click();
		driver.findElement(By.xpath(conpro.getProperty("ObjUname"))).sendKeys(uname);
		driver.findElement(By.xpath(conpro.getProperty("ObjPwd"))).sendKeys(pwd);
		driver.findElement(By.xpath(conpro.getProperty("ObjSubmit"))).click();
		
		String Expected = "dashboard";
		String Actual = driver.getCurrentUrl();
		
		if(Actual.contains(Expected))
		{
			driver.findElement(By.xpath(conpro.getProperty("ObjLogout"))).click();
			driver.findElement(By.xpath(conpro.getProperty("ObjLogoutOk"))).click();
			Reporter.log("Admin login successfull with user " + uname + ":: Test Pass", true);
			message = "Admin login successfull";
			return message;
		}
		else
		{
			message = driver.findElement(By.xpath(conpro.getProperty("ObjAlert"))).getText();
			driver.findElement(By.xpath(conpro.getProperty("ObjAlertOk"))).click();
			Reporter.log(message + " :: Test Fail", true);
			return message;
		}
	}
}
