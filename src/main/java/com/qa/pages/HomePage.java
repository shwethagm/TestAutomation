package com.qa.pages;

import java.io.File;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.util.JsonParserUtil;
import com.qa.util.LoggerUtil;

public class HomePage {

	private WebDriver driver;
	Logger log = LoggerUtil.getLogger();

	@FindBy(xpath = "//span[@class='-link--channel-name']")
	WebElement stackoverflowIcon;

	@FindBy(xpath = "//*[@id=\"left-sidebar\"]/div[1]/nav/ol/li[2]/ol/li[3]")
	WebElement tags;

	@FindBy(xpath = "//a[@id='nav-users']")
	WebElement users;

	@FindBy(xpath = "//a[@class='ws-nowrap s-btn s-btn__primary']")
	WebElement askButton;

	public HomePage(WebDriver driver) {
		log.info("HomePage() constructor driver=" + driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void readUsers() {
		JsonParserUtil jsonParser = new JsonParserUtil();
		File file;
		jsonParser.ConvertJsonToType();
	}

	public void clickOnAsk() {
		log.info("clickOnAsk");
		askButton.click();

	}

	public void navigateToTagsPage() {
		log.info("navigateToTags");
		tags.click();
	}

	public void navigateToUsers() {
		log.info("navigateToUsers");
		users.click();

	}

}
