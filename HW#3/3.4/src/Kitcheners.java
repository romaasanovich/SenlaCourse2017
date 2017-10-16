
public class Kitcheners {

	public Kitchener[] kitcheners = new Kitchener[] {
			new Kitchener("Mike"),new Kitchener("Lilie"),new Kitchener("Bob"),new Kitchener("John")
	};

	public void addDish(String name) {
		Kitchener temp[] = new Kitchener[kitcheners.length + 1];
		for (int i = 0; i < kitcheners.length; i++) {
			temp[i] = kitcheners[i];
		}

		Kitchener newDish = new Kitchener(name);
		temp[kitcheners.length + 1] = newDish;
		kitcheners = temp;
	}

	public void delDish(String chossenName) {
		Kitchener temp[] = new Kitchener[kitcheners.length - 1];
		for (int j=0,i = 0; i < kitcheners.length;j++, i++) {
			if (!kitcheners[i].name.equals(chossenName)) {
				temp[i] = kitcheners[j];
			}
			else {
				j++;
				temp[i] = kitcheners[j];
			}
		}
		kitcheners = temp;
	}

	public void outputListOfKitcheners() {
		for (int i = 0; i < kitcheners.length; i++) {
			if (kitcheners[i].isCook == true) {
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("Kitchener ");
				stringBuilder.append(kitcheners[i].name);
				stringBuilder.append(" cook ");
				stringBuilder.append(kitcheners[i].dish.name);
				stringBuilder.append(" for ");
				stringBuilder.append(kitcheners[i].table.numOfTable+1);
				stringBuilder.append(" table.");
				System.out.println(stringBuilder.toString());
			}
		}
	}

	public void outputWhatDoKitcheners() {
		for (int i = 0; i < kitcheners.length; i++) {
			System.out.print("Kitchener "+kitcheners[i].name);
				if (kitcheners[i].isCook == true) {
					System.out.println(" is cook");
					}
				else System.out.println(" isn't cook");
		}
	}
}
