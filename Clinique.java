package IntellectTraining.JavaPractise.ObjectOriented;
	
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CliniqueManagement {

	private static String filename = "IntellectTraining/JavaPractise/ObjectOriented/cliniqueData.json";
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		Clinique clinique = new Clinique(filename);
		ArrayList<Doctor> dList= null;
		ArrayList<Patient> pList=null;;
		HashMap<DoctorAppointment, Integer> hMap = new HashMap<DoctorAppointment, Integer>();
		clinique.saveToList();
		char reinitiate;

		Utility.displayList(clinique.doctorArrList);

		System.out.println("\n**************CLINIQUE MANAGEMENT******************");
		
        do{
        	reinitiate = 'N';
			System.out.println("\nChoose an operation:  ");
			System.out.println("1.Search for doctors.\n2.Search for patients.\n3.Make an " +
				"appointment.\n4.Print the Doctor Patient Report.\n5.Exit.");
			int choice = sc.nextInt();
			switch(choice)
			{
				case 1:
					  char flag = 'N';
				      do{
				      	System.out.println("1.Search by ID.\n2.Search by Name.\n3.Search by "+ 
				      		"Specialization\n4.Search by Availability\n5.Exit");
				      	System.out.println("Enter your choice.");
				      	int ch = sc.nextInt();
				      	dList = null;
				      	switch(ch)
				      	{
				      		case 1:
				      			  System.out.println("Enter the id: ");
				      			  int docId = sc.nextInt();
				      		      dList = SearchUtility.searchDocById(clinique.doctorArrList, docId);
				      		      Utility.displayList(dList);
				      		      break;
				      		case 2:
				      			  System.out.println("Enter the name: ");
				      			  String docName = sc.next();
				      		      dList = SearchUtility.searchDocByName(clinique.doctorArrList, docName);
				      		      Utility.displayList(dList);
				      		      break;
				      		case 3:
				      			  System.out.println("Enter the specialization: ");
				      			  String spec = sc.next();
				      		      dList = SearchUtility.searchDocBySpec(clinique.doctorArrList, spec);
				      		      Utility.displayList(dList);
				      		      break;
				      		case 4:
				      			  System.out.println("Enter the availability: ");
				      			  String avail = sc.next();
				      		      dList = SearchUtility.searchDocByAvail(clinique.doctorArrList, avail);
				      		      Utility.displayList(dList);
				      		      break;
				      		case 5:
				      			break;			      	 
				      	}
				      	System.out.println("Do you want to continue searching doctors(y/n)?");
				      	flag = sc.next().toUpperCase().charAt(0);
				      }
				      while(flag == 'Y');
				      break;

				case 2:
					  flag = 'N';
				      do{
				      	System.out.println("1.Search by ID.\n2.Search by Name.\n3.Search by"+ 
				      		"Mobile Number. \n4.Exit");
				      	System.out.println("Enter your choice.");
				      	int ch = sc.nextInt();
		                pList = null;
				      	switch(ch)
				      	{
				      		case 1:
				      			  System.out.println("Enter the id: ");
				      			  int patId = sc.nextInt();
				      		      pList = SearchUtility.searchPatById(clinique.patientArrList, patId);
				      		      Utility.displayList(pList);
				      		      break;
				      		case 2:
				      			  System.out.println("Enter the name: ");
				      			  String patName = sc.next();
				      			  pList = SearchUtility.searchPatByName(clinique.patientArrList, patName);
				      		      Utility.displayList(pList);
				      		      break;
				      		case 3:
				      			  System.out.println("Enter the mobile number: ");
				      			  String mob = sc.next();
				      		      pList = SearchUtility.searchPatByMob(clinique.patientArrList, mob);
				      		      Utility.displayList(dList);
				      		      break;
				      		case 4:
				      			break;		      	 
				      	}
				      	System.out.println("Do you want to continue searching patients(y/n)?");
				      	flag = sc.next().toUpperCase().charAt(0);
				      }
				      while(flag == 'Y');
				      break;
				      
				case 3:
					  int id = 0;
					  Date inpDate = null;
					  boolean doSetAnotherDate;
					  System.out.println("Enter the (Doctor's ID/date) for whom you wish to schedule appoitment.");
					  boolean isNotValidDocId = true;
					
					  System.out.print("Id: ");
					  while(isNotValidDocId)
					  {
						  id = sc.nextInt();					  
						  if(SearchUtility.searchDocById(clinique.doctorArrList, id) == null)
						  {
							  System.out.println("Sorry!!Doctor with Id"+ id + "does not exists.\nPlease enter correct Doctor Id: ");
						  }
						  else
							  isNotValidDocId = false;
					  }
					  do{
					  	doSetAnotherDate = false;
					 	boolean isDateValid = true;
					  	
					  	System.out.print("Date of appointment(mm-dd-YYYY): ");
					  	while(isDateValid){
						  	inpDate = Utility.parseDate(sc.next());
					      	if(inpDate == null)
					      	{
					    	  	System.out.println("Wrong date format!Re-Enter the date!!");
							 	System.out.print("Date of appointment(mm-dd-YYYY):  ");
					    	}
					      	else
					    	 	isDateValid = false;
					  		}
					  	
					  	
					    int appointments = 0;;
					    int totAppointments = 0;
						DoctorAppointment newAppoint = new DoctorAppointment(id, inpDate);
						System.out.println(hMap.containsKey(newAppoint));
					  	if(hMap.containsKey(newAppoint))
					  	{
					  		System.out.println("*****");
						 	appointments = hMap.get(newAppoint); 
                            totAppointments = appointments + 1;
						 	if(appointments < 5)
						  	{
								hMap.put(newAppoint, totAppointments);
						  	}
						    else
						  	{
							  	System.out.println("Doctor with "+ newAppoint.getId() +" is occupied for the day.\nSchedule for some other date.");
							  	doSetAnotherDate = true;
						  	}
					  	}
					  	else
					  	{
					  		hMap.put(newAppoint, 1);	
					  	}
				 	 }
				 	 while(doSetAnotherDate);
				 	 break;

				case 4:
				      System.out.println("*********************DOCTOR PATIENT REPORT******************");
				      Utility.displayHashmap(hMap); 
				      break;
				case 5:
				      System.out.println();
				      System.out.println("Most Popular Doctor:  ");
				      Utility.searchPopularityById(hMap, dList);
				      System.out.println("Most Popular Specialization:  ");
				      Utility.searchPopularityBySpec(hMap, dList);
				      System.exit(0);
			}
			System.out.println("Do you want to continue searching(y/n) ?");
			reinitiate = sc.next().toUpperCase().charAt(0);
		}
		while(reinitiate == 'Y');
	}
}


