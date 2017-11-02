package comparator.master;

import java.util.Comparator;
import master.Master;

public class SortedByAlphabet implements Comparator<Master> {
	public  int compare(Master obj1, Master obj2) {
		String str1=obj1.getName();
		String str2=obj2.getName();
		return str1.compareTo(str2);
	}
}
