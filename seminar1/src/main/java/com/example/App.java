package com.example;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        
        Student.setNoStud(2);
		Student s0 = new Student("S0", new short[] {5, 5, 9});
		Student s1 = new Student("S1", new short[] {9, 10, 9});
//		
		// Student[] classStudents = new Student[Student.getNoStud()];
		// classStudents[0] = s0;
		// classStudents[1] = s1;

        // for (int i = 0; i < Student.getNoStud(); i++)
        //     System.out.println(classStudents[i].toString());

        // s0.setsName("S0.1");

        // for (int i = 0; i < Student.getNoStud(); i++)
        //     System.out.println(classStudents[i].toString());
		
		Student[] classStudents = new Student[Student.getNoStud()];
		// classStudents[0] = new Student("S0", new short[] {5, 5, 9});
		// classStudents[1] = new Student("S1", new short[] {9, 10, 9});
		
		for (int i = 0; i < Student.getNoStud(); i++) {
			if ( i == 0 ) {
				classStudents[i] = new Student("S0", new short[] {5, 5, 9});
			} else {
				classStudents[i] = new Student("S1", new short[] {9, 10, 9});
			}
		}
		
		for (int i = 0; i < Student.getNoStud(); i++) {
			System.out.println("The average mark for the student "+ i +" is = "+
					classStudents[i].getAverageMark());
		}

    }
}
