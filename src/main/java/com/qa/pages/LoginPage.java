package com.qa.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.util.LoggerUtil;

public class LoginPage {
	private WebDriver driver;
	Logger log = LoggerUtil.getLogger();

	@FindBy(xpath = "//input[@class='s-input']")
	WebElement username;

	@FindBy(xpath = "//input[@class=\"grid--cell s-input\"]")
	WebElement password;

	@FindBy(xpath = "//button[@name='submit-button']")
	WebElement submitBtn;

	public LoginPage(WebDriver driver) {
		log.info(" driver=" + driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public HomePage login(String user, String pass) {
		log.info(" user=" + user + " pass=" + pass);
		username.sendKeys(user);
		password.sendKeys(pass);
		submitBtn.click();

		return new HomePage(driver);
	}

}
