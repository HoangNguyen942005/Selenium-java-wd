package javaTester;

public class Topic_01_Variable {
	
 static int studentNumber;
 static boolean statusValue;
 static final String browserName="Chrome";
 
 String studentName="AutomationFC";
 
 public static void main(String[] args) {
	
	 System.out.println(studentNumber);
	 System.out.println(statusValue);
 }
 
 // Getter : getcurrentUrl/ getittle/ getText/ getAttribute/ getSize/....
 public String getStudentName() {
	 return this.studentName;
 }
 
 // Setter : click/ senkey/ clear/ select/ back/ foward/ refresh/ accept/ get....
 public void setStudentName(String stdName) {
	 this.studentName=stdName;
 }
}
