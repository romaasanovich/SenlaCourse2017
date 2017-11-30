package com.senla.autoservice.run;


import com.senla.autoservice.facade.Autoservice;
import com.senla.autoservice.ui.menu.MenuController;

public final class Start {

    public static void main(final String[] args) {
    	Autoservice.getInstance().Deserialize();
        final MenuController mc = new MenuController();
        mc.run();
        Autoservice.getInstance().Serialize();
    }
}
