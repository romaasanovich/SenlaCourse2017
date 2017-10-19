
public class DubstepComposition extends Composition {
	public DubstepComposition(String name,int size) {
		super(name,size);		
	}
	
	public Composition getTrack() {
		return new DubstepComposition(name,size);
	}

}
