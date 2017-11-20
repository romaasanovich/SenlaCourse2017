package com.senla.autoservice.utills;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.senla.autoservice.api.Constants;

public final class ExceptionLogger {

	private static Logger logger = Logger.getLogger("Logger");
	private static Handler fileHandler;

	public void write(String message, final Exception ex) {
		try {
			fileHandler = new FileHandler(Constants.LOG_PATH);
			logger= Logger.getLogger(message);
			logger.setUseParentHandlers(false);
			logger.addHandler(fileHandler);
			logger.log(Level.SEVERE, "Exception", ex);
		} catch (final IOException e) {
			Printer.printMessage("No logger file");
		}
	}

}