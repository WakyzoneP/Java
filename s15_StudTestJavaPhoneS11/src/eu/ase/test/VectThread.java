package eu.ase.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

// Mark 5:
//	a. create the class VectThread which implements Runnable and contains 2 fields:
//	- phonesList with interface as type List<ElectronicDevices>
//  - avgWeight is a real (double) number for storing the average weight of the phones list
//  b. In the constructor read the file using readBinaryPhones static method from Utils
//	c. provide get methods for the both fields (phoneList and avgweight) of the class  
//	d. Within the override run method (with the signature in Runnable interface) 
//	- the developer should go through the phoneList and calculate the average of the weights from the phone list objects (Phone class - explicit cast) 

public class VectThread implements Runnable {
	private List<ElectronicDevices> phonesList;
	private double avgWeight;

	public VectThread(String file) {
		// TODO Auto-generated constructor stub
		phonesList = Utils.readBinaryPhones(file);
	}

	public List<ElectronicDevices> getPhonesList() {
		return phonesList;
	}

	public double getAvgWeight() {
		return avgWeight;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		double suma = 0;
		for (int i = 0; i < this.phonesList.size(); i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			suma += ((Phone) phonesList.get(i)).getWeight();
		}
		this.avgWeight = suma / this.phonesList.size();
	}

}
