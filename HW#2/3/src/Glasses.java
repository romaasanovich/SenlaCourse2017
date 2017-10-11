
public class Glasses implements IProduct {
	private Body body;
	private Bow bow;
	private Lens lens;
	
	public void installFirstPart(IProductPart part) {
		body=(Body)part;
		System.out.println("Install body");
	}
	
	public void installSecondPart(IProductPart part) {
		bow=(Bow) part;
		System.out.println("Install bows");
	}
	
	public void installThirdPart(IProductPart part) {
		lens=(Lens) part;
		System.out.println("Install lenses");
	}
}
