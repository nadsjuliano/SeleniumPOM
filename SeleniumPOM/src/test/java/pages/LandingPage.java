package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import actiondriver.Action;
import base.BaseTest;

public class LandingPage extends BaseTest {

	public By mnuShop = By.xpath("//a[@href='https://practice.automationtesting.in/shop/' and text()='Shop']");
	public By mnuMyAccount = By.xpath("//a[@href='https://practice.automationtesting.in/my-account/' and text()='My Account']");
	public By mnuViewShoppingCart = By.xpath("//a[@title='View your shopping cart']");

	public LandingPage(WebDriver driver, ExtentTest logger, Action action) {
		this.driver = driver;
		this.logger = logger;
		this.action = new Action(driver, logger);
	}

	public void selectFromTopMenu(String menu) {
		switch (menu.toLowerCase()) {
		case "shop":
			action.clickElement(mnuShop, "Shop");
			break;
		case "my account":
			action.clickElement(mnuMyAccount, "My Account");
			break;
		case "view shopping cart":
			action.clickElement(mnuViewShoppingCart, "View Item");
		}

	}

}
