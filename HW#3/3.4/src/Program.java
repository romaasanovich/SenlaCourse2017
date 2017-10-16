
public class Program {
	public static void main(String [] s) {
		Menu menu= new Menu();
		Kitcheners kitcheners=new Kitcheners();
		Tables tables = new Tables();
		menu.addDish("Murshmallows", 1.0);
		menu.outputMenu();
		kitcheners.outputWhatDoKitcheners();
		tables.tables[0].order.addDish("Fried Fish",menu);
		tables.tables[0].order.addDish("Murshmallows",menu);
		tables.tables[0].order.checkOut(kitcheners,tables.tables[0]);
		tables.tables[0].outputTotalPriceOfOrder();
		kitcheners.outputListOfKitcheners();
		kitcheners.outputWhatDoKitcheners();
	}

}
