package com.jupiter.commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.jupiter.utils.ThreadRunner;

/**
 * 
 * CommandRepository class contains all the commands related to the commands and test functions
 * 
 * 
 * @author Prabath
 *
 */
public class CommandsRepository {

	/**
	 * 
	 * Opens a browser instance and navigate for the @param url
	 * 
	 * @param url
	 * @param waitTime
	 */
	public void openBrowser(final String url, final long waitTime){
		
		String webURL = url;
		doOpenBrowser(webURL, waitTime);
	}
	
	public void openBrowser(final long waitTime){
		
		String webURL = ThreadRunner.configHandler.getRuntimeProperty("APPLICATION_URL");
		
		doOpenBrowser(webURL, waitTime);
	}
	
	public void doOpenBrowser(String url, long waitTime) {
		
		try {
			
			WebDriver driver = ThreadRunner.getExecutorUnit().getDriver();
			driver.manage().window().maximize();
			driver.get(url);
			/*
			 * 
			 * Initial wait time after a page start
			 */
			try {
				
				driver.manage()
						.timeouts()
						.implicitlyWait(waitTime,
								TimeUnit.MILLISECONDS);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			ThreadRunner.reportUtils.addToStepPassed("Open Browser Command : URL : "+url, "PASSED");
		} catch (Exception e) {

			e.printStackTrace();
			ThreadRunner.reportUtils.addToStepFailed("Open Browser Command : URL : "+url, "FAILED");
		}
		
		
	}
	
	/**
	 * 
	 * Click function on a web element
	 * 
	 * 
	 * @param element
	 */
	public void click(By element) {

		try {

			WebDriver driver = ThreadRunner.getExecutorUnit().getDriver();
			driver.findElement(element).click();

			ThreadRunner.reportUtils.addToStepPassed("Click Command : element : " + element.toString(), "PASSED");
		} catch (Exception e) {

			e.printStackTrace();
			ThreadRunner.reportUtils.addToStepFailed("Click Command : element : " + element.toString(), "FAILED");
		}
	}

	/**
	 * 
	 * Type function of a web element
	 * 
	 * @param element
	 * @param value
	 */
	public void type(By element, String value) {
		
		try {

			WebDriver driver = ThreadRunner.getExecutorUnit().getDriver();
			driver.findElement(element).sendKeys(value);

			ThreadRunner.reportUtils.addToStepPassed("Type Command : element : " + element.toString(), "PASSED");
		} catch (Exception e) {

			e.printStackTrace();
			ThreadRunner.reportUtils.addToStepFailed("Type Command : element : " + element.toString(), "FAILED");
		}
	}
	
	/**
	 * 
	 * Is element present conditional command
	 * 
	 * 
	 * @param element
	 * @param expected
	 * @param waitTime
	 */
	public void isElementPresent(By element, boolean expected, long waitTime) {
	
		boolean isElementPresent = false;
		try {

			
			try {
			
				WebDriver driver = ThreadRunner.getExecutorUnit().getDriver();
				FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
				        .withTimeout(waitTime, TimeUnit.SECONDS)
				        .pollingEvery(200, TimeUnit.MILLISECONDS)
				        .ignoring(NoSuchElementException.class);
				
				WebElement webElement = fluentWait.until(ExpectedConditions.presenceOfElementLocated(element));
				if(webElement!=null) {
					
					isElementPresent = true;
				}
				
			} catch (Exception e) {

				isElementPresent = false;
			}
			
			
			if(expected == isElementPresent) {
				ThreadRunner.reportUtils.addToStepPassed("Is Element Present Command : element : " + element.toString()+ " Actual : "+isElementPresent+" Expected : "+expected, "PASSED");
			} else {
				
				ThreadRunner.reportUtils.addToStepFailed("Is Element Present Command : element : " + element.toString()+ " Actual : "+isElementPresent+" Expected : "+expected, "FAILED");
			}
			
		} catch (Exception e) {
			
			ThreadRunner.reportUtils.addToStepFailed("Is Element Present Command : element : " + element.toString()+ " Actual : "+isElementPresent+" Expected : "+expected, "FAILED");
		}
	}
	
	/**
	 * 
	 * Wait a specific element to be visible using selenium Fluent Wait
	 * 
	 * @param element
	 * @param waitTime
	 */
	public void waitTillElementLoad(By element, long waitTime) {
		
		try {
			
			WebDriver driver = ThreadRunner.getExecutorUnit().getDriver();
			FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
			        .withTimeout(waitTime, TimeUnit.SECONDS)
			        .pollingEvery(200, TimeUnit.MILLISECONDS)
			        .ignoring(NoSuchElementException.class);
			
			WebElement webElement = fluentWait.until(ExpectedConditions.presenceOfElementLocated(element));
			
		} catch (Exception e) {
			

		}
	}
	
	public String getElementString(By element, long waitTime) {

		try {

			WebDriver driver = ThreadRunner.getExecutorUnit().getDriver();
			FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).withTimeout(waitTime, TimeUnit.SECONDS)
					.pollingEvery(200, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

			WebElement webElement = fluentWait.until(ExpectedConditions.presenceOfElementLocated(element));
			ThreadRunner.reportUtils.addToStepPassed("Get Element String Command : element : " + element.toString()+" value : "+webElement.getText(), "PASSED");
			return webElement.getText();
		} catch (Exception e) {
			
			ThreadRunner.reportUtils.addToStepFailed("Get Element String Command : element : " + element.toString()+" value : ", "FAILED");
			return "";
		}
	}
	
	public void StoreValue(String key, String value) {

		String projectPropertiesLocation = "data.properties";
		File file = new File(projectPropertiesLocation);
		FileInputStream fis = null;
		FileOutputStream fos = null;
		Properties prop = new Properties();

		try {

			fis = new FileInputStream(file.getAbsoluteFile());
			prop.load(fis);
			prop.setProperty(key, value);
			fos = new FileOutputStream(projectPropertiesLocation);
			prop.store(fos, "project settings");
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	public String retrieveValue(String key) {
		String projectPropertiesLocation = "data.properties";
		Properties prop = new Properties();
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(projectPropertiesLocation);
			prop.load(fis);
			 return prop.getProperty(key);
		} catch (IOException e) {

			return "";
		}
	}
}
