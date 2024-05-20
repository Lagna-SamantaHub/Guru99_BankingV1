package com.inetBanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;
import com.inetBanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass {

    @Test(dataProvider = "LoginData")
    public void loginDDT(String user, String pwd) {
        LoginPage lp = new LoginPage(driver);
        logger.info("Testing login for user: " + user);

        try {
            lp.SetUserName(user);
            lp.SetPassword(pwd);
            lp.ClickLogin();

            // Check if login failed
            if (isAlertPresent()==true) {
                driver.switchTo().alert().accept(); // Close the alert
                driver.switchTo().defaultContent(); // Switch back to the main content
                Assert.assertTrue(false);
                logger.warn("Login failed for user: " + user);
                
                // Mark the test as failed
              //  throw new AssertionError("Login failed for user: " + user);
            } else {
                //logger.info("Login passed for user: " + user);
                lp.ClickLogout();
                logger.info("Login Passed");
                driver.switchTo().alert().accept(); // Close the logout alert
                driver.switchTo().defaultContent(); // Switch back to the main content
            }
        } catch (Exception e) {
            //logger.error("An unexpected error occurred for user: " + user + " with error: " + e.getMessage());
        }
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @DataProvider(name = "LoginData")
    String[][] getData() throws IOException {
        String path = System.getProperty("user.dir") + "/src/test/java/com/inetBanking/testData/LoginDetails.xlsx";
        int rownum = XLUtils.getRowCount(path, "Sheet1");
        int colcount = XLUtils.getCellCount(path, "Sheet1", 1);

        String logindata[][] = new String[rownum][colcount];
        for (int i = 1; i <= rownum; i++) {
            for (int j = 0; j < colcount; j++) {
                logindata[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
            }
        }
        return logindata;
    }
}
