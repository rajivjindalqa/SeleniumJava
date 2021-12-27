package utils;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	static XSSFWorkbook workbook;
	static XSSFSheet sheet;


	public ExcelUtility(String sheetpath, String sheetTab) {
		try {
			workbook = new XSSFWorkbook(sheetpath);
			sheet = workbook.getSheet(sheetTab);
		}catch(Exception e) {

		}

	}
	public int Excel_getRowCount() throws IOException {
		int rowCount=0;
		rowCount = sheet.getPhysicalNumberOfRows();
		System.out.println("Total number of rows: "+rowCount);
		return rowCount;
	}


	public int Excel_getColumnCount() throws IOException {
		int columnCount=0;
		columnCount = sheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println("Total number of Columns: "+columnCount);
	return columnCount;
	
	}
	public String Excel_getCellStringValue(int rowNum, int ColNum) throws IOException {
		String cellValue=null;
		 cellValue = sheet.getRow(rowNum).getCell(ColNum).getStringCellValue();
		
		return cellValue;
	}


	public void Excel_getCellNumericValue(int rowNum, int ColNum) throws IOException {
		
		double numericCellValue = sheet.getRow(rowNum).getCell(ColNum).getNumericCellValue();
		System.out.println("Cell Value is :"+numericCellValue);
		
	}







}
