package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;

import actiondriver.Action;
import base.BaseTest;

public class HomePage extends BaseTest {
	
	public By nav_Home = By.xpath("//nav[@class='woocommerce-breadcrumb']/a[text()='Home']");
	public By sld_ArrivalsColumn = By.xpath("(//div[contains(@class,'themify_builder_sub_row')])[2]//child::div[contains(@class,'sub_column')]");
	public By sld_ArrivalsImage = By.xpath("(//div[contains(@class,'themify_builder_sub_row')])[2]//descendant::a[@class='woocommerce-LoopProduct-link']/img");
	public By btn_AddToBasket = By.xpath("//button[text()='Add to basket']");
	
	public HomePage(WebDriver driver, ExtentTest logger, Action action) {
		this.driver = driver;
		this.logger = logger;
		this.action = new Action(driver, logger);
	}
	
	public void clickHome() {
		action.clickElement(nav_Home, "Home");
	}
	
	public void verifyNoOfArrivalSlider() {
		List<WebElement> sliders = driver.findElements(sld_ArrivalsColumn);
		int sliderCount = sliders.size();
		
		if(sliderCount == 3)
			logger.pass("Home page contains 3 Arrival sliders.");
		else
			action.logFail("Home page contains " +sliderCount+ " Arrival sliders.", "Home Page");
	}
	
	public void navigateSlider() {
		List<WebElement> sliders = driver.findElements(sld_ArrivalsImage);
		int sliderCount = sliders.size();
		
		for(int i = 1; i <= sliderCount; i++) {
			sliders.get(i-1).click();
			logger.info("Slider image " +i+ " is clicked.");
			action.isElementDisplayed(btn_AddToBasket, "Add to Basket");
			driver.navigate().back();
		}
	}

}
