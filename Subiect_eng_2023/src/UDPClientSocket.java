import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.print.attribute.standard.Media;

public class UDPClientSocket implements AutoCloseable {

    private DatagramSocket socket;

    public UDPClientSocket() throws SocketException {
        socket = new DatagramSocket();
    }

    public String sendAndReceiveMsg(String msg, String ipAddr, int port) throws UnknownHostException {

        String reply = null;
        try {
            byte[] data = msg.getBytes();
            InetAddress address = InetAddress.getByName(ipAddr);
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            socket.send(packet);

            byte[] buffer = new byte[256];
            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            socket.receive(response);
            reply = new String(response.getData(), 0, response.getLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    @Override
    public void close() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'close'");
    }

}
