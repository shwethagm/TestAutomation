package com.qa.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.qa.util.LoggerUtil;


public class TestBase {

	public static WebDriver driver;
	private Logger log = LoggerUtil.getLogger(); 
	String userDir = System.getProperty("user.dir");
	
	public static WebDriver getDriver() {
		return driver;
	}

	@Parameters({ "browser"})
	@BeforeClass 
	public void initializeTestBaseSetup(String browser) {
		String appURL = "https://www.google.com";
		log.info(" browser="+browser+" appURL="+appURL);
		try {
			initDriver(browser, appURL);
		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	private void initDriver(String browser, String appURL) {
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
		log.info("Launching google chrome ..");
		System.setProperty("webdriver.chrome.driver", userDir + "\\drivers\\chromedriver79.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
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
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);
		driver = new InternetExplorerDriver(caps);
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
	}


}
