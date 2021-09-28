package com.vtiger.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

public class Utilities {
	
	private WebDriver driver;
	private ExtentTest logger;
	
	public Utilities(WebDriver driver, ExtentTest logger)
	{
	this.driver=driver;	
	this.logger=logger;
	}
	public void setInputText(WebElement elm, String val, String elmName)
	{
		try {
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(elm));
		elm.clear();
		elm.sendKeys(val);
		logger.pass(val+"has been entered successfully in" +elmName);
		} 
		catch(Exception e)
		{
			logger.fail(e.getMessage()+"<span class='label end-time'><a href='"+getScreenShot()+"'>Screenshot</a></span>");
		}
	}
	
	
	public void ClickElement(WebElement btn_login, String elmName) 
	 {
		
		try {
			WebDriverWait wait=new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(btn_login));
			btn_login.click();
			logger.pass(elmName+ "has been clicked successfully");
			} catch(Exception e)
			{
				logger.fail(e.getMessage()+"<span class='label end-time'><a href='"+getScreenShot()+"'>Screenshot</a></span>");
			}
	}
	public boolean IsElementDisplay(WebElement elm, String elmName) 
	 {
		boolean val=false;
		try {
			WebDriverWait wait=new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(elm));
			val=elm.isDisplayed(); 
			logger.pass(elmName+ "is present");
			return val;
			} catch(Exception e)
			{
				logger.fail(e.getMessage()+"<span class='label end-time'><a href='"+getScreenShot()+"'>Screenshot</a></span>");
				return val;
			}
	}
	public String getScreenShot()
	{
		String destination=null;
		try 
		{
			String DateName=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			TakesScreenshot ts=(TakesScreenshot) driver;
			File source=ts.getScreenshotAs(OutputType.FILE);
			destination=System.getProperty("user.dir")+"/src/test/java/com/vtiger/report/screenshot/"+DateName+".png";
			File finalDestination=new File(destination);
			FileUtils.copyFile(source, finalDestination);
		}
		catch(Exception e)  
		{
		
			
		e.printStackTrace();
			
		}
		return destination;
	}
}

