
public class PopComposition extends Composition {
	public PopComposition(String name,int size) {
		super(name,size);		
	}
	
	public Composition getTrack() {
		return new PopComposition(name,size);
	}

}