class Doctor {
	int doc_id;
	String doc_name;
	String spec;
	String avail;
	Doctor()
	{}
	void setId(int doc_id)
	{
		this.doc_id = doc_id;
	}
	void setName(String doc_name)
	{
		this.doc_name = doc_name;
	}
	void setSpec(String spec)
	{
		this.spec = spec;
	}
	void setAvail(String avail)
	{
		this.avail = avail;
	}
	int getId()
	{
		return doc_id;
	}
	String getName()
	{
		return doc_name;
	}
	String getSpec()
	{
		return spec;
	}
	String getAvail()
	{
		return avail;
	}
}


class Patient {
	int patient_id;
	int age;
	String patient_name;
	String mob;
	Patient()
	{}
	void setId(int patient_id)
	{
		this.patient_id = patient_id;
	}
	void setAge(int age)
	{
		this.age = age;
	}
	void setName(String patient_name)
	{
		this.patient_name = patient_name;
	}
	void setMob(String mob)
	{
		this.mob = mob;
	}
	int getId()
	{
		return patient_id;
	}
	int getAge()
	{
		return age;
	}
	String getName()
	{
		return patient_name;
	}
	String getMob()
	{
		return mob;
	}
}


class DoctorAppointment {
	Integer docId;
	Date date;
	DoctorAppointment(int docId, Date date)
	{
		this.docId = docId;
		this.date= date;
	}
	void setId(int docId)
	{
		this.docId = docId;
	}
	void setDate(Date date)
	{
		this.date = date;
	}
	int getId()
	{
		return docId;
	}
	Date getDate()
	{
		return date;
	}

	@Override
    public int hashCode() {
    	
        return docId.hashCode()+ date.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        DoctorAppointment docAppoint = (DoctorAppointment) obj;
      
        return docAppoint.docId == this.docId  && docAppoint.date.equals(this.date);
    }
}


class Clinique {
	@SuppressWarnings("unused")
	private File file;
	private JSONParser parser;
	private JSONObject jsonObj;
	protected ArrayList<Doctor> doctorArrList;
	protected ArrayList<Patient> patientArrList;
	
	Clinique(String filename)
	{
		try{
			file = new File(filename); 
			parser = new JSONParser();			
			doctorArrList = new ArrayList<Doctor>();
			patientArrList = new ArrayList<Patient>();
			try{
			jsonObj = (JSONObject)parser.parse(new FileReader(filename));
		    }
		    catch(ParseException e)
		    {System.out.println(e);}
		}
		catch(FileNotFoundException e){
			System.out.println(e);
		}
		catch (IOException e) {
            e.printStackTrace();
        } 
            
       
	
	

