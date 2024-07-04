package test;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.Dashboard;
import pages.LandingPage;
import pages.MyAccount;
import utils.Constants;
import utils.ExcelUtils;

public class LoginTest extends BaseTest {

	@BeforeClass
	public void setUp() {
		createReport("Login");
	}
	
	@Test(dataProvider = "loginTestdata")
	public void login(String flag, String testcase, String username, String password, String validationMessage) throws IOException {
		String testcaseName = testcase;
		if(flag.equalsIgnoreCase("y")) {
			// enter test name
			createTest(testcaseName);
			// open browser
			openBrowser("Chrome");
			// create page object
			LandingPage landingPage = new LandingPage(driver, logger, action);
			MyAccount login = new MyAccount(driver, logger, action);
			Dashboard dashboard = new Dashboard(driver, logger, action);
			// test steps
			landingPage.selectFromTopMenu("My Account");
			login.verifyLoginFormIsDisplayed();
			login.enterUsername(username);
			login.enterPassword(password);
			login.clickLogin();
			if(validationMessage.equalsIgnoreCase("Valid"))
				dashboard.verifyAccountSuccess("Hello");
			else
				login.verifyErrorMessage(validationMessage);
			closeBrowser();
		}
		
	}
	
	@DataProvider(name = "loginTestdata")
	public Object[][] getData(){
		// get excel data - give excel path and sheet name respectively
		Object data[][] = ExcelUtils.testdata(Constants.testdataFile, "Login");
		return data;
	}
	
}
