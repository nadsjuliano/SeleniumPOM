package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import actiondriver.Action;
import base.BaseTest;

public class MyAccount extends BaseTest {

	public By frmRegister = By.xpath("//form[@class='register']");
	public By txtEmailAddress = By.id("reg_email");
	public By txtRegPassword = By.id("reg_password");
	public By btnRegister = By.name("register");
	public By frmLogin = By.className("login");
	public By txtUsername = By.id("username");
	public By txtLoginPassword = By.id("password");
	public By chkRememberMe = By.id("rememberme");
	public By btnLogin = By.name("login");
	public By lnkLostYourPassword = By.linkText("Lost your password?");
	public By errorMessageBox = By.xpath("//ul[@class='woocommerce-error']//child::li");

	public MyAccount(WebDriver driver, ExtentTest logger, Action action) {
		this.driver = driver;
		this.logger = logger;
		this.action = new Action(driver, logger);
	}

	// Register Form
	public void verifyRegisterFormIsDisplayed() {
		action.isElementDisplayed(frmRegister, "Register Form");
	}

	public void enterEmail(String email) {
		action.enterText(txtEmailAddress, email, "Email Address");
	}

	public void enterRegisterPassword(String password) {
		action.enterText(txtRegPassword, password, "Password");
	}

	public void submitRegisterForm() {
		action.clickElement(btnRegister, "Register");
	}

	// Login Form
	public void verifyLoginFormIsDisplayed() {
		action.isElementDisplayed(frmLogin, "Login Form");
	}

	public void enterUsername(String username) {
		action.enterText(txtUsername, username, "Username");
	}

	public void enterPassword(String password) {
		action.enterText(txtLoginPassword, password, "Password");
	}

	public void clickLogin() {
		action.clickElement(btnLogin, "Login");
	}

	// Validation Message Box
	public void verifyErrorMessage(String expectedMessage) {
		String actualMessage = driver.findElement(errorMessageBox).getText();
		if (actualMessage.equals(expectedMessage))
			logger.pass(expectedMessage + " is displayed.");
		else
			action.logFail(expectedMessage, "My Account");
	}

}
