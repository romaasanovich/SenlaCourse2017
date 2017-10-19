
public class RockComposition extends Composition {
	public RockComposition(String name,int size) {
		super(name,size);		
	}
	
	public Composition getTrack() {
		return new RockComposition(name,size);
	}

}
