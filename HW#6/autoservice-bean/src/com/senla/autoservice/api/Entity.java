package com.senla.autoservice.api;

<<<<<<< HEAD:HW#5/autoservice-bean/src/com/senla/autoservice/api/Entity.java
public class Entity {
=======
import java.io.Serializable;

public class Entity implements Serializable	{
>>>>>>> HW#6:HW#6/autoservice-bean/src/com/senla/autoservice/api/Entity.java
	private int id;
	
	public Entity(Integer id) {
		this.id = id;
	}


	public void setId(int i) {
		this.id=i;
	}

	public int getId() {
		return id;
	}
}
