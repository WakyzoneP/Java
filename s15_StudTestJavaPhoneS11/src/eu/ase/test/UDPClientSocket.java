package eu.ase.test;

//Subject+2 points <=> Mark 9/10:
//Create public class UDPClientSocket which implements a proprietary communication protocol and implements AutoCloseable (override specific method)
//It has 2 fields: socket - DatagramSocket
//It contains the following constructor methods:
//a. public UDPClientSocket() throws SocketException - init socket WITHOUT bind port
//b. public String sendAndReceiveMsg(String msg, String ipAddr, int port) throws UnknownHostException 
//   - send UDP packets and process them (without infinite loop) with the following rules:
//		- when the sent request contains W? , then the response UDP packet from server contains as pay-load "UDPS".
//		- when the sent request contains BYE , then the response UDP packet from server contains as pay-load "BYE ACK" 
//		- when the sent request contains any other pay-load , then the response UDP packet contains as pay-load "ACK".

public class UDPClientSocket {
	
}


