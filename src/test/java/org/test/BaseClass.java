package org.test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;
	static Actions a;
	static Robot r;
	static JavascriptExecutor js;
	static Alert al;
	static Select s;

	// launch browser using browser name
	public static void browserLaunch(String i) {

		if (i == "Chrome") {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (i == "FireFox") {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (i == "Edge") {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			System.err.println("Invalid browser name");
		}

	}

	// launch Chrome browser
	public static void launchChromeBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

	}

	// launch FireFox browser
	public static void launchFirFoxBrowser() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	}

	// launch Edge browser
	public static void launchEdgeBrowser() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
	}

	// get page title
	public static void pageTitle() {
		System.out.println(driver.getTitle());
	}

	// get current page Url
	public static void pageUrl() {
		System.out.println(driver.getCurrentUrl());
	}

	// launch url
	public static void launchUrl(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}

	// implicit wait
	public static void implicitWait() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	// fill text box
	public static void fillTextBox(WebElement e, String value) {
		e.sendKeys(value);
	}

	// click a button
	public static void btnClick(WebElement e) {
		e.click();
	}

	// clear text box
	public static void clearTxtBox(WebElement e) {
		e.clear();

	}

	// get attribute value
	public static String getAttribute(WebElement e, String value) {
		return e.getAttribute(value);

	}

	// print date
	public static Date date() {
		Date d = new Date();
		return d;
	}

	// quit browser
	public static void quitBrowser() {
		driver.quit();
	}

	// to close current window
	public static void closeWindow() {
		driver.quit();
	}

	// print text from a web element
	public static void printText(WebElement e) {
		System.out.println(e.getText());
	}

	// read all cell values from excel
	public static List<String> excelReadAll(String filename, String sheetName) throws IOException {
		File loc = new File(
				"C:\\Users\\lenin\\eclipse-workspace\\MavenDemo\\src\\test\\resources\\Excel\\" + filename + ".xlsx");

		FileInputStream st = new FileInputStream(loc);

		Workbook w = new XSSFWorkbook(st);

		Sheet sheet = w.getSheet(sheetName);
		String value = null;
		List<String> li = new LinkedList<String>();
		for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
				Cell cell = row.getCell(j);
				int cellType = cell.getCellType();
				if (cellType == 1) {
					value = cell.getStringCellValue();
				} else if (cellType == 0) {
					if (DateUtil.isCellDateFormatted(cell)) {
						SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
						value = s.format(cell.getDateCellValue());
					} else {
						value = String.valueOf((long) cell.getNumericCellValue());
					}
				}
				li.add(value);
			}
		}
		return li;
	}

	// read particular cell value from excel
	public static String readCell(String fileName, String sheetName, int row, int cell, String dateType)
			throws IOException {
		File loc = new File("C:\\Users\\lenin\\eclipse-workspace\\AdactinHotel\\Excel" + fileName + ".xlsx");

		FileInputStream st = new FileInputStream(loc);

		Workbook w = new XSSFWorkbook(st);

		Cell c = w.getSheet(sheetName).getRow(row).getCell(cell);
		int cellType = c.getCellType();
		String value = null;
		if (cellType == 1) {
			value = c.getStringCellValue();
		} else {
			if (DateUtil.isCellDateFormatted(c)) {
				SimpleDateFormat s = new SimpleDateFormat(dateType);
				value = s.format(c.getDateCellValue());
			} else {
				double d = c.getNumericCellValue();
				value = String.valueOf((long) d);
			}
		}
		return value;
	}

	// Write on a particular cell
	public static void createCell(String fileName, String sheetName, int row, int cell, String value)
			throws IOException {

		File f = new File("C:\\Users\\lenin\\eclipse-workspace\\AdactinHotel\\Excel" + fileName + ".xlsx");

		Workbook w = new XSSFWorkbook();
		Cell c = w.createSheet(sheetName).createRow(row).createCell(cell);
		FileOutputStream ot = new FileOutputStream(f);
		c.setCellValue(value);
		w.write(ot);
	}

	// update a particular cell
	public static void updateCell(String fileName, String sheetName, int row, int cell, String value)
			throws IOException {

		File f = new File("C:\\Users\\lenin\\eclipse-workspace\\AdactinHotel\\Excel" + fileName + ".xlsx");

		FileInputStream fi = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(fi);
		Cell c = w.getSheet(sheetName).getRow(row).getCell(cell);

		FileOutputStream ot = new FileOutputStream(f);
		c.setCellValue(value);
		w.write(ot);

	}

	// Actions

	// Mouse over action
	public static void mouseOver(WebElement e) {
		a = new Actions(driver);
		a.moveToElement(e).perform();
	}

	// Drag and drop
	public static void dragDrop(WebElement src, WebElement des) {
		a = new Actions(driver);
		a.dragAndDrop(src, des).perform();
	}

	// left click
	public static void leftclick(WebElement e) {
		a = new Actions(driver);
		a.doubleClick(e).perform();
	}

	// right click
	public static void rightClick(WebElement e) {
		a = new Actions(driver);
		a.contextClick(e).perform();
	}

	// Robot class

	// Select text
	public static void selectText() throws AWTException {
		r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_A);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_A);
	}

	// Copy function
	public static void copyKey() throws AWTException {
		r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_C);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_C);
	}

	// paste function
	public static void pasteKey() throws AWTException {
		r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);
	}

	// Press down key
	public static void downKey() throws AWTException {
		r = new Robot();
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyRelease(KeyEvent.VK_DOWN);
	}

	// press up key
	public static void upKey() throws AWTException {
		r = new Robot();
		r.keyPress(KeyEvent.VK_UP);
		r.keyRelease(KeyEvent.VK_UP);
	}

	// press enter key
	public static void enterKey() throws AWTException {
		r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}

	// press tab key
	public static void tabKey() throws AWTException {
		r = new Robot();
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
	}

	// Java Script executor

	// scroll down to a particular web element
	public static void scrollDown(WebElement ref) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollintoView(true)", ref);
	}

	// scroll up to a particular web element
	public static void scrollUp(WebElement ref) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollintoView(false)", ref);
	}

	// click a particular web element
	public static void jsClick(WebElement ref) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", ref);
	}

	// insert value
	public static void jsInsertvalue(WebElement ref, String input) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value'," + input + ")", ref);
	}

	// print text
	public static void jsPrintText(WebElement ref) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].getAttribute('value')", ref);
	}

	// alert

	// to accept an alert
	public static void acceptAlert() {
		al = driver.switchTo().alert();
		al.accept();
	}

	// to cancel an alert
	public static void cancelAlert() {
		al = driver.switchTo().alert();
		al.dismiss();
	}

	// to insert value for an alert
	public static void insertValuesToAlert(String input) {
		al = driver.switchTo().alert();
		al.sendKeys(input);
	}

	// to print text from an alert
	public static void printTextFromAlert() {
		al = driver.switchTo().alert();
		al.getText();
	}

	// frame

	// frame count
	public static void frameCount(String tagname) {
		List<WebElement> allFrames = driver.findElements(By.tagName(tagname));
		System.out.println("No of Frames: " + allFrames.size());
	}

	// switch to frame
	public static void switchToFrame(int i) {
		driver.switchTo().frame(i);
	}
	
	//select class
	
	//select by value
		public static void selectValue(WebElement e, String value) {
			s=new Select(e);
			s.selectByValue(value);
		}
		
		//select by index
		public static void selectIndex(WebElement e, int index) {
			s=new Select(e);
			s.selectByIndex(index);
		}
		
		//select by visible text
		public static void selectVisbleText(WebElement e, String text) {
			s=new Select(e);
			s.selectByVisibleText(text);
		}
		
		//deselect by value
		public static void deselectValue(WebElement e, String value) {
			s=new Select(e);
			s.deselectByValue(value);
		}
		
		//deselect by index
		public static void deselectIndex(WebElement e, int index) {
			s=new Select(e);
			s.deselectByIndex(index);
		}
		
		//deselect by visible text
		public static void deselectVisbleText(WebElement e, String text) {
			s=new Select(e);
			s.deselectByVisibleText(text);
		}
		
		//deselect all
		public static void deSelectAll(WebElement e) {
			s=new Select(e);
			s.deselectAll();
		}
		
		//to print all selected options
		public static void printAllSelectedOptions(WebElement e) {
			s=new Select(e);
			List<WebElement> allOptions = s.getAllSelectedOptions();
			for (WebElement each : allOptions) {
				System.out.println(each.getText());
			}
			}
		
		//to print first selected options
		public static void printFirstSelectedOption(WebElement e) {
			s=new Select(e);
			WebElement firstSelectedOption = s.getFirstSelectedOption();
			System.out.println(firstSelectedOption.getText());
		}
		
		//to get options
		public static void getOptions(WebElement e) {
			s=new Select(e);
			List<WebElement> options = s.getOptions();
			for (WebElement each : options) {
				System.out.println(each.getText());
			}
		}
		
		//TakeScreenShot
		public static void takeSnap(String imageName) throws IOException {
			TakesScreenshot tk= (TakesScreenshot) driver;
			File src = tk.getScreenshotAs(OutputType.FILE);
			File des=new File("C:\\Users\\Satz\\eclipse-workspace\\Maven_Sample\\Screenshot\\"+imageName+".png");
			FileUtils.copyFile(src, des);
		}

}
