package com.senla.autoservice.csvimporexport;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.senla.autoservice.api.AEntity;

public class CsvExportImport<T extends AEntity> {
	private static final Logger logger = LogManager.getRootLogger();

	public void importToCsv(ArrayList<T> data, String path) throws IOException {

		try (FileWriter fW = new FileWriter(new File(path))) {
			;
			for (T t : data) {
				fW.write(t.toString());
			}
		} catch (IOException ex) {
			logger.error(ex);
		}
	}
	
}
