package com.inetBanking.testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.AddCustomer;
import com.inetBanking.pageObjects.LoginPage;

import java.io.IOException;
import java.time.Duration;

public class TC_addCustomerTest_003  extends BaseClass{
	public static final int WAIT_TIMEOUT_SECONDS = 10;
	@Test
	public void addNewCustomer() throws IOException {
		LoginPage lp=new LoginPage(driver);	
		lp.SetUserName(Username);
		lp.SetPassword(Password);
		lp.ClickLogin();
		
		AddCustomer ac=new AddCustomer(driver);
		ac.ClickNewCustBtn();
		
		ac.custName("Vi");
		ac.selectGender();
		ac.custDOB("02", "04", "2005");
		ac.custAddress("India");
		ac.custCity("Pune");
		ac.custState("MAH");
		ac.cPincode(123456);
		ac.custTele("123456777");
		String email=randomString()+"@gmail.com";
		ac.custEmail(email);
		ac.custpass("1234");
		ac.clickSubmit();
		boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");
		if (res==true) {
			logger.info("Test passed");
			Assert.assertTrue(true);
		}
		else {
			logger.info("Test Failed");
			captureScreen(driver,"addNewCustomer");
			Assert.assertTrue(false);
			
		}
		
	}
	

}
