package com.inetBanking.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;


public class TC_LoginTest_001 extends BaseClass {
    @Test
    public void loginTest() {
        try {
            driver.navigate().to(BaseURL);
            driver.manage().window().maximize();
            logger.info("URL opened");

            LoginPage lp = new LoginPage(driver);
            lp.SetUserName(Username);
            logger.info("Username entered");
            lp.SetPassword(Password);
            logger.info("Password entered");
            lp.ClickLogin();
            logger.info("Login button clicked");

            String pageTitle = driver.getTitle();
            logger.info("Page title: " + pageTitle);
            
            if(driver.getTitle().equals(pageTitle)) {
            Assert.assertEquals(pageTitle, "Guru99 Bank Manager HomePage", "Page title does not match expected title: Guru99 Bank Manager HomePage");
            logger.info("Login test passed");
            }else {
            	captureScreen(driver,"loginTest");
            	Assert.assertTrue(false);
            	logger.info("Login test failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception occurred: " + e.getMessage());
            Assert.fail("Exception occurred: " + e.getMessage());
        }
    }


}
