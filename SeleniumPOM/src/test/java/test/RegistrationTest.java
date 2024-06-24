package test;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LandingPage;
import pages.Dashboard;
import pages.MyAccount;
import utils.Constants;
import utils.ExcelUtils;

public class RegistrationTest extends BaseTest {

	@BeforeClass
	public void setUp() {
		createReport("Registration");
	}
	
	@Test(priority = 0, dataProvider = "registrationTestData")
	public void registration(String flag, String testcase, String email, String password, String validationMessage) throws IOException {
		
		String testcaseName = testcase;
		if (flag.equalsIgnoreCase("y")) {
			// enter test name
			createTest(testcaseName);
			// open browser
			openBrowser("Chrome");
			// create page object
			LandingPage landingPage = new LandingPage(driver, logger, action);
			MyAccount register = new MyAccount(driver, logger, action);
			Dashboard dashboard = new Dashboard(driver, logger, action);

			landingPage.selectFromTopMenu("My Account");
			register.verifyRegisterFormIsDisplayed();
			register.enterEmail(email);
			register.enterRegisterPassword(password);
			register.submitRegisterForm();
			if(validationMessage.equalsIgnoreCase("Valid"))
				dashboard.verifyAccountSuccess("Hello");
			else
				register.verifyErrorMessage(validationMessage);
			
			closeBrowser();
		}

	}

	@DataProvider(name = "registrationTestData")
	public Object[][] getData() {
		// get excel data - give excel path and sheet name respectively
		Object data[][] = ExcelUtils.testdata(Constants.testdataFile, "Registration");
		return data;
	}

}
