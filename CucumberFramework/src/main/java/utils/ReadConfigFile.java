package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfigFile {

	protected InputStream input = null;
	protected Properties properties = null;
	
	public ReadConfigFile() {
		try {
			//ReadConfigFile.class.getClassLoader().getResourceAsStream(Constant.CONFIG_PROPERTIES_DIRECTORY);
			properties = new Properties();
			FileInputStream input = new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/java/properties/config.properties"));
			properties.load(input);
			
		}catch(Exception e) {
			e.getMessage();
		}
	}
	
	public String getBrowser() {
		if(properties.getProperty("browser")==null)
			return "";
		return properties.getProperty("browser");
	}
	
}
