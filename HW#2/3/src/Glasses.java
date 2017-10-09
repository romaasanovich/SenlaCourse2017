
public class Glasses implements IProduct {
	public void installFirstPart(IProductPart body) {
		System.out.println("Install body");
	}
	
	public void installSecondPart(IProductPart bow) {
		System.out.println("Install bows");
	}
	
	public void installThirdPart(IProductPart lens) {
		System.out.println("Install lenses");
	}
}
