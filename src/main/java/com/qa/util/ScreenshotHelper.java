package com.qa.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.qa.base.TestBase;

public class ScreenshotHelper {
	Logger log = LoggerUtil.getLogger();

	public String takeScreenshot(String testClassName, String testMethodName) {
		String timeStamp = getCurrentTimeStamp();
		String screenShotName = timeStamp + testMethodName + ".png";
		String fileSeperator = System.getProperty("file.separator");
		String screenShotDir = System.getProperty("user.dir") + fileSeperator + "screenshots";
		String screenshotPath;
		try {
			File file = new File(screenShotDir + fileSeperator + testClassName);
			if (!file.exists()) {
				if (file.mkdirs()) {
					log.info("Directory: " + file.getAbsolutePath() + " is created!");
				} else {
					log.info("Failed to create directory: " + file.getAbsolutePath());
				}
			}
			WebDriver driver = TestBase.getDriver();
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			screenshotPath = screenShotDir + fileSeperator + testClassName + fileSeperator + screenShotName;
			File targetFile = new File(screenshotPath);
			log.info("Target File location - " + targetFile.getAbsolutePath());
			FileHandler.copy(screenshotFile, targetFile);
		} catch (FileNotFoundException e) {
			log.info("File not found exception occurred while taking screenshot " + e.getMessage());
			screenshotPath = "";
		} catch (Exception e) {
			log.info("An exception occurred while taking screenshot " + e.getCause());
			screenshotPath = "";
		}

		return screenshotPath;
	}


	private String getCurrentTimeStamp() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH_mm__dd_MM_yyyy__");
		String formattedDate = sdf.format(date);
		return formattedDate;
	}

}
