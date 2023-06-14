package eu.ase.test;

import java.util.ArrayList;
import java.util.List;

// This is the Java code playground
// Loc pentru testare obiecte si clase utilizand cod sursa Java

public class ProgMain {

	public static void main(String[] args) {
		PrimeCustomer cc1, cc2, cc3, cc4;
		try {
			cc1 = new PrimeCustomer(1, "User1", 25, 6987, 20);
			cc2 = new PrimeCustomer(2, "User2", 13, 2315, 10);
			cc3 = new PrimeCustomer(3, "User2", 13, 2315, 10);
			cc4 = new PrimeCustomer(4, "User2", 2, 2315, 10);
			if (cc1.equals(cc2))
				System.out.println("incorrect true");
			else
				System.out.println("correct false");

			if (cc2.equals(cc3))
				System.out.println("correct true");
			else
				System.out.println("incorrect false");

			System.out.println(cc1.getAbstractCustomerId());
			System.out.println(cc2.getAbstractCustomerId());

			Customer[][] objs = new Customer[][] { { cc1, cc2 }, { null, cc3 } };
			Matrix m = new Matrix(2, 2, (Object[][]) objs);
			Matrix m2 = (Matrix) m.clone();
			Customer[][] objs2 = new Customer[][] { { cc1, cc2 }, { cc3, cc4 } };
			m.setMatrix(objs2);
			System.out.println("Not NULL = " + m2.getNotNullsNumber());

			List<Customer> list = m.transformMatrix2VectorAndLambdaSortCustomers();
			Matrix.writeBinary("customers.bin", list);

			List<Customer> list2 = Matrix.readBinary("customers.bin");
			for (var element : list2) {
				System.out.println(((PrimeCustomer)element).getAbstractCustomerId());
			}

			float sumaS = m.calculateEarnings();

			System.out.println(sumaS);

			float sume = m.calculateEarningsMultiThreading();

			System.out.println(sume);

			// ...
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
