package com.qa.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.qa.base.TestBase;
import com.qa.domain.Consumer;
import com.qa.domain.UserDetails;
import com.qa.extentreports.ReportManager;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.util.JsonParserUtil;

public class LoginScenarioTest extends TestBase {

	WebDriver driver;
	HomePage homePage;
	ReportManager reportManager;
	JsonParserUtil jsonParserUtil;
	Gson gson;

	public LoginScenarioTest() {
		log.trace("LoginScenarioTest() constructor");
		reportManager = ReportManager.getInstance();
	}

	@BeforeClass
	public void setup() {
		driver = getDriver();
		homePage = new HomePage(driver);
		jsonParserUtil = new JsonParserUtil();
		gson = new Gson();
	}

	@Test
	public void TestJSON1() {
		try {
			JsonArray testData = jsonParserUtil.getDataFromJson("UserDetails");
			UserDetails[] user = gson.fromJson(testData, UserDetails[].class);
			log.info(" uname= " + user[0].getUserName());
			log.info(" pass= " + user[0].getPassword());
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error(e.toString());
		}

	}

	@Test
	public void TestJSON2() {
		try {
			JsonArray testData = jsonParserUtil.getDataFromJson("Consumers");
			Consumer[] consumers = gson.fromJson(testData, Consumer[].class);
			log.info(" testData length= " + consumers.length);
			Consumer consumer1 = consumers[0];
			log.info(" consumer1 FirstName= " + consumer1.FirstName);
			log.info(" consumer1 LastName= " + consumer1.LastName);
			Consumer consumer2 = consumers[1];
			log.info(" consumer2 FirstName= " + consumer2.FirstName);
			log.info(" consumer2 LastName= " + consumer2.LastName);

		} catch (Exception e) {
			log.error("json parse error");
		}

	}

	@Test
	public void TestLoginSuccess() {
		log.info(" reportmanager=" + reportManager);
		try {
			reportManager.takeSnapshotAndAttachToReport("Before login");
			JsonArray testData = jsonParserUtil.getDataFromJson("UserDetails");
			UserDetails[] user = gson.fromJson(testData, UserDetails[].class);
			LoginPage loginpage = homePage.openLoginPage();
			HomePage homePage = loginpage.login(user[0].getUserName(), user[0].getPassword());
			reportManager.takeSnapshotAndAttachToReport("during login");
			boolean loginsuccess = homePage.IsLoginSuccessfull();
			Assert.assertTrue(loginsuccess);
			reportManager.takeSnapshotAndAttachToReport("login success");
		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}

//	@Test
//	public void TestJSON2() {
//		try {
//			JsonElement jsonData = new JsonParser().parse(new FileReader(userDir + "\\resources\\users.json"));
//			JsonElement custmerJson = jsonData.getAsJsonObject().get("Consumers");
//			Type listType = new TypeToken<List<Consumer>>() {
//			}.getType();
//			List<Consumer> testData = new Gson().fromJson(custmerJson, listType);
//			log.info(" testData size= " + testData.size());
//			Consumer consumer1 = testData.get(0);
//			log.info(" consumer1 FirstName= " + consumer1.FirstName);
//			log.info(" consumer1 LastName= " + consumer1.LastName);
//			Consumer consumer2 = testData.get(1);
//			log.info(" consumer2 FirstName= " + consumer2.FirstName);
//			log.info(" consumer2 LastName= " + consumer2.LastName);
//
//		} catch (IOException e) {
//			log.error("json parse error");
//		}
//
//	}
//
//	@Test
//	public void TestJSON2() {
//		try {
//			JsonElement jsonData = new JsonParser().parse(new FileReader(userDir + "\\resources\\users.json"));
//			JsonArray jsonArray = jsonData.getAsJsonObject().getAsJsonArray("Consumers");
//			Consumer[] testData = new Gson().fromJson(jsonArray, Consumer[].class);
//			log.info(" testData length= " + testData.length);
//			Consumer consumer1 = testData[0];
//			log.info(" consumer1 FirstName= " + consumer1.FirstName);
//			log.info(" consumer1 LastName= " + consumer1.LastName);
//			Consumer consumer2 = testData[1];
//			log.info(" consumer2 FirstName= " + consumer2.FirstName);
//			log.info(" consumer2 LastName= " + consumer2.LastName);
//
//		} catch (IOException e) {
//			log.error("json parse error");
//		}
//
//	}
}
