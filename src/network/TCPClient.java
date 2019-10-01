package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

//http://www.java2s.com/Tutorials/Java/Java_Network/0020__Java_Network_TCP_Client_Socket.html

public class TCPClient {
    
    // Default port number to connect TCPServer to
    public static final int DEFAULT_PORT_NUM = 12900;
    
    // Port number to connect TCPServer to
    private int portNum = DEFAULT_PORT_NUM;
    
    // Keeps track of single instance of TCPServer
    private static TCPClient singleton;
    
    // Declare socket, buffered reader, and buffered writer for TCPClient
    private Socket socket;
    private BufferedReader socketReader;
    private BufferedWriter socketWriter;
    
    // Constructor does nothgin
    private TCPClient() {};
    
    // Get instance of TCP Server
    public static TCPClient getInstance() throws IOException {
        if (singleton == null) {
            init();
        }
        return singleton;
    }

    // How to initialize TCPServer
    private synchronized static void init() throws IOException {
        
        if (singleton == null) {
            
            // Initializes new TCPClient class
            singleton = new TCPClient();
            
            // Initialize socket, buffered reader, and buffered writer for client
            singleton.socket = new Socket("DESKTOP-2S3T6VV", singleton.portNum);
            System.out.println("Started client  socket at "
                    + singleton.socket.getLocalSocketAddress());
            singleton.socketReader = new BufferedReader(new InputStreamReader(
                    singleton.socket.getInputStream()));
            singleton.socketWriter = new BufferedWriter(new OutputStreamWriter(
                    singleton.socket.getOutputStream()));
        }
        
    }
    
    /**
     * Sets the port number of the TCPServer if different from default
     * @param portNum
     *          The number of the port
     */
    public void setPort(int portNum) {
        singleton.portNum = portNum;
    }

    public void printMsg() throws IOException {
        String msg = singleton.socketReader.readLine();
        System.out.println(msg);
    }
    
}
