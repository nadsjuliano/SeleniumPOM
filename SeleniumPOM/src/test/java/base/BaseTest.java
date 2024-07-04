package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import actiondriver.Action;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Constants;

public class BaseTest {

	protected WebDriver driver;
	protected ExtentReports extent;
	protected ExtentSparkReporter spark;
	protected ExtentTest logger;
	protected Action action;
	
	public void createReport(String reportName) {

		// initialize report
		extent = new ExtentReports();
		spark = new ExtentSparkReporter(Constants.reportPath + reportName.replaceAll("\\s", "") + ".html");
		extent.attachReporter(spark);

		// extent report config
		spark.config().setDocumentTitle(reportName);
		spark.config().setTimeStampFormat("MM/dd/yyyy hh:mm a");
		spark.config().setTheme(Theme.DARK);
	}
	
	public void createTest(String testName) {
		// create test for each test case
		logger = extent.createTest(testName);
	}

	public void openBrowser(String browser) {

		// initialize browser
		switch(browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		}

		// maximize browser
		driver.manage().window().maximize();
		logger.info("Open browser: " + browser);
		// open URL
		driver.get(Constants.url);
		logger.info("Navigated to URL: " + Constants.url);
		// wait for page to load
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));	
	}
	
	public void closeBrowser() {
		driver.quit();
		logger.info("Browser is closed");
	}
	
/*	
 * @BeforeMethod: This will be executed before every @test annotated method.
 * @AfterMethod: This will be executed after every @test annotated method.
 * @BeforeClass: This will be executed before first @Test method execution. It will be executed one only time throughout the test case.
 * @AfterClass: This will be executed after all test methods in the current class have been run
 * @BeforeTest: This will be executed before the first @Test annotated method. It can be executed multiple times before the test case.
 * @AfterTest: A method with this annotation will be executed when all @Test annotated methods complete the execution of those classes inside the <test> tag in the TestNG.xml file.
 */
	@AfterClass
	public void tearDown() {
		extent.flush();
	}
}
