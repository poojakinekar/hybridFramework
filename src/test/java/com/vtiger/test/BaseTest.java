package com.vtiger.test;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.vtiger.common.Xls_Reader;

//import collectionsExample.Xls_Reader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	
	public static WebDriver driver;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static Properties Prop;
	public static List<Map<String, List<String>>> DataList;
	
	@BeforeSuite
	public void launchApp()
	{
		DataList=loadTestData();
		
		readProperties();
		createExtentReport();
		if(Prop.getProperty("browser").equals("chrome"))
		{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		} 
		else if(Prop.getProperty("browser").equals("firefox"))
		{
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
		}
		else if(Prop.getProperty("browser").equals("Edge"))
		{
		WebDriverManager.edgedriver().setup();
		driver=new EdgeDriver();
		}
		else if(Prop.getProperty("browser").equals("IE"))
		{
		WebDriverManager.iedriver().setup();
		driver=new InternetExplorerDriver();
		}
		driver.get(Prop.getProperty("AppUrl"));
		driver.manage().window().maximize();
	}
	
	public void readProperties()
	{
		Prop=new Properties();
		try {
			FileInputStream FIS=new FileInputStream(System.getProperty("user.dir")+"/src/test\\java/com/vtiger/config/projectSetting.properties");
			Prop.load(FIS);
		    } 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	public void createExtentReport()
	{
		DateFormat fmat=new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
		Date d=new Date();
		String str=fmat.format(d);
		System.out.println(str);
		String ReportPath=System.getProperty("user.dir")+"/src/test/java/com/vtiger/report/";
		String ReportName="VtigerAutomationresult_" +str+".html";
		htmlReporter=new ExtentHtmlReporter(ReportPath+ReportName);
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("HostName", "Automation Test Hub");
		extent.setSystemInfo("Environment", "Test");
		extent.setSystemInfo("user name", "PoojaK");
		htmlReporter.config().setDocumentTitle("Title of the report comes here");
		htmlReporter.config().setReportName("name of the report comes here");
		htmlReporter.config().setTheme(Theme.STANDARD);
		 
		
	}
	public List<Map<String, List<String>>> loadTestData()
	{
		List<Map<String, List<String>>> ls=new ArrayList<Map<String, List<String>>>();
		
		Xls_Reader xr=new Xls_Reader(System.getProperty("user.dir")+"/src/test/resources/Testdata/Data.xlsx");
		int rowcount=xr.getRowCount("Sheet1");
		int colcount=xr.getColumnCount("Sheet1");
		for(int i=2; i<=rowcount; i++)
		{
			Map<String, List<String>> m=new HashMap<String, List<String>>();
			List<String> lst=new ArrayList<String>();
			String vTCname=xr.getCellData("Sheet1", "TestCaseName ", i).trim();
			for(int j=1; j<+colcount; j++)
			{
				String vData=xr.getCellData("Sheet1", j, i).trim();
				lst.add(vData);
			}
			m.put(vTCname, lst);
			ls.add(m);
		}
		return ls;
	}
	
	
	@AfterSuite
	public void closeapp()
	{
	driver.quit();	
	}

}
