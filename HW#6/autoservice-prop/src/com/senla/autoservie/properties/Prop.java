package com.senla.autoservie.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.senla.autoservice.api.Constants;

public class Prop {

	public Properties getProp() {
		FileInputStream fileInputStream;
		Properties prop = new Properties();

		try {

			fileInputStream = new FileInputStream(Constants.PATH_TO_PROP);
			prop.load(fileInputStream);
		} catch (IOException e) {
			System.out.println(Constants.ERROR_FILE_NOT_FOUND);
			e.printStackTrace();
		}
		return prop;
	}

}
