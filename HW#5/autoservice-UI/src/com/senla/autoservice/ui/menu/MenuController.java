package com.senla.autoservice.ui.menu;

import java.io.IOException;

import com.senla.autoservice.api.Constants;
import com.senla.autoservice.utills.Printer;
import com.senla.autoservice.utills.Reader;

public class MenuController {

	private final Builder builder;
	private final Navigator navigator;

	public MenuController() {
		builder = new Builder();
		navigator = new Navigator(builder.buildMenu());
	}

	public void run() {
		boolean flag = true;
		while (flag) {
			try {
				final Integer input = Reader.readInt();
				if (input == 0) {
					flag = false;
					Printer.printMessage("Saving data");
					break;
				}
				navigator.navigate(input);
			} catch (IOException | NumberFormatException | IndexOutOfBoundsException e) {
				Printer.printMessage(Constants.ERROR_WRONG_INPUT);
			} catch (final NullPointerException e) {

				break;
			}
		}
	}
}
