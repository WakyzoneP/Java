//a. Create public class Car which must implement Vehicle interface and it must be inserted 3 fields:
//weight - float, price - double, producer - String

//b. for this class fields, it is mandatory to implement getters and setters, plus the default constructor (without parameters),
//there is NO constructor with parameters

//c. override virtual implementation for the infoVehicle() method (from the Vehicle interface) to return the producer String

//d. the setters should throw Exception if the constraints are not fulfilled
//-producer different than null and producer String length greater than 1
//-price and weight greater than 0 each
//-otherwise throws Exception

//e. Implements Serializable, Cloneable and Override the implementation for equals(), hashCode() and clone() methods

import java.io.Serializable;

public class Car implements Vehicle, Serializable, Cloneable {

    private float weight;
    private double price;
    private String producer;
    private static final long serialVersionUID = 1L;

    public Car() {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(weight);
        long temp;
        temp = Double.doubleToLongBits(price);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((producer == null) ? 0 : producer.hashCode());
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
        Car other = (Car) obj;
        if (Float.floatToIntBits(weight) != Float.floatToIntBits(other.weight))
            return false;
        if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
            return false;
        if (producer == null) {
            if (other.producer != null)
                return false;
        } else if (!producer.equals(other.producer))
            return false;
        return true;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Car newCar = (Car)super.clone();
        newCar.price = price;
        newCar.weight = weight;
        newCar.producer = producer;
        return newCar;
    }

    @Override
    public String infoVehicle() {
        return producer;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) throws Exception {
        if (weight < 0)
            throw new Exception("Weight must be greater than 0");
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws Exception {
        if (price < 0)
            throw new Exception("Price must be greater than 0");
        this.price = price;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) throws Exception {
        if (producer == null || producer.length() < 1)
            throw new Exception("Producer can not be null and must be longer than 1");
        this.producer = producer;
    }

}
