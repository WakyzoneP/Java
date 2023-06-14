import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ServerMultiThread {
    public static void main(String[] args) {

        try {
            DatagramSocket server = new DatagramSocket(1234);

            while (true) {
                byte[] bufferReceived = new byte[256];

                DatagramPacket packet = new DatagramPacket(bufferReceived, bufferReceived.length);
                server.receive(packet);
                int lenght = 0;
                while (('a' <= bufferReceived[lenght] && bufferReceived[lenght] <= 'z')
                        || ('A' <= bufferReceived[lenght] && bufferReceived[lenght] <= 'Z'))
                    lenght += 1;
                String denumire = new String(bufferReceived, 0, lenght, StandardCharsets.UTF_8);

                System.out.println("Denumirea trimisa de client: " + denumire);

                List<Specializare> listaSpecializari = Specializare.citireBazaDate();
                List<Candidat> listaCandidati = Candidat.citireFisier("data/candidati.txt");

                byte[] bufferRespunse = null;

                try {
                    int cod = listaSpecializari.stream()
                            .filter(specializare -> specializare.getDenumire().equals(denumire))
                            .findFirst()
                            .get()
                            .getCod();

                    long locuriOcupate = listaCandidati.stream()
                            .filter(candidat -> candidat.getCod_specialitate_aleasa() == cod)
                            .count();

                    bufferRespunse = Long.toString(locuriOcupate).getBytes();
                } catch (Exception e) {
                    bufferRespunse = new String("Nan").getBytes();
                }

                InetAddress adress = packet.getAddress();
                int port = packet.getPort();
                DatagramPacket packetResponse = new DatagramPacket(bufferRespunse, bufferRespunse.length, adress, port);
                server.send(packetResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}