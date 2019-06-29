package net;

import java.io.*;
import java.net.Socket;

public class Handler implements Runnable {
    private Socket client;
    private DataInputStream in;
    private DataOutputStream out;

    public Handler(Socket client) {
        this.client = client;
    }

    public void run() {
        try {
            out = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
            in = new DataInputStream(new BufferedInputStream(client.getInputStream()));

            while (true) {
                System.out.println("one handler is waiting for client request . . .");
                String msg = in.readUTF();
                if (msg.equals("Close")) {
                    System.out.println("Server Closed.");
                    break;
                } else if (msg.equals("SendProfile")) {
                    sendProfile();
                }
                out.flush();
            }

            in.close();
            out.close();
        } catch (Exception e) {
            System.out.println("Server encountered a problem ...");
        }
    }

    private void sendProfile() {
        //todo send profile name and last played song name.
    }
}