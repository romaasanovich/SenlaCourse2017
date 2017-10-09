class PrevPlaceOfWork{
	private String nameOfCompany;
	private int workExperience;
	private String heldPosition;
	
	PrevPlaceOfWork(String p,int w, String h){
		nameOfCompany=p;
		workExperience=w;
		heldPosition=h;
		System.out.println("place of work create!!!");
	}
	
	public void setWorkExperience(int newWorkExperience){
		workExperience= newWorkExperience;
	}
	
	public int getWorkExperience(){
		return workExperience;
	}
	
	public void setNameOfCompany(String newNameOfCompany){
		nameOfCompany= newNameOfCompany;
	}
	
	public String getNameOfCompany(){
		return nameOfCompany;
	}
	public void setHeldPosition(String newHeldPosition){
		heldPosition = newHeldPosition;
	}
	
	public String getHeldPosition(){
		return heldPosition;
	}
	
	
	
}