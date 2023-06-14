import java.io.*;
import java.net.*;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class TCPServerSocketMultiT {
    private ServerSocket serverSocket;
    private int port = 50001;
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
        if (newFName != null)
            f = new File(newFName);
        else
            f = null;
    }

    public void startTCPServer() throws IOException {

        while (true) {
            Socket socket = serverSocket.accept();

            new Thread(() -> {

                BufferedReader in = null;
                ObjectOutputStream out = null;

                try {
                    vt = new VectThread(f.getAbsolutePath());
                    vt.run();
                    List<ElectronicDevices> phoneList = vt.getPhoneList();

                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new ObjectOutputStream(socket.getOutputStream());

                    String line = in.readLine();
                    while(line != null)
                    {
                        if(line.equals("EXIT"))
                            break;
                        if(line.equals("GETFILE"))
                        {
                            out.writeObject(vt.getPhoneList());
                            out.flush();
                        }
                        if(line.equals("GETJSON"))
                        {
                            JSONArray jsonArray = new JSONArray();
                            for(var phone : phoneList)
                            {
                                JSONObject jsonObject = new JSONObject();
                                Phone p = (Phone)phone;
                                jsonObject.put("weight", p.getWeight());
                                jsonObject.put("diagonal", p.getDiagonal());
                                jsonObject.put("producer", p.getProducer());
                                jsonArray.put(jsonObject);
                            }
                            out.writeObject(jsonArray.toString());
                        }
                        if(line.equals("GETDB"))
                        {
                            UtilsDAO.setConnection();
                            out.writeObject(UtilsDAO.selectData());
                            UtilsDAO.closeConnection();
                        }

                        line = in.readLine();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
