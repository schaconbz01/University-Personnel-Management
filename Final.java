//Final Project
//Name: Sebastian Chacon (I did it alone)

import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;

public class FinalProject {

	public static void main(String[] args) {
		
		ArrayList<Person> list = new ArrayList<> ();
		Scanner scanner = new Scanner (System.in);
		
		
		
		System.out.println("\t\t\t\t\t Welcome to my Personal Management Program \n\n");
		
		while(true) {
		
		
		System.out.println("\tChoose one of the options:\n");
		
		System.out.println("\t1- Enter the information of a faculty");
		System.out.println("\t2- Enter the information of a student");
		System.out.println("\t3- Print tuition invoice for a student");
		System.out.println("\t4- Print faculty information");
		System.out.println("\t5- Enter the information of a staff member");
		System.out.println("\t6- Print the information of a staff member");
		System.out.println("\t7- Delete a person");
		System.out.println("\t8- Exit Program\n");
		
		System.out.print("\t\tEnter your selection: ");
		
		
		
		
		try {
		
		int choice; 
		
		
		Scanner choiceScan = new Scanner (System.in);
		choice = choiceScan.nextInt();
		
		
		
		
		switch(choice) {
		
		case 1:
			
			System.out.println("\n\nEnter the faculty info: ");
			
			System.out.print("\n\tName of the faculty: ");
			String facultyName = scanner.nextLine();
			
			
			String facultyId;
			while(true) {
				System.out.print("\n\tID: ");
				facultyId = scanner.nextLine();
				
				if(facultyId.matches("^[A-z]{2}[0-9]{4}")) {
					boolean duplicate = false;
					for (Person person : list) {
						if(person.getId().equals(facultyId)) {
							duplicate = true;
							break;
						}
					}
					if(!duplicate) {
						break;
					} else {
						System.out.println("\n\tID already exists. Please enter a unique ID.\n");
					}
				} else {
					System.out.println("\n\tInvalid ID format. Must be LetterLetterDigitDigitDigitDigit.\n");
				}
			}
			
			
			
			System.out.print("\n\tRank: ");
			String facultyRank = scanner.nextLine();
			
			while (!facultyRank.equalsIgnoreCase("Professor") && !facultyRank.equalsIgnoreCase("Adjunct")) {
				System.out.println("\n\t\t\""+ facultyRank + "\"" + " is invalid. Please try again with Professor or Adjunct: ");
				System.out.print("\n\tRank: ");
				facultyRank = scanner.nextLine();
			}
			
			System.out.print("\n\tDepartment: ");
			String facultyDepartment = scanner.nextLine();
			
			while (!facultyDepartment.equalsIgnoreCase("Mathematics") && !facultyDepartment.equalsIgnoreCase("Engineering") && 
					!facultyDepartment.equalsIgnoreCase("English")) {
				System.out.println("\n\t\t\""+ facultyDepartment + "\"" + " is invalid. Please try again with Mathematics, Engineering or English: ");
				System.out.print("\n\tDepartment: ");
				facultyDepartment = scanner.nextLine();
			}
			
			Faculty faculty = new Faculty(facultyName, facultyId, facultyDepartment, facultyRank);
			list.add(faculty);
		
			
			System.out.println("\n\nFaculty added!\n\n\n");
			
			break;
		
			
		case 2:
			
			System.out.println("\n\nEnter the student info: ");
			
			System.out.print("\n\tName of student: ");
			String studentName = scanner.nextLine();
			
			String studentId;
			while(true) {
				System.out.print("\n\tID: ");
				studentId = scanner.nextLine();
				
				if(studentId.matches("^[A-z]{2}[0-9]{4}")) {
					boolean duplicate = false;
					for (Person person : list) {
						if(person.getId().equals(studentId)) {
							duplicate = true;
							break;
						}
					}
					if(!duplicate) {
						break;
					} else {
						System.out.println("\n\tID already exists. Please enter a unique ID.\n");
					}
				} else {
					System.out.println("\n\tInvalid ID format. Must be LetterLetterDigitDigitDigitDigit.\n");
				}
			}
			
			double gpa;
			while(true) {
				System.out.print("\n\tGPA: ");
				//gpa = scanner.nextDouble();
				String gpaInput = scanner.nextLine();
				//scanner.nextLine();
				
				try {
				gpa = Double.parseDouble(gpaInput);	
					if(gpa >= 0.0 && gpa <= 4.0) {
						break;
					}else {
						System.out.println("\n\tInvalid GPA. Please enter a value between 0.0 and 4.0.\n");
					}
					
				} catch (NumberFormatException e) {
					System.out.println("\n\tInvalid GPA format. Please enter a numeric value (0.0-4.0).\n");
				}
				
			}
			
			
			int creditHours;
			while(true) {
				System.out.print("\n\tCredit hours: ");
				//creditHours = scanner.nextInt();
				String creditHoursInput = scanner.nextLine();
				//scanner.nextLine();
				try {
			        creditHours = Integer.parseInt(creditHoursInput);

			        
			        if (creditHours >= 0 && creditHours <= 20) {
			            break;
			        } else {
			            System.out.println("\n\tInvalid credit hours. Please enter a value between 0 and 20.\n");
			        }
			    } catch (NumberFormatException e) {
			        System.out.println("\n\tInvalid credit hours format. Please enter a numeric value (0-20).\n");
			    }
			}
			
			
			
			Student student = new Student(studentName, studentId, gpa,creditHours);
			list.add(student);
			
			System.out.println("\n\nStudent added!\n\n\n");
			
			break;
			
			
		case 3:
			
			System.out.print("\n\nEnter the student's ID: ");
			String tuitionId = scanner.nextLine();
			
			boolean found = false;
			
			for(Person person : list) {
				if( person instanceof Student && person.getId().equals(tuitionId)) {
					Student tuitionStudent = (Student) person;
					tuitionStudent.print();
					found = true;
					break;
				}
				
			}
			
			if(!found) {
				System.out.println("\n\tNo student matched!\n\n");
			}
			
			break;
		
			
		case 4:
			
			System.out.print("\n\tEnter the Faculty's id: ");
			String facultyIdInfo = scanner.nextLine();
			found = false;
			
			for(Person person : list) {
				if( person instanceof Faculty && person.getId().equals(facultyIdInfo)) {
					Faculty facultyInfo = (Faculty) person;
					facultyInfo.print();
					found = true;
					break;
				}
				
			}
			if(!found) {
				System.out.println("\n\tFaculty not found.");
			}	
					
			break;
			
			
		case 5:
				
			System.out.print("\n\n\tName of the staff member: ");
			String staffName = scanner.nextLine();
			String staffId;
			
			while(true) {
				
				System.out.print("\n\tID: ");
				staffId = scanner.nextLine();
				
				if(staffId.matches("^[A-z]{2}[0-9]{4}")) {
					boolean duplicate = false;
					for (Person person : list) {
						if(person.getId().equals(staffId)) {
							duplicate = true;
							break;
						}
					}
					if(!duplicate) {
						break;
					} else {
						System.out.println("\n\tID already exists. Please enter a unique ID.\n");
					}
				} else {
					System.out.println("\n\tInvalid ID format. Must be LetterLetterDigitDigitDigitDigit.\n");
				}
				
			}
			
			System.out.print("\n\tDepartment: ");
			String staffDepartment = scanner.nextLine();
			
			while (!staffDepartment.equalsIgnoreCase("Mathematics") && !staffDepartment.equalsIgnoreCase("Engineering") && 
					!staffDepartment.equalsIgnoreCase("English")) {
				System.out.println("\n\t\t\""+ staffDepartment + "\"" + " is invalid. Please try again with Mathematics, Engineering or English: ");
				System.out.print("\n\tDepartment: ");
				staffDepartment = scanner.nextLine();
			}
			
			System.out.print("\n\tStatus, Enter P for Part Time, or Enter F for Full Time: ");
			String staffStatus = scanner.nextLine();
			
			while (!staffStatus.equalsIgnoreCase("P") && !staffStatus.equalsIgnoreCase("F")) {
				System.out.println("\n\t\t\""+ staffStatus + "\"" + " is invalid. Please try again with P or F: ");
				System.out.print("\n\tStatus, Enter P for Part Time, or Enter F for Full Time: ");
				staffStatus = scanner.nextLine();
			}
			
			if (staffStatus.equalsIgnoreCase("P")) {
				staffStatus = "Part time";
			}
			if (staffStatus.equalsIgnoreCase("F")) {
				staffStatus = "Full time";
			}
			
			Staff staff = new Staff(staffName, staffId, staffDepartment, staffStatus);
			list.add(staff);
			
			System.out.println("\n\n\tStaff member added!\n\n\n");
			
			
			break;
		
			
			
		case 6:
				
			System.out.print("\n\n\tEnter the Staff's id: ");
			String staffIdInfo = scanner.nextLine();
			
			found = false;
			
			for(Person person : list) {
				if( person instanceof Staff && person.getId().equals(staffIdInfo)) {
					Staff staffInfo = (Staff) person;
					
					staffInfo.print();
					found = true;
					break;
				}
				
			}
			
			if(!found) {
				System.out.println("\n\tNo staff member matched!\n\n");
			}
			
			
			break;	
				
			
			
		case 7:
			
			System.out.print("\n\n\tEnter the id of the person to delete: ");
			String deleteId = scanner.nextLine();
			
			found = false;
			
			for(Person person : list) {
				if( person.getId().equals(deleteId)) {
					list.remove(person);
					
					System.out.println("\n\tPerson deleted!\n\n");
					
					found = true;
					break;
				}
				
			}
			
			if(!found) {
				System.out.println("\n\tSorry no such person exists.\n\n\n");
			}
			
			
			break;
			
			
				
		case 8:
					
			System.out.print("\n\n\tWould you like to create the report? (Y/N): ");
			String createReport = scanner.nextLine();
			
			while (!createReport.equalsIgnoreCase("Y") && !createReport.equalsIgnoreCase("N")) {
				System.out.println("\n\t\t\""+ createReport + "\"" + " is invalid. Please try again with 'Y' for yes or 'N' for no: ");
				System.out.print("\n\tWould you like to create the report? (Y/N): ");
				createReport = scanner.nextLine();
				
				}
			
			if (createReport.equalsIgnoreCase("Y")) {
				try {
					
					System.out.print("\n\tWould you like to sort your students by descending gpa or name (1 for gpa, 2 for name): ");
					String sortingChoice = scanner.nextLine();
					
					while (!sortingChoice.equals("1") && !sortingChoice.equals("2")) {
					System.out.println("\n\t\t\""+ sortingChoice + "\"" + " is invalid. Please try again with '1' for gpa or '2' for name: ");
					System.out.print("\n\tWould you like to sort your students by descending gpa or name (1 for gpa, 2 for name): ");
					sortingChoice = scanner.nextLine();
					
					}
					
					sortStudents(list, sortingChoice);
					
					generateReport(list, sortingChoice);
					System.out.println("\n\tReport created and saved on your hard drive!");
					
					
				} catch (Exception e) {
					System.out.println("\n\tOOPS! Failed to generate the reports.");
					e.printStackTrace();
				}
				
			} else if (createReport.equalsIgnoreCase("N")) {
				System.out.println("\n\tPerfect, no problem!");
			}
			
			System.out.println("\n\tGoodbye!");
			System.exit(0);
			break;
			
		default:	
			
			System.out.println("\n\tInvalid choice! Please try again.");
			
			break;
		
			}
		
		} catch (InputMismatchException e) {
			System.out.println("\n\tInvalid entry - please try again (Press enter to continue).\n\n");
			scanner.nextLine();
		
			}
		
		
		
		
		
		
		
		
		}//end of while

	}//end of main

	




//Sorting Students
	
