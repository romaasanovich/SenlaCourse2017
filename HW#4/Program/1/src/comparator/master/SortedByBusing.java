package comparator.master;

import java.util.Comparator;

import master.Master;

public class SortedByBusing implements Comparator<Master> {
	public  int compare(Master obj1, Master obj2) {
		Boolean str1=obj1.getIsWork();
		Boolean str2=obj2.getIsWork();
		return str1.compareTo(str2);
	}
}
