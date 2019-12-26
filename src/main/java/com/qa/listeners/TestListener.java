package com.qa.listeners;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qa.extentreports.ReportManager;
import com.qa.util.LoggerUtil;

public class TestListener implements ITestListener {
	private Logger log = LoggerUtil.getLogger();
	private ExtentTest extentTest;
	private static ReportManager reportManager = ReportManager.getInstance();

	@Override
	public void onStart(ITestContext context) {
		log.info("*** Test Suite " + context.getName() + " started ***");
	}

	@Override
	public void onFinish(ITestContext context) {
		log.info(("*** Test Suite " + context.getName() + " ending ***"));
		reportManager.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		log.info(("*** Running test method " + result.getMethod().getMethodName() + "..."));
		extentTest = reportManager.startTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		log.info("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
		reportManager.takeSnapshotAndAttachToReport("Test passed");
		extentTest.log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		log.info("*** Test execution " + result.getMethod().getMethodName() + " failed...");
		reportManager.takeSnapshotAndAttachToReport("Test Failed");
		extentTest.log(Status.FAIL, "Test Failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		log.info("*** Test " + result.getMethod().getMethodName() + " skipped...");
		extentTest.log(Status.SKIP, "Test Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		log.info("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}

	private String getTestClassName(String testName) {
		log.info(" testName : " + testName);

		String[] reqTestClassname = testName.split("\\.");

		int i = reqTestClassname.length - 1;
		log.info("Required Test Name : " + reqTestClassname[i]);
		return reqTestClassname[i];
	}

}