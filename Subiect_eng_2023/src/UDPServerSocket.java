import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServerSocket implements AutoCloseable {
    private DatagramSocket socket;
    private int bindPort;

    public UDPServerSocket() throws SocketException
    {
        bindPort = 60001;
        socket = new DatagramSocket(bindPort);
    }

    public int getBindPort() {
        return bindPort;
    }

    public void processRequest() throws IOException
    {
        byte[] buffer = new byte[256];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        String result = new String(packet.getData(), 0, packet.getLength());
        String payload = null;
        if(result.equals("W?"))
        {
            payload = "UDPS";
        }
        if(result.equals("BYE"))
        {
            payload = "BYE ACK";
            close();
        }
        if(result.equals("W?"))
        {
            payload = "ACK";
        }

        byte[] replyData = payload.getBytes();

        DatagramPacket reply = new DatagramPacket(replyData, replyData.length, packet.getAddress(), packet.getPort());
        socket.send(reply);
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }
}
