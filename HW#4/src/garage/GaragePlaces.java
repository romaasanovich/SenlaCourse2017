package garage;
import java.util.Arrays;
import java.util.Comparator;
import java.util.GregorianCalendar;

import master.MastersList;
import utills.ArrayChecker;

public class GaragePlaces {
	private Place[] placesList;
	static private int lastID;
	
	public GaragePlaces(int size){
		placesList = new Place[size];
	}
	
	public Place[] getPlaces() {
		
		return placesList;
	}
	
public Place[] getSortedPlaces(Comparator<Place> comp) {
		Arrays.sort(placesList,comp);
		return placesList;
	}

	public Place[]getFreePlaces(){
		Place[] temp = new Place [placesList.length];
		Integer pos=0;
		for(Place place:placesList) {
			if(place == null) {
				break;
			}
			if(!place.isBusy()) {
				temp[pos++]= place;
			}
		}
		
		return temp;
	}
	
	public Place[]getSortedFreePlaces(Comparator<Place> comp){
		Place[] temp = getFreePlaces();
		Arrays.sort(temp, comp);
		return temp;
	}
	
	public Place getPlaceById(int id) {
		for (int i = 0; i < placesList.length; i++) {
			if (placesList[i].getId() == id) {
				return placesList[i];
			}
		}
		return null;
	}
	
	
	public Boolean add(Place obj) {
		if (!ArrayChecker.isFreeId(obj.getId(), placesList)) {
			return false;
		}
		if (!ArrayChecker.isEnoughtSpace(placesList)) {
			placesList = Arrays.copyOf(ArrayChecker.resize(placesList),
					placesList.length*2, Place[].class);
		}
		Integer position = ArrayChecker.getFreePosition(placesList);
		lastID=position;
		placesList[position] = obj;
		return true;
	}
	
	public int getFreePlacesOnDate(GregorianCalendar date, MastersList masters) {
		int count = 0;
		for (int i = 0; i < masters.getListOfMasters().length; i++)
			for (int j = 0; j < masters.getMasterById(i).getOrders().getListOfOrders().length; j++) {
				if (!masters.getMasterById(i).getOrders().getOrderById(j).getDateOfOrder().after(date)
						&& !masters.getMasterById(i).getOrders().getOrderById(j).getDateOfOrder().before(date)) {
					count++;
				}
			}
		if (count > getPlaces().length) {
			return getPlaces().length;
		} else {
			return count;
		}
	}
}

