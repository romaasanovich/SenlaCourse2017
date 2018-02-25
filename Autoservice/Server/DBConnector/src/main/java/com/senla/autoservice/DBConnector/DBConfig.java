package com.senla.autoservice.DBConnector;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.senla.autoservice.utills.constants.Constants;

public class DBConfig {
    private static java.util.Properties properties = new java.util.Properties();
    private final Logger logger = LogManager.getLogger(getClass().getSimpleName());


    public DBConfig() throws IOException {
        FileInputStream fis = new FileInputStream(Constants.PATH_TO_DB_PROP);
        try {
            properties.load(fis);
        } catch (FileNotFoundException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        } finally {
            fis.close();
        }
    }

    public static String getProp(String key) {
        return properties.getProperty(key, "");
    }


}
