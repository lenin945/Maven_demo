package org.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
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

public class ExcelRead {
	
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
						Date d = cell.getDateCellValue();
						SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
						 value = s.format(d);
					} else {
						double d = cell.getNumericCellValue();
						long l = (long) d;
						 value = String.valueOf(l);
					}
				}
				li.add(value);
			}
		}
		return li;
	}

	public static void main(String[] args) throws IOException {
		List<String> all = excelReadAll("Data", "Datas");
		
		
		
	}
}
