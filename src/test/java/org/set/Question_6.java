package org.set;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Question_6 {

	public static void main(String[] args) {
	Set<Object> s=new LinkedHashSet<Object>();
	
	@SuppressWarnings("resource")
	Scanner sc =new Scanner(System.in);
	
//	empId,name,phone,address,dob,doj,eMail,gender,Sal
	
	System.out.println("Enter Employee ID.....");
	String empID = sc.nextLine();
	
	System.out.println("Enter Name.....");
	String name = sc.nextLine();
	
	System.out.println("Enter Address.....");
	String address = sc.nextLine();
	
	System.out.println("Enter DOB.....");
	String dob = sc.nextLine();
	
	System.out.println("Enter Date of Joining.....");
	String doj = sc.nextLine();
	
	System.out.println("Enter Email.....");
	String email = sc.nextLine();
	
	System.out.println("Enter Gender.....");
	String gender = sc.nextLine();
	
	System.out.println("Enter Phone No.....");
	long phno = sc.nextLong();
	
	System.out.println("Enter Salary.....");
	float salary = sc.nextFloat();
	
	
	s.add(empID);
	s.add(name);
	s.add(phno);
	s.add(address);
	s.add(dob);
	s.add(doj);
	s.add(email);
	s.add(gender);
	s.add(salary);
	
	System.out.println(s);
	
	
	
	
	}
}
