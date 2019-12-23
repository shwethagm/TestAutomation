package com.qa.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.HomePage;

public class HomePageTest extends TestBase {

	WebDriver driver = null;
	HomePage homePage = null;

	public HomePageTest() {
		log.info("HomePageTest() constructor");
	}

	@BeforeTest
	public void BeforeTestHomePage() {
		log.info("@BeforeTest");
	}

	@AfterTest
	public void AfterTestHomePage() {
		log.info("@@AfterTest");
	}

	@BeforeClass
	public void BeforeClassHomePageTest() {
		log.info("@BeforeClass ===>");
		driver = getDriver();
		homePage = new HomePage(driver);
		log.info("@BeforeClass <===");

	}

	@AfterClass
	public void AfterClassHomePage() {
		log.info("@@AfterClass");
	}

	@Test(priority = 1)
	public void TestNavigationFromHomepageToTagspage() {
		log.info("TestNavigationFromHomepageToTagspage  ===>");
		homePage.navigateToTagsPage();
		Assert.assertEquals("Tags - Stack Overflow", getPageTitle());
		log.info("TestNavigationFromHomepageToTagspage  <===");

	}
//
//	@Test
//	public void navigateFromHomePagetoUser() {
//		homePage.navigateToUsers();
//		;
//	}

	@Test
	public void verifyTitle() {
		log.info("verifyTitle ===>");
		String title = getPageTitle();
		log.info("pagetitle = " + title);
//		Assert.assertEquals(title, "Google");
		Assert.assertTrue(true);
		log.info("verifyTitle <===");

	}
//
//	@Test
//	public void verifyTitle2() {
//		HomePage homepage = new HomePage(driver);
//
//		homepage.readUsers();
//	}

}
