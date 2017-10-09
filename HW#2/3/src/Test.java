
public class Test {
	public static void main (String[] s) {
		System.out.println("Start new Line");
		AssemblyLineProduct line = new AssemblyLineProduct(new BuildBody(), new BuildLens(),new BuildBow());
		Glasses glasses = new Glasses();
		glasses =(Glasses) line.assembleProduct(glasses);
	}
}
