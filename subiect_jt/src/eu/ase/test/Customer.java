package eu.ase.test;

import java.io.Serializable;

/*
 * Nota 3: 
 * implementati clasa abstracta Customer ce implementeaza interfetele: Cloneable, AutoCloseable si java.io.Serializable .
 * 
 * Clasa are urmatoarele campuri private:
 * customerId : int ce contine un identificator unic
 * name : String ce contine numele clientului
 * nrTransactions : int ce contine numarul de tranzactii efectuate
 * totalSpent : float ce reprezinta totalul cheltuielilor
 * serialVersionUID : static final long
 * 
 * Trebuie implementata metoda constructor fara parameteri si contructorul cu 4 parameteri: 
 * "customerId" de tip int, "name" de tip String, "nrTransactions" de tip int, "totalSpent" de tip float
 * Constructorul cu parametri trebuie sa dea o exceptie din Exception daca totalul cheltuielilor (totalSpent) este mai mic decat 0 
 * 
 * Clasa contine campul className : String ce este privat, static si final cu valoarea: "eu.ase.test.Customer" 
 * si metoda de tip get asociata - publica si statica (getClassName)
 * 
 * Clasa mai contine un camp private si static "customersNo" (numar total de clienti / obiecte create din aceasta clasa)
 * si metoda publica si statica de tip get asociata (getCustomersNo -> returns int). 
 * Campul (customersNo) este utilizat pentru a contoriza numarul de obiecte create din aceasta clasa. 
 * Acest camp este incrementat in constructori si metoda clone si decrementat in metoda close.
 * 
 * Implementati metodele get/set in mod crespunzator pentru fiecare camp
 * 
 * Implementati consistent metoda clone pentru a creeea obiecte clona de tip "deep copy" din obiectul curent:
 * public clone() -> return Object si "throw CloneNotSupportedException"
 * 
 * Implementati interfata AutoCloseable (close() -> return void)
 * 
 * Declarati metoda publica si abstracta: getAbstractCustomerId ce nu are parametri si returneaza -> String.
 */

public abstract class Customer implements Cloneable, AutoCloseable, Serializable {
	private int customerId;
	private String name;
	private int nrTransactions;
	private float totalSpent;
	private static final long serialVersionUID = 1L;
	private static final String className = "eu.ase.test.Customer";
	private static int customersNo = 0;
	
	public Customer() {
	}

	public Customer(int customerId, String name, int nrTransactions, float totalSpent) throws Exception {
		if(totalSpent < 0)
			throw new Exception("totalSpent must be positive");
		this.customerId = customerId;
		this.name = name;
		this.nrTransactions = nrTransactions;
		this.totalSpent = totalSpent;
		customersNo++;
	}

	public String getClassName()
	{
		return className;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNrTransactions() {
		return nrTransactions;
	}

	public void setNrTransactions(int nrTransactions) {
		this.nrTransactions = nrTransactions;
	}

	public float getTotalSpent() {
		return totalSpent;
	}

	public void setTotalSpent(float totalSpent) {
		this.totalSpent = totalSpent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static int getCustomersNo() {
		return customersNo;
	}

	public static void setCustomersNo(int customersNo) {
		Customer.customersNo = customersNo;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Customer newCustomer = (Customer)super.clone();
		newCustomer.customerId = customerId;
		newCustomer.name = name;
		newCustomer.nrTransactions = nrTransactions;
		newCustomer.totalSpent = totalSpent;
		customersNo++;
		return newCustomer;
	}

	@Override
	public void close() throws Exception {
		customersNo--;
	}

	public abstract String getAbstractCustomerId();
}
