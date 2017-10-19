
public class RaggyComposition extends Composition {
	public RaggyComposition(String name,int size) {
		super(name,size);		
	}
	
	public Composition getTrack() {
		return new RaggyComposition(name,size);
	}

}
