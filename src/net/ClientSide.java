package net;
import java.net.*;
import java.util.*;
import java.io.*;

public class ClientSide {

    // initialize socket and input output streams
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;


    public ClientSide(String address, int port) throws Exception {
        socket = new Socket(address, port);
        System.out.println("Connected");
        out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            //todo event listeners calls some methods in this class
        socket.close();
        out.close();
    }

}
