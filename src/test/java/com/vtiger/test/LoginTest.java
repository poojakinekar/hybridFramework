 package com.vtiger.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.vtiger.pages.HeaderPage;
import com.vtiger.pages.LoginPage;

public class LoginTest extends BaseTest{

	
	@Test
	public void validLogin_TC01()
	{
		logger=extent.createTest("validLogin_TC01");
		LoginPage lp=new LoginPage(driver,logger);
		lp.validLogin(DataList.get(0).get("validLogin_TC01").get(0), DataList.get(0).get("validLogin_TC01").get(1));
		//logger.log(Status.PASS, "Login successfully");
		HeaderPage hp=new HeaderPage(driver);
		boolean val=hp.varifyLogout();
		//Assert.assertEquals(val, true);
		//logger.log(Status.PASS, "logout validated successfully");
		hp.clickLogout();
		extent.flush();
	}
	@Test
	public void InvalidLogin_TC02()
	{
		logger=extent.createTest("InvalidLogin_TC02");
		LoginPage lp=new LoginPage(driver,logger);
		lp.validLogin(DataList.get(1).get("InvalidLogin_TC02").get(0), DataList.get(1).get("InvalidLogin_TC02").get(1));
		//logger.log(Status.PASS, "Error message validated");
		boolean val=lp.varifyerrormessage();
		//Assert.assertEquals(val, true);
		extent.flush();
	}
	
	@Test
	public void VarifyLogo_TC03()
	{
		logger=extent.createTest("VarifyLogo_TC03");
		LoginPage lp=new LoginPage(driver,logger);
		boolean val=lp.validateLogo();
		//Assert.assertEquals(val, true);
		//logger.log(Status.PASS, "Logo displayed");
		extent.flush();
		
	}
	
}
