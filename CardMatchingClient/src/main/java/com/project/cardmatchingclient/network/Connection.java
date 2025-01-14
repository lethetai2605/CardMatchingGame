package com.project.cardmatchingclient.network;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection {

    public final static String SERVER_ADDRESS = "0.tcp.ngrok.io";
    public final static int SERVER_PORT = 14057;

    private static Socket socket = null;
    private static DataInputStream is = null;
    private static DataOutputStream os = null;

    public static boolean initialize() {

        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT); // Connect to server
            System.out.println("Connected: " + socket);

            is = new DataInputStream(socket.getInputStream());
            os = new DataOutputStream(socket.getOutputStream());

            return true;
        } catch (IOException ie) {
            System.out.println("Can not connect to server");
            return false;
        }
    }

    public static void send(String s) throws IOException{
        os.writeUTF(s);
        os.flush();
        System.out.println("Send to server: " + s);
    }

    public static String receive() throws IOException{
        String receivedLine = is.readUTF();
        System.out.println("Received from server: " + receivedLine);
        return receivedLine;
    }

}
