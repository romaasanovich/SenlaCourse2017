package garage;

import java.util.GregorianCalendar;

import autoService.Constants;
import master.MastersList;
import utills.ArrayChecker;

public class GarageManager {
	private static final String	PLACE_WAS_SUCCESFUL_ADDED = "Room was succesful added";
	private static final String NO_ANY_PLACES = "Error. There is no any place\n";
	private static final String PLACE_STATUS_WAS_CHANGED = "Place's status was changed";
	private static final String NO_ANY_FREE_PLACES = "Error. There is no any free place\n";
	private static final String FREE_PLACES = "free places is:";
	
	
	
	private GaragePlaces places;

	public GarageManager(){
		places = new GaragePlaces(Constants.ARRAY_SIZE);
	}
	
	public GaragePlaces getPlaces() {
		return places;
	}

	public void setGarage(GaragePlaces garage) {
		this.places = garage;
	}
	
	
	public String showFreePlaces() {
		Place[] freePlaces =	places.getFreePlaces();
		if (freePlaces == null || freePlaces.length == 0) {
			return NO_ANY_PLACES;
		}
		StringBuilder sb = new StringBuilder();
		for (Place place : freePlaces) {
			if (place == null) {
				break;
			}
			sb.append(place).append("\n");
		}
		return sb.toString();
	}
	
	public String add(Place place) {
		String message;
		if (places.add(place)) {
			message = PLACE_WAS_SUCCESFUL_ADDED;
		} else {
			message = Constants.ERROR_THIS_ID_IS_NOT_FREE;
		}
		return message;
	}
	
	
	public String getCountOfFreeDate(GregorianCalendar date, MastersList masters) {
		String message;
		if(places.getFreePlacesOnDate(date, masters)==0) {
			message = NO_ANY_FREE_PLACES;
		}
		else message = (FREE_PLACES+places.getFreePlacesOnDate(date, masters));
		return message;
		
	}
	
	public String changeStatus(Integer id,Boolean bool) {
		places.getPlaceById(id).setBusy(bool);
		return PLACE_STATUS_WAS_CHANGED;
	}
	
}
