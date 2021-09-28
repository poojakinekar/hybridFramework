package com.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderPage {
	private WebDriver driver;
	public HeaderPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(linkText="New Lead")
	WebElement lnk_newlead;
	
	@FindBy(linkText="Logout")
	WebElement lnk_logout;
	
	public void clickLogout()
	{
		lnk_logout.click();
	}
	public void cliknewLead()
	{
		lnk_newlead.click();
	}
	public boolean varifyLogout()
	{
		return lnk_logout.isDisplayed();
	}
}
