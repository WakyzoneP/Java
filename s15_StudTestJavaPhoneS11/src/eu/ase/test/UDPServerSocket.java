package eu.ase.test;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;

//Subject+2 points <=> Mark 9/10:
// Create public class UDPServerSocket which implements a proprietary communication protocol and implements AutoCloseable (override specific method)
// It has 2 fields: socket - DatagramSocket and bindPort - int
// It contains the following constructor methods:
// a. public UDPServerSocket() throws SocketException - init bindPort on 60001
// b. public int getBindPort() - returns the bindPord field
// c. public void processRequest() throws IOException - receive UDP packets and process them (without infinite loop) with the following rules:
//		- if the request contains W? , then the reply UDP packet contains as pay-load "UDPS".
//		- if the request contains BYE , then the reply UDP packet contains as pay-load "BYE ACK" and close the resources (e.g. socket)
//		- if the request contains any other pay-load , then the reply UDP packet contains as pay-load "ACK".

public class UDPServerSocket implements AutoCloseable {

	private DatagramSocket socket;
	private int bindPort;

	public UDPServerSocket() throws SocketException {
		bindPort = 60001;
	}

	public int getBindPort() {
		return bindPort;
	}

	public void processRequest() throws IOException {

	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub

	}

}
