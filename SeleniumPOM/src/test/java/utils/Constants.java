package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface Constants {

	final String url = "http://practice.automationtesting.in/";
	final String userDir = System.getProperty("user.dir");

	// extent report path
	final String reportPath = userDir + "/src/test/resources/reports/";

	// excel file path
	final String testdataFile = userDir + "/src/test/resources/testdata/TestData.xlsx";

	// get current date and time with format
	String currentDateTime = new SimpleDateFormat("yyyy-MM-dd_hhmmssaa").format(new Date());
	
	// failed test case screenshot path
	public static String failedScreenshotPath (String moduleName) {
		String errorScreenPath = userDir + "/src/test/resources/screenshots/" + moduleName + "/" + currentDateTime + ".png";
		return errorScreenPath;
	}
}
