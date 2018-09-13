package excel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.RowId;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadWriteExcell {

	public void readWriteXLXS (){

		    DataFormatter df = new DataFormatter();

     		try {
				 
				FileInputStream  excelFile = new FileInputStream("C:\\testdata\\testdatesheetAlan.xlsx");
				XSSFWorkbook workbook = new XSSFWorkbook(excelFile); 
				Sheet datatypeSheet = workbook.getSheetAt(0);
				
				//Iterator<Row> iterator = datatypeSheet.iterator();
				int noOfColumns = datatypeSheet.getPhysicalNumberOfRows();
				System.out.println(noOfColumns);
				String connectionStatus = null;
	            for (int i=1 ; i<noOfColumns; i++) {
	            	
	            	System.out.println("i==" + i);
	            	Row currentRow = datatypeSheet.getRow(i);
	            	Cell CellValue0 = currentRow.getCell(0);
	            	String Username = df.formatCellValue(CellValue0);
	            	//final String connectionStatus = numm; 
	            	//String connectionStatus1 = "Not Connected";
	            				//int j = 1;
	            				Row previousRow = datatypeSheet.getRow(i-1);
	            				Cell CellValuex = previousRow.getCell((0));
	            				Row nextRow = datatypeSheet.getRow(i+1);
	            				Cell CellValuey = nextRow.getCell((0));
	            			if (!Username.equals(df.formatCellValue(CellValuex))){
	            				System.out.println("Username miss match, So connection initiated");
	            				Cell CellValue1 = currentRow.getCell(1);
	        	            	String Password = df.formatCellValue(CellValue1);
	        	            	//System.out.println(Username + "     yes       "+ PUsername );
	        	            	//smppSession = getConnection.newSession(Username, Password, "localhost", "40001");
	        	            	connectionStatus = "Connected";        	            	
	            			} else if  (!Username.equals(df.formatCellValue(CellValuey))){
	            				System.out.println("Username is same, So connection not required");
	            				Cell CellValue2 = currentRow.getCell(2);
				            	String frmMSISDN = df.formatCellValue(CellValue2);
				            	Cell CellValue3 = currentRow.getCell(3);
				            	String toMSISDN = df.formatCellValue(CellValue3);
				            	Cell CellValue4 = currentRow.getCell(4);
				            	String txtmsg = df.formatCellValue(CellValue4);				            	
				            	System.out.println("-----------"+frmMSISDN + "-----"+ toMSISDN + "--------"+txtmsg);
				            	//getConnection.sendSMPPTXT(frmMSISDN, toMSISDN, txtmsg, "1", smppSession, currentRow);
				            	FileOutputStream excelFileout =new FileOutputStream("C:\\testdata\\testdatesheetAlan.xlsx");
						        workbook.write(excelFileout);
	            				//smppSession.unbindAndClose();
	            				
	            			} else {	            				
	            				connectionStatus = "Connected";	            								            	
				            	
	            			}
	            			
	            			if (connectionStatus.equals("Connected")){
	            				
	            				Cell CellValue2 = currentRow.getCell(2);
				            	String frmMSISDN = df.formatCellValue(CellValue2);
				            	Cell CellValue3 = currentRow.getCell(3);
				            	String toMSISDN = df.formatCellValue(CellValue3);
				            	Cell CellValue4 = currentRow.getCell(4);
				            	String txtmsg = df.formatCellValue(CellValue4);
				            	
				            	System.out.println("-----------"+frmMSISDN + "-----"+ toMSISDN + "--------"+txtmsg);

				            	//getConnection.sendSMPPTXT(frmMSISDN, toMSISDN, txtmsg, "1", smppSession, currentRow);
				            		        	            		        	            	
	            			}

	            	

	            }
            	FileOutputStream excelFileout =new FileOutputStream("C:\\testdata\\testdatesheetAlan.xlsx");
		        workbook.write(excelFileout);
		        System.out.println("Final Excell sheet updated ");
            	
			}
			catch (IOException e1) {
				e1.printStackTrace();

			}



	}
	
	
}
