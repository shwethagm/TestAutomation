package com.qa.tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.HomePage;
import com.qa.util.LoggerUtil;

public class HomePageTest extends TestBase {

	private WebDriver driver;
	private Logger log = LoggerUtil.getLogger();

	@BeforeClass
	public void setUp() {
		driver = getDriver();
		log.info("driver="+driver);
	}

	@Test
	public void verifyTitle() {
		log.info("Home page test...");
		HomePage homepage = new HomePage(driver);
		String title = homepage.getPageTitle();
		log.info("pagetitle = " + title);

		Assert.assertEquals(title, "Google");
	}

	@Test
	public void verifyTitle2() {
		log.info("Home page test...");
		HomePage homepage = new HomePage(driver);
		String title = homepage.getPageTitle();
		log.info("pagetitle = " + title);

		Assert.assertEquals(title, "Google1");
	}

}
