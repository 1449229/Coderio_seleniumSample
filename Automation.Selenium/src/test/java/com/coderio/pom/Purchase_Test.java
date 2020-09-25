package com.coderio.pom;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Purchase_Test {


	private WebDriver driver;
	PurchasePage purchasePage;
	private ReadExcelFile readFile;
    public ReporteExtent reports;
    public ExtentTest test;
	
	@Before
	public void setUp() throws Exception {
		purchasePage = new PurchasePage(driver);
		driver = purchasePage.chromeDriverConnection();
		purchasePage.visit("https://www.demoblaze.com/");
		readFile = new ReadExcelFile();
		//Extent Report
	    File reportDir = new File(System.getProperty("user.dir"));
	    String reportFilePath = reportDir.toString() + "/ExtentReports/";
        reports = new ReporteExtent(reportFilePath, false);
        test = reports.startTest("Extent Report for Coderio");
		String filepath = System.getProperty("user.dir")+"\\CoderioExcel_Test.xlsx";
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
		//Get variables from Excel
		//		String filepath = "C:\\Users\\Administrator\\Desktop\\CoderioExcel_Test.xlsx";
		String filepath = System.getProperty("user.dir")+"\\CoderioExcel_Test.xlsx";
		String userToLog = readFile.getCellValueAsString(filepath, "Sheet1", 0, 0);
		String name = readFile.getCellValueAsString(filepath, "Sheet2", 0, 0);
		String country = readFile.getCellValueAsString(filepath, "Sheet2", 0, 1);
		String city = readFile.getCellValueAsString(filepath, "Sheet2", 0, 2);
		int creditCard = readFile.getCellValueAsInt(filepath, "Sheet2", 0, 3);
		int month = readFile.getCellValueAsInt(filepath, "Sheet2", 0, 4);
		int year = readFile.getCellValueAsInt(filepath, "Sheet2", 0, 5);
		
		purchasePage.logIn(); /*test without excel*/
		if (purchasePage.isLogged().contains(userToLog)) {
		    test.log(LogStatus.PASS,test.addScreenCapture(reports.capture(driver))+ "Tested ok: User logged.");
		} else {
		    test.log(LogStatus.FAIL,test.addScreenCapture(reports.capture(driver))+ "Test Failed: User is not logged.");
		}
		purchasePage.addCart();
		if (purchasePage.alertActive().contains("Product added.")) {
		    test.log(LogStatus.PASS,test.addScreenCapture(reports.capture(driver))+ "Tested ok: Product added to the cart.");
		} else {
		    test.log(LogStatus.FAIL,test.addScreenCapture(reports.capture(driver))+ "Test Failed: Product not added.");
		}
		
		//purchasePage.purchaseCart(); /*test without excel*/
		purchasePage.purchaseCart(name, country, city, creditCard, month, year);
		if (purchasePage.purchaseOkDisplayed()) {
		    test.log(LogStatus.PASS,test.addScreenCapture(reports.capture(driver))+ "Tested ok: Product sold.");
		} else {
		    test.log(LogStatus.FAIL,test.addScreenCapture(reports.capture(driver))+ "Test Failed: Product not sold.");
		}
	}

}
