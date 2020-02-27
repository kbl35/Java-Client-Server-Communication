import java.io.Serializable;


public class Student implements Serializable{
	private static final long serialVersionUID = 1L;
	  
	
	 private String Fname;
	 private  String Lname;
	 private  int ID;
	 private  int score;
	 
	 Student()
	 {}
	 
	 Student(Student obj)
	 {
		 this.ID = obj.ID;
		 this.Fname = obj.Fname;
		 this.Lname = obj.Lname;
		 this.score = obj.score;
	 }
	 
	 public int getId() {
		 return ID;
	 }
	 
	 public void setId(int ID) {
		 this.ID = ID;
	 }
	 
	 public String getFName()
	 {
		 return this.Fname;
	 }
	 
	 public void setFName(String name)
	 {
		 this.Fname = name;
	 }
	 
	 public String getLName()
	 {
		 return Lname;
	 }
	 
	 public void setLName(String name)
	 {
		 this.Lname = name;
	 }
	 
	 public void setScore(int score)
	 {
		 this.score = score;
	 }
	 
	 public int getScore()
	 {
		 return score;
	 }
	 
	 public String toString() 
	 {
		 return "Student ID: "+ ID + "  "+ "Student First Name: " + Fname + "  " +  "Student Last name: " + Lname + "  "+ "Student's Score" + score;
	 }
	   
}