package com.senla.autoservie.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.senla.autoservice.api.constants.Constants;;

public class Prop {
	private static final Logger logger = Logger.getLogger(Prop.class.getName());
	private static java.util.Properties properties = new java.util.Properties();
	
	 public static void loadConfiguration(){
	 try (FileInputStream fis = new FileInputStream("..\\autoservice-view\\src\\com\\senla\\autoservice\\facade\\res\\Prop.txt")){
         properties.load(fis);
     } catch (FileNotFoundException e) {
         e.printStackTrace();
     } catch (IOException e) {
         e.printStackTrace();
     }
 }

	
	
	public Prop() {
		try {
			final Handler fileHandler = new FileHandler(Constants.LOG_PATH);
			logger.setUseParentHandlers(false);
			logger.addHandler(fileHandler);
		} catch (final IOException e) {
			logger.log(Level.SEVERE, Constants.LOGGER_MSG, e);
		}
		loadConfiguration();
	}
	
	
	 public static  String getProp(String key){
	        return properties.getProperty(key, "");
	    }

}
