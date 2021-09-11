package com.jupiter.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportUtils {

    private ExtentHtmlReporter htmlReporter;
    
    private ExtentReports extent;

    private ExtentTest test;
    
    public void startReport() {

    	Date date = new Date();
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/testOutput/"+new SimpleDateFormat("yyyy_mm_dd_HHMMSS").format(date.getDate())+"_test_Report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Extent Report Demo");
        htmlReporter.config().setReportName("Test Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }

    public void addToStepPassed(String message, String status) {
    	
    	  test.log(Status.PASS, MarkupHelper.createLabel(message+" "+status+" ", ExtentColor.GREEN));
    }
    
    public void addToStepFailed(String message, String status) {
    	
  	  test.log(Status.FAIL, MarkupHelper.createLabel(message+" "+status+" ", ExtentColor.RED));
    }
    
	public ExtentHtmlReporter getHtmlReporter() {
		return htmlReporter;
	}

	public void setHtmlReporter(ExtentHtmlReporter htmlReporter) {
		this.htmlReporter = htmlReporter;
	}

	public ExtentReports getExtent() {
		return extent;
	}

	public void setExtent(ExtentReports extent) {
		this.extent = extent;
	}

	public ExtentTest getTest() {
		return test;
	}

	public void setTest(ExtentTest test) {
		this.test = test;
	}
    
	public void setBrowser(String browser) {
		
        extent.setSystemInfo("Browser", browser);
	}
    
}
