package eu.ase.test;

import java.util.Objects;

import javax.management.relation.RoleResult;

/*
 * Nota 4: Creati clasa PrimeCustomer ce este derivata din Customer si implementeaza interfetele Cloneable si Comparable<PrimeCustomer>.
 * 
 * Pe langa campurile si metodele mostenite din clasa abstracta Customer, clasa PrimeCustomer are urmatoarele campuri private aditional:
 * discount : int pentru reducerea aplicata
 * serialVersionUID : static final long
 * 
 * Implementati metoda constructor cu 5 parameteri: 
 * "customerId" de tip int, "name" de tip String, "transactions" de tip int, "totalSpent" de tip float, "discount" de tip int
 * Constructorul arunca o exceptie din clasa Exception daca discount sau totalSpent sunt mai mici decat 0 si daca discount este mai mare decat 100.
 * 
 * Implementati corespunsator metodele get/set si supra-screti metoda equals:
 * metoda publica equals(Object o) ce returneaza boolean
 * Metoda setDiscount produce o exceptie din clasa Exception daca reducerea este mai mica decat 0 ori mai mare decat 100.
 * 
 * Implementati consistent metoda clone pentru a creeea obiecte clona de tip "deep copy" din obiectul curent:
 * public clone() -> return Object si "throw CloneNotSupportedException"
 * 
 * Supra-scrieti metoda compareTo din interfata Comparable (compareTo() -> return int ce compara clientii in functie de discount)
 * 
 * Supra-scrieti si implementati metoda getAbstractCustomerId() ce returneaza un obiect de tip String 
 * ce reprezinta urmatoarea concatenare: "Prime customer " + (valoarea mostenita pentru customerId) + " has " + (valoarea discount-ului) + "% discount"
 */

public class PrimeCustomer extends Customer implements Cloneable, Comparable<PrimeCustomer> {

    private int discount;
    private static final long serialVersionUID = 124353423L;

    public PrimeCustomer(int customerId, String name, int nrTransactions, float totalSpent, int discount)
            throws Exception {
        super(customerId, name, nrTransactions, totalSpent);
        if (0 > discount || discount > 100)
            throw new Exception("Discound must be between 0 and 100");
        this.discount = discount;
    }

    @Override
    public int compareTo(PrimeCustomer o) {
        return discount - o.discount;
    }

    @Override
    public String getAbstractCustomerId() {
        return "Prime customer " + Integer.toString(getCustomerId()) + " has " + Integer.toString(discount) + "% discount";
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) throws Exception {
        if (0 > discount || discount > 100)
            throw new Exception("Discound must be between 0 and 100");
        this.discount = discount;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        PrimeCustomer other = (PrimeCustomer)obj;
        if(getCustomerId() != other.getCustomerId())
            return false;
        if(getName() == null)
        {
            if(other.getName() != null)
                return false;
        }
        else if(getName().equals(other.getName()) == false)
            return false;
        if(getNrTransactions() != other.getNrTransactions())
            return false;
        if(getTotalSpent() != other.getTotalSpent())
            return false;
        if(discount != other.discount)
            return false;
        return true;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        PrimeCustomer newPrimeCustomer = (PrimeCustomer)super.clone();
        newPrimeCustomer.discount = discount;
        return newPrimeCustomer;
    }

}
