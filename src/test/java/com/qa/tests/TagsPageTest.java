package com.qa.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.TagsPage;

public class TagsPageTest extends TestBase {
	private WebDriver driver = null;
	TagsPage tagsPage = null;

	public TagsPageTest() {
		log.info(" TagsPageTest() constructor");
	}

	@BeforeTest
	public void setUp() {
		driver = getDriver();
		log.info("@@BeforeTest driver=" + driver);
		tagsPage = new TagsPage(driver);
	}

	@BeforeClass
	public void beforeClassTagsPagetest() {
		log.info("@BeforeClass");
	}

	@Test
	public void TestNavigationFromTagsPageToUserPage() {
		log.info(" ");
		tagsPage.navigateFromTagsPageToOpenPage();
		Assert.assertTrue(true);
	}

	@AfterTest
	public void AfterTestTagspage() {
		log.info("@AfterTest");
	}

	@AfterClass
	public void AfterClassTagsPage() {
		log.info("@@AfterClass");
	}

}
