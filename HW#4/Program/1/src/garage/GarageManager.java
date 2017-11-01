package garage;

import java.util.Date;
import java.util.Arrays;
import java.util.Comparator;

import autoService.Constants;
import master.MastersList;

public class GarageManager {
	private static final String PLACE_WAS_SUCCESFUL_ADDED = "Room was succesful added";
	private GaragePlaces places;

	public GarageManager() {
		places = new GaragePlaces(Constants.ARRAY_SIZE);
	}

	public GaragePlaces getPlaces() {
		return places;
	}

	public void setGarage(GaragePlaces garage) {
		this.places = garage;
	}

	public GaragePlaces getSortedPlaces(Comparator<Place> comp) {
		Arrays.sort(places.getPlaces(), comp);
		return places;
	}

	public GaragePlaces getFreePlaces() {
		GaragePlaces temp = new GaragePlaces(places.getPlaces().length);
		for (Place place : places.getPlaces()) {
			if (place == null) {
				break;
			}
			if (!place.isBusy()) {
				temp.add(place);
			}
		}
		return temp;
	}

	public GaragePlaces getSortedFreePlaces(Comparator<Place> comp) {
		GaragePlaces temp = getFreePlaces();
		Arrays.sort(temp.getPlaces(), comp);
		return temp;
	}

	public int getCountOfFreePlacesOnDate(Date date, MastersList masters) {
		int count = 0;
		for (int i = 0; i < masters.getListOfMasters().length; i++)
			if (masters.getMasterById(i).getOrders()!= null ) {
				for (int j = 0; j < masters.getMasterById(i).getOrders().getListOfOrders().length; j++) {
					if (!masters.getMasterById(i).getOrders().getOrderById(j).getDateOfOrder().after(date)
							&& !masters.getMasterById(i).getOrders().getOrderById(j).getDateOfOrder().before(date)) {
						count++;
					}
				}
			}
		if (count > getPlaces().getPlaces().length) {
			return getPlaces().getPlaces().length;
		} else {
			return count;
		}
	}

	/*
	 * public String showFreePlaces() { Place[] freePlaces = places.getFreePlaces();
	 * if (freePlaces == null || freePlaces.length == 0) { return NO_ANY_PLACES; }
	 * StringBuilder sb = new StringBuilder(); for (Place place : freePlaces) { if
	 * (place == null) { break; } sb.append(place).append("\n"); } return
	 * sb.toString(); }
	 */

	public String add(Place place) {
		String message;
		if (places.add(place)) {
			message = PLACE_WAS_SUCCESFUL_ADDED;
		} else {
			message = Constants.ERROR_THIS_ID_IS_NOT_FREE;
		}
		return message;
	}

	/*
	 * public String getCountOfFreeDate(GregorianCalendar date, MastersList masters)
	 * { String message; if(places.getFreePlacesOnDate(date, masters)==0) { message
	 * = NO_ANY_FREE_PLACES; } else message =
	 * (FREE_PLACES+places.getFreePlacesOnDate(date, masters)); return message;
	 * 
	 * }
	 * 
	 * public String changeStatus(Integer id,Boolean bool) {
	 * places.getPlaceById(id).setBusy(bool); return PLACE_STATUS_WAS_CHANGED; }
	 */
}
