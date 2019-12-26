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

	public static String takeSnapshot(String msg) {
		String snapshotFilename = getCurrentTimeStamp() + msg + ".png";
		String fileSeperator = System.getProperty("file.separator");
		String snapshotDir = System.getProperty("user.dir") + fileSeperator + "screenshots";
		String snapshotPath;
		try {
			File file = new File(snapshotDir);
			if (!file.exists()) {
				if (file.mkdirs()) {
					log.info("Directory: " + file.getAbsolutePath() + " is created!");
				} else {
					log.info("Failed to create directory: " + file.getAbsolutePath());
				}
			}
			WebDriver driver = TestBase.getDriver();
			File snapshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			snapshotPath = snapshotDir + fileSeperator + snapshotFilename;
			File targetFile = new File(snapshotPath);
			FileHandler.copy(snapshotFile, targetFile);
		} catch (Exception e) {
			log.info("An exception occurred while taking screenshot " + e.getMessage());
			snapshotPath = "";
		}
		log.info("snapshotPath=" + snapshotPath);
		return snapshotPath;
	}

	private static String getCurrentTimeStamp() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH_mm_ss.SSS ");
		String formattedDate = sdf.format(date);
		return formattedDate;
	}

}
