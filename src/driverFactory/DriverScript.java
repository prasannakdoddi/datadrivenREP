package driverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtils;

public class DriverScript extends AppUtil {

	String inputpath = "./File Input/TestData.xlsx";
	String outputpath = "./File Output/TestResult.xlsx";
	String adminLoginSheet = "Login";
	
	ExtentReports reports;
	ExtentTest loggers;
	
	@Test
	public void adminLoginValidation() throws Throwable
	{
		
		ExcelFileUtils xl = new ExcelFileUtils(inputpath);
		String username, password;
		String resp;
		
		reports = new ExtentReports("./ExtentReports//Admin Login.html");
		
		int rowCount = xl.rowCount(adminLoginSheet);
		Reporter.log("No. of rows :: " + rowCount,true);
		
		for(int i=1; i<rowCount; i++)
		{
			loggers = reports.startTest("Admin Login Test");
			
			username = xl.getCellData(adminLoginSheet, i, 0);
			password = xl.getCellData(adminLoginSheet, i, 1);
			
			loggers.log(LogStatus.INFO, username + " " + password);
			
			resp = FunctionLibrary.adminLogin(username, password);
			
			if(resp.contains("successfull"))
			{
				xl.setCellData(adminLoginSheet, i, 2, "Valid username and password", outputpath);
				xl.setCellData(adminLoginSheet, i, 3, "Pass", outputpath);
				loggers.log(LogStatus.PASS, "Valid username and password");
			}
			else
			{
				xl.setCellData(adminLoginSheet, i, 2, resp, outputpath);
				xl.setCellData(adminLoginSheet, i, 3, "Fail", outputpath);
				loggers.log(LogStatus.FAIL, resp);
			}
			reports.endTest(loggers);
			reports.flush();
		}
		
	}
}
