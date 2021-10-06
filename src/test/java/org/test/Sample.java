package org.test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;

public class Sample extends BaseClass {
	// static void browserLaunch(String i) {
	//
	// WebDriver driver;
	// if (i=="Chrome") {
	// WebDriverManager.chromedriver().setup();
	// driver = new ChromeDriver();
	// }
	// else if (i=="FireFox") {
	// WebDriverManager.firefoxdriver().setup();
	// driver = new FirefoxDriver();
	// }
	// else if (i=="Edge") {
	// WebDriverManager.edgedriver().setup();
	// driver = new EdgeDriver();
	// }
	// else {
	// System.err.println("Invalid browser name");
	// }
	// }

	// public static void launchUrl(String url) {
	// driver.get(url);
	// }

	// public static void fillTextBox(WebElement e, String value) {
	// e.sendKeys(value);
	// }
//	public static void logInBtn(WebElement e) {
//		e.click();
//	}
//	public static String getTitle() {
//		String pageTitle = driver.getTitle();
//		return pageTitle;
//
//	}
//	public static String getCurrentUrl() {
//		String url = driver.getCurrentUrl();
//		return url;
//
//	}

	public static void main(String[] args) throws IOException {
		List<String> allValues = excelReadAll("Data", "Datas");
		browserLaunch("Chrome");
		launchUrl("https://en-gb.facebook.com/");
//		String user = readCell("Data", "Datas", 5, 0, "dd-Mm-yyyy");
		
		fillTextBox(driver.findElement(By.id("email")), allValues.get(4));
//		String pass = readCell("Data", "Datas", 5, 2,"dd-MM-YYYY");
		fillTextBox(driver.findElement(By.id("pass")), allValues.get(6));
		
		pageTitle();
		pageUrl();
		System.out.println(getAttribute(driver.findElement(By.id("email")), "value"));
		System.out.println(getAttribute(driver.findElement(By.id("pass")), "value"));
		btnClick(driver.findElement(By.name("login")));
		
	}
	
}
