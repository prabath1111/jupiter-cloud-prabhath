package com.jupiter.rutime;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.jupiter.utils.ExecutorUnit;
import com.jupiter.utils.ThreadRunner;
import com.jupiter.utils.cons.BrowserCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExecutionConfig {
	
	
	/**
	 * 
	 * Initiate the report structure for the test
	 * 
	 * 
	 */
	@BeforeTest
	public final void configSetup() {
		
		ThreadRunner.reportUtils.startReport();
	}
	 	/**
		 * Sets the test context.
		 * 
		 * @param method
		 *            the new test context
		 */
		@BeforeMethod
		@Parameters({"url","browser"})
		public final void setup(@Optional("url") final String url, @Optional("browser") final String browser, final Method method) {
			
			ThreadRunner.reportUtils.setTest(ThreadRunner.reportUtils.getExtent().createTest(method.getName(), "Testcase has been started!"));
			ThreadRunner.reportUtils.setBrowser(browser);
			
			WebDriver driver = startBrowserSession(browser);
			
			synchronized (this) {
				
				ExecutorUnit eu = new ExecutorUnit();
				eu.setDriver(driver);
				ThreadRunner.setExecutorUnit(eu);
			}
		}
		
		@AfterTest
		public void tearDown() {

			ThreadRunner.reportUtils.getExtent().flush();
		}

		@AfterMethod
		public void tearDownTest() {

			ThreadRunner.getExecutorUnit().getDriver().quit();
		}

		/**
		 * 
		 * Browser session handling method
		 * 
		 * 
		 * @param browserString
		 * @return
		 */
		private final synchronized WebDriver startBrowserSession(String browserString) {
			
			if ("browser".equals(browserString)) {
				browserString = BrowserCapabilities.BROWSER_CHROME;
			}
			switch (browserString) {
			
			case BrowserCapabilities.BROWSER_CHROME:
				
				ChromeOptions chromeOptions = new ChromeOptions();
				WebDriverManager.chromedriver().setup();
				WebDriver driver = new ChromeDriver(chromeOptions);
				return driver;

			default:
				return null;
			}
		}
}
