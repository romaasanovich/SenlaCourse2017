package com.senla.autoservice.ui.action.placemenu;

import com.senla.autoservice.facade.Autoservice;
import com.senla.autoservice.ui.action.IAction;

public class ImportPlaceAction implements IAction{
	Autoservice autoservice = Autoservice.getInstance();

	@Override
	public void excute() {
		autoservice.importPlaces();
	}


}
