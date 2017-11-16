

import com.senla.autoservice.api.Constants;
import com.senla.autoservice.facade.Autoservice;
import com.senla.autoservice.ui.MenuController;

public final class Start {

    public static void main(final String[] args) {

    	String pathToMasters;
		String pathToPlaces;
		
		if (args.length > 0) {
			pathToMasters = args[0];
			pathToPlaces = args[1];
		} else {
			pathToMasters = Constants.PATH_TO_MASTERS;
			pathToPlaces = Constants.PATH_TO_PLACES;
		}
        Autoservice.getInstance().readDataFromFiles(pathToMasters,pathToPlaces);
        final MenuController mc = new MenuController();
        mc.run();
        Autoservice.getInstance().writeDataIntoFiles(pathToMasters,pathToPlaces);

    }
}
