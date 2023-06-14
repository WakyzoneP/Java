package eu.ase.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Subject of + 2 points <=> Mark 6 or 7 (and parts of the mark 8):
//	a. Create public class TCPServerSocketMultiT which handles multi-threading TCP server socket connections
//	for implementing a proprietary communication protocol (set of rules)
//	b. The class contains the following private fields:
//	- serverSocket as ServerSocket, port = 50001 as int, f as File and vt as VectThread ("has a" relationship)
//	c. The class contains the following methods and constructor:
//		c.1 - constructor which get the port as parameter and create the serverSocket:
//		public TCPServerSocketMultiT(int port) throws Exception
//		c.2 - getter and setter for the field port
//		c.3 - public void setFileName(String newFName) method which allocate memory for the field f, if and only if, 
//			the String parameter is different than null, otherwise is setting null
//		c.4.- public void startTCPServer() throws IOException method which is having the infinite processing loop and is implementing 3 commands from the proprietary protocol
//		HINTS for startTCPServer method:
//		-create multi-threading by using lambda expressions from Runnable functional interface after the blocking accept() method from serverSocket object
//		-get the input stream as BufferedReader and output stream as ObjectOutputStream
//		-initialize the vt field from class VectThread by passing the file absolute path from f field as parameters AND OBTAIN the list (ArrayList of Phone objects) from the file 
//		-parse line by line the TCP request
//			- if EXIT text command is received over the socket, then break the infinite loop of the processing and send TCP FIN packet back to the TCP client (e.g. by closing socket, etc.)
//			- (mark 6) if GETFILE text command is received over the socket, then reply back the serialized list encapsulated in the vt object field
//			- (mark 7) if GETJSON text command is received over the socket, then reply back with the list in JSON format
//			- (mark 8) if GETDB text command is received over the socket, then reply back with the list as String produced by UtilsDAO.selectData()
//(please also take into account, you have to initialize JDBC connection and close it with static methods from UtilsDAO);
//	YOU MAY NOT create the TCP client because it is already created into JUnit test framework; for mark 8, please also see UtilsDAO class (without UtilsDAO class, mark 8 can not be achieved)

public class TCPServerSocketMultiT {
	private ServerSocket serverSocket = null;
	private int port;
	private File f;
	private VectThread vt;

	public TCPServerSocketMultiT(int port) throws Exception {
		serverSocket = new ServerSocket(port);
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setFileName(String newFName) {
		if (newFName != null) {
			f = new File(newFName);
		} else {
			f = null;
		}
	}

	public void startTCPServer() throws IOException {

		Runnable ths = () -> {
			while (true) {
				Socket server;
				try {
					server = serverSocket.accept();
					Thread t = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							InputStream is;
							try {
								is = server.getInputStream();
								BufferedReader br = new BufferedReader(new InputStreamReader(is));
								String rec = br.readLine();
								vt = new VectThread(f.getPath());
								List<ElectronicDevices> ls = vt.getPhonesList();
								if (rec == "EXIT") {
									server.close();
								}
								if (rec == "GETFILE") {
									OutputStream os = server.getOutputStream();
									ObjectOutputStream oos = new ObjectOutputStream(os);

									oos.writeObject(ls);
									oos.close();
								}

								br.close();
								if (rec == "GETJSON") {
									JSONObject jlist = new JSONObject();
									for (int i = 0; i < ls.size(); i++) {
										jlist.put("Device", new JSONArray());
										JSONObject elem1 = new JSONObject();
										JSONObject elem2 = new JSONObject();
										JSONObject elem3 = new JSONObject();

										Phone aux = (Phone) ls.get(i);
										elem1.put("weight", aux.getWeight());
										elem2.put("diagonal", aux.getDiagonal());
										elem3.put("producer", aux.getProducer());

										jlist.append("Device", elem1);
										jlist.append("Device", elem2);
										jlist.append("Device", elem3);

										OutputStream os = server.getOutputStream();
										ObjectOutputStream oos = new ObjectOutputStream(os);

										oos.writeObject(jlist);
										oos.close();
									}
								}
								server.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					});
					t.start();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		};

	}
}
