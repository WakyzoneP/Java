package haules.baules;

import java.util.*;

public class Utils {
    private ClientEmag[][] clients;
    private List<ClientEmag> list;
    private Map<String, ClientEmag> map;

    public Utils() {}
    

    public ClientEmag[][] getClients() {
        return clients;
    }

    public void setClients(Object[][] clients) throws Exception {
        this.clients = new ClientEmag[clients.length][clients[0].length];
        for(int i = 0; i < clients.length; i++)
            for(int j = 0; j < clients[0].length; j++){
                if(clients[i][j] != null)
                    this.clients[i][j] = ((ClientEmag)clients[i][j]).clone();
            }
    }

    public void printMatrix()
    {
        for(int i = 0; i < clients.length; i++)
        {
            for(int j = 0; j < clients[0].length; j++)
            {
                System.out.print(clients[i][j] + " ");
            }
            System.out.println();
        }
    }


}
