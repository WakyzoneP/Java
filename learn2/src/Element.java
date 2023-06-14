public class Element {
    private int linie;
    private int coloana;
    private double element;

    public Element(int linie, int coloana, double element){
        this.linie = linie;
        this.coloana = coloana;
        this.element = element;
    }

    public int getLinie() {
        return linie;
    }

    public void setLinie(int linie) {
        this.linie = linie;
    }

    public int getColoana() {
        return coloana;
    }

    public void setColoana(int coloana) {
        this.coloana = coloana;
    }

    public double getElement() {
        return element;
    }

    public void setElement(double element) {
        this.element = element;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + linie;
        result = prime * result + coloana;
        long temp;
        temp = Double.doubleToLongBits(element);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Element other = (Element) obj;
        if (linie != other.linie)
            return false;
        if (coloana != other.coloana)
            return false;
        if (Double.doubleToLongBits(element) != Double.doubleToLongBits(other.element))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Element [linie=" + linie + ", coloana=" + coloana + ", element=" + element + "]";
    }
    
}
