package browser;

import java.util.Collections;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeBrowser {
	 //public static String driverPath = "C:/Messaging/";
	//public static String driverPath = "resources/";
	 public static void main(String[] args) {
		 

			 }

	 public static WebDriver getdriver(String driverPath){	 		
	 		 System.setProperty("webdriver.chrome.driver",driverPath +"chromedriver.exe");
			 ChromeOptions options = new ChromeOptions();
			 options.setExperimentalOption("excludeSwitches", Collections.singletonList("disable-automation"));		 
			 DesiredCapabilities handlSSLErr = DesiredCapabilities.chrome();       
			 handlSSLErr.setCapability (CapabilityType.ACCEPT_SSL_CERTS, true);
			 WebDriver driver = new ChromeDriver (handlSSLErr);
			return driver;
	 	}
		  
	 	public static void loginmavenir(WebDriver driver){	 		
			 
			 driver.get("www.google.com");

	 	}

		public static void quitdriver(WebDriver driver) {
			driver.close();
			driver.quit();
			
		}

}
