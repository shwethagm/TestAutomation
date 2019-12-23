package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class TagsPage extends TestBase {

	@FindBy(xpath = "//a[@id='nav-users']")
	WebElement userButton;

	public TagsPage(WebDriver driver) {
		log.info("TagsPage(driver) driver=" + driver);
		PageFactory.initElements(driver, this);
	}

	public void navigateFromTagsPageToOpenPage() {
		log.info("navigateFromTagsPageToOpenPage");
		userButton.click();
	}

}
