package rUnner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;

import airgateFeatures.CreateConnection;
import browser.ChromeBrowser;
import properties.PropertiesFile;

public class TestRunner {
	private static Logger Log = Logger.getLogger(TestRunner.class);
	static WebDriver driver;
	public static void main(String[] args) {
		PropertyConfigurator.configure("Resources/log4j.properties");		
		
			Log.info(" ########################## Info Log is Started ########################## ");
			PropertiesFile prop = new PropertiesFile();
			String driverPath = prop.GeneralProperties("DriverPath");
			driver = ChromeBrowser.getdriver(driverPath);
			CreateConnection.loginmavenir(driver);
			CreateConnection.Createconnection(driver);
			//ChromeBrowser.quitdriver(driver);

	}

}
