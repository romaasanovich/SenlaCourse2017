package com.senla.autoservice.run;


import com.senla.autoservice.api.constants.Constants;
import com.senla.autoservice.facade.Autoservice;
import com.senla.autoservice.ui.menu.MenuController;

public final class Start {

    public static void main(final String[] args) {
    	//Autoservice.getInstance().readDataFromFiles(Constants.PATH_TO_MASTERS, Constants.PATH_TO_PLACES);
    	Autoservice.getInstance().Deserialize();
        final MenuController mc = new MenuController();
        mc.run();
        Autoservice.getInstance().Serialize();
    }
}
