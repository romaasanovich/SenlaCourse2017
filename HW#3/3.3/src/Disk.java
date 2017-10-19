
public class Disk {
	private Composition[] tempArray;

	public Disk(Composition[] arrayComposition) {
		tempArray = new Composition[arrayComposition.length];
		for (int i = 0; i < arrayComposition.length; i++) {
			tempArray[i] = arrayComposition[i].getTrack();
		}
	}

	public int getSize() {
		int generalSize = 0;
		for (int i = 0; i < tempArray.length; i++) {
			generalSize += tempArray[i].size;
		}
		return generalSize;
	}
	
	public String getType() {
		Composition[] temp = tempArray.clone();
		int maxIndex = 0, maxCount = 0;
		for (int i = maxIndex; i < temp.length; i++) {
			if (temp[i] == null)
				continue;
			int count = 0;
			for (int j = i + 1; j < temp.length; j++) {
				if (temp[j] != null)
					if (temp[i].getClass().equals(temp[j].getClass())) {
						count++;
						temp[j] = null;
					}
				if (count > maxCount) {
					maxCount = count;
					maxIndex = i;
				}
			}
		}
		return tempArray[maxIndex].getClass().toString();
	}
	
}



