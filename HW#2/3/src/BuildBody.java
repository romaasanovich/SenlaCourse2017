
public class BuildBody implements ILineStep {
	public Body buildProductPart()
	{
	System.out.println("Build Body");
	return new Body();
	}
}
