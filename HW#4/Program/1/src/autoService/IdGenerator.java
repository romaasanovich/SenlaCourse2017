package autoService;

public class IdGenerator {
	static void setNewId(Entity[] entity) {
		for(int i=0;i<=entity.length;i++) {
			if(entity[i]!=null) {
				entity[i].setId(i);
				break;
			}
		}
	}
}
