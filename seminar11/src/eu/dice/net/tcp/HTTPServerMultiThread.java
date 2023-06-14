package eu.dice.net.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class HTTPServerMultiThread extends Thread {
    private Socket socket;
    public HTTPServerMultiThread(Socket objSocketRepr4Client) {
        this.socket = objSocketRepr4Client;
    }

    @Override
    public void run() {
        OutputStream os = null; 
        PrintWriter out = null;
        InputStream is = null;
        BufferedReader in = null;
        try {
            is = this.socket.getInputStream();
            in = new BufferedReader(new InputStreamReader(is));

            os = this.socket.getOutputStream();
            out = new PrintWriter(os, true);

            String inputLine = "";
            String outputLine = "";
            StringBuffer procesLineBuffer = new StringBuffer();

            while ((inputLine = in.readLine() ) != null && (inputLine.length()) > 1) {
                procesLineBuffer.append(inputLine);
            }

            // System.out.println(procesLineBuffer.toString());
            HTTPSeminarProtocol objParserIO = new HTTPSeminarProtocol();
            outputLine = objParserIO.processInput(procesLineBuffer.toString());

            out.println(outputLine);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (out != null)
                out.close();
        }
    }
}
