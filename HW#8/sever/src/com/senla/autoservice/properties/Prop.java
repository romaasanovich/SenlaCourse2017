package com.senla.autoservice.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.senla.autoservice.api.constants.Constants;;

public class Prop {
	private static final Logger logger = Logger.getLogger(Prop.class.getName());
	private static java.util.Properties properties = new java.util.Properties();


	public Prop() {
		try (FileInputStream fis = new FileInputStream(Constants.PATH_TO_PROP)) {
			properties.load(fis);
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE, Constants.LOGGER_MSG, e);
		} catch (IOException e) {
			logger.log(Level.SEVERE, Constants.LOGGER_MSG, e);
		}
	}

	public static String getProp(String key) {
		return properties.getProperty(key, "");
	}

}
