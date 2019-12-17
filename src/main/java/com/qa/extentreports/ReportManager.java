package com.qa.extentreports;

import java.io.File;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.util.LoggerUtil;

public class ReportManager {
	static ExtentReports extentReport = new ExtentReports();
	final static String reportFileName = "Test-Automaton-Report" + ".html";
	final static String fileSeperator = System.getProperty("file.separator");
	final static String reportFilepath = System.getProperty("user.dir") + fileSeperator + "TestReport";
	final static String reportFileLocation = reportFilepath + fileSeperator + reportFileName;
	static Logger log = LoggerUtil.getLogger();

	public ReportManager() {
		createInstance();
	}

	private void createInstance() {
		String fileName = getReportPath(reportFilepath);
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle(reportFileName);
		htmlReporter.config().setReportName(reportFileName);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		extentReport.attachReporter(htmlReporter);
	}

	public ExtentTest startTest(String testName) {
		return extentReport.createTest(testName);
	}

	public void flush() {
		extentReport.flush();
	}

	// Create the report path
	private static String getReportPath(String path) {
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