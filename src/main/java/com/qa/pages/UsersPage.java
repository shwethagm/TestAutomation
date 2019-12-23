package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class UsersPage extends TestBase {

	@FindBy(xpath = "//a[@id='nav-users']")
	WebElement users;

	public UsersPage(WebDriver driver) {
		log.info("UsersPage() driver=" + driver);
		PageFactory.initElements(driver, this);
	}
}