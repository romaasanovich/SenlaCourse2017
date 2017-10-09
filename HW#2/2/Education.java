class Education{
	private String nameOfEstablishmentOfEducation;
	private String nameOfSpec;
		
	Education(String n, String h){
		nameOfEstablishmentOfEducation=n;
		nameOfSpec=h;
		System.out.println("education create!!!");
	}
		
	public void setNameOfEstablishmentOfEducation(String newNameOfEstablishmentOfEducation){
		nameOfEstablishmentOfEducation= newNameOfEstablishmentOfEducation;
	}
	
	public String getNameOfEstablishmentOfEducation(){
		return nameOfEstablishmentOfEducation;
	}
	public void setNameOfSpec(String newNameOfSpec){
		nameOfSpec = newNameOfSpec;
	}
	
	public String getNameOfSpec(){
		return nameOfSpec;
	}
	
}