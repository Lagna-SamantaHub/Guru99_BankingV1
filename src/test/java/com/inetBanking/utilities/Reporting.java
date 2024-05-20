package com.inetBanking.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {

    public ExtentSparkReporter spark;
    public ExtentReports extent;
    public ExtentTest logger;
    private static final Logger log = LogManager.getLogger(Reporting.class);

    @Override
    public void onStart(ITestContext testContext) {
        log.debug("Test execution started");
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String repName = "Extent-Test-Report-" + timeStamp + ".html";

        spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/" + repName);
        extent = new ExtentReports();
        extent.attachReporter(spark);

        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("inetBanking Test Report");
        spark.config().setReportName("Functional Test Automation Report");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        log.debug("Test passed: " + tr.getName());
        logger = extent.createTest(tr.getName());
        logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        log.error("Test failed: " + tr.getName(), tr.getThrowable());
        logger = extent.createTest(tr.getName());
        logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));

        String screenShotPath = System.getProperty("user.dir") + "\\Screenshots\\" + tr.getName() + ".png";
        File f = new File(screenShotPath);
        if (f.exists()) {
            try {
                logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenShotPath));
            } catch (Exception e) {
                log.error("Failed to attach screenshot", e);
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        log.warn("Test skipped: " + tr.getName());
        logger = extent.createTest(tr.getName());
        logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
    }

    @Override
    public void onFinish(ITestContext testContext) {
        log.debug("Test execution finished");
        extent.flush();
        log.debug("Flushed report");
    }
}
