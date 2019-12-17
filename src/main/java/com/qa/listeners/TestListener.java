package com.qa.listeners;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.qa.extentreports.ReportManager;
import com.qa.util.LoggerUtil;
import com.qa.util.ScreenshotHelper;

public class TestListener implements ITestListener {
	Logger log = LoggerUtil.getLogger();
	ExtentTest extentTest;
	static ReportManager reportManager = new ReportManager();
	static ScreenshotHelper screenshotHelper = new ScreenshotHelper();

	public void onStart(ITestContext context) {
		log.info("*** Test Suite " + context.getName() + " started ***");
	}

	public void onFinish(ITestContext context) {
		log.info(("*** Test Suite " + context.getName() + " ending ***"));
		reportManager.flush();
	}

	public void onTestStart(ITestResult result) {
		log.info(("*** Running test method " + result.getMethod().getMethodName() + "..."));
		extentTest = reportManager.startTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		log.info("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
		extentTest.log(Status.PASS, "Test passed");

		String testClassName = getTestClassName(result.getInstanceName()).trim();
		String testMethodName = result.getName().toString().trim();

		String screenshotPath = screenshotHelper.takeScreenshot(testClassName, testMethodName);
		attachScreenshotToReport(screenshotPath, true);
	}

	public void onTestFailure(ITestResult result) {
		log.info("*** Test execution " + result.getMethod().getMethodName() + " failed...");
		extentTest.log(Status.FAIL, "Test Failed");
		
		String testClassName = getTestClassName(result.getInstanceName()).trim();
		String testMethodName = result.getName().toString().trim();

		String screenshotPath = screenshotHelper.takeScreenshot(testClassName, testMethodName);
		attachScreenshotToReport(screenshotPath, false);
	}

	public void onTestSkipped(ITestResult result) {
		log.info("*** Test " + result.getMethod().getMethodName() + " skipped...");
		extentTest.log(Status.SKIP, "Test Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		log.info("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}

	private void attachScreenshotToReport(String path, boolean testPassed) {
		if (path.isEmpty()) {
			log.info("Screenshot path empty");
			return;
		}
		// attach screenshots to report
		try {
			if (testPassed == true)
				extentTest.pass("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			else
				extentTest.fail("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		} catch (IOException e) {
			log.info("An exception occured while attaching screenshot " + e.getCause());
		}
	}
	
	private String getTestClassName(String testName) {
		log.info(" testName : " + testName);

		String[] reqTestClassname = testName.split("\\.");

		int i = reqTestClassname.length - 1;
		log.info("Required Test Name : " + reqTestClassname[i]);
		return reqTestClassname[i];
	}


}