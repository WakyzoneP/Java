package eu.ase.test;

import java.io.Serializable;

//Mark 3:
//a. Create public class Phone which must implement ElectronicDevice interface and It must be inserted 3 fields:
//weight - float, diagonal - double, producer - String
//b. for this class fields it is mandatory to implement getters and setters, plus the default constructor (without parameters), 
//there is NO constructor with parameters
//c. override implementation for the infoDevice() method (from the ElectronicDevices interface) to return the producer String
//d. the setters should throw Exception if the constraints are not fulfilled:
//- producer different than null and producer String length greater than 1
//- diagonal and weight greater than 0 each 
//e. Implements Serializable, Cloneable and Override the implementation for equals(), hashCode() and clone() methods

public class Phone implements ElectronicDevices, Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float weight;
	private double diagonal;
	private String producer;

	public Phone() {
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) throws Exception {
		if (weight < 0)
			throw new Exception();
		this.weight = weight;
	}

	public double getDiagonal() {
		return diagonal;
	}

	public void setDiagonal(double diagonal) throws Exception {
		if (diagonal <= 0)
			throw new Exception();
		this.diagonal = diagonal;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) throws Exception {
		if (producer == null || producer.length() <= 1) {
			throw new Exception();
		}
		this.producer = producer;
	}

	@Override
	public String infoDevice() {
		return this.producer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(diagonal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((producer == null) ? 0 : producer.hashCode());
		result = prime * result + Float.floatToIntBits(weight);
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
		Phone other = (Phone) obj;
		if (Double.doubleToLongBits(diagonal) != Double.doubleToLongBits(other.diagonal))
			return false;
		if (producer == null) {
			if (other.producer != null)
				return false;
		} else if (!producer.equals(other.producer))
			return false;
		if (Float.floatToIntBits(weight) != Float.floatToIntBits(other.weight))
			return false;
		return true;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Phone p = (Phone) super.clone();
		try {
			p.setDiagonal(this.diagonal);
			p.setProducer(this.producer);
			p.setWeight(this.weight);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return p;
	}

}
