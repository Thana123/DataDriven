package properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

public class PropertiesFile {
	private static Logger Log = Logger.getLogger(PropertiesFile.class);
	public String GeneralProperties (String PrValue){
		String Values = null;
		try {
			Properties property=new Properties();
			property.load(new FileInputStream("Resources/GeneralConf.properties"));
			Values = property.getProperty(PrValue);
			Log.info("Properties file is loaded to get : " +PrValue + " is " + Values);
		} catch (FileNotFoundException e) {
			Log.info("Could not find the properties file");
		} catch (IOException e) {
			Log.info("Script failed to load the properties file");
		}
		return Values ;
	}
}
