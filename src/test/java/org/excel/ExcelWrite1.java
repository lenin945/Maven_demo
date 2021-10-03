package org.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWrite1 {

		  public static void main(String[] args) {
		    
			  for (int i = 1; i <= 3; i++) {
			      for (int j = 1; j <= i; j++) {
			        System.out.println(j);
			      }

			    }
		    }}
