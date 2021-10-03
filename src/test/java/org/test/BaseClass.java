package org.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;

	static void browserLaunch(String i) {

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

	public static void launchUrl(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}

	public static void fillTextBox(WebElement e, String value) {
		e.sendKeys(value);
	}

	public static void logInBtn(WebElement e) {
		e.click();
	}

	public static String getTitle() {
		String pageTitle = driver.getTitle();
		return pageTitle;

	}
	public static String getCurrentUrl() {
		String url = driver.getCurrentUrl();
		return url;

	}
	public static String getAttribute(WebElement e) {
		return e.getAttribute("value");
		

	}
//	read all cell values from excel
	public static List<String> excelReadAll(String filename, String sheetName) throws IOException {
		File loc = new File("C:\\Users\\lenin\\eclipse-workspace\\MavenDemo\\src\\test\\resources\\Excel\\"+filename+".xlsx");

		FileInputStream st = new FileInputStream(loc);

		Workbook w = new XSSFWorkbook(st);

		Sheet sheet = w.getSheet(sheetName);
		String value=null;
		List<String> li =new LinkedList<String>();
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
						 value = String.valueOf((long)cell.getNumericCellValue());
					}
				}
				li.add(value);
			}
		}
		return li;
	}
	
//	read particular cell value from excel
	public static String readCell(String fileName, String sheetName, int row, int cell, String dateType) throws IOException {
		File loc =new File("C:\\Users\\lenin\\eclipse-workspace\\MavenDemo\\src\\test\\resources\\Excel\\"+fileName+".xlsx");
		
		FileInputStream st = new FileInputStream(loc);
		
		Workbook w = new XSSFWorkbook(st);
		
		Cell c = w.getSheet(sheetName).getRow(row).getCell(cell);
		int cellType = c.getCellType();
		String value=null;
		if (cellType==1) {
			 value = c.getStringCellValue();
		}
		else{
			if (DateUtil.isCellDateFormatted(c)) {
				SimpleDateFormat s =new SimpleDateFormat(dateType);
				 value = s.format(c.getDateCellValue());
			}
			else {
				double d = c.getNumericCellValue();
				 value = String.valueOf((long) d);
			}
		}
		return value;
	}
	
//	Write on a particular cell
	public static void createCell(String fileName, String sheetName, int row, int cell, String value) throws IOException {

		File f= new File("C:\\Users\\lenin\\eclipse-workspace\\MavenDemo\\src\\test\\resources\\Excel\\"+fileName+".xlsx");
		
		Workbook w =new XSSFWorkbook();
		Cell c = w.createSheet(sheetName).createRow(row).createCell(cell);
		FileOutputStream ot =new FileOutputStream(f);
		c.setCellValue(value);
		w.write(ot);
	}
	
//	update a particular cell
	public static void updateCell(String fileName, String sheetName, int row, int cell, String value)
			throws IOException {

		File f = new File(
				"C:\\Users\\lenin\\eclipse-workspace\\MavenDemo\\src\\test\\resources\\Excel\\" + fileName + ".xlsx");

		FileInputStream fi = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(fi);
		Cell c = w.getSheet(sheetName).getRow(row).getCell(cell);

		FileOutputStream ot = new FileOutputStream(f);
		c.setCellValue(value);
		w.write(ot);

	}
	
}
