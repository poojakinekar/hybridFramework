package com.vtiger.test;

import org.testng.annotations.Test;

import com.vtiger.pages.LeadPage;
import com.vtiger.pages.LoginPage;

public class E2E extends BaseTest{
	
	@Test
	public void CreateLeadWithmandatorydata_TC04()
	{   
		logger=extent.createTest("CreateLeadWithmandatorydata_TC04");
		LoginPage lp=new LoginPage(driver,logger);
		lp.validLogin(DataList.get(3).get("CreateLeadWithmandatorydata_TC04").get(0), DataList.get(3).get("CreateLeadWithmandatorydata_TC04").get(1));
		LeadPage ldp=new LeadPage(driver, logger);
		ldp.cliknewLead();
		ldp.createLeadwithmandatoryfields(DataList.get(3).get("CreateLeadWithmandatorydata_TC04").get(0), DataList.get(3).get("CreateLeadWithmandatorydata_TC04").get(1)); 
		ldp.clickLogout();
		extent.flush();
	}
	
}
