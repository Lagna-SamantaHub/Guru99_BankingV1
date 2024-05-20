package com.inetBanking.testCases;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestaddCust {

	public static void main(String[] args) {
		ChromeDriver driver=new ChromeDriver();
		driver.navigate().to("https://demo.guru99.com/v4/index.php"); 
		driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Wait for the GDPR consent notice to appear
            WebElement gdprConsentNotice = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("gdpr-consent-notice")));
            driver.switchTo().frame(gdprConsentNotice);
            // Click on the save button inside the iframe
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='save']")));
            saveButton.click();
        } catch (Exception e) {
            System.out.println("Exception occurred while handling GDPR consent notice: " + e.getMessage());
        }

        try {
            // Switch back to the default content
            driver.switchTo().defaultContent();
            // Wait for the username input field to be visible
            WebElement userNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='uid']")));
            userNameInput.sendKeys("mngr571129");
            // Locate and enter password
            WebElement passwordInput = driver.findElement(By.xpath("//input[@name='password']"));
            passwordInput.sendKeys("YmAdEqu");
            // Locate and click login button
            WebElement loginButton = driver.findElement(By.name("btnLogin"));
            loginButton.click();
        } catch (Exception e) {
            System.out.println("Exception occurred during login process: " + e.getMessage());
        }
        try {
        	driver.findElement(By.xpath("//a[normalize-space()='New Customer']")).click();
        	Thread.sleep(2000);
            // Locate the Google ad iframe using its ID
            /*WebElement googleAdIframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("google_ads_iframe_/24132379/INTERSTITIAL_DemoGuru99_0")));
            driver.switchTo().frame(googleAdIframe);

            // Now you are inside the Google ad iframe, you can interact with elements as needed
            // Find and click the close button using its class name
            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"dismiss-button\"]")));
            closeButton.click();*/
        } catch (Exception e) {
            System.out.println("Exception occurred while handling Google ad iframe: " + e.getMessage());
        } 
	}

}
