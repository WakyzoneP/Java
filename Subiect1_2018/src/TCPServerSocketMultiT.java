//Subject of +2 points ⇔ Mark 6 or 7(and parts of mark 8):
//a. Create public class TCPServerSocketMultiT which handles multi-threading TCP server socket connections for implementing a proprietary 
//communication protocol (set of rules)
//b.The class contains the following private fields:
//-serversocket as ServerSocket,port =50001 as int,f as File and vt as VectorThread(“has a” relationship)
//c.The class contains the folllowing methods and constructor:
//c1. constructor which get the port as parameter and create the serverSocket:
//public TCPServerSocketMultiT(int port) throws Exception
//c2. getter and setter for the field port
//c3. public void SetFileName(String fileName) method which allocate memory for the field f, if and only if, the string parameter is different 
//than null, otherwise is setting null
//c4. public void startTCPServer() throws IOException method which is having the infinite processing loop and is immplementing 3 commands from 
//the propietary pro…???

// Hints for startTCPServer method:
// -  create multi-threading by using lambda expressions from Runnable functional interface after the blocking accept() method from serverSocket 
// object.
// - get the input stream as BufferedReader and output stream as ObjectOutputStream
// - initialize the vt field from class VectThread by passing the file absolute path from f field as parameters AND OBTAIN the list 
// (ArrayList of Car objects) from the … ???
// - parse line by line the TCP request
// - if EXIT text command is recived over the socket,then break the infinite loop of the processing and send TCP FIN packet back to the TCP 
// client (e.g. by ???)
// - (Mark 6) if GETFILE text command is recived over the socket,then reply back the serialized list encapsulated in the vt object field.
// - (Mark 7)  if GETJSON text command is recived over the socket, then reply back with the list in JSON format.
// - (Mark 8)  if GETDB text command is recived over the socket, then reply back with the list as String produced by  ??? UtilsDAO.selectdata() 
// /UtilsDAD.selectdata() (please also take into  ???)
// YOU MAY NOT create the TCP client because it is already created into JUnit test framework; for mark 8, please also see UtilsDAD / UtilsDAO 
// ??? class (without UtilsDAD/UtilsDAO ??? class, mark 8...)

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class TCPServerSocketMultiT {
    private ServerSocket serversocket;
    private int port = 50001;
    private File f;
    private VectorThread vt;

    public TCPServerSocketMultiT(int port) throws Exception {
        this.port = port;
        serversocket = new ServerSocket(port);
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void SetFileName(String fileName) {
        if (fileName != null)
            f = new File(fileName);
        else
            f = null;
    }

    public void startTCPServer() throws IOException {
        while (true) {
            Socket socket = serversocket.accept();

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        ObjectOutputStream out = new ObjectOutputStream(
                                new BufferedOutputStream(socket.getOutputStream()));
                        vt = new VectorThread(f.getAbsolutePath());
                        vt.run();
                        List<Vehicle> carList = vt.getCarList();
                        String line = in.readLine();
                        while (line != null) {
                            if (line.equals("EXIT")) {
                                out.writeObject("TCP FIN");
                                break;
                            }
                            if (line.equals("GETFILE")) {
                                out.writeObject(carList);
                            }
                            if (line.equals("GETJSON")) {
                                JSONArray jsonArray = new JSONArray();
                                for(var vehicle : carList)
                                {
                                    Car c = (Car)vehicle;
                                    JSONObject jsonObject = new JSONObject();
                                    jsonObject.put("weight", c.getWeight());
                                    jsonObject.put("price", c.getPrice());
                                    jsonObject.put("producer", c.getProducer());

                                    jsonArray.put(jsonObject);
                                }

                                out.writeObject(jsonArray);
                            }
                            if(line.equals("GETDB"))
                            {
                                UtilsDAO.setConnection();
                                try {
                                    out.writeObject(UtilsDAO.selectData());
                                } catch (Exception e)
                                {
                                    e.printStackTrace();
                                }
                                UtilsDAO.closeConnection();
                            }
                            line = in.readLine();
                        }
                    } catch (EOFException eof) {
                    } catch (Exception e) {
                        e.printStackTrace();
                        ;
                    }
                }
            });

            thread.start();
            socket.close();
        }
    }
}
