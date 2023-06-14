import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.spi.InetAddressResolver;
import java.nio.charset.StandardCharsets;

import javax.swing.JOptionPane;

public class Client {
    public static void main(String[] args) throws IOException {
        DatagramSocket client = new DatagramSocket();

        String denumireSpecializare = JOptionPane.showInputDialog("Denumire Specializare");
        if (denumireSpecializare == null) {
            return;
        }
        byte[] messageBuffer = denumireSpecializare.getBytes();

        InetAddress address = InetAddress.getByName("127.0.0.1");
        int port = 1234;
        DatagramPacket packet = new DatagramPacket(messageBuffer, messageBuffer.length, address, port);
        client.send(packet);

        byte[] bufferRespunse = new byte[256];
        packet = new DatagramPacket(bufferRespunse, bufferRespunse.length, address, port);
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

        client.close();
    }
}