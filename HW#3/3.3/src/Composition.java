
public abstract class Composition {
	protected int size;
	protected String name;
	
	
	public abstract Composition getTrack();
	
	public Composition(String name,int size) {
		setName(name);
		setSize(size);
	}
	
	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
	
	public void setSize(int size) {
		this.size=size;
	}
	public int getSize(){
		return size;
	}
	
}
	