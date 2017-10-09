
public class BuildLens implements ILineStep {
	public Lens buildProductPart()
	{
	System.out.println("Build Lens");
	return new Lens();
	}
}
