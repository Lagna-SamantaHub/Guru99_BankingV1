package com.inetBanking.pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class LoginPage {
	
	WebDriver ldriver;
    WebDriverWait wait;
	public LoginPage(WebDriver rdriver){
		ldriver=rdriver;
		PageFactory.initElements( rdriver,this);
		wait=new WebDriverWait(ldriver,Duration.ofSeconds(10));
	}
	@FindBy(xpath= "//input[@name='uid']")
	WebElement txtUserName;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement txtPassword;
	
	@FindBy(name="btnLogin")
	WebElement loginBtn;
	@FindBy(xpath="//a[normalize-space()='Log out']")
	WebElement logoutBtn;
	public void SetUserName(String uname) {
		wait.until(ExpectedConditions.visibilityOf(txtUserName));
		txtUserName.sendKeys(uname);
	}
	public void SetPassword(String pname) {
		wait.until(ExpectedConditions.visibilityOf(txtPassword));
		txtPassword.sendKeys(pname);
	}
	public void ClickLogin() {
		wait.until(ExpectedConditions.visibilityOf(loginBtn));
		loginBtn.click();
	}
	public void ClickLogout() {
		//((JavascriptExecutor) ldriver).executeScript("arguments[0].scrollIntoView(true);", logoutBtn);
        wait.until(ExpectedConditions.visibilityOf(logoutBtn));
		logoutBtn.click();
	}



}
