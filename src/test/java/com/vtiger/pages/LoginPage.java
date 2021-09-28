package com.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.vtiger.common.Utilities;

public class LoginPage {
	private WebDriver driver;
	public Utilities utils;
	
	
	
	public LoginPage(WebDriver driver, ExtentTest logger)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		utils=new Utilities(driver,logger);
		
	}
	@FindBy(name="user_name")
	WebElement tb_username;
	
	@FindBy(name="user_password")
	WebElement tb_userpwd;
	
	@FindBy(name="Login")
	WebElement btn_login;
	
	@FindBy(xpath="//img[@src='include/images/login_left12.gif']")
	WebElement img_logo;
	
	@FindBy(xpath="//*[contains(text(), 'You mmust specify a valid username and password.')]")
	WebElement msg_error;
	/*
	By tb_username=By.name("user_name");
	By tb_userpwd=By.name("user_password");
	By btn_login=By.name("Login");
	*/
	public void validLogin(String userid, String pwd)
	{
		utils.setInputText(tb_username, userid, "username field");
		utils.setInputText(tb_userpwd, pwd, "password field");
		//utils.ClickElement(btn_login, "login button");
		utils.ClickElement(btn_login, "login button");
		
		//tb_userpwd.sendKeys(pwd);
		//btn_login.click();
	}
	public boolean validateLogo()
	{
		//return img_logo.isDisplayed();
		return utils.IsElementDisplay(img_logo, "Logo");
	}
	
	public boolean varifyerrormessage() {
		// TODO Auto-generated method stub
		return utils.IsElementDisplay(msg_error, "Error Message");
	}

}
