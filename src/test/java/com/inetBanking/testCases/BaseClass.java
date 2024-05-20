package com.inetBanking.testCases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetBanking.utilities.ReadConfig;

public class BaseClass {
    ReadConfig readConfig = new ReadConfig();

    public String BaseURL = readConfig.getApplictionURL();
    public String Username = readConfig.getUsername();
    public String Password = readConfig.getPassword();
    public static WebDriver driver;
    public static Logger logger;

    @Parameters("browser")
    @BeforeClass
    public void setup(String br) {
        logger = LogManager.getLogger();
        if (br.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (br.equals("edge")) {
            driver = new EdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(BaseURL);
        driver.manage().window().maximize();
        // Handle consent window
        handleGDPRConsentNotice();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    // Method to handle GDPR consent notice
    private void handleGDPRConsentNotice() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("gdpr-consent-notice"));
            driver.findElement(By.id("save")).click();
            driver.switchTo().defaultContent();
            System.out.println("GDPR consent notice handled");
        } catch (Exception e) {
            System.out.println("Error handling GDPR consent notice: " + e.getMessage());
        }
    }

    public void captureScreen(WebDriver driver, String tname) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
        FileUtils.copyFile(source, target);
        System.out.println("Screenshot Taken");
    }
	public String randomString() {
		String randomStr=RandomStringUtils.randomAlphabetic(8);
		return randomStr;
	}

}
