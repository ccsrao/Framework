package com.helperclasses;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigFile {
	
	public File src;
	public FileInputStream fis;
	public static Properties prop;

	// Method for get the config property file
	public void loadPropertyFile(String configFileName) throws Exception {
		src = new File("." + File.separator + "ConfigUtilityFiles" + File.separator + configFileName);
		fis = new FileInputStream(src);
		prop = new Properties();
		prop.load(fis);
	}

	// Method for Getting the values from config property file
	public String getProperty(String propKey) {
		return prop.getProperty(propKey);
	}
}
