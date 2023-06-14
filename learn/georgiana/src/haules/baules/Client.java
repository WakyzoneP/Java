package haules.baules;

import java.io.*;

public abstract class Client implements Serializable, Cloneable, AutoCloseable {
    private String name;
    private String email;
    private int numberOfOrders;
    
    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    private static long noOfClients = 0;
    
    private static final long serialVersionUID = 103044033L;

    public Client(String name, String email, int numberOfOrders) throws Exception {
        this.name = name;
        if(email.length() < 7) throw new Exception("Email too short");
        else this.email = email;
        this.numberOfOrders = numberOfOrders;
        noOfClients++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static long getNoOfClients() {
        return noOfClients;
    }

    public static void setNoOfClients(long noOfClients) {
        Client.noOfClients = noOfClients;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Client aux = (Client) super.clone();
        aux.email = this.email;
        aux.name = this.name;

        return aux;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(!(obj instanceof Client)) return false;
        Client aux = (Client) obj;
        return this.email.equals(aux.email) && this.name.equals(aux.name);
    }

    @Override
    public void close() throws Exception {
        noOfClients--;
    }

    @Override
    public String toString() {
        return "Client [email=" + email + ", name=" + name + "]";
    }

    public abstract int nameLenght();
}