	private static void sortStudents(ArrayList<Person> list, String sortingChoice) {
	    if (sortingChoice.equals("1")) {
	        Collections.sort(list, new Comparator<Person>() {
	            @Override
	            public int compare(Person p1, Person p2) {
	                if (p1 instanceof Student && p2 instanceof Student) {
	                    Student s1 = (Student) p1;
	                    Student s2 = (Student) p2;
	                    int result = Double.compare(s2.getGpa(), s1.getGpa());
	                    if (result == 0) {
	                        return s1.getFullName().compareToIgnoreCase(s2.getFullName());
	                    }
	                    return result;
	                }
	                return 0;
	            }
	        });
	    } else if (sortingChoice.equals("2")) {
	        Collections.sort(list, new Comparator<Person>() {
	            @Override
	            public int compare(Person p1, Person p2) {
	                if (p1 instanceof Student && p2 instanceof Student) {
	                    Student s1 = (Student) p1;
	                    Student s2 = (Student) p2;
	                    return s1.getFullName().compareToIgnoreCase(s2.getFullName());
	                }
	                return 0;
	            }
	        });
	    }
	}
	
	
//Create the report here
	
private static void generateReport(ArrayList<Person> list, String sortingChoice) throws Exception{
	
		

	
	FileWriter fileWriter = new FileWriter("report.txt");
	PrintWriter writer = new PrintWriter(fileWriter);
	
	writer.println("\t\tReport created on " + java.time.LocalDate.now());
    writer.println("\t\t***********************");
	
	
    //Printing Faculty Members
    writer.println("\n\nFaculty Members");
    writer.println("-------------------------");
    
	for (Person person : list) {
		if (person instanceof Faculty) {
			Faculty faculty = (Faculty) person;
			writer.println("\t" + faculty.getFullName());
			writer.println("\tID: " + faculty.getId());
			writer.println("\t" +faculty.getDepartment() + " Department, " + faculty.getRank());
			writer.println();
		}
	}
	
	
	//Printing Staff Members
	 writer.println("\n\nStaff Members");
	 writer.println("-------------------------");
	
	 for (Person person : list) {
			if (person instanceof Staff) {
				Staff staff = (Staff) person;
				writer.println("\t" + staff.getFullName());
				writer.println("\tID: " + staff.getId());
				writer.println("\t" + staff.getDepartment() + " Department, " + staff.getStatus());
				writer.println();
			}
		}
	
	 
	//Printing Student
	 
	 if(sortingChoice.equals("2")) {
		 
		 writer.println("\n\nStudent (Sorted by name in descending order)");
		 writer.println("-------------------------");
		 
		 for (Person person : list) {
				if (person instanceof Student) {
					Student student = (Student) person;
					writer.println("\t" + student.getFullName());
					writer.println("\tID: " + student.getId());
					writer.println("\tGpa: " + student.getGpa());
					writer.println("\tCredit hours: " + student.getCreditHours());
					writer.println();
				}
			}
	 } else if (sortingChoice.equals("1")) {
		 
		 writer.println("\n\nStudent (Sorted by gpa in descending order)");
		 writer.println("-------------------------");
		 
		 for (Person person : list) {
				if (person instanceof Student) {
					Student student = (Student) person;
					writer.println("\t" + student.getFullName());
					writer.println("\tID: " + student.getId());
					writer.println("\tGpa: " + student.getGpa());
					writer.println("\tCredit hours: " + student.getCreditHours());
					writer.println();
				}
			}
		 
	 }
	 
	 
	 writer.close();
	 
	}









}//End of FinalProject class

