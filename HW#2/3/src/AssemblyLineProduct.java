
public class AssemblyLineProduct implements IAssemblyLine {
	ILineStep buildBody,buildBow,buildLens;
	public AssemblyLineProduct(BuildBody buildBody,BuildLens buildLens, BuildBow buildBow){
		this.buildBody=buildBody;
		this.buildLens = buildLens;
		this.buildBow = buildBow;
	}
	
	public IProduct assembleProduct(IProduct product) {
		System.out.println("Assembly Glasses");
		product.installFirstPart(buildBody.buildProductPart());
		product.installSecondPart(buildBow.buildProductPart());
		product.installThirdPart(buildLens.buildProductPart());
		System.out.println("Glasses are assembled");
		return product;
	}
	
}
