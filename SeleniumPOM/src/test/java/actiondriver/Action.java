package actiondriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import base.BaseTest;
import utils.Constants;

public class Action extends BaseTest {

	public Action(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}
	
	public String screenCapture(WebDriver driver, String moduleName) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File(Constants.failedScreenshotPath(moduleName));
		String errflpath = Dest.getAbsolutePath();
		try {
			FileUtils.copyFile(scrFile, Dest);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return errflpath;
	}

	public void logFail(String msg, String moduleName) {
		String screenshotPath = screenCapture(driver, moduleName);
		logger.fail(msg,
				MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	}
	
	public void waitForPageToLoad() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	public void waitForElementVisibility(By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.fail("Element not found.");
		}
	}
	
	public void waitForElementsVisibility(List<WebElement> element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.visibilityOfAllElements(element));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.fail("Element not found.");
		}
	}

	public void isElementDisplayed(By locator, String elementName) {
		WebElement ele = driver.findElement(locator);
		try {
			waitForElementVisibility(locator);
			if (ele.isDisplayed())
				logger.pass(elementName + " is displayed.");
			else
				logger.fail(elementName + " not found.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.fail(elementName + " not found.");
		}
	}

	public void clickElement(By locator, String elementName) {
		waitForElementVisibility(locator);
		driver.findElement(locator).click();
		logger.pass(elementName + " is clicked.");
	}

	public void enterText(By locator, String value, String elementName) {
		waitForElementVisibility(locator);
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(value);
		logger.pass("\"" + value + "\" is entered in " + elementName + " field.");
	}



}
