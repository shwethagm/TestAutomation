package com.qa.base;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.util.LoggerUtil;

public class TestBase {

	public static WebDriver driver = null;
	public static Logger log = LoggerUtil.getLogger();
	public String userDir = System.getProperty("user.dir");

	public TestBase() {
		log.trace("TestBase() constructor");
	}

	public static WebDriver getDriver() {
		log.trace("getDriver driver=" + driver);
		return driver;
	}

	@BeforeClass
	public void BeforeClassTestBase() {
		log.trace("@BeforeClass TestBase");
	}

	@AfterClass
	public void AfterClassTestBase() {
		log.trace("@AfterClass TestBase");
	}

	@BeforeTest
	public void BeforeTestTestBase() {
		log.trace("@BeforeTest TestBase");
	}

	@AfterTest
	public void AfterTestTestBase() {
		log.trace("@AfterTest TestBase");
	}

	@Parameters({ "browser", "appUrl" })
	@BeforeSuite
	public void initializeTestBaseSetup(String browser, String appUrl) {
		log.info("@@BeforeSuite browser=" + browser + " appUrl=" + appUrl);
		try {
			initDriver(browser, appUrl);
		} catch (Exception e) {
			log.error("Exception stack ....." + e.getStackTrace());
			log.error("Exception String ....." + e.toString());
			log.error("Exception Message....." + e.getMessage());
		}
	}

	@AfterSuite
	public void tearDown() {
		log.info("@@AfterSuite tearDown");
		driver.quit();
	}

	private void initDriver(String browser, String appURL) {
		log.info("initDriver");
		switch (browser) {
		case "Chrome":
			initializeChrome(appURL);
			break;
		case "Firefox":
			initializeFirefox(appURL);
			break;
		case "Edge":
			initializeEdge(appURL);
			break;
		case "IE":
			initializeIE(appURL);
			break;
		}
	}

	private void initializeChrome(String appURL) {
		log.info("initializeChrome() Launching google chrome ..");
		System.setProperty("webdriver.chrome.driver", userDir + "\\drivers\\chromedriver79.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	private void initializeFirefox(String appURL) {
		log.info("Launching Firefox browser..");
		System.setProperty("webdriver.gecko.driver", userDir + "\\drivers\\geckodriver.exe");
		System.setProperty("webdriver.firefox.bin",
				"C:\\Users\\puttaraju_r\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
	}

	private void initializeEdge(String appURL) {
		log.info("Launching Edge browser...");
		System.setProperty("webdriver.edge.driver", userDir + "\\drivers\\MicrosoftWebDriver_Release17134.exe");
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
	}

	private void initializeIE(String appURL) {
		log.info("Launching Internet Explorer browser...");
		System.setProperty("webdriver.ie.driver", userDir + "\\drivers\\IEDriverServer32bit.exe");
//		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
//		caps.setCapability("ignoreZoomSetting", true);
		driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
	}

	public String getPageTitle() {
		return driver.getTitle();
	}
}
