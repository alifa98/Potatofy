package net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Serverside {
    // initialize socket and input stream
    private Socket socket;
    private ServerSocket server;
    private ExecutorService ex;

    // constructor with port
    public Serverside(int port) throws IOException {
        server = new ServerSocket(port);
        System.out.println("LOG : Server Created on Port " + port);
        ex = Executors.newCachedThreadPool();
    }

    public void starter() throws IOException {
        while (true) {
            socket = server.accept();
            ex.submit(new Handler(socket));
        }
    }
}
