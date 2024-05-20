package com.inetBanking.testCases;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestLogin {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://demo.guru99.com/V1/");
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
            // Print the title of the page
            System.out.println("Page Title: " + driver.getTitle());
        } catch (Exception e) {
            System.out.println("Exception occurred during login process: " + e.getMessage());
        } finally {
            // Quit the driver
            driver.quit();
        }
    }
}

