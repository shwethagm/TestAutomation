package com.qa.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.qa.base.TestBase;

public class ScreenshotHelper {
	private static Logger log = LoggerUtil.getLogger();

	public String takeSnapshot(String msg) {
		String snapFilename = getCurrentTimeStamp() + msg + ".png";
		String fileSeperator = System.getProperty("file.separator");
		String snapsDirName = System.getProperty("user.dir") + fileSeperator + "TestReport" + fileSeperator
				+ "snapshots";
		String snapshotPath;
		try {
			File snapsDir = new File(snapsDirName);
			if (!snapsDir.exists()) {
				if (snapsDir.mkdirs()) {
					log.info("Directory: " + snapsDir.getAbsolutePath() + " is created!");
				} else {
					log.info("Failed to create directory: " + snapsDir.getAbsolutePath());
				}
			}
			WebDriver driver = TestBase.getDriver();
			File snapshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			snapshotPath = snapsDirName + fileSeperator + snapFilename;
			File targetFile = new File(snapshotPath);
			FileHandler.copy(snapshotFile, targetFile);
		} catch (Exception e) {
			log.info("An exception occurred while taking screenshot " + e.getMessage());
			snapshotPath = "";
		}

		String relativePath = "snapshots" + fileSeperator + snapFilename;
		log.info("snapshotPath=" + relativePath);
		return relativePath;
	}

	private static String getCurrentTimeStamp() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH_mm_ss.SSS ");
		String formattedDate = sdf.format(date);
		return formattedDate;
	}

}