//________________________________________________________
abstract class Person {
	
	private String fullName;
	private String id;
	
public Person (String fullName, String id) {
		this.fullName = fullName;
		this.id = id;
	}
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public abstract void print();	
	
	
}
//______________________________________________________

class Student extends Person {
	
	private double gpa;
	private int creditHours;
	private static final DecimalFormat decfor = new DecimalFormat("0.00");
	
	
	public Student (String fullName, String id, double gpa, int creditHours) {
		super(fullName, id);
		this.gpa = gpa;
		this.creditHours = creditHours;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public int getCreditHours() {
		return creditHours;
	}

	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}
	
@Override
	public void print() {
	double totalPayment;
	double discountAmount = 0.0;
	
	System.out.println("\n\nHere is the tuition invoice for " + getFullName() + ":\n\n");
	System.out.println("---------------------------------------------------------------------------------------------------");
	System.out.println(getFullName() + "\t\t" + getId());
	System.out.println("Credit Hours: " + getCreditHours() + "\t($236.45/credit hour)");
	System.out.println("Fees: $52");
	
	if(gpa >= 3.85) {
		
		double originalPayment = getCreditHours() * 236.45 + 52.0;
		discountAmount = originalPayment * 0.25;
		totalPayment = originalPayment - discountAmount;
		System.out.println("Discount Applied: 25%");
		
	}else {
		totalPayment = getCreditHours() * 236.45 + 52.0;
	}

	
	System.out.println("Total payment (after discount): $" + decfor.format(totalPayment) + " \t\t($" + decfor.format(discountAmount) + " discount applied)");
	
	System.out.println("----------------------------------------------------------------------------------------------------\n\n\n");
	}
	
	
}
//____________________________________________________________________________________________________________

abstract class Employee extends Person {
	
	private String department;
	
	public Employee (String fullName, String id, String department) {
		super(fullName, id);
		this.department = department;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public abstract void print();
}

//_____________________________________________________________________________________________________

class Faculty extends Employee {
	
	private String rank;
	
	public Faculty (String fullName, String id, String department, String rank) {
		super(fullName, id, department);
		this.rank = rank;
	}

	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	
	@Override
	public void print() {
		
		System.out.println("\n\nFaculty Information: ");
		System.out.println("------------------------------------------------------");
		System.out.println(getFullName() + "\t\t" + getId());
		System.out.println(getDepartment() + " " + "Department,\t" + rank);
		System.out.println("------------------------------------------------------\n\n\n");
	}
	
}
//_____________________________________________________________________________________________

class Staff extends Employee {
	
	private String status;
	
	public Staff (String fullName, String id, String department, String status) {
		super(fullName, id, department);
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public void print() {
		
		System.out.println("\n\n-------------------------------------------------------------");
		System.out.println(getFullName() + "\t\t" + getId());
		System.out.println(getDepartment() + " " + "Department,\t" + status );
		System.out.println("-------------------------------------------------------------\n\n");
		
	}
	
} 
