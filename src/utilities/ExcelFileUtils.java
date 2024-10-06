package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

public class ExcelFileUtils {
	public static XSSFWorkbook wb;
	
	//Constructor to open file with excel file path as argument
	public ExcelFileUtils(String excelpath) throws Throwable
	{
		FileInputStream fileInput = new FileInputStream(excelpath);
		wb = new XSSFWorkbook(fileInput);
	}
	
	// Count no. of rows in a sheet
	public int rowCount(String sheetname)
	{
		int rc = wb.getSheet(sheetname).getPhysicalNumberOfRows();
		return rc;
	}
	
	// Get a value from a given row and column position
	public String getCellData(String sheetname, int row, int col)
	{
		String data = "";
		if(wb.getSheet(sheetname).getRow(row).getCell(col).getCellType() == CellType.NUMERIC)
		{
			int celldata = (int) wb.getSheet(sheetname).getRow(row).getCell(col).getNumericCellValue();
			data = String.valueOf(celldata);
		}
		else
			data = wb.getSheet(sheetname).getRow(row).getCell(col).getStringCellValue();
		return data;
	}
	
	// Set a cell value with red or green or blue color font
	public void setCellData(String sheetname, int row, int col, String data, String writeExccelpath) throws Throwable
	{
		wb.getSheet(sheetname).getRow(row).createCell(col).setCellValue(data);
		
		XSSFCellStyle style = wb.createCellStyle();
		XSSFFont font = wb.createFont();
		font.setBold(true);
				
		if(data.equalsIgnoreCase("Pass"))
			font.setColor(IndexedColors.GREEN.getIndex());
		else if(data.equalsIgnoreCase("Fail"))
			font.setColor(IndexedColors.RED.getIndex());
		else if(data.equalsIgnoreCase("Blocked"))
			font.setColor(IndexedColors.BLUE.getIndex());
		
		style.setFont(font);
		wb.getSheet(sheetname).getRow(row).getCell(col).setCellStyle(style);
		
		FileOutputStream fOut = new FileOutputStream(writeExccelpath);
		wb.write(fOut);
	}
}
