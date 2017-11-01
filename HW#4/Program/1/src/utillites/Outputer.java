package utillites;

import autoService.Entity;

public class Outputer {
		public static void printMessage(String line) {
			System.out.println(line);
		}
		
		public static void printArray(Entity [] arg) {
			for(Entity obj:arg) {
				if(obj!=null) {
				System.out.println(obj);
				}
			}
		}
}
