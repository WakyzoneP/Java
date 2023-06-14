package eu.dice.net.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServerMultiThreadMain {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = null;
        boolean listening = true;

        try {
            int port = Integer.parseInt(args[0]);
            serverSocket = new ServerSocket(port);
            System.out.println("Web HTTP Server DICE is listening in port: " + port);
        } catch (IOException ioe){
            ioe.printStackTrace();
        }

        while(listening) {
            try {
                Socket objSocketRepr4Client = serverSocket.accept();
                HTTPServerMultiThread objThread4Client =  new HTTPServerMultiThread(objSocketRepr4Client);
                objThread4Client.start();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        try {
            if(serverSocket != null)
                serverSocket.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
