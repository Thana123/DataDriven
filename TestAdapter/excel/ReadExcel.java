package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcel {
	 static DataFormatter df = new DataFormatter();
	 private static Logger Log = Logger.getLogger(ReadExcel.class);
	public Sheet OpenExcelSheet (String filepath, int sheetNum) throws IOException {
		 	FileInputStream  excelFile = new FileInputStream(filepath);
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile); 
			Sheet datatypeSheet = workbook.getSheetAt(sheetNum);
     	return datatypeSheet;
	 }
	 public static String readCelValue (int ColumNum, int RowNum, Sheet datatypeSheet) {
		
				Row workingRow = datatypeSheet.getRow(RowNum);
            	Cell CellValue = workingRow.getCell(ColumNum);
            	String Username = df.formatCellValue(CellValue);
	return Username;

	}
	 public static String WriteCelValue (String filepath, int ColumNum, int RowNum, String message,int sheetnum) throws IOException {
		 FileInputStream  excelFile = new FileInputStream(filepath);
		 XSSFWorkbook workbook = new XSSFWorkbook(excelFile); 
		 Sheet datatypeSheet = workbook.getSheetAt(sheetnum);
		Row workingRow = datatypeSheet.getRow(RowNum);
     	Cell CellValue = workingRow.getCell(ColumNum);
     	CellValue.setCellType(Cell.CELL_TYPE_STRING);
     	CellValue.setCellValue(message);
      	FileOutputStream output_file =new FileOutputStream(filepath);
     	workbook.write(output_file);
     	//output_file.close();
     	//excelFile.close();
     	
     	return "done";
	 }
	 public static String createRow (String filepath, int RowNum,int sheetnum) throws IOException {
		FileInputStream  excelFile = new FileInputStream(filepath);
		XSSFWorkbook workbook = new XSSFWorkbook(excelFile); 
		Sheet datatypeSheet = workbook.getSheetAt(sheetnum);
		Row workingRow = datatypeSheet.createRow(RowNum);
      	FileOutputStream output_file =new FileOutputStream(filepath);
     	workbook.write(output_file);
     	//output_file.close();
     	//excelFile.close();
     	
     	return "done";
	 }
	 public static String createCelValue (String filepath, int ColumNum, int RowNum, String message, int sheetnum) throws IOException {
			FileInputStream  excelFile = new FileInputStream(filepath);
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile); 
			Sheet datatypeSheet = workbook.getSheetAt(sheetnum);
			Row workingRow = datatypeSheet.getRow(RowNum);			
	     	Cell CellValue = workingRow.createCell(ColumNum);
	     	CellValue.setCellType(Cell.CELL_TYPE_STRING);
	     	CellValue.setCellValue(message);
	      	FileOutputStream output_file =new FileOutputStream(filepath);
	     	workbook.write(output_file);
	     	//output_file.close();
	     	//excelFile.close();
	     	
	     	return "done";
		 }

}
