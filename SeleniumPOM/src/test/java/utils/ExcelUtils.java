package utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	XSSFWorkbook workbook;
	static XSSFSheet sheet;

	public ExcelUtils(String excelPath, String sheetName) {
		try {
			workbook = new XSSFWorkbook(excelPath);
			sheet = workbook.getSheet(sheetName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int getRowCount() {
		int rowCount = 0;
		try {
			rowCount = sheet.getPhysicalNumberOfRows();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
		}
		return rowCount;
	}

	public int getColCount() {
		int colCount = 0;
		try {
			colCount = sheet.getRow(0).getPhysicalNumberOfCells();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
		}
		return colCount;
	}

	public String getCellData(int rowNum, int colNum) {
		String cellData = null;
		try {
			if (sheet.getRow(rowNum).getCell(colNum) == null)
				return cellData = "";
			else 
				cellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue().toString().trim();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
		}
		return cellData;
	}

	public static Object[][] testdata(String excelPath, String sheetName) {
		ExcelUtils excel = new ExcelUtils(excelPath, sheetName);

		int rowCount = excel.getRowCount();
		int colCount = excel.getColCount();
//		System.out.println("Total rows: " + rowCount);
//		System.out.println("Total columns: " + colCount);

		Object data[][] = new Object[rowCount - 1][colCount];
		
		for (int i = 1; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				String cellData = excel.getCellData(i, j);
//				System.out.print(cellData + " | ");
				data[i - 1][j] = cellData;
			}
//			System.out.println();
		}
		return data;
	}
	
}
