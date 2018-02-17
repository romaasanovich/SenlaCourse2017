package com.senla.autoservice.DBConnector;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.senla.autoservice.api.constants.Constants;

public class DBConfig {	
	private static java.util.Properties properties = new java.util.Properties();
	


	public DBConfig() {
		try (FileInputStream fis = new FileInputStream(Constants.PATH_TO_DB_PROP)) {
			properties.load(fis);
		} catch (FileNotFoundException e) {
		//	logger.log(Level.SEVERE, Constants.LOGGER_MSG, e);
		} catch (IOException e) {
			//logger.log(Level.SEVERE, Constants.LOGGER_MSG, e);
		}
	}

	public static String getProp(String key) {
		return properties.getProperty(key, "");
	}


}
