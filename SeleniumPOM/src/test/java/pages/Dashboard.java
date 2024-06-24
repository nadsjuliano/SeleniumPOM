package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import actiondriver.Action;
import base.BaseTest;

public class Dashboard extends BaseTest {

	public By txtaHelloMessage = By.xpath("//div[@class='woocommerce-MyAccount-content']");
	
	public Dashboard(WebDriver driver, ExtentTest logger, Action action) {
		this.driver = driver;
		this.logger = logger;
		this.action = new Action(driver, logger);
	}
	
	public void verifyAccountSuccess(String success) {
		String getMessage = driver.findElement(txtaHelloMessage).getText();
		if(getMessage.contains(success))
			logger.pass("User is redirected to Account Dashboard.");
		else
			logger.fail("User is not redirected to Account Dashboard.");
	}
}
