package com.coderio.pom;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.coderio.pom.ReadExcelFile;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginOut_Test {
	
	private WebDriver driver;
	LogInOutPage logPage;
	private ReadExcelFile readFile;
    public ReporteExtent reports;
    public ExtentTest test;
	
	@Before
	public void setUp() throws Exception {
		logPage = new LogInOutPage(driver);
		driver = logPage.chromeDriverConnection();
		logPage.visit("https://www.demoblaze.com/");
		readFile = new ReadExcelFile();
		//Extent Report
	    File reportDir = new File(System.getProperty("user.dir"));
	    String reportFilePath = reportDir.toString() + "/ExtentReports/";
        reports = new ReporteExtent(reportFilePath, false);
        test = reports.startTest("Extent Report for Coderio");
		
	}

	@After
	public void tearDown() throws Exception {
        driver.close();
        driver.quit();
        reports.endTest(test);
        reports.flush();
        reports.close();
	}

	@Test
	public void test() throws InterruptedException, IOException {
		String filepath = System.getProperty("user.dir")+"\\CoderioExcel_Test.xlsx";
		String userToLog = readFile.getCellValueAsString(filepath, "Sheet1", 0, 0);
		String passToLog = readFile.getCellValueAsString(filepath, "Sheet1", 0, 1);
		//logPage.loginUser();  /*test without excel*/
		logPage.loginUser(userToLog,passToLog);
		//assertEquals("Welcome "+userToLog, logPage.isLogged()) /*test without report*/
		if (logPage.isLogged().contains(userToLog)) {
		    test.log(LogStatus.PASS,test.addScreenCapture(reports.capture(driver))+ "Tested ok: User logged.");
		} else {
		    test.log(LogStatus.FAIL,test.addScreenCapture(reports.capture(driver))+ "Test Failed: User is not logged.");
		}
		logPage.logOutUser();
		if (logPage.isLogged().contains(userToLog)) {
		    test.log(LogStatus.FAIL,test.addScreenCapture(reports.capture(driver))+ "Test failed: User logged.");
		} else {
		    test.log(LogStatus.PASS,test.addScreenCapture(reports.capture(driver))+ "Tested ok: User is not logged.");
		}
		
	}

}
