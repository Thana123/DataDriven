package airgateFeatures;

import java.io.IOException;
import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import excel.ReadExcel;

public class CreateConnection {
	static properties.PropertiesFile prop = new properties.PropertiesFile();
	private static Logger Log = Logger.getLogger(CreateConnection.class);
	public static void loginmavenir(WebDriver driver){	
		String url = prop.GeneralProperties("URL");
		String username = prop.GeneralProperties("uname");
		String password = prop.GeneralProperties("password");
		 driver.get(url);
		 driver.findElement(By.name("j_username")).sendKeys(username);
		 driver.findElement(By.name("j_password")).sendKeys(password);
		 driver.findElement(By.id("loginButton")).sendKeys(Keys.ENTER);
		 
	}
	
	public static void Createconnection(WebDriver driver ){
		
		String filepat = prop.GeneralProperties("excelfilepath");
		String filepath = filepat+"AG_DataSheet.xlsx";
		Timestamp timestamp0 = new Timestamp(System.currentTimeMillis());
		Log.info("Start Time   : "+timestamp0);
		Log.info("Open Excelsheet for data to create connection");
		ReadExcel rdxl= new ReadExcel();
				
		try {
			 Sheet datatypeSheet =rdxl.OpenExcelSheet(filepath,0);
			 Log.info("Data Sheet num is " +datatypeSheet);
			 int lastrownum = datatypeSheet.getLastRowNum();
			 Log.info("The Last row num is " +lastrownum);
			 System.out.println("The Last row num is " +lastrownum);
			 int j =0;
			 for ( int i = 1; i <= lastrownum; i++) {
				 try {
				String conName = ReadExcel.readCelValue(0, i, datatypeSheet);
				String sidentifier = ReadExcel.readCelValue(1, i, datatypeSheet);
				String sourceTON = ReadExcel.readCelValue(2, i, datatypeSheet);
				String sourceNPI = ReadExcel.readCelValue(3, i, datatypeSheet);
				String destTON = ReadExcel.readCelValue(4, i, datatypeSheet);
				String destNPI = ReadExcel.readCelValue(5, i, datatypeSheet);
				Log.info("Started creating connection");
				
				if (conName!= null){
					Log.info("Started creating connection  Row details are " +conName + sidentifier);
					Create(driver,conName,sidentifier, sourceTON,sourceNPI,destTON,destNPI, i);					
	 				} 

				 } catch (NullPointerException e) {
					 
				 }

				}

			 
			 	System.out.println("################### Total Numbe of Connections updated are:  " + j +  " ###################");
			 	Log.info("################### Total Numbe of Connections updated are:  " + j +  " ###################");
			 	Log.info("Finished create connection");
			 	Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
				Log.info("Completed Time  : "+timestamp1);
			 	driver.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch ( NoSuchElementException ignored) {	}
		
	}
	public static void Create(WebDriver driver,String conName,String sidentifier, String sourceTON, String sourceNPI, String destTON, String destNPI, int i){
		String link2SP = prop.GeneralProperties("ServiceProvider"); 
		Log.info("SP name   : "+link2SP);
		try {
		driver.findElement(By.xpath("//*[@id=\"appnavlist\"]/li[3]/a[2]")).click();
		driver.findElement(By.xpath("//*[@id=\"connectionManagement\"]/li[1]/a")).click();
 		
 		/* 
		 * ######################################################################################################################################
		 *  General Connection
		 * ######################################################################################################################################		
		 */
 		 WebElement slctdriver = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div/div/form/fieldset[1]/table[1]/tbody/tr[2]/td[2]/select"));
		 Select select = new Select(slctdriver);
		 select.selectByIndex(5);
		 driver.findElement(By.name("Name")).sendKeys(conName);
		 
	     /* 
		 * ######################################################################################################################################
		 *  Routing identifier
		 * ######################################################################################################################################		
		 */		 
		 WebElement radbtn1 =  driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div/div/form/fieldset[3]/table/tbody/tr[2]/td[2]/input[5]"));
		 radbtn1.click();			 
		 driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div/div/form/fieldset[3]/table/tbody/tr[3]/td[2]/input")).sendKeys(conName);
		 /* 
		 * ######################################################################################################################################
		 *  VFNZ Customer Routing
		 * ######################################################################################################################################		
		 */
		 if (sidentifier != null){
				driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div/div/form/fieldset[5]/table/tbody/tr[1]/td[2]/input[2]")).sendKeys(sidentifier); 
			 }
			 
	     /* 
		 * ######################################################################################################################################
		 *  Override Message Fields 
		 * ######################################################################################################################################		
		 */ 
			 
			 driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div/div/form/fieldset[6]/table/tbody/tr[1]/td[2]/input[2]")).click();
						 
			 if (sourceTON != null){
	 			driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div/div/form/fieldset[6]/table/tbody/tr[3]/td[2]/input[2]")).sendKeys(sourceTON);	 				
				}
			if (sourceNPI != null){
				driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div/div/form/fieldset[6]/table/tbody/tr[4]/td[2]/input[2]")).sendKeys(sourceNPI);
	 			}
			if (destTON != null){
	 			driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div/div/form/fieldset[6]/table/tbody/tr[6]/td[2]/input[2]")).sendKeys(destTON);
				}
			if (destNPI != null){
				driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div/div/form/fieldset[6]/table/tbody/tr[7]/td[2]/input[2]")).sendKeys(destNPI);
				}
			  /* 
			 * ######################################################################################################################################
			 *  Save and read the status of created connection
			 * ######################################################################################################################################		
			 */ 
			 driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div/div/form/p/input[3]")).click();
			 
			 String actrslt = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div/div/div/ul/li")).getText();
		 
			 String actrslt1 = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div/div/h1")).getText();
			
			 if (actrslt.contains("Connection added")){
				 Log.info(conName + actrslt);
				 
		   /* ######################################################################################################################################
			 *  Link the created connection
			 * ######################################################################################################################################		
			 */ 
				 driver.get(driver.findElement(By.xpath("//*[@id=\"appnavlist\"]/li[1]/a[2]")).getAttribute("href"));	
			 	 driver.get(driver.findElement(By.partialLinkText(link2SP)).getAttribute("href"));
			 	 System.out.println("Modica is slected " );
			 	 driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div/div/fieldset[8]/table/tbody/tr[1]/td/a")).click();
			 	 String yesRadioButtonXPath = "//input[contains(@name, '"+conName + "') and contains(@value, e)]";
			 	 WebElement yesRadioButton = driver.findElement(By.xpath(yesRadioButtonXPath));
			 	 yesRadioButton.click();
			 	 driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div/div/form/p/input[2]")).click();
							 
			 } else {
				 Log.info("Row :"+ (i)+ " " +"Failed " + actrslt);
				 driver.get(driver.findElement(By.xpath("//*[@id=\"appnavlist\"]/li[1]/a[2]")).getAttribute("href"));
				// driver.findElement(By.xpath("//*[@id=\"appnavlist\"]/li[3]/a/strong")).click();
			 }
		 	 Log.info("All done for  Row #" +i);
		} catch (NullPointerException e) {
			 
		 }
			
 	}
}
