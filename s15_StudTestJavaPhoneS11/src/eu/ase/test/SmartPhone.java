package eu.ase.test;

//Mark 3:
// a. create public class SmartPhone must inherit the Phone class and it adds the batteryDuration - int field
// b. for this class fields it is mandatory to implement getters and setters
// c. override virtual implementation for the infoDevice() method (from the ElectronicDevices interface and Phone implementation),
//    to return the batteryDuration as a String
// d. the setters should throw Exception if the constraints are not fulfilled:
// - batteryDuration greater than 0

public class SmartPhone extends Phone {
	private int batteryDuration;

	public int getBatteryDuration() {
		return batteryDuration;
	}

	public void setBatteryDuration(int batteryDuration) throws Exception {
		if(batteryDuration<=0)
			throw new Exception();
		this.batteryDuration = batteryDuration;
	}

	@Override
	public String infoDevice() {
		// TODO Auto-generated method stub
		return Integer.toString(this.batteryDuration);
	}

}
