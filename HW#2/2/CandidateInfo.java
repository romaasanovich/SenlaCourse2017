import java.util.*;


class CandidateInfo extends Man {
	private int salary;
	private ArrayList<PrevPlaceOfWork> placesOfWork = new ArrayList<PrevPlaceOfWork>();
	private ArrayList<Education> education = new ArrayList<Education>();
		
	CandidateInfo(String n, String s, PrevPlaceOfWork pr,Education edu){
		name =n;
		surname=s;
		placesOfWork.add(pr);
		education.add(edu);
		System.out.println("candidate created!!!");
	}
	
	public void setSalary(int newSalary){
		salary=newSalary;
	}
	
	public int getSalary(){
		return salary;
	}
	
	public void setPrevPlaceOfWork(PrevPlaceOfWork work){
		placesOfWork.add(work);
	}
	
	public ArrayList<PrevPlaceOfWork> getPrevPlaceOfWork(){
		return placesOfWork;
	}
	
	public void setEducation(Education newEducation){
		education.add(newEducation);
	}
	
	public ArrayList<Education> getEducation(){
		return education;
	}
}