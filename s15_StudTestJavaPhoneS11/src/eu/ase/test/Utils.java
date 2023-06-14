package eu.ase.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

//Mark 4:
// Create public class Utils which contains private static list field with interface as type: List<ElectronicDevices>
// Insert the following methods:
// a. public static List<ElectronicDevices> createPhones(int n) throws Exception - for creating an ArrayList of n elemnts
//    which are containing n default Phone objects and it using the static field of the class (list)
// b. public static List<ElectronicDevices> readPhones(String file) 
//    - for reading and parsing text files with string lines for creating Phone objects 
// (e.g. please see for example phonesList.txt file); first line is the weight in grams, second line is screen diagonal and third line is the producer
//   hint: use RandomAccessFile and read / parse line by line (first is parsing for float - weight, second line is double - diagonal, third is String - producer)
// c. public static void writeBinaryPhones(String file, List<ElectronicDevices> listP) - for writing binary the phones into the file
//   hint: use FileOutputStream with FileOurputStream to serialized/save the Phone objects from the ArrayList of the phones objects
// d. public static List<ElectronicDevices> readBinaryPhones(String file) - for reading binary the Phone objects from the file and creating the ArrayList 

public class Utils {
	private static List<ElectronicDevices> list;

	public static List<ElectronicDevices> createPhones(int n) throws Exception {
		if (n <= 0)
			throw new Exception();
		list = new ArrayList<ElectronicDevices>();
		for (int i = 0; i < n; i++) {
			list.add(new Phone());
		}
		return list;
	}

	public static List<ElectronicDevices> readPhones(String file) {
		List<ElectronicDevices> lista = null;
		File f = new File(file);
		if (f.exists()) {
			lista=new ArrayList<ElectronicDevices>();
			try {
				FileReader fr = new FileReader(f);
				BufferedReader reader = new BufferedReader(fr);
				for (int i = 0; i < 4; i++) {
					float weight = Float.parseFloat(reader.readLine());
					double diagonal = Double.parseDouble(reader.readLine());
					String producer = reader.readLine();
					System.out.println(weight + " - " + diagonal + " " + producer);
					Phone p = new Phone();
					p.setDiagonal(diagonal);
					p.setProducer(producer);
					p.setWeight(weight);
					lista.add(p);
				}
				reader.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lista;
	}

	public static void writeBinaryPhones(String file, List<ElectronicDevices> listP) {
		File f = new File(file);
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(f);
			DataOutputStream dos = new DataOutputStream(fos);
			dos.writeInt(listP.size());
			for (int i = 0; i < listP.size(); i++) {
				dos.writeFloat(((Phone) listP.get(i)).getWeight());
				dos.writeDouble(((Phone) listP.get(i)).getDiagonal());
				dos.writeUTF(((Phone) listP.get(i)).getProducer());
			}
			dos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<ElectronicDevices> readBinaryPhones(String file) {
		List<ElectronicDevices> lista = new ArrayList<ElectronicDevices>();
		File f = new File(file);
		if (f.exists()) {
			FileInputStream fis;
			try {
				fis = new FileInputStream(f);
				DataInputStream dis = new DataInputStream(fis);
				int size = dis.readInt();
				for (int i = 0; i < size; i++) {
					Phone p = new Phone();
					float weight = dis.readFloat();
					double diagonal = dis.readDouble();
					String producer = dis.readUTF();
					p.setDiagonal(diagonal);
					p.setProducer(producer);
					p.setWeight(weight);
					lista.add(p);
				}
				dis.close();
				fis.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lista;
	}
}
