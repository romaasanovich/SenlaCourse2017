


import com.senla.autoservice.api.Constants;
import com.senla.autoservice.facade.Autoservice;

public class Program {
	private static final Autoservice facade = Autoservice.getInstance();

	public static void main(String[] args) {
		
		String pathToMasters;
		String pathToPlaces;
		
		if (args.length > 0) {
			pathToMasters = args[0];
			pathToPlaces = args[1];
		} else {
			pathToMasters = Constants.PATH_TO_MASTERS;
			pathToPlaces = Constants.PATH_TO_PLACES;
		}
		facade.readDataFromFiles(pathToPlaces, pathToMasters);
		facade.showAllFreePlaces();
		facade.showCurrentOrdersPrice();
		facade.showMastersByAlpha();
		facade.showOrdersByDateOfCompletion();
		facade.writeDataIntoFiles(pathToMasters, pathToPlaces);	
		}
}
