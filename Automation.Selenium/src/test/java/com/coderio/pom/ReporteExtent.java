package com.coderio.pom;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;

public class ReporteExtent extends ExtentReports{

    public ReporteExtent(String filePath, Boolean replaceExisting) {
		super(filePath + "ReporteExtent" + getRandomNumber() + ".html", replaceExisting);
		// TODO Auto-generated constructor stub
	}


	public String capture(WebDriver driver) throws IOException {
	    File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    File Dest = new File("src/../ErrImages/" + System.currentTimeMillis() + ".png");
	    String errflpath = Dest.getAbsolutePath();
	    FileUtils.copyFile(scrFile, Dest);
	    return errflpath;
    }


    public static long getRandomNumber() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime();
    }
    
}
