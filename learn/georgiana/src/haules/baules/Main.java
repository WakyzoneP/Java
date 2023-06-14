package haules.baules;

import java.io.*;
import java.util.*;
import java.util.function.IntFunction;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println();

        ClientEmag[] clientsObj = new ClientEmag[3];
        List<ClientEmag> clientList = new ArrayList<ClientEmag>();

        File file = new File("data/date.txt");
        Scanner scanner = new Scanner(file);

        int idx = 0;
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] values = line.split(",");
            String name = values[0];
            String email = values[1];
            String address = values[2];
            int noOfOrders = Integer.parseInt(values[3]);
            ClientEmag client = new ClientEmag(name, email, address, noOfOrders);
            clientsObj[idx++] = client;
            clientList.add(client);
        }

        Collections.sort(clientList);

        for(var client : clientList)
            System.out.println(client.toString());

        ClientEmag[][] clienti = { { null, clientsObj[0], null }, { clientsObj[1], null, clientsObj[2] } };
        Utils utils = new Utils();
        // utils.setClients(clienti);
        // utils.printMatrix();

        

        // try {
        // ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream( new
        // FileOutputStream("data/serializare.txt")));

        // // for(var emelemt : clienti)
        // // for(var client : emelemt)
        // // out.writeObject(client);
        // out.writeObject(clienti);

        // out.flush();
        // out.close();
        // } catch(Exception e) {
        // e.printStackTrace();
        // }

        // ClientEmag[][] clients2;

        // try {
        // ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new
        // FileInputStream("data/serializare.txt")));
        // clients2 = (ClientEmag[][]) in.readObject();
        // utils.setClients(clients2);
        // utils.printMatrix();
        // in.close();
        // } catch(Exception e) {
        // e.printStackTrace();
        // }

    }
}
