import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UdpClient {

    public static void sendCard(GymCard card) {
        try {
            DatagramSocket client = new DatagramSocket();

            StringBuilder sb = new StringBuilder();
            sb.append(card.getCardHolder());
            sb.append(",");
            sb.append(Float.toString(card.getLoyaltyPoints()));
            sb.append(",");
            Format formatter = new SimpleDateFormat("dd-MM-yyyy");
            sb.append(formatter.format(card.getExpiryDate()));
            sb.append(",");
            sb.append(card.typeToString());
            String reqString = sb.toString();

            byte[] request = reqString.getBytes();

            InetAddress address = InetAddress.getByName("127.0.0.1");
            int port = 7000;

            DatagramPacket reqPacket = new DatagramPacket(request, request.length, address, port);
            client.send(reqPacket);

            // byte[] resounse = new byte[1000];
            // DatagramPacket resPacket = new DatagramPacket(resounse, resounse.length);
            // client.receive(resPacket);

            // String resString = new String(resPacket.getData());
            // System.out.println(resString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            GymCard card = new GymCard("John", 100, new Date(2020, 11, 31), GymCard.CardType.bronze);
            sendCard(card);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
