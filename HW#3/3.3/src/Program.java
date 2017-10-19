
public class Program {

	public static void main(String[] args) {
		 Composition[] compositions= new Composition[] {
				new PopComposition("song1", 15),
				new RockComposition("song2", 16),
				new DubstepComposition("song3", 25 ),
				new DubstepComposition("song3", 25 ),
				new RaggyComposition("song4",35)
		 };
		 Disk d = new Disk(compositions);
		 System.out.println("Generald Size: "+d.getSize());
		 System.out.println("General type:" + d.getType());
	}

}
