package com.qa.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class BaseFunctions {

	protected static WebDriver driver;

	protected static final int ID = 1;
	protected static final int CLASS = 2;
	protected static final int LINKTEXT = 3;
	protected static final int XPATH = 4;
	protected static final int CSS = 5;
	protected static final int TAGNAME = 6;

	protected static final int VISIBLETEXT = 1;
	protected static final int VALUE = 2;
	protected static final int INDEX = 3;

	public static void wait(int timeOutInSeconds) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(timeOutInSeconds, TimeUnit.SECONDS);
	}

	public static Actions getAction() {
		Actions action = new Actions(driver);
		return action;
	}

	private static WebElement chooseElement(int byStrategy, String locatorValue) {
		By by = null;

		switch (byStrategy) {
		case ID:
			by = By.id(locatorValue);
			break;
		case CLASS:
			by = By.className(locatorValue);
			break;
		case LINKTEXT:
			by = By.linkText(locatorValue);
			break;
		case XPATH:
			by = By.xpath(locatorValue);
			break;
		case CSS:
			by = By.cssSelector(locatorValue);
			break;
		case TAGNAME:
			by = By.tagName(locatorValue);
			break;
		}
		return driver.findElement(by);
	}

	public static void mouseOver(int byStrategy, String locatorValue) throws InterruptedException {
		WebElement mO = chooseElement(byStrategy, locatorValue);
		getAction().moveToElement(mO).perform();
	}

	public static void textBox(int byStrategy, String locatorValue, String text) throws InterruptedException {
		chooseElement(byStrategy, locatorValue).sendKeys(text);
		getAction().sendKeys(Keys.ESCAPE);
	}

	public static void click(int byStrategy, String locatorValue) throws InterruptedException {
		chooseElement(byStrategy, locatorValue).click();
	}

	public static String getTxt(int byStrategy, String locatorValue) throws InterruptedException {
		String returnText = chooseElement(byStrategy, locatorValue).getText();
		return returnText;
	}

	public static void dropDown(int byStrategy, String locatorValue, int selectStrategy, Object strategyValue)
			throws InterruptedException {
		try {
			WebElement webElement = chooseElement(byStrategy, locatorValue);

			Select select = new Select(webElement);

			switch (selectStrategy) {
			case VISIBLETEXT:
				System.out.println("case 1");
				select.selectByVisibleText((String) strategyValue);
				break;
			case VALUE:
				System.out.println("case 2");
				select.selectByValue((String) strategyValue);
				break;
			case INDEX:
				System.out.println("case 3");
				select.selectByIndex(((Integer) strategyValue).intValue());
				break;
			}
		} catch (NoSuchElementException e) {

		}
	}
}
