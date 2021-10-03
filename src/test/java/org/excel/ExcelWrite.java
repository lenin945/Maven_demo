package org.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.test.BaseClass;

public class ExcelWrite extends BaseClass{

//	public static void updateCell(String fileName, String sheetName, int row, int cell, String value)
//			throws IOException {
//
//		File f = new File(
//				"C:\\Users\\lenin\\eclipse-workspace\\MavenDemo\\src\\test\\resources\\Excel\\" + fileName + ".xlsx");
//
//		FileInputStream fi = new FileInputStream(f);
//		Workbook w = new XSSFWorkbook(fi);
//		Cell c = w.getSheet(sheetName).getRow(row).getCell(cell);
//
//		FileOutputStream ot = new FileOutputStream(f);
//		c.setCellValue(value);
//		w.write(ot);
//
//	}

	public static void main(String[] args) throws IOException {

//		updateCell("New", "Flipcart", 4, 0, "Lenin");
//		System.out.println("done..........");
		List<String> all = excelReadAll("Data", "Datas");
		for (String eachCell : all) {
			System.out.println(eachCell);
		}
	}
}
