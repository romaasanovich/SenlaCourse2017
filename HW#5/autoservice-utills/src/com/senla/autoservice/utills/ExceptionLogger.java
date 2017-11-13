package com.senla.autoservice.utills;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.senla.autoservice.api.Constants;

public final class ExceptionLogger {

	private static final Logger logger = Logger.getLogger("Logger");
	private static Handler fileHandler;

	public static void write(final Exception ex) {
		try {
			fileHandler = new FileHandler(Constants.LOG_PATH);
			logger.setUseParentHandlers(false);
			logger.addHandler(fileHandler);
			logger.log(Level.SEVERE, "Exception", ex);
		} catch (final IOException e) {
			Printer.printMessage("No logger file");
		}
	}

}