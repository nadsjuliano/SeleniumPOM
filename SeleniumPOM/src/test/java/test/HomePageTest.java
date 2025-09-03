package test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LandingPage;
import utils.Constants;
import utils.ExcelUtils;


public class HomePageTest extends BaseTest {
	
	@BeforeClass
	public void setUp() {
		createReport("Home Page");
	}
	
	@Test(dataProvider="testData")
	public void placeOrder(String flag, String testcase, String addToBasket) {
		String testcaseName = testcase;
		if(flag.equalsIgnoreCase("y")) {
			// enter test name
			createTest(testcaseName);
			// open browser
			openBrowser("Chrome");
			// create page object
			LandingPage landingPage = new LandingPage(driver, logger, action);
			HomePage homePage = new HomePage(driver, logger, action);
			
			// test steps
			landingPage.selectFromTopMenu("Shop");
			homePage.clickHome();
			homePage.verifyNoOfArrivalSlider();
			homePage.navigateSlider();
			homePage.addItemToBasket(addToBasket);
			landingPage.selectFromTopMenu("View Shopping Cart");
			homePage.verifyTotal();
			homePage.clickProceedToCheckout();
//			closeBrowser();
		}
	}
	
	@DataProvider(name = "testData")
	public Object[][] getData(){
		// get excel data - give excel path and sheet name respectively
		Object data[][] = ExcelUtils.testdata(Constants.testdataFile, "HomePage");
		return data;
	}

}
