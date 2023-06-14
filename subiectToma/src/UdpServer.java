import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UdpServer {
    public static GymCard receiveCard() {
        GymCard card = null;
        try {
            DatagramSocket server = new DatagramSocket(7000);
            // while (true) 
            {
                byte[] request = new byte[1000];
                DatagramPacket reqPacket = new DatagramPacket(request, request.length);
                server.receive(reqPacket);

                String reqString = new String(reqPacket.getData());
                String[] items = reqString.split(",");
                String cardHolder = items[0];
                float loyaltyPoints = Float.parseFloat(items[1]);
                System.out.println(items[2]);
                SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");
                Date expiryData = parser.parse(items[2]);
                GymCard.CardType type = GymCard.CardType.bronze;
                card = new GymCard(cardHolder, loyaltyPoints, expiryData, type);
                
                // return card;
            }
            server.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return card;
    }

    public static void main(String[] args) {
        try {
            GymCard card = receiveCard();
            System.out.println(card.getCardHolder());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
