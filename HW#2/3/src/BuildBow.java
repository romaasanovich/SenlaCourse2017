
public class BuildBow implements ILineStep{
	public Bow buildProductPart()
	{
	System.out.println("Build Bow");
	return new Bow();
	}
}
