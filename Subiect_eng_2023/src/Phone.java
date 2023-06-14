import java.io.EOFException;
import java.io.Serializable;

public class Phone implements ElectronicDevices, Serializable, Cloneable {
    private float weight;
    private double diagonal;
    private String producer;
    
    public Phone() {}

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) throws Exception {

        if(weight < 0)
            throw new Exception("The weight must be positive");

        this.weight = weight;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(double diagonal) throws Exception {

        if(diagonal < 0)
            throw new Exception("The diagonal must be positive");

        this.diagonal = diagonal;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) throws Exception {

        if(producer == null)
            throw new Exception("The producer can not be null");
        
        if(producer.length() <= 1)
            throw new EOFException("The producer must have more than 1 character");

        this.producer = producer;
    }

    @Override
    public String infoDevice() {
        
        return producer;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Phone newPhone = (Phone)super.clone();
        newPhone.diagonal = diagonal;
        newPhone.weight = weight;
        newPhone.producer = new String(producer);
        return newPhone;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(obj == null)
            return false;
        if(obj.getClass() != getClass())
            return false;
        Phone other = (Phone)obj;
        if(other.diagonal != diagonal)
            return false;
        if(other.weight != weight)
            return false;
        if(producer == null)
        {
            if(other.producer != null)
                return false;
        }
        else if(producer.equals(other.producer) == false)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int primeNumber = 31;
        int result = 1;
        result = primeNumber * result + (int)Double.doubleToLongBits(diagonal);
        result = primeNumber * result + Float.floatToIntBits(weight);
        result = primeNumber * result + ((producer == null) ? 0 : producer.hashCode());
        return result;
    }

    
}
