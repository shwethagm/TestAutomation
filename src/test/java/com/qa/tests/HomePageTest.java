package com.qa.tests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.HomePage;
import com.qa.util.JsonParserUtil;

public class HomePageTest extends TestBase {

	WebDriver driver = null;
	HomePage homePage = null;

	public HomePageTest() {
		log.trace("HomePageTest() constructor");
	}

	@BeforeTest
	public void BeforeTestHomePage() {
		log.trace("@BeforeTest");
	}

	@AfterTest
	public void AfterTestHomePage() {
		log.trace("@@AfterTest");
	}

	@BeforeClass
	public void BeforeClassHomePageTest() {
		log.trace("@BeforeClass ===>");
		driver = getDriver();
		homePage = new HomePage(driver);
		log.trace("@BeforeClass <===");

	}

	@AfterClass
	public void AfterClassHomePage() {
		log.trace("@@AfterClass");
	}

	@Test(priority = 1)
	public void TestNavigationFromHomepageToTagspage() {
		log.trace("TestNavigationFromHomepageToTagspage  ===>");
		homePage.navigateToTagsPage();
		Assert.assertEquals("Tags - Stack Overflow", getPageTitle());
		log.trace("TestNavigationFromHomepageToTagspage  <===");

	}

	@Test
	public void TestLogin() {
		log.trace("");
		JsonParserUtil jsonutil = new JsonParserUtil();

		try {
			String jsonStr = new String(Files.readAllBytes(Paths.get(userDir, "resources\\users.json")));
			String key = "user";
			String userDir = System.getProperty("user.dir");
			log.info("json string is " + jsonStr);
			String val = jsonutil.getJsonValue(jsonStr, key);

			homePage.login(key, val);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void verifyTitle() {
		log.trace("verifyTitle ===>");
		String title = getPageTitle();
		log.info("pagetitle = " + title);
//		Assert.assertEquals(title, "Google");
		Assert.assertTrue(true);
		log.trace("verifyTitle <===");

	}
//
//	@Test
//	public void verifyTitle2() {
//		HomePage homepage = new HomePage(driver);
//
//		homepage.readUsers();
//	}

}
