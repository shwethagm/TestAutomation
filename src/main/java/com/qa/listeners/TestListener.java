package com.qa.listeners;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.extentreports.ReportManager;
import com.qa.util.LoggerUtil;

public class TestListener implements ITestListener {
	private Logger log = LoggerUtil.getLogger();
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
		reportManager.startTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		log.info("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
		reportManager.reportTestSuccess();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		log.info("*** Test execution " + result.getMethod().getMethodName() + " failed...");
		reportManager.reportTestFailure();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		log.info("*** Test " + result.getMethod().getMethodName() + " skipped...");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		log.info("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}

}