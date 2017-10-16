
public class Composition {
	public int size=0;
	Type type;
		Composition( int size, Type  type){
			this.size=size;
			this.type=type;
		}
		Composition(){
			size =8;
			type=Type.POP;
		}
		
}

enum Type{
	ROCK,
	CLUB,
	POP,
	RAGGY
}