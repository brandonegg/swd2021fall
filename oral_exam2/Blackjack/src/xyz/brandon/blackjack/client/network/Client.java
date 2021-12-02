package xyz.brandon.blackjack.client.network;// Java implementation for a client
// Save file as Client.java

import java.io.*;
import java.net.*;
import java.util.Scanner;

// Client class
public class Client {

    private InetAddress address;
    private int port;
    private DataInputStream dis;
    private DataOutputStream dos;
    private Scanner scn;
    private Socket s;
    private boolean connected;

    public Client(InetAddress address, int port) {
        this.address = address;
        this.port = port;
        dis = null;
        dos = null;
        scn = null;
        s = null;
        connected = false;
    }

    public void connect() throws IOException
    {
        if (connected) {
            System.out.println("A connection has already been established!");
            return;
        }
        try
        {
            scn = new Scanner(System.in);

            // establish the connection with server port 5056
            s = new Socket(address, port);

            // obtaining input and out streams
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());

            connected = true;

            // the following loop performs the exchange of
            // information between client and client handler
            /*
            while (true)
            {
                System.out.println(dis.readUTF());
                String tosend = scn.nextLine();
                dos.writeUTF(tosend);

                // If client sends exit,close this connection
                // and then break from the while loop
                if(tosend.equals("Exit"))
                {
                    System.out.println("Closing this connection : " + s);
                    s.close();
                    System.out.println("Connection closed");
                    break;
                }

                // printing date or time as requested by client
                String received = dis.readUTF();
                System.out.println(received);
            }

            // closing resources
            scn.close();
            dis.close();
            dos.close();*/
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            s.close();
            scn.close();
            dis.close();
            dos.close();
            connected = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        return connected;
    }

    public boolean sendString(String identifier, String args) {
        boolean success = false;
        try {
            dos.writeUTF(identifier + "=" + args); //send string to server
            dos.flush();
            String recieved = dis.readUTF();
            System.out.println(recieved.toString());
            if (recieved.equals(identifier)) {
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    public void listenFor(String identifier, String args) {
        //Wait until it gets a specific response from server (like username=turn:activate)
    }

    @Override
    public String toString() {
        if (isConnected()) {
            return "Client connected to: " +address.toString() + ":"+port;
        } else {
            return "Client is not connected";
        }
    }
}