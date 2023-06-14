package eu.dice.oop;

public class ProgMainOop {
	public static void main(String... args) {
		Car c1 = new Car(93, "Porsche Panamera");
		Car c2 = new Car(55, "Dacia Spring");
		
		System.out.println("c1 - registrationId = " + c1.getRegId() + ", name = " + c1.getName());
		System.out.printf("c2 - registrationId = %d, name = %s \n", c2.getRegId(), c2.getName());
		
		c2 = c1;
		c2.setRegId(100);
		
		System.out.println("c1 - registrationId = " + c1.getRegId() + ", name = " + c1.getName());
		System.out.printf("c2 - registrationId = %d, name = %s \n", c2.getRegId(), c2.getName());
	}
}
