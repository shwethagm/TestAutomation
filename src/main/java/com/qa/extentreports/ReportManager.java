package com.qa.extentreports;

import java.io.File;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.util.LoggerUtil;
import com.qa.util.ScreenshotHelper;

public class ReportManager {
	static ExtentReports extentReport = new ExtentReports();
	final static String reportFileName = "Test-Automaton-Report" + ".html";
	final static String fileSeperator = System.getProperty("file.separator");
	final static String reportFilepath = System.getProperty("user.dir") + fileSeperator + "TestReport";
	final static String reportFileLocation = reportFilepath + fileSeperator + reportFileName;
	private static Logger log = LoggerUtil.getLogger();
	private static ReportManager reportManager = null;
	ExtentTest extentTest = null;

	private void configureHtmlReport() {
		String fileName = getReportPath(reportFilepath);
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle(reportFileName);
		htmlReporter.config().setReportName(reportFileName);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		extentReport.attachReporter(htmlReporter);
	}

	private ReportManager() {
		log.info("");
	}

	// singleton
	public static ReportManager getInstance() {
		if (reportManager == null) {
			reportManager = new ReportManager();
			reportManager.configureHtmlReport();
		}
		return reportManager;

	}

	public ExtentTest startTest(String testName) {
		extentTest = extentReport.createTest(testName);
		return extentTest;
	}

	public void flush() {
		extentReport.flush();
	}

	private void attachScreenshotToReport(String msg, String path) {
		if (path.isEmpty()) {
			log.info("Screenshot path empty");
			return;
		}
		log.info("msg=" + msg + " path=" + path);
		try {
			extentTest.log(Status.INFO, msg, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		} catch (Exception e) {
			log.info("exception = " + e.getMessage());
			log.info("exception = " + e.toString());
		}
	}

	public void takeSnapshotAndAttachToReport(String msg) {
		String screenshotPath = ScreenshotHelper.takeSnapshot(msg);
		attachScreenshotToReport(msg, screenshotPath);
	}

	private String getReportPath(String path) {
		File testDirectory = new File(path);
		if (!testDirectory.exists()) {
			if (testDirectory.mkdir()) {
				log.info("Directory: " + path + " is created!");
				return reportFileLocation;
			} else {
				log.info("Failed to create directory: " + path);
				return System.getProperty("user.dir");
			}
		} else {
			log.info("Directory already exists: " + path);
		}
		return reportFileLocation;
	}

}