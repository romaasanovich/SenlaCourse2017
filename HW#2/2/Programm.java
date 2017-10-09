import java.util.*;
class Programm {
	public static void main(String[] args){
		Education education=new Education("GrSu","POIT");
		PrevPlaceOfWork prevWork= new PrevPlaceOfWork("Entan",3,"Sys Admin");
		CandidateInfo candidate = new CandidateInfo("Alex","Ivanov",prevWork,education);
		System.out.println(candidate.getName()+", "+candidate.getSurName()+", "+prevWork.getHeldPosition()+", "+prevWork.getNameOfCompany()+", "+education.getNameOfEstablishmentOfEducation()+", "+education.getNameOfSpec());
		candidate.setName("Kate");
		candidate.setSurName("Petrova");
		prevWork.setHeldPosition("QA");
		prevWork.setNameOfCompany("Some Name of Company");
		education.setNameOfEstablishmentOfEducation("BSU");
		education.setNameOfSpec("Computer Science");
		System.out.println(candidate.getName()+", "+candidate.getSurName()+", "+prevWork.getHeldPosition()+", "+prevWork.getNameOfCompany()+", "+education.getNameOfEstablishmentOfEducation()+", "+education.getNameOfSpec());
	}
	
}