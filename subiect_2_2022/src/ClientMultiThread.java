import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

import javax.swing.JOptionPane;

public class ClientMultiThread {

    public static void main(String[] args) throws IOException {
        MulticastSocket client = new MulticastSocket(4447);

        InetAddress address = InetAddress.getByName("230.0.0.1");
        client.joinGroup(address);

        for (int i = 0; i < 5; ++i) {
            String denumireSpecializare = JOptionPane.showInputDialog("Denumire Specializare");
            if (denumireSpecializare == null) {
                return;
            }
            byte[] messageBuffer = denumireSpecializare.getBytes();

            DatagramPacket packet = new DatagramPacket(messageBuffer, messageBuffer.length);
            client.send(packet);

            byte[] bufferRespunse = new byte[256];
            packet = new DatagramPacket(bufferRespunse, bufferRespunse.length);
            client.receive(packet);

            int lenght = 0;
            while (('a' <= bufferRespunse[lenght] && bufferRespunse[lenght] <= 'z')
                    || ('A' <= bufferRespunse[lenght] && bufferRespunse[lenght] <= 'Z'))
                lenght += 1;
            String nan = new String(bufferRespunse, 0, lenght, StandardCharsets.UTF_8);

            String receivedString = new String(packet.getData());
            if (nan.equals("Nan") == true)
                System.out.println("Nu exista aceasta specializare");
            else
                System.out.println("Mai sunt " + receivedString + " locuri disponibile");
        }
        client.leaveGroup(address);
        client.close();
    }
}
