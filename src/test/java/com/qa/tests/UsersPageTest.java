//package com.qa.tests;
//
//import org.apache.log4j.Logger;
//import org.openqa.selenium.WebDriver;
//import org.testng.annotations.BeforeClass;
//
//import com.qa.base.TestBase;
//import com.qa.pages.HomePage;
//import com.qa.pages.UsersPage;
//import com.qa.util.LoggerUtil;
//
//public class UsersPageTest extends TestBase {
//	private WebDriver driver;
//	private Logger log = LoggerUtil.getLogger();
//	HomePage homePage = new HomePage();
//	UsersPage userPage = new UsersPage();
//
//	@BeforeClass
//	public void setUp() {
//		driver = getDriver();
//		log.info("driver=" + driver);
//	}
//
//	public homePage userPage() {
//
//		
//		userPage.UsersPageOpen();
//		
//		return homePage;
//	}
//
//}
//}
