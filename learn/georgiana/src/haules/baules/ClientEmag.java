package haules.baules;

public class ClientEmag extends Client implements Comparable<ClientEmag> {
    private String address;

    public ClientEmag(String name, String email, String address, int numberOfOrders) throws Exception {
        super(name, email, numberOfOrders);
        this.address = address;
    }

    @Override
    public int compareTo(ClientEmag o) {
        return this.getNumberOfOrders() - o.getNumberOfOrders();
    }

    @Override
    public int nameLenght() {
        return this.getName().length();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    protected ClientEmag clone() throws CloneNotSupportedException {

        ClientEmag aux = (ClientEmag) super.clone();
        aux.address = this.address;
        return aux;
    }

    @Override
    public boolean equals(Object obj) {
        // create regex for email
        return super.equals(obj);
    }

}
